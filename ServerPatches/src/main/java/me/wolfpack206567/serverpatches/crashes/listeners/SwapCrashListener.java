package me.wolfpack206567.serverpatches.crashes.listeners;

import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.SimplePacketListenerAbstract;
import com.github.retrooper.packetevents.event.simple.PacketPlayReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import dev.dejvokep.boostedyaml.YamlDocument;
import me.wolfpack206567.serverpatches.api.CrashEvent;
import me.wolfpack206567.serverpatches.api.CrashType;
import me.wolfpack206567.serverpatches.utils.InvalidPacketKicker;

public class SwapCrashListener extends SimplePacketListenerAbstract {
    private final InvalidPacketKicker invalidPacketKicker;
    private final YamlDocument config;

    public SwapCrashListener(InvalidPacketKicker invalidPacketKicker, YamlDocument config) {
        super(PacketListenerPriority.NORMAL);
        this.invalidPacketKicker = invalidPacketKicker;
        this.config = config;
    }

    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event) {
        if (event.getPacketType() != PacketType.Play.Client.CLICK_WINDOW) return;
        User user = event.getUser();
        int button = new WrapperPlayClientClickWindow(event).getButton();
        if (button < 0 || button > 40) {
            event.setCancelled(true);
            invalidPacketKicker.kickUser(user, config.getString("click-swap-exploit.kick-message"), new CrashEvent(user, CrashType.SWAP_CRASH));
        }
    }
}