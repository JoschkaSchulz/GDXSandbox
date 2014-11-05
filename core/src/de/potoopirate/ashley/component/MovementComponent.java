package de.potoopirate.ashley.component;

import com.badlogic.ashley.core.Component;

public class MovementComponent extends Component {
	public float speed;
	
	public MovementComponent(int speed) {
		this.speed = speed;
	}
}
