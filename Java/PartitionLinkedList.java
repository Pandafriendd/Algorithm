/*
Given a linked list and a target value T, partition it such that all nodes less than T are listed before the nodes larger than or equal to target value T. The original relative order of the nodes in each of the two partitions should be preserved.

Examples

L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null
*/

/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode partition(ListNode head, int target) {
    // Write your solution here
    if(head == null || head.next == null) {
      return head;
    }
    
    ListNode dummySmaller = new ListNode(0);
    ListNode dummyLarger = new ListNode(0);
    ListNode cur = head;
    ListNode small = dummySmaller;
    ListNode large = dummyLarger;
    while(cur != null) {
      if(cur.value < target) {
        small.next = cur;
        small = small.next;
      }
      else {
        large.next = cur;
        large = large.next;
      }
      cur = cur.next;
    }
    
    // connect dummysmall and dummylarge together
    large.next = null;  // !!!! otherwise we will have a loop
    small.next = dummyLarger.next;
    
    return dummySmaller.next;
    
  }
}
