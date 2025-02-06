package me.wolfpack206567.serverpatches.utils;

import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerDisconnect;
import me.wolfpack206567.serverpatches.api.CrashEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class InvalidPacketKicker {
    private final Plugin plugin;

    public InvalidPacketKicker(Plugin plugin) {
        this.plugin = plugin;
    }

    public void kickUser(User user, String reason, CrashEvent event) {
        user.sendPacket(new WrapperPlayServerDisconnect(me.wolfpack206567.serverpatches.utils.TextUtil.formatColor(reason)));
        user.closeConnection();
        Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().callEvent(event));
    }
}
