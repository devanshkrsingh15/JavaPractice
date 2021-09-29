package problems;
import java.util.*;

public class LRU {
//least recently used
	public class Node{
		int key;
		int val;
		Node(int key,int val){
			this.key=key;
			this.val=val;
		}
		
		Node next=null;
		Node prv=null;
		
	}
	
	 Node head=null;
	 Node tail=null;
	
	 HashMap<Integer,Node>hm;
	 int cap;
	 int size;
	
	public void LRUCache(int capacity) {
		this.hm=new HashMap<>();
		this.cap=capacity;
		this.size=0;
	}
	    
	public int get(int key) {
		if(!hm.containsKey(key)) return -1;
		
		int rv=hm.get(key).val;
		MakeMostRecent(hm.get(key));
		return rv;
	}
	
	public void MakeMostRecent(Node node) {
		if(node==this.head) return;
		removeNode(node);
		addFirst(node);
	}
	
	public void removeNode(Node node) {
		if(this.size==1) {
			this.head=null;
			this.tail=null;
		}else if(this.tail==node){
			removeTail(node);
			return;
		}else {
			Node nextNode=node.next;
			Node preNode=node.prv;
			
			node.next=null;
			node.prv=null;
			
			preNode.next=nextNode;
			nextNode.prv=preNode;
		}
		
		this.size--;
	}
	
	public void removeTail(Node node) {
		if(this.size==1) {
			this.head=null;
			this.tail=null;
		}else {
			Node nextNode=this.tail.next;
			this.tail.next=null;
			nextNode.prv=null;
			this.tail=nextNode;
		}
		this.size--;
	}
	
	public void addFirst(Node node) {
		if(this.head==null) {
			this.head=node;
			this.tail=node;
		}else {
		this.head.next=node;
		node.prv=this.head;
		this.head=this.head.next;
		}
		this.size++;
	}
	    
	public void put(int key, int value) {
		if(hm.containsKey(key)) {
			Node node=hm.get(key);
			node.val=value;
			get(key);
		}else {
			if(this.size==this.cap){
				Node node=hm.get(this.tail.key);
				hm.remove(this.tail.key);
				removeTail(node);
			}
			
			Node node=new Node(key,value);
			hm.put(key, node);
			addFirst(node);
		}
	}
	
	public static void main(String[] args) {
		
	}
	

}
