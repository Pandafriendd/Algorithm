
import java.util.*;
public class MyLFU {
	
	
	class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;  // initialize as 1
        }
    }
    
    class DLList {
        Node head;
        Node tail;
        int listSize;
        
        public DLList() {
            listSize = 0;
            
            head = new Node(-100, -100);
            tail = new Node(-100, -100);
        
            // build a empty list with no actual node
            head.prev = null;
            tail.next = null;
            head.next = tail;
            tail.prev = head;            
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
            listSize--;
        } 
    
        public void addNodeToHead(Node node) {
            Node next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
            listSize++;
        }
    
        public Node removeNodeFromTail() {
            if(listSize > 0) {
                Node realTail = tail.prev;
                removeNode(realTail);
                return realTail;
            }
            else {
                return null;
            }
        } 
        
    }
    
    
    
    HashMap<Integer, DLList> freqMap;  // frequency ==> head of doubly linked list that has same frequency 
    HashMap<Integer, Node> nodeMap;  // key ==> node
    int capacity;
    int minFreq;
    int cacheSize;
    
    public MyLFU(int capacity) {
        // initialization fields
        freqMap = new HashMap<>();   
        nodeMap = new HashMap<>();
        this.capacity = capacity;
        minFreq = 0;
        cacheSize = 0;
    }
    
    public int get(int key) {
        Node node = nodeMap.get(key);
        if(node == null) {
            return -1;
        }
        else { // node != null
            update(node);
            return node.value;
        }
    }
    
    public void put(int key, int value) {
    	if(capacity == 0)  // handle edge case
    		return;
    	
        Node node = nodeMap.get(key);
        if(node != null) {
            node.value = value;
            update(node);
            // ?? nodeMap.put(key, node);
        }
        else { // node == null
            node = new Node(key, value);
            if(cacheSize < capacity) {
                minFreq = 1;
                DLList newList = freqMap.getOrDefault(node.freq, new DLList());
                newList.addNodeToHead(node);
                freqMap.put(node.freq, newList);  // need?  yes! since newList maybe a new list and not be added before
                nodeMap.put(key, node);
                cacheSize++;
            }
            else { // cacheSize == capacity
                // remove last node
                DLList leastFreqList = freqMap.get(minFreq);
                Node leastFreqNode = leastFreqList.removeNodeFromTail();
                nodeMap.remove(leastFreqNode.key);
                
                // add new node
                minFreq = 1;
                DLList newList = freqMap.getOrDefault(node.freq, new DLList());
                newList.addNodeToHead(node);
                freqMap.put(node.freq, newList);  // ?? really need? yes! since newList maybe a new list and not be added before
                nodeMap.put(key, node);
            }
        }
    }
    
    private void update(Node node) {
        DLList oldList = freqMap.get(node.freq);
        oldList.removeNode(node);
        if(node.freq == minFreq && oldList.listSize == 0) {
            minFreq++;
        }
        node.freq++;
        DLList newList = freqMap.getOrDefault(node.freq, new DLList());
        newList.addNodeToHead(node);
        freqMap.put(node.freq, newList);  // ??
    }
}
