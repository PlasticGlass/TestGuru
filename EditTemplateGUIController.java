import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
/**EditTemplateGUIController Class
 * Takes user selected template and all their inputted text and adds it to the test.
 * Generates random numbers and fills template with them if they want that instead.
 * 
 * @author Zubair Waheed
 * @since 1/16/2016
 */
public class EditTemplateGUIController implements ActionListener
{
  //Requied objects
  private JTextArea template;            //The text area containing the users template
  private String temp;                   //the template text 
  private EditTemplateGUI eT;            //The GUI this is implemented in
  private CreateTestGUI cT;              //The main program GUI which will update when the user finishes editing their test
  private shortAnswerModel model;        //The model which contains the test this will be added to
  private StringTokenizer templateSplit; //Used to split template into tokens which can be used or replaced in the final string
  private StringBuffer filledTemplate;   //Used to create the template by appending random numbers or the users numbers
  private Random gen;                    //Used to generate random numbers
  private String next;                   //The next token in the string

  /**Constructor
    * 
    * @param template the text area the text will obtained from
    * @param model the model this question will be added to
    * @param eT the gui which uses this controller 
    * @param cT the main program GUI which will update when a question is added
    */
  public EditTemplateGUIController(JTextArea template, shortAnswerModel model, EditTemplateGUI eT, CreateTestGUI cT)
  {
    this.template = template;
    this.eT = eT;
    this.model = model;
    this.cT = cT;
    this.gen = new Random();
    this.filledTemplate = new StringBuffer(" ");
  }

  /**Closes the gui whose component called this method
   * Obtains text from JTextArea and adds to the end of the file of user templates
   * @param e The event that triggers this method*/
  public void actionPerformed(ActionEvent e)
  {
    //Obtain text
    this.temp = this.template.getText();
    
    if(e.getActionCommand().equals("Fill With Random Values"))
      this.temp = this.fillWithRandValues(this.temp);
    
    this.model.createQuestion(new Question(this.temp, " ", 1));
    this.cT.updateTestDisplay();
    this.eT.close(); 
  }
  
  /**Fills the template with random value based on where ever it finds 2 underscores
    *@param tempToFill the string which will be filled with random values 
    */
  private String fillWithRandValues(String tempToFill)
  {
      this.templateSplit = new StringTokenizer(tempToFill);
    
      //While the template has more tokens
      while(templateSplit.hasMoreTokens())
      {
        next = templateSplit.nextToken();
        
        //If a part requiring a rand value reached, concat that instead of the next token
        if(next.equals("__"))
          this.filledTemplate.append(Double.toString(Math.round(gen.nextDouble() * 100/0.01)*0.01) + " ");      
        else 
          this.filledTemplate.append(next+ " ");
      }
      return this.filledTemplate.toString();
  }
}