# class Connection
class Connection(object):

    #instance
    instance = None
    # is protocoles we use it
    protocoles =[ ]

    # list attribute of Protocol it have type Protocol and boolean[false]:is not use 
    SSH   = ['SSH', False]
    TENET = ['TENET', False]
    HTTP  = ['HTTP', False]
    SCP   = ['SCP', False]
    FTP   = ['FTP', False]

    # Protocol we have 
    checkIsProtocol = [SSH, TENET ,HTTP ,SCP ,FTP]

    # define the classmethod
    @classmethod
    def Instance(cls):
        # Creating the object Connection
        cls.instance =Connection()
        # return the singleton instance
        return cls.instance

    """
    create a new object if there is no object created
    for this given type, else return the current object for the given protocol.
     @para : protocol ([Protocol ,boolean]) list have type of Protocol and boolean
    """
    def getInstance(self, protoco):

        # loop until the size of checkIsProtocol
        for x in range(len(self.checkIsProtocol)):
            # check if protocol is have it in checkIsProtocol        
            if protoco[0] is str(self.checkIsProtocol[x][0]):
                """
                check is protocoles is empty ,then create the first protocol,
                i make it because when i check is protoel is true[i use it] here we get error of range because is empty
                """
                if len(self.protocoles) == 0 :
                    # change the boolean of protocol to true because is use it now
                    self.checkIsProtocol[x][1] = True
                    # save the protocol to self protocol
                    self.protocol = self.checkIsProtocol[x]
                    # add protocol to protocoles
                    self.protocoles.append(self.checkIsProtocol[x])
                    # return object
                    return self
                # check if size of protocoles is less than 3
                elif len(self.protocoles) < 3:
                    #check if protocol is true(i use it)if true he return text say 'protocol is Current'
                    if protoco[1] == True:
                        return 'Current %s connection'%protoco
                    #if not
                    else:
                        # change the boolean of protocol to true because is use it now
                        self.checkIsProtocol[x][1] = True
                        # save the protocol to self protocol
                        self.protocol = self.checkIsProtocol[x]
                        # add protocol to protocoles
                        self.protocoles.append(self.checkIsProtocol[x])
                        # return object
                        return self
                #else this mean in protocoles i have 3 protocol i use it
                else :
                    return Exception("You can\'t create more than 3 connections!")    

    # return list of all current created connections.           
    def getCurrentConnections(self):
        return self.protocoles

    #get protocol and boolean when i use it
    def getData(self):
        return self.protocol

    # print a message //massage and type of protocol i use it
    def send(self,message):
        print('sending [%s] vi %s protocol'%(message,self.protocol[0]))

    # destroy the connection for the given protocol.
    def release(self,connectionProtocol):
        # change protocol from true to fase because i release it
        connectionProtocol[1] = False
        # loop until the size of protocoles
        for x in range(len(self.protocoles)):
            # check if connectionProtocol is exist in protocoles
            if self.protocoles[x][0] is connectionProtocol[0]:
                # delete the current protocol connection
                del self.protocoles[x]
                # return true,
                return True 
        # return false if there is no protocol connection at all.
        return False

def main():

    # create a new object and return a TELNET connection
    tenetConnection = Connection.Instance().getInstance(Connection.TENET)
    print(tenetConnection.getData())
    
    # return the current TELNET connection 
    tenetConnection2 = Connection.Instance().getInstance(Connection.TENET)
    print(tenetConnection2)
    
    # create a new object
    sshConnection = Connection.Instance().getInstance(Connection.SSH)
    print(sshConnection.getData())
    
    # create a new object
    httpConnection = Connection.Instance().getInstance(Connection.HTTP)
    print(httpConnection.getData())
    
    # throw an expectation telling the user he can’t create more than 3 connections!
    scpConnection = Connection.Instance().getInstance(Connection.SCP)
    print(scpConnection)
    
    # return the current HTTP connection 
    httpConnection2 = Connection.Instance().getInstance(Connection.HTTP)
    print(httpConnection2)
    
    """
        will delete the current TELNET connection and return true, 
	    and it will return false if there is no TELNET connection at all.
    """
    isReleased = Connection.Instance().release(Connection.TENET)
    del tenetConnection
    print(isReleased)

    # create a new FTP connection and return it
    ftpConnection = Connection.Instance().getInstance(Connection.FTP)
    print(ftpConnection.getData())

    # return a list of current connections as Connection objects
    CurrentConnections = Connection.Instance().getCurrentConnections()
    print(CurrentConnections)

    # print a message says: Sending [My message] vi FTP protocol. 
    ftpConnection.send('my message')
    # print a message says: Sending [My message] vi SSH protocol. 
    sshConnection.send('my message')


if __name__ == "__main__":
    main()
    