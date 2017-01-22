/**MainMenuModel
  * Created by Davin Luong
  * Last Modified 15/01/2016
  * Contains methods used by the MainMenuGUI and MainMenuOptionsController */
public class MainMenuModel extends Object
{
  //Instance Variable
  private MainMenuGUI gui;   //View for the MainMenu
  
  /** Default constructor for the MainMenuModel
    * Creates a MainMenuModel */
  public MainMenuModel()
  {
    super();
  }
  
  /** setView method
    * Links the model to a GUI
    * @param aGUI     The MainMenuGUI to be assigned with */ 
  public void setView(MainMenuGUI aGUI)
  {
    this.gui = aGUI;
  }
  
  /** nextPage method
    * Determines whether to user would like to create or edit a test and opens up the correct page
    * @param anOption     The option that the user has chosen(edit or create) */ 
  public void nextPage(String anOption)
  {
    String option = anOption;
    if (option.equals("Create Test"))
    {
      CreateTestModel model = new CreateTestModel();
      CreateTestGUI gui = new CreateTestGUI(model);   //Opens a CreateTestGUI
      model.createNewTest();   //Creates a new test object
      this.gui.close();   //Closes the MainMenuGUI
    }
    else if (option.equals("Edit Test"))
    {
      EditTestModel model = new EditTestModel();
      EditTestGUI gui = new EditTestGUI(model);   //Opens an EditTestGUI
      this.gui.close();   //Closes the MainMenuGUI
    } 
  }
}