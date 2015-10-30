package snake;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class snakefun {

    private int columns, rows, blocks;
    private boolean [][] daVector;

    public snakefun() {
    	try {
            parseText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	getSequence();
    }
    
    interface ISnakeProcessor
    {
        void process(ArrayList<Pair<Integer, Integer>> snake);
    }

    public void parseText() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/Marten/Desktop/Skolan/Applikationer/Föreläsningar/DA345AF8HT15/text.txt"));
        String[] nmk;
        String[][] xy;
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            nmk = line.split(",");
            rows = Integer.parseInt(nmk[0]);
            columns = Integer.parseInt(nmk[1]);
            blocks = Integer.parseInt(nmk[2]);
            line = br.readLine();
            String[] xyArray = new String[columns + rows];
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
                String everything = sb.toString();
                xyArray = everything.split("\n");
            }
            vectorPostitions(xyArray);
        } finally {
            br.close();
        }
    }

    public void vectorPostitions(String[] blockPositions) {
		daVector = new boolean[columns][rows];		
		String[] temp;
		String[] xy = new String[blockPositions.length];
		for(int i =0; i< blockPositions.length; i++){
			temp = blockPositions[i].split(",");
			int b = Integer.parseInt(temp[0]);
			int a = Integer.parseInt(temp[1]);
			daVector[a][b] = true;
		}
		for(int i = 0; i< daVector.length; i++){
			for(int j = 0; j<daVector[i].length; j++){
					System.out.print(daVector[i][j] + "\t" );					
			}
		System.out.println();
		}
	}
    
    private boolean continuesInRow(int iRow, int iCol){
        if (iRow < 0 || iRow >= rows-1)
            return false;
        if (iCol < 0 || iCol >= columns)
            return false;
        boolean myVal = daVector[iRow][iCol];
        if (daVector[iRow+1][iCol] == myVal ==false || daVector[iRow+1][iCol] == myVal == true)
            return true;
        return false;
    }
    
    private boolean continuesInCol(int iRow, int iCol){
        if (iRow < 0 || iRow >= rows)
            return false;
        if (iCol < 0 || iCol >= columns-1)
            return false;
        boolean myVal = daVector[iRow][iCol];
        if (daVector[iRow][iCol+1] == myVal == false || daVector[iRow][iCol+1] == myVal == true)
            return true;
        return false;
    }
    
    private boolean isHead(int iRow, int iCol){
        if (iRow < 0 || iRow >= rows)
            return false;
        if (iCol < 0 || iCol >= columns)
            return false;

        boolean myVal = daVector[iRow][iCol];
        if (iRow > 0 && (daVector[iRow-1][iCol] == myVal == false || daVector[iRow-1][iCol] == myVal == true))
            return false;
        if (iCol > 0 && (daVector[iRow][iCol-1] == myVal == false || daVector[iRow][iCol-1] == myVal == true))
            return false;
        return true;
    }
    
    private void walkSnake(ISnakeProcessor processor, int iRow, int iCol, ArrayList<Pair<Integer, Integer>> snake)
    {
        snake.add(new Pair<Integer, Integer>(iRow, iCol));
        
        boolean isTail = true;
        if (continuesInRow(iRow, iCol))
        {
            walkSnake(processor, iRow+1, iCol, snake);
            isTail = false;
        }
        if (continuesInCol(iRow, iCol)) 
        {
            walkSnake(processor, iRow, iCol+1, snake);
            isTail = false;
        }
        if (isTail)
        {
            processor.process(snake);
        }
        snake.remove(snake.size() - 1);
    }
    
    class LongestSnakeFinder implements ISnakeProcessor{
       
        ArrayList<Pair<Integer, Integer>> longest = new ArrayList<Pair<Integer, Integer>>();

       
        
        public LongestSnakeFinder(snakefun snakefun) {
		}

		public void process(ArrayList<Pair<Integer, Integer>> snake)
        {
            if (snake.size() > longest.size())
            {
                longest.clear();
                longest.addAll(snake);
            }
        }
        
        public void dumpLongest(){
            System.out.format("The first encountered longest snake has length %d:\n", longest.size());
            for (int i = 0; i < longest.size(); i++){
                int iRow = longest.get(i).getFirst();
                int iCol = longest.get(i).getSecond();
                System.out.format("   (%d,%d): %d\n", iRow, iCol, rows, columns);
            }
        }
    }
    public void getSequence() {
        LongestSnakeFinder finder = new LongestSnakeFinder(this);
        finder.dumpLongest();
    }

	public static void main(String[] args) {
        Snake snake = new Snake();     
    }
}
