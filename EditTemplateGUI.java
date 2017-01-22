import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.BorderLayout;
/** EditTemplateGUI class
  * 
  * Opens a gui where the user can edit one of their templates, allows them to fill it with their own numbers, or have the program 
  * generate random values for it
  * 
  * @author Zubair Waheed
  * @since 1/16/2016
  */
public class EditTemplateGUI
{
  //All components 
  private JFrame frame = new JFrame("Editing a Template");//Main frame
  private JPanel contents = new JPanel();                 //Content pane
  private JTextArea templateEdit = new JTextArea(10,70);  //JTextArea for user to edit template
  private JButton rand = new JButton("Fill With Random Values");
  private JButton done = new JButton("Done");
  private JScrollPane templateScroll = new JScrollPane(templateEdit,JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
  private JPanel buttons = new JPanel();                 //Panel containing all the buttons in the GUI
  private File templates = new File("uTemplates.tg");    //File the templates are located in
  private String template;                               //The template chosen
  public shortAnswerModel model;                         //The main model for short answer questions
  private CreateTestGUI cT;                              //The gui the test is displayed on
  
  /**Constructor
   *
   * Creates GUI for user to edit their templates in
   * 
   * @param selectedTemplate the template the user chose in the previous GUI, OwnTemplateGUI which creates this object
   * @param model the main model for all short answer questions
   * @param cT the main GUI, contains the test display
   */
  public EditTemplateGUI(int selectedTemplate, shortAnswerModel model, CreateTestGUI cT)
  {
    try
    {
      template = Files.readAllLines(Paths.get(templates.toString()), StandardCharsets.UTF_8).get(selectedTemplate); 
    }catch(IOException ex)
    {
      ErrorMessages.getMessage(4); //4- File I/O Error
    }
    
    //Set gui and model to send data back to
    this.cT = cT;
    this.model = model;
    
    //Initilialize window
    createGUI();
    registerControllers();
  }
  
  /**Creates frame and places all components in it*/
  private void createGUI()
  {
    initFrame();
    
    //Set JTextArea text to user chosen template
    this.templateEdit.setText(template);
    
    //Layout buttons in panel
    buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
    buttons.add(rand);
    buttons.add(done);
    buttons.setBorder(BorderFactory.createEtchedBorder());
    
    //Set JTextArea border
    templateScroll.setBorder(BorderFactory.createTitledBorder("Your Template"));
    
    //Add panels to content pane
    contents.add(templateScroll,BorderLayout.NORTH);
    contents.add(buttons,BorderLayout.SOUTH);
    
    //Finalize frame
    this.frame.setContentPane(contents);
    this.frame.pack();
    this.frame.setLocationRelativeTo(null);
  }
  
  /**Assigns controller to "random values" and "done" buttons*/
  private void registerControllers()
  {
    EditTemplateGUIController eC = new EditTemplateGUIController(this.templateEdit, this.model, this, this.cT);
    this.rand.addActionListener(eC);
    this.done.addActionListener(eC);
  }
  
  /**Initializes the frame for the GUI*/
  private void initFrame()
  {
    contents.setLayout(new BorderLayout());
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
  
  /**Closes the GUI*/
  public void close()
  {
    this.frame.dispose();
  }
}
