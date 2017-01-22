/**Branding
  *Created by Davin Luong
  *Last Modified 15/01/2016
  *Retrieves the Test Guru Banner so that it can be painted on the Main Menu */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Branding extends JComponent
{
  ImageIcon logo;   //Variable in which the image will be stored
  
  /** Default constructor for the Branding class
    * Gets the banner's jpg file */ 
  public Branding()
  {
    super();
    logo = new ImageIcon("Banner2.jpg");
    this.setPreferredSize(new Dimension(1500,350));
  }
  
  /** paintComponent method
    * Draws/paints that banner 
    * @param g     A Graphics abstract */ 
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Image logoImage = logo.getImage();
    g.drawImage(logoImage,0,0,null);
  }
}