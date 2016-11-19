/*File Proj05.java

This version defaults to a scope value of "2". As such:

It will ignore all hyperlinks except those
that have a document-relative path including a path to
sub-directories.



The following comments apply to the program named WebCrawler01. It has been broken into two parts and renamed Proj05/Proj05Runner
to accommodate Asg05.

Downloaded from
https://cs.nyu.edu/courses/fall02/G22.3033-008/WebCrawler.java
Modified to recursively follow, download, and display the names of hyperlinked
files according to the criteria given later. Also modified to search for
an arbitrary text string specified as a command-line parameter.
See https://helpx.adobe.com/dreamweaver/using/linking-navigation.html for a discussion of a document-relative path.

Hard coded to automatically process a maximum of 20 pages at the following URL
 "http://www.austincc.edu/baldwin/WebCrawlerSite01/ITSE2321.htm";

The robotSafe method is disabled in this version for simplicity.

The proxy code is disabled in this version for simplicity.

This version contains the ability to search for a specified string and to identify the file or files in which it appears.

Usage: From command line
    java WebCrawler01 [SearchString] [Scope]
 where:
 SearchString (optional) is the string, if any, to search for. Must be provided in order to provide scope.
 Scope (optional) is the scope in which to perform the search.

If SearchString and Scope are not provided, defaults to a hard-coded SearchString (which isn't likely to be found), and a Scope value of 1.

If SearchString is provided and Scope is not provided, defaults to a Scope value of 1.

Depending on the value of scope, this version can do one of the following:
1, Follow and process all hyperlinks to files that are linked to the root file (ITSE2321.htm) in the same directory as the root file and
in sub-directories of that directory. Ignore all other hyperlinks.
2. Ignore all hyperlinks except those that have a document-relative path, including those with a document-relative path to a sub-directory.
3. Ignore all hyperlinks except those that have a document-relative path, ignoring those with a document-relative path to a sub-directory.

Any other Scope value, including none, defaults to a Scope value of 1.

At the end of the run, the program prints out the number of files that it
found that meet the criteria given above.

The search for a text string is case sensitive. Search strings containing spaces must be enclosed in double quotes.
To simplify the search, the text string must all appear on a single line
of the raw html. It cannot be broken on two or more lines due to whitespace
issues at the end of the line.

When run with no command-line parameters, it will search for the word Java by default.

Tested under Win 7 and JDK 1.8.0_40
*******************************************************************************/


public class Proj05 {
public static void main(String[] argv){
  Proj05Runner runner = new Proj05Runner();
  runner.run(argv);
}//end main method
//==================================================================================//

}//end WebCrawler01
