package net.turniptales.buildingserver;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.DARK_GRAY;
import static net.kyori.adventure.text.format.NamedTextColor.GRAY;
import static net.kyori.adventure.text.format.NamedTextColor.RED;
import static net.kyori.adventure.text.format.NamedTextColor.YELLOW;

public class ScoreBoard {

    public static void setAllPlayerTeams() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            PlayerListTeam playerListTeam = PlayerListTeam.getPlayerListTeam(player);
            player.playerListName(playerListTeam.getPlayerListName(player));
        });
    }

    public enum PlayerListTeam {

        ADMINISTRATOR("000_ADMINISTRATOR", "ADMIN", RED),
        BUILDER("050_BUILDER", "Builder", YELLOW),
        DEFAULT("100_DEFAULT", "", GRAY);

        private final String playerListTeamName;
        private final String chatPrefix;
        private final NamedTextColor color;

        PlayerListTeam(String playerListTeamName, String chatPrefix, NamedTextColor color) {
            this.playerListTeamName = playerListTeamName;
            this.chatPrefix = chatPrefix;
            this.color = color;
        }

        public Component getPlayerListName(Player player) {
            Component prefix = !this.chatPrefix.isBlank() ? empty()
                    .append(text(this.chatPrefix.toUpperCase(), this.color)).appendSpace()
                    .append(text("|", DARK_GRAY)).appendSpace() : empty();

            return prefix.append(text(player.getName(), GRAY));
        }

        public Component getChatMessage(Player player, Component message) {
            return empty()
                    .append(getChatPrefix()).appendSpace()
                    .append(text("|", DARK_GRAY)).appendSpace()
                    .append(text(player.getName(), GRAY)).appendSpace()
                    .append(text("»", DARK_GRAY)).appendSpace()
                    .append(message.color(GRAY));
        }

        private Component getChatPrefix() {
            return text(this.chatPrefix, this.color);
        }

        public static PlayerListTeam getPlayerListTeam(Player player) {
            if (player.hasPermission("prefix.admin")) {
                return ADMINISTRATOR;
            } else if (player.hasPermission("prefix.builder")) {
                return BUILDER;
            } else {
                return DEFAULT;
            }
        }
    }
}
