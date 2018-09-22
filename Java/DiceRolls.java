/*
backtracking:
a "brute force" algorithmic technique (try all paths)
often implemented recursively

decisions:
	if there are no more decisions to make:
		stop
	else, let handle one decision ourselves, and the rest by recursion
		for each available choice C for this decision:
			Choose C
			Explore the remaining choice that can follow C
			Un-choose C (backtracking!!)

 */

import java.util.*;
public class DiceRolls {
	static int call1;
	static int call2;
	
	public List<List<Integer>> diceRoll(int diceNum) {
		List<List<Integer>> res = new ArrayList<>();
		diceHelper(res, new ArrayList<>(), diceNum);
		return res;
	}
	
	private void diceHelper(List<List<Integer>> res, ArrayList<Integer> list, int remain) {
		if(remain == 0) {  // base case
			//res.add(list); !!!!
			res.add(new ArrayList<>(list));  //!!!
		}
		else {
			for(int i = 1; i <= 6; i++) {
				list.add(i);
				diceHelper(res, list, remain - 1);
				list.remove(list.size() - 1);
			}
		}
	}
	
	public List<List<Integer>> diceSum(int diceNum, int sum) {
		List<List<Integer>> res = new ArrayList<>();
		diceSumHelper(res, new ArrayList<>(), diceNum, sum);
		return res;
	}
	
	private void diceSumHelper(List<List<Integer>> res, ArrayList<Integer> list, int diceRemain, int sum) {
		call2++;
		if(diceRemain == 0) {  // if we have perfect restriction, we don't need to check: if(sum == 0)
			//res.add(list); !!!!
			res.add(new ArrayList<>(list));  //!!!
		}
		else {
			for(int i = 1; i <= 6; i++) {
				// avoid choose a too big i or too small i
				int newSum = sum - i;
				if(newSum >= 1 * (diceRemain - 1) && newSum <= 6 * (diceRemain - 1)) {  // if(newSum >= 0) is not good enough
					list.add(i);
					diceSumHelper(res, list, diceRemain - 1, newSum);
					list.remove(list.size() - 1);
				}
			}
		}
	}
	
	// inefficient, since it trys all paths of the decision tree
	private void diceSumHelper2(List<List<Integer>> res, ArrayList<Integer> list, int diceRemain, int sum) {
		call1++;
		if(diceRemain == 0) {
			if(calcalateSum(list) == sum)
				res.add(new ArrayList<>(list));  //!!!
		}
		else {
			for(int i = 1; i <= 6; i++) {
				list.add(i);
				diceSumHelper2(res, list, diceRemain - 1, sum);
				list.remove(list.size() - 1);
			}
		}
	}
	
	private int calcalateSum(ArrayList<Integer> list) {
		//ArrayList<Integer> l = new ArrayList<>(list);
		int sum = 0;
		for(int dice : list) {
			sum += dice;
		}
		return sum;
	}
	
	
	
	public static void main(String[] args) {
		DiceRolls d = new DiceRolls();
		//System.out.println(d.diceRoll(3));
		
		//long startTime = System.currentTimeMillis();
		
		System.out.println(d.diceSum(4, 9));
		System.out.println(call2);
		
		//long stopTime = System.currentTimeMillis();
	    //System.out.println(stopTime - startTime);
	}
}
