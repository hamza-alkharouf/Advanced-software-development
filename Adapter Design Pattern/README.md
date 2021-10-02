# Adapter Design Pattern

Adapter pattern works as a bridge between two incompatible interfaces.
This type of design pattern comes under structural pattern as this pattern combines the capability of two independent interfaces.

This pattern involves a single class which is responsible to join functionalities of independent or incompatible interfaces. 
A real life example could be a case of card reader which acts as an adapter between memory card and a laptop.
You plugin the memory card into card reader and card reader into the laptop so that memory card can be read via laptop.


- The Question:

  1. Analyze the below solution and check how it violates the SOLID principle. (At least two)
  https://github.com/mkassaf/AdvancedSoftwareDevelopment/blob/main/Design%20Pattern/Singleton/src/edu/najah/cap/Connection.java

  2. Check if your solution 100% applies the SOLID principle and fix it if any.
  
  3. Let’s assume we have legacy code that is used to connect and send messages via TFTP. The code used for TFTP connection is mentioned below. I want you to use the Adapter design pattern to add this protocol to your application. 

  
- The Solution :

  1. There is more than one violation of solid:
     - Open-Closed: As it is not expandable and adding new features such as adding a new protocol from a specific class.
      - Interface Segregation: It does not contain specific and carefully selected Interfaces to perform a specific task,
     or even a number of small Interfaces that perform a specific task is better than one large Interface that performs many tasks.
   
   2. My solution does not achieve 100% on the principle of SOLID,
      where fix is the same as the answer in the first question.
      
   3. Solution in Description
      
## Getting Started

### Prerequisites

The things you need before installing the software.

* You need install **python**
* and
  ```
    pip install zope.interface
  ```
* And you need Code Editor(vscode, atom, pycharm, ..)
 
 ### Description
 
  You can see the link below, to understand file otherProtocols.py
  https://github.com/hamza-alkharouf/Advanced-software-development/tree/main/Singleton%20design%20pattern
  
  
  ###### Make Interface 
  create file  Connection_Interface.py 

```
  
 import zope.interface
 
class MyInterface(zope.interface.Interface):
    def Instance(cls):
        pass

    def getInstance(self, protoco):
        pass

    def getCurrentConnections(self):
        pass

    def send(self,message):
        pass

    def release(self,connectionProtocol):
        pass
```

  ###### Make TFTPProtocol
  create file  TFTPProtocol.py

```
  
class TFTPProtocol():

    instance = None
    connected = False

 
    def isConnected(self):
        return self.connected

    def TFTPProtocol(self):
        self.connected =True
    
    def getTFTPInstance(self):
        if(self.instance ==None):
            self.instance = TFTPProtocol()
        return self.instance

    def releaseTFTP(self):
        if(self.instance !=None):
            self.instance = None
            return True
        return False

    def sendMessage(self,message):
        print("I am sending a %s vi TFTP Protocol"%message)
```



  ###### To make Adapter we need to create PluginAdapter
  create file  PluginAdapter.py

```
  
from typing import overload
import zope.interface
from Connection_Interface import *
from TFTPProtocol import *


@zope.interface.implementer(MyInterface)
# class Connection
class PluginAdapter:
    plugin = TFTPProtocol()

    def __init__(self, plugin):
        self.plugin = plugin
    
    def Instance(self):
        return self.plugin.isConnected()

    def getInstance(self):
        return self.plugin.TFTPProtocol()

    def getCurrentConnections(self):
        return self.plugin.getTFTPInstance()

    def getData(self):
        return ["TFTP", self.plugin.connected]

    def release(self):
        return self.plugin.releaseTFTP()
    
    def send(self,message):
        return self.plugin.sendMessage(message)
```

  ###### Finally we need Executor the Adapter
  create file  Executor.py

```
  
from Connection_Interface import *
from otherProtocols import *
from TFTPProtocol import *
from PluginAdapter import *

def usePlugin(plugin):
    if (isinstance(plugin, Connection)):
        # create a new object and return a TELNET connection
        tenetConnection = plugin.getInstance(Connection.TENET)
        print(tenetConnection.getData())
        
        # return the current TELNET connection 
        tenetConnection2 = plugin.getInstance(Connection.TENET)
        print(tenetConnection2)
        
        # create a new object
        sshConnection = plugin.getInstance(Connection.SSH)
        print(sshConnection.getData())
        
        # create a new object
        httpConnection = plugin.getInstance(Connection.HTTP)
        print(httpConnection.getData())
        
        # throw an expectation telling the user he can’t create more than 3 connections!
        scpConnection = plugin.getInstance(Connection.SCP)
        print(scpConnection)
        
        # return the current HTTP connection 
        httpConnection2 = plugin.getInstance(Connection.HTTP)
        print(httpConnection2)
        
        """
            will delete the current TELNET connection and return true, 
            and it will return false if there is no TELNET connection at all.
        """
        isReleased = plugin.release(Connection.TENET)
        del tenetConnection
        print(isReleased)

        # create a new FTP connection and return it
        ftpConnection = plugin.getInstance(Connection.FTP)
        print(ftpConnection.getData())

        # return a list of current connections as Connection objects
        CurrentConnections = plugin.getCurrentConnections()
        print(CurrentConnections)

        # print a message says: Sending [My message] vi FTP protocol. 
        ftpConnection.send('my message')
        # print a message says: Sending [My message] vi SSH protocol. 
        sshConnection.send('my message')

    elif (isinstance(plugin, PluginAdapter)):
        print(plugin.release())
        print(plugin.Instance())
        plugin.getInstance()
        plugin.getCurrentConnections()
        print(plugin.release())
        plugin.send("[my message]")
    
    print("\n")

def main():

    usePlugin(Connection.Instance())

    tftpProtocol = PluginAdapter(TFTPProtocol())
    usePlugin(tftpProtocol)



if __name__ == "__main__":
    main()
```

