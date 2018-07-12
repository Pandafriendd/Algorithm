/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


public class deleteDuplicates {

    //98%
    public ListNode deleteDuplicates1(ListNode head) {
        ListNode cur = head;
        while(cur != null){
            while(cur.next != null && cur.val == cur.next.val) //! while, not if, since it would need to delete all duplicates, not just one
                cur.next = cur.next.next;
            cur = cur.next;
        }
        return head;
    }
    
    
    //96%  time: O(n)    space: O(1)
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while(cur != null && cur.next != null) {
            if(cur.next.val == cur.val)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return head;
    }
}
