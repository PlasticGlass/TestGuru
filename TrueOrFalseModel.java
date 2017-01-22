/**Created by Karan
  * Modified by Davin
  * Last modified on 21/01/2016
  * Model for the TrueOrFalse class */

import java.util.*;

public class TrueOrFalseModel
{
   private TrueOrFalseGUI gui;
   private String question;
 
   
   public TrueOrFalseModel()
   {
    super();
    this.question = "";     
   }
   
   public void setGUI(TrueOrFalseGUI currentGUI)
   {
    this.gui = currentGUI;     
   }
      
   public void giveQuestion()
   {
    this.gui.createQuestion(question); 
   }
   
   public String getQuestion()
   {
    return this.question;  
   }
   
   public void setQuestion(String question)
   {
    this.question = question; 
   }
   
   /** getAnswer method
     * determines which of the choices is the answer and stores it
     * @authors Davin*/ 
   
   public String getAnswer()
   {
     if (this.gui.trueBox.isSelected() == true)
     {
       return("True");
     }
     else if (this.gui.falseBox.isSelected() == true)
     {
       return("False");
     }
     else 
     {
       return(null);
     }
   }
}