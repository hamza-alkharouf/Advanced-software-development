package edu.najah.it.capp.asd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.najah.it.capp.asd.constants.ConnectionType;
import edu.najah.it.capp.asd.impl.ProtocolFactory;
import edu.najah.it.capp.asd.intf.Protocol;
import edu.najah.it.capp.exception.ConnectionAlreadyReleasedException;
import edu.najah.it.capp.exception.NoConnectionAvailableException;
import edu.najah.it.capp.exception.ProtocolBusyException;
import edu.najah.it.capp.exception.ProtocolException;

public class Connection {
	
	
	
	public static Map connections = new HashMap<String, Protocol>();
	//getInstance, createConnection , getConnection 
	public static Protocol getInstance(String connectionType) throws ProtocolException{
		if(connections.containsKey(connectionType)) {
			throw new NoConnectionAvailableException("Connection is already created!.");
		} else {
			if(connections.size() >= 3 ) {
				throw new ProtocolBusyException("Can't create more than 3 connection!!");
			}
			Protocol instance = ProtocolFactory.createProcol(connectionType);
			connections.put(connectionType, instance);
			return instance;
			
		}
	}
	
	public static boolean release(String connectionType) throws ProtocolException {
		if(connections.containsKey(connectionType)) {
			connections.remove(connectionType);
			
			ProtocolFactory.createProcol(connectionType).release();
			
			return true;
		}
		throw new ConnectionAlreadyReleasedException("Connection is already released !!");

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
