
public class RemoveNodesFromLinkedList {
	/*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
	static class SinglyLinkedListNode {
		int data;
		SinglyLinkedListNode next;
		SinglyLinkedListNode(int x) {
			data = x;
		}
	}
	
    static SinglyLinkedListNode removeNodes(SinglyLinkedListNode listHead, int x) {
        
        if(listHead == null)
            return null;
        
        SinglyLinkedListNode dummyHead = new SinglyLinkedListNode(0);
        dummyHead.next = listHead;
        SinglyLinkedListNode cur = listHead;
        SinglyLinkedListNode pre = dummyHead;
        while(cur != null) {
            if(cur.data <= x) {
                pre = cur;
                cur = cur.next;
            }
            else {
                pre.next = cur.next;
                cur = pre.next;
            }
        }
        
        return dummyHead.next;

    }
}
