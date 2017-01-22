import java.util.*;
/** The Test Class
  * 
  * The class containing all the questions in the test. 
  * Allows questions to be added, removed, and edited.
  * 
  * Last Modified: 12/21/2015
  * @author Zubair Waheed
  * */
public class Test extends Object
{
  private ArrayList<Question> test; // The Collection of all of the questions in the test
  private String title;
  
  /**Constructor*/
  public Test()
  {
    super();
    this.test = new ArrayList<Question>();
  }
  
  
  /** size method
    * returns the length of the test
    * 
    * @returns the length of the ArrayList of questions in the test
    */
  public int size()
  {
    return this.test.size();
  }
  
  
  /** addQuestion method
    * adds a question to the end of the test
    * 
    * @param question the question to be added
    */
  public void addQuestion(Question question)
  {
    //Add the inputted question to the collection of 
    this.test.add(question);
  }
  
  
  /** removeQuestion method
    * removes a given question from the test
    * 
    * @param index the position in the test of the question to be removed
    */
  public void removeQuestion(int index)
  {
    if(index > this.test.size() || index <= 0)
      ErrorMessages.getMessage(5);
    
    //If index exists in collection of questions, remove it
    else
      this.test.remove(index-1);


  }
  
  
  /** removeQuestion method
    * removes a given question from the test
    * 
    * @param index the position in the test of the question to be removed
    */
  private boolean questionExists(int index)
  {
    //If index exists in collection of questions
    if(index-1 < this.test.size()-1 && index > 0)
      return false;
    else
      return true;
  }
  
  
  /** getQuestion method
    * gets the question located at index in the test
    * 
    * @param index the position in the test of the question to be ontained
    * @returns returns the question wanted
    */
  public Question getQuestion(int index)
  {
    //Gets the question if it exists, returns null if it doesnt
    try
    {
      return this.test.get(index);
    }catch(IndexOutOfBoundsException ex)
    {
      ErrorMessages.getMessage(6);
      return null;
    }
  }
  
  /** getQuestions method
    * returns all questions in the test as an ArrayList of questions
    * 
    * @returns returns all the questions in the test as an arraylist
    */
  public ArrayList<Question> getQuestions()
  {
    return this.test;
  }
  
  
  /** editQuestion method
    * prompts for the information for question at index in the test, and changes its information accrodingly
    * 
    * @param index where the question to be edited is located in the test
    * @returns returns the edited question
    */
  public Question editQuestion(int index, String newQuestion, String newCorrectAnswer)
  {
    //If index out of bounds
    if (index > this.test.size() || index<=0)
    {
      ErrorMessages.getMessage(6);
      return null;
    }
    
    //Obtin selected question
    Question qE = this.test.get(index-1);
    
    //If answer has not been changed
    if(newCorrectAnswer.equals("") && !newQuestion.equals(""))
    {
       qE.setQuestion(newQuestion);
    }
    
    else
    {
      qE.setQuestion(newQuestion);
      qE.setAnswer(newCorrectAnswer);
    }
    return qE;
  }
  
  /** getTitle method
    * @authors Zubair and Davin
    * @returns returns the test's title */
  public String getTitle()
  {
    return this.title;
  }
  
  /** setTitle method
    * @authors Zubair and Davin
    * @param title which is the given title of the test
    * sets the test's title */
  public void setTitle(String title)
  {
    this.title = title;
  }
}
