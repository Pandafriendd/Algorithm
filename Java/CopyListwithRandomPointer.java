import java.util.*;

/*
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null. 
 * Return a deep copy of the list.
 */

public class CopyListwithRandomPointer {
	public class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x) {
			this.label = x;
		}
	}
	
	/*
	 * Approach 1: !!!! 100%   O(n)
	 * insert a same node after the current node
	 * then split into two lists
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
		if(head == null)
			return head;
		// 1. Iterate the original list and duplicate each node. The duplicate of each node follows its original immediately.
		// before:  1 -> 2 -> 3 -> null
		RandomListNode p = head;
		while(p != null) {
			RandomListNode copy = new RandomListNode(p.label);
			copy.next = p.next;
			p.next = copy;
			p = p.next.next;
		}
		// after: 1 -> c1 -> 2 -> c2 -> 3 -> c3 -> null
		
		// 2. Iterate the new list and assign the random pointer for each duplicated node.
		// dealing with .random
		p = head;
		while(p != null && p.next != null) {  // should not be last one
			if(p.random != null)
				p.next.random = p.random.next;   // c1 -> c*, since p.ran = c*
			p = p.next.next;  // move to next c* 
		}
		
		// 3. split two lists
		p = head;
		RandomListNode copy = p.next; // c1, head of new list
		RandomListNode dummy = copy; 
		while(copy != null && p != null) {
			p.next = p.next.next;
			if(copy.next == null)
				break;
			copy.next = copy.next.next;
			copy = copy.next;
			p = p.next;
		}
		return dummy;
	}
	
	/*
	 * Approach2: time;O(n)    space: O(n)
	 * Hash map without recursion
	 */
	public RandomListNode copyRandomList2(RandomListNode head) {
		  if (head == null) 
			  return null;
		  
		  Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		  
		  // loop 1. copy all the nodes
		  RandomListNode node = head;
		  while (node != null) {
		    map.put(node, new RandomListNode(node.label));
		    node = node.next;
		  }
		  
		  // loop 2. assign next and random pointers
		  node = head;
		  while (node != null) {
		    map.get(node).next = map.get(node.next);   // !!
		    map.get(node).random = map.get(node.random);
		    node = node.next;
		  }
		  
		  return map.get(head);
	}
	
	/*
	 * Approach 3:   65.84%
	 * use a hash map to store map between original node and copy node,
	 * via which we just need to iterate the list in 2 rounds respectively to create nodes and assign the values for their random pointers
	 */
	public RandomListNode copyRandomList3(RandomListNode head) {
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		return helper(head, map);
	}
	
	//get copy node from map
	private RandomListNode helper(RandomListNode node, Map<RandomListNode, RandomListNode> map) {
		if(node == null)
			return null;
		if(map.containsKey(node))
			return map.get(node); // return node
		RandomListNode res = new RandomListNode(node.label); // since we need to deep copy, we need to new RandomListNode objects in memory  
		map.put(node, res);  // build map
		res.next = helper(node.next, map); // build next
		res.random = helper(node.random, map); // build copy
		return res;
	}
	
}
