package com.drew.fakeplayer.commands;

import com.drew.fakeplayer.FakePlayer;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Create implements CommandExecutor {
    private FakePlayer plugin;
    public static List<ServerPlayer> fakeplayers = new ArrayList<>();

    public Create(FakePlayer plugin){
        this.plugin = plugin;
        plugin.getCommand("fk_create").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.isOp()){
            if (args.length != 0) {
                if (args[0] instanceof String) {
                    CraftPlayer craftplayer = (CraftPlayer) sender;
                    ServerPlayer sp = craftplayer.getHandle();
                    MinecraftServer server = sp.getServer();
                    ServerLevel level = sp.getLevel();
                    GameProfile gameProfile = new GameProfile(UUID.randomUUID(), args[0]);
                    ServerPlayer npc = new ServerPlayer(server, level, gameProfile);
                    ServerGamePacketListenerImpl ps = sp.connection;
                    fakeplayers.add(npc);
                    player.sendMessage(ChatColor.GREEN + args[0]+" Has Been Added to the Tab-list.");
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        CraftPlayer craftOnlinePlayer = (CraftPlayer) onlinePlayer;
                        ServerPlayer spOnlinePlayer = craftOnlinePlayer.getHandle();
                        spOnlinePlayer.connection.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc));
                        spOnlinePlayer.connection.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED, npc));
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Error: Name should be a string.");
                }
            }else{
                player.sendMessage(ChatColor.RED+"Error: There was no name provided.");
            }
            }else{
                player.sendMessage(ChatColor.RED+"Error: Only Admins can run this command.");
            }
            return true;
        }else{
            sender.sendMessage("Error: This command is only for player.");
        }
        return true;
    }
}
