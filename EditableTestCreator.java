import java.util.*;
import java.io.*;
/**EditableTestCreator Class
  * Contains a single static method which generates a .test file which accompanies the .docx created for a test.
  * This file is used to edit an old test
  * 
  * @author Zubair Waheed
  * @since 1/20/2016
  */
public class EditableTestCreator
{
  /**Generates a .test file which allows the uer to continue editing an old test
    * @param userTest  The test this .test file will be created for*/
  public static void createTestFile(Test userTest)
  {
    //Variables
    String fileName = "";                 //Name of file to be created
    String[] lines;                       //An array of all the data in the test
    String question = "";                 //The question text of the question being outputted
    String answer = "";                   //The answer text of the question being outputted
    int type;                             //The type of question the current question is
    Question currentQuestion;             //The current question being outputter
    ArrayList<String> mChoiceAnswers;     //The possible answers to a multiple choice question
    File test;                            //The .test file to be created
    FileOutputStream fileCreator = null;  //Used to create file
    BufferedWriter writer = null;         //Used to qrite to file
    int questionCounter = 0;              //Used to keep track of whih wiestion is being outputted to the file
    StringBuffer answerLine = null;
    
    //Set file name
    fileName = userTest.getTitle() + ".test";
    
    //Create file object
    test = new File(fileName);
    
    //Array holding test elements should be twice as long as test to hold question + accompanying answer
    //in successive indexes
    lines = new String[userTest.size()*2];
    
    //Obtain info from test object and fill lines array with it
    for(int i = 0;i < lines.length;i+=2)
    {
      currentQuestion = userTest.getQuestion(questionCounter);
      questionCounter++;
      
      //If current question exists
      if(currentQuestion != null)
      {
        type = currentQuestion.getType();
        question = currentQuestion.getQuestion();
        answerLine = new StringBuffer("");
        
        //If mChoice or true/false, get all answers and output them seperately using delimiter ";"
        if(type == 2 || type == 3)
        {
          mChoiceAnswers = currentQuestion.getAnswers();
          
          answerLine.append(currentQuestion.getAnswer() + ";");
          
          if(type == 2)
          {
            for(int p = 0;p < 4; p++)
              answerLine.append(mChoiceAnswers.get(p) + ";"); 
          }
          else 
          {
            for(int p = 0;p < 2; p++)
              answerLine.append(mChoiceAnswers.get(p) + ";"); 
          }
          
          answer = answerLine.toString();
        }
        
        else
          answer = currentQuestion.getAnswer();
        
        //Add question and answer to array of test content
        lines[i] = type + question;
        lines[i+1] = answer;
      }
    }
    
    //Create file (output stream)
    //Partial code from http://www.programcreek.com/2011/03/java-write-to-a-file-code-example/ used
    try
    {
      fileCreator = new FileOutputStream(test);
      writer = new BufferedWriter(new OutputStreamWriter(fileCreator,"UTF-8"));
    }catch(IOException ex)
    {
      ErrorMessages.getMessage(4); //File not accessible
    }
    
    //Write content of lines array to the file
    for (int q = 0; q < lines.length; q++) 
    {
      try
      {
        writer.write(lines[q]);
        writer.newLine();
      }catch(IOException ex)
      {
        ErrorMessages.getMessage(4); //4 - File IO error
      }
    }
    
    //Close filewriter
    try
    {
      writer.close();
    }catch(IOException ex)
    {
      ErrorMessages.getMessage(4); //4 - File IO error
    }
  }
}