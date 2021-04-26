package com.github.kdserra.compasstracker;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickListener implements Listener
{
    @EventHandler
    public void onRightClick(PlayerInteractEvent e)
    {
        Action action = e.getAction();
        Player player = e.getPlayer();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
        {
            if (e.getItem() != null)
            {
                if (e.getItem().getType() == Material.COMPASS)
                {
                    if (player != null)
                    {
                        CommandTracker.UpdateTrackedCompass(player);
                    }
                }
            }
        }

    }
}
