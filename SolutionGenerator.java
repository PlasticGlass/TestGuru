/** Answer Generator Class
  * The class containing all methods to solve physics equations
  * Allows variables to be inputted and solve for answer
  * @author Michael Chen
  * @since 15/01/2015
  * */
public class SolutionGenerator
{
  private String goal = "";
  private double time = -1.2958; //time 
  private double force = -1.2958; //as long as its not this number, it will keep running (:
  private double velocityAvg = -1.2958; //velocity without direction...
  private double velocity1 = -1.2958; //velocity of object 1
  private double velocity2 = -1.2958; //velocity of object 2
  private double displacement = -1.2958; //displacement
  private double distance = -1.2958;
  private double mass = -1.2958; //mass of object 1
  private double mass2 = -1.2958;//mass of object 2
  private double acc = -1.2958;//acceleration
  private double answer = -1.2958;
  private double answer2 = -1.2958; //for quadratic questions there are 2 answers
  private String explain = "";
  
  /**Constructor*/
  public SolutionGenerator()
  {
    
  }
  
  /** setGoal method
    * sets the variable to find from a question
    * @param goal, the variable that question is looking for
    */
  public void setGoal(String goal1)
  {
    this.goal = goal1;
  }
  
  /** setTime method
    * sets the time variable to from a question
    * @param time1, the time from a question
    */
  public void setTime(double time1)
  {
    this.time = time1;
  }
  
  /** setForce method
    * sets the force variable to from a question
    * @param force1, the force from a question
    */
  public void setForce(double force1)
  {
    this.force = force1;
  }
  
  /** setVelocity method
    * sets the average velocity variable from a question
    * @param v, the average velocity from a question
    */
  public void setVelocityAvg(double v)
  {
    this.velocityAvg = v;
  }
  
  /** setVelocity1 method
    * sets the velocity1 (beginning) variable from a question
    * @param v1, v1 from a question
    */
  public void setVelocity1(double v1)
  {
    this.velocity1 = v1;
  }
  
   /** setVelocity2 method
    * sets the velocity2 (final) variable from a question
    * @param v2, v2 from a question
    */
  public void setVelocity2(double v2)
  {
    this.velocity2 = v2;
  }
  
   /** setDisplacement method
    * sets the displacement variable from a question
    * @param displacement1, displacement from a question
    */
  public void setDisplacement(double displacement1)
  {
    this.displacement = displacement1;
  }
  
   /** setDistance method
    * sets the distance variable from a question
    * @param distance1, distance1 from a question
    */
  public void setDistance(double distance1)
  {
    this.distance = distance1;
  }
  
  /** setMass method
    * sets mass variable 
    * @param mass1 the mass to be added
    */
  public void setMass(double mass1)
  {
    this.mass = mass1;
  }
  
  /** setMass2 method
    * sets mass2 variable 
    * @param mass2 the mass to be added
    */
  public void setMass2(double mass2)
  {
    this.mass2 = mass2;
  }
  
  /** setAcc method
    * sets acceleration variable 
    * @param acc1 the acceleration to be added
    */
  public void setAcc(double acc1)
  {
    this.acc = acc1;
  }
  
  /** findSolution method
    * uses the variables given to calculate and find a solution
    */
  public void findSolution()
  {
    //F = ma solving for F
    if(this.mass != -1.2958 && this.acc != -1.2958) //if mass and acceleration are given
    {
      if(this.goal.equals("@force") || this.goal.equals("@force?")) //if missing variable is force
      {
        this.answer = this.mass * this.acc;
        this.explain = "Since you have the mass: " + this.mass + " and acceleration: " + this.acc 
          + ", you can use the formula F = ma to calculate the force. \n " + "F = " + this.mass + " * "
          +  this.acc + "\n F = " + this.answer + "N";
      }
    }
    
    //F = ma solving for acceleration
    if(this.mass != -1.2958 && this.force != -1.2958) //if mass and force are given
    {
      if(this.goal.equals("@acceleration") || this.goal.equals("@acceleration?")) //if missing variable is acceleration
      {
        {
          this.answer = this.force / this.mass;
          this.explain = "Since you have the mass: " + this.mass + " and force: " + this.force 
          + ", you can use the formula a = F/m to calculate the force. \n " + "a = " 
          + this.force + " / " +  this.mass + "\n a = " + this.answer + "m/s^2";
        }
      }
    }
    
    //F = ma solving for mass
    if(this.force != -1.2958 && this.acc != -1.2958) //if force and acceleration are given
    {
      if(this.goal.equals("@mass") || this.goal.equals("@mass?")) //if missing variable is mass
      {
        this.answer = this.force / this.acc;
        this.explain = "Since you have the force: " + this.force + " and acceleration: " + this.acc 
          + ", you can use the formula m = F/a to calculate the force. \n " + "m = " 
          + this.force + " / " +  this.acc + "\n m = " + this.answer + "kg";
      }
    }
    
    //v avg = v1+v2/2 solving for v avg
    if(this.velocity1 != -1.2958 && this.velocity2 != -1.2958) //if v1 and v2 are given
    {
      if(this.goal.equals("@averagevelocity") || this.goal.equals("@averagevelocity?")) //if missing variable is avg v
      {
        this.answer = (this.velocity1+this.velocity2) / 2;
        this.explain = "Since you have v1: " + this.velocity1 + " and v2: " + this.velocity2 
          + ", you can use the formula v avg = (v2+v1)/2 to calculate the average velocity. \n " + "v avg = (" 
          + this.velocity1 + " + " + this.velocity2 + ") / 2" + "\n v avg = " + this.answer + "m/s";
      }
    }
    
    //v avg = v1+v2/2 solving for v1
    if(this.velocityAvg != -1.2958 && this.velocity2 != -1.2958) //if avg v and v2 are given
    {
      if(this.goal.equals("@velocity1") || this.goal.equals("@velocity1?")) //if missing variable is v1
      {
        this.answer = (this.velocityAvg*2) - this.velocity2;
        this.explain = "Since you have the average velocity: " + this.velocityAvg + " and v2: " + this.velocity2 
          + ", you can use the formula v1 = (v avg * 2) - v2 to calculate v1. \n " + "v1 = (" 
          + this.velocityAvg + " * 2) - " + this.velocity2 + "\n v1 = " + this.answer + "m/s";
      }
    }
    
    //v avg = v1+v2/2 solving for v2
    if(this.velocityAvg != -1.2958 && this.velocity1 != -1.2958) //if avg v and v1 are given
    {
      if(this.goal.equals("@velocity2") || this.goal.equals("@velocity2?")) //if missing variable is v2
      {
        this.answer = (this.velocityAvg*2) - this.velocity1;
        this.explain = "Since you have the average velocity: " + this.velocityAvg + " and v1: " + this.velocity1 
          + ", you can use the formula v2 = (v avg * 2) - v1 to calculate v2. \n " + "v2 = (" 
          + this.velocityAvg + " * 2) - " + this.velocity1 + "\n v2 = " + this.answer + "m/s";
      }
    }
    
    //v = d/t solving for v avg
    if(this.displacement != -1.2958 && this.time != -1.2958) //if displacement and time are given
    {
      if(this.goal.equals("@averagevelocity") || this.goal.equals("@averagevelocity?"))//if missing variable is avg v
      {
        this.answer = this.displacement / this.time;
        this.explain = "Since you have the displacement: " + this.displacement + " and time: " + this.time 
          + ", you can use the formula v = d/t to calculate the average velocity. \n " + "v = " + this.displacement 
          + " / " + this.time + "\n v = " + this.answer + "m/s";
      }
    }
    
    //v = d/t solving for time
    if(this.displacement != -1.2958 && this.velocityAvg != -1.2958) //if displacement and avg v are given
    {
      if(this.goal.equals("@time") || this.goal.equals("@time?")) //if missing variable is time
      {
        this.answer = this.displacement / this.velocityAvg;
        this.explain = "Since you have the displacement: " + this.displacement + " and velocity: " 
          + this.velocityAvg + ", you can use the formula t = d/v to calculate the time. \n " + "t = " 
          + this.displacement + " / " + this.velocityAvg + "\n t = " + this.answer + "s";
      }
    }
    
    //v = d/t solving for displacement
    if(this.time != -1.2958 && this.velocityAvg != -1.2958) // if time and avg v are given
    {
      if(this.goal.equals("@displacement") || this.goal.equals("@displacement?")) //if missing variable is displacement
      {
        this.answer = this.velocityAvg / this.time;
        this.explain = "Since you have the time: " + this.time + " and velocity: " 
          + this.velocityAvg + ", you can use the formula d = v/t to calculate the displacement. \n " + "d = " 
          + this.velocityAvg + " / " + this.time + "\n d = " + this.answer + "m";
      }
    }
    
    //a = v2-v1/t solving for average acc
    if(this.velocity1 != -1.2958 && this.velocity2 != -1.2958 && this.time != -1.2958) //if v1, v2, time are given
    {
      if(this.goal.equals("@averageacceleration") || this.goal.equals("@averageacceleration?"))//if missing variable is avg acc
      {
        this.answer = (this.velocity2 - this.velocity1) / this.time;
        this.explain = "Since you have v1: " + this.velocity1 + " and v2: " + this.velocity2 + " and time: " + this.time
          + ", you can use the equation a = (v2-v1) / t to calculate the average acceleration. \n" + "a = (" + this.velocity2
          + " - " + this.velocity1 + ") / " + this.time + "\n a = " + this.answer + "m/s^2";
      }
    }
    
    //a = v2-v1/t solving for average time
    if(this.velocity2 != -1.2958 && this.acc != -1.2958 && this.velocity1 != -1.2958) //if v1, v2, acc are given
    {
      if(this.goal.equals("@time") || this.goal.equals("@time?"))//if missing variable is time
      {
        this.answer = (this.velocity2 - this.velocity1) / this.acc;
        this.explain = "Since you have v1: " + this.velocity1 + " and v2: " + this.velocity2 + " and acceleration: " + this.acc
          + ", you can use the equation t = (v2-v1) / a to calculate the time. \n" + "t = (" + this.velocity2
          + " - " + this.velocity1 + ") / " + this.acc + "\n t = " + this.answer + "s";
      }
    }
    
    //a = v2-v1/t solving for v2
    if(this.velocity1 != -1.2958 && this.acc != -1.2958 && this.time != -1.2958) //if v1, acc, time are given
    {
      if(this.goal.equals("@velocity2") || this.goal.equals("@velocity2?")) //if missing variable is v2
      {
        this.answer = (this.time * this.acc) + this.velocity1;
        this.explain = "Since you have v1: " + this.velocity1 + " and acceleration: " + this.acc + " and time: " + this.time
          + ", you can use the equation v2 = a*t + v1 to calculate v2. \n" + "v2 = " + this.acc
          + " * " + this.time + " + " + this.velocity1 + "\n v2 = " + this.answer + "m/s";
      }
    }
    
    //a = v2-v1/t solving for v1
    if(this.velocity2 != -1.2958 && this.acc != -1.2958 && this.time != -1.2958)//if v2, acc, time are given
    {
      if(this.goal.equals("@velocity1") || this.goal.equals("@velocity1?"))//if missing variable is v1
      {
        this.answer = this.velocity2 - (this.acc*this.time);
        this.explain = "Since you have v2: " + this.velocity2 + " and acceleration: " + this.acc + " and time: " + this.time
          + ", you can use the equation v1 = v2 - (a*t) to calculate v1. \n" + "v1 = " + this.velocity2
          + " - (" + this.acc + " * " + this.time + ")" +  "\n v1 = " + this.answer;
      }
    }
    
    //v2 = v1 + at solving for acceleration
    if(this.velocity1 != -1.2958 && this.velocity2 != -1.2958 && this.time != -1.2958)//if v1, v2, time are given 
    {
      if(this.goal.equals("@acceleration") || this.goal.equals("@acceleration?"))//if missing varibale is acc
      {
        this.answer = (this.velocity2 - this.velocity1)/this.time;
        this.explain = "Since you have v1: " + this.velocity1 + " and v2: " + this.velocity2 + " and time: " 
          + this.time + ", you can use the formula a = (v2-v1)/t to calculate the average acceleration. \n " 
          + "a = (" + this.velocity2 + " - " +  this.velocity1 + ") / " + this.time + "\n a = " + this.answer + "m/s^2";
      }
    }
    
    //v2 = v1 + at solving for time
    if(this.velocity1 != -1.2958 && this.velocity2 != -1.2958 && this.acc != -1.2958)//if v1, v2, acc are given
    {
      if(this.goal.equals("@time") || this.goal.equals("@time?"))//if missing variable is time
      {
        this.answer = (this.velocity2 - this.velocity1)/this.acc;
        this.explain = "Since you have v1: " + this.velocity1 + " and v2: " + this.velocity2 + " and the average acceleration: " 
          + this.acc + ", you can use the formula t = (v2-v1)/a to calculate the time. \n " + "t = (" + this.velocity2 + " - " 
          +  this.velocity1 + ") / " + this.acc + "\n t = " + this.answer + "s";
      }
    }
    
    //v2 = v1 + at solving for v2
    if(this.velocity1 != -1.2958 && this.time != -1.2958 && this.acc != -1.2958) //if v1, time, acc are given
    {
      if(this.goal.equals("@velocity2") || this.goal.equals("@velocity2?"))//if missing variable is v2
      {
        this.answer = (this.acc*this.time) + this.velocity1;// / this.time);
        this.explain = "Since you have v1: " + this.velocity1 + " and time: " + this.time + " and the average acceleration: " 
          + this.acc + ", you can use the formula v2 = (a*t) + v1 to calculate v2. \n " + "v2 = (" + this.acc + " * " 
          +  this.time + ") + " + this.velocity1 + "\n v2 = " + this.answer + "m/s";
      }
    }
    
    //v2 = v1 + at solving for v1
    if(this.velocity2 != -1.2958 && this.time != -1.2958 && this.acc != -1.2958) //if v2, time, acc are given
    {
      if(this.goal.equals("@velocity1") || this.goal.equals("@velocity1?")) //if missing variable is v1
      {
        this.answer = this.velocity2 - (this.acc*this.time);
        this.explain = "Since you have v2: " + this.velocity2 + " and time: " + this.time + " and the average acceleration: " 
          + this.acc + ", you can use the formula v1 = v2 - (a*t) to calculate v1. \n " + "v1 = " + this.velocity2 + " - (" 
          +  this.acc + " * " + this.time + ")" + "\n v1 = " + this.answer + "m/s";
      }
    }
    
    //d = v1t + .5at^2 solving for displacement
    if(this.velocity1 != -1.2958 && this.time != -1.2958 && this.acc != -1.2958) //if v1, time, acc are given
    {
      if(this.goal.equals("@displacement") || this.goal.equals("@displacement?")) //if missing variable is displacement
      {
        this.answer = (this.velocity1*this.time) + (.5*this.acc*(this.time*this.time));
        this.explain = "Since you have v1: " + this.velocity1 + " and time: " + this.time + " and the acceleration: " 
          + this.acc + ", you can use the formula d = v1*t + (.5*a*(t^2)) to calculate displacement. \n " + "d = " 
          + this.velocity1 + " * " +  this.time + " + (0.5 * " + this.acc + " * " + this.time + "^2)" + "\n d = " + this.answer + "m";
      }
    }
    
    //d = v1t + .5at^2 solving for v1
    if(this.displacement != -1.2958 && this.time != -1.2958 && this.acc != -1.2958)//if displacement, time, acc are given
    {
      if(this.goal.equals("@velocity1") || this.goal.equals("@velocity1?"))//if missing varaible is v1
      {
        this.answer = (this.displacement - (.5*this.acc*(this.time*this.time)))/this.time;
        this.explain = "Since you have the displacement: " + this.displacement + " and time: " + this.time + " and the acceleration: " 
          + this.acc + ", you can use the formula v1 = (d - (.5 * a * (t^2)))/t to calculate velocity1. \n " + "v1 = (" 
          + this.displacement + " - (0.5 * " +  this.acc + " * (" + this.time + "^2))) / " + this.time + "\n v1 = " + this.answer + "m/s";
      }
    }
    
    //d = v1t + .5at^2 solving for acceleration
    if(this.displacement != -1.2958 && this.time != -1.2958 && this.velocity1 != -1.2958)//if displacement, time, v1 are given
    {
      if(this.goal.equals("@acceleration") || this.goal.equals("@acceleration?"))//if missing variable is acc
      {
        this.answer = (2*((this.displacement) - (this.velocity1 * this.time))) /  (this.time*this.time);
        this.explain = "Since you have the displacement: " + this.displacement + " and time: " + this.time + " and v1: " 
          + this.velocity1 + ", you can use the formula a = ((2(d - v1 * t))) / t^2 to calculate acceleration. \n " + "a = ((2 * " 
          + this.displacement + " - " +  this.velocity1 + " * " + this.time + ")) / " + this.time + "^2" + "\n a = " + this.answer + "m/s^2";
      }
    }
    
    //d = v1t + .5at^2 solving for time
    if(this.displacement != -1.2958 && this.velocity1 != -1.2958 && this.acc != -1.2958)//if displacement, v1, acc are given
    {
      if(this.goal.equals("@time") || this.goal.equals("@time?"))//if time is missing variable
      {
        double temp1 = Math.sqrt(this.velocity1 * this.velocity1 - 4 * (.5*this.acc) * this.displacement);
        this.answer = ((-1 * this.velocity1) + temp1)/(this.acc); 
        this.answer2 = ((-1 * this.velocity1) - temp1)/(this.acc);  
        this.explain = "Since you have the displacement: " + this.displacement + " and v1: " + this.velocity1 
          + " and the acceleration: " + this.acc + ", you can use the quadratic formula to calculate velocity1. \n " 
          + "t = -" + this.velocity1 + " +/- " + "\u221A(" + this.velocity1 + "^2) - 4 * 0.5 * " + this.acc + " * " 
          + this.displacement + " /2 * (0.5) * " + this.acc + "\n t = " + this.answer + "s or " + this.answer2 + "s";
      } 
    }
    
    //d = v2t - .5at^2 solving for displacement
    if(this.velocity2 != -1.2958 && this.time != -1.2958 && this.acc != -1.2958)//if v2, timem, acc are given
    {
      if(this.goal.equals("@displacement") || this.goal.equals("@displacement?"))//if displacement is missing variable
      {
        this.answer = (this.velocity2*this.time) - (.5*this.acc*(this.time*this.time));
        this.explain = "Since you have v2: " + this.velocity2 + " and time: " + this.time + " and the acceleration: " 
          + this.acc + ", you can use the formula d = v2*t - (.5*a*(t^2)) to calculate displacement. \n " + "d = " 
          + this.velocity2 + " * " +  this.time + " - (0.5 * " + this.acc + " * " + this.time + "^2)" + "\n d = " + this.answer + "m";
      }
    }
    
    //d = v2t - .5at^2 solving for v2
    if(this.displacement != -1.2958 && this.time != -1.2958 && this.acc != -1.2958)//if displacement, time, acc are given
    {
      if(this.goal.equals("@velocity2") || this.goal.equals("@velocity2?"))//if missing variable is v2
      {
        this.answer = (this.displacement + (.5*this.acc*(this.time*this.time)))/this.time;
        this.explain = "Since you have the displacement: " + this.displacement + " and time: " + this.time + " and the acceleration: " 
          + this.acc + ", you can use the formula v2 = (d + (.5 * a * (t^2)))/t to calculate velocity2. \n " + "v2 = (" 
          + this.displacement + " + (0.5 * " +  this.acc + " * (" + this.time + "^2))) / " + this.time + "\n v2 = " + this.answer + "m/s";
      }
    }
    
    //d = v2t - .5at^2 solving for acceleration
    if(this.displacement != -1.2958 && this.time != -1.2958 && this.velocity2 != -1.2958)//if displacement, time, v2 are given
    {
      if(this.goal.equals("@acceleration") || this.goal.equals("@acceleration?"))//if missing variable is acc
      {
        this.answer = (-2*((this.displacement) - (this.velocity2 * this.time))) /  (this.time*this.time);
        this.explain = "Since you have the displacement: " + this.displacement + " and time: " + this.time + " and v2: " 
          + this.velocity2 + ", you can use the formula a = ((-2(d - v2 * t))) / t^2 to calculate acceleration. \n " + "a = ((-2 * " 
          + this.displacement + " - " +  this.velocity2 + " * " + this.time + ")) / " + this.time + "^2" + "\n a = " + this.answer + "m/s^2";
      }
    }
    
    //d = v2t - .5at^2 solving for time
    if(this.displacement != -1.2958 && this.velocity2 != -1.2958 && this.acc != -1.2958)//if displacement, v2, acc are given
    {
      if(this.goal.equals("@time") || this.goal.equals("@time?"))//if missing variable is time
      {
        double temp1 = Math.sqrt(this.velocity2 * this.velocity2 - 4 * ((-1*.5)*this.acc) * this.displacement);
        this.answer = ((-1 * this.velocity2) + temp1)/(-1*this.acc); 
        this.answer2 = ((-1 * this.velocity2) - temp1)/(-1*this.acc);  
        this.explain = "Since you have the displacement: " + this.displacement + " and v2: " + this.velocity2 
          + " and the acceleration: " + this.acc + ", you can use the quadratic formula to calculate time. \n " 
          + "t = -" + this.velocity2 + " +/- " + "\u221A(" + this.velocity2 + "^2) - 4 * (-1*0.5) * " + this.acc + " * " 
          + this.displacement + " /2 * (-1*0.5) * " + this.acc + "\n t = " + this.answer + "s or " + this.answer2 + "s";
      } 
    }
    
    //d=((v1+v2)*t)/2 solving for displacement
    if(this.velocity1 != -1.2958 && this.velocity2 != -1.2958 && this.time != -1.2958)//if v1, v2, time are given
    {
      if(this.goal.equals("@displacement") || this.goal.equals("@displacement?"))//if missing variable is displacement
      {
        this.answer = ((this.velocity1 + this.velocity2) * this.time)/2;
        this.explain = "Since you have v1: " + this.velocity1 + " and v2: " 
          + this.velocity2 + "and time: " +this.time + ", you can use the formula  d=((v1+v2)*t)/2 to calculate the displacement. \n " 
          + "d = ((" + this.velocity1 + "+" +  this.velocity2 +") * " + this.time + ") / 2 " + "\n d = " + this.answer + "m"; 
      }
    }
    
    //d=((v1+v2)*t)/2 solving for v1
    if(this.velocity2 != -1.2958 && this.displacement != -1.2958 && this.time != -1.2958)//if v2, displacement, time are given
    {
      if(this.goal.equals("@velocity1") || this.goal.equals("@velocity1?"))//if missing variable is v1
      {
        this.answer = ((this.displacement * 2) / this.time) - this.velocity2;
        this.explain = "Since you have displacement: " + this.displacement + " and v2: " 
          + this.velocity2 + "and time: " + this.time + ", you can use the formula  v1 = ((d*2)/t)-v2  to calculate v1. \n " 
          + "v1 = (("+this.displacement + " * 2)/" +  this.time +") - " + this.velocity2 + "\n v1 = " + this.answer + "m/s"; 
      }
    }
    
    //d=((v1+v2)*t)/2 solving for v2
    if(this.velocity1 != -1.2958 && this.displacement != -1.2958 && this.time != -1.2958)//if v1, displacement, time are given
    {
      if(this.goal.equals("@velocity2") || this.goal.equals("@velocity2?"))//if missing variable is v2
      {
        this.answer = ((this.displacement * 2) / this.time) - this.velocity1;
        this.explain = "Since you have displacement: " + this.displacement + " and v1: " 
          + this.velocity1 + "and time: " + this.time + ", you can use the formula  v2 = ((d*2)/t)-v1  to calculate v2. \n " 
          + "v2 = (("+this.displacement + " * 2)/" +  this.time +") - " + this.velocity1 + "\n v2 = " + this.answer + "m/s"; 
      }
    }
    
    //d=((v1+v2)*t)/2 solving for time
    if(this.velocity1 != -1.2958 && this.displacement != -1.2958 && this.velocity2 != -1.2958)//if v1, displacement, v2 are given
    {
      if(this.goal.equals("@time") || this.goal.equals("@time?"))//if missing variable is time
      {
        this.answer = ((this.displacement * 2) / (this.velocity2 + this.velocity1));
        this.explain = "Since you have displacement: " + this.displacement + " and v1: " 
          + this.velocity1 + "and v2: " + this.velocity2 + ", you can use the formula  t = ((d*2))/(v1+v2)  to calculate t. \n " 
          + "t = (("+this.displacement + " * 2) / (" +  this.velocity2 + " + " + this.velocity1 + ")" + "\n t = " + this.answer + "s"; 
      }
    }
    
    //v2^2=v1^2+2*a*d solving for v2
    if(this.velocity1 != -1.2958 && this.acc != -1.2958 && this.displacement != -1.2958)//if v1, acc, displacement
    {
      if(this.goal.equals("@velocity2") || this.goal.equals("@velocity2?"))//if missing variable is v2
      {
        this.answer = Math.sqrt((this.velocity1 * this.velocity1) + (2*this.acc*this.displacement));
        this.explain = "Since you have v1: " + this.velocity1 + " and acceleration: " + this.acc + " and displacement: "
          + this.displacement + " you can use the formula v2^2=v1^2+2*a*d to calculate v2." + "\n v2 = sqrt(" + this.velocity1
          + "^2 + 2 * " + this.acc + " * " + this.displacement + "\n v2 = " + this.answer + "m/s";
      }
    }
    
    //v2^2=v1^2+2*a*d solving for v1
    if(this.velocity2 != -1.2958 && this.acc != -1.2958 && this.displacement != -1.2958)//if v2, acc, displacement are given
    {
      if(this.goal.equals("@velocity1") || this.goal.equals("@velocity1?"))//if missing variable is v1
      {
        this.answer = Math.sqrt((this.velocity2 * this.velocity2) - (2*this.acc*this.displacement));
        this.explain = "Since you have v2: " + this.velocity2 + " and acceleration: " + this.acc + " and displacement: "
          + this.displacement + " you can use the formula v1^2=v1^2-2*a*d to calculate v1." + "\n v1 = sqrt(" + this.velocity2
          + "^2 - 2 * " + this.acc + " * " + this.displacement + "\n v1 = " + this.answer + "m/s";
      }
    }
    
    //v2^2=v1^2+2*a*d solving for acceleration
    if(this.velocity2 != -1.2958 && this.velocity1 != -1.2958 && this.displacement != -1.2958)//if v2, v1, displacement are given
    {
      if(this.goal.equals("@acceleration") || this.goal.equals("@acceleration?"))//if missing variable is acc
      {
        this.answer = ((this.velocity2 * this.velocity2) - (this.velocity1 * this.velocity1)) / (2*this.displacement);
        this.explain = "Since you have v2: " + this.velocity2 + " and v1: " + this.velocity1 + " and displacement: "
          + this.displacement + " you can use the formula a = (v2^2 - v1^2) / 2*d" + "\n a = (" + this.velocity2
          + "^2 - " + this.velocity1 + "^2) / (2 * " + this.displacement + ")" + "\n a = " + this.answer + "m/s^2";
      }
    }
    
    //v2^2=v1^2+2*a*d solving for displacement
    if(this.velocity2 != -1.2958 && this.velocity1 != -1.2958 && this.acc != -1.2958)//if v1,v2, acc are given
    {
      if(this.goal.equals("@displacement") || this.goal.equals("@displacement?"))//if missing variable is displacement
      {
        this.answer = ((this.velocity2 * this.velocity2) - (this.velocity1 * this.velocity1)) / (2*this.acc);
        this.explain = "Since you have v2: " + this.velocity2 + " and v1: " + this.velocity1 + " and acceleration: "
          + this.acc + " you can use the formula d = (v2^2 - v1^2) / 2*a" + "\n d = (" + this.velocity2
          + "^2 - " + this.velocity1 + "^2) / (2 * " + this.acc + ")" + "\n d = " + this.answer + "m";
      }
    }
  }
  
  /** getExplain method
    * returns soluton to question
    */
  public String getExplain()
  {
    return this.explain;
  }
  
  //Methods used during testing process
  
  /** getGoal method
    * returns goal
    */
  public String getGoal()
  {
    return this.goal;
  }
  
  /** getVAvg method
    * returns average velocity
    */
  public double getVAvg()
  {
    return this.velocityAvg;
  }
  
  /** getDisplacement method
    * returns displacement
    */
  public double getDisplacement()
  {
    return this.displacement;
  }
  
  /** getV1 method
    * returns v1
    */
  public double getV1()
  {
    return this.velocity1;
  }
  
  /** getV2 method
    * returns v2
    */
  public double getV2()
  {
    return this.velocity2;
  }
  
  /** getTime method
    * returns time
    */
  public double getTime()
  {
    return this.time;
  }
  
  /** getAcc method
    * returns acceleration
    */
  public double getAcc()
  {
    return this.acc;
  }
  
  /** getForce method
    * returns force
    */
  public double getForce()
  {
    return this.force;
  }
  
  /** getMass method
    * returns mass
    */
  public double getMass()
  {
    return this.mass;
  }
  
  /** getAnswer method
    * returns answer
    */
  public double getAnswer()
  {
    return this.answer;
  }
}