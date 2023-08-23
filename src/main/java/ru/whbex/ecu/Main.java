package ru.whbex.ecu;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private ChunkHolder chunkHolder;
    private ChunkListener chunkListener;
    // workaround for onDisable() world unload task
    // bukkit api doesn't have dedicated event/method called on server shutdown
    // and we don't need to unload chunks on plugin startup failure
    private boolean successStartup = false;

    @Override
    public void onEnable() {
        this.chunkHolder = new ChunkHolder(this);
        this.chunkListener = new ChunkListener(chunkHolder, true);
        Bukkit.getPluginManager().registerEvents(new InteractListener(chunkHolder), this);
        Bukkit.getPluginManager().registerEvents(chunkListener, this);
        this.getCommand("ecu").setExecutor(new EcuCommand(chunkHolder));

        successStartup = true;
    }

    @Override
    public void onDisable() {
        getLogger().info("Shutting down");
        if(successStartup)
            Bukkit.getWorlds().forEach(chunkListener::onWorldSave);
    }
}
