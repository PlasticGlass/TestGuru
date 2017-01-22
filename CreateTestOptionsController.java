/**CreateTestOptionsController
  * Created by Davin Luong
  * Last Modified 15/01/2016
  * Gathers user's input on what they would like to do next based on what button they have pressed */
import java.awt.event.*;
import javax.swing.*;
public class CreateTestOptionsController extends Object implements ActionListener
{
  //Instance Variables
  private CreateTestModel model;   //Model for the CreateTest
  private JButton pressed;   //The button that was pressed
  
  /** Default constructor for the CreateTestOptionsController
    * Creates a CreateTestOptionsController
    * @param aModel     The model to be assigned to
    * @param anOption     The button the user pressed*/
  public CreateTestOptionsController(CreateTestModel aModel, JButton anOption)
  {
    this.model = aModel;
    this.pressed = anOption;
  }
  
  /** actionPerformed method
    * Gets the text from the button that was pressed and gives it to the model which determines what will be done next
    * @param ActionEvent e     The event sent from the button that was pressed*/
  public void actionPerformed(ActionEvent e)
  {
    String option = this.pressed.getText();
    this.model.testType(option);
  }
}