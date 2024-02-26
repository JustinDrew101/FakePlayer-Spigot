package com.drew.fakeplayer;

import com.drew.fakeplayer.commands.Create;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {
    private FakePlayer plugin;

    public Listeners(FakePlayer plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for (ServerPlayer fakyplayer : Create.fakeplayers) {
            CraftPlayer craftplayer = (CraftPlayer) player;
            ServerPlayer sp = craftplayer.getHandle();
            sp.connection.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, fakyplayer));
            sp.connection.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED, fakyplayer));
        }
    }
}
