package problems;
import java.util.*;

public class Tree {
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int val){
			this.val=val;
		}
	}
	
	public void PreRec(TreeNode node){
		if(node==null) return;
		System.out.print(node.val +  " ");
		PreRec(node.left);
		PreRec(node.right);
	}
	
	public void InRec(TreeNode node){
		if(node==null) return;
		InRec(node.left);
		System.out.print(node.val +  " ");
		InRec(node.right);
	}
	
	public void PostRec(TreeNode node){
		if(node==null) return;
		PostRec(node.left);
		PostRec(node.right);
		System.out.print(node.val +  " ");
	}
	
	public void Morris_Preorder(TreeNode node){
		if(node==null) return;
		
		TreeNode curr=node;
		
		while(curr!=null) {
			TreeNode next=curr.left;
			
			if(next==null) {
				System.out.print(curr.val + " ");
				curr=curr.right;
			}else{
				TreeNode rightMost=getRightMost(next,curr);
				if(rightMost.right==null) {
					System.out.print(curr.val + " ");
					rightMost.right=curr;
					curr=curr.left;
				}else {
					curr=curr.right;
					rightMost.right=null;
				}
			}
		}
		
	}
	
	public TreeNode getRightMost(TreeNode node,TreeNode curr) {
		TreeNode temp=node;
		while(temp.right!=null && temp.right!=curr) temp=temp.right;
		return temp;
	}
	
	public void Morris_Inorder(TreeNode node){
		if(node==null) return;
		TreeNode curr=node;
		while(curr!=null) {
			TreeNode next=curr.left;
			if(next==null) {
				System.out.print(curr.val + " ");
				curr=curr.right;
			}else{
				TreeNode rightMost=getRightMost(next,curr);
				if(rightMost.right==null) {
					rightMost.right=curr;
					curr=curr.left;
				}else {
					System.out.print(curr.val + " ");
					curr=curr.right;
					rightMost.right=null;
				}
			}
		}
	}
	
	public void Morris_Postorder(TreeNode node){
		if(node==null) return;
		TreeNode curr=node;
		
		while(curr!=null) {
			TreeNode next=curr.right;
			
			if(next==null) {
				System.out.print(curr.val + " ");
				curr=curr.left;
			}else{
				TreeNode leftMost=getLeftMost(next,curr);
				if(leftMost.left==null) {
					System.out.print(curr.val + " ");
					leftMost.left=curr;
					curr=curr.right;
				}else {
					curr=curr.left;
					leftMost.left=null;
				}
			}
		}
		
		// revrse the ans at the end
		
	}
	
	public TreeNode getLeftMost(TreeNode node,TreeNode curr) {
		TreeNode temp=node;
		while(temp.left!=null && temp.left!=curr) temp=temp.left;
		return temp;
	}
	
	public List<List<Integer>> levelOrder(TreeNode node) {
	       List<List<Integer>>ans=new ArrayList<>();
			if(node==null) return ans;
			
			ArrayDeque<TreeNode>q=new ArrayDeque<>();
			q.add(node);
			while(q.size()!=0){
				int s=q.size();
	            List<Integer>lv=new ArrayList<>();
				while(s-->0){
					TreeNode rn=q.remove();
					lv.add(rn.val);
					if(rn.left!=null) q.add(rn.left);
					if(rn.right!=null) q.add(rn.right);
				}
	            ans.add(lv);
			}
			
			return ans; 
	    }
	
	public List<Integer> RightSideView(TreeNode node){
		List<Integer>ans=new ArrayList<>();
		if(node==null) return ans;
		
		ArrayDeque<TreeNode>q=new ArrayDeque<>();
		q.add(node);
		while(q.size()!=0){
			int s=q.size();
			TreeNode temp=q.getLast();
			ans.add(temp.val);
			while(s-->0){
				TreeNode rn=q.remove();
				if(rn.left!=null) q.add(rn.left);
				if(rn.right!=null) q.add(rn.right);
			}
		}
		
		return ans;
		
	}
	
	public List<Integer> LeftSideView(TreeNode node){
		List<Integer>ans=new ArrayList<>();
		if(node==null) return ans;
		
		ArrayDeque<TreeNode>q=new ArrayDeque<>();
		q.add(node);
		while(q.size()!=0){
			int s=q.size();
			TreeNode temp=q.getFirst();
			ans.add(temp.val);
			while(s-->0){
				TreeNode rn=q.remove();
				if(rn.left!=null) q.add(rn.left);
				if(rn.right!=null) q.add(rn.right);
			}
		}
		
		return ans;
		
	}
	
	public int[] BottomView(TreeNode node){
		if(node==null) return new int[0];
		HashMap<Integer,Integer>hm=new HashMap<>();
		int min=(int)1e9;
		int max=-(int)1e9;
		
		ArrayDeque<Pair>q=new ArrayDeque<>();
		q.add(new Pair(node,0));
		while(q.size()!=0) {
			int s=q.size();
			while(s-->0) {
				Pair rp=q.remove();
				TreeNode rn=rp.node;
				int idx=rp.idx;
				
				hm.put(idx,rn.val);
				min=Math.min(min, idx);
				max=Math.max(max, idx);
				
				if(rn.left!=null) {
					q.add(new Pair(rn.left,idx-1));
				}
				if(rn.right!=null) {
					q.add(new Pair(rn.right,idx+1));
				}
			}
		}
		
		
		
		int[]ans=new int[max-min+1];
		int ptr=0;
		for(int i=min;i<=max;i++) {
			ans[ptr++]=hm.get(i);
		}
		return ans;
	}
	
	public class Pair{
		TreeNode node;
		int idx;
		Pair(TreeNode node,int idx){
			this.idx=idx;
			this.node=node;
		}
	}
	
	public int[] TopView(TreeNode node){
		if(node==null) return new int[0];
		HashMap<Integer,Integer>hm=new HashMap<>();
		int min=(int)1e9;
		int max=-(int)1e9;
		
		ArrayDeque<Pair>q=new ArrayDeque<>();
		q.add(new Pair(node,0));
		while(q.size()!=0) {
			int s=q.size();
			while(s-->0) {
				Pair rp=q.remove();
				TreeNode rn=rp.node;
				int idx=rp.idx;
				
				hm.putIfAbsent(idx,rn.val);
				min=Math.min(min, idx);
				max=Math.max(max, idx);
				
				if(rn.left!=null) {
					q.add(new Pair(rn.left,idx-1));
				}
				if(rn.right!=null) {
					q.add(new Pair(rn.right,idx+1));
				}
			}
		}
		
		
		
		int[]ans=new int[max-min+1];
		int ptr=0;
		for(int i=min;i<=max;i++) {
			ans[ptr++]=hm.get(i);
		}
		return ans;
	}
	
	public class VPair{
		TreeNode node;
		int lidx;
		int hidx;
		VPair(TreeNode node,int lidx,int hidx){
			this.node=node;
			this.lidx=lidx;
			this.hidx=hidx;
		}
	}

	public List<List<Integer>>verticalTraversal(TreeNode node) {
    
	List<List<Integer>>ans=new ArrayList<>();
	if(node==null) return ans;
	
	 PriorityQueue<VPair>pq=new PriorityQueue<>((a,b)->{
		 if(a.lidx==b.lidx) return a.node.val-b.node.val;
         
		 else return a.lidx-b.lidx;
	 });
    
	HashMap<Integer,List<Integer>>hm=new HashMap<>();
		int min=(int)1e9;
		int max=-(int)1e9;
		pq.add(new VPair(node,0,0));
		while(pq.size()!=0) {
			int s=pq.size();
			while(s-->0) {
				VPair rp=pq.remove();
				TreeNode rn=rp.node;
				int lidx=rp.lidx;
				int hidx=rp.hidx;
				hm.putIfAbsent(hidx,new ArrayList<>());
                
				hm.get(hidx).add(rn.val);
                
				min=Math.min(min, hidx);
				max=Math.max(max, hidx);
				
				if(rn.left!=null) {
					pq.add(new VPair(rn.left,lidx+1,hidx-1));
				}
				if(rn.right!=null) {
					pq.add(new VPair(rn.right,lidx+1,hidx+1));
				}
			}
		}
		
		for(int i=min;i<=max;i++) {
			ans.add(hm.get(i));
		}
    
		return ans;
	 
}
	
	public List<List<Integer>>revlevelOrder(TreeNode node){
		List<List<Integer>>ans=new ArrayList<>();
		 Stack<List<Integer>>st=new Stack<>();
		 if(node==null) return ans;
		 ArrayDeque<TreeNode>q=new ArrayDeque<>();
		 q.add(node);
		 while(q.size()!=0){
			 int s=q.size();
			 List<Integer>lv=new ArrayList<>();
				while(s-->0){
					TreeNode rn=q.remove();
					lv.add(rn.val);
					if(rn.left!=null) q.add(rn.left);
					if(rn.right!=null) q.add(rn.right);
				}
				st.push(lv);
			}
			
		 
		 while(st.size()!=0) {
			 ans.add(st.pop());
		 }
			return ans; 
		 
	}	
	
	public ArrayList<Integer> diagonalTraversal(TreeNode node){
		ArrayList<Integer>ans=new ArrayList<>();
		if(node==null) return ans;
		
		HashMap<Integer,ArrayList<Integer>>hm=new HashMap<>();
		int min=(int)1e9;
		int max=-(int)1e9;
		ArrayDeque<Pair>q=new ArrayDeque<>();
		q.add(new Pair(node,0));
		while(q.size()!=0) {
			int s=q.size();
			while(s-->0) {
				Pair rp=q.remove();
				TreeNode rn=rp.node;
				int idx=rp.idx;
				
				hm.putIfAbsent(idx,new ArrayList<>());
				hm.get(idx).add(rn.val);
				min=Math.min(min, idx);
				max=Math.max(max, idx);
				
				if(rn.left!=null) {
					q.add(new Pair(rn.left,idx-1));
				}
				if(rn.right!=null) {
					q.add(new Pair(rn.right,idx));
				}
				}
			}
		
		for(int i=max;i>=min;i--) {
			for(int ele:hm.get(i)) {
				ans.add(ele);
			}
		}
		
		return ans;
	}

	public ArrayList<Integer> BoundaryTraversal(TreeNode node){
		ArrayList<Integer>ans=new ArrayList<>();
		if(node==null) return ans;
		ans.add(node.val);
		if(node.left==null && node.right==null) return ans;
		
		leftBoundaryNodes(node.left,ans);
		leafNodes(node,ans);
		rightBoundaryNodes(node.right,ans);
		return ans;
	}
	
	public void rightBoundaryNodes(TreeNode curr, ArrayList<Integer> ans) {
		if(curr.left==null && curr.right==null) return;
		if(curr.right!=null) rightBoundaryNodes(curr.right,ans);
		else if(curr.left!=null) rightBoundaryNodes(curr.left,ans);
		ans.add(curr.val);
	}

	public void leafNodes(TreeNode curr, ArrayList<Integer> ans) {
		if(curr==null) return;
		if(curr.left==null && curr.right==null) ans.add(curr.val);
		leafNodes(curr.left,ans);
		leafNodes(curr.right,ans);
	
		
	}

	public void leftBoundaryNodes(TreeNode curr, ArrayList<Integer> ans) {
		if(curr.left==null && curr.right==null) return;
		ans.add(curr.val);
		if(curr.left!=null) leftBoundaryNodes(curr.left,ans);
		else if(curr.right!=null) leftBoundaryNodes(curr.right,ans);
	}
	//Leetcode 99. Recover Binary Search Tree
	 TreeNode a=null;
	 TreeNode b=null;
	 TreeNode prev=null;
	  public void recoverTree(TreeNode root) {
	        recoverTree_(root);
	        if(a!=null && b!=null){
	            int temp=a.val;
	            a.val=b.val;
	            b.val=temp;
	        }
	    }
	    
	  public void recoverTree_(TreeNode curr){
	        if(curr==null) return ;
	        
	        recoverTree_(curr.left);
	        
	        if(prev!=null && prev.val>curr.val && a==null){
	            a=prev;
	        }
	        if(prev!=null && prev.val>curr.val && a!=null){
	            b=curr;
	        }
	        
	        prev=curr;
	        
	        recoverTree_(curr.right);
	        
	    }
	
	//Leetcode 834. Sum of Distances in Tree
		public int[] sumOfDistancesInTree(int n, int[][] edges) {
	        int[]ans=new int[n];
	        ArrayList<Integer>graph[]=new ArrayList[n];
	        makeGraph(graph,edges);
	        int[]subtreeSize=new int[n];
	        calculateSubtreeSize(0,graph,subtreeSize,new boolean[n]);
	        calculaterefDist(ans,graph,new boolean[n]);
	        makeAns(0,graph,subtreeSize,new boolean[n],ans);
	        
	        return ans;
	    }
		
		public void makeAns(int src,ArrayList<Integer>graph[],int[]subtreeSize,boolean[]visited,int[]ans) {
			int n=graph.length;
			visited[src]=true;
			for(int nbr:graph[src]) {
				if(!visited[nbr]) {
					ans[nbr]=ans[src] + (n-subtreeSize[nbr]) - subtreeSize[nbr];
					
					makeAns(nbr,graph,subtreeSize,visited,ans);
				}
			}
		}
		
		public void calculaterefDist(int[]ans,ArrayList<Integer>graph[],boolean[]visited) {
			Queue<Integer>q=new ArrayDeque<>();
			q.add(0);
			int level=0;
			int dist=0;
			visited[0]=true;
			while(q.size()!=0) {
				int s=q.size();
				while(s-->0) {
					int rv=q.remove();
					dist+=level;
					for(int nbr:graph[rv]) {
						if(!visited[nbr]) {
							visited[nbr]=true;
							q.add(nbr);
						}
					}
				}
				level++;
			}
			
			ans[0]=dist;
		}
		
		public void calculateSubtreeSize(int src,ArrayList<Integer>graph[],int[]subtreeSize,boolean []visited) {
			visited[src]=true;
			for(int nbr:graph[src]) {
				if(!visited[nbr]) {
					calculateSubtreeSize(nbr,graph,subtreeSize,visited);
					subtreeSize[src]+=subtreeSize[nbr];
				}
			}
			subtreeSize[src]++;
		}
		
		public void makeGraph( ArrayList<Integer>graph[],int[][] edges) {
			int n=graph.length;
			for(int i=0;i<n;i++){
				graph[i]=new ArrayList<>();
			}
			
			for(int[]ed:edges) {
				int u=ed[0];
				int v=ed[1];
				graph[u].add(v);
				graph[v].add(u);
				
			}
		}
	public static void main(String[] args) {
		
	}

}

//Window -> Preferences -> Java -> Code Style -> Formatter
