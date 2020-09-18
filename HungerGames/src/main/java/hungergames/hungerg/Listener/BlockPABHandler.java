package hungergames.hungerg.Listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPABHandler implements Listener {

    @EventHandler
    public void PlaceBlockHandler(BlockPlaceEvent event){
        event.setCancelled(true);
        if(event.getBlockPlaced().getType().equals(Material.TNT)){
            Location locationForPlaceTNT = event.getBlockPlaced().getLocation();
            int amountOfTNT = event.getItemInHand().getAmount();
            event.getItemInHand().setAmount(amountOfTNT - 1);
            event.getBlockPlaced().getWorld().spawnEntity(locationForPlaceTNT, EntityType.PRIMED_TNT);
        }
        else if(event.getItemInHand().equals(new ItemStack(Material.FLINT_AND_STEEL))){
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void BreakBlockHandler(BlockBreakEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void ExplodeBlockHandler(EntityExplodeEvent event){ event.setCancelled(true); }
}