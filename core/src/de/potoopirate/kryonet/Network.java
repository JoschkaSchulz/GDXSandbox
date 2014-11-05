package de.potoopirate.kryonet;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.examples.chat.Network.ChatMessage;
import com.esotericsoftware.kryonet.examples.chat.Network.RegisterName;
import com.esotericsoftware.kryonet.examples.chat.Network.UpdateNames;

public class Network {
	static public void register (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Position.class);
		kryo.register(Connect.class);
	}
	
	static public class Connect {
		public int id;
	}
	
	static public class Position {
		public int id;
		public int x;
		public int y;
	}
}
