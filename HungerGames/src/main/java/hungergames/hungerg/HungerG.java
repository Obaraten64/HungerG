package hungergames.hungerg;

import hungergames.hungerg.Commands.BTPcommand;
import hungergames.hungerg.Listener.*;
import hungergames.hungerg.Methods.ChestItems;
import hungergames.hungerg.Methods.CopyFiles;
import hungergames.hungerg.Methods.SpawnPlayers;

import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;

public final class HungerG extends JavaPlugin {

    public static World hgworld;
    public static Boolean GameStarting = false;
    public static HungerG instance;
    public static BukkitTask GameStartTask = null;
    public static BukkitTask PreStartTimerTask = null;
    public static BukkitTask GameProcessTimerTask = null;
    public static BukkitTask DeathMatchTimerTask = null;
    public static BossBar bossBar;
    String MessageAboutStart = "No";

    @Override
    public void onEnable() {

        instance = this;

        Bukkit.getPluginManager().registerEvents(new PlayerJAQHandlers(), instance);
        Bukkit.getPluginManager().registerEvents(new MoveHandler(), instance);
        Bukkit.getPluginManager().registerEvents(new BlockPABHandler(), instance);
        Bukkit.getPluginManager().registerEvents(new InteractiveHandler(), instance);
        Bukkit.getPluginManager().registerEvents(new DeathHandler(), instance);
        instance.getCommand("btp").setExecutor(new BTPcommand());

        instance.getConfig().options().copyDefaults(true);
        instance.saveConfig();

        Bukkit.getScheduler().runTask(instance, new Runnable() {
            @Override
            public void run() {
                try {
                    CopyFiles.CopyMap();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                hgworld = Bukkit.createWorld(new WorldCreator("HungerG"));
                hgworld.setGameRuleValue("doDaylightcycle", "false"); //Create map
                instance.getConfig().set("MessageAboutStart", MessageAboutStart); //can't be longer than 40 characters
                //new SpawnPlayers().CheckNonStatic();//it's like how to realize non-static methods
            }
        });
    }

    @Override
    public void onDisable() {
        //Plugin disable
    }

    //Старт игры
    public static void StartGame() {
        GameStarting = true;
        SpawnPlayers.TeleportPlayers();
        ChestItems.FillChestOnSpawn();
        for(Player player : Bukkit.getOnlinePlayers()){
            player.setGameMode(GameMode.SURVIVAL);
            player.setFoodLevel(24);
        }
    }
    //Старт игры

    //Конец игры
    public static void EndGame() {
        for(Player player : Bukkit.getOnlinePlayers()){
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            player.setGameMode(GameMode.ADVENTURE);
            bossBar.removePlayer(player);
        }
        GameStarting = false;
        HungerG.instance.getServer().getScheduler().cancelTask(HungerG.PreStartTimerTask.getTaskId());
        HungerG.instance.getServer().getScheduler().cancelTask(HungerG.GameProcessTimerTask.getTaskId());
        HungerG.instance.getServer().getScheduler().cancelTask(HungerG.DeathMatchTimerTask.getTaskId());
    }
    //Конец игры
}

//Listener.PlayerJAQHandlers(может больше логики)
//Methods.MethodForScoreboard(написано), Methods.SpawnPlayers(написано(50)),Timer.PreStartTimer(написано), Timer.BeforeStartTimer(написано)
//пару customs craft