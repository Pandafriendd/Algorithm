/*
Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

Example:

MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // returns 1
hashMap.get(3);            // returns -1 (not found)
hashMap.put(2, 1);          // update the existing value
hashMap.get(2);            // returns 1 
hashMap.remove(2);          // remove the mapping for 2
hashMap.get(2);            // returns -1 (not found) 

Note:

All keys and values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashMap library.
 */

import java.util.*;
public class HashMapDesign {
	
	final Bucket[] buckets = new Bucket[10000]; // LinkedList Array
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int i = getIndex(key);
        if(buckets[i] == null) {
        	buckets[i] = new Bucket();
        }
        
        ListNode prev = findPrev(buckets[i], key);
        if(prev.next == null)  // not exist, add new a ListNode
        	prev.next = new ListNode(key, value);
        else // already existed, update the value
        	prev.next.val = value;
    }
    
    public int get(int key) {
        int i = getIndex(key);
        if(buckets[i] == null)
        	return -1;
        ListNode prev = findPrev(buckets[i], key);
        return (prev.next == null) ? -1 : prev.next.val;
    }
       
    public void remove(int key) {
        int i = getIndex(key);
        if(buckets[i] == null)
        	return;
        ListNode prev = findPrev(buckets[i], key);
        if(prev.next == null)
        	return;
        prev.next = prev.next.next;
        
    }
    
    // get index in buckets[]
    int getIndex(int key) {
    	return Integer.hashCode(key) % buckets.length;
    }
    
    // find the previous node that has same index. If collision happened and no such key-value pair, prev.next = null; else prev.next.val = value;  
    ListNode findPrev(Bucket buckets, int key) {
    	ListNode node = buckets.head;
    	ListNode prev = null;
    	while(node != null && node.key != key) {
    		prev = node;
    		node = node.next;
    	}
    	return prev;
    }
    
    // LinkedList
    class Bucket {
    	final ListNode head = new ListNode(-1, -1);
    }
    
    class ListNode {
    	int key, val;
    	ListNode next;
    	
    	ListNode(int key, int val){
    		this.key = key;
    		this.val = val;
    	}
    }
}

// using huge array
class MyHashMap {
    
    int[] map;

    /** Initialize your data structure here. */
    public MyHashMap() {
        map = new int[1000001];
        Arrays.fill(map, -1);
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        map[key] = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return map[key];
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        map[key] = -1;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
