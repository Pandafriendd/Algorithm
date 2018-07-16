/*
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null
 * How about space O(1) ?
 */


public class LinkedListCycleII {
	public class ListNode{
		int val;
		ListNode next;
		ListNode(int z){
			val = z;
		}
	}
	
	
	/*
	 * two pointers or runner technique
	 * ������ָ�� fast and slow, �ֱ����㿪ʼ�ߣ�slow ÿ����һ����fast ÿ����������
	 * ��������� fast �ߵ�null����˵�������ڻ���
	 * ���� slow �� fast �������� slow ������㣬fast ����ԭ�ز�����
	 * Ȼ������ָ��ÿ�ηֱ���һ����������ʱ����������ǻ�����ڡ�
	 * 
	 * ������Ŀ�������𣬵� fast, slow������slow��head ������ڵľ�����һ���ģ����Դ�ʱ��LeetCode 160. 
	 * Intersection of Two Linked Lists����������� ֱ��������ָ��ֱ����������ͬʱ��ʼ�ߣ��ٶ���ͬ����������ǻ���ڡ�
	 * 
	 * This is because: Floyd's cycle detection Algorithm
	 */
	public ListNode detectCycle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast != null && slow != null) {
			slow = slow.next;
			fast = fast.next;
			if(fast != null)
				fast = fast.next;
			else
				return null;
			
			if(fast == slow) {
				slow = head;  
				while(slow != fast) {
					slow = slow.next;
					fast = fast.next;
				}
				return slow;
			}
		}
		return null;
	}
	
}
