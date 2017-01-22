/**DeleteQController
  * Created by Davin Luong
  * Last Modified 16/01/2016
  * Gathers user's input on what they would like to do next (import a question or save changes to a question) */
import java.awt.event.*;
import javax.swing.*;
public class DeleteQController extends Object implements ActionListener
{
  //Instance Variables
  private DeleteQModel model;   //Model for the DeleteQ
  private JButton pressed;   //The button that was pressed
  
  /** Default constructor for the DeleteQController
    * Creates a DeleteQController
    * @param aModel     The model to be assigned to
    * @param anOption     The button the user pressed*/
  public DeleteQController(DeleteQModel aModel, JButton anOption)
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
    if(option.equals("View"))
    {
      this.model.displayQ();   //Display the desired question
    }
    else if(option.equals("Delete Question"))
    {
      this.model.delete();   //Remove a question
    }
  }
}