





array: 1 3 5 8 

6

HashMap

1  0
3  1
5  2
8  3






1 3 4 8 
  i j


public ArrayList<Integer> indexOfTaget(int[] array, int target) {
    int i = 0;
    int j = array.length() - 1;

    ArrayList<Integer> result = new ArrayList();

    while (i ! = j) {
        if (array[i] + array[j] == target) {
            result.add(i);
            result.add(j);
        }

        else if (array[i] + array[j] > target) {
            j--;
        }

        else if (array[i] + array[j] < target) {
            i++
        }

    }

    return result;
}
