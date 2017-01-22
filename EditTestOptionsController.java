/**EditTestOptionsController
  * Created by Davin Luong
  * Last Modified 15/01/2016
  * Gathers user's input on what they would like to do next (find an old test or begin editing) */
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class EditTestOptionsController extends Object implements ActionListener 
{
  //Instance Variables
  private EditTestModel model;    //Model for the EditTest
  private JButton pressed;   //The button that was pressed
  
  /** Default constructor for the EditTestOptionsController
    * Creates an EditTestOptionsController
    * @param aModel     The model to be assigned to
    * @param anOption     The button the user pressed*/
  public EditTestOptionsController(EditTestModel aModel, JButton anOption) 
  {
    this.model = aModel;
    this.pressed = anOption;
  }
  
  /** actionPerformed method
    * Gets the text from the button that was pressed and determines what will be done next based one which button was pressed
    * @param ActionEvent e     The event sent from the button that was pressed*/
  public void actionPerformed(ActionEvent e)
  {
    String option = this.pressed.getText();   //the name of the button pressed
    if (option.equals("Find Test"))
    {
      this.model.findFile();
    }
    else if (option.equals("Begin Editing"))
    {
      this.model.begin();
    }
    else if (option.equals("Return to the MainMenu"))
    {
      this.model.back();
    }
  }
}