import java.util.*;
public class Main {
  static Scanner console = new Scanner(System.in);
  public static void main(String[] args) {
    clear(); // clears console
    String[][] map = new String [6][6]; //creates map of size 6 by 6
    map = setmap(map);

    //creates player with their username, hp 100, def 9, maxatk 20, first strike speed 9, fist weapon, no armor
    System.out.println("Please type in a username.");
    Player human = new Player(console.nextLine(), 100, 9, 20, 9, new Item ("fist", "weapon", 0, 0), new Item ("no armor", "armor", 0, 0));
    System.out.println("\nHello " + human.getname() + "!\n");

    //enemies
    Mosquito m1 = new MosquitoEasy();
    Mosquito m2 = new MosquitoMedium();
    Mosquito m3 = new MosquitoHard();
    
    //instructions
    System.out.println("You are playing the Zombified Mosquito Escape Game®. Your task is to move through the mosquito-infested city until you escape. You can collect supplies and will have to fight mosquitoes if they appear. There will be 3 stages.\n");
    wait(5);

    //important variables
    boolean active = true;
    int row = 5;
    int col = 0;
    int store = 0;
    int stage = 1;
    int limiter = 0;
    String choice;
    boolean valid = false;
    
    while (active) { //game loop
      clear();
      printmapinstructions(map);
      while (valid == false) { //confirms movement has taken place
        System.out.println("\nType left, right, up, or down to choose where you want to move or type stats to see your current stats.");
        choice = console.nextLine();
        if (choice.equalsIgnoreCase("left") || choice.equalsIgnoreCase("right")) { //enables horizontal movement
          store = movehori(choice, col);
          if (store != -1) {
            valid = true;
            if (map[row][store] == "-") {
              valid = false;
              map[row][store] = "P"; // updates player position on map
            }
            map[row][col] = "-";
            col = store;
          }
        } else if (choice.equalsIgnoreCase("up") || choice.equalsIgnoreCase("down")) { //enables vertical movement
          store = movevert(choice, row);
          if (store != -1) {
            valid = true;
            if (map[store][col] == "-") {
              valid = false;
              map[store][col] = "P"; // updates player position on map
            }
            map[row][col] = "-";
            row = store;
          }
        } else if (choice.equalsIgnoreCase("stats")) { //prints out stats
          System.out.println("\n" + human.printstats());
        } else {
            System.out.println("Invalid choice.");
        }
      }
      valid = false;
      
      if (map[row][col] == "E") { //checks if exit reached or game won
        if (stage == 1) {
          System.out.println("\nYou reached the exit for stage 1. Two more stages to go!");
          map = setmap(map);
          row = 5;
          col = 0;
          wait(5);
        } else if (stage == 2) {
          System.out.println("\nYou reached the exit for stage 2. One more stage to go!");
          map = setmap(map);
          row = 5;
          col = 0;
          wait(5);
        } else if (stage == 3) {
          System.out.println("\nYOU FINISHED THE GAME!!! CONGRATULATIONS!");
          printtrophy();
          break;
        }
        stage++;
      } else {
        map[row][col] = "P"; // updates player position on map
      }

      if (stage == 1) { //stage 1
        if ((int) (Math.random() * 100) + 1 <= 50 && limiter <= 4) { //determines probability of encountering zombie vs supply
          limiter++;
          clear();
          printmapinstructions(map);
          System.out.println("You have encountered a " + m1.gettype() + "zombified mosquito!\n");
          System.out.println(m1.printstats());
          active = battlesequence(human, m1, active);
        } else { //encountered supply
          clear();
          printmapinstructions(map);
          int random = (int) (Math.random() * 15) + 1; //1-15 tier 1, 1/3 chance within each tier
          supplysequence(random, human);
        }
      }

      if (stage == 2) {
        if ((int) (Math.random() * 100) + 1 <= 60 && limiter <= 5) {
          limiter++;
          clear();
          printmapinstructions(map);
          if ((int) (Math.random() * 100) + 1 <= 70 ) { //probability of encountering easy mosquito
            System.out.println("You have encountered a " + m1.gettype() + "zombified mosquito!\n");
            System.out.println(m1.printstats());
            active = battlesequence(human, m1, active);
          } else { //probability of encountering medium mosquito
            limiter += 2;
            System.out.println("You have encountered a " + m2.gettype() + "zombified mosquito!\n");
            System.out.println(m2.printstats());
            active = battlesequence(human, m2, active);
          }
        } else { //encountered supply
          clear();
          printmapinstructions(map);
          int random = (int) (Math.random() * 81) + 1; //1-15 tier 1, 16-81 tier 2, 1/3 chance within each tier
          supplysequence(random, human);
        }
      }

      if (stage == 3) {
        if ((int) (Math.random() * 100) + 1 <= 70 && limiter <= 6) {
          clear();
          printmapinstructions(map);
          int probability = (int) (Math.random() * 100) + 1;
          if (probability <= 70) { //encountering medium mosquito
            limiter += 2;
            System.out.println("You have encountered a " + m2.gettype() + "zombified mosquito!\n");
            System.out.println(m2.printstats());
            active = battlesequence(human, m2, active);
          } else { //encountering hard mosquito
            limiter +=3;
            System.out.println("You have encountered a " + m3.gettype() + "zombified mosquito!\n");
            System.out.println(m3.printstats());
            active = battlesequence(human, m3, active);
          }
        } else { //encountered supply
          clear();
          printmapinstructions(map);
          int random = (int) (Math.random() * 102) + 1; //1-15 tier 1, 16-81 tier 2, 82-102 tier 3, 1/3 chance within each tier
          supplysequence(random, human);
        }
      }
    }
  }

  public static void clear () { //clears console
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  
  public static void wait(int sec) {
    try {
      Thread.sleep(sec*1000);
    }
    catch(InterruptedException exception) {
      System.out.println("wait error");
    }
  }

  public static String[][] setmap (String[][] map) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        map[i][j] = "?";
      }
    }
    map[5][0] = "P"; //player position
    map[0][5] = "E"; //exit 
    return map;
  }

  public static void print (String[][] map) { //prints map
    for (String[] i: map) {
      for (String j: i) {
        System.out.print(j + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static int movehori (String s, int c) { //player movement horizontal
   if (s.equalsIgnoreCase("left") && c > 0) {
      return --c;
    } else if (s.equalsIgnoreCase("right") && c < 5) {
     return ++c;
    } else {
     System.out.println("Invalid choice.");
     return -1;
    }
  }

  public static int movevert (String s, int r) { //player movement vertical
    if (s.equalsIgnoreCase("down") && r < 5) {
      return ++r;
    } else if (s.equalsIgnoreCase("up") && r > 0) {
     return --r;
    } else {
      System.out.println("Invalid choice.");
      return -1;
    }
  }

  public static boolean battlesequence (Player human, Mosquito m, boolean active) {
    while (human.gethp() > 0 && m.gethp() > 0) { //ensures one is still alive
      System.out.println("\nFight or inventory?");
      String choice = console.nextLine();
      if (choice.equalsIgnoreCase("fight")) {
        if (human.getfirststrikespeed() > m.getfirststrikespeed()) { //player attacks first
          m.reducehp(human.attack());
          if (m.gethp() > 0) {
            int temp = m.attack() - human.defend();
            if (temp > 0) {
              human.reducehp(temp);
            }
          }
        } else if (human.getfirststrikespeed() < m.getfirststrikespeed()) { //zombie attacks first
          int temp = m.attack() - human.defend();
          if (temp > 0) {
            human.reducehp(temp);
          }
          if (human.gethp() > 0) {
            m.reducehp(human.attack());
          }
        } else {
          if (Math.random() < 0.5) { //random chance for who attacks first
            m.reducehp(human.attack());
            if (m.gethp() > 0) {
              int temp = m.attack() - human.defend();
              if (temp > 0) {
                human.reducehp(temp);
              }
            }
          } else {
            int temp = m.attack() - human.defend();
            if (temp > 0) {
              human.reducehp(temp);
            }
            if (human.gethp() > 0) {
              m.reducehp(human.attack());
            }
          }
        }
        if (human.gethp() < 0) {
          human.sethptozero();
        } else if (m.gethp() < 0) {
          m.sethptozero();
        }
        printhealths(human, m);
      } else if (choice.equalsIgnoreCase("inventory")) { //access inventory
        System.out.println("\n" + human.printinv() + "\n");
        System.out.println("Type in the corresponding number of the item you want to use, or anything else to exit your inventory.");
        int choicenum = -1;
        if (console.hasNextInt()) {
          choicenum = console.nextInt() - 1;
        }
        console.nextLine();
        if (choicenum >= 0 && choicenum <= human.getinv().size()) { //checks if selection is in inv or for exiting inv
          if (human.getinv().get(choicenum).gettype().equals("weapon")) { //equips weapon and changes attack
            human.subtratk(human.getequippedweapon().getmodifier());
            human.addatk(human.getinv().get(choicenum).getmodifier());
            human.subtrspeed(human.getequippedweapon().getspeedboost());
            human.addspeed(human.getinv().get(choicenum).getspeedboost());
            human.addinv(human.getequippedweapon());
            human.setequippedweapon(human.getinv().get(choicenum));
            System.out.print("You have equipped the " + human.getinv().get(choicenum).getname() + ".\n");
          } else if (human.getinv().get(choicenum).gettype().equals("heal")) { //uses potion and adds to health
            human.addhp(human.getinv().get(choicenum).getmodifier());
            System.out.print("You use the " + human.getinv().get(choicenum).getname() + ".\n");
          } else if (human.getinv().get(choicenum).gettype().equals("armor")) { //equips armor and changes def
            human.subtrdef(human.getequippedarmor().getmodifier());
            human.adddef(human.getinv().get(choicenum).getmodifier());
            human.addinv(human.getequippedarmor());
            human.setequippedarmor(human.getinv().get(choicenum));
            System.out.print("You have equipped the " + human.getinv().get(choicenum).getname() + ".\n");
          }
          human.removeinv(human.getinv().get(choicenum)); //removes newly equipped item from inv
          human.printstats();
          choicenum = -1;
        }
      } else {
        System.out.println("Invalid choice.");
      }
    }
    if (human.gethp() <= 0) {
      System.out.println("\nYou died. Game over.");
      printendingmessage();
      active = false;
    } else {
      wait(2);
      clear();
      System.out.println("You won the battle!\n");
      m.sethp();
    }
    return active;
  }

  public static void supplysequence (int random, Player human) {
    String choice;
    Item weapon1 = new Item ("swatter", "weapon", 6, 7);
    Item weapon2 = new Item ("spear", "weapon", 20, 14);
    Item weapon3 = new Item ("UV Light of Salvation", "weapon", 40, 28);
    Item heal1 = new Item ("health potion", "heal", 50, 0);
    Item heal2 = new Item ("full health potion", "heal", 100, 0);
    Item heal3 = new Item ("double health potion", "heal", 200, 0);
    Item armor1 = new Item ("repellant", "armor", 5, 0);
    Item armor2 = new Item ("mosquito net", "armor", 9, 0);
    Item armor3 = new Item ("Bubble of Solidarity", "armor", 25, 0);
    while (random >= 1 && random <= 5) {
      System.out.println("You have found a " + weapon1.getname() + ". Would you like to equip, abandon, or save it into your inventory?\n");
      choice = console.nextLine();
      if (choice.equalsIgnoreCase("equip")) {
        human.subtratk(human.getequippedweapon().getmodifier());
        human.addatk(weapon1.getmodifier());
        human.subtrspeed(human.getequippedweapon().getspeedboost());
        human.addspeed(weapon1.getspeedboost());
        human.addinv(human.getequippedweapon());
        human.setequippedweapon(weapon1);
        System.out.println("You have equipped the " + weapon1.getname() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("abandon")) {
        System.out.println("You have abandoned the " + weapon1.getname() + ".\n");
        random = -1;
        wait(2);
        clear();
      } else if (choice.equalsIgnoreCase("save")) {
        System.out.println("You have saved the " + weapon1.getname() + ".\n");
        human.addinv(weapon1);
        wait(2);
        clear();
        random = -1;
      } else {
        System.out.println("Invalid choice.");
      }
    }
    while (random >= 6 && random <= 10) {
      System.out.print("You have found a " + heal1.getname() + ". Would you like to use, abandon, or save it into your inventory?\n");
      choice = console.nextLine();
      if (choice.equalsIgnoreCase("use")) {
        human.addhp(heal1.getmodifier());
        System.out.println("You use the " + heal1.getname() + ".");
        System.out.println("Your health is now " + human.gethp() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("abandon")) {
        System.out.println("You have abandoned the " + heal1.getname() + ".\n");
        random = -1;
        wait(2);
        clear();
      } else if (choice.equalsIgnoreCase("save")) {
        System.out.println("You have saved the " + heal1.getname() + ".\n");
        human.addinv(heal1);
        wait(2);
        clear();
        random = -1;
      } else {
        System.out.println("Invalid choice.");
      }
    }
    while (random >= 11 && random <= 15) {
      System.out.print("You have found some " + armor1.getname() + ". Would you like to equip, abandon, or save it into your inventory?\n");
      choice = console.nextLine();
      if (choice.equalsIgnoreCase("equip")) {
        human.subtrdef(human.getequippedarmor().getmodifier());
        human.adddef(armor1.getmodifier());
        human.addinv(human.getequippedarmor());
        human.setequippedarmor(armor1);
        System.out.println("You have equipped the " + armor1.getname() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("abandon")) {
        System.out.println("You have abandoned the " + armor1.getname() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("save")) {
        System.out.println("You have saved the " + armor1.getname() + ".\n");
        wait(2);
        clear();
        human.addinv(armor1);
        random = -1;
      } else {
        System.out.println("Invalid choice.");
      }
    }
    while (random >= 16 && random <= 37) {
      System.out.println("You have found a " + weapon2.getname() + ". Would you like to equip, abandon, or save it into your inventory?\n");
      choice = console.nextLine();
      if (choice.equalsIgnoreCase("equip")) {
        human.subtratk(human.getequippedweapon().getmodifier());
        human.addatk(weapon2.getmodifier());
        human.subtrspeed(human.getequippedweapon().getspeedboost());
        human.addspeed(weapon2.getspeedboost());
        human.addinv(human.getequippedweapon());
        human.setequippedweapon(weapon2);
        System.out.println("You have equipped the " + weapon2.getname() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("abandon")) {
        System.out.println("You have abandoned the " + weapon2.getname() + ".\n");
        random = -1;
        wait(2);
        clear();
      } else if (choice.equalsIgnoreCase("save")) {
        System.out.println("You have saved the " + weapon2.getname() + ".\n");
        human.addinv(weapon2);
        wait(2);
        clear();
        random = -1;
      } else {
        System.out.println("Invalid choice.");
      }
    }
    while (random >= 38 && random <= 59) {
      System.out.print("You have found a " + heal2.getname() + ". Would you like to use, abandon, or save it into your inventory?\n");
      choice = console.nextLine();
      if (choice.equalsIgnoreCase("use")) {
        human.addhp(heal2.getmodifier());
        System.out.println("You used the " + heal2.getname() + ".");
        System.out.println("Your health is now " + human.gethp() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("abandon")) {
        System.out.println("You have abandoned the " + heal2.getname() + ".\n");
        random = -1;
        wait(2);
        clear();
      } else if (choice.equalsIgnoreCase("save")) {
        System.out.println("You have saved the " + heal2.getname() + ".\n");
        human.addinv(heal2);
        wait(2);
        clear();
        random = -1;
      } else {
        System.out.println("Invalid choice.");
      }
    }
    while (random >= 60 && random <= 81) {
      System.out.print("You have found a " + armor2.getname() + ". Would you like to equip, abandon, or save it into your inventory?\n");
      choice = console.nextLine();
      if (choice.equalsIgnoreCase("equip")) {
        human.subtrdef(human.getequippedarmor().getmodifier());
        human.adddef(armor2.getmodifier());
        human.addinv(human.getequippedarmor());
        human.setequippedarmor(armor2);
        System.out.println("You have equipped the " + armor2.getname() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("abandon")) {
        System.out.println("You have abandoned the " + armor2.getname() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("save")) {
        System.out.println("You have saved the " + armor2.getname() + ".\n");
        wait(2);
        clear();
        human.addinv(armor2);
        random = -1;
      } else {
        System.out.println("Invalid choice.");
      }
    }
    while (random >= 82 && random <= 88) {
      System.out.println("You have found the " + weapon3.getname() + ". Would you like to equip, abandon, or save it into your inventory?\n");
      choice = console.nextLine();
      if (choice.equalsIgnoreCase("equip")) {
        human.subtratk(human.getequippedweapon().getmodifier());
        human.addatk(weapon3.getmodifier());
        human.subtrspeed(human.getequippedweapon().getspeedboost());
        human.addspeed(weapon3.getspeedboost());
        human.addinv(human.getequippedweapon());
        human.setequippedweapon(weapon3);
        System.out.println("You have equipped the " + weapon3.getname() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("abandon")) {
        System.out.println("You have abandoned the " + weapon3.getname() + ".\n");
        random = -1;
        wait(2);
        clear();
      } else if (choice.equalsIgnoreCase("save")) {
        System.out.println("You have saved the " + weapon3.getname() + ".\n");
        human.addinv(weapon3);
        wait(2);
        clear();
        random = -1;
      } else {
        System.out.println("Invalid choice.");
      }
    }
    while (random >= 89 && random <= 95) {
      System.out.print("You have found a " + heal3.getname() + ". Would you like to use, abandon, or save it into your inventory?\n");
      choice = console.nextLine();
      if (choice.equalsIgnoreCase("use")) {
        human.addhp(heal3.getmodifier());
        System.out.println("You use the " + heal3.getname() + ".");
        System.out.println("Your health is now " + human.gethp() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("abandon")) {
        System.out.println("You have abandoned the " + heal3.getname() + ".\n");
        random = -1;
        wait(2);
        clear();
      } else if (choice.equalsIgnoreCase("save")) {
        System.out.println("You have saved the " + heal3.getname() + ".\n");
        human.addinv(heal3);
        wait(2);
        clear();
        random = -1;
      } else {
        System.out.println("Invalid choice.");
      }
    }
    while (random >= 96 && random <= 102) {
      System.out.print("You have found the " + armor3.getname() + ". Would you like to equip, abandon, or save it into your inventory?\n");
      choice = console.nextLine();
      if (choice.equalsIgnoreCase("equip")) {
        human.subtrdef(human.getequippedarmor().getmodifier());
        human.adddef(armor3.getmodifier());
        human.addinv(human.getequippedarmor());
        human.setequippedarmor(armor3);
        System.out.println("You have equipped the " + armor3.getname() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("abandon")) {
        System.out.println("You have abandoned the " + armor3.getname() + ".\n");
        wait(2);
        clear();
        random = -1;
      } else if (choice.equalsIgnoreCase("save")) {
        System.out.println("You have saved the " + armor3.getname() + ".\n");
        wait(2);
        clear();
        human.addinv(armor3);
        random = -1;
      } else {
        System.out.println("Invalid choice.");
      }
    }
  }

  public static void printmapinstructions (String[][] map) {
    System.out.println("Here is the map. P represents your position (the player) and E represents the exit for this stage. \"?\" represents unexplored areas. \"-\" represents explored areas.\n");
    print(map);
  }

  public static void printhealths (Player h, Mosquito m) {
    System.out.println("\n" + h.printhealth() + "\n");
    System.out.println(m.printhealth());
  }

  public static void printendingmessage () {
    System.out.println("                     ,-.");
    System.out.println("         `._        /  |        ,");
    System.out.println("            `--._  ,   '    _,-'");
    System.out.println("     _       __  `.|  / ,--'");
    System.out.println("      `-._,-'  `-. \\ : /");
    System.out.println("           ,--.-.-`'.'.-.,_-");
    System.out.println("         _ `--'-'-;.'.'-'`--");
    System.out.println("     _,-' `-.__,-' / : \\");
    System.out.println("                _,'|  \\ `--._");
    System.out.println("           _,--'   '   .     `-.");
    System.out.println("         ,'         \\  |        `");
  }

  public static void printtrophy () {
    System.out.println("             ___________");
    System.out.println("            '._==_==_=_.'");
    System.out.println("            .-\\:      /-.");
    System.out.println("           | (|:.     |) |");
    System.out.println("            '-|:.     |-'");
    System.out.println("              \\::.    /");
    System.out.println("               '::. .'");
    System.out.println("                 ) (");
    System.out.println("               _.' '._");
    System.out.println("              `\"\"\"\"\"\"\"`");
  }
}