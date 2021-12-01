package edu.najah.it.capp.asd;


import edu.najah.it.capp.asd.constants.ConnectionType;
import edu.najah.it.capp.asd.intf.Protocol;
import edu.najah.it.capp.asd.service.Connection;
import edu.najah.it.capp.exception.*;
import edu.najah.it.capp.logger.Logger;


public class Demo {

	public static void main(String[] args) throws ProtocolException {
		int numberOfTries = 5;
		int state = 200;

		while(numberOfTries > 0) {
			try {
				Protocol ssh = Connection.getInstance(ConnectionType.SSH);
				Protocol telnet = Connection.getInstance(ConnectionType.TELNET);
				Protocol scp = Connection.getInstance(ConnectionType.SCP);


				//Protocol ssh2 = Connection.getInstance(ConnectionType.SSH); //1
				//Protocol ftp = Connection.getInstance(ConnectionType.FTP); //0

				Logger.getInstance().logInfo("comment 1,2 in demo and comment 1 in connection class to run 0");
				Logger.getInstance().logInfo("comment 0 in demo and connection class to run 1");


				Connection.release(ConnectionType.SSH);
				//Connection.release(ConnectionType.SSH);

				String shh = "SSH";
				if (ConnectionType.SSH == shh){
					throw new ConnectionInUseExpcetion("Connection is inuse, you can’t release now");
				}

				if (state != 200){
					throw new UnknownErrorExpcetion("Unable to release the connection because of an unknown error");
				}
				Logger.getInstance().logInfo("This is a info message");
				break;
			} catch (ProtocolBusyException e) {

				try {
					System.out.println("ProtocolBusyException:: " + e.getMessage());
					e.printStackTrace();
					numberOfTries--;
					sleep(1000L);
					if (numberOfTries == 0){
						throw new FailedSendExpcetion("");
					}
					Logger.getInstance().logWarning("This is a warning message");
				}catch (FailedSendExpcetion e1) {

					System.out.println("numberOfTries:: " + numberOfTries);
					System.out.println("Failed to send the data because of a timeout error:: " + e1.getMessage());
					e1.printStackTrace();

					Logger.getInstance().logError("This is a error message");
					Logger.getInstance().logDebug("Waiting time is over so you need released protocol to crete new connection.");
				}
				Logger.getInstance().logError("This is a error message");
				Logger.getInstance().logDebug("Protocol Busy so you need to wait ");

			} catch (ConnectionAlreadyReleasedException e) {

				System.out.println("ConnectionAlreadyReleasedException:: " + e.getMessage());
				e.printStackTrace();
				Logger.getInstance().logError("This is a error message");
				Logger.getInstance().logDebug("Connection Already Released Exception so to create new connection to Release it");

				break;
			}catch (NoConnectionAvailableException e) {

				System.out.println("NoConnectionAvailableException:: " + e.getMessage());
				e.printStackTrace();
				Logger.getInstance().logError("This is a error message");
				Logger.getInstance().logDebug("This is a debug message");

				break;
			}catch (UnknownErrorExpcetion e){
				System.out.println("UnknownErrorExpcetion:: " + e.getMessage());
				e.printStackTrace();
				Logger.getInstance().logError("This is a error message");
				Logger.getInstance().logDebug("make state 200");
				break;
			}
			//2
			finally {
				release();
			}



		}

//		Logger.getInstance().logInfo("This is a info message");
//		Logger.getInstance().logDebug("This is a debug message");
//		Logger.getInstance().logWarning("This is a warning message");
//		Logger.getInstance().logError("This is a error message");
//
//
//		Protocol ssh = Connection.getInstance(ConnectionType.SSH);
//		Protocol ssh2 = Connection.getInstance(ConnectionType.SSH);
//		Protocol telnet = Connection.getInstance(ConnectionType.TELNET);
//		Protocol scp = Connection.getInstance(ConnectionType.SCP);
//		Protocol ftp = Connection.getInstance(ConnectionType.FTP);
//
//
//		if(ssh == ssh2) {
//			System.out.println(" ssh is equal to ssh2");
//		}
//		ssh.send(" testing ssh ");
//		telnet.send("Testing telnet ");
//		scp.send("Testing scp");
//
//
//		System.out.println(Connection.getCurrentConnections());
//		Connection.release(ConnectionType.SSH);
//		System.out.println(Connection.getCurrentConnections());
//
//		ftp = Connection.getInstance(ConnectionType.FTP);
//		System.out.println(Connection.getCurrentConnections());
//
//
//		ssh = Connection.getInstance(ConnectionType.SSH);
//		ftp = Connection.getInstance(ConnectionType.FTP);
//		ftp = Connection.getInstance(ConnectionType.FTP);
//
//		ftp = Connection.getInstance(ConnectionType.FTP);
//		ftp.send("Testing FTP");
//		Connection.release(ConnectionType.FTP);
//
//		Protocol tftp = Connection.getInstance(ConnectionType.TFTP);
//		Protocol tftp2 = Connection.getInstance(ConnectionType.TFTP);
//		System.out.println(Connection.getCurrentConnections());
//		if(tftp == tftp2 ) {
//			System.out.println("Same object");
//		}
//		tftp.send("test the TFTP ");
//		tftp2.send("test the TFTP ");
//
//		//ftp = Ftp.getInsatnce();
//		///Connection.release(ConnectionType.TFTP);
//		System.out.println(Connection.getCurrentConnections());//3
//		if(ftp == null) {
//			System.out.println("FTP is a null");
//		} else {
//			System.out.println("FTP is not a null");
//		}
//		ftp.send(" breaking the logic ");
//
//
	}
	private static void sleep(Long timeout)  {
		try {
			Thread.sleep(timeout);

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}

	}

	private static void release() throws ProtocolException {

		for (String protocol : Connection.getCurrentConnections()){
			if (ConnectionType.SSH == protocol){
				Connection.release(ConnectionType.SSH);
			}
			if (ConnectionType.SSH == protocol){
				Connection.release(ConnectionType.TELNET);
			}
			if (ConnectionType.SSH == protocol){
				Connection.release(ConnectionType.SCP);
			}
			if (ConnectionType.SSH == protocol){
				Connection.release(ConnectionType.FTP);
			}

		}


	}
}
