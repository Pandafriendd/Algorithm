/*
Given a linked list, check whether it is a palindrome.

Examples:

Input:   1 -> 2 -> 3 -> 2 -> 1 -> null

output: true.

Input:   1 -> 2 -> 3 -> null  

output: false.

Requirements:

Space complexity must be O(1)
 */

public class CheckIfLinkedListIsPalindrome {
	
	
	class ListNode {
	    public int value;
	    public ListNode next;
	    public ListNode(int value) {
	      this.value = value;
	      next = null;
	    }
	  }
	
	public boolean isPalindrome(ListNode head) {
	    // Write your solution here
	    
	    if(head == null || head.next == null) {
	      return true;
	    }
	    
	    ListNode fast = head;
	    ListNode mid = head;
	    while(fast.next != null && fast.next.next != null) {
	      fast = fast.next.next;
	      mid = mid.next;
	    }
	    ListNode l1 = head;
	    ListNode l2 = mid.next;
	    mid.next = null;
	    
	    l2 = reverse(l2);
	    
	    while(l1 != null && l2 != null) {
	      if(l1.value != l2.value) {
	        return false;
	      }
	      l1 = l1.next;
	      l2 = l2.next;
	    }
	     
	    return true;
	  }
	  
	  private ListNode reverse(ListNode head) {
	    if(head == null || head.next == null) {
	      return head;
	    }
	    ListNode newHead = reverse(head.next);
	    head.next.next = head;
	    head.next = null;
	    return newHead;
	  }
}
