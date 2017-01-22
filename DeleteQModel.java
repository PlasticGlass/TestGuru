/**DeleteQModel
  * Created by Davin Luong
  * Last Modified 16/01/2016
  * Contains methods used by the DeleteQGUI and DeleteQController */
public class DeleteQModel extends Object
{
  //Instance Variables
  private DeleteQGUI gui;   //View for the DeleteQ
  private CreateTestGUI cT;
  private Test test;   //A Test instance
  private int question;   //The question that the user would like to delete
  
  /** Default constructor for the DeleteQModel
    * Creates a DeleteQModel and links it to a CreateTestGUI and Test instance
    * @param ctGUI     CreateTestGUI to link to
    * @param aTest     Test to link to */
  public DeleteQModel(CreateTestGUI ctGUI, Test aTest)
  {
    super();
    cT = ctGUI;
    this.test = aTest;
  }
  
  /** setView method
    * Links the model to a GUI
    * @param aGUI     The DeleteQGUI to be assigned with */ 
  public void setView(DeleteQGUI aGUI)
  {
    this.gui = aGUI;
  }
  
  /** displayQ method
    * Displays the desired question
    * @param theQuestion     The question that the user would like to delete */
  public void displayQ()
  {
    this.question = this.gui.getQuestionNumber();
    Question aQuestion = this.test.getQuestion(this.question - 1);
    if(aQuestion != null)
    {
      String questionText = aQuestion.getQuestion();
      this.gui.displayQuestion(questionText);
    }
    
  }
  
  /** delete method
    * remove the desired question */ 
  public void delete()
  {
    this.test.removeQuestion(question);
    this.cT.updateTestDisplay();
    this.gui.close();   //Closes the DeleteQGUI
  }
}