package com.ldy.password;

import org.apache.commons.lang3.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * pam_passwdqc.8 is under the 3-clause BSD-style license as specified
 * within the file itself.
 * <p>
 * wordset_4k.c is in the public domain.
 * <p>
 * The rest of the files in this package fall under the following terms:
 * <p>
 * You're allowed to do whatever you like with this software (including
 * re-distribution in source and/or binary form, with or without
 * modification), provided that credit is given where it is due and any
 * modified versions are marked as such.  There's absolutely no warranty.
 * <p>
 * Note that you don't have to re-distribute this software under these
 * same relaxed terms.  In particular, you're free to place modified
 * versions under (L)GPL, thus disallowing further re-distribution in
 * binary-only form.
 * <p>
 * $Owl: Owl/packages/pam_passwdqc/pam_passwdqc/LICENSE,v 1.3 2005/11/16 13:28:57 solar Exp $
 */
public class PasswdQcCheck {

    public static final String REASON_WORD = "REASON_WORD";
    public static final String REASON_ERROR = "REASON_ERROR";
    public static final String REASON_SEQ = "REASON_SEQ";
    public static final String REASON_SAME = "REASON_SAME";
    public static final String REASON_LONG = "REASON_LONG";
    public static final String REASON_SIMPLE = "REASON_SIMPLE";
    public static final String REASON_SIMPLESHORT = "REASON_SIMPLESHORT";
    public static final String REASON_SHORT = "REASON_SHORT";
    public static final String REASON_SIMILAR = "REASON_SIMILAR";
    public static final String REASON_PERSONAL = "REASON_PERSONAL";

    private static Logger logger = LoggerFactory.getLogger(PasswdQcCheck.class);

    private static int FIXED_BITS = 15;

    /**
     * Calculates the expected number of different characters for a random
     * password of a given length.  The result is rounded down.  We use this
     * with the _requested_ minimum length (so longer passwords don't have
     * to meet this strict requirement for their length).
     *
     * @param charset
     * @param length
     * @return
     */
    private static int expectedDifference(int charset, int length) {
        // The c implementation uses an unsigned long here...
        // So this could be completely wrong I've run some tests and it seems to work fine though.
        long x, y, z;
        x = ((long) (charset - 1) << FIXED_BITS) / charset;
        y = x;
        while (--length > 0) {
            y = (y * x) >> FIXED_BITS;
        }

        z = (long) charset * ((1L << FIXED_BITS) - y);
        int diff = (int) (z >> FIXED_BITS); // I've pulled this out to a variable so it's easier for me to see while debugging
        return diff;
    }

    /**
     * A password is too simple if it is too short for its class, or doesn't
     * contain enough different characters for its class, or doesn't contain
     * enough words for a passphrase.
     *
     * @param params
     * @param newPassword
     * @return
     */
    public static int isSimple(PasswdQcParams params, String newPassword) {
        int length, classes, words, chars;
        int digits, lowers, uppers, others, unknowns;
        char p = ' ';
        char c = ' ';

        length = classes = words = chars = 0;
        digits = lowers = uppers = others = unknowns = 0;
        p = ' ';

        // Can't use a while loop the way the C version does...
        char[] newPasswordCharacters = newPassword.toCharArray();

        for (int i = 0; i < newPasswordCharacters.length; i++) {
            c = newPasswordCharacters[i];
            length++;

            if (!isAscii(c)) {
                unknowns++;
            } else if (isDigit(c)) {
                digits++;
            } else if (isLower(c)) {
                lowers++;
            } else if (isUpper(c)) {
                uppers++;
            } else {
                others++;
            }


			/*
             * A word starts when a letter follows a non-letter or when a non-ASCII
			 * character follows a space character.  We treat all non-ASCII characters
			 * as non-spaces, which is not entirely correct (there's the non-breaking
			 * space character at 0xa0, 0x9a, or 0xff), but it should not hurt. */

            if (isAscii(c) && isAlpha(c) && isAscii(p) && !isAlpha(p)) {
                words++;
            }

            p = c;

			/* Count this character just once: when we're not going to see it anymore */
            if (newPassword.indexOf(c, length) == -1) {
                chars++;
            }
        }

        // Yes in Java-land we could have checked this using string length...
        if (length == 0) {
            return 1;
        }


		/* Upper case characters and digits used in common ways don't increase the
         * strength of a password */
        c = newPassword.charAt(0);
        if (uppers != 0 && isAscii(c) && isUpper(c)) {
            uppers--;
        }

        c = newPassword.charAt(newPassword.length() - 1);
        if (digits != 0 && isAscii(c) && isDigit(c)) {
            digits--;
        }

		/* Count the number of different character classes we've seen.  We assume
         * that there are no non-ASCII characters for digits. */
        if (digits != 0) {
            classes++;
        }

        if (lowers != 0) {
            classes++;
        }

        if (uppers != 0) {
            classes++;
        }

        if (others != 0) {
            classes++;
        }

        if (unknowns != 0 && (classes == 0 || (digits != 0 && classes == 1))) {
            classes++;
        }

        for (; classes > 0; classes--) {
            switch (classes) {
                case 1:
                    if (length >= params.getMin()[0] &&
                            chars >= expectedDifference(10, params.getMin()[0]) - 1) {
                        return 0;
                    }
                    return 1;
                case 2:
                    if (length >= params.getMin()[1] &&
                            chars >= expectedDifference(36, params.getMin()[1] - 1)) {
                        return 0;
                    }

                    if (!(params.getPassphraseWords() > 0) ||
                            words < params.getPassphraseWords()) {
                        continue;
                    }

                    if (length >= params.getMin()[2] &&
                            chars >= expectedDifference(27, params.getMin()[2] - 1)) {
                        return 0;
                    }
                    continue;

                case 3:
                    if (length >= params.getMin()[3] &&
                            chars >= expectedDifference(62, params.getMin()[3] - 1)) {
                        return 0;
                    }
                    continue;

                case 4:
                    if (length >= params.getMin()[4] &&
                            chars >= expectedDifference(95, params.getMin()[4] - 1)) {
                        return 0;
                    }
                    continue;
            }
        }

        return 1;
    }

    /**
     * This method is a little bit different to the C one because java strings are immutable.
     * It makes all the characters in the string lowercase (Unlike the later version which converts to leet)
     *
     * @param input
     * @return
     */
    private static String unify(String input) {
        // I use a StringBuilder here to try and maintain efficiency
        StringBuilder sb = new StringBuilder();
        for (char character : input.toCharArray()) { // Change from for loop C version uses.
            char c = isAscii(character) ? Character.toLowerCase(character) : character;

            sb.append(c);
        }
        return sb.toString();
    }

    private static String leetify(String input) {
        // I use a StringBuilder here to try and maintain efficiency
        StringBuilder sb = new StringBuilder();
        for (char character : input.toCharArray()) { // Change from for loop C version uses.
            char c = isAscii(character) ? Character.toLowerCase(character) : character;
            switch (c) {
                case 'a':
                case '@':
                    c = '4';
                    break;
                case 'e':
                    c = '3';
                    break;
                case 'i':
                case '|':
                    c = '!';
                    break;
        /* Unfortunately, if we translate both 'i' and 'l' to '1', this would
         * associate these two letters with each other - e.g., "mile" would
         * match "MLLE", which is undesired.  To solve this, we'd need to test
         * different translations separately, which is not implemented yet. */
                case 'l':
                    c = '1';
                    break;
                case 'o':
                    c = '0';
                    break;
                case 's':
                case '$':
                    c = '5';
                    break;
                case 't':
                case '+':
                    c = '7';
                    break;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String reverse(String src) {
        return new StringBuilder(src).reverse().toString();
    }

    /**
     * Needle is based on haystack if both contain a long enough common
     * substring and needle would be too simple for a password with the
     * substring either removed.
     *
     * @param params
     * @param haystack
     * @param needle
     * @param original
     * @return
     */
    private static int isBased(PasswdQcParams params, String haystack, String needle, String original) {
        return isBased(params, haystack, needle, original, false);
    }

    private static int isBased(PasswdQcParams params, String haystack, String needle, String original, boolean leet) {
        String scratch;
        int length;
        int i, j;
        char p;
        int match = 0;

        // This is so we can allow a little flexibility for words that are leet.
        int matchLength = params.getMatchLength() + (leet ? 1 : 0);

        if (params.getMatchLength() == 0) {
            return 0; // disabled
        }

        if (params.getMatchLength() < 0) {
            return 1; // misconfigured (Log this?)
        }

        if (haystack.indexOf(needle) >= 0) { // closest I can get as the C version uses strstr which returns a pointer.
            return 1;
        }

        scratch = null;

        length = needle.length();
        for (i = 0; i <= length - matchLength; i++) {
            for (j = matchLength; i + j <= length; j++) {
                match = 0;
                for (int k = 0; k < haystack.length(); k++) {
                    p = haystack.charAt(k);
                    if (p == needle.charAt(i) && strncmp(haystack.substring(k), needle.substring(i), j) == 0) {
                        match = 1;
                        scratch = original.substring(0, i) + original.substring(i + j);
                        if (isSimple(params, scratch) != 0) {
                            return 1;
                        }
                    }

                }
                if (match == 0) {
                    break;
                }
            }
        }
        return 0;
    }

    /*
     * This wordlist check is now the least important given the checks above
     * and the support for passphrases (which are based on dictionary words,
     * and checked by other means).  It is still useful to trap simple short
     * passwords (if short passwords are allowed) that are word-based, but
     * passed the other checks due to uncommon capitalization, digits, and
     * special characters.  We (mis)use the same set of words that are used
     * to generate random passwords.  This list is much smaller than those
     * used for password crackers, and it doesn't contain common passwords
     * that aren't short English words.  Perhaps support for large wordlists
     * should still be added, even though this is now of little importance.
     */
    private static int isWordBased(PasswdQcParams params, String needle, String original) {
        //String word = "";
        String unified;
        String leetWord;
        String leetNeedle = leetify(needle);
        int i;
        for (String word : Wordset4k.passwdqcWordset4k) {
            if (word.length() < params.getMatchLength()) {
                continue;
            }
            unified = unify(word);
            if (isBased(params, unified, needle, original) != 0) {
                return 1;
            }

            // okooheji: added this after QA testing.
            leetWord = leetify(word);
            if (isBased(params, leetWord, leetNeedle, original, true) != 0) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * @param params
     * @param newPass
     * @param oldPass
     * @param pw      Struct with details from passwd file
     * @return
     */
    public static String passwdQcCheck(PasswdQcParams params, String newPass, String oldPass, Passwd pw) {
        String truncated;
        String reversed;
        String uNewPass = null;
        String uReversed = null;
        String uOldPass = null;


        // okooheji: I'm adding the leet checking code here:
        // this is not in the version of PasswdQC we based this on.
        // BUT: We seem to be allowing passwords that are leet versions of dictionary words.
        String lNewPass = null;
        String lReversed = null;
        String lOldPass = null;

        String uName = null;
        String uGecos = null;
        String uDir = null;
        String reason;
        int length;
        try {


            reason = null;

            if (oldPass.equals(newPass)) {
                return REASON_SAME;
            }

            length = newPass.length();

            if (length < params.getMin()[4]) {
                return REASON_SHORT;
            }

            if (length > params.getMax()) {
                if (params.getMax() == 8) {
                    truncated = newPass.substring(0, 8);
                    newPass = truncated;
                    if (oldPass.equals(newPass)) {
                        return REASON_SAME;
                    }
                } else {
                    return REASON_LONG;
                }
            }

            if (isSimple(params, newPass) != 0) {
                if (length < params.getMin()[1] && params.getMin()[1] <= params.getMax()) {
                    reason = REASON_SIMPLESHORT;
                } else {
                    reason = REASON_SIMPLE;
                }
            }

            reversed = reverse(newPass);
            if (reason == null) { // keep on going!

                if (reversed != null) {
                    uNewPass = unify(newPass);
                    lNewPass = leetify(newPass);
                    if (uNewPass == null) {
                        return reason;  /* REASON_ERROR */
                    }

                    uReversed = reverse(uNewPass);
                    lReversed = unify(uReversed);
                    if (uReversed == null) {
                        return reason; /* REASON_ERROR */
                    }

                    if (oldPass != null) {
                        uOldPass = unify(oldPass);
                        lOldPass = leetify(oldPass);
                    }

                    if (pw != null) {
                        uName = unify(pw.getName());
                        uGecos = unify(pw.getGecos());
                    }
                }

                if (reversed == null ||
                        uNewPass == null || uReversed == null ||
                        (oldPass != null && uOldPass == null) ||
                        (pw != null && uName == null)) { // Ignoring uGecos at the moment.
                    reason = REASON_ERROR;
                }

                if ((reason == null) &&
                        (lNewPass == null || lReversed == null ||
                                (oldPass != null && lOldPass == null))) {
                    reason = REASON_ERROR;
                }

                if ((reason == null) &&
                        (oldPass != null) &&
                        (params.getSimilarDeny()) != 0 &&
                        (isBased(params, uOldPass, uNewPass, newPass) != 0 ||
                                isBased(params, uOldPass, uReversed, reversed) != 0 ||
                                isBased(params, lOldPass, lNewPass, newPass, true) != 0 || // allow for a match one character longer if we are matching the leet version.
                                isBased(params, lOldPass, lReversed, reversed, true) != 0)) {
                    reason = REASON_SIMILAR;
                }

                logger.info("checking against personal info...");
                if (reason == null && (pw != null &&
                        (isBased(params, uName, uNewPass, newPass) != 0 ||
                                isBased(params, uName, uReversed, reversed) != 0))) {
                    // Ignoring gecos for now becuase we can't get them for remote users or users that haven't been created
                    reason = REASON_PERSONAL;
                }
            }

            if (reason == null && newPass.length() < params.getMin()[2] &&
                    (isWordBased(params, uNewPass, newPass) != 0 ||
                            isWordBased(params, uReversed, reversed) != 0)) {
                reason = REASON_WORD;
            }
        } catch (Exception e) {
            reason = REASON_ERROR;
        }
        return reason;
    }

    /**
     * As close to the C implementation of strncmp as I can get, from C docs:
     * > Compares up to num characters of the C string str1 to those of the C string str2.
     * > This function starts comparing the first character of each string.
     * > If they are equal to each other, it continues with the following pairs until the characters differ,
     * > until a terminating null-character is reached, or until num characters match in both strings, whichever happens first.
     * <p>
     * NOTE: We don't worry about
     *
     * @param str1  First string to compare
     * @param str2  Second string to compare
     * @param count Maximum number of characters to compare.
     * @return 0 if they match <0: firs character that doesn't match has a lower value in str1 than str2 >0: the first character that doesn't match has a greater value in str1 than str2
     */
    public static int strncmp(String str1, String str2, int count) {
        // Because C strings are just null terminated arrays of char we need to add one because the algorithm assumed that when you reach the end of the string
        // you will end up comparing a character against \0 and thus not get a match.
        String str1nullTerminated = str1 + '\0';
        String str2nullTerminated = str2 + '\0';
        char[] longerArray = str1nullTerminated.length() >= str2nullTerminated.length() ? str1nullTerminated.toCharArray() : str2nullTerminated.toCharArray();
        char[] shortArray = str1nullTerminated.length() >= str2nullTerminated.length() ? str2nullTerminated.toCharArray() : str1nullTerminated.toCharArray();
        for (int i = 0; i < shortArray.length && i < count; i++) {
            if (shortArray[i] != longerArray[i]) {
                return str1nullTerminated.charAt(i) - str2nullTerminated.charAt(i);
            }
        }
        return 0;
    }

    private static boolean isSpace(char c) {
        return Character.isWhitespace(c);
    }

    private static boolean isAlpha(char c) {
        return Character.isLetter(c);
    }


    private static boolean isUpper(char c) {
        return Character.isUpperCase(c);
    }

    private static boolean isLower(char c) {
        return Character.isLowerCase(c);
    }

    private static boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private static boolean isAscii(char c) {
        return CharUtils.isAscii(c);
    }

}
