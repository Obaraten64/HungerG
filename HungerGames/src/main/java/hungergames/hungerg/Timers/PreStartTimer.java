package hungergames.hungerg.Timers;

import hungergames.hungerg.HungerG;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PreStartTimer extends BukkitRunnable {

    public static int PreStartTime = 30;
    public static int TotalPreStartTime = 30; //Решения для BossBar просто закинуть все файл конфиг
    BossBar bossBar = HungerG.instance.getServer().createBossBar("PreStartTime", BarColor.RED, BarStyle.SEGMENTED_6);

    @Override
    public void run() {
        PreStartTime--;
        HungerG.instance.getConfig().set("PreStartTime", PreStartTime);
        if(PreStartTime > 0){
            bossBar.setProgress(PreStartTime / ((double) TotalPreStartTime));
            bossBar.setTitle("PreStartTime: " + PreStartTime);
            for(Player player : Bukkit.getOnlinePlayers()) {
                bossBar.addPlayer(player);
                bossBar.setVisible(true);
            }
        }
        else {
            for(Player player : Bukkit.getOnlinePlayers()){
                bossBar.setVisible(false);
                bossBar.removePlayer(player);
            }
            HungerG.GameProcessTimerTask = HungerG.instance.getServer().getScheduler().runTaskTimer(HungerG.instance, new GameProcessTimer(), 20, 20);
            HungerG.instance.getServer().getScheduler().cancelTask(HungerG.PreStartTimerTask.getTaskId());
        }
    }
}//Упрощения