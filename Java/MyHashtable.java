import java.util.Arrays;

public class MyHashtable<K, V> {  // !!!!!!
	
		// nested static class
		public static class Node<K, V> {  // better use public and static
			private final K key;   // should be final, since the index of buckets depends on key, it should not be changed
			private V val;
			Node<K, V> next;

			public Node(K k, V v) { // non-static methods don't need generics type specified, class already define
				key = k;
				val = v;
				next = null;
			}

			public K getKey() {
				return key;
			}

			public V getValue() {
				return val;
			}

			public V setValue(V val) {  // return old val
				V oldVal = this.val;
				this.val = val;
				return oldVal;
			}
		}	

		private Node<K, V>[] buckets;
		private int size;
		// private int cap;  //not need, use buckets.length
		private static final int DEFAULT_CAPACITY = 16;
		private static final double LOAD_FACTOR = 0.75;
		private static final int EXPAND_FACTOR = 2;

		@SuppressWarnings("unchecked")
		public MyHashtable() {
			buckets = (Node<K, V>[]) new Node[DEFAULT_CAPACITY]; // !!
			size = 0;

		}

		public V get(K key) {
			int hashCode = getHashCode(key);
			int index = getIndex(hashCode);
			Node<K, V> cur = buckets[index];

			while (cur != null) {
				if (keysEqual(cur.getKey(), key)) {
					return cur.getValue();
				}
				cur = cur.next;
			}

			return null;
		}

		public boolean containsKey(K key) {
			int hashCode = getHashCode(key);
			int index = getIndex(hashCode);
			Node<K, V> cur = buckets[index];

			while (cur != null) {
				if (keysEqual(cur.getKey(), key)) {
					return true;
				}
				cur = cur.next;
			}

			return false;
		}

		public V put(K key, V val) {  // Reture old val, maybe need to rehash
			int hashCode = getHashCode(key);
			int index = getIndex(hashCode);
			Node<K, V> cur = buckets[index];
			while (cur != null) {
				if (keysEqual(cur.getKey(), key)) {
					return cur.setValue(val);
				}
				cur = cur.next;
			}

			// when the key is not existed
			Node<K, V> newNode = new Node<>(key, val);
			newNode.next = buckets[index];
			buckets[index] = newNode;
			size++;    // !!!
			// return null; WRONG!

			if ((double) buckets.length / (double) size >= LOAD_FACTOR) {
				reHash();
			}

			return null;
		}

		private void reHash() {
			Node<K, V>[] oldBuckets = buckets;
			@SuppressWarnings("unchecked")
			Node<K, V>[] buckets = (Node<K, V>[]) new Node[oldBuckets.length * EXPAND_FACTOR];
			for (Node<K, V> node : oldBuckets) {
				while (node != null) {
					Node<K, V> next = node.next;
					int index = getIndex(getHashCode(node.getKey()));
					node.next = buckets[index];  // insert in head
					buckets[index] = node;
					node = next;
				}
			}
		}

		private int getHashCode(K key) {
			if (key == null) {
				return 0;
			}

			int res = key.hashCode();
			return res & 0x7fffffff;  // make sure always positive
		}

		private int getIndex(int hashCode) {
			return hashCode % buckets.length;
		}

		private boolean keysEqual(K k1, K k2) {
			if (k1 == null && k2 == null) {
				return true;
			}
			else if (k1 == null || k2 == null) {
				return false;
			}
			else {
				return k1.equals(k2);
			}

		}

		public V remove(K key) {
			int hashCode = getHashCode(key);
			int index = getIndex(hashCode);
			Node<K, V> cur = buckets[index];
			Node<K, V> pre = null;
			
			while (cur != null) {
				if (keysEqual(cur.getKey(), key)) {
					if (pre == null) {  // if the remove element is head
						// buckets[index] = null; // wrong!!!
						buckets[index] = cur.next;
					}
					else {   // if not remove head element
						pre.next = cur.next;
					}
					size--;  // !!! don't forget
					return cur.getValue();
				}
				pre = cur;
				cur = cur.next;
			}

			return null;  // return null if hashcode doesn't contain this key
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public int size() {
			return size;
		}

		public void clear() {
			Arrays.fill(buckets, null);
			size = 0;
		}
}
