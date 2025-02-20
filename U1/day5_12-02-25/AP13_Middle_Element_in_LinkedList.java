/*
You are given a singly linked list containing N nodes. 
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
5
1 2 3 4 5

Sample Output-1:
----------------
3


Sample Input-2:
---------------
6
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

class Solution {

	public ListNode findMid(ListNode head) {
		if (head == null || head.next == null)
			return head;

		// Step 1: Find Middle (slow = mid or mid.next if odd length)
		ListNode slow = head, fast = head;
		ListNode mid= null;
		while (fast != null && fast.next != null) {
			mid = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast != null) {
		mid = slow;
		slow = slow.next;
		}
		return mid;
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

public class AP13_Middle_Element_in_LinkedList {
	public ListNode findMid(ListNode head) {
		if (head == null || head.next == null)
			return head;

		// Step 1: Find Middle (slow = mid or mid.next if odd length)
		ListNode slow = head, fast = head;
		ListNode mid= null;
		while (fast != null && fast.next != null) {
			mid = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast != null) {
		mid = slow;
		slow = slow.next;
		}
		return mid;
	}

	void print(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.val + "->");
			temp = temp.next;
		}
		System.out.println();
	}
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		
		int[] in = new int[m];
		for(int i=0;i<m;i++) in[i]=sc.nextInt();
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


		System.out.println(findMid(head).val);

		sc.close();
	}
}