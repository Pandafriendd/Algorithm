import java.util.*;
public class MyLRU {
	
	// define doubly linked list node
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    HashMap<Integer, Node> map;
    Node head; // dummy
    Node tail; // dummy
    int currentSize;
    int capacity;
    
    public MyLRU(int capacity) {
        this.capacity = capacity;
        currentSize = 0;
        map = new HashMap<>();
        head = new Node(-3, -3);
        tail = new Node(-3, -3);
        
        // build a empty doubly linked list
        head.prev = null;
        tail.next = null;
        head.next = tail;
        tail.prev = head;
    }
    
    // O(1)
    public int get(int key) {
        Node node = map.get(key);
        if(node == null) {
            return -1;
        }
        else { // node != null
            moveToHead(node);
            return node.value;
        }
    }
    
    // O(1)
    public void put(int key, int value) {
        Node node = map.get(key);
        if(node != null) {
            node.value = value;
            // map.put(key, node);  doesn't need since node.value modify heap!
            moveToHead(node);
        }
        else {  // node == null
            node = new Node(key, value);
            if(currentSize < capacity) {
                addNodeToHead(node);
                map.put(key, node);
                currentSize++;
            }
            else {
                Node actualTail = removeNodeFromTail();
                map.remove(actualTail.key);
                addNodeToHead(node);
                map.put(key, node);
            }
        }
    }
    
    public void moveToHead(Node node) {
        removeNode(node);
        // add this node to head
        addNodeToHead(node);
    }
    
    public void removeNode(Node node) {
        // remove node from doubly linked list first
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    } 
    
    public void addNodeToHead(Node node) {
        Node next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }
    
    public Node removeNodeFromTail() {
        Node realTail = this.tail.prev;
        /*
        Node prev = realTail.prev;
        prev.next = this.tail;
        this.tail.prev = prev;
        */
        removeNode(realTail);
        return realTail;
    }
}
