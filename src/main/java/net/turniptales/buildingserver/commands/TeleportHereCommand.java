package net.turniptales.buildingserver.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static java.util.Objects.nonNull;

public class TeleportHereCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player player && args.length == 1) {
            if (!player.hasPermission("2")) return false;

            Player target = Bukkit.getPlayer(args[0]);
            if (!nonNull(target)) return false;

            target.teleport(player.getLocation());
            player.sendMessage(Component.text("§bBauserver §8| §7Du hast §b" + target.getName() + " §7zu dir teleportiert."));
            return true;
        }

        return false;
    }
}