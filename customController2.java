import javax.swing.*;
import java.awt.event.*;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.util.*;

/** custom controller class
*  connects the shortAnswerModel with the shortAnswerGUI
*  Last Modified:  19/01/2016
*  @author M.Chen
*/
public class customController2 implements ActionListener
{
  private shortAnswerModel model = new shortAnswerModel(); //the short answer model
  private JTextArea question;                 //text area for question
  private JTextArea answer;                   //text area for answer
  private JButton button;                     //button that is pressed
  private ButtonGroup group;                  //button group for options of short answers: custom, template or random
  private ButtonGroup group2;                 //button group for options of custom or generated solution
  private QuestionGeneratorGUI g;           //Random question generator
  private shortAnswerGUI s;                   //the short answer GUI
  private CreateTestGUI cT;                 //Main program GUI
  private customSolutionClass solution = new customSolutionClass(); //class for solving questions
  
  /** Default constructor
    * Links the component to the Model
    * @param model              The current shortAnswerModel model
    * @param add;               The button pressed
    * @param questionField;     The question recieved
    * @param answerField;       The answer recieved
    * @param optionGroup;       The button group for options of short answers: custom, template or random
    * @param solveGroup;        The button group for options of custom or create solution
    */ 
  public customController2(shortAnswerModel model, JButton add, JTextArea questionField, JTextArea answerField, ButtonGroup optionGroup, ButtonGroup solveGroup, shortAnswerGUI gui, CreateTestGUI ctGUI)
  {
    this.model = model;
    this.question = questionField;
    this.answer = answerField;
    this.button = add;
    this.group = optionGroup;
    this.group2 = solveGroup;
    this.s = gui;
    this.cT = ctGUI;
  }
  
  /** Performs the requested action of adding question to test
    * @param e      The event sent from the button that was clicked
    */ 
  public void actionPerformed(ActionEvent e)
  {
    String option = getSelectedButtonText(this.group); //sees which option of short answer is chosen
    String yesNo = getSelectedButtonText(this.group2); //sees if user wants generated solution or custom solution
    
    if(option.equals("custom")) //if they want to make their own custom question
    {
      if(yesNo.equals("Create Solution")) //if they want to make their own solution
      {       
        this.getWords();
        this.model.createQuestion();
      }
      else //else they want to generate a solution from program
      {
        String question = this.question.getText(); //gets question from text area
        this.solution.getQuestion(question); //sets question to customSolution class
        this.solution.getValues(); //gets the answer for the question
        this.model.setQuestion(question); //sets question to shortAnswerModel class
        String answer = this.solution.getAnswer(); //sets answer from customSolution class to answer variable
        this.model.setAnswer(answer);//sets answer variable to model
        this.model.createQuestion();//creates question 
      }
    }
    else if(option.equals("use template")) //if they want to use a template
    {
    }
    else //if they want a randomly generated question
    {
      this.g = new QuestionGeneratorGUI(model, 1,cT);
    }
    cT.updateTestDisplay();
    this.s.frame.dispose();
  }
  
  /**Gets question and answer sentence from text areas for custom question
    */
  private void getWords()
  {
    String question = this.question.getText();
    this.model.setQuestion(question);
    String answer = this.answer.getText();
    this.model.setAnswer(answer);
  }
  
  /**Sees what the text of selected button in button group is (I DIDNT MAKE THIS!)
    */
  public String getSelectedButtonText(ButtonGroup buttonGroup) 
  {
    for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
      AbstractButton button = buttons.nextElement();
      
      if (button.isSelected()) {
        return button.getText();
      }
    }
    
    return null;
  }
}