public class Solution {
  public int missing(int[] array) {
    // Write your solution here
    
    HashSet<Integer> set = new HashSet<>();
    for (int i = 0; i < array.length; i++) {
      set.add(array[i]);
    }
    
    for (int i = 1; i <= array.length + 1; i++) {
      if (!set.contains(i)) {
        return i;
      }
    }
    
    return 1;
  }
}
