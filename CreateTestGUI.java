/** CreateTestGUI
  * Created by Davin Luong
  * Last Modified 21/01/2016
  * GUI that allows user to create a test */
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class CreateTestGUI extends JComponent
{
  //Instance Variables
  private CreateTestModel model;   //Instance of a CreateTestModel
  private JFrame page = new JFrame("Test Guru - Create Test");   //JFrame that contains all other JComponents
  private JButton back = new JButton("Return to the MainMenu");   //Button that will bring the user back to the main menu
  private JButton mC = new JButton ("Multiple Choice");   //Button that allows the user to create a Multiple Choice question
  private JButton tF = new JButton("True or False");   //Button that allows the user to create a True or False question
  private JButton sA = new JButton ("Short Answer");   //Button that allows the user to create a Short Answer question
  private JButton change = new JButton ("Change a Question");   //Button that allows the user to change a question
  private JButton delete = new JButton ("Delete a Question");   //Button that allows the user to delete a question
  private JButton finish = new JButton ("Finish");   //Button that will write the test to a Microsoft Word document (.docx)
  public JTextArea testDisplay = new JTextArea();   //Where the test will be displayed
  public JTextField testTitle = new JTextField();   //Where the user can enter the test's title
  
  /** Default constructor for the CreateTestGUI
    * Assigns a Model, sets a view, registers controllers, and displays the GUI
    * @param aModel     The CreateTestModel to be assigned with */ 
  public CreateTestGUI(CreateTestModel aModel)   //Creates object
  {
    super();
    this.model = aModel;
    this.model.setView(this);
    this.drawCreateTest();
    this.registerControllers();
  }
  
  /** close method
    * closes the CreateTestGUI once user has finished creating a test */ 
  public void close()
  {
    page.setVisible(true);
    page.dispose();
  }
  
  /** drawEditTest method
    * Draws the CreateTestGUI */
  private void drawCreateTest()   
  {
    //New JComponents
    JPanel contents = new JPanel();   //Content pane to hold all components
    JPanel options = new JPanel();   //Contains buttons to create/modify a test 
    JPanel question = new JPanel();   //Contains buttons that determine what type of question will be created
    JPanel testDisplayPanel = new JPanel();   //Panel that contains the JTextField to allow user to view a test
    JPanel getTitle = new JPanel();   //Panel that will contain components to get the test's title
    JPanel mCtF = new JPanel();   //Panel that will hold both the multiple choice and short answer buttons
    JPanel backB = new JPanel();   //Panel that will contain the back button and the page's title
    JLabel title = new JLabel("Create Test");   //The title of the page
    
    //The backB panel
    backB.add(this.back);
    backB.add(title);
    
    //The mCtF panel
    mCtF.setLayout(new GridLayout(2,1));
    mCtF.add(mC);
    mCtF.add(tF);

    //The question panel
    question.setLayout(new GridLayout(2,2));
    question.add(mCtF);
    question.add(sA);
    question.add(change);
    question.add(delete);
    Font larger = new Font("Times New Roman", Font.PLAIN, 24);   //New font with increased fon size
    mC.setFont(larger);
    tF.setFont(larger);
    sA.setFont(larger);
    change.setFont(larger);
    delete.setFont(larger);
    
    //The options panel
    options.setLayout(new BorderLayout());
    options.add(backB, BorderLayout.NORTH);
    options.add(question, BorderLayout.CENTER);
    options.add(finish, BorderLayout.SOUTH);
    title.setFont(new Font("Times New Roman", Font.BOLD, 72));   //Larger font size for the GUI's title
    finish.setFont(new Font("Times New Roman", Font.BOLD, 28));   //Large and bolded font for the "Finish" button
    
    //The getTitle panel
    getTitle.setLayout(new GridLayout(2,1));
    getTitle.add(new JLabel("Please enter the title of the test in the line below:"), BorderLayout.NORTH);
    getTitle.add(testTitle, BorderLayout.CENTER);
    
    //The testDisplay panel
    testDisplayPanel.setLayout(new BorderLayout());
    testDisplayPanel.add(getTitle, BorderLayout.NORTH);
    this.testDisplay.setEditable(false);
    JScrollPane displayScroll = new JScrollPane(this.testDisplay);   //Scrollpane incase test is greater than the set size of the JTextArea
    testDisplayPanel.add(displayScroll, BorderLayout.CENTER);
    
    //The contents pane
    contents.setLayout(new GridLayout(1,2));
    contents.add(options);
    contents.add(testDisplayPanel);
    
    page.setContentPane(contents);
    page.setExtendedState(JFrame.MAXIMIZED_BOTH); //Full screen
    page.pack();
    page.setVisible(true);
  }
  
  
  /** registerControllers method
    * Registers CreateTestOptionsControllers and assigns them to buttons */ 
  private void registerControllers()
  {
    CreateTestOptionsController backOp = new CreateTestOptionsController(this.model, this.back);
    this.back.addActionListener(backOp);
    CreateTestOptionsController mCOp = new CreateTestOptionsController(this.model, this.mC);
    this.mC.addActionListener(mCOp);
    CreateTestOptionsController tFOp = new CreateTestOptionsController(this.model, this.tF);
    this.tF.addActionListener(tFOp);
    CreateTestOptionsController sAOp = new CreateTestOptionsController(this.model, this.sA);
    this.sA.addActionListener(sAOp);
    CreateTestOptionsController changeQOp = new CreateTestOptionsController(this.model, this.change);
    this.change.addActionListener(changeQOp);
    CreateTestOptionsController deleteQOp = new CreateTestOptionsController(this.model, this.delete);
    this.delete.addActionListener(deleteQOp);
    CreateTestOptionsController finishOp = new CreateTestOptionsController(this.model, this.finish);
    this.finish.addActionListener(finishOp);
  }
  
  /**public void showTest(String text)
  {
    this.testDisplay.setText(text);
  }*/
  
  /** updateTestDisplay method
    * Runs a loop that will get questions from the Test class and display them in the testDisplay JTextField */ 
  public void updateTestDisplay()
  {
    this.testDisplay.setText("");
    ArrayList<Question> completeQs = new ArrayList<Question>();   //temporary ArrayList 
    completeQs = this.model.test.getQuestions();                  //that stores all the questions from the Test class
    for (int x=0; x<completeQs.size(); x++)   //Short Answer 
    {
      int type = completeQs.get(x).getType();
      if (type == 1)
      {
        this.testDisplay.append((x + 1)+ ".   " + completeQs.get(x).getQuestion() + "\n\n\n");   //Displays a question on the JTextField each time it loops until no questions remain
      }
      else if(type == 2)   //Multiple Choice
      {
        ArrayList<String> choices = completeQs.get(x).getAnswers();
        this.testDisplay.append((x + 1)+ ".   " + completeQs.get(x).getQuestion() + "\n" + "      A) " + choices.get(0) + "\n" + "      B) " + choices.get(1) + 
        "\n" + "      C) " + choices.get(2) + "\n" + "      D) " + choices.get(3) + "\n\n\n");
      }
      else if(type == 3)   //True/False
      {
        ArrayList<String> choices = completeQs.get(x).getAnswers();
        this.testDisplay.append((x + 1)+ ".   " + completeQs.get(x).getQuestion() + "\n" + "      True" + "\n" + "      False" + "\n\n\n");
      }
    }
  } 
}