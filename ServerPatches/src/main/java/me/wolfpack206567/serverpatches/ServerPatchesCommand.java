package me.wolfpack206567.serverpatches;

import dev.dejvokep.boostedyaml.YamlDocument;
import me.wolfpack206567.serverpatches.crashes.CrashManager;
import me.wolfpack206567.serverpatches.utils.TextUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.logging.Logger;

public class ServerPatchesCommand implements CommandExecutor {
    private final static Logger logger = Logger.getLogger("ServerPatchesCommand");

    private final YamlDocument config;
    private final CrashManager crashManager;

    public ServerPatchesCommand(YamlDocument config, CrashManager crashManager) {
        this.config = config;
        this.crashManager = crashManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission("serverpatches.command.reload")) {
            sender.sendMessage(TextUtil.formatColor(config.getString("Misc.no_permission")));
            return true;
        }
        if (strings.length == 0 || !strings[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(TextUtil.formatColor("&7Available Commands: &d/spatches reload"));
            return true;
        }
        try {
            config.reload();
        } catch (IOException e) {
            logger.severe("Failed to reload config:");
            e.printStackTrace();
            sender.sendMessage(Component.text("Something went wrong :[ while reloading the config, please check the console", NamedTextColor.RED));
            return true;
        }
        crashManager.reload();
        sender.sendMessage(TextUtil.formatColor(config.getString("Misc.config_reloaded")));
        return true;
    }
}
