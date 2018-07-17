import java.util.*;

public class swapPairs {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			this.val = x;
			this.next = null;
		}
	}
	public ListNode swapPairs(ListNode head) {
		ListNode dummyNode = new ListNode(0);
		dummyNode.next = head;
		ListNode cur = dummyNode;
		
		while(cur != null && cur.next != null & cur.next.next != null) {
			cur.next = swapNode(cur.next, cur.next.next);
			cur = cur.next.next;
		}
		return dummyNode.next;
	}
	
	private ListNode swapNode(ListNode l1, ListNode l2) {
		l1.next = l2.next;
		l2.next = l1;
		return l2;	
	}
	
	
	public ListNode integerArrayToListNode(int[] nodeValues) {
		swapPairs sp = new swapPairs();
		ListNode dummyNode = sp.new ListNode(0);
		ListNode cur = dummyNode;
		for(int i : nodeValues) {
			cur.next = sp.new ListNode(i);
			cur = cur.next;
		}
		return dummyNode.next;
	}
	
	public static void main(String[] args) {
		swapPairs sp = new swapPairs();
		int[] nodeValues = {1,2,3,4};
		ListNode cur = sp.integerArrayToListNode(nodeValues);
		ListNode pre = sp.integerArrayToListNode(nodeValues);
		while(cur != null) {
			System.out.print(cur.val);
			cur = cur.next;
		}
		
//		ListNode after = sp.swapPairs(pre);
//		while(after != null) {
//			System.out.print(after.val);
//			after = after.next;
//		}
		
	}
	
	
	/*
	 * yuan he's implementation 
	ListNode dummy = new ListNode(0);
    dummy.next = head;        
    while(head!=null && head.next!=null){
        ListNode temp1 = head;
        ListNode temp2 = head.next.next;
        head = head.next;
        head.next = temp1;
        temp1.next = temp2; 
        head = head.next.next;
    }
    return dummy.next;
    */
	
	
	
}
