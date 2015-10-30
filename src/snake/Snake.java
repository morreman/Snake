package snake;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Snake {

    private int columns, rows, blocks;
    private boolean [][] daVector = new boolean[columns][rows];
    
    public Snake() {
    	try {
            parseText();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    
    
    

	public static void main(String[] args) {
        Snake snake = new Snake();  
    }
}
