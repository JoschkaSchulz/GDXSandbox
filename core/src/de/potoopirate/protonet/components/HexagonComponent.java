package de.potoopirate.protonet.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class HexagonComponent extends Component {
	public float x, y;
	public float size;
	public float r, g, b, a;
	public float raise;
	public ShapeType shapeType;
	
	public HexagonComponent(float x, float y, float size) {
		this.x = x;
		this.y = y;
		this.size = size;
		r = g = b = 0.1f;
		a = 1f;
		raise = 0f;
		shapeType = ShapeType.Line;
	}
}
