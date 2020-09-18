package hungergames.hungerg.Methods;

import hungergames.hungerg.HungerG;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ChestItems {

    public static int OpenedChest;

    public static void FillChest(Inventory inventory){
        Material[] RandomItems = {Material.DIAMOND, Material.GOLD_ORE, Material.IRON_ORE, Material.LEATHER_HELMET, Material.STICK,
                Material.GOLD_BOOTS, Material.APPLE, Material.COOKED_FISH, Material.COAL, Material.WOOD_AXE, Material.WOOD, Material.SAND,
                Material.GLASS, Material.PAPER, Material.LEATHER, Material.FLINT, Material.FEATHER, Material.LAPIS_BLOCK, Material.SULPHUR,
                Material.IRON_NUGGET, Material.GOLD_NUGGET, Material.WHEAT, Material.SUGAR_CANE, Material.GLOWSTONE_DUST,
                Material.REDSTONE, Material.BLAZE_POWDER, Material.NETHER_STALK, Material.GHAST_TEAR, Material.EXP_BOTTLE};
        for(int CountOfItem = 9; CountOfItem > 0; CountOfItem--) {
            ItemStack Item = new ItemStack(RandomItems[new Random().nextInt(RandomItems.length)]);
            if(Item.getType().equals(Material.APPLE)){
                int MaxItemsForItems = 3;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.GOLD_ORE)){
                int MaxItemsForItems = 5;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.IRON_ORE)){
                int MaxItemsForItems = 4;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.IRON_NUGGET)){
                int MaxItemsForItems = 9;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.GOLD_NUGGET)){
                int MaxItemsForItems = 12;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.STICK)){
                int MaxItemsForItems = 5;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.COOKED_FISH)){
                int MaxItemsForItems = 8;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.INK_SACK)){
                int MaxItemsForItems = 2;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.PAPER)){
                int MaxItemsForItems = 3;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.GLASS)){
                int MaxItemsForItems = 3;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.WHEAT)){
                int MaxItemsForItems = 5;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.SUGAR_CANE)){
                int MaxItemsForItems = 4;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.EXP_BOTTLE)){
                int MaxItemsForItems = 4;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.SAND)){
                int MaxItemsForItems = 3;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            else if(Item.getType().equals(Material.SULPHUR)){
                int MaxItemsForItems = 2;
                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
            }
            inventory.setItem(new Random().nextInt(inventory.getSize()), Item);
        }
    }

    public static void FillChestOnSpawn(){
        int radius = 5;
        Location loc = HungerG.hgworld.getSpawnLocation();
        World world = loc.getWorld();
        for (int x = -radius; x - 1 < radius; x++) {
            for (int y = -radius; y - 1 < radius; y++) {
                for (int z = -radius; z - 1 < radius; z++) {
                    Block block = world.getBlockAt(loc.getBlockX()+x, loc.getBlockY()+y, loc.getBlockZ()+z);
                    if (block.getType() == Material.CHEST) {
                        OpenedChest++;
                        Location chestloc = block.getLocation();
                        HungerG.instance.getConfig().set("x.OpenedChest" + OpenedChest, chestloc.getX());
                        HungerG.instance.getConfig().set("y.OpenedChest" + OpenedChest, chestloc.getY());
                        HungerG.instance.getConfig().set("z.OpenedChest" + OpenedChest, chestloc.getZ());
                        Chest chest = (Chest) block.getState();
                        Inventory inventory = chest.getBlockInventory();
                        Material[] RandomItems = {Material.STONE_SWORD, Material.STONE_AXE, Material.IRON_INGOT, Material.BREAD, Material.CHAINMAIL_LEGGINGS,
                                Material.GOLD_BOOTS, Material.ENDER_PEARL, Material.GOLD_INGOT, Material.CHAINMAIL_CHESTPLATE, Material.APPLE,
                                Material.GOLD_HELMET, Material.GLASS_BOTTLE, Material.BOOK, Material.EXP_BOTTLE, Material.BLAZE_ROD,
                                Material.ARROW, Material.BOW, Material.TNT};
                        for(int CountOfItem = 7; CountOfItem > 0; CountOfItem--){
                            ItemStack Item = new ItemStack(RandomItems[new Random().nextInt(RandomItems.length)]);
                            if(Item.getType().equals(Material.BREAD)){
                                int MaxItemsForItems = 2;
                                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
                            }
                            else if(Item.getType().equals(Material.IRON_INGOT)){
                                int MaxItemsForItems = 2;
                                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
                            }
                            else if(Item.getType().equals(Material.GOLD_INGOT)){
                                int MaxItemsForItems = 2;
                                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
                            }
                            else if(Item.getType().equals(Material.APPLE)){
                                int MaxItemsForItems = 5;
                                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
                            }
                            else if(Item.getType().equals(Material.GLASS_BOTTLE)){
                                int MaxItemsForItems = 2;
                                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
                            }
                            else if(Item.getType().equals(Material.ARROW)){
                                int MaxItemsForItems = 3;
                                Item = new ItemStack(Item.getType(), new Random().nextInt(MaxItemsForItems));
                            }
                            inventory.setItem(new Random().nextInt(inventory.getSize()), Item);
                        }
                        ChestCords.OpenedChest = OpenedChest;
                    }
                }
            }
        }
    }
}