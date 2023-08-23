package ru.whbex.ecu;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldSaveEvent;

import java.util.Arrays;

public class ChunkListener implements Listener {
    private final ChunkHolder chunkHolder;
    private final boolean unloadOnSave;

    public ChunkListener(ChunkHolder chunkHolder, boolean unloadOnSave){
        this.chunkHolder = chunkHolder;
        this.unloadOnSave = unloadOnSave;
    }

    @EventHandler
    public void onUnload(ChunkUnloadEvent e){
        if(!chunkHolder.hasChunk(e.getChunk()))
            e.setSaveChunk(false);
    }

    @EventHandler
    public void onWorldSaveEv(WorldSaveEvent e){
        onWorldSave(e.getWorld());
    }
    public void onWorldSave(World world){
        if(unloadOnSave){
            // this will unload unchanged chunks even near players
            Arrays.stream(world.getLoadedChunks()).
                    filter(c -> !chunkHolder.hasChunk(c)).
                    forEach(c -> c.unload(false));
        }
        chunkHolder.removeAllAt(world);

    }



}
