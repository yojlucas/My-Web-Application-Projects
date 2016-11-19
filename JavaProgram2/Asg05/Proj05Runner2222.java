/*
 * Decompiled with CFR 0_102.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.Vector;

public class Proj05Runner {
    public static final int SEARCH_LIMIT = 20;
    public static final boolean DEBUG = false;
    public static final int MAXSIZE = 20000;
    Vector newURLs;
    Hashtable knownURLs;
    int maxPages;
    int lenA = "http://www.austincc.edu/baldwin/WebCrawlerSite01/".length();
    String searchString = "Java";
    String scope = "1";
    String rootFile = "http://www.austincc.edu/baldwin/WebCrawlerSite01/ITSE2321.htm";

    public void initialize(String[] arrstring) {
        URL uRL;
        this.knownURLs = new Hashtable();
        this.newURLs = new Vector();
        if (arrstring.length != 0) {
            if (arrstring.length == 1) {
                this.searchString = arrstring[0];
                this.scope = "2";
            } else if (arrstring.length == 2) {
                this.searchString = arrstring[0];
                this.scope = arrstring[1];
                if (!(this.scope.equals("1") || this.scope.equals("2") || this.scope.equals("3"))) {
                    this.scope = "1";
                }
            }
        }
        try {
            uRL = new URL(this.rootFile);
        }
        catch (MalformedURLException var3_3) {
            System.out.println("A: Invalid starting URL " + this.rootFile);
            return;
        }
        this.knownURLs.put(uRL, new Integer(1));
        this.newURLs.addElement(uRL);
        System.out.println("Starting search: Initial URL:\n" + uRL.toString());
        this.maxPages = 20;
        System.out.println("Maximum number of pages: " + this.maxPages);
    }

    public void addnewurl(URL uRL, String string) {
        try {
            int n;
            String string2;
            URL uRL2 = new URL(uRL, string);
            if (!(this.knownURLs.containsKey(uRL2) || (n = (string2 = uRL2.getFile()).lastIndexOf("htm")) != string2.length() - 3 && n != string2.length() - 4)) {
                this.knownURLs.put(uRL2, new Integer(1));
                this.newURLs.addElement(uRL2);
            }
        }
        catch (MalformedURLException var4_5) {
            return;
        }
    }

    public String getpage(URL uRL) {
        try {
            URLConnection uRLConnection = uRL.openConnection();
            int n = uRL.toString().length();
            System.out.println("Searching: " + uRL.toString().substring(this.lenA, n));
            uRLConnection.setAllowUserInteraction(false);
            InputStream inputStream = uRL.openStream();
            byte[] arrby = new byte[1000];
            int n2 = inputStream.read(arrby);
            String string = new String(arrby, 0, n2);
            while (n2 != -1 && string.length() < 20000) {
                n2 = inputStream.read(arrby);
                if (n2 == -1) continue;
                String string2 = new String(arrby, 0, n2);
                string = string + string2;
            }
            return string;
        }
        catch (IOException var2_3) {
            System.out.println("I: ERROR: couldn't open URL " + uRL.toString());
            return "";
        }
    }

    public void processpage(URL uRL, String string) {
        String string2 = string.toLowerCase();
        int n = 0;
        while ((n = string2.indexOf("<a", n)) != -1) {
            int n2;
            int n3 = string2.indexOf(">", n);
            int n4 = string2.indexOf("href", n);
            if (n4 != -1 && (n2 = string2.indexOf("\"", n4) + 1) != -1 && n3 != -1 && n2 < n3) {
                int n5 = string2.indexOf("\"", n2);
                int n6 = string2.indexOf("#", n2);
                if (n5 != -1 && n5 < n3) {
                    int n7 = n5;
                    if (n6 != -1 && n6 < n5) {
                        n7 = n6;
                    }
                    String string3 = string.substring(n2, n7);
                    if (this.scope.equals("1")) {
                        if (!string3.toUpperCase().startsWith("HTTP") || string3.toUpperCase().startsWith("HTTP://WWW.AUSTINCC.EDU/BALDWIN/WEBCRAWLERSITE01/")) {
                            this.addnewurl(uRL, string3);
                        }
                    } else if (this.scope.equals("2")) {
                        if (!string3.toUpperCase().startsWith("HTTP")) {
                            this.addnewurl(uRL, string3);
                        }
                    } else if (!(!this.scope.equals("3") || string3.toUpperCase().startsWith("HTTP") || string3.contains((CharSequence)"/"))) {
                        this.addnewurl(uRL, string3);
                    }
                }
            }
            n = n3;
        }
    }

    public void searchPage(String string, URL uRL, String string2) {
        boolean bl = false;
        if (string2.indexOf(string) != -1) {
            int n = uRL.toString().length();
            System.out.println("Found search string: \"" + this.searchString + "\"\n");
        }
    }

    public void run(String[] arrstring) {
        System.out.println("Display your name here.");
        this.initialize(arrstring);
        for (int i = 0; i < this.maxPages; ++i) {
            URL uRL = (URL)this.newURLs.elementAt(0);
            this.newURLs.removeElementAt(0);
            String string = this.getpage(uRL);
            if (string.length() != 0) {
                this.processpage(uRL, string);
            }
            if (string.length() != 0) {
                this.searchPage(this.searchString, uRL, string);
            }
            if (this.newURLs.isEmpty()) break;
        }
        System.out.println();
        System.out.println("Search complete.");
        System.out.println("" + this.knownURLs.size() + " files were found");
    }
}