package game;

import java.util.Random;
import java.util.Scanner;

public class TTTGame {
	public static void main(String Args[]){
		Scanner scan = new Scanner(System.in);
		
		//Asks for players names
	   	System.out.println("Player 1:  ");
        String name1 = scan.next();
	   	System.out.println("Player 2:  ");
	   	String name2 = scan.next();
		
	   	//CREATES A NEW TIC TAC TOE BOARD
	   	TTTGame game = new TTTGame(name1, name2);
    	
    	while (game.winner() == '_') {
      		int row,column;
      		boolean done = false;
      		do {
	
        		game.gameBoard();

        		System.out.print(game.CurrentPlayer() + ": ");            
        		System.out.print("Enter the row(0-2) and column(0-2). WITH SPACE IN BETWEEN ");

      			row = scan.nextInt();
      			column = scan.nextInt();	
        		
      			if (!game.inside(row,column)) 
          			System.out.println("WOAH HOTSHOT, THAT ISN'T IN THE GAME. TRY AGAIN");
        		else {
          			if (!game.validMove(row,column))
            			System.out.println("NO NO NOOOOOO NOT TODAY. CHOOSE SOMEWHERE ELSE");
          			else
            			done = true;
       			}  
      		} while (!done);
	
      		//Next person's turn
      		game.takeTurn();
    	}

    	// Prints out message with winner
    	game.gameBoard();
    	char win = game.winner();

    	if (win == 'T')
      		System.out.println("**********TIE GAME**********");
    	else {
      		System.out.print("AND THE WINNER IS....... " + game.whosePiece(win));
    	}
    	scan.close();
	}
	private char[]letter = {'#','@'};
	private String[] players;
	int numMoves;
	private char[][] board;
	private int turn;
	
	
	// METHOD TAKES IN PLAYERS' NAMES , STARTS NEW GAME W/ NEW BOARD
	public TTTGame(String p1, String p2){
	board = new char[3][3];
	//TIME COMPLEXITY
	for(int i=0; i < 3; i++)
		for(int j = 0; j< 3; j++)
			board[i][j]= '_';
	
	turn = 0;
	numMoves = 0;
	players = new String[2];
	players[0] = p1;
	players[1] = p2;
	}
	
	//check to see if move input is valid
	public boolean validMove(int horizon, int vertical){
		
	if((board[horizon][vertical]=='_')&& inside(horizon, vertical)){
		board[horizon][vertical]= letter[turn];
		numMoves++;
		return true;
	}
else
		return false;
}  
	public boolean inside(int horizon, int vertical){
		if ((horizon < 0) || (vertical < 0))
      		return false;
    	if ((horizon > 2) || (vertical> 2))
      		return false;
    	return true;
	}
	public void takeTurn(){
		turn = (turn + 1)%2;
	}
	
	public String CurrentPlayer(){
		return players[turn];
		
	}
	public void gameBoard(){
	//CREATES LABELS FOR TIC TAC TOE BOARD
		//TIME COMPLEXITY: O(c) (DOUBLE CHECK THIS)
		System.out.println("\t0  1  2");
    	for (int i=0; i<3; i++) {
      		System.out.print(i+"\t");
      		for (int j=0; j<3; j++)
        		System.out.print(board[i][j]+"  ");
      		System.out.println();
    	}
	}
    //CHECKS FOR WINNER IN THE GAME
	public char winner() {

    		//LOOP THROUGH THE BOARD 
        	for (int i=0; i<3; i++)
        		//CHECKS FOR WINNER ON ALL ROWS 
          		if (occupiedSpot(board[i]) && board[i][0] != '_')
            		return board[i][0];

    		
        		// CHECKS FOR WINNER IN ALL COLUMNS 
          		else if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && board[0][i] != '_')
             		return board[0][i];

    		// CHECKS FOR WINNER IN DIAGONAL
          		else if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]))
             	return board[0][0];

    		// CHECKS FOR WINNER IN REVERSE DIAGONAL 
          		else if ((board[2][0] == board[1][1]) && (board[1][1] == board[0][2]))
             	return board[2][0];

        	if (numMoves == 9)
          		return 'T';

        	return '_';
      	}	
    	//CHECK TO VERIFY THAT NO SPOT IS USED TWICE
    	private static boolean occupiedSpot(char[] s) {
    		//O(N) 
        	char check = s[0];
        	for (int i=1; i<s.length; i++)
          		if (check != s[i])
            		return false;
        	return true;
      	}

      	// Returns the player who's playing piece is the character x
      	public String whosePiece(char x) {
        
        	for (int i=0; i<2; i++)
          		if (x == letter[i])
            		return players[i];
        	return "That is incorrect";
      	}

	}
