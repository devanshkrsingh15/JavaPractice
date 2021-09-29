package problems;

public class PreFixSum {
	
	int[]psum;
	
	void createPrefixSumArray(int[]arr) {
		int n=arr.length;
		
		psum=new int[n];
		
		for(int i=0;i<n;i++) {
			psum[i]=arr[i] + ((i==0)?0:psum[i-1]);
		}
	}
	
	int queryGetSum(int l,int r) {
		return psum[r]-((l==0)?0:psum[l-1]);
	}
	//return count of ele in arr
	int PrefixCount(int[]arr,int ele) {
		//ele is in arr
		
		int  n=arr.length;
		int pcount[]=new int[n];
		
		for(int i=0;i<n;i++) {
			if(i==0) {
				pcount[i]=(arr[i]==ele)?1:0;
			}else {
				pcount[i]+=(arr[i]==ele)?1:0;
				pcount[i]+=pcount[i-1];
			}
		}
		
		return pcount[n-1];
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
