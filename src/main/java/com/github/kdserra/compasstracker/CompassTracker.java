package com.github.kdserra.compasstracker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CompassTracker extends JavaPlugin {

    @Override
    public void onEnable()
    {
        FileConfiguration config = this.getConfig();
        config.addDefault("usingDistanceTracking", false);
        config.addDefault("usingCoordinateTracking", false);
        config.options().copyDefaults(true);
        saveConfig();

        CommandTracker.UsingDistanceTracking = config.getBoolean("usingDistanceTracking");
        CommandTracker.UsingCoordinateTracking = config.getBoolean("usingCoordinateTracking");

        this.getCommand("tracker").setExecutor(new CommandTracker());
        getServer().getPluginManager().registerEvents(new RightClickListener(), this);
    }
}
