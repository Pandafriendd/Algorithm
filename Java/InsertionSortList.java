
public class InsertionSortList {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x){
			val = x;
		}
	}
	
	/**
     * Check the list one by one to find a node that has smaller value than 
     * nodes before it and swap
     * O(n^2)
     */
	public ListNode insertionSortList(ListNode head) {
		if(head == null || head.next == null)
			return head;
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;   // keep the head
		
		for(ListNode node = head.next, pre = head; node != null; pre = node, node = node.next) {
			for(ListNode cur = dummy; cur.next != node; cur = cur.next) {
				if(cur.next.val > node.val ) {
					pre.next = node.next; // skip node
					node.next = cur.next; // insert between cur and cur.next
					cur.next = node;
					node = pre; // node is inserted to somewhere in the front, reset
					break;
				}
			}
		}
		return dummy.next;
	}
	
	
	
	public ListNode insertionSortList2(ListNode head) {
		if( head == null ){
			return head;
		}
		
		ListNode helper = new ListNode(0); //new starter of the sorted list
		ListNode cur = head; //the node will be inserted
		ListNode pre = helper; //insert node between pre and pre.next
		ListNode next = null; //the next node will be inserted
		//not the end of input list
		while( cur != null ){
			next = cur.next;
			//find the right place to insert
			while( pre.next != null && pre.next.val < cur.val ){
				pre = pre.next;
			}
			//insert between pre and pre.next
			cur.next = pre.next;
			pre.next = cur;
			pre = helper;
			cur = next;
		}
		
		return helper.next;
	}
	
}
