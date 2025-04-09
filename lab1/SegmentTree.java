
/*
 * The provided code is a solution to the "Maximum Number of Events That Can Be Attended" problem using a Segment Tree. Below is a breakdown of the code, its functionality, and how it works.
 * 
 * Problem Description:
You are given an array of events, where each event is represented as [startDay, endDay]. Each event can only be attended on one day within its range [startDay, endDay]. The goal is to find the maximum number of events that can be attended.

Sample Input-1: 
4 
1 2,2 4,2 3,2 2 
Sample Output-1: 
4 
Sample Input-2: 
6 
1 5,2 3,2 4,2 2,3 4,3 5 
Sample Output-2: 
5

 */
import java.util.*;

class Node {
    int min, lb, rb;
    Node left, right;

    Node() {
        left = null;
        right = null;
        min = 0;
    }

    Node(int lb, int rb) {
        left = null;
        right = null;
        min = 0;
        this.lb = lb;
        this.rb = rb;
    }
}

public class SegmentTree {

    static Node root;

    static Node build(int lb, int rb) {
        Node node = new Node(lb, rb);
        if (lb == rb)
            node.min = lb;
        // Store the day itself as the "earliest available day"
        else {
            int mid = lb + (rb - lb) / 2;
            Node left = build(lb, mid);
            Node right = build(mid + 1, rb);
            node.left = left;
            node.right = right;
            node.min = Math.min(left.min, right.min);
            // The minimum value in the range is the earliest available day
        }
        return node;
    }

    static int query(Node node, int low, int high) {
        if (low == node.lb && high == node.rb)
            return node.min;
        int mid = node.lb + (node.rb - node.lb) / 2;
        if (high <= mid)
            return query(node.left, low, high);
        else if (low > mid)
            return query(node.right, low, high);
        else
            return Math.min(query(node, low, mid), query(node, mid + 1, high));
    }

    static int update(Node node, int day) {
        int old = Integer.MAX_VALUE;
        if (node.lb == node.rb)
            node.min = Integer.MAX_VALUE;
        else {
            int mid = node.lb + (node.rb - node.lb) / 2;
            if (day <= mid)
                old = update(node.left, day);
            else
                old = update(node.right, day);
            old = node.min;
            node.min = Math.min(node.left.min, node.right.min);
        }
        return old;
    }

    static int maxEvents(int[][] events) {
        if (events == null || events.length == 0)
            return 0;
        // Sort events by end day, and by start day if end days are the same
        Arrays.sort(events, (a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            else
                return a[1] - b[1];
        });

        int firstDay = Integer.MAX_VALUE, lastDay = Integer.MIN_VALUE;
        for (int[] event : events) {
            firstDay = Math.min(firstDay, event[0]);
            lastDay = Math.max(lastDay, event[1]);
        }

        root = build(firstDay, lastDay);
        int count = 0;
        for (int[] event : events) {
            // Query the earliest available day in the event's range
            int earliestDay = query(root, event[0], event[1]);
            if (earliestDay != Integer.MAX_VALUE) {
                // Mark the day as unavailable
                count++;
                update(root, earliestDay);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline character
        int[][] events = new int[n][2];
        String str[] = sc.nextLine().split(",");
        for (int i = 0; i < n; i++) {
            String val[] = str[i].split(" ");
            events[i][0] = Integer.parseInt(val[0]);
            events[i][1] = Integer.parseInt(val[1]);
        }

        int result = maxEvents(events);
        System.out.println(result);

        sc.close();
    }
}
