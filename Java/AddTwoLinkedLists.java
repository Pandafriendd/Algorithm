/*
You are given two linked lists representing two non-negative numbers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.  

Example

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)

Output: 7 -> 0 -> 8
 */

public class AddTwoLinkedLists {
	
	class ListNode {
	    public int value;
	    public ListNode next;
	    public ListNode(int value) {
	      this.value = value;
	      next = null;
	    }
	  }
	
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	    // Write your solution here
	    if(l1 == null && l2 != null) {
	      return l2;
	    }
	    if(l1 != null && l2 == null) {
	      return l1;
	    }
	    
	    ListNode dummy = new ListNode(0);
	    ListNode cur = dummy;
	    int sum = 0;
	    int carry = 0;
	    while(l1 != null && l2 != null) {
	      sum = l1.value + l2.value + carry;
	      carry = sum / 10;   // !!!! should calculate first
	      if(carry > 0) {
	        sum = sum % 10;
	      }
	      ListNode newNode = new ListNode(sum);
	      cur.next = newNode;
	      cur = cur.next;
	      l1 = l1.next;
	      l2 = l2.next;
	    }
	    
	    while(l2 != null) {  // should keep while loop to add all nodes left
	      sum = l2.value + carry;
	      carry = sum / 10;
	      if(carry > 0) {
	        sum = sum % 10;
	      }
	      
	      ListNode newNode = new ListNode(sum);
	      cur.next = newNode;
	      cur = cur.next;
	      l2 = l2.next;
	    }
	    
	    while(l1 != null) {
	      sum = l1.value + carry;
	      carry = sum / 10;
	      if(carry > 0) {
	        sum = sum % 10;
	      }
	      ListNode newNode = new ListNode(sum);
	      cur.next = newNode;
	      cur = cur.next;
	      l1 = l1.next;
	    }
	    
	    if(carry != 0) {  // !!!!  there maybe carry left and need to be added
	      ListNode newNode = new ListNode(carry);
	      cur.next = newNode;
	    }
	    
	    return dummy.next;
	  }
}
