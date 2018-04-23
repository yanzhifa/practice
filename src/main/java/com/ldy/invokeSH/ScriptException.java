package com.ldy.invokeSH;

public class ScriptException extends Exception {

    private static final long serialVersionUID = 432784879328479L;

    public ScriptException(String message) {
        super(message);
    }

    public ScriptException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScriptException(Throwable cause) {
        super(cause);
    }

}
