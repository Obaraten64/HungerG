package hungergames.hungerg.Listener;

import hungergames.hungerg.HungerG;

import hungergames.hungerg.Timers.DeathMatchProcessTimer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveHandler implements Listener {

    @EventHandler
    public void PlayerMoveHandler(PlayerMoveEvent event){
        if((HungerG.instance.getConfig().getInt("PreStartTime") > 0 && HungerG.GameStarting || DeathMatchProcessTimer.DeathMatchStarted)) {
            if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                event.setCancelled(true);
            }
        }
    }
}