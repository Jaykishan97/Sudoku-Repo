package com.sudoku.game;
import java.util.Scanner;

public class Sudoku {
	private static final int SIZE = 9; // size of the board
	private static final int BOX_SIZE = 3; // size of each box
	private int[][] board; // the Sudoku board

	public Sudoku() {
		board = new int[SIZE][SIZE];
	}

	public void play() {
		Scanner input = new Scanner(System.in);

		while (!isComplete()) {
			displayBoard();

			System.out.print("Enter the row number (1-9): ");
			int row = input.nextInt() - 1;

			System.out.print("Enter the column number (1-9): ");
			int col = input.nextInt() - 1;

			if (board[row][col] != 0) {
				System.out.println("That cell is already filled.");
				continue;
			}

			System.out.print("Enter the value (1-9): ");
			int value = input.nextInt();

			if (!isValid(row, col, value)) {
				System.out.println("Invalid move. Please try again.");
				continue;
			}

			board[row][col] = value;
		}

		System.out.println("Congratulations! You have completed the Sudoku puzzle!");
		displayBoard();
	}

	private boolean isValid(int row, int col, int value) {
		// check row and column
		for (int i = 0; i < SIZE; i++) {
			if (board[row][i] == value || board[i][col] == value) {
				return false;
			}
		}

		// check box
		int boxRow = (row / BOX_SIZE) * BOX_SIZE;
		int boxCol = (col / BOX_SIZE) * BOX_SIZE;
		for (int i = boxRow; i < boxRow + BOX_SIZE; i++) {
			for (int j = boxCol; j < boxCol + BOX_SIZE; j++) {
				if (board[i][j] == value) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean isComplete() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	private void displayBoard() {
		System.out.println(" -----------------------");
		for (int i = 0; i < SIZE; i++) {
			System.out.print("| ");
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == 0) {
					System.out.print(". ");
				} else {
					System.out.print(board[i][j] + " ");
				}
				if (j % BOX_SIZE == BOX_SIZE - 1) {
					System.out.print("| ");
				}
			}
			System.out.println();
			if (i % BOX_SIZE == BOX_SIZE - 1) {
				System.out.println(" -----------------------");
			}
		}
	}

	public static void main(String[] args) {
		Sudoku game = new Sudoku();
		game.play();
	}
}
