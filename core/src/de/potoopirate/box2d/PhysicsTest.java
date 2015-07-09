package de.potoopirate.box2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsTest extends ScreenAdapter {

	private World world;
	private OrthographicCamera cam;
	private Box2DDebugRenderer renderer;
	
	private Body body;
	private Fixture fixture;
	
	@Override
	public void show() {
		super.show();
		renderer = new Box2DDebugRenderer();
		world = new World(new Vector2(0, 0), true);
		
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        
        addSpaceship(cam.project(new Vector3(100, 300, 0)), cam.project(new Vector3(50, 25, 0)));
        
        for(int i = 0; i < 100; i++) {
        	addStuff(cam.project(new Vector3(((float)Math.random())*2000, ((float)Math.random())*2000, 0)), cam.project(new Vector3(25, 25, 0)));	
        }
	}
	
	public void addStuff(Vector3 pos, Vector3 size)  {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(pos.x, pos.y);
		
		Body body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.x, size.y);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.005f; 
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.0f;
		
		Fixture fixture = body.createFixture(fixtureDef);
		
		shape.dispose();
	}
	
	public void addSpaceship(Vector3 pos, Vector3 size)  {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(pos.x, pos.y);
		
		body = world.createBody(bodyDef);
		body.setFixedRotation(true);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.x, size.y);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.001f; 
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.0f;
		
		fixture = body.createFixture(fixtureDef);
		
		shape.dispose();
	}
	
	public void addGround() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, 0);
		
		Body body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1000, 10);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		
		Fixture fixture = body.createFixture(fixtureDef);
		
		shape.dispose();
	}
	
	@Override
	public void render(float delta) {
		world.step(1f/45f, 6, 2);

		super.render(delta);
		Gdx.gl.glClearColor(1f, 1f,1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render(world, cam.combined);
		
		if (Gdx.input.isKeyPressed(Keys.W)) {
			body.applyForceToCenter(
					((float) Math.cos(body.getAngle())) * 1000 * delta, 
					((float) Math.sin(body.getAngle())) * 1000 * delta, true);
			body.setLinearVelocity(
					MathUtils.clamp(body.getLinearVelocity().x, -500, 500), 
					MathUtils.clamp(body.getLinearVelocity().y, -500, 500));
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			body.applyForceToCenter(
					-(((float) Math.cos(body.getAngle())) * 100 * delta), 
					-(((float) Math.sin(body.getAngle())) * 100 * delta), true);
			body.setLinearVelocity(
					MathUtils.clamp(body.getLinearVelocity().x, -500, 500), 
					MathUtils.clamp(body.getLinearVelocity().y, -500, 500));
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			body.setTransform(
					body.getPosition().x + fixture.getShape().getRadius(), 
					body.getPosition().y + fixture.getShape().getRadius(), 
					body.getAngle()+1*delta);
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			body.setTransform(
					body.getPosition().x + fixture.getShape().getRadius(), 
					body.getPosition().y + fixture.getShape().getRadius(), 
					body.getAngle()-1*delta);
		}
		
		cam.position.set(body.getPosition().x, body.getPosition().y, 0);
		cam.update();
	}
}
