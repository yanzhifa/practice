package com.ldy.jdi.jdiscript.handlers;

import com.sun.jdi.event.ModificationWatchpointEvent;

@FunctionalInterface
public interface OnModificationWatchpoint extends DebugWatchpointHandler
{
    void modificationWatchpoint(ModificationWatchpointEvent event);
}
