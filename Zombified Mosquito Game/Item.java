public class Item {
  private String name;
  private String type;
  private int modifier; //applies for increased attacking, healing, and adding defense
  private int speedboost; //only for weapon
  
  //constructor
  public Item (String n, String t, int m, int sb) {
    name = n;
    type = t;
    modifier = m;
    speedboost = sb;
  }

  public String gettype () {
    return type;
  }

  public String getname () {
    return name;
  }

  public int getmodifier () {
    return modifier;
  }

  public int getspeedboost () {
    return speedboost;
  }
}