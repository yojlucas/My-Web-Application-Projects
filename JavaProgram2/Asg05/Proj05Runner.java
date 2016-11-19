import java.io.*;
import java.net.*;
import java.util.*;

class Proj05Runner {
  
  public Proj05Runner() {
    System.out.println("Gladys Joy Lucas");
  }
  public static final int SEARCH_LIMIT = 20;
  public static final boolean DEBUG = false;
  public static int MAXSIZE = 2000;
  
  Vector newUrls;
  Hashtable knownUrls;
  int maxPages;
  
  int length = "http://www.austincc.edu/baldwin/WebCrawlerSite01/".length();
  String searchString = "Java";
  String scope = "2";
  String rootURL = "http://www.austincc.edu/baldwin/WebCrawlerSite01/ITSE2321.htm";
  
//=================================initialize method================================
  public void initialize(String[] argv) {
    URL url;
    this.knownUrls = new Hashtable();
    this.newUrls = new Vector();
    
    if(argv.length != 0) {
      if(argv.length == 1) {
        this.searchString = argv[0];
        this.scope = "2";
      }else if(argv.length == 2) {
        this.searchString = argv[0];
        this.scope = argv[1];
        if(!(this.scope.equals("1") || this.scope.equals("2") || this.scope.equals("3"))) {
          this.scope = "1";
        }
      }
    }
    try {
      url = new URL(this.rootURL);
    }catch (MalformedURLException m) {
      System.out.println("Invalid starting URL " + this.rootURL);
      return;
    }//end catch
    this.knownUrls.put(url, new Integer(1));
    this.newUrls.addElement(url);
    System.out.println("Starting search: Initial URL:\n" + url.toString());
    this.maxPages = 20;
    System.out.println("Maximum number of pages: " + this.maxPages);
  }//end initialize method==============================================================
  
  public void addNewUrl(URL url, String string) {
    
    try{
      int in;
      String strings;
      URL urls = new URL(url, string);
      if(!(this.knownUrls.containsKey(urls) || (in = (strings = urls.getFile())
             .lastIndexOf("htm"))!= strings.length() - 3 && in != strings.length() - 4)) {
        this.knownUrls.put(urls, new Integer(1));
        this.newUrls.addElement(urls);
      }
    }catch(MalformedURLException ma) {
      return;
    }
  }//end addNewUrl method======================================================================
  
  public String getPage(URL url) {
    
    try {
      URLConnection urlConnection = url.openConnection();
      int n = url.toString().length();
      System.out.println("Searching: " + url.toString().substring(this.length, n));
      urlConnection.setAllowUserInteraction(false);
      InputStream inputStream = url.openStream();
      
      byte[] array = new byte[1000];
      int n2 = inputStream.read(array);
      String string = new String(array, 0, n2);
      
      while (n2 != -1 && string.length() < 20000) {
        n2 = inputStream.read(array);
        if (n2 == -1) continue;
        String strings = new String(array, 0, n2);
        string = string + strings;
      }//end while
      return string;
    }catch (IOException e) {
      System.out.println("ERROR: couldn't open URL " + url.toString());
      return "";
    }
  }//end getPage method===============================================================
  
  public void processPage (URL url, String string) {
    String strings = string.toLowerCase();
    int n = 0;
    while ((n = strings.indexOf("<a", n)) != -1) {
      int n2;
      int n3 = strings.indexOf(">", n);
      int n4 = string.indexOf("href", n);
      if(n4 != -1 && (n2 = strings.indexOf("\"", n4) + 1) != -1 && n3 != -1 && n2 < n3){
        int n5 = strings.indexOf("\"",n2);
        int n6 = strings.indexOf("#", n2);
        if(n5 != -1 && n5 < n3) {
          int n7 = n5;
          if(n6 != -1 && n6 < n5) {
            n7 = n6;
          }
          String string3 = string.substring(n2, n7);
          if (this.scope.equals("1")) {
            if (!string3.toUpperCase().startsWith("HTTP") ||
                  string3.toUpperCase().startsWith("HTTP://WWW.AUSTINCC.EDU/BALDWIN/WEBCRAWLERSITE01/")) {
              this.addNewUrl(url, string3);
            }
          }else if(this.scope.equals("2")) {
            if(!string3.toUpperCase().startsWith("HTTP")) {
              this.addNewUrl(url, string3);
            }
            }else if(!(!this.scope.equals("3") || string3.toUpperCase().startsWith("HTTP") 
                       || string3.contains((CharSequence)"/"))) {
              this.addNewUrl(url, string3);             
            }
            }
}//end while
n = n3;
}
}//end processPage method=================================================================================
  public void searchPage(String string, URL url, String strings) {
    boolean bol = false;
    if(strings.indexOf(string) != -1) {
      int n = url.toString().length();
      System.out.println("Found search string: \"" + this.searchString + "\"\n");
    }
  }//end searchPage========================================================================================
  public void run(String[] argv) {

    this.initialize(argv);
    for(int i = 0; i < this.maxPages; ++i) {
      URL url = (URL)this.newUrls.elementAt(0);
      this.newUrls.removeElementAt(0);
      
      String string = this.getPage(url);
      if(string.length() != 0) {
        this.processPage(url, string);
      }
      if (string.length() != 0) {
        this.searchPage(this.searchString, url, string);
      }
      if(this.newUrls.isEmpty())
        break;
    }//end for
    System.out.println();
    System.out.println("Search complete.");
    System.out.println("" + this.knownUrls.size() + " files were found");
  }//end run method==========================================================================================
  }


                  
    
          
        
      
        
        
         
    