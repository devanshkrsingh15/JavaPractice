package problems;

public class FenwickTree {
//given array should be in 1 base indexing
	static int[]fenArr;
	
	//o(n)
	void createFenwickArray_(int[]arr) {
		int n=arr.length;
		fenArr=new int[n];
		
		int[]psum=new int[n];
		for(int i=1;i<n;i++) {
			psum[i]=psum[i-1]+arr[i];
		}
		
		for(int i=1;i<n;i++) {
			fenArr[i]=psum[i]-psum[i-(i&-i)];
		}
		
	}
	
	//o(nlogn)
	void createFenwickArray_1(int[]arr) {
		int n=arr.length;
		fenArr=new int[n];
		
		for(int i=1;i<n;i++) {
			update(i,arr,arr[i]);
		}
	}
	
	
	//o(logn)
	//this ele is the diff between nval and oldval
	void update(int idx,int[]arr,int ele){
		int n=arr.length;
		
		while(idx<n){ 
			fenArr[idx]+=ele;
			idx+=(idx&-idx);
		}
	}
	
	//o(logn)
	int getSum(int idx){
		int ans=0;
		while(idx>0) {
			ans+=fenArr[idx];
			idx-=(idx&-idx);
		}
		return ans;
	}
	
	//o(logn)
	int query(int l,int r){
		return getSum(r)-getSum(l-1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
