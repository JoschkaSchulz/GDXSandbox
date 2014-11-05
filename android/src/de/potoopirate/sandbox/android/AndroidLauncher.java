package de.potoopirate.sandbox.android;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import de.potoopirate.play.GooglePlayInterface;
import de.potoopirate.sandbox.GDXSandbox;

public class AndroidLauncher extends AndroidApplication implements GooglePlayInterface {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GDXSandbox(this), config);
	}

	@Override
	public void helloWorld() {
		Log.i("DEBUG", "Hello World");
	}
}
