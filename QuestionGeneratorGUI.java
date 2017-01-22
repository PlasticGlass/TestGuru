import javax.swing.*;
import java.awt.*;
/** The QuestionGeneratorGUI Class
  * 
  * This class creates a GUI where the user can select a unit to generate a random question for
  * 
  * Last Modified: 1/3/2016
  * @author Zubair Waheed
  * */
public class QuestionGeneratorGUI
{
  private JFrame frame;                  //The frame containing all relevant info
  private int type;                      //the type of question this will get the QuestionGenerator to generate
  private JPanel contents;               //Content pane
  private static JList<String> units;    //list of selectable units
  private JButton add;                   //Button used to add question from selected unit
  private shortAnswerModel test;         //Main model for all short answer questions
  private String[] unitNames = {"Kinematics", "Work, Energy, and Power", "Newtons Laws/Forces"}; //Units user can select from
  private CreateTestGUI cT;              //Main GUI used to display test

  /**Constructor
    * 
   *  @param test the test model used throughout the entire program
   *  @param type the type of question this will get the QuestionGenerator to generate
   */
  public QuestionGeneratorGUI(shortAnswerModel test, int type, CreateTestGUI ctGUI)
  {
    this.test = test;
    this.type = type;
    this.createGUI();
    this.cT = ctGUI;
    this.registerControllers();
  }
  
  /**Creates a JFrame and fills it with components*/
  private void createGUI()
  {
    initFrame();
    add = new JButton("Add Question");
    
    //Initialize List of units
    units = new JList<String>(unitNames);
    units.setVisibleRowCount(3); //3 options for user
    units.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Can only select one at a time
    
    //Add components to content pane
    this.contents.add(units, BorderLayout.WEST);
    this.contents.add(add,BorderLayout.EAST);
    this.contents.add(new JLabel("Select the unit this question will be from:"), BorderLayout.NORTH);
    
    //Finalize Frame
    this.frame.setContentPane(contents);
    this.frame.pack();
    frame.setLocationRelativeTo(null);
  }
  
  /**Adds a controller to the only button in the frame*/
  private void registerControllers()
  {
    QuestionGeneratorController qgc = new QuestionGeneratorController(this.test, this.units, this.type,this,cT);
    this.add.addActionListener(qgc);
  }
  
  /**Creates the JFrame which houses the GUI*/
  private void initFrame()
  {
    frame = new JFrame("Random Question");
    contents = new JPanel();
    contents.setLayout(new BorderLayout());
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
    
  /**Closes the created frame*/
  public void closeFrame()
  {
    this.frame.dispose();
  }
}