/**MainMenuGUI
  * Created by Davin Luong
  * Last Modified 15/01/2016
  * GUI that determines whether the user would like to create or edit a test*/
import java.awt.*;
import javax.swing.*;

public class MainMenuGUI extends JComponent
{
  //Instance Variables
  private MainMenuModel model;   //Instance of a MainMenuModel
  private JFrame menu = new JFrame("Test Guru - Main Menu");   //JFrame that contains all other JComponents
  private JButton create = new JButton ("Create Test");   //Buton that allows the user to begin creating a test
  private JButton edit = new JButton ("Edit Test");   //Button that will allow the user to begin editing a test
 
  /** Default constructor for the MainMenuGUI
    * Assigns a Model, sets a view, registers controllers, and displays the GUI
    * @param aModel     The MainMenuModel to be assigned with */ 
  public MainMenuGUI(MainMenuModel aModel)   
  {
    super();
    this.model = aModel;
    this.model.setView(this);
    this.drawMainMenu();
    this.registerControllers();
  }
  
  /** close method
    * closes the MainMenuGUI once user has decided to create/edit a test */ 
  public void close()
  {
    menu.setVisible(true);
    menu.dispose();  
  }
  
  /** drawMainMenu method
    * Draws the MainMenuGUI */
  private void drawMainMenu()   
  { 
    //New JComponents
    JPanel contents = new JPanel();   //Content pane to hold all components
    JPanel banner = new JPanel();   //Panel just for the Program's banner/logo
    JPanel options = new JPanel();   //Contains the button to create/edit a test
    Branding logo = new Branding();   //the banner/logo
    
    //The banner panel
    banner.setLayout(new BorderLayout());
    banner.add(logo, BorderLayout.CENTER);
    
    //The options panel
    options.setLayout(new GridLayout(1,2));
    options.add(create);
    options.add(edit);
    Font larger = new Font("Times New Roman", Font.PLAIN, 32);   //A new font with an increased size
    create.setFont(larger);
    edit.setFont(larger);

    //The contents pane
    contents.setLayout(new BorderLayout());
    contents.add(banner, BorderLayout.NORTH);
    contents.add(options, BorderLayout.CENTER);
    
    menu.setContentPane(contents);
    menu.setExtendedState(JFrame.MAXIMIZED_BOTH); //Full screen
    menu.pack();
    menu.setVisible(true);
  }
  
  /** registerControllers method
    * Registers MainMenuOptionsControllers and assigns them to buttons */ 
  private void registerControllers()
  {
    MainMenuOptionsController createOp = new MainMenuOptionsController(this.model, this.create);
    this.create.addActionListener(createOp);
    MainMenuOptionsController editOp = new MainMenuOptionsController(this.model, this.edit);
    this.edit.addActionListener(editOp);
  }
}