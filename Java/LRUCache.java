/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?
 */

import java.util.*;
public class LRUCache {
	
	/*
Hashtable + Double linked list (with a touch of pseudo nodes)
The problem can be solved with a hashtable that keeps track of the keys and its values in the double linked list. 
One interesting property about double linked list is that the node can remove itself without other reference. 
In addition, it takes constant time to add and remove nodes from the head or tail.
One particularity about the double linked list that I implemented is that I create a pseudo head and tail to mark the boundary, 
so that we don't need to check the NULL node during the update. This makes the code more concise and clean, and also it is good for the performance as well.

why "key" is needed in DLinkedNode class? 
When the cache reaches its capacity, 2 updates need to be done to invalidate the least recently used item:
1. Remove the node before Tail, by updating the necessary links in the DoublyLinkedList.
2. Remove the key from the HashTable. This is where the key stored in DLinkedNode class comes to rescue.
This key stored in DLinkedNode class is needed to remove the <key, node> pair from the HashTable. 
After traversing to the node to be deleted (the one just before Tail), we retrieve this key from the node and use it to delete the pair from the hashtable.
In the above code, this is where it is being used:
this.cache.remove(tail.key);

why hashtable?
the usage of hash table is for the required performance. 
Without performance constraint, one can come up with many "simple" solutions, 
such as simply using vector<pair<int,int>> in C++ to store key-value pairs ordered by history. However, both get and set have O(capacity) complexity.
The HashTable cache here provides a quick look-up from key to linked node in O(1) time, which is very critical to the required performance

Node that although the linked list maintains the order of history when a node was added, 
it does not offer the random access capability to visit any given node because a list is a referenced based container (not random access container like array). 
Without hash table, one has to linearly traverse through the linked list to locate a specific node, which will violate the performance requirement.

Why Double LinkedList?
You can not move a node from the middle to head by single linked list with only carrying the reference of that node. But you can do it by double linked list.
For LinkedList in java, We don't have access to internal structure of the LinkedList.
So, Each time we call list.remove(Object o), it actually iterate through the LinkedList to first search this object, and then remove it from its internal structure.
So one operation of remove(Object o) in LinkedList actually takes O(n), not O(1).

The beauty of defining our own Double Linked List is that:
1. We don't have to iterate the list and find the object in it. Because we already know we have it.
2. we have control to the internal structure of the list and then we can achieve O(1) for remove(Object o).
	 */
	class DLinkedNode {
		int key;
		int value;
		DLinkedNode pre;
		DLinkedNode post;
	}
	
	private Hashtable<Integer, DLinkedNode> cache = new Hashtable<>();
	private int count;
	private int capacity;
	private DLinkedNode head, tail;
	
	/*
	 * Always add the new node right after head;
	 */
	private void addNode(DLinkedNode node) {
		node.pre = head;
		node.post = head.post;
		
		head.post.pre = node;
		head.post = node;
	}
	
	/*
	 * Remove an existing node from the linked list.
	 */
	private void removeNode(DLinkedNode node) {
		DLinkedNode pre = node.pre;
		DLinkedNode post = node.post;
		
		pre.post = post;
		post.pre = pre;
	}
	
	/*
	 * Move certain node in between to the head.
	 */
	private void moveToHead(DLinkedNode node) {
		this.removeNode(node); //this: To make sure there won't be a overloading within the public duplicate name methods from other classes which are happened to be imported by you.
		this.addNode(node);
	}
	
	/*
	 * pop the current tail
	 */
	private DLinkedNode popTail() {
		DLinkedNode res = tail.pre;
		this.removeNode(res);
		return res;
	}
	
	public LRUCache(int capacity) {
		this.count = 0;
		this.capacity = capacity;
		
		head = new DLinkedNode();
		head.pre = null;
		
		tail = new DLinkedNode();
		tail.post = null;
		
		head.post = tail;
		tail.pre = head;
	}
	
	public int get(int key) {
		DLinkedNode node = cache.get(key);
		if(node == null)
			return -1;   // or raise exception 
		
		// if hit, move the accessed node to the head;
		this.moveToHead(node);
		
		return node.value;
	}
	
	public void put(int key, int value) {
		DLinkedNode node = cache.get(key);
		
		if(node == null) {
			DLinkedNode newNode = new DLinkedNode();
			newNode.key = key;
			newNode.value = value;
			
			this.cache.put(key, newNode);
			this.addNode(newNode);
			count++;
			
			if(count > capacity) {
				// pop the tail (least recently cache)
				DLinkedNode tail = this.popTail();
				this.cache.remove(tail.key);
				count--;
			}
		}
		else {
			// update the value and move to head
			node.value = value;
			this.moveToHead(node);
		}
	}
	
	// Solution using Java's LinkedHashMap
    public class LRUCache2 {
        private LinkedHashMap<Integer, Integer> map;
        private final int CAPACITY;
        
        public LRUCache2(int capacity) {
            CAPACITY = capacity;
            map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > CAPACITY;
                }
            };
        }
        
        public int get(int key) {
            return map.getOrDefault(key, -1);
        }
        
        public void put(int key, int value) {
            map.put(key, value);
        }
    }
	
	
}


// 
