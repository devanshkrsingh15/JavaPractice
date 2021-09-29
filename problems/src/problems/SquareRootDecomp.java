package problems;

public class SquareRootDecomp {
	static int[]sqrtArray;
	
	void createArayy_Max(int[]arr) {
		int n=arr.length;
		int numBoxes=(int)(Math.ceil(Math.sqrt(n)));
		int boxLen=numBoxes;
		sqrtArray=new int[numBoxes];
		
		for(int i=0;i<n;i++){
			int idx=i/boxLen;
			sqrtArray[idx]=Math.max(sqrtArray[idx], arr[i]);
		}
		
	}
	
	int query_Max(int l,int r,int[]arr) {
		int n=arr.length;
		int numBoxes=(int)Math.sqrt(n);
		int boxLen=(int)Math.ceil(n/numBoxes);
		int ans= -(int)1e9;
		while(l<=r) {
			if(l%boxLen==0 && l+boxLen-1<=r ) {
				ans=Math.max(ans, sqrtArray[l/boxLen]);
				l+=boxLen;
			}else {
				ans=Math.max(ans, arr[l]);
				l++;
			}
		}
		
		return ans;
	}
	
	void update_Max(int[]arr,int val,int i){
		int n=arr.length;
		int numBoxes=(int)(Math.ceil(Math.sqrt(n)));
		int boxLen=numBoxes;
		arr[i]=val;
		sqrtArray[i/boxLen]=Math.max(val, sqrtArray[i/boxLen]);
		
	}

	void createArayy_Sum(int[]arr) {
		int n=arr.length;
		int numBoxes=(int)(Math.ceil(Math.sqrt(n)));
		int boxLen=numBoxes;
		sqrtArray=new int[numBoxes];
		for(int i=0;i<n;i++){
			int idx=i/boxLen;
			sqrtArray[idx]+=arr[i];
		}
		
	}
	
	int query_Sum(int l,int r,int[]arr) {
		int n=arr.length;
		int numBoxes=(int)(Math.ceil(Math.sqrt(n)));
		int boxLen=numBoxes;
		int ans=0;
		while(l<=r) {
			if(l%boxLen==0 && l+boxLen-1<=r ) {
				ans+=sqrtArray[l/boxLen];
				l+=boxLen;
			}else {
				ans+=arr[l];
				l++;
			}
		}
		
		return ans;
	}
	
	void update_Sum(int[]arr,int val,int i){
		int n=arr.length;
		int numBoxes=(int)(Math.ceil(Math.sqrt(n)));
		int boxLen=numBoxes;
		int oval=arr[i];
		arr[i]=val;
		sqrtArray[i/boxLen]-=oval;
		sqrtArray[i/boxLen]+=val;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
