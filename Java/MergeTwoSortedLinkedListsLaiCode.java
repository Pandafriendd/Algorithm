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
  public ListNode merge(ListNode one, ListNode two) {
    // Write your solution here
    if(one == null) {
      return two;
    }
    if(two == null) {
      return one;
    }
    
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while(one != null && two != null) {
      if(one.value < two.value) {
        cur.next = one;
        one = one.next;
        cur = cur.next;
      }
      else {  // one >= two
        cur.next = two;
        two = two.next;
        cur = cur.next;
      }
    }
    
    // now one == null || two == null, cur = the last one of the shorter list
    if(one == null) {
      cur.next = two;
    }
    if(two == null) {
      cur.next = one;
    }
    
    return dummy.next;
  }
}
