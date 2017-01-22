import javax.swing.*;
import java.awt.*;
/** shortAnswerGUI
    * view of short answer
    *  Last Modified:  13/01/2016
    *  @author M.Chen
    */
public class shortAnswerGUI extends JPanel
{
  private shortAnswerModel model;
  private JTextArea question = new JTextArea (10,40);   //First number for calculation
  JScrollPane sp = new JScrollPane(question); //Scroller for question
  private JTextArea answer = new JTextArea(10,20);   //Second number for calculation
  JScrollPane sp2 = new JScrollPane(answer); //Scroller for answer
  private JRadioButton custom = new JRadioButton("Custom");//custom question
  private JRadioButton template = new JRadioButton("Work With My Own Templates");//own template
  private JRadioButton equation = new JRadioButton("Random Question Based on Unit");//random question
  private JRadioButton yesAnswer = new JRadioButton("Write your own solution");//own solution
  private JRadioButton noAnswer = new JRadioButton("Generate solution");//generate solution
  private JButton add = new JButton("Continue");//continue
  private ButtonGroup group; //groups radio buttons together
  private ButtonGroup group2; //groups radio buttons together
  public JFrame frame = new JFrame("Short Answer Questions");//Jframe
  public CreateTestGUI cT;//main GUI
  
  //Constructor: links GUI to the model and test object
  public shortAnswerGUI(shortAnswerModel newModel, Test tests, CreateTestGUI ctGUI)
  {
    super();
    this.model = newModel;
    cT = ctGUI;
    this.model.setGUI(this); //connects model to GUI
    this.model.setTest(tests); //connects test to the model
    this.layoutView();
    this.registerButtonControllers();
    this.update();
  }
  
  /** layoutView
    * creates view of short answer
    */
  private void layoutView()
  {
    JPanel top = new JPanel(); //layout for top portion. Includes add question and choices
    BoxLayout layout = new BoxLayout(top,BoxLayout.X_AXIS);
    top.setLayout(layout);
    JPanel addPanel = new JPanel();
    addPanel.add(add);
    JPanel buttonPanel = new JPanel();
    group = new ButtonGroup(); //adding radio buttons to group
    group.add(custom);
    group.add(template);
    group.add(equation);
    custom.setSelected(true); //sets custom one as default
    buttonPanel.add(custom);
    buttonPanel.add(template);
    buttonPanel.add(equation);
    buttonPanel.setBorder(BorderFactory.createTitledBorder("How do you want to create your question?"));
    top.add(addPanel);
    top.add(buttonPanel);
    
    JPanel bot = new JPanel(); //layout for bot portion, includes textareas for q and a
    BoxLayout layout2 = new BoxLayout(bot, BoxLayout.X_AXIS);
    JPanel questionPan = new JPanel();//question panel
    questionPan.setBorder(BorderFactory.createTitledBorder("Write question here"));
    JPanel answerPan = new JPanel(); //answer panel
    answerPan.setBorder(BorderFactory.createTitledBorder("Write answer here"));
    
    JPanel answerOrNah = new JPanel();
    BoxLayout ansOrNahLayout = new BoxLayout(answerOrNah, BoxLayout.Y_AXIS);
    answerOrNah.setLayout(ansOrNahLayout);
    group2 = new ButtonGroup(); //adding radio buttons to group
    group2.add(yesAnswer);
    group2.add(noAnswer);
    answerOrNah.add(yesAnswer);
    answerOrNah.add(noAnswer);
    yesAnswer.setSelected(true);
    
    questionPan.add(sp); //sets sp for question
    answerPan.add(sp2); //sets sp2 for answer
    bot.setLayout(layout2);
    bot.add(questionPan);
    bot.add(answerPan);
    bot.add(answerOrNah);
    
    question.setLineWrap(true); //sets wrap for q
    question.setWrapStyleWord(true);
    
    answer.setLineWrap(true); //sets wrap for a
    answer.setWrapStyleWord(true);
    
    BoxLayout layout3 = new BoxLayout(this, BoxLayout.Y_AXIS); //overall layout of program
    this.setLayout(layout3);
    this.add(top);
    this.add(bot);
    
    //Initialize the Frame
    //Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();   //By
    //int width = (int)screenDimensions.getWidth() / 2;                           //Davin
    //int height = (int)screenDimensions.getHeight() - 40;                        //
    //frame.setPreferredSize(new Dimension(width, height));                       //
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setContentPane(this);
    frame.setVisible(true);
    frame.pack();
    frame.setLocationRelativeTo(null);
  }
  
  /** registerButtonControllers
    * register controllers
    */
  private void registerButtonControllers()
  {
    customController addButtonController = new customController(this.model, this.add, this.question, this.answer, this.group, this.group2, this, cT);
    this.add.addActionListener(addButtonController);
  }
  
  /** update
    * update view...not used in this program
    */
  public void update()
  {
  }
}