# ECU (EmptyChunkUnsaver)

Proof of concept.
Block break/place, entity damage (by player) mark chunk as "dirty". Then on chunk unload plugin checks, is chunk dirty or not. If no - disable chunk save (ChunkUnloadEvent#setChunkSave)


