/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode r = pre;
        int sum = 0;
        while(l1 != null || l2 != null){
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            r.next = new ListNode(sum % 10);
            r = r.next;
            sum /= 10;
        }
        if(sum == 1){
            r.next = new ListNode(sum);
        }
        return pre.next;
    }
}