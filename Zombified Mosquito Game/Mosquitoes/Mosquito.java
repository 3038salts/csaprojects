public class Mosquito{
    private int health;
    private int maxattack;
    private int firststrike;
    private String type;
  
    //constructor
    public Mosquito (int hp, int atk, int fstrike, String t) {
      health = hp;
      maxattack = atk;
      firststrike = fstrike;
      type = t;
    }
  
    public String gettype () {
      return type;
    }
  
    public int gethp () {
      return health;
    }
  
    public void sethptozero () {
      health = 0;
    }
  
    public void sethp (int hp) {
      health = hp;
    }
  
    public void sethp() {
      sethp();
    }
    
    public int getfirststrikespeed () {
      return firststrike;
    }
    
    public void reducehp (int deficit) {
      health -= deficit;
    }
  
    public int attack () {
      return (int) (Math.random() * (maxattack - maxattack/2)) + maxattack/2;
      //random damage from half of max attack to max attack
    }
  
    public String printstats () {
      return "The " + type + "mosquito's stats:\nhealth: " + health + "\nmaxattack: " + maxattack + "\nfirst strike speed: " + firststrike;
    }
  
    public String printhealth () {
      return "The " + type + "mosquito's health: " + health + ".";
    }
    
  }