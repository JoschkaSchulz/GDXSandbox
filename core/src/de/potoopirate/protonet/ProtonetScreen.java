package de.potoopirate.protonet;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

import de.potoopirate.protonet.components.CameraComponent;
import de.potoopirate.protonet.components.OptionsComponent;
import de.potoopirate.protonet.components.StageComponent;
import de.potoopirate.protonet.systems.AvatarLoadSystem;
import de.potoopirate.protonet.systems.AvatarSpawn;
import de.potoopirate.protonet.systems.BackgroundSystem;
import de.potoopirate.protonet.systems.ChatSimulationSystem;
import de.potoopirate.protonet.systems.ColorBeatSystem;
import de.potoopirate.protonet.systems.InactivitySystem;
import de.potoopirate.protonet.systems.MovementSystem;
import de.potoopirate.protonet.systems.RenderConnectionSystem;
import de.potoopirate.protonet.systems.RenderSystem;
import de.potoopirate.protonet.systems.ViewportSystem;
import de.thathalas.protonet.ProtonetWrapper;
import de.thathalas.protonet.objects.ProtonetSystem;

public class ProtonetScreen extends ScreenAdapter {
	private Engine engine;
	
	public ProtonetScreen() {
		ProtonetHelper.initProtonetWrapper("joschka@protonet.info", "123456789");
		//ProtonetHelper.initProtonetWrapper("office.152", "bbbbbbbb");
		systemOutput();

		engine = new Engine();
		
		CameraComponent cam = new CameraComponent();
		cam.camera = new OrthographicCamera();
		Entity entity = new Entity();
		entity.add(cam);
		engine.addEntity(entity);
		
		Entity entity2 = new Entity();
		entity2.add(new StageComponent());
		engine.addEntity(entity2);
		
		Entity optionsEntity = new Entity();
		optionsEntity.add(new OptionsComponent());
		engine.addEntity(optionsEntity);

		engine.addSystem(new BackgroundSystem());
		//engine.addSystem(new RenderConnectionSystem());
		engine.addSystem(new ColorBeatSystem());
		engine.addSystem(new RenderSystem());
		engine.addSystem(new AvatarSpawn());
		engine.addSystem(new MovementSystem());
		engine.addSystem(new AvatarLoadSystem());
		engine.addSystem(new ViewportSystem());
		engine.addSystem(new InactivitySystem());
		engine.addSystem(new ChatSimulationSystem());
	}

	private void systemOutput() {
		ProtonetSystem system = ProtonetHelper.getConnection().getSystems().index();
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
