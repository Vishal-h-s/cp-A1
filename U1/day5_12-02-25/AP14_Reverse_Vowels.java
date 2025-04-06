/*
Given a string s, reverse only all the vowels in the 
string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can 
appear in both lower and upper cases, more than once.

Sample Input-1:
---------------
hello

Sample Output-1:
----------------
holle

Sample Input-2:
----------------
Keshavmemorial

Sample Output-2:
----------------
Kashivmomerael

 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * String val;
 * ListNode next;
 * ListNode() {}
 * ListNode(String val) { this.val = val; }
 * ListNode(String val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
import java.util.*;

class ListNode {
	char val;
	ListNode next;

	ListNode() {
	}

	ListNode(char val) {
		this.val = val;
	}

	ListNode(char val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

public class AP14_Reverse_Vowels {

	static ListNode reverse(ListNode slow) {
		ListNode prev = null, curr = slow, tempNext = null;
		while (curr != null) {
			tempNext = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tempNext;
		}
		return prev;
	}

	static boolean isVowel(char c) {
		return "AEIOU".indexOf(Character.toUpperCase(c)) != -1;
	}

	static void reverseVowels(ListNode head) {
		if (head == null || head.next == null)
			return;

		// Step 1: Find Middle (slow = mid or mid.next if odd length)
		ListNode slow = head, fast = head;
		ListNode mid = null;
		while (fast != null && fast.next != null) {
			mid = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		// Step 2: Reverse Second Half
		ListNode prev = reverse(slow);

		ListNode first = head, second = prev;
		while (first != null && second != null) {
			boolean firstis = isVowel(first.val), secondis = isVowel(second.val);
			if (firstis && secondis) {
				first.val = (char) (first.val ^ second.val);
				second.val = (char) (first.val ^ second.val);
				first.val = (char) (first.val ^ second.val);

				first = first.next;
				second = second.next;
			} else if (firstis) {
				second = second.next;
			} else {
				first = first.next;
			}
		}

		// Step 3: Restore the second half to its original order
		prev = reverse(prev);
		if (mid != null) {
			mid.next = prev;
		}
	}

	static void print(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.val);
			temp = temp.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] in = sc.nextLine().toCharArray();
		ListNode head = null, tail = null;
		for (char x : in) {
			ListNode n = new ListNode(x);
			if (head == null) {
				head = n;
				tail = n;
			} else {
				tail.next = n;
				tail = n;
			}
		}
		if (head.next != null)
			reverseVowels(head);
		print(head);

		sc.close();
	}
}