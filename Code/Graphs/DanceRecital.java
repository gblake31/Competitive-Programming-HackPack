import java.util.Arrays;
import java.util.Scanner;

public class DanceRecital 
{
	static int minimum = Integer.MAX_VALUE;
	static String[] routines;
	static int[][] changes;
	
	public static int calculate(int[] perm)
	{
		boolean[] freq = new boolean[26];
		
		int total = 0;
		
		for (int i = 0; i < perm.length - 1; i++)
		{
			total += changes[perm[i]][perm[i + 1]];
		}
		
		return total;
	}
	
	public static void permute(int[] perm, int numFixed, boolean[] used)
	{
		if (numFixed == perm.length)
		{
			int numQuickChanges = calculate(perm);
			
			if (numQuickChanges < minimum)
				minimum = numQuickChanges;
			
			return;
		}
		
		for (int i = 0; i < perm.length; i++)
		{
			if (!used[i])
			{
				perm[numFixed] = i;
				used[i] = true;
				permute(perm, numFixed + 1, used);
				used[i] = false;
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		int numRoutines = scanner.nextInt();
		
		routines = new String[numRoutines];
		
		for (int i = 0; i < numRoutines; i++)
		{
			routines[i] = scanner.next();
		}
		
		changes = new int[numRoutines][numRoutines];
		
		for (int i = 0; i < numRoutines - 1; i++)
		{
			
			for (int j = i + 1; j < numRoutines; j++)
			{
				int total = 0;
				boolean[] freq = new boolean[26];
		
				for (int k = 0; k < routines[i].length(); k++)
				{
					freq[routines[i].charAt(k) - 'A'] = true;
				}
				
				
				for (int k = 0; k < routines[j].length(); k++)
				{
					if (freq[routines[j].charAt(k) - 'A'])
					{
						total++;
					}
				}
				
	
				changes[i][j] = total;
				changes[j][i] = total;
				
				for (int k = 0; k < 26; k++)
				{
					freq[k] = false;
				}
			
			}
	
		}
		
		int[] perm = new int[numRoutines];
		boolean[] used = new boolean[numRoutines];
		
		permute(perm, 0, used);
		
		System.out.println(minimum);
		
		
	}
}
