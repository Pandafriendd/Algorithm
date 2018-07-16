//Write a program to find the node at which the intersection of two singly linked lists begins.
/*
 * The linked lists must retain their original structure after the function returns. 
 * You may assume there are no cycles anywhere in the entire linked structure. 
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */

public class IntersectionofTwoLinkedLists {
	/*
	 * Approach 1: brute force.
	 * For each node ai in list A, traverse the entire list B and check if any node in list B coincides with ai 
	 * Time: O(mn)    space: O(1)
	 */
	
	
	/*
	 * Approach 2: Hash table(hash set)
	 * Traverse list A and store the address / reference to each node in a hash set. 
	 * Then check every node bi in list B: if bi appears in the hash set, then bi is the intersection node
	 * time: O(m + n)   space: O(n) or O(m)
	 */
	public class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val = x;
		}
	}
	
	/*
	 * !!!!Approach 3: two pointers  Awesome!!!
	 * Maintain two pointers pA and pB initialized at the head of A and B, respectively. 
	 * Then let them both traverse through the lists, one node at a time. 
	 * When pA reaches the end of a list, then redirect it to the head of B (yes, B, that's right.); 
	 * similarly when pB reaches the end of a list, redirect it the head of A. 
	 * If at any point pA meets pB, then pA/pB is the intersection node. 
	 * 
	 * 
	 * To see why the above trick would work, consider the following two lists: 
	 * A = {1,3,5,7,9,11} and B = {2,4,9,11}, which are intersected at node '9'. 
	 * Since B.length (=4) < A.length (=6), pB would reach the end of the merged list first, 
	 * because pB traverses exactly 2 nodes less than pA does. 
	 * By redirecting pB to head A, and pA to head B, we now ask pB to travel exactly 2 more nodes than pA would. 
	 * So in the second iteration, they are guaranteed to reach the intersection node at the same time. 
	 * If two lists have intersection, then their last nodes must be the same one. 
	 * So when pA/pB reaches the end of a list, record the last element of A/B respectively. 
	 * If the two last elements are not the same one, then the two lists have no intersections.
	 * 
	 * time O(m + n)   space O(1)
	 */
	
	public ListNode getNode(ListNode headA, ListNode headB) {
		//boundary check, actually not needed
	    if(headA == null || headB == null) 
	    	return null;
		ListNode pA = headA;
		ListNode pB = headB;
		
		//if headA & headB have different len, then we will stop the loop after second iteration
		//for the end of first iteration, we just reset the pointer to the head of another linkedlist
		while(pA != pB) {   //!!!!
			pA = pA == null ? headA : pA.next;
			pB = pB == null ? headB : pB.next;
		}
		
		return pA;
	}
}
