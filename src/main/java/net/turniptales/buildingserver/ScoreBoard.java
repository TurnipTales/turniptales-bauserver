package net.turniptales.buildingserver;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.DARK_GRAY;
import static net.kyori.adventure.text.format.NamedTextColor.GRAY;
import static net.kyori.adventure.text.format.NamedTextColor.RED;
import static net.kyori.adventure.text.format.NamedTextColor.YELLOW;
import static org.bukkit.Bukkit.getOnlinePlayers;

public class ScoreBoard {

    public static void setAllPlayerTeams() {
        getOnlinePlayers().forEach(player -> {
            PlayerListTeam playerListTeam = PlayerListTeam.getPlayerListTeam(player);
            player.playerListName(playerListTeam.getPlayerListName(player));
        });
    }

    public enum PlayerListTeam {

        ADMINISTRATOR("Admin", RED),
        BUILDER("Builder", YELLOW);

        private final String chatPrefix;
        private final NamedTextColor color;

        PlayerListTeam(String chatPrefix, NamedTextColor color) {
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
            return player.isOp() ? ADMINISTRATOR : BUILDER;
        }
    }
}
