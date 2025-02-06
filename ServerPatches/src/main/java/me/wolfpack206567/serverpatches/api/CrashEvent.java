package me.wolfpack206567.serverpatches.api;

import com.github.retrooper.packetevents.protocol.player.User;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CrashEvent extends Event {
    private static final HandlerList handlerList = new HandlerList();

    private final User user;
    private final me.wolfpack206567.serverpatches.api.CrashType type;

    public CrashEvent(User user, me.wolfpack206567.serverpatches.api.CrashType type) {
        this.user = user;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public me.wolfpack206567.serverpatches.api.CrashType getType() {
        return type;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
