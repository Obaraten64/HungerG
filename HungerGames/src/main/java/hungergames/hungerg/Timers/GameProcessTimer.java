package hungergames.hungerg.Timers;

import hungergames.hungerg.HungerG;
import hungergames.hungerg.Methods.ChestCords;
import hungergames.hungerg.Methods.EndGamesMethods;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameProcessTimer extends BukkitRunnable {

    public static int GameProcessTime = 20;//20 na 400
    public static int TotalGameProcessTime = 400; //In config file
    BossBar bossBar = HungerG.instance.getServer().createBossBar("GameProcessTime", BarColor.RED, BarStyle.SEGMENTED_10);

    @Override
    public void run() {
        GameProcessTime--;
        if(GameProcessTime > 0){
            if(GameProcessTime > 280){
                bossBar.setProgress(GameProcessTime / ((double) TotalGameProcessTime));
                bossBar.setTitle("Обновления сундуков через: " + (GameProcessTime - 280));
                for(Player player : Bukkit.getOnlinePlayers()){
                    bossBar.addPlayer(player);
                    bossBar.setVisible(true);
                }
            }
            else if(GameProcessTime < 280 && GameProcessTime > 140) {
                bossBar.setProgress(GameProcessTime / ((double) TotalGameProcessTime));
                bossBar.setTitle("Обновления сундуков через: " + (GameProcessTime - 140));
                for (Player player : Bukkit.getOnlinePlayers()) {
                    bossBar.addPlayer(player);
                    bossBar.setVisible(true);
                }
            }
            else if(GameProcessTime < 140){
                bossBar.setProgress(GameProcessTime / ((double) TotalGameProcessTime));
                bossBar.setTitle("Дезматч через: " + GameProcessTime);
                for(Player player : Bukkit.getOnlinePlayers()){
                    bossBar.addPlayer(player);
                    bossBar.setVisible(true);
                }
                if(GameProcessTime == 5){
                    DeathMatchProcessTimer.DeathMatchStarted = true;
                }
            }
            else{
                Bukkit.getServer().broadcastMessage("Chest refiled");
                ChestCords.RefillChest();
            }
            HungerG.bossBar = bossBar;
            EndGamesMethods.Checkiner();
        }
        else {
            for(Player player : Bukkit.getOnlinePlayers()){
                bossBar.setVisible(false);
                bossBar.removePlayer(player);
            }
            EndGamesMethods.DeathMatch();
            HungerG.DeathMatchTimerTask = HungerG.instance.getServer().getScheduler().runTaskTimer(HungerG.instance, new DeathMatchProcessTimer(), 20, 20);
            HungerG.instance.getServer().getScheduler().cancelTask(HungerG.GameProcessTimerTask.getTaskId());
        }
    }
}