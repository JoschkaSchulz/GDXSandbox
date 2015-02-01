package de.potoopirate.ashley.component;

import com.badlogic.ashley.core.Component;

public class LifetimeComponent extends Component {
	public float lifetime;
	public float lifetimeCounter;
	
	public LifetimeComponent() {
		lifetime = 5f;
		lifetimeCounter = 0f;
	}
	
	public LifetimeComponent(float lifetime) {
		this.lifetime = lifetime;
		this.lifetimeCounter = 0f;
	}
}
