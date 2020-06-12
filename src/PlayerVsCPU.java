/*AUTHOR: NOMAKEWZI , KIMBERLEN , DEVIN
 * CS 3410 SPRING
 * SPRING 2016
 * PROGRAM 6
 */
package game;

import java.util.Scanner;

public class PlayerVsCPU {

	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args){
		
		//Board Size
		
		char[][] tttboard = new char[3][3];
		//Empty spaces on board
		blankBoard(tttboard);
		
		System.out.println("********TIC TAC TOE**********");
		System.out.println("Player 1 VS. AI");
		printBoard(tttboard);

	//C
		System.out.print("What piece would you like today? (\"X\" or \"O\") ");
		char playerOne = scan.next().toLowerCase().charAt(0);
		char computer = (playerOne == 'x') ? 'o' : '*';
		System.out.print("Hello, lets begin!!! ");
		
		int turn = 0;
		int numMoves = 3 * 3;

		player1(tttboard, playerOne);
		
		CPU(tttboard, computer);
		
		printBoard(tttboard);
		numMoves--;

		boolean finito = false;
		int winner = -1;

		
		while (!finito && numMoves > 0){
		
			finito = Winner(tttboard, turn, playerOne, computer);
			if (finito)
				winner = turn;
			else {
				
				turn = (turn + 1 ) % 2;
				if (turn == 0)
					player1(tttboard, playerOne);
				else
					CPU(tttboard, computer);
				//game board
				printBoard(tttboard);
				numMoves--;
			}
		}
		//winner
		if (winner == 0)
			System.out.println("\n******YOU WON THE GAME***********");
		else if (winner == 1)
			System.out.println("\n*********YOU HAVE LOST TO THE COMPUTER**********");
		else
			System.out.println("\n*********TIE GAME***************");
}



public static void printBoard(char[][] board){
//CREATES LABELS FOR TIC TAC TOE BOARD
		//TIME COMPLEXITY: O(c) (DOUBLE CHECK THIS)
		System.out.println("\t0  1  2");
 	for (int i=0; i<3; i++) {
   		System.out.print(i+ "\t");
   		for (int j=0; j<3; j++)
     		System.out.print(board[i][j]+" ");
   		System.out.println();
 	}
}
public static void blankBoard(char[][] gameON){
	 for (int i = 0; i < gameON.length; i++)
	   for (int j = 0; j < gameON[0].length; j++)
	    gameON[i][j] = ' ';
	}

	public static void player1(char[][] board, char player1){
	 System.out.print("\nEnter the row(0-2) and column(0-2). WITH SPACE IN BETWEEN ");
	 int row= scan.nextInt();
	 int column = scan.nextInt();

	 while (board[row][column] != ' ') {
	   System.out.print("\nNO NO NOOOOOO NOT TODAY. CHOOSE SOMEWHERE ELSE");
	   row= scan.nextInt();
	   column = scan.nextInt();
	 }

	 board[row][column] = player1;
	}

//Computer's moves
	public static void CPU(char[][] board, char computer){
	//TIME COMPLEXITY........
	 for (int i = 0; i < board.length; i++) {
	   for (int j = 0; j < board[0].length; j++) {
	     if (board[i][j] == ' ') {
	       board[i][j] = computer;
	       return;
	     }
	   }
	 }
	}

//WINNNNNERRRRRRRRRRR ANNOUNCED
	public static boolean Winner(char[][] GameBoard, int pop, char PLAYER, char COMPUTER){
	 char champ;
	 if (pop == 0)
	   champ = PLAYER;
	 else
	   champ = COMPUTER;
	 int i, j;
	 boolean yay = false;

	//CHECK ROWS FOR WINNER
	 for (i = 0; i < GameBoard.length && !yay; i++) {
	   for (j = 0; j < GameBoard[0].length; j++) {
	     if (GameBoard[i][j] != champ)
	       break;
	   }
	   if (j == GameBoard[0].length)
	     yay = true;
	 }

	//CHECK COLUMNS FOR WINNER
	 for (j = 0; j < GameBoard[0].length && !yay; j++) {
	   for (i = 0; i < GameBoard.length; i++) {
	     if (GameBoard[i][j] != champ)
	       break;
	   }
	   if (i == GameBoard.length)
	     yay = true;
	 }

	//  CHECK DIAGONALS FOR WINNER
	 if (!yay) {
	   for (i = 0; i < GameBoard.length; i++) {
	     if (GameBoard[i][i] != champ)
	       break;
	   }
	   if (i == GameBoard.length)
	     yay = true;
	 }
	 if (!yay) {
	   for (i = 0; i < GameBoard.length; i++) {
	     if (GameBoard[i][GameBoard.length - 1 - i] != champ)
	       break;
	   }
	   if (i == GameBoard.length)
	     yay = true;
	 }
	 return yay;
	}
 }

