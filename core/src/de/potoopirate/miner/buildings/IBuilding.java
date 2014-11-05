package de.potoopirate.miner.buildings;

import de.potoopirate.miner.world.GameWorld;

public interface IBuilding {
	public IBuilding build(int x, int y, int width, int height, GameWorld gameWorld);
}
