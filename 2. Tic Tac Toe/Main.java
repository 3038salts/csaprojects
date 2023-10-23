import java.util.*;
class Main {
  public static void main(String[] args) {
    String[][] board = new String[3][3];
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        board[i][j] = "e";
      }
    }
    System.out.println("TIC TAC TOE");
    System.out.println("Type \"one\" for one-player and \"two\" for two-player mode");
    Scanner console = new Scanner(System.in);
    String mode = console.next();
    boolean win1 = false, win2 = false;
    if(mode.equals("one")) {
      System.out.println("One player mode selected.\nThe positions look like");
      System.out.println("(1,1) (1,2) (1,3)");
      System.out.println("(2,1) (2,2) (2,3)");
      System.out.println("(3,1) (3,2) (3,3)");
      System.out.println("Here is the board (e indicates empty):");
      for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
          System.out.print(board[i][j] + " ");
        }
        System.out.println();
      }
      int num1 = 0, num2 = 0, count = 0, track = 0;
      boolean check = false;
      while (win1 == false && win2 == false) {
        while(check == false) {
          System.out.println("Player, type two numbers to indicate which position you want to pick.");
          num1 = console.nextInt();
          num2 = console.nextInt();
          if((board[num1-1][num2-1].equals("e"))) {
            check = true;
            track++;
            board[num1-1][num2-1] = "X";
            System.out.println("Updated board:");
            for(int i = 0; i < 3; i++) {
              for(int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
              }
            System.out.println();
            }
          } else {
            System.out.println("Spot taken.");
          }
        }
        check = false;
        for(int i = 0; i < 3; i++) {
          if(board[num1-1][i].equals("X")) {
            count++;
          }
        }
        if (count == 3) {
            win1 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          if(board[i][num2-1].equals("X")) {
            count++;
          }
        }
        if (count == 3) {
            win1 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          if(board[i][i].equals("X")) {
            count++;
          }
        }
        if (count == 3) {
            win1 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          int j = 2 - i;
          if(board[i][j].equals("X")) {
            count++;
          }
          j--;
        }
        if (count == 3) {
            win1 = true;
            break;
        } else {
          count = 0;
        }
        if(track == 9) {
            break;
        }
        while(check == false) {
          num1 = (int) (Math.random()*3)+1;
          num2 = (int) (Math.random()*3)+1;
          if((board[num1-1][num2-1].equals("e"))) {
            System.out.println("Computer's turn.");
            check = true;
            track++;
            board[num1-1][num2-1] = "O";
            System.out.println("Updated board:");
            for(int i = 0; i < 3; i++) {
              for(int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
              }
            System.out.println();
            }
          }
        }
        check = false;
        for(int i = 0; i < 3; i++) {
          if(board[num1-1][i].equals("O")) {
            count++;
          }
        }
        if (count == 3) {
            win2 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          if(board[i][num2-1].equals("O")) {
            count++;
          }
        }
        if (count == 3) {
            win2 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          if(board[i][i].equals("O")) {
            count++;
          }
        }
        if (count == 3) {
            win2 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          int j = 2 - i;
          if(board[i][j].equals("O")) {
            count++;
          }
          j--;
        }
        if (count == 3) {
            win2 = true;
            break;
        } else {
          count = 0;
        }
      }
    }
    if(mode.equals("two")) {
      System.out.println("Two player mode selected.\nThe positions look like:");
      System.out.println("(1,1) (1,2) (1,3)");
      System.out.println("(2,1) (2,2) (2,3)");
      System.out.println("(3,1) (3,2) (3,3)");
      System.out.println("Here is the board (e indicates empty):");
      for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
          System.out.print(board[i][j] + " ");
        }
        System.out.println();
      }
      int num1 = 0, num2 = 0, count = 0, track = 0;
      boolean check = false;
      while (win1 == false && win2 == false) {
        while(check == false) {
          System.out.println("Player 1 type two numbers to indicate which position you want to pick.");
          num1 = console.nextInt();
          num2 = console.nextInt();
          if((board[num1-1][num2-1].equals("e"))) {
            check = true;
            track++;
            board[num1-1][num2-1] = "X";
            System.out.println("Updated board:");
            for(int i = 0; i < 3; i++) {
              for(int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
              }
            System.out.println();
            }
          } else {
            System.out.println("Spot taken.");
          }
        }
        check = false;
        for(int i = 0; i < 3; i++) {
          if(board[num1-1][i].equals("X")) {
            count++;
          }
        }
        if (count == 3) {
            win1 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          if(board[i][num2-1].equals("X")) {
            count++;
          }
        }
        if (count == 3) {
            win1 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          if(board[i][i].equals("X")) {
            count++;
          }
        }
        if (count == 3) {
            win1 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          int j = 2 - i;
          if(board[i][j].equals("X")) {
            count++;
          }
          j--;
        }
        if (count == 3) {
            win1 = true;
            break;
        } else {
          count = 0;
        }
        if(track == 9) {
            break;
        }
        while(check == false) {
          System.out.println("Player 2 type two numbers to indicate which position you want to pick.");
          num1 = console.nextInt();
          num2 = console.nextInt();
          if((board[num1-1][num2-1].equals("e"))) {
            check = true;
            track++;
            board[num1-1][num2-1] = "O";
            System.out.println("Updated board:");
            for(int i = 0; i < 3; i++) {
              for(int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
              }
            System.out.println();
            }
          } else {
            System.out.println("Spot taken.");
          }
        }
        check = false;
        for(int i = 0; i < 3; i++) {
          if(board[num1-1][i].equals("O")) {
            count++;
          }
        }
        if (count == 3) {
            win2 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          if(board[i][num2-1].equals("O")) {
            count++;
          }
        }
        if (count == 3) {
            win2 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          if(board[i][i].equals("O")) {
            count++;
          }
        }
        if (count == 3) {
            win2 = true;
            break;
        } else {
          count = 0;
        }
        for(int i = 0; i < 3; i++) {
          int j = 2 - i;
          if(board[i][j].equals("O")) {
            count++;
          }
          j--;
        }
        if (count == 3) {
            win2 = true;
            break;
        } else {
          count = 0;
        }
      }
    }
    if (win1 == true) {
      System.out.println("Player 1 won!");
    } else if (win2 == true) {
      System.out.println("Player 2 won!");
    } else {
      System.out.println("Tie.");
    }
    console.close();
  }
}