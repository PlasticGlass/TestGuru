/**DeleteQGUI
  * Created by Davin Luong
  * Last Modified 16/01/2016
  * GUI that allows the user to change a desired question*/
import java.awt.*;
import javax.swing.*;

public class DeleteQGUI extends JComponent
{
  //Instance Variables
  private DeleteQModel model;   //Instance of a DeleteQModel
  private JFrame frame = new JFrame("Test Guru - Delete a Question");   //JFrame that contains all other JComponents
  private JButton view = new JButton ("View");   //Buton that allows the user to view a question
  private JButton delete = new JButton ("Delete Question");   //Button that will allow the user to delete the desired question
  private JTextField questionNumber = new JTextField();   //Where the user will enter the question's number
  private JTextArea question = new JTextArea();   //Where the desired question will be displayed 
 
  /** Default constructor for the DeleteQGUI
    * Assigns a Model, sets a view, registers controllers, and displays the GUI
    * @param aModel     The DeleteQModel to be assigned with */ 
  public DeleteQGUI(DeleteQModel aModel)   
  {
    super();
    this.model = aModel;
    this.model.setView(this);
    this.drawDeleteQ();
    this.registerControllers();
  }
  
  /** close method
    * closes the DeleteQGUI */ 
  public void close()
  {
    frame.setVisible(true);
    frame.dispose();  
  }
  
  /** drawChangeQ method
    * Draws the ChangeQGUI */
  private void drawDeleteQ()   
  { 
    //New JComponents
    JPanel contents = new JPanel();   //Content pane to hold all components
    JPanel getQ = new JPanel();   //Panel containing components to get the user' input on which question they would like to change
    JPanel deleteQ = new JPanel();   //Contains components to view a question 
    JPanel deletePanel = new JPanel();   //Contains button that will delete a question
    JLabel prompt = new JLabel("Please enter the number of the question you would like to delete then press \"View\"");   //Prompts user to input the number of the question they would like to delete
    JLabel prompt2 = new JLabel("Please make sure the question below is the one you would like to delete, otherwise re-enter a number and press view above");   //Prompts user to double check the question
    
    //The getQ panel
    getQ.setLayout(new BorderLayout());
    getQ.add(prompt, BorderLayout.NORTH);
    getQ.add(questionNumber, BorderLayout.CENTER);
    getQ.add(this.view, BorderLayout.SOUTH);
    Font larger = new Font("Times New Roman", Font.PLAIN, 14);   //A new font with an increased size
    prompt.setFont(larger);
    Font bold = new Font("Times New Roman", Font.BOLD, 14);
    this.view.setFont(bold);
  
    //The deleteQ panel
    deleteQ.setLayout(new GridLayout(4,1));
    deleteQ.add(prompt2);
    deleteQ.add(question);
    prompt2.setFont(larger);
    
    //The delete panel
    deletePanel.add(this.delete);
    Font giant = new Font("Times New Roman", Font.BOLD, 24);   //Giant font for the delete button
    this.delete.setFont(giant);
    
    
    //The contents pane
    contents.setLayout(new BorderLayout());
    contents.add(getQ, BorderLayout.NORTH);
    contents.add(deleteQ, BorderLayout.CENTER);
    contents.add(deletePanel, BorderLayout.SOUTH);
    frame.setContentPane(contents);
    
    Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();   
    int width = (int)screenDimensions.getWidth() / 2;
    int height = (int)screenDimensions.getHeight() - 40;
    //frame.setPreferredSize(new Dimension(width, height));
    //frame.setExtendedState(JFrame.MAXIMIZED_VERT); 
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
  }
  
  /** registerControllers method
    * Registers ChangeQControllers and assigns them to buttons */ 
  private void registerControllers()
  {
    DeleteQController viewOp = new DeleteQController(this.model, this.view);
    this.view.addActionListener(viewOp);
    DeleteQController deleteOp = new DeleteQController(this.model, this.delete);
    this.delete.addActionListener(deleteOp);
  }
  
   /** getQuestionNumber method
    * returns desired question's number */ 
  public int getQuestionNumber()
  {
    return((Integer.parseInt(this.questionNumber.getText())));
  }
  
  /** displayQuestion method
    * Displays the desired question
    * @param aQuestion     The desired question */ 
  public void displayQuestion(String aQuestion)
  {
    this.question.setText(aQuestion);
  }
}