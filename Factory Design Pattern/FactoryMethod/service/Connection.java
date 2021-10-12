package com.Factory_Design_Pattern.FactoryMethod.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.FactoryMethod.Factory.ProtocolFactory;
import com.FactoryMethod.constants.ConnectionType;
import com.FactoryMethod.impl.Ftp;
import com.FactoryMethod.impl.Scp;
import com.FactoryMethod.impl.Ssh;
import com.FactoryMethod.impl.Telnet;
import com.FactoryMethod.intf.Protocol;

public class Connection {
	
	public static Map connections = new HashMap<String, Protocol>();
	//getInstance, createConnection , getConnection 
	//release (String type)
	//getCurrentConnections
	public static Protocol getInstance(String connectionType) {
		if(connections.containsKey(connectionType)) {
			System.out.println("Connection is already created!.");
			return (Protocol) connections.get(connectionType);
		} else {
			if(connections.size() >= 3 ) {
				//do not create connection
				System.out.println("Can't create more than 3 connection!!");
				return null;
			}
			ProtocolFactory protocolfactory = new ProtocolFactory();
			if(connectionType.equals(ConnectionType.FTP)) {
				Protocol ftp = new Ftp();
				connections.put(connectionType, ftp);
				return ftp;//Create a new instance
			}
			if(connectionType.equals(ConnectionType.SSH)) {
				connections.put(connectionType, Ssh.getInsatnce());
				return protocolfactory.getInstance(ConnectionType.SSH);
			}
			if(connectionType.equals(ConnectionType.TELNET)) {
				connections.put(connectionType, Telnet.getInsatnce());
				return protocolfactory.getInstance(ConnectionType.TELNET);
			}
			if(connectionType.equals(ConnectionType.SCP)) {
				connections.put(connectionType, Scp.getInsatnce());
				return protocolfactory.getInstance(ConnectionType.SCP);
			}
		}
		return null;
		
	}
	
	public static boolean release(String connectionType) {
		if(connections.containsKey(connectionType)) {
			connections.remove(connectionType);
			ProtocolFactory protocolfactory = new ProtocolFactory();

			if(connectionType.equals(ConnectionType.FTP)) {
				protocolfactory.release(ConnectionType.FTP);
				
			} else if(connectionType.equals(ConnectionType.SSH)) {
				protocolfactory.release(ConnectionType.SSH);

			} else if(connectionType.equals(ConnectionType.TELNET)) {
				protocolfactory.release(ConnectionType.TELNET);

			} else if(connectionType.equals(ConnectionType.SCP) ) {
				protocolfactory.release(ConnectionType.SCP);

			}
			return true;
		}
		
		return false;
		
	}
	public static ArrayList<String> getCurrentConnections() {
		Set<String> keys = connections.keySet();
		
		ArrayList<String> myConnection =  new ArrayList<String>();
		for (String key : keys) {
			myConnection.add(key);
		}
		return myConnection;
		
		
	}

}
