package codes.ollieg.SimpleSpawnTP;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;


public final class CommandSpawn implements CommandExecutor {
    private final ConfigurationSection world_configs;

    public CommandSpawn(ConfigurationSection world_configs) {
        this.world_configs = world_configs;
    }


    /**
     * Returns the spawn location using the config.
     *
     * @param player The player to get the spawn location for.
     * @return The spawn location.
     */
    private Location getAdjustedLocationFromConfig(Player player) {
        World world = player.getWorld();

        // if there are no valid configs, return the default spawn
        if (this.world_configs == null) {
            return world.getSpawnLocation();
        }

        // if there is no config for this world, return the default spawn
        ConfigurationSection world_config = this.world_configs.getConfigurationSection(world.getName());
        if (world_config == null) {
            return world.getSpawnLocation();
        }

        // determine whether to use personal or global spawn
        Location spawn;
        if (world_config.getBoolean("use_personal_spawn")) {
            spawn = player.getBedSpawnLocation();
        } else {
            spawn = world.getSpawnLocation();
        }

        // get yaw from config
        // accept double or int and convert to float
        // set the float as the new yaw
        if (world_config.isDouble("yaw")) {
            spawn.setYaw((float) world_config.getDouble("yaw"));
        } else if (world_config.isInt("yaw")) {
            spawn.setYaw((float) world_config.getInt("yaw"));
        }

        return spawn;
    }


    public void sendToSpawn(Player player) {
        Location spawn = getAdjustedLocationFromConfig(player);
        boolean success = player.teleport(spawn);

        if (!success) {
            player.sendMessage(ChatColor.RED + "Failed to send you to spawn!");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must execute this command as a player!");
            return true;
        }

        if (!(sender.hasPermission("sstp.spawncmd"))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        Player player = (Player) sender;
        sendToSpawn(player);

        return true;
    }
}
