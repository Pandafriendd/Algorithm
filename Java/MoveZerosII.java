/*
Given an array of integers, move all the 0s to the right end of the array.

The relative order of the elements in the original array need to be maintained.

Assumptions:

The given array is not null.
Examples:

{1} --> {1}
{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0}
*/

public class Solution {
  public int[] moveZero(int[] array) {
    if (array == null || array.length == 0 || array.length == 1) {
      return array;
    }

    int slow = 0;  // [0, slow - 1] is processed
    int fast = 0;  // current index

    while (fast < array.length) {
      if (array[fast] != 0) {   // should be kept
        array[slow++] = array[fast++];
      } else {  // should be ignored
        fast++;
      }
    }
    // post processed
    return addZero(array, slow); 
  }

  private int[] addZero(int[] array, int slow) {
    for (int i = slow; i < array.length; i++) {
      array[i] = 0;
    }

    return array;
  }
}
