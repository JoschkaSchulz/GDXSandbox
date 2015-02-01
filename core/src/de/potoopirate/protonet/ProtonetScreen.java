package de.potoopirate.protonet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.potoopirate.protonet.components.AvatarComponent;
import de.potoopirate.protonet.components.CameraComponent;
import de.potoopirate.protonet.systems.AvatarLoadSystem;
import de.potoopirate.protonet.systems.ChatSimulationSystem;
import de.potoopirate.protonet.systems.RenderSystem;
import de.potoopirate.protonet.systems.AvatarSpawn;
import de.potoopirate.protonet.systems.MovementSystem;
import de.potoopirate.protonet.systems.ViewportSystem;
import de.thathalas.protonet.ProtonetWrapper;
import de.thathalas.protonet.objects.ProtonetSystem;

public class ProtonetScreen extends ScreenAdapter {
	private ProtonetWrapper protonet;
	private Engine engine;
	
	public ProtonetScreen() {
		protonet = new ProtonetWrapper("http://127.0.0.1:3001"); 
		protonet.getToken("joschka@protonet.info", "123456789", "libGDX activity board");
		systemOutput();

		engine = new Engine();
		
		CameraComponent cam = new CameraComponent();
		cam.camera = new OrthographicCamera();
		Entity entity = new Entity();
		entity.add(cam);
		engine.addEntity(entity);

		engine.addSystem(new RenderSystem());
		engine.addSystem(new AvatarSpawn(protonet));
		engine.addSystem(new MovementSystem());
		engine.addSystem(new AvatarLoadSystem(protonet));
		engine.addSystem(new ViewportSystem());
		engine.addSystem(new ChatSimulationSystem());
	}

	private void systemOutput() {
		ProtonetSystem system = protonet.getSystems().index();
		System.out.println("---- System");
		System.out.println("- Name : " + system.getName());
		System.out.println("- Host : " + system.getPublicHost());
		System.out.println("- Type : " + system.getProductType());
		System.out.println("-------------------------------------");
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		engine.update(delta);
	}
}
