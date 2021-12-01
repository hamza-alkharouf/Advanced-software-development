# Factory Design Pattern

Factory pattern is one of the most used design patterns in Java. This type of design pattern comes under creational pattern as this pattern provides one of the best ways to create an object.

In Factory pattern, we create object without exposing the creation logic to the client and refer to newly created object using a common interface.

## Getting Started

### Prerequisites

The things you need before installing the software.

* You need install **Java**
* And you need Code Editor(IntelliJ, eclipse, ..)

##

- The Question :
  * Part 1: 
  
    In the code below, the “edu.najah.it.capp.asd.service.Connection” class calls the contercreate(The        implementation) classes in order to create/get objects for protocols (FTP, TFTP, SCP and SSH).          Factory method pattern solves this problem so we want to create a ProtocoleFactory class that is        used to create/get objects of the protocols. 

  https://github.com/mkassaf/AdvancedSoftwareDevelopment/tree/main/Design%20Pattern/Factory%20Method 
  
  * part 2 :
    1. Draw the class diagram for the Factory method in Part 1.
    2. Which SOLID principle(s) is solved by the Factory method and how?


- The Solution :

  * Part 1:
   
       https://github.com/hamza-alkharouf/Advanced-software-development/tree/main/Factory%20Design%20Pattern/FactoryMethod
       
  * part 2 :
  1. Class Diagram of Factory Pattern
  
  
    ![Design Pattern - Factory Pattern](https://github.com/hamza-alkharouf/Advanced-software-development/blob/main/Factory%20Design%20Pattern/image/factory_pattern.png)
    
    
   2. 
   * SOLID principles are a set of general guidelines that are widely applicable to consider as you       go throughout the process of designing your code structure.
   
   * The factory method doesn't follow any principle but it is meant to separate the responsibility of object creation to a different class to deal with the problem of creating objects without having to specify the exact class of the object that will be created.
   
   
   ##
   
    ###### Create Folder Factory to maek file  ProtocolFactory 
  Create a Factory

```
  
public class ProtocolFactory {
    public static Protocol getInstance(String ConnectionTypeProtocol){

        if(ConnectionTypeProtocol == null){
            return null;
        }
        if(ConnectionTypeProtocol.equalsIgnoreCase("TELNET")){

            return Telnet.getInsatnce();

        } else if(ConnectionTypeProtocol.equalsIgnoreCase("SSH")) {
            return Ssh.getInsatnce();

        }else if(ConnectionTypeProtocol.equalsIgnoreCase("SCP")) {
            return Scp.getInsatnce();

        }

        return null;
    }
    public static void release(String ConnectionTypeProtocol){



        if(ConnectionTypeProtocol.equalsIgnoreCase("TELNET")){

            Telnet.getInsatnce().release();

        } else if(ConnectionTypeProtocol.equalsIgnoreCase("SSH")) {
            Ssh.getInsatnce().release();

        }else if(ConnectionTypeProtocol.equalsIgnoreCase("SCP")) {
            Scp.getInsatnce().release();

        }else if(ConnectionTypeProtocol.equalsIgnoreCase("FTP")) {
            Ftp.getInsatnce().release();

        }

    }

}

```

###### Of Connection 

- create object 

```
  ProtocolFactory protocolfactory = new ProtocolFactory();
```

- passing ConnectionType.protocol

```
  protocolfactory.getInstance(ConnectionType.SSH);
  .
  .
  .
  .
  .
  
```
##

```
  
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

```


   
   
   
