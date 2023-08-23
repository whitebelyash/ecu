package ru.whbex.ecu;

import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EcuCommand implements CommandExecutor {

    private final ChunkHolder chunkHolder;

    private final Map<String, Consumer<CommandSender>> cmds = new HashMap<>();

    public EcuCommand(ChunkHolder chunkHolder){
        this.chunkHolder = chunkHolder;
        cmds.put("state", this::state);
        cmds.put("chunk-amount", this::chunk_amount);
        cmds.put("check", this::check);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1 || !cmds.containsKey(args[0])){
            sender.sendMessage("Unknown command. Known: /" + label + ' ' + String.join("|", cmds.keySet()));
            return true;
        }
        cmds.get(args[0]).accept(sender);

        return true;
    }
    private void state(CommandSender sender){
        sender.sendMessage("Showing \"dirty\" chunks: ");
        StringBuilder m = new StringBuilder();
        chunkHolder.getSet().forEach(c -> {
            m.append(c.getX() + ' ' + c.getZ()).append(" | ");
        });
        sender.sendMessage(m.toString());
    }
    private void chunk_amount(CommandSender sender){
        sender.sendMessage("Dirty chunk amount: " + chunkHolder.getSet().size());
    }
    private void check(CommandSender sender){
        if(!(sender instanceof Player)){
            sender.sendMessage("Player only!");
            return;
        }
        Chunk c = ((Player) sender).getLocation().getChunk();
        String answ = chunkHolder.hasChunk(c) ? "yes" : "no";
        sender.sendMessage(String.format("Chunk %d %d dirty: %s", c.getX(), c.getZ(), answ));
    }
}
