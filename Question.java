import java.util.*;
/**The Question Class
  * 
  * Contains a string representation of a question, its answer, and its other answers if it is a multiple choice question
  * Can be a short answer or multiple choice question.
  * 
  * @author Zubair Waheed
  * @since 12/21/2015
  * */
public class Question extends Object
{
  private String question;          //The question itself
  private String answer;            //The correct answer or complete solution to the question
  private int type;                 //The type of question it is (short answer = 1/mChoice = 2)
  private ArrayList<String> answers;//All the possible answers to the quesiton if it is multiple choice
  
  /**Constructor
    * Creates a question
    * 
    * @param questionText the question itself
    * @param answerText the answer to the question
    * @param qType the type of question it is (1/2)(short answer/multiple choice)
    */
  public Question(String questionText, String answerText, int qType)
  {
    super();
    this.question = questionText;
    this.answer = answerText;
    this.type = qType;
    
    //No need for answer array if short answer question
    if(this.type == 1)
      this.answers = null;
    else 
      this.answers = new ArrayList<String>();
  }
  
  /**Overload constructor
    *
    * User can use this if they know their other answer beforehand
    * 
    * @param questionText the question itself
    * @param answerText the answer to the question
    * @param qType the type of question it is (1/2)(short answer/multiple choice)
    * @param answers the other answer to the multiple choice question
    */
  public Question(String questionText, String answerText, int qType, ArrayList<String> answers)
  {
    super();
    this.question = questionText;
    this.answer = answerText;
    this.type = qType;
    this.answers = answers;
  }
  
  
  /**Allows user to set answer to question
    *
    * @param newAnswer the new answer to the question
    */
  public void setAnswer(String newAnswer)
  {
    this.answer = newAnswer;
  }
  
  
  /**Allows user to set other answers to question
    *
    * @param answer the collection of answers to replace the current one with
    * edited by Davin on 21/01/2016 to include seting answers for T/F questions
    */
  public void setAnswers(ArrayList<String> answers)
  {
    if(this.type == 2 ^ this.type == 3)
      this.answers = answers;
  }
  
  
  /**Allows user to set question text
    *
    * @param newQuestion the new question
    */
  public void setQuestion(String newQuestion)
  {
    this.question = newQuestion;
  }
  
  
  /**Returns question text
    *
    * @returns the question text for this Question
    */
  public String getQuestion()
  {
    return this.question;
  }
  
  
  /**Returns answer to question
    *
    * @returns the answer to the question
    */
  public String getAnswer()
  {
    return this.answer;
  }
  
  
  /**Returns all the answer to a multiple choice question
    *
    * @return collection of answers
    */
  public ArrayList<String> getAnswers()
  {
    return this.answers;
  }
  
  
  /**Returns the type of question this is
    *
    * @returns the type of question it is
    */
  public int getType()
  {
    return this.type;
  }
  
  
  /**Allows user todelete an answer from the collection of answers
    *
    * @param answerNum the index of the answer to remove
    */
  public boolean deleteAnswer(int answerNum)
  {
    //Can only be removed if it exists
    try
    {
      this.answers.remove(answerNum-1);
      return true;
    }
    catch(IndexOutOfBoundsException ex)
    {
      return false;
    }
  }
}