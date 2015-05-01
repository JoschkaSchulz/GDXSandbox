package de.potoopirate.sandbox.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.potoopirate.play.GooglePlayInterface;
import de.potoopirate.sandbox.GDXSandbox;

public class DesktopLauncher{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1600;
		config.height = 1200;
		//config.samples = 4; MSAA
		new LwjglApplication(new GDXSandbox(new GooglePlayDesktop()), config);
	}
}
