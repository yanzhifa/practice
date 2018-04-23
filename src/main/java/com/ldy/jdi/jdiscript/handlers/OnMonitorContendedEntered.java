package com.ldy.jdi.jdiscript.handlers;

import com.sun.jdi.event.MonitorContendedEnteredEvent;

@FunctionalInterface
public interface OnMonitorContendedEntered extends DebugLocatableHandler
{
    void monitorContendedEntered(MonitorContendedEnteredEvent event);
}
