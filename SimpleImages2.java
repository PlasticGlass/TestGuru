import org.apache.poi.util.Units; //apache stuff
import java.io.FileInputStream;  
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.BreakType; 
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.awt.Graphics; //image stuff
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class SimpleImages2 {
  
  public static void main(String[] args) throws Exception 
  {
    XWPFDocument doc = new XWPFDocument();
    XWPFParagraph p = doc.createParagraph();
    
    XWPFRun r = p.createRun();
    
    //for(String imgFile : args) {
    String imgFile = "pepe.png";
    BufferedImage bimg = ImageIO.read(new File(imgFile));
    int width = bimg.getWidth();
    int height = bimg.getHeight();
    System.out.println(width);
    System.out.println(height);
    
    int format =XWPFDocument.PICTURE_TYPE_JPEG;
    
    r.setText(imgFile);
    r.addBreak();
    r.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(width/3), Units.toEMU(height/3)); 
    r.addBreak(BreakType.PAGE);
    //}
    
    FileOutputStream out = new FileOutputStream("Word.docx");
    doc.write(out);
    out.close();
  }
}