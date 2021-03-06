package com.ldy.jdi.jdiscript.handlers;

import com.sun.jdi.event.BreakpointEvent;

@FunctionalInterface
public interface OnBreakpoint extends DebugLocatableHandler
{
    void breakpoint(BreakpointEvent event);
}
