package de.potoopirate.miner.world;

public class TileNotFoundException extends RuntimeException{
	public TileNotFoundException() {
		super("Tile not found");
	}
}
