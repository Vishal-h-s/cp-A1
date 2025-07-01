import java.util.HashSet;
import java.util.Set;

class Solution {
	private int rows; // number of rows in the grid
	private int cols; // number of columns in the grid
	private int[][] grid; // grid representation
	private StringBuilder path; // used to store the path during DFS to identify unique islands
	public int numDistinctIslands(int[][] grid) {
		rows = grid.length; // set the number of rows
		cols = grid[0].length; // set the number of columns
		this.grid = grid; // reference the grid
		Set<String> uniqueIslands = new HashSet<>(); // store unique island paths as strings
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				if (grid[i][j] == 1) { // if it's part of an island
					path = new StringBuilder(); // initialize the path
					exploreIsland(i, j, 'S'); // start DFS with dummy direction 'S' (Start)
					uniqueIslands.add(path.toString()); // add the path to the set
				}
			}
		}
		return uniqueIslands.size(); // the number of unique islands
	}
	private void exploreIsland(int i, int j, char direction) {
		grid[i][j] = 0; // mark as visited
		path.append(direction); // append the direction to path
		// directions represented as delta x and delta y
		int[] dX = { -1, 0, 1, 0 };
		int[] dY = { 0, 1, 0, -1 };
		char[] dirCodes = { 'U', 'R', 'D', 'L' }; // corresponding directional codes
		for (int dir = 0; dir < 4; ++dir) { // iterate over possible directions
			int x = i + dX[dir];
			int y = j + dY[dir];
			if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) { // check for valid next cell
				exploreIsland(x, y, dirCodes[dir]); // recursive DFS call
			}
		}
		path.append('B'); // append backtrack code to ensure paths are unique after recursion return
	}
}