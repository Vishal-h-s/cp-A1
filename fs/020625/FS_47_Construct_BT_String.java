
/*
 * Imagine you’re decoding a secret message that outlines the hierarchical structure 
of a covert spy network. The message is a string composed of numbers and parentheses. 
Here’s how the code works:

- The string always starts with an agent’s identification number, this is the 
  leader of the network.
- After the leader’s ID, there can be zero, one, or two segments enclosed in 
  parentheses. Each segment represents the complete structure of one subordinate 
  network.
- If two subordinate networks are present, the one enclosed in the first (leftmost) 
  pair of parentheses represents the left branch, and the second (rightmost) 
  represents the right branch.

Your mission is to reconstruct the entire spy network hierarchy based on this 
coded message.

Sample Input-1:
---------------
4(2(3)(1))(6(5))

Sample Output-1: 
----------------
[4, 2, 6, 3, 1, 5]

Spy network:
        4
       / \
      2   6
     / \  /
    3   1 5

Explanation:
Agent 4 is the leader.
Agent 2 (with its own subordinates 3 and 1) is the left branch.
Agent 6 (with subordinate 5) is the right branch.

Sample Input-2:
---------------
4(2(3)(-1))(6(5)(7))

Sample Output-2:
-----------------
[4, 2, 6, 3, -1, 5, 7]

Spy network:
         4
       /   \
      2     6
     / \   / \
    3   1 5   7

Explanation:
Agent 4 leads the network.
Agent 2 with subordinates 3 and 1 forms the left branch.
Agent 6 with subordinates 5 and 7 forms the right branch.

 */
import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    this.val = x;
  }
}

class Solution {
  public TreeNode str2tree(String s) {
    if (s.isEmpty())
      return null;

    Stack<TreeNode> stack = new Stack<>();
    int i = 0;

    while (i < s.length()) {
      char c = s.charAt(i);

      if (c == ')') {
        stack.pop(); // End of a subtree
        i++;
      } else if (c == '(') {
        i++; // Start of a subtree
      } else {
        // Parse the number
        int start = i;
        while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '-')) {
          i++;
        }
        int val = Integer.parseInt(s.substring(start, i));
        TreeNode node = new TreeNode(val);

        if (!stack.isEmpty()) {
          TreeNode parent = stack.peek();
          if (parent.left == null) {
            parent.left = node;
          } else {
            parent.right = node;
          }
        }
        stack.push(node);
      }
    }

    return stack.isEmpty() ? null : stack.peek();
  }
}

public class FS_47_Construct_BT_String {
  static List<Integer> levelOrderTraversal(TreeNode root) {
    List<Integer> levelOrder = new ArrayList<>();
    if (root == null)
      return levelOrder;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      TreeNode current = queue.poll();
      levelOrder.add(current.val);

      if (current.left != null) {
        queue.offer(current.left);
      }
      if (current.right != null) {
        queue.offer(current.right);
      }
    }

    return levelOrder;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String line = sc.nextLine().trim();
    if (line.isEmpty()) {
      sc.close();
      return;
    }

    TreeNode root = new Solution().str2tree(line);
    System.out.print(levelOrderTraversal(root));
    sc.close();
  }
}