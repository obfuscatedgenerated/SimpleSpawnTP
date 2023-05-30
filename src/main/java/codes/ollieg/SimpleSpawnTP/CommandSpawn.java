package codes.ollieg.SimpleSpawnTP;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;


public final class CommandSpawn implements CommandExecutor {
    private final ConfigurationSection yaws;

    public CommandSpawn(ConfigurationSection yaws) {
        this.yaws = yaws;
    }

    public void sendToSpawn(Player player) {
        World world = player.getWorld();
        Location spawn = world.getSpawnLocation();

        if (this.yaws != null) {
            String name = world.getName();

            // if there are defined yaws and there is one for this world
            if (this.yaws.get(name) != null) {
                // accept double or int and convert to float
                // set the float as the new yaw
                if (this.yaws.isDouble(name)) {
                    spawn.setYaw((float) this.yaws.getDouble(name));
                } else if (this.yaws.isInt(name)) {
                    spawn.setYaw((float) this.yaws.getInt(name));
                }
            }
        }

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
