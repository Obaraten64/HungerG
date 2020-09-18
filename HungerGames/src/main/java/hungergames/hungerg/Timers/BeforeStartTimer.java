package hungergames.hungerg.Timers;

import hungergames.hungerg.HungerG;
import hungergames.hungerg.Methods.MethodForScoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class BeforeStartTimer extends BukkitRunnable {

    public int LeftToStart = 30;//В config или другой файл 30 na 180

    @Override
    public void run(){
        LeftToStart--;
        HungerG.instance.getConfig().set("LeftToStart", LeftToStart);
        if(LeftToStart > 0){
            if (LeftToStart <= 10) {
                Bukkit.getServer().broadcastMessage(ChatColor.GOLD + Integer.toString(LeftToStart) + "секунд до старта!");
            }
            MethodForScoreboard.CreateScoreboard();
        }
        else {
            HungerG.StartGame();
            MethodForScoreboard.CreateScoreboard();
            HungerG.PreStartTimerTask = HungerG.instance.getServer().getScheduler().runTaskTimer(HungerG.instance, new PreStartTimer(), 20, 20);
            HungerG.instance.getServer().getScheduler().cancelTask(HungerG.GameStartTask.getTaskId());
        }
    }
}//упрощения