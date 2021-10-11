package com.FactoryMethod.Factory;

import com.FactoryMethod.constants.ConnectionType;
import com.FactoryMethod.impl.Ftp;
import com.FactoryMethod.impl.Scp;
import com.FactoryMethod.impl.Ssh;
import com.FactoryMethod.impl.Telnet;
import com.FactoryMethod.intf.Protocol;
import com.FactoryMethod.service.Connection;

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
