/**ChangeQGUI
  * Created by Davin Luong
  * Last Modified 16/01/2016
  * GUI that allows the user to change a desired question*/
import java.awt.*;
import javax.swing.*;

public class ChangeQGUI extends JComponent
{
  //Instance Variables
  private ChangeQModel model;   //Instance of a ChangeQModel
  private JFrame frame = new JFrame("Test Guru - Change a Question");   //JFrame that contains all other JComponents
  private JButton begin = new JButton ("Begin");   //Buton that allows the user to begin changing a question
  private JButton save = new JButton ("Save Changes");   //Button that will allow the user to save their changes
  private JTextField questionNumber = new JTextField();   //Where the user will enter the question's number
  private JTextArea question = new JTextArea();   //Where the desired question will be displayed and changed
  private JTextArea answer = new JTextArea();   //The new answer
 
  /** Default constructor for the ChangeQGUI
    * Assigns a Model, sets a view, registers controllers, and displays the GUI
    * @param aModel     The ChangeQModel to be assigned with */ 
  public ChangeQGUI(ChangeQModel aModel)   
  {
    super();
    this.model = aModel;
    this.model.setView(this);
    this.drawChangeQ();
    this.registerControllers();
  }
  
  /** close method
    * closes the ChangeQGUI */ 
  public void close()
  {
    frame.setVisible(true);
    frame.dispose();  
  }
  
  /** drawChangeQ method
    * Draws the ChangeQGUI */
  private void drawChangeQ()   
  { 
    //New JComponents
    JPanel contents = new JPanel();   //Content pane to hold all components
    JPanel getQ = new JPanel();   //Panel containing components to get the user' input on which question they would like to change
    JPanel changeQ = new JPanel();   //Contains components to make changes to a question 
    JPanel savePanel = new JPanel();   //Contains button that will save changes
    JLabel prompt = new JLabel("Please enter the number of the question you would like to change then press \"Begin\"");   //Prompts user to input the number of the question they would like to change
    JLabel prompt2 = new JLabel("Please make changes to the question in the text field below");   //Prompts user to change the question
    JLabel prompt3 = new JLabel("Enter the answer for the new question below then click \"Save Changes\" once you are finished");   //Prompts user to input new answer
    
    //The getQ panel
    getQ.setLayout(new BorderLayout());
    getQ.add(prompt, BorderLayout.NORTH);
    getQ.add(questionNumber, BorderLayout.CENTER);
    getQ.add(this.begin, BorderLayout.SOUTH);
    Font larger = new Font("Times New Roman", Font.PLAIN, 14);   //A new font with an increased size
    prompt.setFont(larger);
    Font bold = new Font("Times New Roman", Font.BOLD, 14);
    this.begin.setFont(bold);
  
    //The changeQ panel
    changeQ.setLayout(new GridLayout(4,1));
    changeQ.add(prompt2);
    //changeQ.add(question);
    JScrollPane questionScroll = new JScrollPane(question);   //Scrollpane incase question is greater than the set size of the JTextArea
    changeQ.add(questionScroll);
    changeQ.add(prompt3);
    //changeQ.add(answer);
    JScrollPane answerScroll = new JScrollPane(answer);   //Scrollpane incase answer is greater than the set size of the JTextArea
    changeQ.add(answerScroll);
    prompt2.setFont(larger);
    prompt3.setFont(larger);
    
    //The save panel
    savePanel.add(this.save);
    Font giant = new Font("Times New Roman", Font.BOLD, 24);   //Giant font for the save button
    this.save.setFont(giant);
    
    
    //The contents pane
    contents.setLayout(new BorderLayout());
    contents.add(getQ, BorderLayout.NORTH);
    contents.add(changeQ, BorderLayout.CENTER);
    contents.add(savePanel, BorderLayout.SOUTH);
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
    ChangeQController beginOp = new ChangeQController(this.model, this.begin);
    this.begin.addActionListener(beginOp);
    ChangeQController saveOp = new ChangeQController(this.model, this.save);
    this.save.addActionListener(saveOp);
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
  
  /** displayAnswer method
    * Displays the desired question's answer
    * @param anAnswer     The desired answer */ 
  public void displayAnswer(String anAnswer)
  {
    this.answer.setText(anAnswer);
  }
  
  /** getQuestion method
    * returns changed question */ 
  public String getQuestion()
  {
    return(this.question.getText());
  }
  
  /** getAnswer method
    * returns changed answer */ 
  public String getAnswer()
  {
    return(this.answer.getText());
  }
}