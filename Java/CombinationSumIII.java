/*
 * Find all possible combinations of k numbers that add up to a number n, 
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers. 
 * Note: All numbers will be positive integers. The solution set must not contain duplicate combinations.
 * 
 * Input: k = 3, n = 9 
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

import java.util.*;



/*
 * Build an array to apply to "subset" template. Each time we add an element to the "list", 
 * for the next step, target= target - num[i]. Since we have already added one element, 
 * for the next step, we can only add k-1 elements. 
 * Since no duplicated elements accept, for the next loop, the "start" should point to the next index of current index. 
 * The list.remove(list.size() - 1) here means, we need to change the element here. 
 * I know it is hard to understand it, let me give you an example.
 * When k=3, n=9, my answer works like this:
 * [1]->[1,2]->[1,2,3]. Since now sum is not 9, no more backtracking, so after list.remove(list.size() - 1), it is [1,2]. 
 * Then next follows [1,2,4], sum is not 9, repeat process above untill [1,2,6].
 *  When go to next backtracking, the list will be added to result, and for this list, no more backtracking.
 *  Now we can go back to a previous backtracking, which is [1,3]->[1,3,4], fail. [1,4,]->[1,4,5], fail. And so one.
 *  So the point of list.remove(list.size() - 1) is, after each "fail" or "success", 
 *  since we don't need to do further attempts given such a condition, we delete the last element, 
 *  and then end current backtracking. Next step is, add the next element to the deleted index, go on attempting.
 */

//  5%, really slow...
public class CombinationSumIII {
	public List<List<Integer>> combinationIII(int k, int n){
		List<List<Integer>> res = new ArrayList<>();
		helper(res, new ArrayList<>(), n, 1, 0, k);
		return res;
	}
	
	// helper(List<List<Integer>> res, ArrayList<Integer> temp, int target, int start, int k)   if(temp.size() == k && target == 0)
	private void helper(List<List<Integer>> res, ArrayList<Integer> temp, int target, int start, int len, int k) { // don't forget len and k
		if(target == 0 && len == k) {
			res.add(new ArrayList<Integer>(temp));
			System.out.println(temp.toString());
			return;
		}
		for(int i = start; i <= 9; i++) {
			int newTarget = target - i;
			
			if(newTarget >= 0) {
				temp.add(i);
				helper(res, temp, newTarget, i + 1, len + 1, k);
				temp.remove(temp.size() - 1);
			}
			else
				break;
		}
	}
	
	
	// difference in the backtracking function and some pruning
	public List<List<Integer>> combinationIII2(int k, int n) {
	    List<List<Integer>> ans = new ArrayList<>();
	    combination(ans, new ArrayList<Integer>(), k, 1, n);
	    return ans;
	}

	private void combination(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
		if (comb.size() == k && n == 0) {   // !!
			List<Integer> li = new ArrayList<Integer>(comb);
			ans.add(li);
			return;
		}
		
		 // Break early
		if (comb.size() == k)  {
			return;
		}
		
		for (int i = start; i <= 9; i++) {
			comb.add(i);
			combination(ans, comb, k, i+1, n-i);
			comb.remove(comb.size() - 1);
		}
	}
	
	
	/*
	 * recursive approach
	 */
	private List<List<Integer>> res = new ArrayList<List<Integer>>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        findCombo( k, n, 1, new LinkedList<Integer>() );
        return res;
    }
    public void findCombo(int k, int n, int start, List<Integer> list){
        if( k == 1 ){
            if( n < start || n > 9 ) return;
            list.add( n );
            res.add( list );
            return;
        }
        for( int i = start; i <= n / k && i < 10; i++ ){
            List<Integer> subList = new LinkedList<Integer>( list );
            subList.add( i );
            findCombo( k - 1, n - i, i + 1, subList );
        }
    }
	
	
	
	
	
	public static void main(String[] args) {
		CombinationSumIII c  = new CombinationSumIII();
		c.combinationIII(2, 18);
	}
}
