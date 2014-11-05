package de.potoopirate.miner.buildings;

public class NoBuildingSpaceException extends RuntimeException {
	public NoBuildingSpaceException() {
		super("No Space for the building");
	}
}
