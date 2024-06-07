package net.turniptales.buildingserver.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.turniptales.buildingserver.ScoreBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static org.bukkit.Bukkit.broadcast;

public class ChatListener implements Listener {

    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        ScoreBoard.PlayerListTeam playerListTeam = ScoreBoard.PlayerListTeam.getPlayerListTeam(player);
        event.setCancelled(true);
        broadcast(playerListTeam.getChatMessage(player, event.message()));
    }
}
