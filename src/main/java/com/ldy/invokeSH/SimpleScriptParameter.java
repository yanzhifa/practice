package com.ldy.invokeSH;

/**
 * A parameter to a script that will be executed.
 */
public class SimpleScriptParameter extends ScriptParameter {


    /**
     * The plaintext script parameter
     */
    final String plaintext;


    /**
     * We use a factory style to prevent accidental leaking of sensitive information.
     * This is why this constructor is PACKAGE local.
     */
    SimpleScriptParameter(String plaintext, boolean sensitive) {
        this.plaintext = plaintext;
        super.sensitive = sensitive;
    }


    @Override
    public String[] getPlaintext() {
        return new String[] { plaintext };
    }

    @Override
    public String[] getPlainTextEscaped() {
        return new String[] {escape(plaintext)};
    }

    /**
     * This will return the plaintext if the parameter is not sensitive, or mask it out
     * if it is.
     */
    @Override
    public String toString() {
        if (this.sensitive) {
            return "****";
        } else {
            return this.plaintext;
        }
    }


}
