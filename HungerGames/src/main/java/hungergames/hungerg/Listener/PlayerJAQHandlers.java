package hungergames.hungerg.Listener;

import hungergames.hungerg.HungerG;
import hungergames.hungerg.Methods.MethodForScoreboard;
import hungergames.hungerg.Timers.BeforeStartTimer;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJAQHandlers implements Listener {

    @EventHandler
    public void handlerJoin(PlayerJoinEvent event){

        int CountOfPlayers = Bukkit.getOnlinePlayers().size(); //В config(число)
        HungerG.instance.getConfig().set("CountOfPlayer", CountOfPlayers);
        if (CountOfPlayers >= 8 && !HungerG.GameStarting){ //Если игроков >= 8 и таймер не запущен, запускаем таймер
            HungerG.GameStartTask = HungerG.instance.getServer().getScheduler().runTaskTimer(HungerG.instance, new BeforeStartTimer(), 20 ,20);
            HungerG.instance.getConfig().set("MessageAboutStart", "Yes");//can't be longer than 40 characters
        }

        MethodForScoreboard.CreateScoreboard();
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
        event.getPlayer().setFoodLevel(100);
    }

    @EventHandler
    public void handlerLeave(PlayerQuitEvent event){
        if(Bukkit.getOnlinePlayers().size() <= 8 && !HungerG.GameStarting && HungerG.GameStartTask != null){
            HungerG.instance.getServer().getScheduler().cancelTask(HungerG.GameStartTask.getTaskId());
        }
    }
}