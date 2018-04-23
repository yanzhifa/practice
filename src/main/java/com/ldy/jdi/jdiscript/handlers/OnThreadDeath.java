package com.ldy.jdi.jdiscript.handlers;

import com.sun.jdi.event.ThreadDeathEvent;

@FunctionalInterface
public interface OnThreadDeath extends DebugEventHandler
{
    void threadDeath(ThreadDeathEvent event);
}
