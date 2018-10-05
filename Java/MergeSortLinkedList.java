/*
Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The merge sort algorithm should be used to solve this problem.

Examples

null, is sorted to null
1 -> null, is sorted to 1 -> null
1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6
 */



public class MergeSortLinkedList {
	
	 class ListNode {
	    public int value;
	    public ListNode next;
	    public ListNode(int value) {
	      this.value = value;
	      next = null;
	    }
	  }
	 
	
	public ListNode mergeSort(ListNode head) {
	    // Write your solution here
	    
	    if(head == null || head.next == null) {
	      return head;
	    }
	    
	    // step 1: split into two lists
	    ListNode fast = head;
	    ListNode mid = head;
	    while(fast.next != null && fast.next.next != null) {
	      fast = fast.next.next;
	      mid = mid.next;
	    }
	    ListNode leftHead = head;
	    ListNode rightHead = mid.next;
	    mid.next = null;
	    
	    // step 2: merge two lists by recursion
	    leftHead = mergeSort(leftHead);
	    rightHead = mergeSort(rightHead);
	    
	    // step3: merge two sorted list
	    head = merge(leftHead, rightHead);
	    
	    return head;
	  }
	  
	  private ListNode merge(ListNode l1, ListNode l2) {
	    if(l1 == null && l2 != null) {
	      return l2;
	    }
	    if(l1 != null && l2 == null) {
	      return l1;
	    }
	    
	    ListNode dummy = new ListNode(-1);
	    ListNode cur = dummy;
	    while(l1 != null && l2 != null) {
	      if(l1.value < l2.value) {
	        cur.next = l1;
	        l1 = l1.next;
	      }
	      else {  // l1.v >= l2.v
	        cur.next = l2;
	        l2 = l2.next;
	      }
	      cur = cur.next;
	    }
	    
	    if(l1 == null) {
	      cur.next = l2;
	    }
	    else {  // l1 != null, meaning l2 == null
	      cur.next = l1;
	    }
	    
	    return dummy.next;
	  }
}
