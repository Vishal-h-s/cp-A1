
/*You are given a singly linked list containing N nodes. 
Your task is to find the middle element of the linked list.

Input Format:
-------------
Line 1: An integer N, representing the number of nodes in the linked list.
Line 2: N space-separated integers representing the elements of the linked list.

Output Format:
--------------
Line-1: Print a single integer, the middle element of the linked list.

Sample Input-1:
---------------
1 2 3 4 5

Sample Output-1:
----------------
3


Sample Input-2:
---------------
1 2 3 4 5 6

Sample Output-2:
----------------
4
 */

import java.util.*;

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

public class MiddleElementLL {
    
	static ListNode findMid(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode slow = head, fast = head;
		while ( fast!=null && fast.next!=null ) {
		    System.out.println("slow : "+slow.val+"fast ;"+fast.val);
			slow = slow.next;
// 			if(fast.next==null){
// 			    slow = slow.next;
// 			    return slow;
// 			}
			fast = fast.next.next;
		}
		return slow;
	}
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
        int[] in=Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		ListNode head = null, tail = null;
		for(int x: in){ 
			ListNode n = new ListNode(x);
			if (head == null) {
				head = n;
				tail = n;
			} else {
				tail.next = n;
				tail = n;
			}
		}
		System.out.println(findMid(head).val);

		sc.close();
	}
}