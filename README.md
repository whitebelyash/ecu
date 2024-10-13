# ECU (EmptyChunkUnsaver)

Proof of concept.  
Block break/place, entity damage (by player) mark chunk as "dirty". Non-dirty chunks won't be saved on ChunkUnloadEvent.

#### Breaks entity despawn logic, seems like they're not being deeleted same as chunks, so the world becomes flooded with mobs.
There are two ways of fixing that:
- Find a way to despawn all mobs on non-dirty chunk unload
- Don't use this logic at all, just remove chunks in offline/background, even if they're saved on disk.



