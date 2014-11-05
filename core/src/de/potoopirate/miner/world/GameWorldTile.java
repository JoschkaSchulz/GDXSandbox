package de.potoopirate.miner.world;

public class GameWorldTile {
	private int mId;
	private boolean mLocked;
	private boolean mBuildable;
	
	
	private Object mUserData;
	
	public enum PRINT_TYPE { ID, USER_DATA }
	
	public GameWorldTile(int id) {
		mId = id;
		mLocked = false;
		mBuildable = true;
	}
	
	public boolean isBuildable() {
		return mBuildable;
	}
	
	public void setBuildable(boolean buildable) {
		mBuildable = buildable;
	}
	
	public void setUserData(Object userData) {
		mUserData = userData;
	}
	
	public Object getUserData() {
		return mUserData;
	}
	
	public int getId() {
		return mId;
	}
	
	public String printTile(PRINT_TYPE type) {
		if (type == PRINT_TYPE.ID) {
			return "[" + (mId > 9 ? (mId > 99 ? mId : "0"+mId ) : "00"+mId ) + "]";
		} else if (type == PRINT_TYPE.USER_DATA) {
			return "[" + mUserData +  "]"; 
		} else {
			return "[xxx]";
		}
	}
}
