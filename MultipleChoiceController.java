import javax.swing.*;
import java.awt.event.*;
/** MultipleChoiceController class
  * The controller for adding the inputs from the user
  * LastModified:  21/01/2016
  * @author  K.Damodar
  */ 

public class MultipleChoiceController implements ActionListener
{
  private MultipleChoiceModel model;
  private JTextField question;
  private JTextField multipleChoice1;
  private JTextField multipleChoice2;
  private JTextField multipleChoice3;
  private JTextField multipleChoice4;
  private JButton pressed;
  
  /**Constructor
   * Links the component to the Model
   *@param model             Current model
   *@param question          Question to be added
   *@param multipleChoice1   Multiple Choice to be added
   *@param multipleChoice2   Multiple Choice to be added
   *@param multipleChoice3   Multiple Choice to be added
   *@param multipleChoice4   Multiple Choice to be added
   *@param aButton           Add button
   */
  
  public MultipleChoiceController(MultipleChoiceModel model, JTextField question, JTextField multipleChoice1, JTextField multipleChoice2, JTextField multipleChoice3, JTextField multipleChoice4, JButton aButton)
  {
   this.model = model;
   this.question = question;
   this.multipleChoice1 = multipleChoice1;
   this.multipleChoice2 = multipleChoice2;
   this.multipleChoice3 = multipleChoice3;
   this.multipleChoice4 = multipleChoice4;    
   this.pressed = aButton;
  }
  
  /** Adds the questions and multiple choice to the test page
   * @param e   The event sent from the button that was clicked
   * 
   */ 
  public void actionPerformed(ActionEvent e)
  {
    if (pressed.getText().equals("Add"))
    {
      //Variable declaration
      String quest;     //Questions
      String mC1;       //First Multiple Choice
      String mC2;       //Second Multiple Choice
      String mC3;       //Third Multiple Choice
      String mC4;       //Fourth Multiple Choice
      
      //get question and multiple choices
      quest = this.question.getText();
      this.model.setQuestion(quest);
      
      mC1 = this.multipleChoice1.getText();
      this.model.setMC1(mC1);
      
      mC2 = this.multipleChoice2.getText();
      this.model.setMC2(mC2);
      
      mC3 = this.multipleChoice3.getText();
      this.model.setMC3(mC3);
      
      mC4 = this.multipleChoice4.getText();
      this.model.setMC4(mC4);
      
      this.model.giveQuestion();
    }
   
    
  }
  
}