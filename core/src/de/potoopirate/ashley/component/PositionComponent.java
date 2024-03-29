package de.potoopirate.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent extends Component {
	public float x;
	public float y;
	public float z;
	public float rotation;
	public Vector2 direction;
	
	public PositionComponent(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 0f;
		direction = new Vector2((float)(Math.random()*2-1), (float)(Math.random()*2-1)).nor();
	}
}
