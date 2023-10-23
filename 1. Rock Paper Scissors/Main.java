import java.util.*;
class Main {
  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    System.out.print("Please enter your pick (1--rock, 2--paper, 3--scissor, q--exit): ");
    String human = console.next();
    int count = 0;
    String computer = (int)(Math.random()*3+1)+"";
    while (!(human.equals("q"))) {
      while (!(human.equals("q") || human.equals("1") || human.equals("2") || human.equals("3"))) {
      System.out.print("Try again.");
      human = console.next();
      }
      System.out.println("Computer picks: " + computer);
      if (human.equals(computer)) {
        System.out.println("Result: tie\n");
      } else if (human.equals("3") && computer.equals("1") || human.equals("2") && computer.equals("3") || human.equals("1") && computer.equals("2")) {
        System.out.println("Result: computer wins\n");
      } else if (human.equals("1") && computer.equals("3") || human.equals("2") && computer.equals("1") || human.equals("3") && computer.equals("2")) {
        System.out.println("Result: user wins\n");
        count++;
      }
      System.out.print("Please enter your pick (1--rock, 2--paper, 3--scissor, q--exit): ");
      computer = (int)(Math.random()*3+1)+"";
      human = console.next();
    }
    console.close();
    System.out.println("Game over.\nUser total wins(s) " + count + " time.");
  }
}