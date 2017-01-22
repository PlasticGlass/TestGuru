import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;
/** The ErrorMessages Class
  * 
  * This class contains a singular static method which, depending on the error value inputted, will create
  * a JFrame to help ensure data validation for further uer input
  * 
  * Last Modified: 1/19/2016
  * @author Zubair Waheed
  * */
public class ErrorMessages 
{
  /** getMessage method
    * creates a JFrame wchich contains details about an error which occured in the program
    * Contains a button to close the window when the user has read the message
    * 
    * @param type used to identify which error occured so the corresponding/relevant messages can be displayed
    */
  public static void getMessage(int type)
  {
    final JFrame errorW = new JFrame("ERROR"); //The Frame
    JPanel contents = new JPanel();            //The main JPanel containing all the labels
    JLabel top = new JLabel("");               //The top portion of the error message
    JLabel bottom = new JLabel("");            //The bottom portion of the error message
    JButton ok = new JButton("OK");            //A button which the user can press to close the window
    
    contents.setLayout(new BorderLayout());//Set layout of main panel
    
    //Choose which message to display based on inputted error value
    switch(type)
    {
      case 1:
        top = new JLabel("The file countaining templates for the questions was not found!");
        bottom = new JLabel("You cant use this functionality until it exits (\"qTemplates.tg\")");
        break;
      case 2:
        top = new JLabel("The file countaining templates for solutions was not found!");
        bottom = new JLabel("You cant use this functionality until it exits (\"aTemplates.tg\")");
        break;
      case 3:
        top = new JLabel("You have not selected a unit!");
        bottom = new JLabel("Please select one and try again or close this window.");
        break;
      case 4:
        top = new JLabel("The file could not be accessed or created for some reason.");
        bottom = new JLabel("Try again or close the window.");
        break;
      case 5:
        top = new JLabel("The question number you entered is invalid.");
        bottom = new JLabel("Please try again.");
        break;
      case 6:
        top = new JLabel("That question doesnt exist.");
        break;
      case 7:
        top = new JLabel("You havent selected a template!");
        bottom = new JLabel("Please select one and try again.");
        break;
      case 8:
        top = new JLabel("You havent given your test a title!");
        bottom = new JLabel("Please input one and try again.");
        break;
        
    }
    
    //Add the messages to the frame
    contents.add(top, BorderLayout.NORTH);
    contents.add(bottom, BorderLayout.CENTER);
    contents.add(ok, BorderLayout.EAST);
    
    //Create Anonymous ActionListener class only to be used to close the JFrame through the "ok" button
    ok.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        errorW.dispose();
      }
    });
    
    //Initialize the frame
    errorW.setContentPane(contents);
    errorW.pack();
    errorW.setLocationRelativeTo(null);
    errorW.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    errorW.setVisible(true);
  }
}