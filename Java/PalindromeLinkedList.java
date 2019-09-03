/*
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // find the middle of the list
        ListNode mid = findMid(head);
        // cut the list to be left and right half, then reverse the list from mid to end
        ListNode newHead = cutAndReverse(mid);
        
        ListNode left = head;
        ListNode right = newHead;
        while (right != null) { // since right half maybe has less node
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        
        return true;
    }
    
    // when the # of node in list are even, return the first one. e.g. 1-2(mid)-2-1
    // when the # of node in list are odd, return the exact mid one  e.g. 1-3(mid)-1
    private ListNode findMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast.next != null && fast.next.next != null) {  // !! &&
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    // cut the list from mid to form two lists: 
    // left side list from begin to mid, 
    // right side list from mid.next to end
    private ListNode cutAndReverse(ListNode mid) {
        ListNode oldRightHead = mid.next;
        mid.next = null;
        
        // reverse
        ListNode prev = null;
        ListNode cur = oldRightHead;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
