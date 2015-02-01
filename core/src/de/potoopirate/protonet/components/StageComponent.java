package de.potoopirate.protonet.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class StageComponent extends Component {
	public Stage stage;
	
	public StageComponent() {
		stage = new Stage();
	}
}
