

public class RemoveNthNodeFrom {
	
	// 15.84 %
	
	public class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }
	
	public ListNode Solution(ListNode head, int n) {
			ListNode l = head;
			int len = 0;
			while(l != null) {
				len++;
				l = l.next;
			}
			
			if(len == 1 && n == 1)
				return null;
			if(len == 2) {
				if(n == 1) {
					head.next = null;
					return head;
				}
				if(n == 2)
					return head.next;
			}
			
			
			if(n < 1 || n > len)
				return null;
			
			int index = len - n;
			ListNode cur = head;
			
			if(index == 0) {
				head = head.next;
			}
			else {
				for(int i = 0; i < index - 1; i++) {
					cur = cur.next;
				}
				
				ListNode tmp = cur.next.next; //index ! = 0
				cur.next = tmp;
	
			}
			
			return head;
	}
	
	public ListNode integerArrayToListNode(int[] nodeValues) {
		RemoveNthNodeFrom r = new RemoveNthNodeFrom();
		ListNode dummyNode = r.new ListNode(0);
		ListNode cur = dummyNode;
		for(int i : nodeValues) {
			cur.next = r.new ListNode(i);
			cur = cur.next;
		}
		return dummyNode.next;
	}
	
	public static void main(String[] args) {
		RemoveNthNodeFrom r = new RemoveNthNodeFrom();
		int[] nodeValue = {1,2,3};
		
		ListNode head = r.integerArrayToListNode(nodeValue);
		
		ListNode cur = head;
		
		while(cur != null) {
			System.out.print(cur.val);
			cur = cur.next;
		}
		
		ListNode res = r.Solution(head, 3);
		
		System.out.println();
		
		while(res != null) {
			System.out.print(res.val);
			res = res.next;
		}
		
	}

}
