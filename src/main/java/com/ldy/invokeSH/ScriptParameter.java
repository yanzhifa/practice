package com.ldy.invokeSH;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.translate.*;

/**
 * A script parameter.
 */
abstract public class ScriptParameter {

    /**
     * Whether the parameter contains sensitive information.
     */
    protected boolean sensitive;

    // Escaper for shell commands.
    public final static CharSequenceTranslator shellEscaper =
            new AggregateTranslator(
                    new LookupTranslator(
                            new String[][] {
                                    {"'", "\\'"},
                                    {"\"", "\\\""},
                                    {"\\", "\\\\"},

                            }),
                    new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE()),
                    UnicodeEscaper.outsideOf(32, 0x7f) // don't escape Java control characters in this range.
            );

    /**
     * Escapes the entire string to make it safe, all characters apart from new-line are escaped and the string is surrounded in quotes.
     * @param plaintext
     * @return
     */
    protected String escape(String plaintext) {
        StringBuilder escapedStringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(plaintext)) {
            escapedStringBuilder.append('\'');
            escapedStringBuilder.append(SimpleScriptParameter.shellEscaper.translate(plaintext));
            escapedStringBuilder.append('\'');
        }
        return escapedStringBuilder.toString();
    }

    /**
     * Get whether sensitive or not
     */
    public boolean isSensitive() {
        return sensitive;
    }

    /**
     * returns an array of the plain text elements of this script parameter.
     */
    public abstract String[] getPlaintext();

    /**
     * Get plaintext but escaped for output to shell script.
     * @return
     */
    public abstract String[] getPlainTextEscaped();

    /**
     * Benign single parameter
     * @param parameter single parameter
     */
    static public ScriptParameter benign(String parameter) {
        return new SimpleScriptParameter(parameter, false);
    }


    /**
     * Sensitive single parameter
     * @param parameter single parameter
     */
    static public ScriptParameter sensitive(String parameter) {
        return new SimpleScriptParameter(parameter, true);
    }

    /**
     * Benign flagged parameter - e.g., '--username' 'user'
     * @param flag flag - e.g. '--username'
     * @param parameter flag - e.g. 'user'
     */
    static public ScriptParameter benignFlagged(String flag, String parameter) {
        return new FlaggedScriptParameter(flag, parameter, false);
    }

    /**
     * Sensitive flagged parameter - e.g., '--password' 'password'
     * @param flag flag - e.g. '--password'
     * @param parameter flag - e.g. 'password'
     */
    static public ScriptParameter sensitiveFlagged(String flag, String parameter) {
        return new FlaggedScriptParameter(flag, parameter, true);
    }
}