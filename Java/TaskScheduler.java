/*
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */
import java.util.*;
public class TaskScheduler {
	
	/*
	 * PriorityQueue and greedy solution
	 * We need to arrange the characters in string such that each same character is K distance apart, where distance in this problems is time between two similar task execution.
	 * Idea is to add them to a priority Q and sort based on the highest frequency.
	 * And pick the task in each round of 'n' with highest frequency. As you pick the task, decrease the frequency, and put them back after the round.
	 */
	public int leastInterval(char[] tasks, int n) {
		Map<Character, Integer> map = new HashMap<>();
	    for (int i = 0; i < tasks.length; i++) {
	        map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1); // map key is TaskName, and value is number of times to be executed.
	    }
	    PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>( //frequency sort
	    		(a,b) -> !a.getValue().equals(b.getValue()) ? b.getValue() - a.getValue() : a.getKey() - b.getKey());

	    q.addAll(map.entrySet());

	    int count = 0;
	    while (!q.isEmpty()) {
	        int k = n + 1;
	        List<Map.Entry> tempList = new ArrayList<>();
	        while (k > 0 && !q.isEmpty()) {
	            Map.Entry<Character, Integer> top = q.poll(); // most frequency task
	            top.setValue(top.getValue() - 1); // decrease frequency, meaning it got executed
	            tempList.add(top); // collect task to add back to queue
	            k--;
	            count++; //successfully executed task
	        }

	        for (Map.Entry<Character, Integer> e : tempList) {
	            if (e.getValue() > 0) q.add(e); // add valid tasks 
	        }

	        if (q.isEmpty()) 
	        	break;
	        count = count + k; // if k > 0, then it means we need to be idle
	    }
	    return count;
    }
	
	// Same idea, different implementation:
	public int leastInterval22(char[] tasks, int n) {
	    
	    int[] frequencies = new int[26];
	    for (char c : tasks) frequencies[c - 'A']++;
	    
	    Queue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
	    for (int i : frequencies) if (i != 0) pq.add(i);
	    
	    Map<Integer, Integer> map = new HashMap<>();
	    int time = 0, tasksRemaining = tasks.length;
	    while (tasksRemaining > 0) {
	        Integer prev = map.get(time);
	        if (prev != null) pq.add(prev); 
	        Integer cur = pq.poll();
	        if (cur != null) tasksRemaining--;
	        if (cur != null && --cur > 0) map.put(time + n + 1, cur);
	        time++;
	    }
	    return time;
	}
	
	/*
No sorting solution:

tasks = ["A","A","A","B","B","B"], n = 2
The solution will be:

A,B,#
A,B,#
A,B

So you only need to count number of #.
You only need to consider row 1 and row 2. That's why we let max-- and Math.min(max, t) in the code.
The reason of writing n + 1 is that if n is 2, actually means we need 3 columns.
Row 1 and 2 has 2 * 3 totally spaces, then we deduct 2 A and 2B, then we still have two space.
At the end, we check is space smaller than 0, it means cool time is short, and we have more tasks, then every task has enough time to cool down, so the length is exactly the length of tasks.
Otherwise, we let the length of tasks plus the number of #
	 */
	public int leastInterval444(char[] tasks, int n) {
	      int[] times = new int[26];
	      for(char t: tasks){
	        times[t - 'A']++;
	      }
	      
	      int max = 0;
	      for(int t: times){
	        max = Math.max(max, t);
	      }
	      
	      max--;
	      System.out.println(max);
	      int space = max * (n + 1);
	      for(int t: times){
	        space -= Math.min(max, t);
	      }
	      
	      if(space <= 0){
	        return tasks.length;
	      } else {
	        return tasks.length + space; 
	      }
	    }
	
}
/*
proof of greedy works:

The goal is to use least number of intervals.

So, lets say we have AAAAA BBB CCC DD and n = 2
Independently, just execution of A, B, C and D looks like this.
A - idle - idle - A - idle - idle - A - idle - idle - A - idle - idle - A
B - idle - idle - B - idle - idle - B
C - idle - idle - C - idle - idle - C
D - idle - idle - D

As you can see, the execution actually becomes a cycle of 3 tasks. Meaning, the task execution should have 3 unique tasks in a sequence. 
This uniqueness is critical for this problem. The goal is, for each cycle you need make a choice among tasks in such a way that for the next cycle you still have enough unique tasks.

Why? Lets say,
To start with, you have 4 unique tasks, ABC and D. Lets say your execution is B-C-D-B-C-D, which is a valid case, BUT you used up all D. 
Now for the remaining execution, you have only 3 unique tasks. Then you'll pick -B-C-A-. You used up all B and C, and remaining 4 A's, which has to be executed with idle times.

But if instead of choosing D, if you had picked the A at first, by the time you finish executing all B and C, you would still have 2 unique tasks A and D, which further reduces idle times. 
So instead of B-C-D, starting with A-B-C will ensure that, by the time B and C are used up, we still have 2 tasks to reduce idle times.
Also, note that if you start with ABC instead of BCA it can further optimize to use A immediately.
*/
