package net.turniptales.buildingserver.listener;

import net.turniptales.buildingserver.ScoreBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.AQUA;
import static net.kyori.adventure.text.format.NamedTextColor.GRAY;
import static net.turniptales.buildingserver.BuildingServer.prefix;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(prefix
                .append(text("Willkommen zurück", GRAY)).appendSpace()
                .append(text(player.getName(), AQUA)));

        event.joinMessage(prefix
                .append(text(player.getName(), AQUA)).appendSpace()
                .append(text("hat den Bauserver betreten.", GRAY)));

        ScoreBoard.setAllPlayerTeams();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.quitMessage(prefix
                .append(text(event.getPlayer().getName(), AQUA)).appendSpace()
                .append(text("hat den Bauserver verlassen.", GRAY)));
    }
}
