import zope.interface


class MyInterface(zope.interface.Interface):
    def Instance(cls):
        pass

    def getInstance(self, protoco):
        pass

    def getCurrentConnections(self):
        pass

    def send(self,message):
        pass

    def release(self,connectionProtocol):
        pass


