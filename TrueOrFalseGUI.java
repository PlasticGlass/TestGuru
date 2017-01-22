/**Created by Karan
  * Modified by Davin
  * Last modified on 21/01/2016
  * GUI for the TrueOrFalse class */

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TrueOrFalseGUI extends JPanel
{
  //Instance Variables
  private TrueOrFalseModel model;
  private Test test;
  private CreateTestGUI cT;
  
  //labels and such
  private JFrame f = new JFrame("True Or False");
  
  private JLabel question = new JLabel("Question: ");
  
  private JTextField enterQuestion = new JTextField(10);
  
  private JButton add = new JButton ("Add");
  
  private JPanel questionPanel = new JPanel();
  private JPanel trueOrFalse = new JPanel();
  private JPanel output = new JPanel();
  
  private JLabel labelTrue = new JLabel("True");
  private JLabel labelFalse = new JLabel("False");
  
  private JLabel empty1 = new JLabel("                       ");  
  private JLabel empty2 = new JLabel("                 ");  
  
  public JCheckBox trueBox = new JCheckBox("True");
  public JCheckBox falseBox = new JCheckBox("False");
  
  //Constructor
  public TrueOrFalseGUI(TrueOrFalseModel model, Test aTest, CreateTestGUI ctGUI)
  {
   super();   
   this.model = model;
   this.test = aTest;
   this.cT = ctGUI;
   this.model.setGUI(this);
   this.layoutView();
   this.registerControllers();
  }
  public void createQuestion(String aQuestion)
  {
   String anAnswer = this.model.getAnswer();
   ArrayList<String> choices = new ArrayList<String>();
   choices.add("True");
   choices.add("False");
   this.test.addQuestion(new Question(aQuestion, anAnswer, 3, choices));
   this.cT.updateTestDisplay();
   this.f.dispose();   
  }
  
  public void layoutView()
  {
    //Initialize the Frame
    this.f.setSize(500,200);
    this.f.setLocationRelativeTo(null);
    this.f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.f.setContentPane(this);
    this.f.setVisible(true);
    
    trueOrFalse.setLayout(new BoxLayout(this.trueOrFalse, BoxLayout.X_AXIS));
    //trueOrFalse.add(labelTrue);
    trueOrFalse.add(trueBox);
    //trueOrFalse.add(labelFalse);
    trueOrFalse.add(falseBox);
    
    //Panel holds question label and textfields
    questionPanel.setLayout(new BoxLayout(this.questionPanel, BoxLayout.X_AXIS));
    questionPanel.add(question);
    questionPanel.add(empty2);
    questionPanel.add(enterQuestion);
    questionPanel.add(trueOrFalse);
    
    //output.setLayout(new GridLayout(2,1));
    //output.add(questionPanel);
    //output.add(trueOrFalse);
    
    this.f.setLayout(new BorderLayout());
    this.f.add(this.questionPanel, BorderLayout.CENTER);
    this.f.add(this.add,BorderLayout.SOUTH);
  }
  
  private void registerControllers()
  {
    TrueOrFalseController controller = new TrueOrFalseController(this.model,this.enterQuestion,this.add);
    this.enterQuestion.addActionListener(controller);
    this.add.addActionListener(controller);    
  }
}