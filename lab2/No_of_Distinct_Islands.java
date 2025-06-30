
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class No_of_Distinct_Islands {

	static int[] parent;

	static List<Integer> countDistinctIslands(int[][] grid) {
		int dim0 = grid.length, dim1 = grid[0].length;
		parent = new int[dim0 * dim1];
		int count = 0;
		List<Integer> answer = new ArrayList<>();
		int[] directions = { -1, 0, 1, 0, -1 };
		for(int i=0;i<parent.length;i++) parent[i]=i;
		for (int i = 0; i < dim0; i++) {
			for (int j = 0; j < dim1; j++) {
				if (grid[i][j] == 1) {
					int index = i * dim1 + j;
					count++;

					for (int k = 0; k < 4; k++) {
						int x = i + directions[k], y = j + directions[k + 1];
						int adjIndex = x * dim1 + y;

						if (x >= 0 && x < dim0 && y >= 0 && y < dim1 && grid[x][y] == 1
								&& find(adjIndex) != find(index)) {
							union(adjIndex, index);
							count--;
						}
					}
					answer.add(count);
				}
			}
		}
		return answer;
	}

	static int find(int node) {
		if (parent[node] != node)
			parent[node] = find(parent[node]);
		return parent[node];
	}

	static void union(int node1, int node2) {
		int parent1 = find(node1), parent2 = find(node2);
		if (parent1 != parent2)
			parent[parent2] = parent1;
	}

	public static void main(String[] args) {
            try (Scanner sc = new Scanner(System.in)) {
                int dim0 = sc.nextInt(), dim1 = sc.nextInt();
                int[][] grid = new int[dim0][dim1];
                for (int i = 0; i < dim0; i++)
                    for (int j = 0; j < dim1; j++)
                        grid[i][j] = sc.nextInt();
                List<Integer> answer = countDistinctIslands(grid);
                System.out.println(answer);
            }
	}
}