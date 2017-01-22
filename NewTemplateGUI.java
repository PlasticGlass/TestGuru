import javax.swing.*;
import java.io.*;
import java.awt.*;

/**NewTemplateGUI class
 * Allows user to create a new question template along with its answer template
 * 
 * @author Zubair Waheed
 * @since 1/16/2016
 */ 
public class NewTemplateGUI 
{
  //Initialize all components
  private JFrame frame = new JFrame("Add a new template");//Main frame
  private JTextArea question = new JTextArea(10,80); //Question/Answer text areas
  private JPanel top = new JPanel();                 //Panel containing all components at top of frame
  private JPanel bottom = new JPanel();              //Contains all components to be in the bottom of the frame
  private JPanel questionP = new JPanel();           //Panel containing question info
  private JPanel answerP = new JPanel();             //Panel containing answer info
  private JPanel contents = new JPanel();            //Content pane
  private JButton add = new JButton("Add Template"); //Used to add template to file
  
  //Scrollpane containing question text area
  private JScrollPane qs = new JScrollPane(question,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
  
  //Instructions
  private JPanel instructions = new JPanel();
  private JLabel instruction0 = new JLabel("READ ME!");
  private JLabel instruction1 = new JLabel("1. Write your template and its answer here. Click the Add Template button when finished");
  private JLabel instruction2 = new JLabel("2. Type two underscores (with spaces on both sides) where you want numbers to be when you use it later.");
  private JLabel instruction3 = new JLabel("3. ex. the force applied was __ N. You wont be able to edit these numbers later if you dont do this!");
  private JLabel instruction4 = new JLabel("4. You can add multiple templates at once. ONLY 1 TEMPLATE/ANSWER PER LINE. Do NOT split a template or answer onto multiple lines!");
  private JLabel instruction5 = new JLabel("CORRECT: the force was __ N. The mass was __ kg. Find the acceleration");
  private JLabel instruction6 = new JLabel("INCORRECT: the force was __ N. The mass was __ kg.");
  private JLabel instruction7 = new JLabel("                          Find the acceleration");
  
  
  /**Constructor*/
  public NewTemplateGUI()
  {
    createGUI();
    registerControllers();
  }
  
  /**Creates a JFrame and fills it with all the components*/
  private void createGUI()
  {
    initFrame();
    
    //Set Layouts
    top.setLayout(new BorderLayout());
    bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
    instructions.setLayout(new BoxLayout(instructions, BoxLayout.Y_AXIS));
    
    //Question/answer panels initialization
    questionP.add(qs);
    questionP.setBorder(BorderFactory.createTitledBorder("Question"));
    
    //Add components to all panels
    instructions.add(instruction0);
    instructions.add(instruction1);
    instructions.add(instruction2);
    instructions.add(instruction3);
    instructions.add(instruction4);
    instructions.add(instruction5);
    instructions.add(instruction6);
    instructions.add(instruction7);
    instructions.setBorder(BorderFactory.createTitledBorder("Instructions"));
    
    top.add(add, BorderLayout.WEST);
    top.add(instructions, BorderLayout.EAST);
    
    bottom.add(questionP);
    
    contents.add(top,BorderLayout.NORTH);
    contents.add(bottom,BorderLayout.SOUTH);
    
    //Finalize frame
    this.frame.setContentPane(contents);
    this.frame.pack();
    this.frame.setLocationRelativeTo(null);
  }
  
  /**Initializes the window (JFrame)*/
  private void initFrame()
  {
    this.frame = new JFrame("Adding Your Own Template");
    contents.setLayout(new BorderLayout());
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
  
  /**Assigns controller to Add template button*/
  private void registerControllers()
  {
    NewTemplateGUIController nT = new NewTemplateGUIController(question, this);
    this.add.addActionListener(nT);
  }
  
  /**Closes the GUI*/
  public void close()
  {
    this.frame.dispose();
  }
  
  
}