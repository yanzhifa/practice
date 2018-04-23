package com.ldy.jdi.example;


import com.ldy.jdi.jdiscript.JDIScript;
import com.ldy.jdi.jdiscript.util.VMLauncher;

import static com.ldy.jdi.jdiscript.util.Utils.unchecked;

public class HelloWorldExample {
    
    public static void main(final String[] args) {
        String OPTIONS = "-cp ./target/classes/";
        String MAIN = "com.ldy.jdi.example.HelloWorld";

        JDIScript j = new JDIScript(new VMLauncher(OPTIONS, MAIN).start());
        
        j.onFieldAccess("com.ldy.jdi.example.HelloWorld", "helloTo", e -> {
            j.onStepInto(e.thread(), j.once(se -> {
                unchecked(() -> e.object().setValue(e.field(), 
                                                 j.vm().mirrorOf("JDIScript!99")));
            }));
        });

        j.run();
    }
}
