/*
 * Given an integer array nums, return the number of reverse pairs in the array. A reverse pair is a 
pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j]. 
Example 1: 
Input: nums = [1,3,2,3,1] 
Output: 2 
Example 2: 
Input: nums = [2,4,3,5,1] 
Output: 3 
Constraints: 
1 <= nums.length <= 5 * 104 -2^31 <= nums[i] <= 2^31 – 1
 */
import java.util.Random;

class Node {
    int key, priority, count; // `count` stores the size of the subtree rooted at this node
    Node left, right;

    public Node(int key) {
        this.key = key;
        this.priority = new Random().nextInt(); // Assign a random priority
        this.count = 1; // Initially, the size of the subtree is 1 (the node itself)
        this.left = null;
        this.right = null;
    }
}

public class Treap {
    private Node root;

    // Rotate right
    private Node rotateRight(Node parent) {
        Node child = parent.left;
        Node temp = child.right;

        // Perform rotation
        child.right = parent;
        parent.left = temp;

        // Update subtree sizes
        updateCount(parent);
        updateCount(child);

        return child;
    }

    // Rotate left
    private Node rotateLeft(Node parent) {
        Node child = parent.right;
        Node temp = child.left;

        // Perform rotation
        child.left = parent;
        parent.right = temp;

        // Update subtree sizes
        updateCount(parent);
        updateCount(child);

        return child;
    }

    // Update the size of the subtree rooted at a node
    private void updateCount(Node node) {
        if (node != null) {
            node.count = 1 + getCount(node.left) + getCount(node.right);
        }
    }

    private int getCount(Node node) {
        return node == null ? 0 : node.count;
    }

    // Insert a key into the Treap
    public Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        // BST insertion
        if (key < node.key) {
            node.left = insert(node.left, key);

            // Heap property violation
            if (node.left.priority > node.priority) {
                node = rotateRight(node);
            }
        } else {
            node.right = insert(node.right, key);

            // Heap property violation
            if (node.right.priority > node.priority) {
                node = rotateLeft(node);
            }
        }

        // Update the size of the subtree
        updateCount(node);

        return node;
    }

    // Query the Treap to count elements greater than a given value
    public int countGreater(Node node, long value) {
        if (node == null) {
            return 0;
        }

        if (node.key > value) {
            // Count this node and all nodes in the right subtree
            return 1 + getCount(node.right) + countGreater(node.left, value);
        } else {
            // Skip this node and move to the right subtree
            return countGreater(node.right, value);
        }
    }

    // Wrapper for insert
    public void insert(int key) {
        root = insert(root, key);
    }

    // Wrapper for countGreater
    public int countGreater(long value) {
        return countGreater(root, value);
    }

    // Solve the reverse pairs problem
    public int reversePairs(int[] nums) {
        int count = 0;

        // Iterate through the array from right to left
        for (int i = nums.length - 1; i >= 0; i--) {
            // Count elements greater than 2 * nums[i]
            count += countGreater(2L * nums[i]);

            // Insert nums[i] into the Treap
            insert(nums[i]);
        }

        return count;
    }

    public static void main(String[] args) {
        Treap treap = new Treap();

        // Example 1
        int[] nums1 = {1, 3, 2, 3, 1};
        System.out.println("Output: " + treap.reversePairs(nums1)); // Output: 2

        // Example 2
        int[] nums2 = {2, 4, 3, 5, 1};
        System.out.println("Output: " + treap.reversePairs(nums2)); // Output: 3
    }
}