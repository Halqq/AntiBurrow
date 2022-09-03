package burrow.preventburrow.listener;


import burrow.preventburrow.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author halq
 * @since 03/09/2022
 */

public class PreventBurrowListener implements Listener {

    private static Main pl;

    public PreventBurrowListener(Main pl) {
        PreventBurrowListener.pl = pl;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player p = event.getPlayer();
        Location l = p.getLocation();

        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();

        Material m = l.getWorld().getBlockAt(x, y, z).getType();

        if (!m.equals(Material.AIR) && m.isOccluding()) {
            if (pl.getConfig().getBoolean("Teleport")) {

                p.teleport(l.add(0, 1, 0));
            }

            if (pl.getConfig().getBoolean("Damage")) {

                p.damage(pl.getConfig().getDouble("DamageV"));
            }

            if (pl.getConfig().getBoolean("Kick")) {

                p.kickPlayer("\u00A74" + "[AntiBurrow]" + " the burrow is disabled");
            }

            if (pl.getConfig().getBoolean("Alert")) {

                p.sendMessage("\u00A74" + "[AntiBurrow]" + " the burrow is disabled");
                p.playSound(l, Sound.BLOCK_GLASS_BREAK, 20, 5);
            }

            if (pl.getConfig().getBoolean("Log")) {

                pl.getServer().getConsoleSender().sendMessage("\u00A74" + "[AntiBurrow]" + " a burrow was prevented, name player:" + p.getName());
            }
        }
    }

}