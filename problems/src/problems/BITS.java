package problems;
import java.util.*;

@SuppressWarnings("unused")
public class BITS {
	public static long nCr(long n,long r){
		if(r>n) return 0;
		if(r==n || r==0)  return (long)1;
        long n_fact=fact(n);
        long r_fact=fact(r);
        long nmr_fact=fact(n-r);
        
        return n_fact / (r_fact*nmr_fact) ;
    }
	
	public static long nCr_(long n,long r) {
		if(r>n) return 0;
		if(r==n)  return (long)1;
		
		long res=1;
		
		for(long i=0;i<r;i++) {
			res*=(n-i);
			res/=(i+1);
		}
		
		return res;
	}
    
    public static  long fact(long n){
        if(n<=1) return (long)1;
        return n*fact(n-1);
    }

    public static long count(long n)
    {
        // Code Here
        int set_bit=countSetBits(n);
        return helper(n,63,set_bit);
        
    }
    
    public static long helper(long n,int pos,int sb){
        if(pos==0 ){
            return 0;
        }
        
        long mask= ((long)1 <<  pos) ;
        
        long rsb= n&mask;
        
        if(rsb==0) {
        	return helper(n,pos-1,sb);
        }
        long zero=nCr_(pos,sb);
        long one=helper(n,pos-1,sb-1);
        
        return zero+one;
    }
    
    public static int countSetBits(long n){
        int count=0;
        
        while(n!=0){
            n-=(n&(-n));
            count++;
        }
        
        return count;
    }
	
    
	public static void  ArrayDifferentiation(int[]a) {
		
		
		int n=a.length;
		int[]narr=new int[n+1];
		for(int i=1;i<=n;i++)narr[i]=a[i-1];
		
		int limit=(int)(Math.pow(3,n));
		
		for(int i=1;i<limit;i++) {
			int temp=i;
			int sum=0;
			for(int j=1;j<=a.length;j++) {
				int rem=temp%3;
				temp=temp/3;
				
				if(rem==1) {
					sum+=narr[j];
				}else if(rem==2) {
					sum-=narr[j];
				}
				
			}
			
			if(sum==0) {
				System.out.println("YES");
				return;
			}
		}
		
		System.out.println("NO");
		return;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn=new Scanner(System.in);
		int t=scn.nextInt();
		while(t-->0) {
			int n=scn.nextInt();
			int[]a=new int[n];
			for(int i=0;i<n;i++)a[i]=scn.nextInt();
			ArrayDifferentiation(a);
			
		}

	}

}
