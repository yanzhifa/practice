package com.ldy.json;

public enum Type {
    A("a"), MANAGEMENT("m"), B("b");

    String val;

    Type(String val) {
        this.val = val;
    }

    public String value() {
        return val;
    }
}
