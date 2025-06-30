import java.util.Scanner;

public class LongestIncreasingPath {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // up, down, left, right
    static int rows, cols;
    static int[][] matrix, memo;
    
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        rows = matrix.length;
        cols = matrix[0].length;
        memo = new int[rows][cols];
        int maxLength = 0;
        
        // Try starting from each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxLength = Math.max(maxLength, dfs(matrix, i, j));
            }
        }
        
        return maxLength;
    }
    
    private static int dfs(int[][] matrix, int row, int col) {
        // If already computed, return memoized result
        if (memo[row][col] > 0) return memo[row][col];
        
        int max = 1; // Minimum path length is 1 (current cell)
        
        // Try all four directions
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            // Check bounds and increasing condition
            if (newRow >= 0 && newRow < rows && 
                newCol >= 0 && newCol < cols && 
                matrix[newRow][newCol] > matrix[row][col]) {
                
                max = Math.max(max, 1 + dfs(matrix, newRow, newCol));
            }
        }
        
        // Memoize and return
        memo[row][col] = max;
        return max;
    }
    
    public static void main(String[] args) {
        // Read matrix dimensions
        try (Scanner sc = new Scanner(System.in)) {
            // Read matrix dimensions
            rows = sc.nextInt();
            cols = sc.nextInt();
            
            // Read matrix
            matrix = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }
            
            // Find and print result
            System.out.println(longestIncreasingPath(matrix));
        }
    }
}