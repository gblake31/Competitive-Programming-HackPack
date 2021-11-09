import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// We need this in order to keep track of the weights of each edge
// These go into our adjacency list.
class Edge
{
	int to;
	int weight;
	
	Edge(int node, int w)
	{
		to = node;
		weight = w;
	}
}

// This is what will be stored in the priority queue, we go to the smallest dist node first.

public class BellmanFord 
{
	static ArrayList<Edge>[] adjList;
	static int inf = (int) 1e9;
	
	static boolean hasNegativeCycle;
	static int indexInQuestion;

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int numCases = scanner.nextInt();
		
		for (int k = 1; k <= numCases; k++)
		{
			int numNodes = scanner.nextInt();
			int numEdges = scanner.nextInt();
			
			adjList = new ArrayList[numNodes];
			
			for (int i = 0; i < numNodes; i++)
			{
				adjList[i] = new ArrayList<Edge>();
			}
			
			for (int i = 0; i < numEdges; i++)
			{
				int from = scanner.nextInt();
				int to = scanner.nextInt();
				int weight = scanner.nextInt();
				
				// add an edge in both directions of the same weight
				adjList[from].add(new Edge(to, weight));
			}
			
			int[] res = runBellmanFord(0);
			
			if (!hasNegativeCycle)
			{
				System.out.println("Case #" + k + ": " + "Not Possible");
			}
			
			else
			{
				boolean[] visited = new boolean[numNodes];
				System.out.println("Case #" + k + ": " + (dfs(0, indexInQuestion, visited) ? "Possible" : "Not Possible"));
			}
			
			
		}
		
	}
	
	public static int[] runBellmanFord(int source)
	{
		// Initialize the data structures we'll need.
		int[] distances = new int[adjList.length];
		Arrays.fill(distances, inf);
		distances[source] = 0;
		
		// Process all nodes
		for (int i = 0; i < adjList.length - 1; i++)
		{
			for (int u = 0; u < adjList.length; u++)
			{
				for (int v = 0; v < adjList[u].size(); v++)
				{
					distances[adjList[u].get(v).to] = Math.min(distances[adjList[u].get(v).to], distances[u] + adjList[u].get(v).weight);
				}
			}
		}
		
		hasNegativeCycle = false;
		// IMPORTANT: Check for negative cycle
		for (int u = 0; u < adjList.length; u++)
		{
			for (int v = 0; v < adjList[u].size(); v++)
			{
				if (distances[u] + adjList[u].get(v).weight < distances[adjList[u].get(v).to])
				{
					System.out.println("We found a negative cycle!!");
				}
			}
		}
		
		return distances;
		
	}
	
}
