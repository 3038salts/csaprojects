import java.util.*;
class Main {
  public static void main(String[] args) {
    String[] four = new String[5]; //declare & initialize word banks
    String[] five = new String[5];
    String[] six = new String[5];
    String[] seven = new String[5];
    four[0] = "kept";
    four[1] = "cash";
    four[2] = "desk";
    four[3] = "fade";
    four[4] = "leap";
    five[0] = "dying";
    five[1] = "super";
    five[2] = "leave";
    five[3] = "grief";
    five[4] = "child";
    six[0] = "grouch";
    six[1] = "browse";
    six[2] = "clouds";
    six[3] = "wallop";
    six[4] = "tables";
    seven[0] = "history";
    seven[1] = "persona";
    seven[2] = "chronic";
    seven[3] = "limited";
    seven[4] = "diverge";
    System.out.print("\033[H\033[2J"); //clears screen
    System.out.flush();
    Scanner console = new Scanner(System.in); //set up user input
    boolean play = true;
    while (play == true) { //allows for multiple rounds
      int choice = 0;
      System.out.println("You are playing Wordle®.\nThere are 4-7 letter modes. Type the number of the mode you want.");
      while (choice == 0) {
        if (console.hasNextInt()) {
          choice = console.nextInt(); //mode player wants
          if (choice > 7 || choice < 4) {
          choice = 0;
          System.out.println("That is not a choice. Please type a number again.");
        }
        } else {
          console.next();
          System.out.println("That is not a choice. Please type a number again.");
        }
      }
      Random rand = new Random();
      int index = rand.nextInt(5); //chooses random index to use for word bank later
      System.out.println("You are playing " + choice + " letter mode.\nYou have 6 attempts. \"*\" means that the letter is in the word, but in the wrong position. \nType your guess.");
      String[] board = new String[6]; //board view for player
      for (int b = 0; b < 6; b++) { //initialize all to first placeholder
        board[b] = "_";
      }
      for (int b = 0; b < 6; b++) { //initialize all to board placeholder
        for (int c = 1; c < choice; c++) {
          board[b] += "_";
        }
      }
      boolean finished = false;
      int track = 0; //updates to add each guess to the following row
      while (finished == false) {
        String answer = ""; //declares answer variable
        String guess = console.next(); //takes in the guess
        while (guess.length() > choice || guess.length() < choice) { //checks if guess word length is same as the mode
          System.out.println("The length is not the same. Try again.");
          guess = console.next();
        }
        System.out.println("\nAttempt " + (track + 1) + ".");
        if (choice == 4) {
          answer = four[index]; //initializes answer
          board[track] = check(guess, four, index); //puts the result on corresponding row
          for (int z = 0; z < board.length; z++) { //prints board
            System.out.println(board[z]);
          }
          System.out.println();
        } else if (choice == 5) {
          answer = five[index]; //initializes answer
          board[track] = check(guess, five, index); //puts the result on corresponding row
          for (int z = 0; z < board.length; z++) { //prints board
            System.out.println(board[z]);
          }
        } else if (choice == 6) {
          answer = six[index]; //initializes answer
          board[track] = check(guess, six, index); //puts the result on corresponding row
          for (int z = 0; z < board.length; z++) { //prints board
            System.out.println(board[z]);
          }
        } else if (choice == 7) {
          answer = seven[index]; //initializes answer
          board[track] = check(guess, seven, index); //puts the result on corresponding row;
          for (int z = 0; z < board.length; z++) { //prints board
            System.out.println(board[z]);
          }
        }
        if (!(board[track].contains("-")) && !(board[track].contains("*"))) {
          finished = true;
          System.out.println("You won. Play again? Type yes or no.");
          guess = console.next();
          if (guess.equals("yes")) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
          } else {
            play = false;
            console.close();
          }
        } else {
          if (track == 5) {
            finished = true;
            System.out.println("You lost. The answer is " + answer + ".\nDo you want to play again? Type yes or no.");
            guess = console.next();
            if (guess.equals("yes")) {
              System.out.print("\033[H\033[2J");
              System.out.flush();
            } else {
              play = false;
              console.close();
            }
          }
        }
        track++;
      }
    }
  }
  public static String check (String guess, String[] bank, int index) { //checks guess against answer
    char[] result = new char[bank[index].length()];
    //stores the result
    char[] guessarr = new char[guess.length()];
    //modifiable guess array
    for (int i = 0; i < bank[index].length(); i++) {
      guessarr[i] = guess.charAt(i);
    }
    char[] answer = new char[bank[index].length()];
    //used to prevent false * from appearing in result
    for (int i = 0; i < bank[index].length(); i++) {
      answer[i] = bank[index].charAt(i);
    }
    for (int i = 0; i < bank[index].length(); i++) {
      if (guess.charAt(i) == bank[index].charAt(i)) {
        result[i] = (guess.charAt(i)); //correct character and index
        answer[i] = '-'; //eliminates matching letters
        guessarr[i] = '_';
      } else {
        result[i] = '-'; //wrong character 
      }
    }
    for (int i = 0; i < bank[index].length(); i++) {
      if (poscheck(answer, guessarr, i) != -1) {
        result[i] = '*'; //correct character but wrong index
      }
    }
    String conclude = "";
    for (char i: result) { //convert the char array into a string
      conclude += i;
    }
    return conclude;
  }
  public static int poscheck (char[] answer, char[] guessarr, int index) {
    for(int i = 0; i < guessarr.length; i++) {
      if (answer[i] == guessarr[index]) {
        return i;
      }
    }
    return -1;
  }
}