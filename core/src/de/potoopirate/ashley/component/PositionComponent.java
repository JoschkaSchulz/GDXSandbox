package de.potoopirate.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent extends Component {
	public float x;
	public float y;
	public float rotation;
	public Vector2 direction;
	
	public PositionComponent(float x, float y) {
		this.x = x;
		this.y = y;
		direction = new Vector2(1f, 0f);
	}
}
