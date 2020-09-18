package hungergames.hungerg.Methods;

import hungergames.hungerg.HungerG;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;

public class ChestCords{

    static boolean CordsInFile;
    static int OpenedChest = ChestItems.OpenedChest;

    public static void CheckChestCords() {
        int FakeOpenedChest = OpenedChest;
        for (CordsInFile = true; CordsInFile;) {
            if (HungerG.instance.getConfig().getInt("x.ActualyCords") == HungerG.instance.getConfig().getInt("x.OpenedChest" + FakeOpenedChest) &&
                HungerG.instance.getConfig().getInt("y.ActualyCords") == HungerG.instance.getConfig().getInt("y.OpenedChest" + FakeOpenedChest) &&
                HungerG.instance.getConfig().getInt("z.ActualyCords") == HungerG.instance.getConfig().getInt("z.OpenedChest" + FakeOpenedChest)
            ) {
                break;
            } else {
                if(FakeOpenedChest > 0){
                    FakeOpenedChest--;
                }
                else {
                    CordsInFile = false;
                }
            }
        }
        if(HungerG.instance.getConfig().get("Inventory") instanceof DoubleChestInventory){
            DoubleChestInventory inventory = (DoubleChestInventory) HungerG.instance.getConfig().get("Inventory");
            //LeftSide
            HungerG.instance.getConfig().set("x.DoubleChestLeftSiteCords", inventory.getLeftSide().getLocation().getX());
            HungerG.instance.getConfig().set("y.DoubleChestLeftSiteCords", inventory.getLeftSide().getLocation().getY());
            HungerG.instance.getConfig().set("z.DoubleChestLeftSiteCords", inventory.getLeftSide().getLocation().getZ());
            //RightSide
            HungerG.instance.getConfig().set("x.DoubleChestRightSideCords", inventory.getRightSide().getLocation().getX());
            HungerG.instance.getConfig().set("y.DoubleChestRightSideCords", inventory.getRightSide().getLocation().getY());
            HungerG.instance.getConfig().set("z.DoubleChestRightSideCords", inventory.getRightSide().getLocation().getZ());
            //SaveCords
            SaveDoubleChestCords();
        }
        else{
            SaveChestCordsFillChest();
        }
    }

    public static void SaveChestCordsFillChest(){
        if(!CordsInFile) {
            OpenedChest++;
            HungerG.instance.getConfig().set("x.OpenedChest" + OpenedChest, HungerG.instance.getConfig().getInt("x.ActualyCords"));
            HungerG.instance.getConfig().set("y.OpenedChest" + OpenedChest, HungerG.instance.getConfig().getInt("y.ActualyCords"));
            HungerG.instance.getConfig().set("z.OpenedChest" + OpenedChest, HungerG.instance.getConfig().getInt("z.ActualyCords"));
            ChestItems.FillChest((Inventory) HungerG.instance.getConfig().get("Inventory"));
            HungerG.instance.getConfig().set("Inventory", null);
        }
    }

    public static void SaveDoubleChestCords(){
        if(!CordsInFile){
            OpenedChest++;
            HungerG.instance.getConfig().set("x.OpenedChest" + OpenedChest, HungerG.instance.getConfig().getInt("x.DoubleChestLeftSiteCords"));
            HungerG.instance.getConfig().set("y.OpenedChest" + OpenedChest, HungerG.instance.getConfig().getInt("y.DoubleChestLeftSiteCords"));
            HungerG.instance.getConfig().set("z.OpenedChest" + OpenedChest, HungerG.instance.getConfig().getInt("z.DoubleChestLeftSiteCords"));
            OpenedChest++;
            HungerG.instance.getConfig().set("x.OpenedChest" + OpenedChest, HungerG.instance.getConfig().getInt("x.DoubleChestRightSideCords"));
            HungerG.instance.getConfig().set("y.OpenedChest" + OpenedChest, HungerG.instance.getConfig().getInt("y.DoubleChestRightSideCords"));
            HungerG.instance.getConfig().set("z.OpenedChest" + OpenedChest, HungerG.instance.getConfig().getInt("z.DoubleChestRightSideCords"));
            ChestItems.FillChest((Inventory) HungerG.instance.getConfig().get("Inventory"));
            HungerG.instance.getConfig().set("Inventory", null);
        }
    }

    public static void RefillChest(){
        int FakeOpenedChest = OpenedChest;
        for(OpenedChest = FakeOpenedChest; OpenedChest > 0; OpenedChest--){
            double x = HungerG.instance.getConfig().getDouble("x.OpenedChest" + OpenedChest);
            double y = HungerG.instance.getConfig().getDouble("y.OpenedChest" + OpenedChest);
            double z = HungerG.instance.getConfig().getDouble("z.OpenedChest" + OpenedChest);
            Location location = new Location(HungerG.hgworld, x, y, z);
            Chest chest = (Chest) location.getBlock().getState();
            chest.getInventory().clear();
            HungerG.instance.getConfig().set("x.OpenedChest" + OpenedChest, null);
            HungerG.instance.getConfig().set("y.OpenedChest" + OpenedChest, null);
            HungerG.instance.getConfig().set("z.OpenedChest" + OpenedChest, null);
        }
        ChestItems.OpenedChest = OpenedChest;
        ChestItems.FillChestOnSpawn();
    }
}