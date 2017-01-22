import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.*;

/** OwnTemplateGUI class
  * This class contains links to other classes which allow for selecting and using a user made template 
  * or creating a new one
  * 
  * @author Zubair Waheed
  * @since 1/15/2016
  */
public class OwnTemplateGUI implements ActionListener
{
  private JFrame frame;                             //The frame containing all relevant info
  private JPanel contents;                          //Content pane
  private JList<String> userTemplates;              //A list of all the user's question templates
  private JButton addNew;                           //Used to add a new template
  private JButton useSelected;                      //Obtains template user selected and allows them to work with it
  private File templates = new File("uTemplates.tg");//File containing user templates 
  private List<String> userTemplateText;            //Text obtained from file
  private String[] temps;                           //The users templates
  private JPanel templatesAndScrollPane;            //Panel containing scrollpane and list of templates
  public shortAnswerModel model;                    //Model used throughout program
  private JScrollPane templateScroll;               //Scroll pane which contains the list of templates
  private NewTemplateGUI nT;                        //A GUI this contains (for creating a new Template
  private EditTemplateGUI eT;                       //A GUI this contains (for using a selected template)
  private CreateTestGUI cT;                         //main program GUI which this passes to other classes, does not use it directly
  
  
  /**Constructor
    *  @param newModel the model used throughout the entire short answer program
    *  @param cT the main program GUI 
    */
  public OwnTemplateGUI(shortAnswerModel newModel, CreateTestGUI cT)
  {
    this.model = newModel;
    this.cT = cT;
    this.obtainTextForList();
    this.createGUI();
    this.registerControllers();
  }
  
  
  /**Closes the gui whose component called this method
   * Opens a new GUI based on user's action*/
  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("Add a new template"))
    {
      this.frame.dispose();
      nT = new NewTemplateGUI();
    }
    
    else if(e.getActionCommand().equals("Use the selected template") && this.userTemplates.getSelectedIndex() == -1)
      ErrorMessages.getMessage(7); //7 - Template not selected
    
    else if(e.getActionCommand().equals("Use the selected template") && this.userTemplates.getSelectedIndex() != -1)
    {
      this.frame.dispose();
      eT = new EditTemplateGUI(this.userTemplates.getSelectedIndex(), this.model, this.cT);
    }
  }
  
  
  /**Lays out the entire GUI, creating and putting all components in their place*/
  private void createGUI()
  {
    initFrame();
    
    //Create components
    this.addNew = new JButton("Add a new template");
    this.useSelected = new JButton("Use the selected template");
    this.userTemplates = new JList<String>(this.temps);
    
    //Setup list of templates and its panel
    this.userTemplates.setVisibleRowCount(5); 
    this.userTemplates.setFixedCellWidth(500);
    
    this.templateScroll = new JScrollPane(this.userTemplates);
    this.templatesAndScrollPane.add(this.templateScroll);
    this.userTemplates.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    //Add to content pane
    this.contents.add(addNew, BorderLayout.WEST);
    this.contents.add(useSelected, BorderLayout.EAST);
    this.contents.add(templatesAndScrollPane, BorderLayout.CENTER);
    this.contents.add(new JLabel("Which one?"), BorderLayout.NORTH);
    
    //Finalize frame
    this.frame.setContentPane(contents);
    this.frame.pack();
    this.frame.setLocationRelativeTo(null);
  }
  
  /**Assigns itself as a actionListener to the buttons*/
  private void registerControllers()
  {
    this.addNew.addActionListener(this);
    this.useSelected.addActionListener(this);
  }
  
  
  /**Initializes the window (JFrame)*/
  private void initFrame()
  {
    frame = new JFrame("Working With Your Own Templates");
    contents = new JPanel();
    templatesAndScrollPane = new JPanel();
    contents.setLayout(new BorderLayout());
    
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
  
  
  /**Opens and reads the contents of the userTemplates file, adds this info to an array used for the list*/
  private void obtainTextForList()
  {
    try{
      //Obtain all lines of text from file
      this.userTemplateText = Files.readAllLines(Paths.get(templates.toString()), StandardCharsets.UTF_8);
      
      //Allocate sufficient memory to array of templates
      this.temps = new String[this.userTemplateText.size()];
    }
    catch(IOException ex)
    {
      ErrorMessages.getMessage(4);
    }
    
    //Copy list of lines into String[]
    for(int i = 0;i<this.userTemplateText.size();i++)
    {
      this.temps[i] = this.userTemplateText.get(i);
    }
  }
  
  
  /**Gets rid of entire window*/
  public void closeFrame()
  {
    this.frame.dispose();
  }
  
}
