package problems;

public class SegmentTree {
	static int[]segArr;
	static int[]lazyArr;
	
	void construct(int[]arr) {
		int n=arr.length;
		segArr=new int[4*n];
		lazyArr=new int[4*n];
		
		build_max(arr,0,0,n-1);
		build_sum(arr,0,0,n-1);
		build_lazy(arr,0,0,n-1);
	}
	
	void build_lazy(int[]arr,int idx,int st,int en) {
		//only leaf stores value
		if(st==en){
			lazyArr[idx]=arr[st];
			return ;
		}
		
		int mid=st+(en-st)/2;
		build_lazy(arr,2*idx+1,st,mid);
		build_lazy(arr,2*idx+2,mid+1,en);
	}
	
	void rangeUpdate_lazy(int idx,int st,int en,int l,int r,int val) {
		if(r<st || l>en) return ;
		
		if(st==en || (l<=st && en<=r) ) {
			lazyArr[idx]+=val;
			return ;
		}
		
		int mid=st+(en-st)/2;
		rangeUpdate_lazy(2*idx +1,st,mid,l,r,val);
		rangeUpdate_lazy(2*idx +2,mid+1,en,l,r,val);
	}
	
	int getVal(int idx,int st,int en,int i) {
		if(st==en) return lazyArr[idx];
		
		propagateVals(idx);
		int mid=st+(en-st)/2;
		
		if(st<=i && i<=mid){
			return getVal(2*idx+1,st,mid,i);
		}else {
			return getVal(2*idx+2,mid+1,en,i);
		}
	}
	
	void propagateVals(int idx) {
		int val=lazyArr[idx];
		
		int lidx=2*idx+1;
		int ridx=2*idx+2;
		lazyArr[idx]=0;
		
		lazyArr[lidx]+=val;
		lazyArr[ridx]+=val;
		
		
	}

	void build_max(int[]arr,int idx,int st,int en) {
		if(st==en) {
			segArr[idx]=arr[st];
			return;
		}else{
			int mid=st+(en-st)/2;
			
			build_max(arr,2*idx+1,st,mid);
			build_max(arr,2*idx+2,mid+1,en);
			
			segArr[idx]=Math.max(segArr[2*idx+1], segArr[2*idx+2]);
		}
	}
	
	void build_sum(int[]arr,int idx,int st,int en) {
		if(st==en) {
			segArr[idx]=arr[st];
			return;
		}else{
			int mid=st+(en-st)/2;
			
			build_sum(arr,2*idx+1,st,mid);
			build_sum(arr,2*idx+2,mid+1,en);
			
			segArr[idx] = segArr[2*idx+1]+ segArr[2*idx+2];
		}
	}
	
	int queryMax(int idx,int st,int en,int l,int r) {
		if(en<l || st>r) {
			return -(int)1e9;
		}
		
		if(st==en || l<=st && en<=r) return segArr[idx];
		
		int mid=st+(en-st)/2;
		
		int lans=queryMax(2*idx+1,st,mid,l,r);
		int rans=queryMax(2*idx+2,mid+1,en,l,r);
		
		return Math.max(lans, rans);
		
	}
	
	int querySum(int idx,int st,int en,int l,int r) {
		if(en<l || st>r) {
			return 0;
		}
		
		if(st==en || l<=st && en<=r) return segArr[idx];
		
		int mid=st+(en-st)/2;
		
		int lans=querySum(2*idx+1,st,mid,l,r);
		int rans=querySum(2*idx+2,mid+1,en,l,r);
		
		return lans+rans;
		
	}
	
	void pointUpdateMax(int idx,int i,int st,int en,int[]arr,int val) {
		if(st==en){
			arr[i]=val;
			segArr[idx]=val;
			return;
		}
		
		int mid=st+(en-st)/2;
		
		if(st<=i && i<=mid) {
			pointUpdateMax(2*idx+1,i,st,mid,arr,val);
		}else {
			pointUpdateMax(2*idx+2,i,mid+1,en,arr,val);
		}
		
		segArr[idx]=Math.max(segArr[2*idx+1], segArr[2*idx+2]);
		
	}
	
	void pointUpdateSum(int idx,int i,int st,int en,int[]arr,int val) {
		if(st==en){
			arr[i]=val;
			segArr[idx]=val;
			return;
		}
		
		int mid=st+(en-st)/2;
		
		if(st<=i && i<=mid) {
			pointUpdateSum(2*idx+1,i,st,mid,arr,val);
		}else {
			pointUpdateSum(2*idx+2,i,mid+1,en,arr,val);
		}
		
		segArr[idx] = segArr[2*idx+1]+ segArr[2*idx+2];
		
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
