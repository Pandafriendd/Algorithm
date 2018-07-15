
public class ReverseLinkedList {
	
	/*
	 * iterative solution
	 * 
	 *   null -> 1 -> 2 -> 3 -> null
	 *   pre    cur   t
	 *   
	 * Brfore while, we have cur = head, pre = null:
	 * 1. t points to cur.next
	 * 2. cur.next points to pre
	 * 3. pre points to cur
	 * 4. cur moves to next
	 * time O(n)    space O(1)
	 */
	public ListNode solution1(ListNode head) {
		if(head == null || head.next == null)
            return head;
        
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
	}
	
	/*
	 * recursive solution
	 * The recursive version is slightly trickier and the key is to work backwards. 
	 * Assume that the rest of the list had already been reversed, now how do I reverse the front part? 
	 * Let's assume the list is: n1 → … → nk-1 → nk → nk+1 → … → nm → Ø 
	 * Assume from node nk+1 to nm had been reversed and you are at node nk: 
	 * n1 → … → nk-1 → nk → nk+1 ← … ← nm We want nk+1’s next node to point to nk. 
	 * So, nk.next.next = nk; 
	 * Be very careful that n1's next must point to Ø. If you forget about this, your linked list has a cycle in it. 
	 * This bug could be caught if you test your code with a linked list of size 2.
	 * Time complexity : O(n). Assume that n is the list's length, the time complexity is O(n). 
	 * Space complexity : O(n). The extra space comes from implicit stack space due to recursion. The recursion could go up to n levels deep.
	 * 
	 * To brief:
	 * 1) Divide the list in two parts - first node and rest of the linked list. 
	 * 2) Call reverse for the rest of the linked list. 
	 * 3) Link rest to first. 
	 * 4) Fix head pointer
	 */
	public ListNode solution2(ListNode head) {
		if(head == null || head.next == null)
			return head;
		//ystem.out.println(head.val);
		ListNode p = solution2(head.next);
		head.next.next = head;
		head.next = null;
		return p;
		
	}
	
	
}
