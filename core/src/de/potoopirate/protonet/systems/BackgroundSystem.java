package de.potoopirate.protonet.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import de.potoopirate.protonet.components.HexagonComponent;
import de.potoopirate.protonet.components.StageComponent;

public class BackgroundSystem extends IteratingSystem {

	private ComponentMapper<StageComponent> stageMapper;
	private Stage stage;
	private ComponentMapper<HexagonComponent> hexagonMapper;
	private Array<HexagonComponent> hexagone; 
	
	private ShapeRenderer renderer;
	
	public BackgroundSystem() {
		super(Family.getFor(HexagonComponent.class));
		hexagone = new Array<HexagonComponent>();
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		
		Entity entity;
		HexagonComponent hexagonComp;
		for(int x = -15; x < 15; x++) {
			for(int y = -15; y < 15; y++) {
				entity = new Entity();
				hexagonComp = new HexagonComponent((y%2==0 ? x*200+100 : x*200), y*150, 95);
				entity.add(hexagonComp);
				engine.addEntity(entity);
			}
		}
		
		hexagonMapper = ComponentMapper.getFor(HexagonComponent.class);
		stageMapper = ComponentMapper.getFor(StageComponent.class);
		renderer = new ShapeRenderer();
		
		stage = stageMapper.get(engine.getEntitiesFor(Family.getFor(StageComponent.class)).first()).stage;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		float sX = stage.getRoot().getX();
		float sY = stage.getRoot().getY();
		
		renderer.begin(ShapeType.Line);
		renderer.setAutoShapeType(true);
		for(HexagonComponent hex : hexagone) {
			renderer.set(hex.shapeType);
			if(hex.shapeType == ShapeType.Filled) {
				drawTriangle(hex.x+sX, hex.y+sY, hex.size);
			} else {
				renderer.setColor(hex.r, hex.g, hex.b, hex.a);
			}
			drawPolygonHex(hex.x, hex.y, hex.size);
			/* if (Gdx.input.getX() > hex.x+sX && Gdx.input.getX() < hex.x+sX+190 &&
					Gdx.graphics.getHeight()-Gdx.input.getY() > hex.y+sY+47 && Gdx.graphics.getHeight()-Gdx.input.getY() < hex.y+sY+47+190) {
				hex.raise = 1;
			}*/
			// bounding boxes? renderer.rect(hex.x+sX, hex.y+sY+47, 190, 190);
		}
		renderer.end();
		hexagone.clear();
	}
	
	private void drawTriangle(float x, float y, float size) {
		//top
		renderer.triangle(1*size+x,2.5f*size+y, 2*size+x,2*size+y, 1*size+x,1.5f*size+y);
		renderer.triangle(0*size+x,2*size+y, 1*size+x,2.5f*size+y, 1*size+x,1.5f*size+y);
		//mid
		renderer.triangle(2*size+x,2*size+y, 2*size+x,1*size+y, 1*size+x,1.5f*size+y);
		renderer.triangle(0*size+x,1*size+y, 0*size+x,2*size+y, 1*size+x,1.5f*size+y);
		//down
		renderer.triangle(2*size+x,1*size+y, 1*size+x,0.5f*size+y, 1*size+x,1.5f*size+y);
		renderer.triangle(1*size+x,0.5f*size+y, 0*size+x,1*size+y, 1*size+x,1.5f*size+y);
	}
	
	private void drawPolygonHex(float x, float y, float size) {
		float[] poly = {
				1f*size+x,2.5f*size+y, 
				2f*size+x,2f*size+y, 
				2f*size+x,1f*size+y, 
				1f*size+x,0.5f*size+y, 
				0f*size+x,1f*size+y, 
				0f*size+x,2f*size+y};
		renderer.polygon(poly);
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		hexagone.add(hexagonMapper.get(entity));
	}
	
}
