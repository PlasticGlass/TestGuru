import javax.swing.*;
import java.awt.event.*;
/** The QuestionGeneratorController Class
  * 
  * This class is implemented in a QuestionGenerator GUI where it obtains which unit was selected and 
  * sends it to a QuestionGenerator, obtains a question, and adds it to the short answer model
  * 
  * Last Modified: 1/10/2015
  * @author Zubair Waheed
  * */
public class QuestionGeneratorController implements ActionListener
{
  shortAnswerModel test;            //Model question will be added to
  QuestionGenerator questionGetter; //Used to generate question using selected unit
  JList units;                      //A list containing all possible ser selectable units
  int type;                         //The type of question this will return (short answer vs Mult Choice)
  QuestionGeneratorGUI GUI;         //GUI this is a part of
  CreateTestGUI cT;                 //Main program GUI to update when question obtained
  int unit;                         //The unit the question will be based on
  Question qToAdd;                  //The generated question which will be added to the test
  
  /** Constructor
    * Sets arguments as required objects and creates a QuestionGenerator
    * 
    * @param test The model the generated question will be added to
    * @param units The list of all the units
    * @param qType The type of question this will generate (1/2 - short answer/multiple choice)
    * @param gui The Gui this is implemented in
    * @param ctGUI The main program GUI which wil update when a question is added to the model
    */
  public QuestionGeneratorController(shortAnswerModel test, JList units, int qType, QuestionGeneratorGUI gui, CreateTestGUI ctGUI)
  {
    this.questionGetter = new QuestionGenerator(); 
    this.test = test;
    this.units = units;
    this.type = qType;
    this.GUI = gui;
    this.cT = ctGUI;
  }
  
  /**Obtains selected unit(its index in the list), adds it to test, and closes GUI
    * @param e The event that triggers this method*/
  public void actionPerformed(ActionEvent e)
  {
    //get the text entered
    this.unit = this.units.getSelectedIndex();
    
    //If nothing selected
    if(this.unit == -1)
      ErrorMessages.getMessage(3);
    
    //Add question to test, update main program gui, and close window
    else
    {
      this.createAndAddQuestion();
      this.cT.updateTestDisplay();
      this.GUI.closeFrame();
    }
  }
  
  /**Obtains a random question from the question generator based on the unit selected and add it to the test*/
  public void createAndAddQuestion()
  {
    //If this controller is part of the short answer part of the program
    if(this.type == 1)
      this.qToAdd = questionGetter.getQuestion(this.unit, 1); //Short Answer
    else
      this.qToAdd = questionGetter.getQuestion(this.unit, 2); //Multiple Choice
    
    this.test.createQuestion(this.qToAdd);
  }
}