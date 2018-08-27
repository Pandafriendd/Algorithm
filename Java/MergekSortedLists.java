/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
 */

import java.util.*;
public class MergekSortedLists {
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
	
	// my dump solution 48%
	// 1. add all node.val to a ArrayList 2. sort this ArrayList 3. build a new linkedlist based on the sorted arraylist
	// Time: O(nlongn), where n is the total number of nodes  Space: O(n)
	public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> arr = new ArrayList<>();
        // costs O(n)
        for(ListNode node : lists) {
        	while(node != null) {
        		arr.add(node.val);
        		node = node.next;
        	}
            //System.out.println(Arrays.toString(arr.toArray()));
        }
        
        //O(nlogn)
        Collections.sort(arr);
        //System.out.println(Arrays.toString(arr.toArray()));
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        
        // O(n)
        for(int val : arr) {
        	cur.next = new ListNode(val);
            cur = cur.next;
        }
        
        return head.next;
    }
	
	
	/*
	 Compare one by one:
	 Compare every k nodes (head of every linked list) and get the node with the smallest value, where k is the number of linkedlist
	 Extend the final sorted linked list with the selected nodes.
	 
	 Time complexity: O(kn)
	 */
	
	
	/*
	Optimize Compare one by one Approach by Priority Queue (heap)
	Almost the same as the one above but optimize the comparison process by priority queue.
	 */
	/**
     * Use a heap, O(n * log(k))
     */
    public ListNode mergeKLists1111(ListNode[] lists) {
    	if (lists == null || lists.length == 0) return null;
        // Build priority queue
        Queue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });
        // enqueue first k nodes
        for (ListNode n : lists) 
        	if (n != null) 
        		queue.add(n); 
        
        ListNode head = new ListNode(0); // set dummy head
        ListNode cur = head;
        while (!queue.isEmpty()) { // build next
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null) 
            	queue.add(cur.next); // the chosen list should move forward 
        }
        return head.next;
    }
    
	
	
	/*
	Merge lists one by one
	Convert merge k lists problem to merge 2 lists (k-1) times.
	Time complexity : O(kN) where k is the number of linked lists.
	We can merge two sorted linked list in O(n) time where n is the total number of nodes in two lists. Sum up the merge process and we can get: O(kN).
	 */
	
	
	/*
	Merge with Divide And Conquer
	We don't need to traverse most nodes many times repeatedly.
	
	Pair up k lists and merge each pair.
	After the first pairing, k lists are merged into k/2 lists with average 2N/k length, then k/4, k/8 and so on.
	Repeat this procedure until we get the final sorted linked list.
	Thus, we'll traverse almost N nodes per pairing and merging, and repeat this procedure about logk times.
	
	Time complexity : O(Nlogk) where k is the number of linked lists.
	We can merge two sorted linked list in O(n) time where n is the total number of nodes in two lists.
	Sum up the merge process and we can get: O(Nlogk)
	 */
    /**
     * Divide and conquer
     * merge two halves, divide to merge two lists
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        // next node should be the result of comparison
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2); // notice l1.next
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next); // notice l2.next
            return l2;
        }
    }

    public ListNode mergeKLists(List<ListNode> lists) {
        /*base cases*/
        if (lists.size() == 0) 
        	return null;
        if (lists.size() == 1) 
        	return lists.get(0);
        if (lists.size() == 2) 
        	return mergeTwoLists(lists.get(0), lists.get(1));
        
        /*merge two halves*/
        return mergeTwoLists(mergeKLists(lists.subList(0, lists.size()/2)), mergeKLists(lists.subList(lists.size()/2, lists.size())));
    }
}
