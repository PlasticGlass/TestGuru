import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
/** TestExtractor Class
  * Opens a user selected file and converts it to a test object to add to the main instance of the program
  *
  * @author Zubair Waheed
  * @since 1/19/2016
  */

public class TestExtractor
{
  //Required info
  private Test userTest; //Test that will be created from parsed info
  private String userFile; //The filename of the file the user selects
  private Object[] fileContents; //An array used to store the files contents
  private ArrayList<String> answers; //Answers to multiple choice questions 
  private StringTokenizer answerLine; //The answer to corresponding question
  private String questionText = ""; //The text for question and answer
  private String answerText = "";
  private int type;                    //The type of question it is working with
  
  /**Constructor-
    * Creates an instance of the class, extracts text from user inputted file
    * @param testFile the file user chose to open
    */
  public TestExtractor(String fileName)
  {
    //Will be opening a .test file
    userFile =  fileName + ".test";
    userTest = new Test();
    
    //Obtain test
    extract();
  }
  
  /**Obtains text from file, converts it to test object*/
  private void extract()
  {
    try
    {
      //Get all text in file
      fileContents = Files.readAllLines(Paths.get(this.userFile),StandardCharsets.UTF_8).toArray();
    }catch(IOException ex)
    {
      ErrorMessages.getMessage(4); //4 - File could not be accessed
    }
    
    //Add questions to test one by one
    for(int i = 0;i < fileContents.length;i+=2)
    {
      //Obtain corresponding questions and answers
      questionText = fileContents[i].toString();
      answerText = fileContents[i+1].toString();
      type = Character.getNumericValue(questionText.charAt(0));
      
      //If short answer question
      if(type == 1)
      {
        questionText = questionText.substring(1);//Dont include type in question text
        this.userTest.addQuestion(new Question(questionText,answerText,1));
      }
      
      //If multiple choice question or true/false question
      else if(type == 2 || type == 3)
      {
        //Initialize list of answers
        answers = new ArrayList<String>();
        
        //Will obtain answers with this
        answerLine =  new StringTokenizer(fileContents[i+1].toString(),";");
        
        //Correct answer is always first token
        answerText = answerLine.nextToken();
        
        //Get other answers
        while(answerLine.hasMoreTokens())
        {
          answers.add(answerLine.nextToken());
        }
        
        this.userTest.addQuestion(new Question(questionText.substring(1),answerText,type,answers));
      }
    }
  }
  
  
  /**returns the test extracted from the file
    * @returns the test the user wants to work with
    */
  public Test getExtractedTest()
  {
    return userTest;
  }
}