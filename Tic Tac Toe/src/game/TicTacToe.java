package game;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Description: A simple command line interface
 * game of Tic Tac Toe.
 *
 * @author: Sam HilliardS
 * @version: 1.0
 */

public class TicTacToe {
  static char[][] marks = new char[3][3];
  static Scanner scan = new Scanner(System.in);
  static int turn;
  static boolean game = false; //stores true in event of win and game-over
  static boolean draw = false; //stores true in event of all spaces filled without single player win

  public static void main(String[] args) {
    game();
  }

  /**
   * holds all of the methods needed to run the game
   * in continuous loop
   */
  public static void game() {
	 clearScreen(); //ensures clear screen and board when playing again
	 freshBoard();

	 System.out.println(
	 		" +-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+-+-+\n" +
	 		" |T|e|r|m|i|n|a|l| |T|i|c|-|T|a|c|-|T|o|e|\n" +
	 		" +-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+-+-+\n");
	 //statements outside to make sure header stays before
	 //clearScreen call
	 printBoard();
     promptPlayer(1);
     checkWin(1);
    //game loop present for continuous gameplay until win or draw
    while (true) {
      if (game)
        break;
      turn = 2;
      clearScreen();
      printBoard();
      promptPlayer(turn);
      checkWin(turn);
      clearScreen();
      printBoard();
      if (game)
        break;
      turn = 1;
      promptPlayer(turn);
      checkWin(turn);
    }

    //prints board last time to view end result
    clearScreen();
    printBoard();
    //chooses correct statement in event of draw or win
    if (draw)
      System.out.println("Game over it's a stalemate!\nEveryone's a winner!!!");
    else
      System.out.println("Player" + turn + " is the winner!!!");

    playAgain();
  }
  /**
   * sets all elements of marks to space char
   */
  public static void freshBoard() {
    for (int i = 0; i < marks.length; i++)
      for (int j = 0; j < marks[i].length; j++)
        marks[i][j] = ' ';
  }
  /**
   * iterates through the marks list to print the entire
   * board layout to the console
   */
  public static void printBoard() {
    for (int i = 0; i < marks.length; i++) {
      System.out.println();
      for (int j = 0; j < marks[i].length; j++) {
        System.out.print("|" + marks[i][j]);
      }
      System.out.print("|");
    }
    System.out.println("\n");
  }

  /**
   * prints space to the console so console does
   * not fill up with game boards and prompts
   */
  public static void clearScreen() {
	  for (int i = 0; i < 50; i++)
		  System.out.println();
  }
  /**
   * used to ask player where mark should be placed
   * and checks the input for validity
   *
   * @param int player  used to distinguish between player 1 and 2
   */
  public static void promptPlayer(int player) {
    int row = 0, column = 0;
    System.out.print("Player" + player + " enter a row and column: ");

    //validates user input
    try {
		row = scan.nextInt() - 1;
		column = scan.nextInt() - 1;
	    //checks for input outside of the board
	    if (row > 2 || column > 2 || row < 0 || column < 0) {
	       throw new ArrayIndexOutOfBoundsException("Mark placed outside of scope of board.");
	    }
	    //checks for existence of a mark
	    if (marks[row][column] != ' ') {
	    	throw new MarkAlreadyExists("Player attempted to place a mark where one already exists.");
	    }
	}
	    //checks for non integer input data assuming it is
	    catch(InputMismatchException e) {
	    	System.out.println("Invalid input.");
	    	System.out.print("Please enter an integer row and column.\n");
	    	scan.nextLine();
	    	promptPlayer(player);
	    }
	    //makes sure mark placement lies within scope of board
	    catch (ArrayIndexOutOfBoundsException e) {
	        System.out.println("Mark coordinates outside of board.");
	        System.out.print("Please enter a row and column inside the board.\n");
	        promptPlayer(player);
	    }
	    //makes sure player can't place a mark where one already exists
	    catch (MarkAlreadyExists e) {
	    	System.out.println("There is already a mark here.");
	    	System.out.print("Please enter a vacant row and column.\n");
	    	promptPlayer(player);
	    }

    //once input is checked, depending on which player,
    //a mark is placed (either X or O) on the board
    if (player == 1)
      marks[row][column] = 'X';
    else
      marks[row][column] = 'O';
  }
  /**
   * runs through all of the win possibilities
   * and depending on turn, player1 or 2 is awarded victory
   *
   * @param int player  used to distinguish between player 1 and 2
   */
  public static void checkWin(int player) {
    checkRow(player);
    checkColumn(player);
    checkDiag(player);
  }
  /**
   * checks for row (horizontal three in a row)
   * win possibilities
   *
   * @param int player  used to distinguish between player 1 and 2
   */
  public static void checkRow(int player) {
	//stores win possibility depending on player turn
    String line;
    if (player == 1)
      line = "XXX";
    else
      line = "OOO";

    String row1 = "" + marks[0][0] + marks[0][1] + marks[0][2]; //stores marks of row1 as a string
    String row2 = "" + marks[1][0] + marks[1][1] + marks[1][2];	//and so on for other rows
    String row3 = "" + marks[2][0] + marks[2][1] + marks[2][2];

    if (row1.equals(line) || row2.equals(line) || row3.equals(line))
      game = true;
    else
      checkDraw();
  }

  /**
   * checks for column (vertical three in a row)
   * win possibilities
   *
   * @param int player  used to distinguish between player 1 and 2
   */
  public static void checkColumn(int player) {
    String line;
    if (player == 1)
      line = "XXX";
    else
      line = "OOO";

    String c1 = "" + marks[0][0] + marks[1][0] + marks[2][0];
    String c2 = "" + marks[0][1] + marks[1][1] + marks[2][1];
    String c3 = "" + marks[0][2] + marks[1][2] + marks[2][2];

    if (c1.equals(line) || c2.equals(line) || c3.equals(line))
      game = true;
    else
      checkDraw();
  }
  /**
   * checks for diagonal three in a row win
   * possibilities
   */
  public static void checkDiag(int player) {
    String line;
    if (player == 1)
      line = "XXX";
    else
      line = "OOO";

    String d1 = "" + marks[0][0] + marks[1][1] + marks[2][2];
    String d2 = "" + marks[0][2] + marks[1][1] + marks[2][0];

    if (d1.equals(line) || d2.equals(line))
      game = true;
    else
      checkDraw();
  }
  /**
   * checks for board fill without win
   * and will end game
   */
  public static void checkDraw() {
    int countMark = 0;

    for (int i = 0; i < marks.length; i++) {
      for (int j = 0; j < marks[i].length; j++) {
        if (marks[i][j] != ' ')
          countMark++;
      }
    }
    if (countMark >= 9) {
      game = true;
      draw = true;
    }
  }

  /**
   * prompts user to play again after
   * the game loop has ended
   */
  public static void playAgain() {
	  System.out.println("\nWould you like to play again?");
	  System.out.print("(y/n): ");
	  String answer = scan.next();

	  while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"))) {
		  System.out.print("Please type 'y' (yes) or 'n' (no) to continue: ");
		  answer = scan.next();
	  }

	  if (answer.equalsIgnoreCase("y")) {
		  game = false;
		  draw = false;
		  clearScreen();
		  game();
	  }
  }

}
