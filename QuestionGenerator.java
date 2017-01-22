import java.util.*;
import java.io.*;
import java.nio.file.*; //For Files and Paths classes
import java.nio.charset.StandardCharsets;
/** The QuestionGenerator Class
  * 
  * This class generates a random physics quiz question. It can be short answer, or multiple choice. It obtains a 
  * question and solution template from files containing them, and fills them in and solves the question providing a 
  * full solution. The question is chosen randomly from a selection based on which unit the user wants it to be from
  * 
  * Last Modified: 1/16/2015
  * @author Zubair Waheed
  * */

public class QuestionGenerator extends Object 
{
  private int type;//...............................The type of question this will generate 1/2 for Short Answer/Multiple Choice respectively
  private Random gen = new Random();//..............A random generator used for generating the random values of the question
  private int rv1, rv2, rv3;//......................The randomly generated values used for the question and their answer, used in the question text for easy reading
  private double rvD1, rvD2, rvD3, answer;//........The decimal equivalents of the random values, for questions where more precision is required
  private StringBuilder question, solution;//.......The final question to part of the question object returned in getQuestion and its solution
  private StringTokenizer templateContents = null;//Used to seperate question text from random values to be inputted
  private ArrayList<String> answers = new ArrayList<String>();//List containing all answers to multiple choice questions
  private String next; //...........................The next token in the tokenizer
  private int template;//...........................Which template the question will be using
  private String templateText;//....................The text of the template used
  private int unit;//...............................The equation the question will be based on
  private boolean qFileFound;//.....................was file containing the question templates found?
  private boolean aFileFound;//.....................was file containing the answer templates found?
  private File answerTemplates = new File("aTemplates.tg");//The files containing the templates used for input
  private File templates = new File("qTemplates.tg");
  
  
  /** Constructor
    * Doesnt do anything besides creating an object
    */
  public QuestionGenerator()
  {
    super();  
  }
  
  
  /** getQuestion method
    * This method: gets templates for a question and its answer from files, fills them in with random values, 
    * generates other random answers for multiple choice and returns this random question 
    * 
    * @param unitNum the unit the question will be from (1-4)
    * @param qType determines whether the question created will be short answer of multiple choice (1 = SA, 2 = MC)
    * @returns a question object using the randomly generated info and templates
    */
  public Question getQuestion(int unitNum, int qType)
  {
    //Set random question type to the one wanted by the user
    type = qType;
    
    //Set inputted equation as equation to get template for
    unit = unitNum;
    
    //Get a template
    getTemplate();
    
    //Reset the strings for the question and its answer eveytime the user wants to genrate a new question
    question = new StringBuilder("");
    solution = new StringBuilder("");
    
    //Get all the info pertaining to the random question
    getRandomValues();
    determineSolution();
    getQuestionText();
    getAnswerText();
    getOtherAnswers();
    
    //Return a question containing all of the generated info if it was able to be created successfully
    if(qFileFound && aFileFound && type == 1)
      return new Question(question.toString(), solution.toString(), 1);
    else if(qFileFound && aFileFound && type == 2)
      return new Question(question.toString(), solution.toString(), 2, answers);
    else
      return null;
  }
  
  
  /** getTemplate method
    * 
    * Using the unit the user inputted, generates a random value within a corersponding range to determine which 
    * template it will use for the question and its correspoding answer
    */
  private void getTemplate()
  {
    //Determines template based on which unit was input
    switch(unit)
    {
      //Kinematics
      case 0:
        //Questions 1 -3 are based on this equation, generate a number in that range to get a template
        template = gen.nextInt(2-0+1);
        break;
        
      //Work, Energy, and power
      case 1:
        //4-7 (lines 3-6) //Conservation of energy/power
        template = gen.nextInt(6-3+1)+3;
        break;
        
      //Newtons laws/Forces
      case 2:
        template = gen.nextInt(8-7+1)+6;
        break;
    }
  }
  
  
  /** getQuestionText method
    * 
    * Gets a template from a file based on the template number, fills in the template with the randomly generated values
    */
  private void getQuestionText()
  {
    try
    {
      //Gets the template wanted from the file containing question templates
      templateText = Files.readAllLines(Paths.get(templates.toString()), StandardCharsets.UTF_8).get(template); 
      
      //Split template text into tokens, use ~ as delimiter
      templateContents = new StringTokenizer(templateText,"~");
      
      //File succesfully found and accessed
      qFileFound = true;
    }catch(IOException ex)
    {
      //File not successfully obtained, this class cannot function
      qFileFound = false;
    }
    
    if(qFileFound)
    {
      while(templateContents.hasMoreTokens())
      {
        next = templateContents.nextToken();
        
        //Concat the the tokens from the template into the question string
        //If a part requiring a rand value reached, concat that instead of the next token
        if(next.equals("(numVal1)"))
          question.append(rv1);
        else if(next.equals("(numVal2)"))
          question.append(rv2);
        else if(next.equals("(numVal3)"))
          question.append(rv3);

        else 
          question.append(next);
      }
    }
    else
    {
      ErrorMessages.getMessage(1); //1 - question template file not found
    }
  }
  
  
  /** getAnswerText method
    * 
    * Gets a template from a file based on the template number, fills in the template with the randomly generated values
    * and answer
    */
  private void getAnswerText()
  {
    try
    {
      //Gets the template wanted from the file containing solution templates
      templateText = Files.readAllLines(Paths.get(answerTemplates.toString()), StandardCharsets.UTF_8).get(template);
      
      //Split template text into tokens, use ~ as delimiter
      templateContents = new StringTokenizer(templateText,"~");
      
      aFileFound = true;
    }catch(IOException ex)
    {
      //File not successfully obtained and accessed, this class cannot function
      aFileFound = false;
    }
    
    if(aFileFound)
    {
      //While the template has more tokens
      while(templateContents.hasMoreTokens())
      {
        next = templateContents.nextToken();
        
        //If a part requiring a rand value reached, concat that instead of the next token
        if(next.equals("(numVal1)"))
          solution.append(rv1);
        else if(next.equals("(numVal2)"))
          solution.append(rv2);
        else if(next.equals("(numVal3)"))
          solution.append(rv3);
        else if(next.equals("(rvD2)"))
          solution.append(rvD2);
        else if(next.equals("(ans)"))
          solution.append(answer);
        else if(next.equals("(numVal2)x2"))
         solution.append((rv2*2) + "");
        else if(next.equals("(numVal1)x(numVal2)"))
         solution.append((rv1*rv2) + "");
        else if(next.equals("(numVal1)+(numVal2)"))
          solution.append((rv1+rv2) + "");
        else if(next.equals("(numVal1)/(numVal2)"))
          solution.append((rv1/rv2) + "");
        else if(next.equals("(numVal1)^2"))
          solution.append(Math.pow(rv1,2) + "");
        else if(next.equals("(numVal2)^2"))
          solution.append(Math.pow(rv2,2) + "");
        else if(next.equals("6.67 x 10^-11 N m^2/kg^2x(numVal1)(numVal1)"))
          solution.append(((6.67e-11) * Math.pow(rv1, 2) + ""));
        else 
          solution.append(next);
      }
    }
    else
      ErrorMessages.getMessage(2); //2 - solution template file not found
  }
  
  
   /** determineSolution method
    * 
    * Determines the correct answer to the question based on which template was used
    */
  private void determineSolution()
  {
    switch(template)
    {
      case 0:
        //Vfinal = Vinitial + at
        answer = (rv2*2)+rv1;
        break;
      case 1:
        //Sum of all distances traveled
        //Dtotal = s1 + s2 + s3...
        answer = rv1+rv2+rv3;
        break;
      case 2:
        //Hypotenuse of triangle
        answer = Math.hypot(rv1+rv3,rv2); 
        break;
      case 3:
        //e = mgh
        answer = rv1 * rv2 * 9.8;
        break;
      case 4:
        //p = e/t
        answer = rvD1/rvD2;
        break;
      case 5:
        //e = mgh
        answer = rv1 * rv2 * 9.8;
        break;
      case 6:
        //e = mv^2/2
        answer = ((1*Math.pow(rv1,2))/2.0);
        break;
      case 7:
        //fg = Gm1m2/d^2
        rvD2 = rvD2/100; //Convert to m first
        answer = ((6.67e-11) * Math.pow(rv1, 2))/Math.pow(rvD2,2); 
        break;
      case 8:
        //fg = mg
        answer = rv1 * 1.6;
        break;
    }
  }
  
  
  /** getOtherAnswers method
    * 
    * Determines 3 other answers for the question, only used if the question is multiple choice
    */
  private void getOtherAnswers()
  {
    boolean addOrSub; //Used to determine whether to add or subtract a random value from the correct answer to get the next answer
    
    //Only runs if multiple choice question
    if(type == 2)
    {
      //Add the correct answer as the first in the collection
      answers.add(Double.toString(answer));
      
      //Add the rest of the answers, generated based on values subbed/added to the correct answer
      for(int i = 1;i<4;i++)
      {
        addOrSub = gen.nextBoolean();
        if(addOrSub == true)
          answers.add(i,Double.toString(answer + ((int)(Math.random() * 3 + 1))));
        else
          answers.add(i,Double.toString(answer - ((int)(Math.random() * 3 + 1))));
      }
    }
  }
  
  
  /** getRandomValues method
    * 
    * Determines 3 random values (from 0 - 100) for the question, creates ints and doubles so the value wanted 
    * can be accessed without having to convert later
    */
  private void getRandomValues()
  {
    rv1 = gen.nextInt(100);
    rvD1 = rv1;
    rv2 = gen.nextInt(100);
    rvD2 = rv2;
    rv3 = gen.nextInt(100);
    rvD3 = rv3;
  }
}