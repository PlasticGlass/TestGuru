import org.apache.poi.xwpf.usermodel.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

/**AnswerGenerator Class
  * Generates an answer key in word document format for a test
  * 
  * @author Zubair Waheed
  * @since 1/19/2016
  */
public class AnswerGenerator
{
  private Test test;                                  //Test for which answer key will be created
  private FileOutputStream out;                       //Used to create new word doc
  private XWPFDocument answerKey = new XWPFDocument();//Word doc to output to
  private XWPFParagraph answer;                       //A defined area inside an XWPFDocument 
  private XWPFRun run;                                //The region of text to be written to (this contains the text)
  private Question currentQuestion;                   //Current question being output
  private StringTokenizer stepByStep;                 //Used to properly format stepbystep solutions
  
  /**Constructor
    * @param userTest the test the answer key will be created for
    */
  public AnswerGenerator(Test userTest)
  {
    this.test = userTest;
  }
  
  
  /**Generates creates and fills a word document with answers*/
  public void generateAnswerKey()
  {
    outputAnswers();
    createDocument();
  }
  
  
  /**Creates a word document through Output stream to fill with answers
    *Takes whatever is supposed to be in the word doc, and puts it in one  
    */ 
  private void createDocument()
  {
    try
    {
      out = new FileOutputStream(this.test.getTitle()+"-Answers.docx");
      answerKey.write(out); //AnswerKey will be written to using out
      
      //Close streams
      out.close();
      answerKey.close();
    }catch(IOException ex)
    {
      ErrorMessages.getMessage(4); //4 - File I/O error
    }
  }
  
  
  /**Creates a word document through Output stream to fill with answers
    *Takes whatever is supposed to be in the word doc, and puts it in one  
    */ 
  private void outputAnswers()
  {
    //Create required XWPF objects
    answer = answerKey.createParagraph();
    run = answer.createRun();
    
    int y = 0; //Used to assign correct question number in output file
    for(int i = 0;i<test.size();i++)
    {
      currentQuestion = test.getQuestion(i);
      
      //Go to next question if current is null
      if(currentQuestion == null)
      {
        y--;
        continue;
      }
      
      //Format question number
      run.setText((++y) + ".   ");
      
      //If current question does not have an answer, skip it
      if(currentQuestion.getAnswer() == "" || currentQuestion.getAnswer() == null)
      {
        run.addBreak(); 
        run.addBreak();  
        run.addBreak();  
        continue;
      }
      
      //If answer can be split into steps
      else if(currentQuestion.getAnswer().indexOf(";;") != -1)
      {
        //Question has been successfully obtained, tokenize its answers using ;; as delimiter 
        stepByStep = new StringTokenizer(currentQuestion.getAnswer(),";;");
        
        //While more steps in solution
        while(stepByStep.hasMoreTokens())
        {
          run.setText(stepByStep.nextToken());
          run.addBreak();
        } 
        run.addBreak(); 
      }
      
      //Set text to whatever there is instead
      else
      {
        run.setText(currentQuestion.getAnswer());
        run.addBreak();  
        run.addBreak();  
      }  
    }
  }
}
