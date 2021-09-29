package problems;
import java.util.*;
public class MaxFlowGraphTheory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
}

class EdmondKarp 
{ 
    int solve(int N, int M, ArrayList<ArrayList<Integer>> Edges) 
    { 
        // code here
        int[][]graph=new int[N+1][N+1];
        for(ArrayList<Integer>ed:Edges){
            int u=ed.get(0);
            int v=ed.get(1);
            int w=ed.get(2);
            graph[u][v]+=w;
            graph[v][u]+=w;
        }
        
        int maxFlow=0;
        int[]par=new int[N+1];
        while(helper_(graph,par)){
            int possibleFlow=(int)1e9;
            
            for(int v=N;v!=1;v=par[v]){
                int u=par[v];
                int w=graph[u][v];
                possibleFlow=Math.min(possibleFlow,w);
            }
            
            maxFlow+=possibleFlow;
            
            for(int v=N;v!=1;v=par[v]){
                int u=par[v];
                graph[u][v]-=possibleFlow;
                graph[v][u]+=possibleFlow;
            }
            par=new int[N+1];
        }
        
        return maxFlow;
        
    }
    
    boolean helper_(int[][]graph,int[]par){
        int n=graph.length;
        boolean visited[]=new boolean[n];
        ArrayDeque<Integer>q=new ArrayDeque<>();
        q.add(1); visited[1]=true;
        
        while(q.size()!=0){
            int u=q.remove();
            if(u==n-1) return true;
            
            for(int v=0;v<n;v++){
                if(graph[u][v]>0 && visited[v]==false){
                    visited[v]=true;
                    par[v]=u;
                    q.add(v);
                }
            }
        }
        
        return false;
        
    }
}

class MaximumBipartiteMatching 
{
    public int maximumMatch(int[][] G)
    {
        // Code here
        int p=G.length;
        int j=G[0].length;
        HashMap<Integer,ArrayList<Integer>>map=new HashMap<>();
        for(int i=0;i<p;i++){
             map.putIfAbsent(i,new ArrayList<Integer>());
            for(int k=0;k<j;k++){
                if(G[i][k]>0){
                    map.get(i).add(k);
                }
            }
        }
        int len=p+j+2; // source -> p -> j -> t
        int[][]graph=new int[len][len];
        
        for(int per=1;per<len-j-1;per++) graph[0][per]+=1;
        
        for(int job=1+p;job<len-1;job++) graph[job][len-1]+=1;
        
        for(int per=1;per<len-j-1;per++){
            for(int jobs:map.get(per-1)){
                int jidx=jobs+p+1;
                graph[per][jidx]+=1;
            }
        }
        int N=graph.length;
        int maxFlow=0;
        int[]par=new int[N];
        while(helper_(graph,par)){
            int possibleFlow=(int)1e9;
            
            for(int v=N-1;v!=0;v=par[v]){
                int u=par[v];
                int w=graph[u][v];
                possibleFlow=Math.min(possibleFlow,w);
            }
            
            maxFlow+=possibleFlow;
            
            for(int v=N-1;v!=0;v=par[v]){
                int u=par[v];
                graph[u][v]-=possibleFlow;
                graph[v][u]+=possibleFlow;
            }
            par=new int[N];
        }
        
        return maxFlow;
        
    }
    
    boolean helper_(int[][]graph,int[]par){
        int n=graph.length;
        boolean visited[]=new boolean[n];
        ArrayDeque<Integer>q=new ArrayDeque<>();
        q.add(0); visited[0]=true;
        
        while(q.size()!=0){
            int u=q.remove();
            if(u==n-1) return true;
            for(int v=0;v<n;v++){
                if(graph[u][v]>0 && visited[v]==false){
                    visited[v]=true;
                    par[v]=u;
                    q.add(v);
                }
            }
        }
        
        return false;
        
    }
}

