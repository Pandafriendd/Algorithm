/*
Design a HashSet without using any built-in hash table libraries.

To be specific, your design should include these functions:

add(value): Insert a value into the HashSet. 
contains(value) : Return whether the value exists in the HashSet or not.
remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

Example:

MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // returns true
hashSet.contains(3);    // returns false (not found)
hashSet.add(2);          
hashSet.contains(2);    // returns true
hashSet.remove(2);          
hashSet.contains(2);    // returns false (already removed)

Note:

All values will be in the range of [0, 1000000].
The number of operations will be in the range of [1, 10000].
Please do not use the built-in HashSet library.
 */

import java.util.*;
public class HashSetDesign {
	
	boolean[] buckets;
	
	/** Initialize your data structure here. */
    public HashSetDesign() {
        buckets = new boolean[1000001];
        Arrays.fill(buckets, false);  // false for null, true for existed
    }
    
    public void add(int key) {
        if(buckets[key] == true)
        	return;
        
        buckets[key] = true;;
    }
    
    public void remove(int key) {
        if(buckets[key] == false)
        	return;
        
        buckets[key] = false;
        
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return buckets[key];
    }
}

/*
itemsPerBucket is set to 1001 to deal with the edge case when the key is 0 even though the description specify the number value ranges from 1-100k. 
This will occupy 1000 additional booleans in memory.
 */
class MyHashSet {

    private int buckets = 1000;
    private int itemsPerBucket = 1001;
    private boolean[][] table;
    
    /** Initialize your data structure here. */
    public MyHashSet() {
        table = new boolean[buckets][];
    }

    public int hash(int key) {
        return key % buckets;
    }

    public int pos(int key) {
        return key / buckets;
    }
    
    public void add(int key) {
        int hashkey = hash(key);
        
        if (table[hashkey] == null) {
            table[hashkey] = new boolean[itemsPerBucket];
        }
        table[hashkey][pos(key)] = true;
    }
    
    public void remove(int key) {
        int hashkey = hash(key);

        if (table[hashkey] != null)
            table[hashkey][pos(key)] = false;
    }
    
    /** Returns true if this set did not already contain the specified element */
    public boolean contains(int key) {
        int hashkey = hash(key);
        return table[hashkey] != null && table[hashkey][pos(key)];
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
