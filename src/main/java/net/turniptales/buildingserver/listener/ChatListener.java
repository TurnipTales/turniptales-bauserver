package net.turniptales.buildingserver.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onWrite(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();

        if (p.hasPermission("prefix.admin")) {
            e.setFormat("§4Admin §8| §7" + p.getName() + " §8»§7 " + e.getMessage());
            return;
        }
        if (p.hasPermission("prefix.moderator")) e.setFormat("§3Moderator §8| §7" + p.getName() + " §8»§7 " + e.getMessage());
        if (p.hasPermission("prefix.supporter")) e.setFormat("§bSupporter §8| §7" + p.getName() + " §8»§7 " + e.getMessage());
        if (p.hasPermission("prefix.builder")) e.setFormat("§eBuilder §8| §7" + p.getName() + " §8»§7 " + e.getMessage());

    }
}
