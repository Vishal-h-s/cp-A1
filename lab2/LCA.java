
import java.util.*;

class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}

public class LCA {
    // Helper method to build the tree from the input list
    private static Node buildTree(List<Integer> v) {
        if (v.isEmpty() || v.get(0) == -1)
            return null;

        Node root = new Node(v.get(0));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < v.size()) {
            Node current = queue.poll();

            if (i < v.size() && v.get(i) != -1) {
                current.left = new Node(v.get(i));
                queue.add(current.left);
            }
            i++;

            if (i < v.size() && v.get(i) != -1) {
                current.right = new Node(v.get(i));
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] arr = sc.nextLine().split(" ");
            String[] persons = sc.nextLine().split(" ");

            List<Integer> v = new ArrayList<>();
            int n = arr.length;
            for (int i = 0; i < n; i++) {
                v.add(Integer.valueOf(arr[i]));
            }

            Node P1 = new Node(Integer.parseInt(persons[0]));
            Node P2 = new Node(Integer.parseInt(persons[1]));

            // Write necessary code
            Node root = buildTree(v);
            Node res = new Solution().lowestCommonAscendant(root, P1, P2);
            System.out.println(res.data);
        } 
    }
}

class Solution {
    private Node dfs(Node root, Node p, Node q) {
        if (root == null || root.data == p.data || root.data == q.data) {
            return root;
        }

        Node left = dfs(root.left, p, q);
        Node right = dfs(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return (left != null) ? left : right;

    }

    public Node lowestCommonAscendant(Node root, Node p, Node q) {
        return dfs(root, p, q);
    }

}