package com.ldy.jdi.jdiscript.handlers;

import com.sun.jdi.event.Event;

@FunctionalInterface
public interface OnEvent extends DebugEventHandler
{
    void event(Event event);
}
