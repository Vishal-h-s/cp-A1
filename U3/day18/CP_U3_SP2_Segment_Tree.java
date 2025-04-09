import java.util.*;

class Node {
    int max, min, sum, lb, rb;
    Node left, right;

    Node() {
        left = null;
        right = null;
        sum = 0;
        min = 0;
        max = 0;
    }

    Node(int lb, int rb) {
        left = null;
        right = null;
        this.lb = lb;
        this.rb = rb;
        sum = 0;
        min = 0;
        max = 0;
    }
}

public class CP_U3_SP2_Segment_Tree {
    static int n;
    static int[] arr;
    static Node root;

    static void print(Node node) {
        if (node == null)
            return;
        print(node.left);
        print(node.right);
        System.out.println(
                "lb " + node.lb + " rb " + node.rb + " sum " + node.sum + " min " + node.min + " max " + node.max);
    }

    static Node build(int lb, int rb) {
        Node node = new Node(lb, rb);
        if (lb == rb) {
            node.sum = node.min = node.max = arr[lb];
        } else {
            int mid = lb + (rb - lb) / 2;
            Node left = build(lb, mid);
            Node right = build(mid + 1, rb);
            node.left = left;
            node.right = right;

            node.sum = left.sum + right.sum;
            node.min = Math.min(left.min, right.min);
            node.max = Math.max(left.max, right.max);
        }
        return node;
    }

    static int rangeSum(Node node, int l, int r) {
        int res = 0;
        if (l == node.lb && r == node.rb)
            res = node.sum;
        else {
            int mid = node.lb + (node.rb - node.lb) / 2;
            if (r <= mid) {
                res = rangeSum(node.left, l, r);
            } else if (mid < l) {
                res = rangeSum(node.right, l, r);
            } else {
                res = rangeSum(node.left, l, mid) + rangeSum(node.right, mid + 1, r);
            }
        }
        return res;
    }

    static int[] rangeExtremum(Node node, int l, int r) {
        int[] res = new int[2];
        if (l == node.lb && r == node.rb) {
            res[0] = node.min;
            res[1] = node.max;
        } else {
            int mid = node.lb + (node.rb - node.lb) / 2;
            if (mid >= r) {
                res = rangeExtremum(node.left, l, r);
            } else if (mid < l) {
                res = rangeExtremum(node.right, l, r);
            } else {
                int[] leftRes = rangeExtremum(node.left, l, mid);
                int[] rightRes = rangeExtremum(node.right, mid + 1, r);
                res[0] = Math.min(leftRes[0], rightRes[0]);
                res[1] = Math.max(leftRes[1], rightRes[1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int q = sc.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        root = build(0, n - 1);
        print(root);
        int res;
        while (q-- > 0) {
            int opt = sc.nextInt();
            switch (opt) {
                case 1:
                    int l = sc.nextInt();
                    int r = sc.nextInt();
                    res = rangeSum(root, l, r);
                    System.out.println(res);
                    break;
                case 2:
                    int idx = sc.nextInt();
                    int val = sc.nextInt();
                    replace(idx, val);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
        sc.close();
    }
}