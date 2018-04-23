package com.ldy.invokeSH;

/**
 * A flagged parameter to a script that will be executed.
 * A flagged parameter has a plaintext flag and a potentially sensitive parameter:
 * for example '--username user' or '-p password'
 */
class FlaggedScriptParameter extends ScriptParameter {
    /**
     * The plaintext flag parameter
     */
    final String flag;

    /**
     * The sensitive
     */
    final String parameter;

    /**
     * We use a factory style to prevent accidental leaking of sensitive information.
     * This is why this constructor is PACKAGE local.
     */
    FlaggedScriptParameter(String flag, String parameter, boolean sensitive) {
        this.flag = flag;
        this.parameter = parameter;
        super.sensitive = sensitive;
    }

    @Override
    public String[] getPlaintext() {
        return new String[] {flag, parameter};
    }

    @Override
    public String[] getPlainTextEscaped() {
        return new String[] {flag, escape(parameter)};
    }

    /**
     * This will return the plaintext if the parameter is not sensitive, or mask it out
     * if it is.
     */
    @Override
    public String toString() {
        if (this.sensitive) {
            return this.flag + " ****";
        } else {
            return this.flag + " " + this.parameter;
        }
    }
}