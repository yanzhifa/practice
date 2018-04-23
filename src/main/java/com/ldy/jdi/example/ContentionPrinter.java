package com.ldy.jdi.example;


import com.ldy.jdi.jdiscript.JDIScript;
import com.ldy.jdi.jdiscript.handlers.OnVMStart;
import com.ldy.jdi.jdiscript.util.VMSocketAttacher;
import com.sun.jdi.VirtualMachine;

import static com.ldy.jdi.example.ConnectorInspector.println;

class ContentionPrinter {   
    public static void main(String[] args) {
        VirtualMachine vm = new VMSocketAttacher(12345).attach();
        JDIScript j = new JDIScript(vm);

        j.monitorContendedEnterRequest(e -> {
            j.printTrace(e, "ContendedEnter for "+e.monitor());
        }).enable();

        j.monitorContendedEnteredRequest(e -> {
            long timestamp = System.currentTimeMillis();
            println(timestamp+": "+e.thread()+": ContendedEntered for "+e.monitor());
        }).enable();
        
        j.run((OnVMStart) e -> { println("Got StartEvent"); });

        println("Shutting down");
    }
}


