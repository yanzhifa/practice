package com.ldy.command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.apache.commons.collections4.MultiValuedMap;

public class CommandTest {

    @Parameter(names = {"--length", "-l"})
    int length;

    @Parameter(names = {"--pattern", "-p"}, password = true)
    String password;

    public static void main(String... argv) {
        CommandTest main = new CommandTest();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);
        main.run();
    }

    public void run() {
        System.out.printf("%d %d", length, password);
    }
}
