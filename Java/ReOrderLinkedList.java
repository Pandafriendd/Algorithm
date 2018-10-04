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
  public ListNode reorder(ListNode head) {
    // Write your solution here
    if(head == null || head.next == null) {
      return head;
    }
    
    // split to two list;
    ListNode fast = head;
    ListNode slow = head;
    while(fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    ListNode leftHead = head;
    ListNode rightHead = slow.next;
    slow.next = null;
    
    // reverse right havles
    ListNode prev = null;
    ListNode temp;
    ListNode cur = rightHead;
    while(cur != null) {
      temp = cur.next;
      cur.next = prev;
      prev = cur;
      cur = temp;
    }
    rightHead = prev;  // update right head
    
    // merge two lists
    ListNode dummy = new ListNode(0);
    cur = dummy;
    while(leftHead != null && rightHead != null) {
      cur.next = leftHead;
      leftHead = leftHead.next;
      cur = cur.next;
      cur.next = rightHead;
      rightHead = rightHead.next;
      cur = cur.next;
    }
    
    if(leftHead == null) {
      cur.next = rightHead;
    }
    if(rightHead == null) {
      cur.next = leftHead;
    }
    
    return dummy.next;
    
  }
}
