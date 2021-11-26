package shreb.me.easybroadcast;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class EasyBroadcast extends JavaPlugin {

    private static EasyBroadcast instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        createFiles();

        loadConfig();

        Objects.requireNonNull(getCommand("eb")).setExecutor(new BroadcastCommands());

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The Easy Broadcast Plugin is enabled");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "The Easy Broadcast Plugin is disabled");
    }

    public static EasyBroadcast getInstance() {
        return instance;
    }

    public void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }

    private void createFiles() {

        File configF = new File(getDataFolder(), "config.yml");

        if (!configF.exists()) {
            configF.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(configF);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
