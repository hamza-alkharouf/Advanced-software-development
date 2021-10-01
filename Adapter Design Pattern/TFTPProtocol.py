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

    
