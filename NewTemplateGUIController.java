import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
/**NewTemplateGUIController Class
 * Takes user inputted templates and outputs them to a file containing their templates
 * 
 * @author Zubair Waheed
 * @since 1/16/2016
 */
public class NewTemplateGUIController implements ActionListener
{
  //Requied objects
  private JTextArea template;                          //Area for question template
  private File qTemplates = new File("uTemplates.tg"); //User question template file
  private String temp;                                 //the text in the question text area
  private String aTemp;                                //the text in the answer text area
  private NewTemplateGUI nT;                           //The GUI which implements this
  private BufferedWriter qOut;                         //Used to qrite to question template file
  
  /**Constructor
    * 
    * @param templates the file the templates will be stored in
    * @param template the text area the text will obtained from
    * @param addTemplate the button used by user to tell program they are finished
    */
  public NewTemplateGUIController(JTextArea template,  NewTemplateGUI nT)
  {
    this.template = template;
    this.nT = nT;
  }

  /**Closes the gui whose component called this method
   * Obtains text from JTextArea and adds to the end of the file of user templates
   */
  public void actionPerformed(ActionEvent e)
  {
    //Obtain text
    this.temp = this.template.getText();
    
    //Output text to file
    try
    {
      //Output question
      this.qOut = new BufferedWriter(new FileWriter(qTemplates,true));//Create writer in append mode
      this.qOut.newLine();
      this.qOut.append(this.temp); 
      this.qOut.close();
      
    }catch(IOException ex)
    {
      ErrorMessages.getMessage(4);
    }
    
    //Close GUI
    this.nT.close(); 
  }
}