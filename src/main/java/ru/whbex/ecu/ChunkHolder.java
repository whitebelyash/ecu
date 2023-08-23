package ru.whbex.ecu;

import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public class ChunkHolder {
    private final Main main;
    public ChunkHolder(Main main){
        this.main = main;
    }

    private final Set<Chunk> chunks = ConcurrentHashMap.newKeySet();


    public boolean hasChunk(Chunk c){
        return chunks.contains(c);
    }
    public void addChunk(Chunk c){
        chunks.add(c);
        main.getLogger().log(Level.INFO, String.format("Added chunk at %d %d", c.getX(), c.getZ()));
    }
    public void removeChunk(Chunk c){
        if(!chunks.contains(c))
            throw new IllegalArgumentException("Chunk not exists in ChunkHolder!");
        chunks.remove(c);
        main.getLogger().log(Level.INFO, String.format("Added chunk at %d %d", c.getX(), c.getZ()));
    }
    public void removeAll(){
        main.getLogger().info("Removing all chunks from ChunkHolder...");
        chunks.clear();
    }
    public void removeAllAt(World world){
        main.getLogger().info("Removing all chunks from ChunkHolder at world " + world.getName());
        chunks.removeIf(c -> c.getWorld() == world);

    }

    public Set<Chunk> getSet(){
        return chunks;
    }
}
