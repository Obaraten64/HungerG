package hungergames.hungerg.Commands;

import hungergames.hungerg.HungerG;
import hungergames.hungerg.Timers.BeforeStartTimer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BTPcommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("btp")){
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(player.isOp()) {
                    if (args.length < 1) {
                        player.sendMessage("Вставь аргумент");
                        return true;
                    } else if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("hgworld")) {
                            player.teleport(HungerG.hgworld.getHighestBlockAt(HungerG.hgworld.getSpawnLocation()).getLocation());
                        } else if (args[0].equalsIgnoreCase("check")) {
                            HungerG.GameStartTask = HungerG.instance.getServer().getScheduler().runTaskTimer(HungerG.instance, new BeforeStartTimer(), 20, 20);//HungerG.StartGame();
                        }
                    } else if (args.length > 1) {
                        player.sendMessage("Только 1 аргумент");
                        return true;
                    }
                }
            }
            else {
                sender.sendMessage("LOX");
            }
        }
        return true;
    }
}