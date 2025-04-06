/*
 * Bubloo is working with computer networks, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) 
is uniquely identified by an integer value.

Bubloo has been assigned an important task: find the shortest communication 
path (in terms of network hops) between two specific servers in the network.

Network Structure:
------------------
The network of servers follows a binary tree topology.
Each server (node) has a unique identifier (integer).
If a server does not exist at a certain position, it is represented as '-1' (NULL).

Given the root of the network tree, and two specific server IDs (E1 & E2), 
determine the minimum number of network hops (edges) required to 
communicate between these two servers.

Input Format:
-------------
Line-1: Space separated integers, elements of the tree.
Line-2: Two Space separated integers, represents node ids.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
4 8

Sample Output-1:
----------------
4

Explanation:
------------
The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


Sample Input-2:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
6 6

Sample Output-2:
----------------
0

Explanation:
------------
No edges between 6 and 6.

 */

 import java.util.*;

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class TreeBuilder {
    public TreeNode buildTree(int[] levelorder) {
        int rootvalue = levelorder[0];
        TreeNode root = new TreeNode(rootvalue);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        int n = levelorder.length;
        while (i < n) {
            TreeNode node = q.poll();
            if (i < n && levelorder[i] != -1) {
                node.left = new TreeNode(levelorder[i]);
                q.offer(node.left);
            }
            i++;
            if (i < n && levelorder[i] != -1) {
                node.right = new TreeNode(levelorder[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    public int distancebetweennodes(TreeNode root, int point1, int point2) {
        // Find the lowest common ancestor (LCA) of the two nodes
        TreeNode lca = findlca(root, point1, point2);

        // Calculate the distance from LCA to point1 and point2
        int dist1 = calculatedistance(lca, point1, 0);
        int dist2 = calculatedistance(lca, point2, 0);

        // Return the total distance
        return dist1 + dist2;
    }

    public int calculatedistance(TreeNode root, int point, int dist) {
        if (root == null) {
            return -1; // Point not found
        }
        if (root.val == point) {
            return dist;
        }

        // Search in the left subtree
        int left = calculatedistance(root.left, point, dist + 1);
        if (left != -1) {
            return left;
        }

        // Search in the right subtree
        return calculatedistance(root.right, point, dist + 1);
    }

    public TreeNode findlca(TreeNode root, int point1, int point2) {
        if (root == null) {
            return null;
        }
        if (root.val == point1 || root.val == point2) {
            return root;
        }

        // Search for LCA in the left and right subtrees
        TreeNode leftLCA = findlca(root.left, point1, point2);
        TreeNode rightLCA = findlca(root.right, point1, point2);

        // If both left and right subtrees return non-null, this is the LCA
        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        // Otherwise, return the non-null subtree
        return (leftLCA != null) ? leftLCA : rightLCA;
    }
}

class FS_AP14_FindDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the tree as a level-order array
        String[] s = sc.nextLine().split(" ");
        int n = s.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(s[i]);
        }

        // Input the two points
        int point1 = sc.nextInt();
        int point2 = sc.nextInt();

        // Build the tree and calculate the distance
        TreeBuilder tree = new TreeBuilder();
        TreeNode root = tree.buildTree(a);
        System.out.println(tree.distancebetweennodes(root, point1, point2));

        sc.close();
    }
}