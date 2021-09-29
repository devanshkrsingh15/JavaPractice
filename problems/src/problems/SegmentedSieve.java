package problems;
import java.util.*;

public class SegmentedSieve {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn=new Scanner(System.in);
		int t=scn.nextInt();
		
		while(t-->0){
			int a=scn.nextInt();
			int b=scn.nextInt();
			
			 ArrayList<Integer>arr=helper(a,b);
			
			for(int ele:arr) System.out.println(ele);
			System.out.println();
		}
		scn.close();
		

	}
	
	public static ArrayList<Integer> helper(int a,int b){
		//false-> is prime, true-> not prime
		boolean[]flag=new boolean[b-a+1];
		ArrayList<Integer>prime=primeOfRootUpperLimit((int)Math.sqrt(b));
		
		for(int p:prime) {
			int st_m=(int)Math.ceil((a*1.0)/p);
			//if st_m==1 then first a can be neglected
			if(st_m==1) st_m++;
			
			int idx=st_m*p - a;
			
			for(int j=idx;j<b-a+1;j+=p) {
				flag[j]=true;
			}
		}
		ArrayList<Integer>list=new ArrayList<>();
		for(int i=0;i<b-a+1;i++) {
			if(flag[i]==false && i+a!=1 )list.add(i+a);
		}
		
		
		
		return list;
	}

	public static ArrayList<Integer> primeOfRootUpperLimit(int nums) {
		//false-> is prime, true-> not prime
		boolean[]flag=new boolean[nums+1];
		
		for(int i=2;i*i<=nums;i++) {
			if(flag[i]==false) {
				for(int j=2*i;j<=nums;j+=i){
					flag[j]=true;
				}
			}
		}
		
		ArrayList<Integer>list=new ArrayList<>();
		for(int i=1;i<=nums;i++) {
			if(flag[i]==false && i!=1 )list.add(i);
		}
		
		return list;
	}
	
	

}
