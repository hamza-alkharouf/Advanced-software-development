# Error Handling Techniques


   
* The Questions:
   - Explain why the logger uses a singleton design pattern. 
   - Explactions for solution in part 1 and 2.
 
* The Answers
   - Singleton classes are used in log file generations. Log files are created by the logger class object. Suppose an application where the logging utility has to produce one log file based on the messages received from the users. If there is multiple client application using this logging utility class they might create multiple instances of this class and it can potentially cause issues during concurrent access to the same logger file. We can use the logger utility class as a singleton and provide a global point of reference so that each user can use this utility and no 2 users access it at the same time.

   - solution method:
      - I created a logger info in try to clarify information that will be useful
      - I created 6 Exception .
      - Create while until it reaches a specified number of attempts so that if the connection is busy it waits about 1000 and
       gives a ProtocolBusyException and logger warning until    
      - it warns that an error may occur and if the number of attempts expires and
       the connection remains busy it gives FailedSendExpcetion logger error and debug and brack .

      - make catch if you delete the same protocol it gives you ConnectionAlreadyReleasedException and logger error and debug
      - make catch if no connection is available it gives you NoConnectionAvailableException and logger error and debug
      - make catch if state is not 200 it gives UnknownErrorExpcetion and logger error and debug
      - make catch if the connection is in use it gives you ConnectionInUseExpcetion and logger error and debug
      - I created a throw for some Expcetion in connection class
