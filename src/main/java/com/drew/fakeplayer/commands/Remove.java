package com.drew.fakeplayer.commands;

import com.drew.fakeplayer.FakePlayer;
import net.minecraft.network.protocol.game.ClientboundCustomChatCompletionsPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Remove implements CommandExecutor {
    private FakePlayer plugin;

    public Remove(FakePlayer plugin){
        this.plugin = plugin;
        plugin.getCommand("fk_remove").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.sendMessage(ChatColor.GREEN+"Success: All Fake Players Have Been Removed!");
        Create.fakeplayers.clear();
        return true;
    }
}
