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
  public ListNode insert(ListNode head, int value) {
    // Write your solution here    
    ListNode newNode = new ListNode(value);
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode cur = dummy;
    while(cur.next != null) {
      if(cur.next.value > value) {
        ListNode next = cur.next;
        cur.next = newNode;
        newNode.next = next;
        return dummy.next;
      }
      else { // value is bigger or equal
        cur = cur.next;
      }
    }
    
    // cur.next = null after while loop finish, meaning value is bigger than all nodes
    cur.next = newNode;
    
    return dummy.next;
  }
}
