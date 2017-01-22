/**ChangeQModel
  * Created by Davin Luong
  * Last Modified 16/01/2016
  * Contains methods used by the ChangeQGUI and ChangeQController */
public class ChangeQModel extends Object
{
  //Instance Variables
  private ChangeQGUI gui;   //View for the ChangeQ
  private CreateTestGUI cT;
  private Test test;   //A Test instance
  private int question;   //The question that the user would like to change
  
  /** Default constructor for the ChangeQModel
    * Creates a ChangeQModel and links it to a CreateTestGUI and Test instance
    * @param ctGUI     CreateTestGUI to link to
    * @param aTest     Test to link to */
  public ChangeQModel(CreateTestGUI ctGUI, Test aTest)
  {
    super();
    cT = ctGUI;
    this.test = aTest;
  }
  
  /** setView method
    * Links the model to a GUI
    * @param aGUI     The ChangeQGUI to be assigned with */ 
  public void setView(ChangeQGUI aGUI)
  {
    this.gui = aGUI;
  }
  
  /** displayQ method
    * Displays the desired question */
  public void displayQ()
  {
    this.question = this.gui.getQuestionNumber();
    Question aQuestion = this.test.getQuestion(this.question - 1);
    String questionText = aQuestion.getQuestion();
    this.gui.displayQuestion(questionText);
  }
  
  /** displayA method
    * Displays the desired question's answer */
  public void displayA()
  {
    this.question = this.gui.getQuestionNumber();
    Question aQuestion = this.test.getQuestion(this.question - 1);
    String answerText = aQuestion.getAnswer();
    this.gui.displayAnswer(answerText);
  }
  
  /** saveChanges method
    * Save the new question and answer over the old */ 
  public void saveChanges()
  {
    this.test.editQuestion(this.question, this.gui.getQuestion(), this.gui.getAnswer());
    this.cT.updateTestDisplay();
    this.gui.close();   //Closes the ChangeQGUI
  }
}