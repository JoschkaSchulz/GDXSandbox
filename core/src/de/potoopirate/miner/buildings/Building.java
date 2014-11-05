package de.potoopirate.miner.buildings;

import de.potoopirate.miner.world.GameWorld;

public class Building implements IBuilding{
	private int mWidth, mHeight;
	private int mX, mY;
	
	private Building(int x, int y, int width, int height) {
		mX = x;
		mY = y;
		mWidth = width;
		mHeight = height;
		
	}
	
	/**
	 * You have to specify the lower left corner for the build checking.
	 * 
	 * @param x the x position of the tile
	 * @param y the y position of the tile
	 * @return
	 */
	public Building build(int x, int y, int width, int height, GameWorld gameWorld) throws NoBuildingSpaceException {
		if(gameWorld.getTile(x, y).isBuildable()) throw new NoBuildingSpaceException();
		for (int w = 1; width <  w; w++) {
			for (int h = 1; height < h; h++) {
				if (!gameWorld.getTile(x-w, y-h).isBuildable()) 
					throw new NoBuildingSpaceException();
			}
		}
		return new Building(x, y, width, height);
	}
}
