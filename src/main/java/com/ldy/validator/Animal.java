package com.ldy.validator;

public interface Animal {
    @NotEmpty
    String getName();

    @NotEmpty
    String getOwnerName();
}
