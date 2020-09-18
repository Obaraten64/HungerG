package hungergames.hungerg.Methods;

import hungergames.hungerg.HungerG;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class SpawnPlayers {
    //Если поставить ворлд спавн в самом центре, то с помощью радыуса проверим все бедроки и зспавним игороков
    public static void SaveBedrockCords(){
        int countofbedrock = 0;
        String bedrock;
        int radius = 21; //В отдельный файл
        Location loc = HungerG.hgworld.getSpawnLocation();
        World world = loc.getWorld();
        for (int x = -radius; x - 1 < radius; x++) {
            for (int y = -radius; y - 1 < radius; y++) {
                for (int z = -radius; z - 1 < radius; z++) {
                    Block block = world.getBlockAt(loc.getBlockX()+x, loc.getBlockY()+y, loc.getBlockZ()+z);
                    if (block.getType() == Material.BEDROCK) {
                        countofbedrock++;
                        bedrock = "Bedrock" + countofbedrock;
                        Location bedrockloc = block.getLocation();
                        HungerG.instance.getConfig().set("x." + bedrock, bedrockloc.getX());
                        HungerG.instance.getConfig().set("y." + bedrock, bedrockloc.getY() + 1);
                        HungerG.instance.getConfig().set("z." + bedrock, bedrockloc.getZ());
                        HungerG.instance.saveConfig();
                    }
                }
            }
        }
    }

    public static void TeleportPlayers(){
        SaveBedrockCords();
        int OnlinePlayers = Bukkit.getOnlinePlayers().size();
        String bedrock;
        for(Player p : Bukkit.getOnlinePlayers()){
            bedrock = "Bedrock" + OnlinePlayers;
            float x = HungerG.instance.getConfig().getInt("x." + bedrock);
            float y = HungerG.instance.getConfig().getInt("y." + bedrock);
            float z = HungerG.instance.getConfig().getInt("z." + bedrock);
            Location LocForTeleport = new Location(HungerG.hgworld, x, y, z);
            p.teleport(LocForTeleport);
            OnlinePlayers--;
            //Настр. yaw & pitch
        }
    }
}