import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Node<T> {
	private Node<T> parent;
	private ArrayList<Node<T>> children;
	private int rank;
	private boolean leaf;
	private T content;
	
	public Node(T content){
		parent = null;
		children = new ArrayList<Node<T>>();
		leaf = true;
		this.content = content;
		rank = 1;
	}
	
	public Node<T> getParent(){
		return parent;
	}
	
	public ArrayList<Node<T>> getChildren(){
		return children;
	}
	
	public boolean isLeaf(){
		return leaf;
	}
	
	public void insert(T parent, T toInsert){
		Node<T> n = find(parent);
		Node<T> insert = new Node<T>(toInsert);
		n.addChild(insert);
	}
	
	public Node<T> delete(T toDelete){
		Node<T> n = find(toDelete);
		n.removeChild();
		return n;
	}
	
	public int[] walk(){
		Stack<Node<T>> s = new Stack<Node<T>>();
		s.push(this);
		
		int leafs = 0;
		int nodes = 0;
		int height = 0;
		int[] result = new int[3];
		
		int initialHeight = this.rank;
		
		while(!s.isEmpty()){
			Node<T> item = s.pop();
			nodes++;
			if(item.isLeaf()){
				leafs++;
			}
			int new_height = (item.rank - initialHeight)+1;
			if( new_height > height){
				height = new_height;
			}
			
			Iterator<Node<T>> it = item.children.iterator();
			while(it.hasNext()){
				Node<T> child = it.next();
				s.push(child);
			}
		}
		//System.out.println("Nodes: "+nodes+", Leafs: "+leafs+", Height: "+height);
		result[0] = leafs;
		result[1] = nodes;
		result[2] = height;
		return result;
	}
	
	public Node<T> find(T content){
		Stack<Node<T>> s = new Stack<Node<T>>();
		s.push(this);
		
		while(!s.isEmpty()){
			Node<T> item = s.pop();
			if(item.content.equals(content)){
				return item;
			}
			
			Iterator<Node<T>> it = item.children.iterator();
			while(it.hasNext()){
				Node<T> child = it.next();
				s.push(child);
			}
		}
		return null;
	}
	
	public void removeChild(){
		parent.children.remove(this);
		if(parent.children.isEmpty()){
			parent.leaf = true;
		}
		parent = null;
	}
	
	public void addChild(Node<T> n){
		if(children.isEmpty())
			leaf = false;
		n.rank = rank+1;
		children.add(n);
		n.parent = this;
	}
}
