import gc

class Connection(object):
    instance = None
    protocoles =[ ]

    SSH   = ['SSH', False]
    TENET = ['TENET', False]
    HTTP  = ['HTTP', False]
    SCP   = ['SCP', False]
    FTP   = ['FTP', False]
    checkIsProtocol = [SSH, TENET ,HTTP ,SCP ,FTP]

    @classmethod
    def Instance(cls):
        cls.instance =Connection()
        return cls.instance

    def getInstance(self, protocol):
        for x in range(len(self.checkIsProtocol)):            
            if protocol[0] is str(self.checkIsProtocol[x][0]):
                if len(self.protocoles) == 0 :
                    self.checkIsProtocol[x][1] = True
                    self.protocol = self.checkIsProtocol[x]
                    self.protocoles.append(self.checkIsProtocol[x])
                    return self
                elif len(self.protocoles) < 3:
                    if protocol[1] == True:
                        return 'Current %s connection'%protocol
                    else:
                        self.checkIsProtocol[x][1] = True
                        self.protocol = self.checkIsProtocol[x]
                        self.protocoles.append(self.checkIsProtocol[x])
                        return self
                else :
                    return Exception("You can\'t create more than 3 connections!")    
                    
    def getCurrentConnections(self):
        return self.protocoles

    def getData(self):
        return self.protocol

    def send(self,message):
        print('sending [%s] vi %s protocol'%(message,self.protocol[0]))

    def release(self,connectionProtocol):
        print(locals())
        connectionProtocol[1] = False
        for x in range(len(self.protocoles)):
            if self.protocoles[x][0] is connectionProtocol[0]:
                del self.protocoles[x]
                return True 
        return False

def main():

    tenetConnection = Connection.Instance().getInstance(Connection.TENET)
    print(tenetConnection.getData())
    
    tenetConnection2 = Connection.Instance().getInstance(Connection.TENET)
    print(tenetConnection2)
    
    sshConnection = Connection.Instance().getInstance(Connection.SSH)
    print(sshConnection.getData())
    
    httpConnection = Connection.Instance().getInstance(Connection.HTTP)
    print(httpConnection.getData())
    
    scpConnection = Connection.Instance().getInstance(Connection.SCP)
    print(scpConnection)
    
    httpConnection2 = Connection.Instance().getInstance(Connection.HTTP)
    print(httpConnection2)
    
    isReleased = Connection.Instance().release(Connection.TENET)
    del tenetConnection
    print(isReleased)

    ftpConnection = Connection.Instance().getInstance(Connection.FTP)
    print(ftpConnection.getData())

    CurrentConnections = Connection.Instance().getCurrentConnections()
    print(CurrentConnections)

    ftpConnection.send('my message')
    sshConnection.send('my message')


    

if __name__ == "__main__":
    main()
    