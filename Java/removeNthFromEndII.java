/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    //two pointers;
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode p1 = pre;
        ListNode p2 = pre;
        
        int i = 0;
        while(i < n) {
            p2 = p2.next;
            i++;
        }
        while(p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        
        p1.next = p1.next.next;
        return pre.next;
    }
}