package hungergames.hungerg.Methods;

import hungergames.hungerg.HungerG;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

public class EndGamesMethods {

    static Player winer;

    public static void Checkiner(){
        int PlayersInGame = 0;
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player.getGameMode().equals(GameMode.SURVIVAL)){
                PlayersInGame++;
                winer = player;
            }
        }
        if(PlayersInGame == 1){
            winer.sendTitle(ChatColor.GOLD + "You win", "GG", 50, 50, 50);
            HungerG.EndGame();
        }
        else if(PlayersInGame < 1){
            HungerG.EndGame();
        }
    }

    public static void DeathMatch(){
        int radius = 21;//В отдельный файл
        SpawnPlayers.TeleportPlayers();
        WorldBorder border = HungerG.hgworld.getWorldBorder();
        border.setDamageAmount(200);
        border.setCenter(HungerG.hgworld.getSpawnLocation());
        border.setSize(radius * 3);
    }
}