package net.turniptales.buildingserver;

import net.kyori.adventure.text.Component;
import net.turniptales.buildingserver.listener.ChatListener;
import net.turniptales.buildingserver.listener.InteractListener;
import net.turniptales.buildingserver.listener.JoinQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.AQUA;
import static net.kyori.adventure.text.format.NamedTextColor.DARK_GRAY;

public final class BuildingServer extends JavaPlugin {

    public static final Component prefix = empty()
            .append(text("Bauserver", AQUA)).appendSpace()
            .append(text("|", DARK_GRAY)).appendSpace();

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinQuitListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new InteractListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
