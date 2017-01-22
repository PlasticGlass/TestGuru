/** EditTestModel
  * Created by Davin Luong
  * Last Modified 15/01/2016
  * Contains methods used by the EditTestGUI and EditTestOptionsController */
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

public class EditTestModel extends Object
{
  //Instance Variables
  private EditTestGUI gui;   //View for the EditTest
  private String text;   //text within the old test
  private Test userTest; //Test to be created from selected file
  private File testChosen;   //the test the user chooses
  private TestExtractor extractor;
  private String fileName;
  
  /** Default constructor for the EditTestModel
    * Creates an EditTestModel */
  public EditTestModel()
  {
    super();
  }
  
  /** setView method
    * Links the model to a GUI
    * @param aGUI     The EditTestGUI to be assigned with */ 
  public void setView(EditTestGUI aGUI)
  {
    this.gui = aGUI;
  }
  
  /** findFile method
    * Open an old test and display it in the JTextField */ 
  public void findFile() 
  {
    //Temporary Variables
    JFileChooser test = new JFileChooser();   //Allows user to find an old test
    int fileSelected;   //Stores whether user opens an old test or not
    XWPFDocument testDocx;   //Store the old test into a temporary document
    XWPFWordExtractor extract;   //Used to extract text from a document
    
    fileSelected = test.showOpenDialog(this.gui.testDisplay);   //Find the old test
    if (fileSelected == test.APPROVE_OPTION)
    {
      testChosen = test.getSelectedFile();
      try
      {
        testDocx = new XWPFDocument(new FileInputStream(testChosen));   
        extract = new XWPFWordExtractor(testDocx);   
        this.text = extract.getText();   //Extract text
        this.gui.showTest(text);   //Display text in JTextField in the GUI
      }catch(IOException e)
      {
      }
    }
    
    //Obtain filename
    fileName = testChosen.getName();
    fileName = fileName.substring(0,fileName.lastIndexOf("."));
    
    //Extract test
    extractor = new TestExtractor(fileName);
    userTest = extractor.getExtractedTest();
  }
  
  /** begin method
    * Opens up a CreateTestGUI so that the user can begin editing the test */ 
  public void begin()
  {
    CreateTestModel model = new CreateTestModel(this.userTest);
    CreateTestGUI cT = new CreateTestGUI(model);   //Creates and displays the CreateTestGUI
    cT.updateTestDisplay();   //Takes the questions from this test and displays it within the CreateTestGUI
    this.gui.close();   //Closes the EditTestGUI
  }
  
  /** back method
    * returns user back to the main menu */ 
  public void back()
  { 
    MainMenuModel model = new MainMenuModel();   //Instantiates a MainMenuModel
    MainMenuGUI gui = new MainMenuGUI(model);    //Instantiates a MainMenuGUI with the MainMenuModel as the argument
    this.gui.close();
  }
}