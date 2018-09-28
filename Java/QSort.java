import java.util.Arrays;

public class QSort {
	void QuickSort(int[] nums, int low, int high) {
		if(high <= low)
			return;
		int mid = (low + high) / 2; //set pivot index to be mid
		swap(nums, mid, high); // put pivot to the last position
		int index = partition2(nums, low, high, nums[high]);
		swap(nums, index, high);
		QuickSort(nums, low, index - 1);//left subarray
		QuickSort(nums, index + 1, high);//right subarray
	}
	
	int partition(int[] nums, int low, int high, int pivot) {
		while(low < high) {
			while(nums[low] <= pivot && low < high)
				low++;
			while(nums[high] >= pivot && low < high)
				high--;
			swap(nums, low, high);
		}
		return low;
	}
	
	//return index, another partition method
	int partition2(int[] nums, int low, int high, int pivot) {
		while(low < high) {
			if(nums[low++] > pivot) {
				swap(nums, --low, --high);
			}
		}
		swap(nums, low, high);
		
		return low;
	}
	
	void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
	
	public static void main(String[] args) {
		QSort q = new QSort();
		int[] a = {4,5,3,1,2,7,10,0,33,-10};
		int n = a.length - 1;
		System.out.println(Arrays.toString(a));
		q.QuickSort(a, 0, n);
		System.out.println(Arrays.toString(a));
	}
}
