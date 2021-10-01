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