package com.FactoryMethod.Factory;

import com.FactoryMethod.constants.ConnectionType;
import com.FactoryMethod.intf.Protocol;
import com.FactoryMethod.service.Connection;

public class ConnectionFactory
{
    public static Protocol CreateConnection(String ConnectionTypeProtocol){

        if(ConnectionTypeProtocol == null){
            return null;
        }
        if(ConnectionTypeProtocol.equalsIgnoreCase("TELNET")){

            return (Protocol) Connection.getInstance(ConnectionType.TELNET);

        } else if(ConnectionTypeProtocol.equalsIgnoreCase("SSH")) {
            return (Protocol) Connection.getInstance(ConnectionType.SSH);

        }else if(ConnectionTypeProtocol.equalsIgnoreCase("SCP")) {
            return (Protocol) Connection.getInstance(ConnectionType.SCP);

        }else if(ConnectionTypeProtocol.equalsIgnoreCase("FTP")) {
            return (Protocol) Connection.getInstance(ConnectionType.FTP);
        }

        return null;
    }

    public static void ReleaseConnection(String ConnectionTypeProtocol) {

        if(ConnectionTypeProtocol.equalsIgnoreCase("TELNET")){
            Connection.release(ConnectionType.TELNET);

        } else if(ConnectionTypeProtocol.equalsIgnoreCase("SSH")) {
            Connection.release(ConnectionType.SSH);

        }else if(ConnectionTypeProtocol.equalsIgnoreCase("SCP")) {
            Connection.release(ConnectionType.SCP);

        }else if(ConnectionTypeProtocol.equalsIgnoreCase("FTP")) {
            Connection.release(ConnectionType.FTP);

        }
    }

    public static void getCurrentConnections() {
        System.out.println(Connection.getCurrentConnections());
    }

}
