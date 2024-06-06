package net.turniptales.buildingserver.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static java.util.Objects.nonNull;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (!nonNull(target)) return false;

                player.teleport(target.getLocation());
                player.sendMessage(Component.text("§bBauserver §8| §7Du hast dich zu §b" + target.getName() + " §7teleportiert."));
                return true;
            }

            if (args.length == 3) {
                if (isFloat(args[0]) && isFloat(args[1]) && isFloat(args[2])) {
                    float x = Float.parseFloat(args[0]);
                    float y = Float.parseFloat(args[1]);
                    float z = Float.parseFloat(args[2]);

                    player.teleport(new Location(player.getWorld(), x, y, z));
                    player.sendMessage(Component.text("§bBauserver §8| §7Du hast dich zu §b" + x + " " + y + " " + z + " §7teleportiert."));
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
