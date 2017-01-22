import java.util.StringTokenizer;
/** The Custom Solution Class
  * The class that sets input from text field to proper variables
  * Once variables are found uses answerGenerator class to solve for answer
  * @author Michael Chen
  * @since 01/17/2015
  * */
public class customSolutionClass
{
  
  private double time = -1.2958; //time 
  private double force = -1.2958; //as long as its not this number, it will keep running (:
  private double velocityAvg = -1.2958;
  private double velocity1 = -1.2958; //velocity of object 1
  private double velocity2 = -1.2958; //velocity of object 2
  private double velocity3 = -1.2958; //velocity of object 3
  private double velocity4 = -1.2958; //velocity of object 4
  private String v1Dir = ""; //direction of velocity1
  private String v2Dir = ""; //direction of velocity2
  private String v3Dir = ""; //direction of velocity3
  private String v4Dir = ""; //direction of velocity4
  private double displacement = -1.2958; //displacement
  private String displaceDir = "";
  private double distance = -1.2958;
  private double mass = -1.2958; //mass of object 1
  private double mass2 = -1.2958;//mass of object 2
  private double acc = -1.2958;//acceleration
  private String goal = "";
  private String q; //question
  private StringTokenizer qToken; //question inside string tokenizer
  
  public double answer;
  public String answerString = ""; //explanation of answer
  
  private SolutionGenerator generator;
  
  /**Constructor*/
  public customSolutionClass()
  {
   this.q = "";
   this.qToken = new StringTokenizer(this.q);
  }
  
  /** getQuestion method
    * gets question from textarea and assigns it to stringtokenizer
    * @param qs the question from textarea
    */
  public void getQuestion(String qs)
  {
    this.q = qs;
    this.qToken = new StringTokenizer(this.q);
  }
  
  /** checkToken method
    * check tokens from stringtokenizer to find values
    */
  public double checkToken(StringTokenizer q)
  {
    String z = this.qToken.nextToken();
    String testChar = Character.toString(z.charAt(0));
    double test = getValue(isNumeric(testChar),z);
    return test;
  }
  
  /** removeQuestion method
    * gets values from stringtokenizer
    */
  public void getValues()
  {
    //keeps running as long as there are tokens
    while(this.qToken.hasMoreTokens())
    {
      String x = this.qToken.nextToken();//gets nextToken
      
      if(x.equals("force") || x.equals("Force")) //if question contains word force, it will get next number and assign it to force variable
      {
        while(force == -1.2958)
        {
          this.force = checkToken(this.qToken);
        }
      }
      else if(x.equals("mass") || x.equals("Mass")) //takes number variable for mass
      {
        while(mass == -1.2958)
        {
          this.mass = checkToken(this.qToken);
        }
      }
      else if(x.equals("mass2") || x.equals("Mass2")) //takes number variable for mass
      {
        while(mass2 == -1.2958)
        {
          this.mass2 = checkToken(this.qToken);
        }
      }
      else if(x.equals("velocity") || x.equals("Velocity")) //takes number variable for average velocity
      {
        while(velocityAvg == -1.2958)
        {
          this.velocityAvg = checkToken(this.qToken);
        }
      }
      else if(x.equals("velocity1") || x.equals("Velocity1")) //takes number variable velocity1
      {
        while(velocity1 == -1.2958)
        {
          this.velocity1 = checkToken(this.qToken);
        }
      }
      else if(x.equals("velocity2") || x.equals("Velocity2")) //takes number variable velocity2
      {
        while(velocity2 == -1.2958)
        {
          this.velocity2 = checkToken(this.qToken);
        }
      }
      else if(x.equals("displacement") || x.equals("Displacement")) //takes number variable for displacement
      {
        while(displacement == -1.2958)
        {
          this.displacement = checkToken(this.qToken);
        }
      }
      else if(x.equals("time") || x.equals("Time")) //takes number variable for time
      {
        while(time == -1.2958)
        {
          this.time = checkToken(this.qToken);
        }
      }
      else if(x.equals("acceleration") || x.equals("Acceleration") || x.equals("Accelerates") || x.equals("accelerates")) //takes number variable for acceleration
      {
        while(acc == -1.2958)
        {
          this.acc = checkToken(this.qToken);
        }
      }
      else if(x.substring(0,1).equals("@")) //missing variable
      {
        this.goal = x;
      }
      else
      {
      }
    }
    generator = new SolutionGenerator();
    setValues(this.goal,this.time, this.force, this.velocityAvg, this.velocity1, this.velocity2, this.displacement, 
              this.displaceDir, this.distance, this.mass, this.acc, generator); //use values taken from this class and places it as arguments in generator
    generator.findSolution();
    System.out.println(generator.getAnswer());
    System.out.println(generator.getExplain());
  }
  
  /** setValues method
    * sets all values into the generator
    * @param ...all the variables in this class that relate to physics (:
    */
  public void setValues(String goal, double time, double force, double velocity, double velocity1, double velocity2,  
                        double displacement, String displaceDir, double distance, double mass, double acc, SolutionGenerator generator)
  {
    generator.setGoal(goal);
    generator.setMass(mass);
    generator.setTime(time);
    generator.setForce(force);
    generator.setVelocityAvg(velocity);
    generator.setVelocity1(velocity1);
    generator.setVelocity2(velocity2);
    generator.setDisplacement(displacement);
    generator.setDistance(distance);
    generator.setAcc(acc); 
  }
  
  /** getAnswer method
    * returns explaintion of answer
    */
  public String getAnswer()
  {
    return this.generator.getExplain();
  }

  /** isNumeric method
    * checks if the first letter of a word is a number, returns true if yes, false if no
    * @param str checks string contains a number,
    */
  public boolean isNumeric(String str) 
  {  
    try  
    {  
      double d = Double.parseDouble(str);  
    }  
    catch(NumberFormatException nfe)  
    {  
      return false;  
    }  
    return true;  
  }
  
  /** getValue method
    * gets full number from a word
    * 
    * @param tf if first letter contains a number, it will get the entire number of the word, else do nothing
    */
  public double getValue(boolean tf, String z) //method for finding value 
  {
    double mass = -1.2958;
    if(tf == true)
    {
      StringBuffer buffer = new StringBuffer("");
      buffer = looper(z);
      mass = getUnit(z,buffer);
    }
    return mass;
  }
  
  /** looper method
    * loop that gets entire number of a word
    * 
    * @param z the word to get all numbers from
    */
  public StringBuffer looper(String z) //finds numbers in string buffer
  {
    StringBuffer buffer = new StringBuffer("");
    int counter = 0;
    for(int g = 0; g<z.length(); g++)
    {
      String test = Character.toString(z.charAt(g));
      if(isNumeric(test) == true)
      {
        buffer.append(test);
      }
      else if(test.equals("."))
      {
        if(counter<1) //only takes one decimal, since you cannot have a number with multiple decimals
        {
          buffer.append(".");
          counter++;
        }
      }
      else
        break;
    }
    return buffer;
  }
  
  /** getUnit method
    * changes numbers depending on the unit
    * @param z the word that contains numbers and units, buffer the buffer object that contains all numbers
    */
  public static double getUnit(String z, StringBuffer buffer)
  {
    double unit = -1.2958;
    
    //without periods
    if((z.charAt(z.length()-1) == 'g' && z.charAt(z.length()-2) == 'k') || ((z.charAt(z.length()-1) == 'm' && (z.charAt(z.length()-2) != 'k')))|| (z.charAt(z.length()-1) == 's') || (z.charAt(z.length()-1) == 's' && z.charAt(z.length()-2) == '/' && z.charAt(z.length()-3) == 'm')) //standard units without conversion, kg, m, m/s^2 and s
    {
      String p = buffer.toString();
      unit = Double.parseDouble(p);
    }
    if(z.charAt(z.length()-1) == 'g' && z.charAt(z.length()-2) != 'k')//if its grams
    {
      String p = buffer.toString();
      unit = Double.parseDouble(p)/1000;
    }
    if(z.charAt(z.length()-1) == 'm' && z.charAt(z.length()-2) == 'k')//if its kilometers
    {
      String p = buffer.toString();
      unit = Double.parseDouble(p)/1000;
    }
    if(z.charAt(z.length()-1) == 'N' || z.charAt(z.length()-1) == 'n' || z.charAt(z.length()-2) == 'N')//neutons
    {
      String p = buffer.toString();
      unit = Double.parseDouble(p);
    }
    if(z.charAt(z.length()-1) == 'h' && z.charAt(z.length()-2) == '/' && z.charAt(z.length()-3) == 'm' && z.charAt(z.length()-4) == 'k')//velocity in km/hr
    {
      String p = buffer.toString();
      unit = Double.parseDouble(p)/3.6;
    }
    if(z.charAt(z.length()-1) == '2' && z.charAt(z.length()-2) == '^' && z.charAt(z.length()-3) == 's' && z.charAt(z.length()-4) == '/' && z.charAt(z.length()-5) == 'm') //m/s^2
    {
      String p = buffer.toString();
      unit = Double.parseDouble(p);
    }
    if(z.charAt(z.length()-1) == 'r' && z.charAt(z.length()-2) == 'h') //if its hours
    {
      String p = buffer.toString();
      unit = Double.parseDouble(p) / 3600;
    }
    
    //with periods, commas, or ! mark at the end
    if(z.charAt(z.length()-1) == '.' || z.charAt(z.length()-1) == ',' ||  z.charAt(z.length()-1) == '!')
    {
      if((z.charAt(z.length()-2) == 'g' && z.charAt(z.length()-3) == 'k') || (z.charAt(z.length()-2) == 'm' && (z.charAt(z.length()-3) != 'k'))|| (z.charAt(z.length()-2) == 's') || (z.charAt(z.length()-2) == 's' && z.charAt(z.length()-3) == '/' && z.charAt(z.length()-4) == 'm')) //standard units without conversion, kg, m
      {
        String p = buffer.toString();
        unit = Double.parseDouble(p);
      }
      if(z.charAt(z.length()-2) == 'g' && z.charAt(z.length()-3) != 'k')//if its grams with period
      {
        String p = buffer.toString();
        unit = Double.parseDouble(p)/1000;
      }
      if(z.charAt(z.length()-2) == 'h' && z.charAt(z.length()-3) == '/' && z.charAt(z.length()-4) == 'm' && z.charAt(z.length()-5) == 'k')//if its km/hr with period
      {
        String p = buffer.toString();
        unit = Double.parseDouble(p)/3.6;
      }
      
      if(z.charAt(z.length()-2) == '2' && z.charAt(z.length()-3) == '^' && z.charAt(z.length()-4) == 's' 
           && z.charAt(z.length()-5) == '/' && z.charAt(z.length()-6) == 'm')//if its m/s^2 with period
      {
        String p = buffer.toString();
        unit = Double.parseDouble(p);
      }
      if(z.charAt(z.length()-2) == 'N' || z.charAt(z.length()-2) == 'n')// if its neutons with period
      {
        String p = buffer.toString();
        unit = Double.parseDouble(p);
      }
      if(z.charAt(z.length()-2) == 'r' && z.charAt(z.length()-3) == 'h') //if its hours with period
      {
        String p = buffer.toString();
        unit = Double.parseDouble(p) / 3600;
      }
    }
    return unit;
  }
}
  
