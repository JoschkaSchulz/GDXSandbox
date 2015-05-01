package de.potoopirate.protonet.components;

import com.badlogic.ashley.core.Component;

import de.potoopirate.protonet.ProtonetHelper;

public class OptionsComponent extends Component {
	public String host;
	public String group;
	
	public OptionsComponent() {
		host = ProtonetHelper.PROTONET_HOST;
		group = "Protonet Team";
	}
}
