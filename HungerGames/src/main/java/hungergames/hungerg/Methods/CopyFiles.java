package hungergames.hungerg.Methods;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;

public class CopyFiles {
    public static void CopyMap() throws IOException {
        Bukkit.unloadWorld("HungerG",true);
        FileUtils.deleteDirectory(new File("HungerG"));
        FileUtils.copyDirectory(new File("HungerGameWorld"), new File("HungerG"));
    }
}
//Создание папки под мир