package com.ldy.jdi.jdiscript.handlers;

import com.sun.jdi.event.AccessWatchpointEvent;

@FunctionalInterface
public interface OnAccessWatchpoint extends DebugWatchpointHandler
{
    void accessWatchpoint(AccessWatchpointEvent event);
}
