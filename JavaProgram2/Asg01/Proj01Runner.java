import java.net.*;

class Proj01Runner {
  
  public Proj01Runner() {
     System.out.println("Gladys Joy Lucas");    
  } 
  
  void run() {
    
    try {    
 
  System.out.println("Get and display IP address of ACC website by name");   
  InetAddress address = InetAddress.getByName(("www.austincc.edu"));
  System.out.println(address);
  
  System.out.println ("Get and display current IP address of LocalHost");
  address = InetAddress.getLocalHost();
  System.out.println(address);
  
  System.out.println("Get and display current name of LocalHost");
  System.out.println(address.getHostName());
  
  System.out.println("Get and display current IP address of LocalHost");  
 //Get IP address as an array of bytes.
  byte[] bytes = address.getAddress();
  
  //Convert IP address bytes to unsigned values and display separated by spaces. 
  for (int cnt = 0; cnt < bytes.length; cnt++) {
    int uByte = bytes [cnt] < 0 ? bytes [cnt] + 256 : bytes [cnt];
    System.out.print (uByte + " " );
  } //end loop  
  System.out.println();
  
    } // end try

    catch (UnknownHostException e) {
      System.out.println(e);
    } //end catch
  }//end main
}//end class Proj01Runner
  
                                                  
  