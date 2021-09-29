package problems;

public class SortingAlgos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]arr= {9,8,7,6,5,4,3,2,1};
		BubbleSort(arr);
		SelectionSort(arr);
		InsertionSort(arr);
		
	}
	
	public static void BubbleSort(int[]nums) {
		int n=nums.length;
		
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<n-1-i;j++) {
				if(nums[j]>nums[j+1]) {
					swap(nums,j,j+1);
				}
			}
		}
		
		for(int ele:nums) System.out.print(ele+" ");
		System.out.println();
	}
	
	public static void SelectionSort(int[]nums) {
		int n=nums.length;
		
		for(int i=0;i<n-1;i++) {
			int min=i;
			for(int j=i+1;j<n;j++) {
				if(nums[j]<nums[min]) min=j;
			}
			swap(nums,min,i);
		}
		for(int ele:nums) System.out.print(ele+" ");
		System.out.println();
		
	}
	
	public static void InsertionSort(int[]nums) {
		int n=nums.length;
		
		for(int i=1;i<n;i++) {
			for(int j=i-1;j>=0;j--) {
				if(nums[j]>nums[j+1]) {
					swap(nums,j,j+1);
				}else {
					break;
				}
			}
		}
		
		for(int ele:nums) System.out.print(ele+" ");
		System.out.println();
	}
	
	public static void swap(int[]arr,int i,int j) {
		int k=arr[i];
		arr[i]=arr[j];
		arr[j]=k;
	}

}
