/*
input:
1 - 2 - 10
    |
    4 - 5 - 6
    |
    7 - 8 - 9
output is : 1 - 2 - 4 - 7 - 8 - 9 - 5 - 6 - 10
*/

class ListNode {
	int value;
	ListNode next;
	ListNode down;

	ListNode() {
		// ....
	}
}

// my solution
public ListNode flattenLinkedList(ListNode head) {
	if(head = null) {
		return head;
	}

	ListNode cur = head;
	Deque<ListNode> stack = new LinkedList<>();

	while(cur != null) {
		if(cur.down == null) { // go herizontal
			
			if(cur.next == null && !stack.isEmpty()) {   // connect to previous next
				cur.next = stack.pop();
			}

			cur = cur.next;
			//cur = cur.next;
		}
		else {  // cur has down node, go vertical
			if(cur.next != null) {
				stack.push(cur.next);
			}
			cur.next = cur.down;
			cur.down = null;
			cur = cur.next;
			if(cur.down == null && cur.next == null) { 
				if(!stack.isEmpty()) {
					cur.next = stack.pop();
				}
			}
			if(cur.down == null && cur.next != null) {
				cur = cur.next;
			}
		}
	}

	return head;
}

public ListNode fatten(ListNode root) {
	if(root==null) 
		return null;

    ListNode nextpoint = root.next;
    if(root.down != null){
        root.next = flatten(root.down);
       	root.down = null;
        ListNode pointer = root.next;
        while(pointer.next != null){
            pointer = pointer.next;
        }
        pointer.next = nextpoint;
    }

    flatten(nextpoint);
    return root;
}
