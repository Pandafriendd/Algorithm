public class Solution {
  public List<Integer> common(List<Integer> A, List<Integer> B) {
    // Write your solution here
    // A should be longer one
    if (A.size() < B.size()) {
      return common(B, A);
    }
    
    HashMap<Integer, Integer> map = new HashMap<>();
    for (Integer i : B) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    
    List<Integer> res = new ArrayList<>();
    for (Integer i : A) {
      if (map.containsKey(i) && map.get(i) != 0) {
        res.add(i);
        map.put(i, map.get(i) - 1);
      }
    }
    
    return res;
  }
}
