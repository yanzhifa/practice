package com.ldy.java8;

import com.ldy.invokeSH.SimpleScriptParameter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.translate.AggregateTranslator;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.EntityArrays;
import org.apache.commons.lang3.text.translate.LookupTranslator;
import org.apache.commons.lang3.text.translate.UnicodeEscaper;

public class StringTest {

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
    protected static String escape(String plaintext) {
        StringBuilder escapedStringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(plaintext)) {
            escapedStringBuilder.append('\'');
            escapedStringBuilder.append(SimpleScriptParameter.shellEscaper.translate(plaintext));
            escapedStringBuilder.append('\'');
        }
        return escapedStringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(StringEscapeUtils.escapeJava("asdf.'\""));
        System.out.println(escape("asdf.'\""));
    }
}
