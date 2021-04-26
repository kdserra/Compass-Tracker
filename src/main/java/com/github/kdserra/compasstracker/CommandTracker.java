package com.github.kdserra.compasstracker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class CommandTracker implements CommandExecutor {
    public static Map<String, String> PlayerTrackingDict = new HashMap<>();
    public static Boolean UsingDistanceTracking = false;
    public static Boolean UsingCoordinateTracking = false;

    public static Integer GetDistance(Player player1, Player player2) {
        return (int) Math.round(Math.abs(player1.getLocation().distance(player2.getLocation())));
    }

    public static ItemStack GetCompassItemStack(Player player, Player trackedPlayer) {
        ItemStack compass = new ItemStack(Material.COMPASS);
        compass.setAmount(1);
        ItemMeta newCompassItemMeta = CommandTracker.GetTrackedCompassItemMeta(player, trackedPlayer);
        compass.setItemMeta(newCompassItemMeta);
        return compass;
    }

    public static ItemMeta GetTrackedCompassItemMeta(Player compassReceiverPlayer, Player trackedPlayer) {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassItemMeta = compass.getItemMeta();
        String compassDisplayName = ChatColor.GOLD + "Tracking: " + trackedPlayer.getDisplayName();
        if (CommandTracker.UsingDistanceTracking) {
            compassDisplayName += (" " + CommandTracker.GetDistance(compassReceiverPlayer, trackedPlayer).toString() + "m");
        }
        if (CommandTracker.UsingCoordinateTracking) {
            compassDisplayName += (
                    " (" +
                            (int) Math.round(trackedPlayer.getLocation().getX()) +
                            "," +
                            (int) Math.round(trackedPlayer.getLocation().getY()) +
                            "," +
                            (int) Math.round(trackedPlayer.getLocation().getZ()) +
                            ")"
            );
        }
        if (compassItemMeta != null)
        {
            compassItemMeta.setDisplayName(compassDisplayName);
        }
        return compassItemMeta;
    }

    public static void UpdateTrackedCompass(Player player) {
        if (PlayerTrackingDict.containsKey(player.getDisplayName())) {
            String trackedPlayerName = PlayerTrackingDict.get(player.getDisplayName());
            if (trackedPlayerName != null && !trackedPlayerName.equals("")) {
                Player trackedPlayer = Bukkit.getPlayer(trackedPlayerName);
                if (trackedPlayer != null) {
                    if (player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS)) {
                        player.getInventory().setItemInMainHand(CommandTracker.GetCompassItemStack(player, trackedPlayer));
                    }
                    player.setCompassTarget(trackedPlayer.getLocation());
                }
            }
        }
    }

    public void giveTrackedCompass(Player compassReceiverPlayer) {
        if (compassReceiverPlayer != null) {
            if (PlayerTrackingDict.containsKey(compassReceiverPlayer.getDisplayName())) {
                String trackedPlayerName = PlayerTrackingDict.get(compassReceiverPlayer.getDisplayName());
                Player trackedPlayer = Bukkit.getPlayer(trackedPlayerName);
                if (trackedPlayer != null) {
                    compassReceiverPlayer.getInventory().addItem(CommandTracker.GetCompassItemStack(compassReceiverPlayer, trackedPlayer));
                    compassReceiverPlayer.setCompassTarget(trackedPlayer.getLocation());

                }
            }
        }
    }

    public void giveTrackedCompassAll(Player trackedPlayer) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.getDisplayName().equals(trackedPlayer.getDisplayName())) {
                setPlayerTracking(player, trackedPlayer);
                giveTrackedCompass(player);
            }
        }
    }

    public void setPlayerTracking(Player player, Player playerToTrack) {
        if (player == null || playerToTrack == null) {
            return;
        }
        PlayerTrackingDict.put(player.getDisplayName(), playerToTrack.getDisplayName());
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            switch (args.length) {
                case 0:
                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + "=========== Compass Tracker ===========");
                    sender.sendMessage(ChatColor.RED + "/tracker <playerToTrack> - Create a compass that tracks a player.");
                    sender.sendMessage(ChatColor.RED + "/tracker <playerToTrack> <player> - Give a compass to a player that tracks a player.");
                    sender.sendMessage(ChatColor.RED + "/tracker <playerToTrack> all - Give a compass to everyone that tracks a player.");
                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + "=======================================");
                    return true;
                case 1:
                    Player _trackedPlayer = Bukkit.getPlayer(args[0]);
                    if (_trackedPlayer != null) {
                        setPlayerTracking(player, _trackedPlayer);
                        giveTrackedCompass(player);
                    }
                    return true;
                case 2:
                    Player __trackedPlayer = Bukkit.getPlayer(args[0]);
                    if (__trackedPlayer != null) {
                        if (args[1].equals("all")) {
                            giveTrackedCompassAll(__trackedPlayer);
                        } else {
                            setPlayerTracking(player, __trackedPlayer);
                            Player _targetPlayer = Bukkit.getPlayer(args[1]);
                            giveTrackedCompass(_targetPlayer);
                        }
                    }
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }
}
