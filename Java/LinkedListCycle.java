import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
	public class ListNode{
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
	//using hash set
	/*
	 * Time complexity : O(n). We visit each of the n elements in the list at most once. 
	 * Adding a node to the hash table costs only O(1) time.
	 * 
	 * Space complexity: O(n). The space depends on the number of elements added to the hash table, which contains at most n elements.
	 */
	boolean hasCycle(ListNode head) {
		Set<ListNode> set = new HashSet<ListNode>(); 
		while(head != null) {
			if(set.contains(head))
				return true;
			else
				set.add(head);
			head = head.next;
		}
		return false;
	}
	
	
	//two pointers
	/*
	 * If there is no cycle in the list, the fast pointer will eventually reach the end and we can return false in this case.
	 * Now consider a cyclic list and imagine the slow and fast pointers are two runners racing around a circle track. 
	 * The fast runner will eventually meet the slow runner. 
	 * Why? Consider this case (we name it case A) - The fast runner is just one step behind the slow runner. 
	 * In the next iteration, they both increment one and two steps respectively and meet each other.
	 * How about other cases? For example, we have not considered cases where the fast runner is two or three steps behind the slow runner yet. 
	 * This is simple, because in the next or next's next iteration, this case will be reduced to case A mentioned above.
	 */
	//time: O(n)  space: O(1)
	boolean hasCycle2(ListNode head) {
		if(head == null || head.next == null)
			return false;
		
		ListNode slow = head;
		ListNode fast = head.next;
		
		while(slow != fast) {
			if(fast == null || fast.next == null)
				return false;
			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
	}
}
