package hungergames.hungerg.Methods;

import hungergames.hungerg.HungerG;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class MethodForScoreboard {

    static ScoreboardManager manager = Bukkit.getScoreboardManager();
    static Scoreboard scoreboard = manager.getNewScoreboard();

    static Objective objective = scoreboard.registerNewObjective("Test", "dummy");

    public static void CreateScoreboard() {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for(String entry : scoreboard.getEntries()){
            scoreboard.resetScores(entry);
        }

        if(!HungerG.GameStarting) {
            Score score = objective.getScore(ChatColor.RED + "Подключилось только " + HungerG.instance.getConfig().getInt("CountOfPlayers") + " человек из 8");
            score.setScore(3);
        }

        if(HungerG.instance.getConfig().getInt("LeftToStart") > 1) {
            Score score1 = objective.getScore(ChatColor.GOLD + "Игра начинаеться");
            score1.setScore(4);

            Score score2 = objective.getScore(ChatColor.BLUE + "Время до старта:" + HungerG.instance.getConfig().getInt("LeftToStart"));
            score2.setScore(5);
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            p.setScoreboard(scoreboard);
        }
    }
}//Больше данных