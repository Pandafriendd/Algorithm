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
public List<List<Integer>> allPairs(int[] array, int target) {  // assume no duplicate
    Map<Integer, Integer> map = new HashMap<>();  // array[i] -> i
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < array.length; i++) {
      int newTarget = target - array[i];
      if (map.containsKey(newTarget)) {
        res.add(Arrays.asList(map.get(newTarget), i));
      }
      map.put(array[i], i);
    }
    return res;
  }

public List<List<Integer>> allPairs(int[] array, int target) {  // assume have duplicates
    Map<Integer, ArrayList<Integer>> map = new HashMap<>();  // array value -> list of all possible indeces
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < array.length; i++) {
      int newTarget = target - array[i];
      List<Integer> indeces = map.get(newTarget);
      if (indeces != null) { // if hit in the map
        for (int index : indeces) {
          res.add(Arrays.asList(index, i));
        }
      } 
      if (!map.containsKey(array[i])) {
        map.push(array[i], new ArrayList<Integer>());
      }
      map.get(array[i]).add(i);
    }
    return res;
  }
