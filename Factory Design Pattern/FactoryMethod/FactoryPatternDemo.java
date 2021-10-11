package com.FactoryMethod;
import com.FactoryMethod.Factory.ConnectionFactory;
import com.FactoryMethod.intf.Protocol;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Protocol ssh = ConnectionFactory.CreateConnection("SSH");
        Protocol ssh2 = ConnectionFactory.CreateConnection("SSH");
        Protocol telnet = ConnectionFactory.CreateConnection("TELNET");
        Protocol scp = ConnectionFactory.CreateConnection("SCP");
        Protocol ftp = ConnectionFactory.CreateConnection("FTP");
        if(ssh == ssh2) {
            System.out.println(" ssh is equal to ssh2");
        }
        ssh.send(" testing ssh ");
        telnet.send("Testing telnet ");
        scp.send("Testing scp");

        connectionFactory.getCurrentConnections();
        connectionFactory.ReleaseConnection("SSH");
        connectionFactory.getCurrentConnections();


        ftp = ConnectionFactory.CreateConnection("FTP");
        connectionFactory.getCurrentConnections();


        ssh = ConnectionFactory.CreateConnection("SSH");
        ftp = ConnectionFactory.CreateConnection("FTP");
        ftp = ConnectionFactory.CreateConnection("FTP");

        ftp = ConnectionFactory.CreateConnection("FTP");
        ftp.send("Testing FTP");
    }

}
