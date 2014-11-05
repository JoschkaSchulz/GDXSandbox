package de.potoopirate.miner.world;

public class GameWorld {
	private GameWorldTile mWorld[][];
	private static GameWorld mGameWorld;
	
	class UD {
		int x, y;
		String bla = "Hello World";
		
		public String toString() { return bla; }
	}
	
	private GameWorld(int x, int y) {
		mWorld = new GameWorldTile[x][y];
		int worldCounter = 0;
		for (int w = 0; w < x; w++) {
			for (int h = 0; h < y; h++) {
				mWorld[w][h] = new GameWorldTile(worldCounter++); 
				mWorld[w][h].setUserData(new UD());
			}
		}
	}
	
	/**
	 * Get an instance of GameWorld
	 * 
	 * @param x the width of the world
	 * @param y the height of the world
	 * @return get the current game World
	 */
	public static GameWorld getInstance(int x, int y) {
		if (mGameWorld == null) mGameWorld = new GameWorld(x,y);
		return mGameWorld;
	}
	
	public static GameWorld getInstance() {
		return getInstance(0, 0);
	}
	
	public GameWorldTile[][] getWorldTiles() {
		return mWorld;
	}
	
	public GameWorldTile getTileById(int id) throws TileNotFoundException {
		for (int w = 0; w < mWorld.length; w++) {
			for (int h = 0; h < mWorld[0].length; h++) {
				if (mWorld[w][h].getId() == id) return mWorld[w][h];
			}
		}
		throw new TileNotFoundException();
	}
	
	public GameWorldTile getTile(int x, int y) {
		return mWorld[x][y];
	}
	
	 /**
	  * prints the world in the console
	  */
	public void printConsole() {
		System.out.println("---Map---");
		for (int w = 0; w < mWorld.length; w++) {
			for (int h = 0; h < mWorld[0].length; h++) {
				System.out.print(mWorld[w][h].printTile(GameWorldTile.PRINT_TYPE.USER_DATA)); 
			}
			System.out.print("\n");
		}
	}
}
