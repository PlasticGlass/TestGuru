/** EditTestGUI
  * Created by Davin Luong
  * Last Modified 15/01/2016
  * GUI that allows user to find an old test to edit*/
import java.awt.*;
import javax.swing.*;

public class EditTestGUI extends JComponent
{
  //Instance Variables
  private EditTestModel model;   //Instance of a EditTestModel
  private JButton findTest = new JButton("Find Test");   //Button that allows user to find an old test
  private JButton startEdit = new JButton("Begin Editing");   //Button that allows user to begin editing the test
  private JButton back = new JButton("Return to the MainMenu");   //Button that brings the user back to the main menu
  private JFrame page = new JFrame("Test Guru - Edit Test");   //JFrame that contains all other JComponents
  public JTextArea testDisplay = new JTextArea();   //Area in which the chosen test will be displayed   
  
  /** Default constructor for the EditTestGUI
    * Assigns a Model, sets a view, registers controllers, and displays the GUI
    * @param aModel     The EditTestModel to be assigned with */ 
  public EditTestGUI(EditTestModel aModel)   //Creates object
  {
    super();
    this.model = aModel;
    this.model.setView(this);
    this.drawEditTest();
    this.registerControllers();
  }
  
  /** close method
    * closes the EditTestGUI once user has decided to begin editing */ 
  public void close()
  {
    page.setVisible(true);
    page.dispose();
  }
  
  /** drawEditTest method
    * Draws the EditTestGUI */
  private void drawEditTest()   
  {
    //New JComponents
    JPanel contents = new JPanel();   //Content pane to hold all components
    JPanel options = new JPanel();   //Contains buttons to find a test and begin editing
    JPanel testDisplayPanel = new JPanel();   //Panel that contains the JTextField to allow user to view a test
    JPanel backB = new JPanel();   //Panel that holds the back button and the page's title
    JLabel title = new JLabel("Edit Test");   //Title of page
    JLabel prompt = new JLabel("Please choose the word doc of the test you would like to edit");   //Prompt for user to choose a test
    JLabel doubleCheck = new JLabel("Please make sure this is the test you would like to edit");   //Reminder to user
    
    //The backB panel
    backB.add(this.back);
    backB.add(title);

    //The options panel
    options.setLayout(new GridLayout(4,1));
    options.add(backB);
    options.add(prompt);
    options.add(this.findTest);
    options.add(this.startEdit);
    Font larger = new Font("Times New Roman", Font.PLAIN, 24);   //New font with an increased size
    this.findTest.setFont(larger);
    prompt.setFont(larger);
    this.startEdit.setFont(new Font("Times New Roman", Font.BOLD, 28));   //New font with an even larger font size
    title.setFont(new Font("Times New Roman", Font.BOLD, 72));   //Very large sized font for the title
    
    //The test display panel
    testDisplayPanel.setLayout(new BorderLayout());
    testDisplayPanel.add(doubleCheck, BorderLayout.NORTH);
    this.testDisplay.setEditable(false);   //So that the user cannot edit the test manually
    JScrollPane displayScroll = new JScrollPane(this.testDisplay);   //Incase the test cannot fit within the JTextArea
    testDisplayPanel.add(displayScroll, BorderLayout.CENTER);  
    doubleCheck.setFont(new Font("Times New Roman", Font.BOLD, 14));   //Increased font size
    
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
    * Registers EditTestOptionsControllers and assigns them to buttons */ 
  public void registerControllers()
  {
    EditTestOptionsController backOp = new EditTestOptionsController(this.model, this.back);
    this.back.addActionListener(backOp);
    EditTestOptionsController fileOp = new EditTestOptionsController(this.model, this.findTest);
    this.findTest.addActionListener(fileOp);
    EditTestOptionsController beginOp = new EditTestOptionsController(this.model, this.startEdit);
    this.startEdit.addActionListener(beginOp);
  }
  
  /** showTest method
    * displays text within an old test on a JTextField */ 
  public void showTest(String text)
  {
    testDisplay.setText(text);
  }
}