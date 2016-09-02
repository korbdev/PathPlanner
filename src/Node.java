import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.imageio.ImageIO;

public class Node<T> {
	private Node<T> parent;
	private ArrayList<Node<T>> children;
	private int rank;
	private boolean leaf;
	private T content;
	
	private final int NODE_SIZE_X = 20;
	private final int NODE_SIZE_Y = 20;
	
	public Node(T content){
		parent = null;
		children = new ArrayList<Node<T>>();
		leaf = true;
		this.content = content;
		rank = 1;
	}
	
	public T getContent(){
		return content;
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
			System.out.println(item.content.toString()+", "+item.rank);
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
	
	public void draw(String filename){
		int[] initial_walk = this.walk();
		int width = initial_walk[0] * NODE_SIZE_X; //leaves
		int height = initial_walk[2] * NODE_SIZE_Y; //height
		
		int col = 0;
		int row = 0;
		int prev_rank = rank;

		BufferedImage img = new BufferedImage(width+1, height+1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		
		Font font = new Font("serif", Font.PLAIN, 10);
		g.setFont(font);
		Queue<Node<T>> q = new LinkedList<Node<T>>();
		q.offer(this);
		
		while(!q.isEmpty()){
			Node<T> item = q.poll();
			
			int[] data = item.walk();
			width = data[0] * NODE_SIZE_X; //leaves
			height = data[2] * NODE_SIZE_Y; //height
			
			if(item.rank != prev_rank){
				col = 0;
				row++;
			}
			
			prev_rank = item.rank;
			
			g.drawOval(col + (width/2-NODE_SIZE_X/2), NODE_SIZE_Y*row, NODE_SIZE_X, NODE_SIZE_Y);
			
			FontMetrics metrics = g.getFontMetrics(font);
			int w = metrics.stringWidth(item.content.toString())/2;
			int h = metrics.getHeight()/2;
			g.drawString(item.content.toString(), col + width/2-w, NODE_SIZE_Y*row-h+metrics.getAscent()+10);
			
			col += width;
			
			Iterator<Node<T>> it = item.children.iterator();
			while(it.hasNext()){
				Node<T> child = it.next();
				q.offer(child);
			}
		}
		
		File out = new File(filename+".png");
		try {
			ImageIO.write(img, "png", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
