import java.io.*;
import java.net.*;
import java.util.*;

class Proj08Runner {
  
  public Proj08Runner() {
    System.out.println("Gladys Joy Lucas");
  }//constructor
  
  public static final int search_limit = 20;
  public static final boolean debug = false;
  public static final int maxsize = 20000;
  
  Vector newUrls;
  Hashtable knownUrls;
  int maxPages;
  
  int length = "http://www.austincc.edu/baldwin/WebCrawlerSite01/".length();
  String searchString = "Java";
 String scope;
  String rootUrl = "http://www.austincc.edu/baldwin/WebCrawlerSite01/ITSE2321.htm";
//=========================================================================================
    public void initialize(String[] argv){
      URL url;
      this.knownUrls = new Hashtable();
      this.newUrls = new Vector();
      
      if(argv.length != 0){
        if(argv.length ==1){
        this.searchString = argv[0];
        this.scope = argv[0];
      }else if(argv.length == 2){
          this.searchString = argv[0];
          this.scope = argv[1];
          if(!(this.scope.equals("1") || this.scope.equals("2") || this.scope.equals("3"))) {
          this.scope = argv[0];
        }
          
          }
         }//end if
//}//end else
      try {
        url = new URL(this.rootUrl);
      }catch (MalformedURLException m){
        System.out.println("Invalid starting URL " + this.rootUrl);
        return;
      }//end catch
      this.knownUrls.put(url, new Integer(1));
      this.newUrls.addElement(url);
      System.out.println("Starting search: Initial URL: \n" + url.toString());
      
      this.maxPages = 20;
      System.out.println("Maximum number of pages: " + maxPages);
      
}//end initialize method===========================================================    
    public void addNewUrl(URL url, String string){      
    try { 
    int in;
    String strings;
    URL urls = new URL(url, string);
    if(!(this.knownUrls.containsKey(urls) || (in = (strings = urls.getFile())
                                                 .lastIndexOf("htm")) != strings.length() -3)){
      this.knownUrls.put(urls,new Integer(1));
      this.newUrls.addElement(urls);
    }
       }catch(MalformedURLException ma){
       return;
       }
    }
//=========================================================================================
    public String getPage(URL url){
      try {
        URLConnection urlConnection = url.openConnection();
        
         int n = url.toString().length();
        System.out.println("Searching: " + url.toString().substring(this.length));
        
        urlConnection.setAllowUserInteraction(false);
        InputStream inputStream = url.openStream();
        
        byte[] array = new byte[1000];
        int n2 = inputStream.read(array);
        String string = new String(array, 0, n2);
        
        while(n2 != -1 && string.length() < maxsize){
        n2 = inputStream.read(array);
        if (n2 == -1) 
        break; // terminate the downloading so it wont download the whole thing.
        
        String strings = new String(array, 0, n2);
        string = string + strings;        
        }//end while loop
        return string;
        
      }catch (IOException e) {
        System.out.println("Error: couldn't open URL " + url.toString());
        return "";
      }//end catch
    }//end getPage method==================================================================
    
    public void processPage(URL url, String string){
    String strings = string.toLowerCase();
    
    int n = 0;
    while((n = strings.indexOf("<a", n)) != -1){
      int n2;
      int n3 = strings.indexOf(">", n);
      int n4 = string.indexOf("href", n);
      
      if(n4 != -1 && (n2 = strings.indexOf("\"", n4) + 1) != -1 && n3 != -1 && n2 < n3){
        int n5 = strings.indexOf("\"",n2);
        int n6 = strings.indexOf("#",n2);
        
        if(n5 != -1 && n5 < n3) {
          int n7 = n5;
          if(n6 != -1 && n6 < n5) {
            n7 = n6;
          }
          String string3 = string.substring(n2, n7);
          if(this.scope.equals("1")){
            if(!string3.toUpperCase().startsWith("HTTP")
                 ||string3.toUpperCase().startsWith("HTTP://WWW.AUSTINCC.EDU/BALDWIN/WEBCRAWLERSITE01/")) {
              this.addNewUrl(url, string3);
            }
          }else if(this.scope.equals("2")){
            if(!string3.toUpperCase().startsWith("HTTP")){
              this.addNewUrl(url, string3);
            }
          }else if(!(!this.scope.equals("3") || string3.toUpperCase().startsWith("HTTP")
                    || string3.contains((CharSequence)"/"))){
            this.addNewUrl(url, string3);
          }
          }
        }
       n = n3;
      }
     
    }//end while
   // }//end ProcessPage==============================================================================                       
    public void searchPage(String string, URL url, String strings){
    boolean bol = false;
    if(strings.indexOf(string) != -1) {
     
   
 int n = url.toString().length();
      System.out.println("Found search string: \"" + this.searchString + "\"\n"); 
      
    }
    }//end searchPage method=======================================================================
    public void run(String[] argv) {
      this.initialize(argv);
      for (int i = 0; i < this.maxPages; i++) {
        URL url = (URL)this.newUrls.elementAt(0);
        this.newUrls.removeElementAt(0);
       
       
        String string = this.getPage(url);
        if(string.length() !=0 ) {
          this.processPage(url, string);//this is to call the processpage method.
        }
        if(string.length() != 0) {//display the Found Search sting: in the searhpage method
          this.searchPage(this.searchString, url, string);
        }
        if(this.newUrls.isEmpty())
          break;
      }//end for loop
      System.out.println();
      System.out.println("Search complete.");
      System.out.println(this.knownUrls.size() + " files were found");
    }//end run method     
}//end Proj08Runner class==================================================================