package hungergames.hungerg.Listener;

import hungergames.hungerg.HungerG;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathHandler implements Listener {

    @EventHandler
    public void DamageEventHandler(EntityDamageByEntityEvent event){
        if(HungerG.GameStarting) {
            if (event.getEntity().getType().equals(EntityType.PLAYER)) {
                Player player = (Player) event.getEntity();
                if (player.getHealth() <= 1) {
                    event.getDamager().sendMessage("Ты убил: " + player.getName());
                    player.sendMessage("Ты был убит: " + event.getDamager().getName());
                }
            } else if (event.getEntity().getType().equals(EntityType.ITEM_FRAME)) {
                event.setCancelled(true);
            }
        }
        else{event.setCancelled(true);}
    }

    @EventHandler
    public void DeathEventHandler(PlayerDeathEvent event){
        if(HungerG.GameStarting) {
            event.getEntity().setHealth(20);
            event.getEntity().setGameMode(GameMode.SPECTATOR);
            event.setDeathMessage("");
        }
        else{
            event.getEntity().teleport(event.getEntity().getWorld().getSpawnLocation());
        }
    }
}
