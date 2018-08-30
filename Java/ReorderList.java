/*
 * Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
 * reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * Example 1:
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * 
 * Example 2:
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */


public class ReorderList {
	
	
	public class ListNode{
		int val;
		ListNode next;
		ListNode(int x){
			val = x;
		}
	}
	
	
	
	//1. find middle node, split the list into two halves
    //2. reverse right halve
    //3. merge the two lists
    
	//efficient, 100%, O(n)
    public void reorderList1(ListNode head) {
        if(head == null || head.next == null)
            return;
        
        // runner technique to find middle node
        ListNode tail = head;
        ListNode mid = head;      
        while(tail != null && tail.next != null){  // !! &&
            tail = tail.next.next;  //faster
            mid = mid.next;  //slower, speed: 2:1
        }
        
        ListNode cur = mid.next;  //mark the head of right list
        mid.next = null;   //seperate the list into two halves
        
        //reverse the right list
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = mid.next;
            mid.next = cur;
            cur = temp;
        }
        
        //merge two lists
        ListNode left = head;  //head of left unmerged list
        ListNode right = mid.next; //head of right unmerged list       
        while(right != null) {   //right halve always has fewer elements !!!
            mid.next = right.next;  //save right.next for right pointer next move
            right.next = left.next;
            left.next = right;
            //move left head and right head
            left = right.next;
            right = mid.next;
        } 
        return;
    }
    
    
    // TLE O(n^2) slow
    public void reorderList2(ListNode head) {
    	if(head == null || head.next == null)
    		return;
    	ListNode cur = head;
    	while(cur != null && cur.next != null && cur.next.next != null) { // cur should not be null, last one, second last one
    		ListNode beforeTail = cur.next;
    		ListNode curNext = cur.next;
    		
    		while(beforeTail != null && beforeTail.next != null && beforeTail.next.next != null)  
    			beforeTail = beforeTail.next;
    		
    		cur.next = beforeTail.next;
    		beforeTail.next.next = curNext;
    		beforeTail.next = null;
    		cur = curNext;
    	}
    	return;
    	
    }
}
