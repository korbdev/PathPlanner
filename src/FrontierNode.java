import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class FrontierNode extends Node<JFrontier>{
	
	public FrontierNode(JFrontier frontier){
		super(frontier);
	}
	
	public FrontierNode(Node<JFrontier> other){
		super(other);
	}
	
	public FrontierNode[] getLeafsAsArray(){
		ArrayList<Node<JFrontier>> leafs = this.getLeafs();
		FrontierNode[] leafsArray = new FrontierNode[leafs.size()];
		for(int i = 0; i < leafs.size(); i++){
			leafsArray[i] = new FrontierNode(leafs.get(i));
			System.out.println("arr height"+leafsArray[i].getRank());
		}
		return leafsArray;
	}
	
	public Node<JFrontier> findClosestNode(JFrontier content){
		Stack<Node<JFrontier> > s = new Stack<Node<JFrontier> >();
		s.push(this);
		
		double dist = Double.MAX_VALUE;
		Node<JFrontier> result = null;
		
		while(!s.isEmpty()){
			Node<JFrontier> item = s.pop();
			
			JFrontier f = item.getContent();
			
			double temp_distance = JFrontier.getDistance(f.mean_x, f.mean_y, content.mean_x, content.mean_y);
			System.out.println("Dist "+item.getContent().toString() + "->" + content.toString()+": "+temp_distance+", min dist: "+dist);
			if(temp_distance < dist && !item.isMarked()){
				//System.out.println("Dist "+item.getContent().toString() + "->" + content.toString()+": "+temp_distance+", min dist: "+dist);
				dist = temp_distance;
				result = item;
			}
			
			Iterator<Node<JFrontier>> it = item.getChildren().iterator();
			while(it.hasNext()){
				Node<JFrontier> child = it.next();
				s.push(child);
			}
		}
		return result;
	}
}
