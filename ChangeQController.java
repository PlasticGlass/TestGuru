/**ChangeQController
  * Created by Davin Luong
  * Last Modified 16/01/2016
  * Gathers user's input on what they would like to do next (import a question or save changes to a question) */
import java.awt.event.*;
import javax.swing.*;
public class ChangeQController extends Object implements ActionListener
{
  //Instance Variables
  private ChangeQModel model;   //Model for the ChangeQ
  private JButton pressed;   //The button that was pressed
  
  /** Default constructor for the ChangeQController
    * Creates a ChangeQController
    * @param aModel     The model to be assigned to
    * @param anOption     The button the user pressed*/
  public ChangeQController(ChangeQModel aModel, JButton anOption)
  {
    this.model = aModel;
    this.pressed = anOption;
  }
  
  /** actionPerformed method
    * Gets the text from the button that was pressed and determines what will be done next
    * @param ActionEvent e     The event sent from the button that was pressed*/
  public void actionPerformed(ActionEvent e)
  {
    String option = this.pressed.getText();
    if(option.equals("Begin"))
    {
      this.model.displayQ();   //Display the desired question
      this.model.displayA();   //Display the desired question's answer
    }
    else if(option.equals("Save Changes"))
    {
      this.model.saveChanges();   //Replace old question and answer with new
    }
  }
}