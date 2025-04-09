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
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class TreapNode {
    int key, priority, size;
    TreapNode left, right;

    TreapNode(int key) {
        this.key = key;
        this.priority = new Random().nextInt();
        this.size = 1;
    }
}

class Treap {
    private TreapNode root;

    // Update the size of a node based on its children's sizes
    private void updateSize(TreapNode node) {
        if (node != null) {
            node.size = 1 + getSize(node.left) + getSize(node.right);
        }
    }

    // Get the size of a node
    private int getSize(TreapNode node) {
        return node == null ? 0 : node.size;
    }

    TreapNode rotateRight(TreapNode parent){
        TreapNode child=parent.left;
        parent.left=child.right;
        child.right=parent;

        updateSize(parent);
        updateSize(child);
        return child;
    }

    TreapNode rotateLeft(TreapNode parent){
        TreapNode child=parent.right;
        parent.right=child.left;
        child.left=parent;

        updateSize(parent);
        updateSize(child);
        return child;
    }

    // Insert a key into the treap
    public void insert(int key) {
        root = insert(root, key);
    }

    private TreapNode insert(TreapNode node, int key) {
        if (node == null) return new TreapNode(key);

        if (key < node.key) {
            node.left = insert(node.left, key);
            if (node.left.priority > node.priority) {
                node = rotateRight(node);
            }
        } else {
            node.right = insert(node.right, key);
            if (node.right.priority > node.priority) {
                node = rotateLeft(node);
            }
        }
        updateSize(node);
        return node;
    }

    // Count nodes with keys greater than a given value
    public int countGreaterThan(int key) {
        return countGreaterThan(root, key);
    }

    private int countGreaterThan(TreapNode node, int key) {
        if (node == null) return 0;
        if (key < node.key) {
            return 1 + getSize(node.right) + countGreaterThan(node.left, key);
        } else {
            return countGreaterThan(node.right, key);
        }
    }
}

public class TreapSolution {
    public int countReversePairs(int[] nums) {
        Treap treap = new Treap();
        int count = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            // Count elements in the treap that are greater than 2 * nums[i]
            count += treap.countGreaterThan(2 * nums[i]);
            // Insert nums[i] into the treap
            treap.insert(nums[i]);
        }

        return count;
    }

    public static void main(String[] args) {
        TreapSolution rpc=new TreapSolution();
        Scanner sc=new Scanner(System.in);
        int[] nums=Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        System.out.println(rpc.countReversePairs(nums));
        sc.close();
    }
}
