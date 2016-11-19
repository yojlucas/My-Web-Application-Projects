 import java.net.*;
import java.io.*;
import java.util.*;

class Proj04Runner {
  
  String server = "www.austincc.edu";
  boolean closedFlag;
  SocketWrapper socketWrapper;
 
  public Proj04Runner() {
    System.out.println("Gladys Joy Lucas");
  }   
  void run(String server){
   socketWrapper = getSocket(server);
   socketWrapper.outputStream.println("GET / HTTP/1.1");
   socketWrapper.outputStream.println("Host: " + server);
   socketWrapper.outputStream.println("Connection: keep-alive");
   
   socketWrapper.outputStream.println();
   closedFlag = print(socketWrapper.inputStream);
       
    try{
      //Close the socket
      socketWrapper.socket.close();
    }catch(Exception e){
      e.printStackTrace();
    }//end catch
  }//end doIt
//===============================================================================
  boolean print(BufferedReader inputStream) {
    int lineCnt = 0;
    String line = null;
    int lineLimit = 22;
    boolean closedFlag = false;
    try {
      while (((line = inputStream.readLine()) != null) && (lineCnt++ < lineLimit)) {
        System.out.println(lineCnt + ": " + line);       
      }// end while
       System.out.println("**Print terminated on line count.**");
       
    }catch (Exception e) {
      e.printStackTrace();
    } //end catch
    
    return closedFlag;
  }// end print method
 //=============================================================================
  class SocketWrapper {
    Socket socket;
    PrintStream outputStream;
    BufferedReader inputStream;
  }// end SocketWrapper class
  
  SocketWrapper getSocket (String server) {
    int port = 80;
    SocketWrapper socketWrapper = new SocketWrapper();
    try {
      socketWrapper.socket = new Socket(server, port);
      socketWrapper.inputStream = new BufferedReader(new InputStreamReader(
                                  socketWrapper.socket.getInputStream()));
      socketWrapper.outputStream = new PrintStream(socketWrapper.socket.
                                                     getOutputStream(), true);
      
    }catch(Exception e){
      e.printStackTrace();
    }//end catch
    
    return socketWrapper;
  }//end getSocket
  
}//end Proj04Runner class
//=====================================END OF THE PROGRAM=============================  
    
  
    
    
    