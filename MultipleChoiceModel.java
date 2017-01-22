import java.util.*;

/** MultipleChoiceModel class
  * The model for compiling the inputs from the user and choosing the answer
  * LastModified:  21/01/2016
  * @author  K.Damodar
  */ 

public class MultipleChoiceModel extends Object
{
  //Variable Declarations
  private MultipleChoiceGUI gui;  //The view for the multiple choice
  private String question;        //question the user sets
  private String multipleChoice1; //First multiple choice the user sets
  private String multipleChoice2; //Second multiple choice the user sets
  private String multipleChoice3; //Third multiple choice the user sets
  private String multipleChoice4; //Fourth multiple choice the user sets
  private String answer;          //Answer - multiple choice that corresponds to the checked box
  
  String[] multipleChoice = new String[6];
  
  /**Constructor
    */
  public MultipleChoiceModel()
  {
   super();
   this.question = "";
   
   this.multipleChoice1 = ""; 
   this.multipleChoice2 = "";
   this.multipleChoice3 = "";
   this.multipleChoice4 = "";
  }
  
  /**Sets the view for the MultipleChoice
    * @param current GUI   The View used to display the MultipleChoice
    */
  public void setGUI(MultipleChoiceGUI currentGUI)
  {
   this.gui = currentGUI;    
  }
  
  /** giveQuestion method
    * provides everything about the multiple choice question 
    * @authors Davin and Karan*/ 
  public void giveQuestion()
  {
    this.gui.createQuestion(question, multipleChoice1, multipleChoice2, multipleChoice3, multipleChoice4);
  }
  
  /**Gets the question inputed by user
    *@return the question inputed by user */
  public String getQuestion()
  {
    return this.question;
  }
  
  /**Gets the first multiple choice inputed by user
    *@return the first multiple choice inputed by user */
  public String getMC1()
  {
    return this.multipleChoice1;
  }
  /**Gets the second multiple choice inputed by user
    *@return the second multiple choice inputed by user */
  public String getMC2()
  {
    return this.multipleChoice2;
  }
  /**Gets the third multiple choice inputed by user
    *@return the third multiple choice inputed by user */
  public String getMC3()
  {
    return this.multipleChoice3;
  }
  /**Gets the fourth multiple choice inputed by user
    *@return the fourth multiple choice inputed by user */
  public String getMC4()
  {
    return this.multipleChoice4;
  }
  
  /** Sets the value of the question from the arguements provided*/
  public void setQuestion(String question)
  {
    this.question = question;
  }
  /** Sets the value of the first multiple choice from the arguements provided*/
  public void setMC1(String multipleChoice)
  {
     this.multipleChoice1 = multipleChoice;
  }
  /** Sets the value of the second multiple choice from the arguements provided*/
  public void setMC2(String multipleChoice)
  {
    this.multipleChoice2 = multipleChoice;
  }
  /** Sets the value of the third multiple choice from the arguements provided*/
  public void setMC3(String multipleChoice)
  {
    this.multipleChoice3 = multipleChoice;
  }
  /** Sets the value of the fourth multiple choice from the arguements provided*/
  public void setMC4(String multipleChoice)
  {
    this.multipleChoice4 = multipleChoice;
  }
  
  /** getAnswer method
    * determines which of the choices is the answer and stores it
    * @authors Davin and Karan*/ 
  public String getAnswer()
  {
    if (this.gui.answerChoice1.isSelected() == true)
    {
      return(this.multipleChoice1);
    }
    else if (this.gui.answerChoice2.isSelected() == true)
    {
      return(this.multipleChoice2);
    }
    else if (this.gui.answerChoice3.isSelected() == true)
    {
      return(this.multipleChoice3);
    }
    else if (this.gui.answerChoice4.isSelected() == true)
    {
      return(this.multipleChoice4);
    }
    else 
    {
      return(null);
    }
  }
  
 
 /* public return question(String question, String multipleChoice1, String multipleChoice2, String multipleChoice3, String multipleChoice4)
  {
    
  }
  */
  /** Updates the view in the GUI */
  public void updateView()
  {
   gui.update(); 
  }
  
}
