package burrow.preventburrow;

import burrow.preventburrow.listener.PreventBurrowListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author halq
 * @since 02/09/2022
 */

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PreventBurrowListener(this), this);
    }

}
