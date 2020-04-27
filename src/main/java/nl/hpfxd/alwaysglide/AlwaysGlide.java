package nl.hpfxd.alwaysglide;

import org.bstats.bukkit.Metrics;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AlwaysGlide extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        new Metrics(this, 7330); // bStats

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    // Prevent a player from not gliding.
    @EventHandler
    public void onGlideToggle(EntityToggleGlideEvent event) {
        if (!event.isGliding() && event.getEntity() instanceof Player) {
            if (((Player) event.getEntity()).getGameMode() != GameMode.CREATIVE) event.setCancelled(true);
        }
    }

    // Make a player glide upon join.
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) event.getPlayer().setGliding(true);
    }

    // Make a player glide upon changing their game mode.
    @EventHandler
    public void onGameModeChange(PlayerGameModeChangeEvent event) {
        if (event.getNewGameMode() != GameMode.CREATIVE) event.getPlayer().setGliding(true);
    }
}
