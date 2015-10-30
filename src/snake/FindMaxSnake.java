package snake;

import java.awt.List;
import java.util.ArrayList;
import java.util.Stack;

public class FindMaxSnake{

  public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for (int i=0; i<grid.length; i++)      // initialize the grid state
			for (int j=0; j<grid[0].length; j++)
				state[i][j] = true;
		
		//int length = 0;
		for (int i=0; i<grid.length; i++)       // iterate on every start position
			for (int j=0; j<grid[0].length; j++){

				for (int ii=0; ii<grid.length; ii++)  // everytime needs to reste the state array and clear the sequence list
					for (int jj=0; jj<grid[0].length; jj++)
						state[ii][jj] = true;
				
				sequence.clear();

				state[i][j] = false;
				sequence.add(grid[i][j]);
				findMaxSnake(grid, i, j, 1);
				// System.out.println(i + " " + j + " " + max_length);
			}
				
		System.out.println(max_length);
		for (Object i:max_seq)
			System.out.print(i + " ");
		
	}

private static void findMaxSnake(int[][] grid, int i, int j, int d){
	/*
	if (!state[i][j]){
		max_length = Math.max(max_length, d);
	}else {
	*/
		boolean flag = true;
		for (int ind=0; ind<nn.length; ind+=2){
			int ii = i + nn[ind];
			int jj = j + nn[ind+1];
			if (check(grid, ii, jj, i, j) && state[ii][jj]){ 
				state[ii][jj] = false;
				flag = false;
				sequence.add(grid[ii][jj]);
				findMaxSnake(grid, ii, jj, d+1);
				state[ii][jj] = true;
				sequence.remove(sequence.size()-1);
			}
		}
		if(flag && d > max_length){
			max_length = d;
			max_seq = sequence.toArray();
			
		}

}

private static boolean check(int[][] grid, int ii, int jj, int i, int j){
	if (ii<0 || ii>=grid.length || jj<0 || jj>=grid[0].length) return false;
	
	return(Math.abs(grid[ii][jj] - grid[i][j]) == 1);
}

private static ArrayList<Integer> sequence = new ArrayList<Integer>();
private static Object[]  max_seq;


private static int max_length = 0;

private static int[] nn = {1,0, 0,1, 1,1, -1,0, 0,-1, 1,1, -1,1, 1,-1};

private static int[][] grid = {
			{ 1,  3,  2,  6, 8},
			{-9,  7,  1, -1, 2},
			{ 1,  5,  0,  1, 9}
		};
private static boolean[][] state = new boolean[grid.length][grid[0].length];

}