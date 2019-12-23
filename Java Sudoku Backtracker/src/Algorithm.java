import java.util.*;
public class Algorithm {
	 /*
	 * This class contains all the methods to solve
	 * a sudoku board recursively using backtracking
	 */
	public static int[][] board = {
			{7,8,0,4,0,0,1,2,0},
		    {6,0,0,0,7,5,0,0,9},
		    {0,0,0,6,0,1,0,7,8},
		    {0,0,7,0,4,0,2,6,0},
		    {0,0,1,0,5,0,9,3,0},
		    {9,0,4,0,6,0,0,0,5},
		    {0,7,0,3,0,0,0,1,2},
		    {1,2,0,0,0,7,4,0,0},
		    {0,4,9,2,0,6,0,0,7}
	};
	
	
	
	public static void main(String[] args) {
		System.out.println("BEFORE: ");
		printBoard(board);
		System.out.println();
		printBoard(board);
		System.out.println();
		System.out.println("AFTER: ");
		solve(board);
		

	
	}
	
	
	
	/*
	 * METHOD 1: Prints the game board to console
	 */
	
	public static void printBoard(int[][] board) {
		
		for (int i = 0; i < board.length; i++) {
			if ((i % 3 == 0) && (i != 0)) {
				System.out.println("---------------------------");
			}
			
			for (int j = 0; j < board[0].length; j++) {
				if ((j % 3 == 0) && (j != 0)) {
					 System.out.printf("%7s","|   ", " ");
				}
				if(j == 8) {
					System.out.println(board[i][j]);
					
				
				} else {
					 System.out.printf("%1s",board[i][j], " ");
					
				}
			}
		}
		
	}
	/*
	 * METHOD 2: The following method finds any empty spots inside the grid 
	 * to place a number to find a possible solution to the board
	 */
	public static Tuple<Integer, Integer> findEmpty(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0) {
					Tuple<Integer, Integer> pos = new Tuple<Integer, Integer>(i,j);
					return pos;
				}
				
			}
			
		}
			
		return null;
		
	}
	
	public static boolean valid(int[][] board, int number, Tuple<Integer, Integer> position) {
		//Checks rows
		for (int i = 0; i < board[0].length; i++) {
			if (board[position.x][i] == number && position.y != 1) {
				return false;
			}
		}
		
		//Checks Columns
		for (int i = 0; i < board.length; i++) {
			if (board[i][position.y] == number && position.x != i) {
				return false;
			}
		}
		
		//Checks 3x3 Grid
		
		int boxX = position.y / 3;
		int boxY = position.x / 3;
		
		for (int i = boxY * 3; i < boxY * 3 + 3; i++) {
			for (int j = boxX * 3; j < boxX * 3 + 3; j++) {
				Tuple<Integer, Integer> tup = new Tuple<Integer, Integer>(i, j);
				if (board[i][j] == number && tup != position) {
					return false;
				}
			}	
		}
		return true;
	}
	
	@SuppressWarnings("null")
	public static boolean solve(int[][] board) {
		
		//Basic Step 
		Tuple<Integer, Integer> find = findEmpty(board);
		Tuple<Integer, Integer> pos = null;
		
		if (find == null) {
			printBoard(board);
			return true;
		} else {
			pos = new Tuple<Integer, Integer>((int) find.getX(), (int) find.y);
		}
		
		//Recursive Step
		for (int i = 0; i < 10; i++) {
			if (valid(board, i, pos)) {
				board[(int) pos.getX()][(int) pos.getY()] = i;
				if (solve(board)) {
					return true;
				}
				board[(int) pos.getX()][(int) pos.getY()] = 0;
			}
		}

		return false;
		
		
	}

}
