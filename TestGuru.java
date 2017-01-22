
/**TestGuru Tester
  * Created by Davin Luong
  * Last Modified 11/01/2016
  * Instantiates a MainMenuModel and MainMenuGUI to begin the program */
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.swing.UIManager.*;

public class TestGuru
{
  public static void main (String[] args) 
  {
    try //Copied from BenjaminLinus at http://stackoverflow.com/questions/4617615/how-to-set-nimbus-look-and-feel-in-main
    {
      for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())  
      {
        if ("Nimbus".equals(info.getName())) 
        {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {}
    
    MainMenuModel model = new MainMenuModel();   //Instantiates a MainMenuModel
    MainMenuGUI gui = new MainMenuGUI(model);    //Instantiates a MainMenuGUI with the MainMenuModel as the argument
  }
}