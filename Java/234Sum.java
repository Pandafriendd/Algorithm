// return boolean
public boolean existSum(int[] array, int target) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < array.length; i++) {
      int newTarget = target - array[i];
      if (set.contains(newTarget)) {
        return true;
      }
      set.add(array[i]);
    }
    return false;
  }
 
// return pairs
public List<List<Integer>> allPairs(int[] array, int target) {  // assume no duplicates
    Map<Integer, Integer> map = new HashMap<>();  // array[i] -> i
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < array.length; i++) {
      int newTarget = target - array[i];
      if (map.containsKey(newTarget)) {
        List<Integer> pair = Arrays.asList(map.get(newTarget), i);
        res.add(pair);
      }
      map.put(array[i], i);
    }
    return res;
  }
