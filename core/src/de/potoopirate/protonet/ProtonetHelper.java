package de.potoopirate.protonet;

import de.thathalas.protonet.ProtonetWrapper;

public class ProtonetHelper {
	
	public static final String PROTONET_HOST = "http://127.0.0.1:3001";
	//private static final String PROTONET_HOST = "https://carla.protonet.info";
	
	private static ProtonetWrapper PROTONET = new ProtonetWrapper(PROTONET_HOST);
	
	public static void initProtonetWrapper(String email, String password) {
		PROTONET.getToken(email, password, "ProtoGraph");
	}
	
	public static ProtonetWrapper getConnection() {
		ProtonetWrapper protonet = new ProtonetWrapper(PROTONET_HOST);
		protonet.setToken(PROTONET.getToken());
		return protonet;
	}
}
