
/* 
# Given an unsorted list of integer intervals, represented as length-2 tuples,
# write a function which merges all overlapping intervals and returns a list
# containing all of the merged intervals which do not overlap.
#
# E.g., given the input:
#
#   [(2, 4), (1, 3), (5, 7), (6, 10), (9, 14)]

#   [(3, 6), (1, 2)]
#   [(1, 2), (3, 6)]

#   [(1, 3), (2, 4)]

#
# we expect the output:
#
#   [(1, 4), (5, 14)]
*/
//[(2, 4), (1, 3), (5, 7), (6, 10), (9, 14)]
// (1, 3)  (2, 4) (5, 7) (6, 10) (9, 14)
// (1, 4) (5, 7) (6, 10) (9, 14)
// (1, 4) (5, 10) (9, 14)
// (1, 4) (5, 14)
// sort intervals first based on the begining
// case1: second begining > first end, cannot merge
// case2: second end <= first end, merge
// case3: second begining <= first end, merge


import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Interval {
  int start;
  int end;
}

class Solution {
  
  public ArrayList<Interval> merge(ArrayList<Inteerval> input) {
    sortIntervals(input);
    List<Interval> result = new ArrayList<>();
    result.add(input.get(0));
    for (int i = 1; i < input.size(); i++) {      
      
      Interval first = result.getLast();
      Interval second = input.get(i);
      
      if (canMergeTwoInterval(first, second)) {
        result.add(mergeTwo(first, second));
      } else {
        
      }
    }
    
    return result;
  }
  
  
}

