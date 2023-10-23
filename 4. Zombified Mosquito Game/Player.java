import java.util.*;
public class Player {
  private int health;
  private int defense;
  private int maxattack;
  private int firststrike;
  private String name;
  private Item equippedweapon;
  private Item equippedarmor;
  private ArrayList<Item> inv;

  public Player (String username, int hp, int def, int atk, int fstrike, Item ew, Item ea) {
    name = username;
    health = hp;
    defense = def;
    maxattack = atk;
    firststrike = fstrike;
    inv = new ArrayList<Item>();
    equippedweapon = ew;
    equippedarmor = ea;
  }

  public String getname () {
    return name;
  }

  public int gethp () {
    return health;
  }
  
  public ArrayList<Item> getinv () {
    return inv;
  }

  public int getfirststrikespeed () {
    return firststrike;
  }
  
  public Item getequippedweapon () {
    return equippedweapon;
  }

  public Item getequippedarmor () {
    return equippedarmor;
  }

  public void reducehp (int deficit) {
    health -= deficit;
  }

  public void addhp (int heal) {
    health += heal;
    if (health > 100) {
      health = 100;
    }
  }

  public void sethptozero () {
    health = 0;
  }
  
  public void addatk (int num) {
    maxattack += num;
  }

  public void subtratk (int num) {
    maxattack -= num;
  }

  public void adddef (int def) {
    defense += def;
  }

  public void subtrdef (int def) {
    defense -= def;
  }

  public void addspeed (int speed) {
    firststrike+= speed;
  }

  public void subtrspeed (int speed) {
    firststrike -= speed;
  }
  
  public int attack () {
    return (int) (Math.random() * (maxattack - maxattack/2)) + maxattack/2;
    //random damage from half of max attack to max attack
  }
  
  public int defend () {
    return (int) (Math.random() * (defense - defense/2)) + defense/2; //blocks damage from half of defense to all of it
  }
  
  public void addinv (Item item) { //collect supply
    inv.add(item);
  }
  
  public void removeinv (Item item) { //use supply
    inv.remove(item);
  }

  public void setequippedweapon (Item ew) {
    equippedweapon = ew;
  }

  public void setequippedarmor (Item ea) {
    equippedarmor = ea;
  }

  public String printstats () {
    String stats = name + "'s stats:\nhealth: " + health + "\ndefense: " + defense + "\nmaxattack: " + maxattack + "\nfirst strike speed: " + firststrike + "\nequipped weapon: " + equippedweapon.getname() + "\nequipped armor: " + equippedarmor.getname() + "\ninventory: ";
    if (inv.size() == 0) {
      return stats + "empty";
    } else {
      for (Item i: inv) {
        stats += i.getname() + ", ";
      }
      return stats.substring(0, stats.length() - 2);
    }
  }

  public String printhealth () {
    return name + "'s health: " + health + ".";
  }

  public String printinv () {
    if (inv.size() == 0) {
      return "Inventory: empty";
    }
    String list = "";
    int a = 1;
    for (Item i: inv) {
        list += a + ". " + i.getname() + " ";
        a++;
      }
    return "Inventory: " + list;
  }
}