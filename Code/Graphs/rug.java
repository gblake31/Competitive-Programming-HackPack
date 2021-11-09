import java.util.Scanner;

public class rug 
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		int roomSize = scanner.nextInt();
		
		int rugSize = scanner.nextInt();
		
		int[][] grid = new int[roomSize][roomSize];
		
		scanner.nextLine();
		
		for (int i = 0; i < roomSize; i++)
		{
			String str = scanner.nextLine();
			
			for (int j = 0; j < roomSize; j++)
			{
				grid[i][j] = (str.charAt(j) == 'D') ? 1 : 0;
			}
			
		}
		
		int[][] prefixGrid = new int[roomSize][roomSize];
		prefixGrid[0][0] = grid[0][0];
		
		// Do first row and first col
		for (int i = 1; i < roomSize; i++)
		{
			prefixGrid[0][i] = prefixGrid[0][i - 1] + grid[0][i];
			prefixGrid[i][0] = prefixGrid[i - 1][0] + grid[i][0];
		}
		
		for (int i = 1; i < roomSize; i++)
		{
			for (int j = 1; j < roomSize; j++)
			{
				prefixGrid[i][j] = grid[i][j] + prefixGrid[i - 1][j] + prefixGrid[i][j - 1] - prefixGrid[i - 1][j - 1];
			}
		}
		
		int[] freqArray = new int[20000];
		
		for (int i = rugSize - 1; i < roomSize; i++)
		{
			for (int j = rugSize - 1; j < roomSize; j++)
			{
				int numDirty = prefixGrid[i][j];
				
				if (i >= rugSize)
					numDirty -= prefixGrid[i - rugSize][j];
				if (j >= rugSize)
					numDirty -= prefixGrid[i][j - rugSize];
							
				if (i >= rugSize && j >= rugSize)
					numDirty += prefixGrid[i - rugSize][j - rugSize];
				
				freqArray[numDirty]++;
			}
		}
		
		for (int i = 0; i < freqArray.length; i++)
		{
			if (freqArray[i] == 0)
				continue;
			System.out.println(i + " " + freqArray[i]);
		}	
		
	}
}
