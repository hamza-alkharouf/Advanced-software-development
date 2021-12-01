package edu.najah.it.capp.asd;


import edu.najah.it.capp.asd.constants.ConnectionType;
import edu.najah.it.capp.asd.intf.Protocol;
import edu.najah.it.capp.asd.service.Connection;
import edu.najah.it.capp.exception.*;


public class Demo {

	public static void main(String[] args) throws ProtocolException {
		int numberOfTries = 5;
		int state = 200;

		while(numberOfTries > 0) {
			try {
				Protocol ssh = Connection.getInstance(ConnectionType.SSH);
				//Protocol ssh2 = Connection.getInstance(ConnectionType.SSH);
				Protocol telnet = Connection.getInstance(ConnectionType.TELNET);
				Protocol scp = Connection.getInstance(ConnectionType.SCP);
				//Protocol ftp = Connection.getInstance(ConnectionType.FTP);

				Connection.release(ConnectionType.SSH);
				//Connection.release(ConnectionType.SSH);

				if (state == 400){
					throw new UnknownErrorExpcetion("Unable to release the connection because of an unknown error");
				}

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
				}catch (FailedSendExpcetion e1) {

					System.out.println("numberOfTries:: " + numberOfTries);
					System.out.println("Failed to send the data because of a timeout error:: " + e1.getMessage());
					e1.printStackTrace();
				}

			} catch (ConnectionAlreadyReleasedException e) {

				System.out.println("ConnectionAlreadyReleasedException:: " + e.getMessage());
				e.printStackTrace();
				break;
			}catch (NoConnectionAvailableException e) {

				System.out.println("NoConnectionAvailableException:: " + e.getMessage());
				e.printStackTrace();
				break;
			}catch (UnknownErrorExpcetion e){
				System.out.println("UnknownErrorExpcetion:: " + e.getMessage());
				e.printStackTrace();
				break;
			}finally {
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
