import java.util.*;


/*
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
class ListNode {
	public class java {

	}
	int val;
	ListNode next;
	ListNode(int x){
		val = x;
	}
}

public class merge2Lists {
	
	//recursive solution 97%
	// Time complexity : O(n+m)  Space: O(n+m)
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// edge cases
		if(l1 == null) 
        	return l2; //!!!!
        if(l2 == null)
        	return l1;
        
        ListNode mergeHead; //!!
        if(l1.val < l2.val){
        	mergeHead = l1;
            mergeHead.next = mergeTwoLists(l1.next, l2);
        }
        else{
        	mergeHead = l2;
        	mergeHead.next = mergeTwoLists(l1, l2.next);
        }
        
        return mergeHead;
    }
	
	public ListNode mergeTwoLists111(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
	
	
	//iteration solution 97%
	// Time complexity : O(n+m)  space: O(1)
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		
		// maintain an unchanging reference to node ahead of the return node.
		ListNode dummy = new ListNode(-1);
		ListNode pre = dummy;
		
		while(l1 != null && l2 != null) {
			if(l1.val < l2.val) {
				pre.next = l1;
				l1 = l1.next;
			}
			else {
				pre.next = l2;
				l2 = l2.next;
			}
			pre = pre.next;
		}
		if(l1 != null)
			pre.next = l1;
		if(l2 != null)
			pre.next = l2;
		return dummy.next;
	}
}
