package hungergames.hungerg.Listener;

import hungergames.hungerg.HungerG;
import hungergames.hungerg.Methods.ChestCords;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;

public class InteractiveHandler implements Listener {

    @EventHandler
    public void PlayerOpenInventory(PlayerInteractEvent event) {
        if (HungerG.GameStarting) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                    Chest chest = (Chest) event.getClickedBlock().getState();
                    Inventory inventory = chest.getInventory();
                    HungerG.instance.getConfig().set("Inventory", inventory);

                    HungerG.instance.getConfig().set("x.ActualyCords", event.getClickedBlock().getX());
                    HungerG.instance.getConfig().set("y.ActualyCords", event.getClickedBlock().getY());
                    HungerG.instance.getConfig().set("z.ActualyCords", event.getClickedBlock().getZ());
                    ChestCords.CheckChestCords();
                } else if (event.getClickedBlock().getType().equals(Material.DISPENSER) || event.getClickedBlock().getType().equals(Material.DROPPER)) {
                    event.setCancelled(true);
                }
            }
        }
        else{event.setCancelled(true);}
    }
}