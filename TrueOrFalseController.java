/**Created by Karan
  * Modified by Davin
  * Last modified on 21/01/2016
  * Controller for the TrueOrFalse class */

import javax.swing.*;
import java.awt.event.*;


public class TrueOrFalseController implements ActionListener
{
  private TrueOrFalseModel model;
  private JTextField question;
  private JButton pressed;
  
  
  public TrueOrFalseController(TrueOrFalseModel model, JTextField question, JButton aButton)
  {
   this.model = model;
   this.question = question;
   this.pressed = aButton;
  }
  
  public void actionPerformed(ActionEvent e)
  {
   
    if(pressed.getText().equals("Add"))
    {
     String quest; 
     quest = this.question.getText();
     this.model.setQuestion(quest);
     
     this.model.giveQuestion();
    }
  }
  
}