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

 
 
import java.util.*;

class ListNode {
	String val;
	ListNode next;

	ListNode() {
	}

	ListNode(String val) {
		this.val = val;
	}

	ListNode(String val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

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



public class AP14_Reverse_Vowels {


	static ListNode reverse(ListNode slow){
		ListNode prev = null, curr = slow, temp = null;
		while (curr != null) {
			temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		return prev;
	}

	static boolean isVowel(String c){
		String[] vowels={"A","E","I","O","U"};
		for(String x:vowels){
			if(c.contains(x)){

				
			}
		}
	}
	static void reverseVowels(ListNode head) {
		if (head == null || head.next == null)
			return ;

		// Step 1: Find Middle (slow = mid or mid.next if odd length)
		ListNode slow = head, fast = head;
		ListNode mid=null;
		while (fast != null && fast.next != null) {
			mid = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		// if (fast != null) {
		// 	mid = slow;
		// 	slow = slow.next;
		// }

		// Step 2: Reverse Second Half
		ListNode prev=reverse(slow);
		
		// mid.next = prev;

		ListNode a = head, b = prev;
		while (b != null) {
			boolean ais=isVowel(a.val), bis=isVowel(b.val);
			if(ais && bis) {
				String temp=a.val;
				a.val=b.val;
				b.val=temp;

				a=a.next;
				b=b.next
			}
			else if(ais) b=b.next;
			else a=a.next;
		}

		prev=reverse(slow);
		mid.next=prev;
		
	}

	static void print(ListNode head) {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.val + "->");
			temp = temp.next;
		}
		System.out.println();
	}



	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String[] in=sc.nextLine().split("");
		ListNode head = null, tail = null;
		for (String x : in) {
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