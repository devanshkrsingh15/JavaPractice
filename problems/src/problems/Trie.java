package problems;
import java.util.*;

public class Trie {
	public class Node{
		char ch;
		Node child[];
		boolean isEnd;
		int numberOfChild;
		String myString;
		
		Node(char ch){
			this.ch=ch;
			this.child=new Node[26]; //only small characters
			this.isEnd=false;
			this.numberOfChild=0;
		}
	}
	
	public Node root;
	
    public Trie() {
        root=new Node(' ');
    }
    
    public void insert(String word) {
    	Node curr=this.root;
    	for(int i=0;i<word.length();i++){
    		char ch=word.charAt(i);
    		if(curr.child[ch-'a']==null){
    			curr.child[ch-'a']=new Node(ch);
    			curr.numberOfChild++;
    		}
    		curr=curr.child[ch-'a'];
    	}
    	curr.isEnd=true;
    	curr.myString=word;
        
    }
    
    public boolean search(String word) {
    	Node curr=this.root;
    	for(int i=0;i<word.length();i++){
    		char ch=word.charAt(i);
    		if(curr.child[ch-'a']==null){
    			return false;
    		}
    		curr=curr.child[ch-'a'];
    	}
    	
    	return curr.isEnd;
        
    }
    
    public boolean startsWith(String word) {
    	Node curr=this.root;
    	for(int i=0;i<word.length();i++){
    		char ch=word.charAt(i);
    		if(curr.child[ch-'a']==null){
    			return false;
    		}
    		curr=curr.child[ch-'a'];
    	}
    	
    	return true;
        
    }
    
    
    public void delete(String word){
    	delete_(word,root,0);
    }
        
    public boolean delete_(String word,Node curr,int idx){
    	if(idx==word.length()) {
    		curr.isEnd=false;
    		if(curr.numberOfChild==0) return false;
    		else return true;
    	}
    	
    	char ch=word.charAt(idx);
    	boolean res=delete_(word,curr.child[ch-'a'],idx+1);
    	if(res==true) return true;
    	
    	if(res==false) {
    		curr.child[ch-'a']=null;
    		curr.numberOfChild--;
    	}
    	
    	if(curr.numberOfChild==0) return false;
    	return true;
    	
    }
    
    //Leetcode 472 Concatenated Words
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
    	ArrayList<String>ans=new ArrayList<>();
    	
    	for(String str:words)insert(str);
    	
    	for(String str:words){
    		Boolean dp[]=new Boolean[str.length()];
    		boolean res=findAllConcatenatedWordsInADict_(str,0,0,dp);
    		if(res) ans.add(str);
    	}
    	return ans;
    }
    
    public boolean findAllConcatenatedWordsInADict_(String str,int idx,int count,Boolean dp[]){
    	if(idx==str.length()) {
    		return count>=2;
    	}
    	
    	if(dp[idx]!=null) return dp[idx];
    	
    	Node curr=this.root;
    	for(int i=idx;i<str.length();i++) {
    		char ch=str.charAt(i);
    		if(curr.child[ch-'a']==null){
    			return  dp[idx]=false;
    		}
    		curr=curr.child[ch-'a'];
    		
    		if(curr.isEnd) {
    			boolean temp=findAllConcatenatedWordsInADict_(str,i+1,count+1,dp);
    			
    			if(temp) return dp[idx]=true;
    		}
    	}
    	
    	return dp[idx]=false;
    }
    //Leetcode 212 Word Search II
    public List<String> findWords(char[][] board, String[] words) {
        ArrayList<String>ans=new ArrayList<>();
        for(String str:words) {
        	insert(str);
        }
        int n=board.length;
        int m=board[0].length;
        int[][]direcs= {{0,1},{1,0},{-1,0},{0,-1}};
        boolean visited[][]=new boolean[n][m];
        Node curr=this.root;
        for(int i=0;i<board.length;i++) {
        	for(int j=0;j<board[0].length;j++) {
        		char ch=board[i][j];
        		
        		if(curr.child[ch-'a']!=null && visited[i][j]==false) {
        			dfs(board,curr.child[ch-'a'],i,j,direcs,ans,visited);
        		}
        	}
        }
        
        return ans;
    }
    
    public void dfs(char[][] board,Node curr,int i,int j,int[][]direcs,ArrayList<String>ans,boolean visited[][]) {
    	if(curr.isEnd){
    		ans.add(curr.myString);
    		curr.isEnd=false;
    	}
    	int n=board.length;
        int m=board[0].length;
    	visited[i][j]=true;
    	for(int k=0;k<direcs.length;k++) {
    		int x=i+direcs[k][0];
    		int y=j+direcs[k][1];
    		
    		if(x>=0 && y>=0 && x<n && y<m && visited[x][y]==false) {
    			char ch=board[x][y];
    			if(curr.child[ch-'a']!=null) {
    				dfs(board,curr.child[ch-'a'],x,y,direcs,ans,visited);
    			}
    		}
    	}
    	visited[i][j]=false;
    	
    }
    
    
   
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Bits_Trie{
	
	public class Node{
		int digit; //0,1;
		Node left; //0
		Node right; //1
		
		Node(int digit){
			this.digit=digit;
		}
	}
	
	Node root=new Node(-1);
	
	public void insert(int number){
		Node curr=this.root;
		
		for(int k=31;k>=0;k--) {
			int mask=(1<<k);
			int val=(number&mask);
			
			if(val==0){
				if(curr.left==null) {
					curr.left=new Node(0);
				}
				curr=curr.left;
			}else{
				if(curr.right==null) {
					curr.right=new Node(1);
				}
				curr=curr.right;
			}
		}
	}
	
	public int find(int ref) {
		int rv=0;
		Node curr=this.root;
		
		for(int k=31;k>=0;k--) {
			int mask=(1<<k);
			int val=(ref&mask);
			
			if(val==0){
				if(curr.left!=null) {
					curr=curr.left;
				}else {
					rv |= (1<<k);
					curr=curr.right;
				}
			}else{
				if(curr.right!=null) {
					rv |= (1<<k);
					curr=curr.right;
				}else {
					curr=curr.left;
				}
			}
		}
		
		return rv;
		
	}
	
	
//Leetcode 421. Maximum XOR of Two Numbers in an Array
    public int findMaximumXOR(int[] nums) {
        for(int ele:nums) insert(ele);
        
        int max=-(int)1e9;
        
        for(int ele:nums) {
        	int ref= Integer.MAX_VALUE-ele;
        	int candidate=find(ref);
        	max=Math.max(max, candidate^ele );
        	
        }
        
        return max;
        
    }
}

// Leetcode 211. Design Add and Search Words Data Structure
class WordDictionary {

    /** Initialize your data structure here. */
	public class Node{
		char ch;
		Node child[];
		boolean isEnd;
		int numberOfChild;
		String myString;
		
		Node(char ch){
			this.ch=ch;
			this.child=new Node[26]; //only small characters
			this.isEnd=false;
			this.numberOfChild=0;
		}
	}
	
	public Node root;
    
    public void insert(String word) {
    	Node curr=this.root;
    	for(int i=0;i<word.length();i++){
    		char ch=word.charAt(i);
    		if(curr.child[ch-'a']==null){
    			curr.child[ch-'a']=new Node(ch);
    			curr.numberOfChild++;
    		}
    		curr=curr.child[ch-'a'];
    	}
    	curr.isEnd=true;
    	curr.myString=word;
        
    }
    
	
    public WordDictionary() {
    	root=new Node(' ');
    }
    
    public void addWord(String word) {
        insert(word);
    }
    
    public boolean search(String word) {
    	return search_(word,0,this.root);
    }
    
    public boolean search_(String word,int idx,Node curr) {
    	if(idx==word.length()) {
    		return curr.isEnd;
    	}
    	
    	char ch=word.charAt(idx);
    	if(ch=='.') {
    		for(int i=0;i<26;i++) {
    			if(curr.child[i]!=null) {
    				boolean res= search_(word,idx+1,curr.child[i]);
    				if(res) return true;
    			}
    		}
    		
    		return false;
    	}
    	
    	if(curr.child[ch-'a']==null) return false;
    	
    	return search_(word,idx+1,curr.child[ch-'a']);
    }
    
}
