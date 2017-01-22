/**MultipleChoiceGUI Class
  * The GUI View for creating a multiple Choice section
  * Last Modified:  02/01/2016
  * @author      K.Damodar
  */
import javax.swing.*;
import java.awt.*;
import java.util.*;


public class MultipleChoiceGUI extends JPanel
{
  //Instance Variables
  
  private MultipleChoiceModel model; // model of the MultipleChoice
  private Test test;   //instance of a test
  private CreateTestGUI cT;   //instance of a CreateTestGUI
  private ArrayList<String> answers; //Array of answers that the multiplechoice will hold
  
  private JFrame f = new JFrame("Multiple Choice");
  
  //instance variables that hold the most recent input by the user
  private String questions = "";
  private String userMultipleChoice1 = "  ";
  private String userMultipleChoice2 = "  ";
  private String userMultipleChoice3 = "  ";
  private String userMultipleChoice4 = "  ";
  
  //holds the inputs from the user
  private JLabel answerQuestions = new JLabel("",SwingConstants.CENTER);
  private JLabel answerMultipleChoice1 = new JLabel("",SwingConstants.CENTER);
  private JLabel answerMultipleChoice2 = new JLabel("",SwingConstants.CENTER);
  private JLabel answerMultipleChoice3 = new JLabel("",SwingConstants.CENTER);
  private JLabel answerMultipleChoice4 = new JLabel("",SwingConstants.CENTER);
 
  //Labels prompting the user to enter multiple choice
  private JLabel question = new JLabel("Question: ");
  private JLabel multipleChoice1 = new JLabel("Multiple Choice #1: ");
  private JLabel multipleChoice2 = new JLabel("Multiple Choice #2: ");
  private JLabel multipleChoice3 = new JLabel("Multiple Choice #3: ");
  private JLabel multipleChoice4 = new JLabel("Multiple Choice #4: ");
  
  //Check boxes for the correct answer to the multiple choices
  public JCheckBox answerChoice1 = new JCheckBox("Answer", false);
  public JCheckBox answerChoice2 = new JCheckBox("Answer", false);
  public JCheckBox answerChoice3 = new JCheckBox("Answer", false);
  public JCheckBox answerChoice4 = new JCheckBox("Answer", false);
  
  //Textfield for the user to input his question and multiple choice
  private JTextField enterQuestion = new JTextField(10);
  private JTextField enterMultipleChoice1 = new JTextField(10);
  private JTextField enterMultipleChoice2 = new JTextField(10);
  private JTextField enterMultipleChoice3 = new JTextField(10);
  private JTextField enterMultipleChoice4 = new JTextField(10);
  
  //button to send info to the main output
  private JButton add = new JButton("Add"); 
  
  //empty JLabel's for spacing
  private JLabel empty1 = new JLabel("                       ");  
  private JLabel empty2 = new JLabel("                 ");  
  private JLabel empty3 = new JLabel("                                                                              ");
    
  private JPanel output = new JPanel(); //Panel  that outputs all the textfields and labels that are seen
  private JPanel buttons = new JPanel(); // Panel that holds buttons
 
  
  // Panel to hold the question label, text field, and check box
  private JPanel questionPanel = new JPanel(); 
  
  // Panel to hold the multiple choice label, text field, and check box
  private JPanel choice1Panel = new JPanel(); 
  private JPanel choice2Panel = new JPanel(); 
  private JPanel choice3Panel = new JPanel();  
  private JPanel choice4Panel = new JPanel(); 
  
  /**Constructor for the GUI. Assigns Model and View, identifies controllers
   *Links component to Model, Test and CreateTestGUI classes
   * @param model The model for the CheckOut
   * @param aTest 
   * @param ctGUI
   */
  public MultipleChoiceGUI(MultipleChoiceModel model, Test aTest, CreateTestGUI ctGUI)
  {
    super();
    this.model = model;  
    this.test = aTest;
    this.cT = ctGUI;
    this.model.setGUI(this);
    this.layoutView();
    this.registerControllers();
    this.update();
  }
  
  /**
    */ 
  public void createQuestion(String aQuestion, String aChoice1, String aChoice2, String aChoice3, String aChoice4)
  {
    String anAnswer = this.model.getAnswer();
    System.out.println(anAnswer);
    ArrayList<String> choices = new ArrayList<String>();
    choices.add(aChoice1);
    choices.add(aChoice2);
    choices.add(aChoice3);
    choices.add(aChoice4);
    this.test.addQuestion(new Question(aQuestion, anAnswer, 2, choices));
    this.cT.updateTestDisplay();   
    this.f.dispose();
  }
  
  /** Draws the layout for the recipt
    */ 
  public void layoutView()
  {
    //Initialize the Frame
    this.f.setSize(500,200);
    this.f.setLocationRelativeTo(null);
    this.f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.f.setContentPane(this);
    this.f.setVisible(true);
    
    //Panel holds question label and textfields
    questionPanel.setLayout(new BoxLayout(this.questionPanel, BoxLayout.X_AXIS));
    questionPanel.add(question);
    questionPanel.add(empty2);
    questionPanel.add(enterQuestion);
    questionPanel.add(empty1);
    
    //Panels holds multiple choice label, textfields and checkboxes
    choice1Panel.setLayout(new BoxLayout(this.choice1Panel,BoxLayout.X_AXIS));
    choice1Panel.add(multipleChoice1);
    choice1Panel.add(enterMultipleChoice1);
    choice1Panel.add(answerChoice1);
    
    choice2Panel.setLayout(new BoxLayout(this.choice2Panel,BoxLayout.X_AXIS));
    choice2Panel.add(multipleChoice2);
    choice2Panel.add(enterMultipleChoice2);
    choice2Panel.add(answerChoice2);
    
    choice3Panel.setLayout(new BoxLayout(this.choice3Panel,BoxLayout.X_AXIS));
    choice3Panel.add(multipleChoice3);
    choice3Panel.add(enterMultipleChoice3);
    choice3Panel.add(answerChoice3);
    
    choice4Panel.setLayout(new BoxLayout(this.choice4Panel,BoxLayout.X_AXIS));
    choice4Panel.add(multipleChoice4);
    choice4Panel.add(enterMultipleChoice4);
    choice4Panel.add(answerChoice4);
    
    //collects the question and multiplechoice panels 
    output.setLayout(new GridLayout(6,1));
    output.add(questionPanel);
    output.add(choice1Panel);
    output.add(choice2Panel);
    output.add(choice3Panel);
    output.add(choice4Panel);
    
    //panel that collects all the buttons
    buttons.setLayout(new BoxLayout(this.buttons, BoxLayout.X_AXIS));    
    buttons.add(empty3);
    buttons.add(this.add);
    
    
    //Complete layout
    this.setLayout(new BorderLayout());
    this.add(this.output, BorderLayout.CENTER);   
    this.add(this.buttons,BorderLayout.NORTH);
  }
  
  /**Assigns controller to the add button
    */
  private void registerControllers()
  {
    MultipleChoiceController controller = new MultipleChoiceController(this.model,this.enterQuestion,this.enterMultipleChoice1,this.enterMultipleChoice2,this.enterMultipleChoice3,this.enterMultipleChoice4, this.add);
    this.enterQuestion.addActionListener(controller);
    this.enterMultipleChoice1.addActionListener(controller);
    this.enterMultipleChoice2.addActionListener(controller);
    this.enterMultipleChoice3.addActionListener(controller);
    this.enterMultipleChoice4.addActionListener(controller);
    this.add.addActionListener(controller);
  }
  
  public void update()
  {
    this.answerQuestions.setText(model.getQuestion());
    this.answerMultipleChoice1.setText(model.getMC1());
    this.answerMultipleChoice2.setText(model.getMC2());
    this.answerMultipleChoice3.setText(model.getMC3());
    this.answerMultipleChoice4.setText(model.getMC4());    
  }   
}


