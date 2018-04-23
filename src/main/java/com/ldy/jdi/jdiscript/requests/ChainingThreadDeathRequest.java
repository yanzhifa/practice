package com.ldy.jdi.jdiscript.requests;


import com.ldy.jdi.jdiscript.handlers.OnThreadDeath;
import com.sun.jdi.ThreadReference;

public interface ChainingThreadDeathRequest extends JDIScriptEventRequest {

    //Chaining EventRequest methods
    ChainingThreadDeathRequest addCountFilter(int count);
    ChainingThreadDeathRequest disable();
    ChainingThreadDeathRequest enable();
    ChainingThreadDeathRequest putProperty(Object key, Object value);
    ChainingThreadDeathRequest setEnabled(boolean val);
    ChainingThreadDeathRequest setSuspendPolicy(int policy);

    //Chaining ThreadDeathRequest methods
    ChainingThreadDeathRequest addThreadFilter(ThreadReference instance);

    ChainingThreadDeathRequest addHandler(OnThreadDeath handler);
}
