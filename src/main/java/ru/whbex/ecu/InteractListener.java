package ru.whbex.ecu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {
    private final ChunkHolder chunkHolder;
    public InteractListener(ChunkHolder chunkHolder){
        this.chunkHolder = chunkHolder;
    }
    public void onInteract(Player player){
        if(chunkHolder.hasChunk(player.getLocation().getChunk()))
            return;
        chunkHolder.addChunk(player.getLocation().getChunk());
    }
    @EventHandler
    public void on(BlockBreakEvent e){
        onInteract(e.getPlayer());
    }
    @EventHandler
    public void on(BlockPlaceEvent e){
        onInteract(e.getPlayer());
    }
    @EventHandler
    public void on(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player)
            onInteract((Player) e.getDamager());
    }



}
