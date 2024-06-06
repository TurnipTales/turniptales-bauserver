package net.turniptales.buildingserver;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreBoard {

    public static void setScoreboard(Player p) {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Team admin = getTeam(sb, "a", "§cAdmin §8| §7", "");
        Team moderator = getTeam(sb, "b", "§3Mod §8| §7", "");
        Team supporter = getTeam(sb, "c", "§bSup §8| §7", "");
        Team builder = getTeam(sb, "d", "§eBuilder §8| §7", "");
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission("prefix.admin")) {
                admin.addPlayer(p);
                return;
            }

            if (all.hasPermission("prefix.moderator")) moderator.addPlayer(p);
            if (all.hasPermission("prefix.supporter")) supporter.addPlayer(p);
            if (all.hasPermission("prefix.builder")) builder.addPlayer(p);
        }

        p.setScoreboard(sb);
    }

    public static Team getTeam(final Scoreboard sb, final String Team, final String prefix, final String suffix) {
        Team team = sb.getTeam(Team);
        if (team == null) {
            team = sb.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.setAllowFriendlyFire(false);
        team.setCanSeeFriendlyInvisibles(true);
        return team;
    }
}
