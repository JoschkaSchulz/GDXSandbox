package de.potoopirate.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class CollisionComponent extends Component {
	public int circleRadius;
	public boolean isCrashed = false;
	
	public CollisionComponent(int size) {
		circleRadius = size;
	}
}
