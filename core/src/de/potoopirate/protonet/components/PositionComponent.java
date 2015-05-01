package de.potoopirate.protonet.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class PositionComponent extends Component {
	public Vector3 position;
	
	public PositionComponent(float x, float y) {
		position = new Vector3(x, y, 0);
	}
}
