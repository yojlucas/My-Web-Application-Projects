import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.text.html.*;
import java.net.*;
import java.awt.*;
import java.util.*;

class Proj03Runner {
  
  void run(String obj) {
    
    try {
      URL website = new URL (obj); // Creating a new URL object from the String.
     new Proj03RunnerHTMLHandler(website); //Instantiate an overall web page handler
   
    } catch (Exception e) {
      e.printStackTrace();
    } // end catch
  }//end run
} // end class Proj03Runner

//========================Beginning of the class HTMLHandler=============================//
class Proj03RunnerHTMLHandler extends JFrame implements HyperlinkListener {
  JEditorPane displayEditorPane;
  JButton backButton;
  JTextField text;
  JButton forwardButton;
  JPanel panel;
  
  java.util.List<URL>history;
  int currentHistoryPage = 0;
  
 
  public Proj03RunnerHTMLHandler (URL website) { //constructor
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setTitle("Gladys Joy Lucas"); 
  System.out.println("Gladys Joy Lucas");
  
  try {
    if(website != null) {
      history = new ArrayList<URL>(); //history list
      
//Create a JEditorPane containing the webpage
      displayEditorPane = new JEditorPane(website);
      displayEditorPane.setEditable(false);
      displayEditorPane.addHyperlinkListener(this);
 
//Add scrollbars      
      JScrollPane scroll = new JScrollPane();
      JViewport vp = scroll.getViewport();
      vp.add(displayEditorPane);
      this.getContentPane().add(scroll,BorderLayout.CENTER);

//Set the size and visibility of the JFrame
      this.setSize(669,669);
      this.setVisible(true);
     
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    
    backButton = new JButton("Back");     
    text = new JTextField(website.toString()); 
    forwardButton = new JButton("Forward");
    
    history.add(website);
   
    panel.add(backButton, "North");
    panel.add(text, "Center");
    panel.add(forwardButton, "South");
      
//add panel to the Top Jframe
    this.getContentPane().add(panel, "North"); 
    
    //Add ActionListener to JField    
    text.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent ae) {
        openURL(ae.getActionCommand(), true);
      } 
    });
    
//Add ActionListener to backButton
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(currentHistoryPage > 0)
          openURL(history.get(--currentHistoryPage).toString(), false);
       }
     });

//Add ActionListener to forwardButton
    forwardButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if(currentHistoryPage < history.size() -1)
          openURL(history.get(++currentHistoryPage).toString(), false);
      }
    });  
    
        } // end if
       }catch(Exception e){
      e.printStackTrace();
    }//end catch 
  }//end constructor
  
//================HYPERLINK EVENTS START HERE====================================  
public void hyperlinkUpdate (HyperlinkEvent e) {
  HyperlinkEvent.EventType type = e.getEventType();
    if (type == HyperlinkEvent.EventType.ACTIVATED)
    openURL(e.getURL().toExternalForm(), true);
    }
    
void openURL(String urlString, boolean addToHistory) {
  try{
    URL url = new URL(urlString);
    displayEditorPane.setPage(url);
    //text.setText(url.toExternalForm());  //will cause the textfiel to follow url links
    
    if(addToHistory){
      history.add(url);
      currentHistoryPage = history.size() -1;
    } //end if
  } catch (Exception e) {
    System.out.println();
  }//end catch
} //end void openURL
}//end HTMLHandler class===========================end class Proj03Runner============