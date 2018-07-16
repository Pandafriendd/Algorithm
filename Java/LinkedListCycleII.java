/*
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null
 * How about space O(1) ?
 */


public class LinkedListCycleII {
	public class ListNode{
		int val;
		ListNode next;
		ListNode(int z){
			val = z;
		}
	}
	
	
	/*
	 * two pointers or runner technique
	 * 用两个指针 fast and slow, 分别从起点开始走，slow 每次走一步，fast 每次走两步。
	 * 如果过程中 fast 走到null，则说明不存在环。
	 * 否则当 slow 和 fast 相遇后，让 slow 返回起点，fast 待在原地不动，
	 * 然后两个指针每次分别走一步，当相遇时，相遇点就是环的入口。
	 * 
	 * 两道题目略有区别，当 fast, slow相遇后，slow和head 到环入口的距离是一样的，所以此时是LeetCode 160. 
	 * Intersection of Two Linked Lists的特殊情况， 直接让两个指针分别从这两个点同时开始走，速度相同，相遇点就是环入口。
	 * 
	 * This is because: Floyd's cycle detection Algorithm
	 */
	public ListNode detectCycle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast != null && slow != null) {
			slow = slow.next;
			fast = fast.next;
			if(fast != null)
				fast = fast.next;
			else
				return null;
			
			if(fast == slow) {
				slow = head;  
				while(slow != fast) {
					slow = slow.next;
					fast = fast.next;
				}
				return slow;
			}
		}
		return null;
	}
	
}
