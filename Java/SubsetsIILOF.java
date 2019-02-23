/* 
DFS solution1:
eg: input a b b 

                   abbbc
               /            \
   	        a                 ()
           /   \                  /\
         ab    ()                b
      /     \   |               / \
     abb    ab  |             bb
     /  \   |   |           /   \
   abbb abb |   |          bbb
    /    |  |   |          /\
abbbc  abbc abc ac     bbbc  bbb

in this recursion tree, each leaf node is a solution
time is O(2^n). space is O(n)
*/

public List<List<Integer>> subsets(int[] nums) {
	Arrays.sort(nums);   // !!!!! must be sorted so that all duls are close together
	List<List<Integer>> res = new ArrayList<>();
	List<Integer> temp =  new ArrayList<>();
	helper(res, temp, 0, nums);
	return res;
}

private void helper(List<List<Integer>> res, List<Integer> temp, int index, int[] nums) {
	if (index == nums.length) {
		res.add(new ArrayList<>(temp));
		return;  // !!
	}

	// pick
	temp.add(nums[index]);
	helper(res, temp, index + 1, nums);
	temp.remove(temp.size() - 1);

	// not pick
	while (index + 1 < nums.length && nums[index] == nums[index + 1]) {
		index++;
	}
	helper(res, temp, index + 1, nums);	
} 


/* 
DFS solution2:
eg: input a b b c 

              abbc
     /          |        \
   a(bbc)      b(bc)     c()
   /    \      /    \
ab(bc) ac()   bb(c)  bc()
  /   \ 
abb(c) abc()
  |
abbc  

in this recursion tree, every node is a solution
Cn1 + Cn2 + ... + Cnn, so time is O(2^n). space is O(n)
*/

public List<List<Integer>> subsets(int[] nums) {
	Arrays.sort(nums);   // !!!!! must be sorted so that all duls are close together
	List<List<Integer>> res = new ArrayList<>();
	List<Integer> temp =  new ArrayList<>();
	helper(res, temp, 0, nums);
	return res;
}

private void helper(List<List<Integer>> res, List<Integer> temp, int index, int[] nums) {
	res.add(new ArrayList<>(temp));

	for (int i = index; i < nums.length; i++) {
		
		temp.add(nums[i]);
		helper(res, temp, i + 1, nums);
		temp.remove(temp.size() - 1);

		while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
			i++;
		}
	}
} 
