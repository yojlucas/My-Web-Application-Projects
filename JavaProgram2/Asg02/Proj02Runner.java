import java.net.*;
import java.io.*;
import java.util.*;

class Proj02Runner {
  
  public Proj02Runner() {
    System.out.println("Gladys Lucas");
  } // end constructor
  
   String line;
   int lineCnt = 0;
   int lineLimit = 10;
   InputStream inputStream;

  void run(String obj) {
  
    try{
      
    URL url = new URL(obj);  // Create the URL
    URLConnection urlConnection = url.openConnection(); //Make the connection
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
    while (((line = reader.readLine()) != null) && (lineCnt++ < lineLimit)) {
      System.out.println(lineCnt + line);
    } // end while loop
        
    }catch (UnknownHostException e) {
     System.out.println(e);
     System.out.println("**Not Connected**");
} // end catch
     
catch(MalformedURLException e){System.out.println(e);}
catch(IOException e){System.out.println(e);}
       
} // end main
} // end Proj02Runner

  
    
                       