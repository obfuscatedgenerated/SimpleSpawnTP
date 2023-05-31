package codes.ollieg.SimpleSpawnTP;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    private CommandSpawn spawn_cmd;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(this, this);

        ConfigurationSection world_configs = getConfig().getConfigurationSection("worlds");
        this.spawn_cmd = new CommandSpawn(world_configs);
        this.getCommand("spawn").setExecutor(spawn_cmd);

        getLogger().info("SimpleSpawnTP - Ready to go!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SimpleSpawnTP - Shutting down...");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("sstp.bypass")) {
            return;
        }

        // reuse spawn routine for space efficiency
        this.spawn_cmd.sendToSpawn(player);
    }
}
