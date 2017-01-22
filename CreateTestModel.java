/** CreateTestModel
  * Created by Davin Luong
  * Last Modified 21/01/2016
  * Contains methods used by the CreateTestGUI and CreateTestOptionsController */

import java.io.*;
import org.apache.poi.xwpf.usermodel.*;
import java.util.*;

public class CreateTestModel extends Object
{
  //Instance Variables
  private CreateTestGUI gui;   //View for the CreateTest
  public Test test;   //Instance of a test object
  private AnswerGenerator aG; //Used to generate answer
  
  /** Default constructor for the CreateTestModel
    * Creates a CreateTestModel */
  public CreateTestModel()
  {
    super();
  }
  
  /** Overloaded constructor for the CreateTestModel
    * Creates a CreateTestModel using a user selected tes */
  public CreateTestModel(Test userTest)
  {
    super();
    test = userTest;
  }
  
  /** setView method
    * Links the model to a GUI
    * @param aGUI     The CreateTestGUI to be assigned with */ 
  public void setView(CreateTestGUI aGUI)
  {
    this.gui = aGUI;
  }
  
  /** createNewTest method
    * Links a new Test object to the model */ 
  public void createNewTest()
  {
    test = new Test();
  }
  
  /** openTest method
    * Should display old test
    * @param text     Text within old test */ 
  public void openTest(String text)
  {
    //this.gui.showTest(text); 
    //SHOULD DISPLAY BY CALLING UP EACH QUESTION FROM TEST CLASS
  }
  
  /** testType method
    * Determines what the user would like to do to the test they are creating/editing
    * @param anOption     User's decision */ 
  public void testType(String anOption)
  {
    if (anOption.equals("Return to the MainMenu"))
    {
      MainMenuModel model = new MainMenuModel();   //Instantiates a MainMenuModel
      MainMenuGUI gui = new MainMenuGUI(model);    //Instantiates a MainMenuGUI with the MainMenuModel as the argument
      this.gui.close();
    }
    else if(anOption.equals("Multiple Choice"))
    {
      MultipleChoiceModel model = new MultipleChoiceModel();            //MultipleChoice model
      MultipleChoiceGUI main = new MultipleChoiceGUI(model, this.test, this.gui);  //The MultipleChoice view
    }
    else if(anOption.equals("True or False"))
    {
      TrueOrFalseModel model = new TrueOrFalseModel();
      TrueOrFalseGUI main = new TrueOrFalseGUI(model, this.test, this.gui);
    }
    else if(anOption.equals("Short Answer"))
    {
      shortAnswerModel game = new shortAnswerModel();          //The game model
      shortAnswerGUI mainScreen = new shortAnswerGUI(game, this.test, this.gui);  //The game view
    }
    else if(anOption.equals("Change a Question"))
    {
      ChangeQModel model = new ChangeQModel(this.gui, this.test);
      ChangeQGUI gui = new ChangeQGUI(model);
    }
    else if(anOption.equals("Delete a Question"))
    {
      DeleteQModel model = new DeleteQModel(this.gui, this.test);
      DeleteQGUI gui = new DeleteQGUI(model);
    }
    else if(anOption.equals("Finish"))
    {
      //Temporary Variables
      String title;   //Title/name of the test
      XWPFDocument testDocx;   //Document to store the finished test
      XWPFParagraph titlePara;   //Where the title will be written upon
      XWPFParagraph questionPara;   //Where questions will be appended to in order to create a paragraph 
      XWPFRun theTitle;   //Will write out the title
      XWPFRun question;   //individual questions to be added to the paragraph
      ArrayList<Question> completeQs = new ArrayList<Question>();   //Temporary ArrayList storing all the questions in the completed test
      FileOutputStream output;   //Where the paragraph will be written to
      aG = new AnswerGenerator(this.test);
      if(this.gui.testTitle.getText().equals(""))
        ErrorMessages.getMessage(8);
      else
      {
        this.test.setTitle(this.gui.testTitle.getText());   //Set the test object's title
        title = this.test.getTitle();   //Store the title
        
        testDocx = new XWPFDocument();            //Create a temporary document
        titlePara = testDocx.createParagraph();   //With a title
        theTitle = titlePara.createRun();
        questionPara = testDocx.createParagraph();   //And a paragraph of
        question = questionPara.createRun();        //Questions
        
        titlePara.setAlignment(ParagraphAlignment.CENTER);   //Center Title
        theTitle.setFontFamily("Times New Roman");   //Professional font
        theTitle.setFontSize(16);   //Increase title's font
        theTitle.setBold(true);   //Bold the title
        theTitle.setUnderline(UnderlinePatterns.SINGLE);   //Underline the title
        theTitle.setText(title);   //Write out the Title first
        
        question.setFontFamily("Times New Roman");   //Professional font
        question.setFontSize(12);   //Increase questions' font
        
        completeQs = test.getQuestions();
        int y = 0;
        for (int x=0; x<completeQs.size(); x++)
        {
          int type = completeQs.get(x).getType();
          if(completeQs.get(x).getQuestion() == null)
          {
            y--;
            continue;
          }
          else
          {
             //int type = completeQs.get(x).getType();
             if (type == 1)   //Short Answer
             {
               question.addBreak();   //New Line
               question.setText((++y) + ".   " + completeQs.get(x).getQuestion());   //Write out questions into the paragraph one at a time
               question.addBreak();   //New Line
               question.addBreak();   //New Line
               question.addBreak();   //New Line
               question.addBreak();   //New Line
               question.addBreak();   //New Line
             }
             else if(type == 2)   //Multiple Choice
             {
               question.addBreak();   //New Line
               ArrayList<String> choices = completeQs.get(x).getAnswers();
               question.setText((++y) + ".   " + completeQs.get(x).getQuestion()); 
               question.addBreak();   //New Line
               question.setText("      A) " + choices.get(0));
               question.addBreak();   //New Line
               question.setText("      B) " + choices.get(1));
               question.addBreak();   //New Line
               question.setText("      C) " + choices.get(2));
               question.addBreak();   //New Line
               question.setText("      D) " + choices.get(3));
               question.addBreak();   //New Line
             }
             else if(type == 3)   //True or False
             {
               question.addBreak();   //New Line
               question.setText((++y) + ".   " + completeQs.get(x).getQuestion()); 
               question.addBreak();   //New Line
               question.setText("      True");
               question.addBreak();   //New Line
               question.setText("      False");
               question.addBreak();   //New Line
             }
          }
        }
        try 
        {
          output = new FileOutputStream(title + ".docx");   //create a MS Word .docx file with the test's title being the name of the file
          testDocx.write(output);   //Write the document to the file
          output.close();   
          testDocx.close();
        }catch(Exception e)
        {
          //e.printStackTrace();
        }
          
        aG.generateAnswerKey(); //Generate answers
        EditableTestCreator.createTestFile(this.test);//Generate.test file to accompany .docx file
        }
      this.gui.close(); 
      }
    }
  }