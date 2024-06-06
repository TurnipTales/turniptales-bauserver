package net.turniptales.buildingserver;

import net.turniptales.buildingserver.commands.TeleportCommand;
import net.turniptales.buildingserver.commands.TeleportHereCommand;
import net.turniptales.buildingserver.listener.ChatListener;
import net.turniptales.buildingserver.listener.InteractListener;
import net.turniptales.buildingserver.listener.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BuildingServer extends JavaPlugin {
    private static final String prefix = "§bBauserver §8| §7";
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);

        this.getCommand("teleport").setExecutor(new TeleportCommand());
        this.getCommand("teleporthere").setExecutor(new TeleportHereCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static String getPrefix() {return prefix;}
}
