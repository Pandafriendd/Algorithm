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
	
	//This is recursive solution 97%
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
	
	
	//iteration solution 97%
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		
		ListNode res = new ListNode(0);
		ListNode pre = res;
		while(l1 != null && l2 != null) {
			if(l1.val < l2.val) {
				pre.next = l1;
				l1 = l1.next;
			}
			else {
				pre.next = l2;
				l2 = l2.next;
			}
			pre = pre.next; //?
		}
		if(l1 != null)
			pre.next = l1;
		if(l2 != null)
			pre.next = l2;
		return res.next;
	}
}
