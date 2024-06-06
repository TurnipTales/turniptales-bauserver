package net.turniptales.buildingserver.listener;

import net.kyori.adventure.text.Component;
import net.turniptales.buildingserver.BuildingServer;
import net.turniptales.buildingserver.ScoreBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.sendMessage(Component.text(BuildingServer.getPrefix() + "§7Willkommen zurück §b" + p.getName()));
        e.joinMessage(Component.text(BuildingServer.getPrefix() + "§b" + p.getName() + "§7 hat den Bauserver betreten."));
        ScoreBoard.setScoreboard(p);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.quitMessage(Component.text(BuildingServer.getPrefix() + "§b" + p.getName() + "§7 hat den Bauserver verlassen."));
    }

}
