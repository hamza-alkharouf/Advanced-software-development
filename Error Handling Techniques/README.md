# Error Handling Techniques


   
* The Questions:
   - Explain why the logger uses a singleton design pattern. 
 
* The Answers
   - Singleton classes are used in log file generations. Log files are created by the logger class object. Suppose an application where the logging utility has to produce one log file based on the messages received from the users. If there is multiple client application using this logging utility class they might create multiple instances of this class and it can potentially cause issues during concurrent access to the same logger file. We can use the logger utility class as a singleton and provide a global point of reference so that each user can use this utility and no 2 users access it at the same time.
