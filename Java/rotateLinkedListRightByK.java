

public class rotateLinkedListRightByK {
	
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
	
	//well done!!! beat 97%!
	public ListNode solution1(ListNode head, int k) {
		//ListNode cur = head;  //shouldn't be here since they need to be update every outer loop
		//ListNode pre = head;
		// can be more efficient if we dealing with k well
		if(head == null || head.next == null) //for cases LinkedList has null or only one node
			return head;
		
		ListNode l = head;
		int len = 0;
		while(l.next != null) {
			l = l.next;
			len++;
		}
		len++; // need to len++ to get actual len
		
		k = k % len;
		while(k > 0) {
			ListNode cur = head; //right place  point to last
			ListNode pre = head; // point to last's prior
			
			while(cur.next != null) {
				cur = cur.next;
			}
			while(pre.next.next != null) {
				pre = pre.next;
			}
			pre.next = null;
			cur.next = head;
			head = cur;
			k--;
		}
		return head;
	}
	
	
	public static void main(String[] args) {
		rotateLinkedListRightByK r = new rotateLinkedListRightByK();
		int[] nodeVal  = {0, 1, 2};
		
		ListNode head = r.integerArrayToListNode(nodeVal);
		ListNode cur = head;
		
		while(cur != null) {
			System.out.print(cur.val);
			cur = cur.next;
		}
		
		ListNode res = r.solution1(head, 4);
		
		System.out.println();
		
		while(res != null) {
			System.out.print(res.val);
			res = res.next;
		}
			
		
	}
	
	public ListNode integerArrayToListNode(int[] nodeValues) {
		rotateLinkedListRightByK r = new rotateLinkedListRightByK();
		ListNode dummyNode = r.new ListNode(0);
		ListNode cur = dummyNode;
		for(int i : nodeValues) {
			cur.next = r.new ListNode(i);
			cur = cur.next;
		}
		return dummyNode.next;
	}
	
	
}
