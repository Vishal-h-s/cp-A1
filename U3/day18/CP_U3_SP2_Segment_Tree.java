/*
 * 
 * 
 */

import java.util.Arrays;
import java.util.Scanner;

class TreeNode {
    int largest, smallest, summation, leftBound, rightBound;
    TreeNode left, right;

    TreeNode() {
        left = null;
        right = null;
    }

    TreeNode(int lb, int rb) {
        left = null;
        right = null;
        leftBound = lb;
        rightBound = rb;
        summation = 0;
        // smallest = Integer.MAX_VALUE;
        // largest = Integer.MIN_VALUE;
        smallest = 0;
        largest = 0;
    }
}

public class CP_U3_SP2_Segment_Tree {
    static int len;
    static int[] nums;
    static TreeNode tree;

    static void printTree(TreeNode node) {
        if (node == null)
            return;
        else {
            printTree(node.left);
            printTree(node.right);
            System.out.println(
                    "leftBound " + node.leftBound + "rightBound " + node.rightBound + "sum " + node.summation + "min "
                            + node.smallest + "max " + node.largest);
        }
    }

    static TreeNode populateTree(int leftBound, int rightBound) {
        TreeNode node = new TreeNode(leftBound, rightBound);
        if (leftBound == rightBound) {
            node.summation = node.smallest = node.largest = nums[leftBound];
        } else {
            int mid = leftBound + (rightBound - leftBound) / 2;
            TreeNode leftNode = populateTree(leftBound, mid);
            TreeNode rightNode = populateTree(mid + 1, rightBound);
            // head recursion
            node.left = leftNode;
            node.right = rightNode;

            node.summation = leftNode.summation + rightNode.summation;
            node.smallest = Math.min(leftNode.smallest, rightNode.smallest);
            node.largest = Math.max(leftNode.largest, rightNode.largest);
        }
        return node;
    }

    static int inclusiveRangeSum(TreeNode node, int startIndex, int endIndex) {
        int result = 0;
        if (startIndex == node.leftBound && endIndex == node.rightBound)
            result = node.summation;
        else {
            int treeMid = node.leftBound + (node.rightBound - node.leftBound) / 2;
            if (endIndex <= treeMid) {
                result= inclusiveRangeSum(node.left, startIndex, endIndex);
            } else if (treeMid < startIndex) {
                result= inclusiveRangeSum(node.right, startIndex, endIndex);
            } else {
                result=inclusiveRangeSum(node.left, startIndex, treeMid)+inclusiveRangeSum(node.right, treeMid+1, endIndex)
            }
        }
        return result;
    }

    static int[] inclusiveRangeExtremum(TreeNode node, int startIndex, int endIndex){
        int[] result=new int[2];
        if(startIndex == node.leftBound && endIndex==node.rightBound){
            result[0]=node.smallest;result[1]=node.largest;
        }else{
            int treeMid=
        }

        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        len = sc.nextInt();
        int queries = sc.nextInt();
        nums = new int[len];
        for (int idx = 0; idx < len; idx++)
            nums[idx] = sc.nextInt();
        tree = populateTree(0, len - 1);
        // populatetree2();
        printTree(tree);
        int result;
        while (queries-- > 0) {
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    int leftBound = sc.nextInt();
                    int rightBound = sc.nextInt();
                    result = inclusiveRangeSum(tree, leftBound, rightBound);
                    System.out.println(result);
                    break;
                case 2:
                    int index = sc.nextInt();
                    int newValue = sc.nextInt();
                    replace(index, newValue);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        sc.close();
    }
}
