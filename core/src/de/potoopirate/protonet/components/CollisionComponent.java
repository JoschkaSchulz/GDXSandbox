package de.potoopirate.protonet.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class CollisionComponent extends Component {
	public Rectangle boundingBox;
	
	public CollisionComponent(float x, float y, float width, float height) {
		boundingBox = new Rectangle(x, y, width, height);
	}
}
