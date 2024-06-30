package net.turniptales.buildingserver.listener;

import net.kyori.adventure.text.Component;
import net.turniptales.buildingserver.ScoreBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.AQUA;
import static net.kyori.adventure.text.format.NamedTextColor.GRAY;
import static net.kyori.adventure.text.format.NamedTextColor.RED;
import static net.kyori.adventure.text.format.TextDecoration.BOLD;
import static net.turniptales.buildingserver.BuildingServer.instance;
import static net.turniptales.buildingserver.BuildingServer.prefix;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.GameMode.CREATIVE;
import static org.bukkit.event.player.PlayerLoginEvent.Result.KICK_OTHER;

public class JoinQuitListener implements Listener {

    private static final Api api = new Api();

    private final Map<UUID, Api.CitizenPermission> permissions = new HashMap<>();

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        Api.CitizenPermission permission = api.getPermission(player.getUniqueId());

        this.permissions.put(player.getUniqueId(), permission);

        if (!permission.isBuilder()) {
            Component component = empty()
                    .append(text("Du hast", GRAY)).appendSpace()
                    .append(text("keine", RED, BOLD)).appendSpace()
                    .append(text("Berechtigung, den Bauserver zu betreten!", GRAY))
                    .appendNewline().appendNewline()
                    .append(text("Du kannst dich unter", GRAY)).appendSpace()
                    .append(text("forum.turniptales.net", AQUA)).appendSpace()
                    .append(text("bewerben.", GRAY));

            event.disallow(KICK_OTHER, component);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(prefix
                .append(text("Willkommen zurück", GRAY)).appendSpace()
                .append(text(player.getName(), AQUA)));

        event.joinMessage(prefix
                .append(text(player.getName(), AQUA)).appendSpace()
                .append(text("hat den Bauserver betreten.", GRAY)));

        UUID uniqueId = player.getUniqueId();
        Api.CitizenPermission permission = this.permissions.get(uniqueId);

        boolean leader = permission.isBuilderLeader();
        player.setOp(leader);
        getLogger().info("[TurnipTales] " + player.getName() + " is " + (leader ? "builder-leader" : "builder") + " -> setting op: " + leader);

        ScoreBoard.setAllPlayerTeams();

        getScheduler().runTaskLater(instance, () -> player.setGameMode(CREATIVE), 50);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.quitMessage(prefix
                .append(text(event.getPlayer().getName(), AQUA)).appendSpace()
                .append(text("hat den Bauserver verlassen.", GRAY)));
    }
}
