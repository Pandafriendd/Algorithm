/*
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */

public class ReverseLinkedListII {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
	/*
	 * To clarify, all you're doing is inserting then between pre and pre.next. 
	 * You keep moving then forward by 1 until you reach the difference, m - n, 
	 * & you keep making start.next point to then.next to insure it's always pointing to the tail part of the list.
	 */
	public ListNode solution1 (ListNode head, int m, int n) {
		if(head == null || m > n)
			return head;
		ListNode dummy = new ListNode(0);   //dummy for marking the head
		dummy.next = head;
		ListNode pre = dummy; //as marker for the node before reversing
		for(int i = 0; i<m-1; i++) 
			pre = pre.next;
		
		ListNode start =  pre.next; // a pointer to the beginning of a sub-list that will be reversed
		ListNode then = start.next; // a pointer to a node that will be reversed
		
		// 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
	    // dummy-> 1 -> 2 -> 3 -> 4 -> 5
		
		for(int i = 0; i < n - m; i++) {
			start.next = then.next;
			then.next = pre.next;
			pre.next = then;
			then = start.next;
		}
		
		// first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
	    // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)
		
		return dummy.next;
	}
	
	
	
	 
    /**
     * Move pointers to m 
     * Then insert next code to sublist's head till we reach n
     * Reconnect sublist with original list after reversing
     * We need 1 dummy head, head and tail for sublist, 
     * And cur for current node, preCur for dummy head of sublist
     * 5 pointers in total
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m >= n || head == null) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 1; i < m; i++) 
        	pre = pre.next;
        
        ListNode cur = pre.next;
        for (int i = m; i < n; i++) { // insert next to head to reverse
            ListNode temp = cur.next.next;
            cur.next.next = pre.next;
            pre.next = cur.next;
            cur.next = temp;
        }
        return dummy.next;
    }
}
