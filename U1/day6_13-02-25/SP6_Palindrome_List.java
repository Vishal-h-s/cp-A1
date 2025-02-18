/*
Cliff Shaw is working on the singly linked list.
He is given a list of boxes arranged as singly linked list,
where each box is printed a positive number on it.

Your task is to help Mr Cliff to find the given list is equivalent to 
the reverse of it or not. If yes, print "true", otherwise print "false"

Input Format:
-------------
Line-1: space separated integers, boxes as list.

Output Format:
--------------
Print a boolean a value.

Sample Input-1:
---------------
3 6 2 6 3

Sample Output-1:
----------------
true


Sample Input-2:
---------------
3 6 2 3 6

Sample Output-2:
----------------
false

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

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {

	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null)
			return true;

		// Step 1: Find Middle (slow = mid or mid.next if odd length)
		ListNode slow = head, fast = head, mid = null;
		;
		while (fast != null && fast.next != null) {
			mid = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast != null) {
			mid = slow;
			slow = slow.next;
		}

		// System.out.println(mid.val);
		// mid is last element of first half
		// if total length odd mid is element before the actual mid
		// just like in an array with odd len if we do len/2 if odd the we land on
		// element before actual mid

		// Step 2: Reverse Second Half
		print(head);
		print(slow);
		// print(prev);
		ListNode prev = null, curr = slow, temp = null;
		while (curr != null) {
			temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		// prev is now the new head of the reversed half
		mid.next = prev;

		// Step 3: Compare First and Reversed Second Half
		print(head);
		print(prev);
		ListNode a = head, b = prev;
		while (b != null) {
			if (a.val != b.val) {
				return false;
			}
			a = a.next;
			b = b.next;
		}
		return true;
	}

	void print(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.val + "->");
			temp = temp.next;
		}
		System.out.println();
	}
}

/*
 * 1️⃣ Catch: Reversing the second half might disconnect it
 * 
 * Answer: The first half's last node (mid) still points to the start of the
 * second half (slow). So, the reference is not lost.
 * 2️⃣ Question: If curr and slow initially point to the same node, does slow
 * become null when curr = null at the end of reversal?
 * 
 * Answer: No. curr = null only affects curr. slow remains unchanged because
 * references in Java work independently unless explicitly reassigned.
 * 3️⃣ Catch: null is a reference, not a value
 * 
 * Answer: Assigning curr = null does not mean slow = null. Java simplifies
 * memory management, so we don’t have to manually track references.
 * 4️⃣ Question: Does the original list still have access to slow after
 * reversal?
 * 
 * Answer: Yes, the node before slow (mid) still has its .next pointing to slow.
 * 5️⃣ Catch: Prev.next = prev is unnecessary
 * 
 * Answer: The reconnection should be done as mid.next = prev, since prev is the
 * new head of the reversed second half.
 * 6️⃣ Question: Will slow = prev; reconnect the halves?
 * 
 * Answer: No. It only changes slow locally. The correct way to reconnect is:
 */

public class SP6_Palindrome_List {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int[] in = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		ListNode head = null, tail = null;
		for (Integer x : in) {
			ListNode n = new ListNode(x);
			if (head == null) {
				head = n;
				tail = n;
			} else {
				tail.next = n;
				tail = n;
			}
		}

		Solution s = new Solution();

		System.out.println(s.isPalindrome(head));

		sc.close();
	}
}