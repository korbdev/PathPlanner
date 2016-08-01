
public class GridNode implements Comparable<GridNode> {
	
	private double key;
	
	private double value;
	
	public GridNode(double key, double value){
		this.key = key;
		this.value = value;
	}
	
	public double getKey(){
		return key;
	}
	
	public double getValue(){
		return value;
	}

	/*@Override
	public int compare(GridNode o1, GridNode o2) {
		if(o1 == null && o2 == null){
			return 0;
		}
		if(o1 == null){
			return -1;
		}
		if(o2 == null){
			return 1;
		}

		if(o1.key == o2.key){
			return 0;
		}
		if(o1.key > o2.key){
			return 1;
		}
		else if(o1.key < o2.key){
			return -1;
		}
		return 0;
	}*/

	@Override
	public int compareTo(GridNode o) {
		if(o == null){
			return 1;
		}
		if(this.key == o.key){
			return 0;
		}
		if(this.key > o.key){
			return 1;
		}
		else if(this.key < o.key){
			return -1;
		}
		return 0;
	}
}
