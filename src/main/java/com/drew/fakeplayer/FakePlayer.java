package com.drew.fakeplayer;

import com.drew.fakeplayer.commands.Create;
import com.drew.fakeplayer.commands.Remove;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FakePlayer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        new Create(this);
        new Listeners(this);
        new Remove(this);
        System.out.println(ChatColor.GREEN+"Fake Player Plugin Has been started!");
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.GREEN+"Fake Player Plugin Has been stopped!");
    }
}
