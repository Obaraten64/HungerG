package hungergames.hungerg.Timers;

import hungergames.hungerg.HungerG;
import hungergames.hungerg.Methods.EndGamesMethods;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathMatchProcessTimer extends BukkitRunnable {

    public static int DeathMatchProcessTime = 150; //В отдельнй файл
    public static int TotalDeathMatchProcessTime = 150;
    BossBar bossBar = HungerG.instance.getServer().createBossBar("DeathMatchProcessTime", BarColor.RED, BarStyle.SEGMENTED_10);
    public static boolean DeathMatchStarted;

    @Override
    public void run() {
        DeathMatchProcessTime--;
        if(DeathMatchProcessTime > 0){
            bossBar.setProgress(DeathMatchProcessTime / ((double) TotalDeathMatchProcessTime));
            bossBar.setTitle("DeathMatchProcessTime: " + DeathMatchProcessTime);
            for(Player player : Bukkit.getOnlinePlayers()){
                bossBar.addPlayer(player);
                bossBar.setVisible(true);
            }
            if(DeathMatchProcessTime == 145){
                DeathMatchStarted = false;
            }
            HungerG.bossBar = bossBar;
            EndGamesMethods.Checkiner();
        }
        else {
            for(Player player : Bukkit.getOnlinePlayers()){
                bossBar.removePlayer(player);
                bossBar.setVisible(false);
            }
            HungerG.EndGame();
            HungerG.instance.getServer().getScheduler().cancelTask(HungerG.DeathMatchTimerTask.getTaskId());
        }
    }
}