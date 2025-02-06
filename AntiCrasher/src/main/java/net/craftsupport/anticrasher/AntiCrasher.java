package net.craftsupport.anticrasher;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import net.craftsupport.anticrasher.commands.reloadCommand;
import net.craftsupport.anticrasher.packet.TabCompleteListener;
import net.craftsupport.anticrasher.packet.WindowListener;
import net.craftsupport.anticrasher.utils.utils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public final class AntiCrasher extends JavaPlugin {
    private boolean isPAPIEnabled;

    public utils utilsInstance;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().getSettings().reEncodeByDefault(false)
                .checkForUpdates(true)
                .kickOnPacketException(true);
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN + "AntiCrasher has been enabled!");

        isPAPIEnabled = getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");

        saveDefaultConfig();

        utilsInstance = new utils(this);
        getCommand("acreload").setExecutor(new reloadCommand(this, utilsInstance));

        PacketEvents.getAPI().getEventManager().registerListener(new WindowListener(this, utilsInstance), PacketListenerPriority.LOWEST);
        PacketEvents.getAPI().getEventManager().registerListener(new TabCompleteListener(this, utilsInstance), PacketListenerPriority.LOWEST);
        PacketEvents.getAPI().init();
    }


    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "AnitCrasher is being disabled");
        PacketEvents.getAPI().terminate();
    }

    public boolean isPAPIEnabled() {
        return isPAPIEnabled;
    }
}