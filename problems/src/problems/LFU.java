package problems;
import problems.LRU.Node;
import java.util.*;

public class LFU {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
		public class Node{
			int key;
			int val;
			int freq;
			
			Node(int key,int val,int freq){
				this.key=key;
				this.val=val;
				this.freq=freq;
			}
			
			Node next=null;
			Node prev=null;
		}
		
		public class DoublyLL{
			int size=0;
			Node head=null;
			Node tail=null;
			
			public void AddFirst(Node node) {
				if(this.head==null) {
					this.head=node;
					this.tail=node;
				}else {
					this.head.next=node;
					node.prev=this.head;
					this.head=node;
				}
				this.size++;
				overallSize++;
			}
			
			public void RemoveHead() {
				if(this.size==1) {
					this.head=null;
					this.tail=null;
				}else {
					Node prevNode=this.head.prev;
					this.head.prev=null;
					prevNode.next=null;
					this.head=prevNode;
				}
				this.size--;
				overallSize--;
			}
			
			public void RemoveNode(Node node) {
				if(this.size==1) {
					this.head=null;
					this.tail=null;
				}else if(this.tail==node){
					RemoveTail();
					return;
				}else if(this.head==node){
					RemoveHead();
					return;
				}
				else {
					Node nextNode=node.next;
					Node preNode=node.prev;
					
					node.next=null;
					node.prev=null;
					
					preNode.next=nextNode;
					nextNode.prev=preNode;
				}
				
				this.size--;
				overallSize--;
			}
			
			public void RemoveTail() {
				if(this.size==1) {
					this.head=null;
					this.tail=null;
				}else {
					Node nextNode=this.tail.next;
					this.tail.next=null;
					nextNode.prev=null;
					this.tail=nextNode;
				}
				this.size--;
				overallSize--;
			}
		}
		
		HashMap<Integer,Node>nodeMapping;
		HashMap<Integer,DoublyLL>freqMpaping;
		int cap;
		int overallSize;
		int minFreq;
		
		public LFU(int capacity) {
			this.nodeMapping=new HashMap<>();
			this.freqMpaping=new HashMap<>();
			this.cap=capacity;
			this.overallSize=0;
			this.minFreq=1;
			
		}
		
		public int get(int key) {
			if(!nodeMapping.containsKey(key)) return -1;
			
			Node rn=nodeMapping.get(key);
			int rv=rn.val;
			updateCache(rn);
			return rv;
		}
		
		public void updateCache(Node node) {
			int cf=node.freq;
			DoublyLL olist=freqMpaping.get(cf);
			olist.RemoveNode(node);
			if(freqMpaping.get(minFreq).size==0)minFreq++;
			
			node.freq++;
	        int nf=node.freq;
			freqMpaping.putIfAbsent(node.freq, new DoublyLL());
			freqMpaping.get(node.freq).AddFirst(node);
	        nodeMapping.put(node.key,node);
			return;
			
		}
		
		public void put(int key, int value) {
			if(this.cap==0) return;
			if(nodeMapping.containsKey(key)) {
				Node rn=nodeMapping.get(key);
				rn.val=value;
	            nodeMapping.put(key,rn);
				get(key);
			}else {
				if(this.overallSize==this.cap) {
					DoublyLL leastFreqList=freqMpaping.get(minFreq);
					Node rn_tail=leastFreqList.tail;
					nodeMapping.remove(rn_tail.key);
					leastFreqList.RemoveTail();
				}
	            
				minFreq=1;
				Node node=new Node(key,value,1);
				freqMpaping.putIfAbsent(minFreq, new DoublyLL());
				freqMpaping.get(minFreq).AddFirst(node);
	            nodeMapping.put(key,node);
			}
			
		}

}
