/** shortAnswerModel
    * adds questions to test
    *  Last Modified:  13/01/2016
    *  @author M.Chen
    */
public class shortAnswerModel extends Object
{
  private shortAnswerGUI view;//view connected to model
  private String question;//question from textArea
  private String answer;//answer from textArea
  public Test test; //test used to create questions
  private Question q;//question that contains question and answer text
  
  //Constructor: creates model
  public shortAnswerModel()
  {
    this.question = "";
    this.answer = "";
  }
  
  /** setGUI method
    * sets GUI to model
    * @param currentGUI gui being connected
    */
  public void setGUI(shortAnswerGUI currentGUI)
  {
    this.view = currentGUI;
  }
  
  /** setTest method
    * sets test from GUI
    * @param tests test from GUI
    */
  public void setTest(Test tests)
  {
    this.test  = tests;
  }
  
  /** setQuestion method
    * sets test from GUI
    * @param q question from GUI
    */
  public void setQuestion(String q)
  {
    this.question = q;
  }
  
  /** setAnswer method
    * sets answer from GUI
    * @param a answer from GUI
    */
  public void setAnswer(String a)
  {
    this.answer = a;
  }
  
  /** returnQuestion method
    * returns question
    */
  public String returnQuestion()
  {
    return this.question;
  }
  
  /** returnAnswer method
    * returns answer
    */
  public String returnAnswer()
  {
    return this.answer;
  }
  
  /** updateView method
    * updates view on gui, doesn't use this in program
    */
  public void updateView()
  {
    view.update();
  }
  
  /** createQuestion method
    * create a new question object from question and answer from GUI
    */
  public void createQuestion()
  {
    this.q = new Question(this.question, this.answer, 1);
    this.test.addQuestion(q);
  }
  
  /** createQuestion method
    * adds a known short answer question object to the test
    */
  public void createQuestion(Question q)
  {
    this.test.addQuestion(q);
  }
}
    