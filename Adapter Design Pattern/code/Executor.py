
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
