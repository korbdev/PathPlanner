import java.util.Arrays;

public class JFrontier {

	public int id;
	
	public int[] points_x;
	
	public int[] points_y;
	
	public double mean_x;
	
	public double mean_y;
	
	public int center_x;
	
	public int center_y;
	
	public int color;
	
	public JFrontier(int id, int[] points_x, int[] points_y, int color){
		this.id = id;
		this.points_x = points_x;
		this.points_y = points_y;
		this.color = color;
		mean_x = getMean(points_x);
		mean_y = getMean(points_y);
		setClosestPointToMean();
	}
	
	private void setClosestPointToMean(){
		if(points_x.length == points_y.length){
			double distance = Double.MAX_VALUE;
			int idx = 0;
			for(int i = 0; i < points_x.length; i++){
				int[] point = new int[2];
				point[0] = points_x[i];
				point[1] = points_y[i];
				
				double temp_distance = getDistance(mean_x, mean_y, (double)point[0], (double)point[1]);
				if(temp_distance < distance){
					distance = temp_distance;
					idx = i;
				}
			}
			center_x = points_x[idx];
			center_y = points_y[idx];
		}
	}
	
	public double getMean(int[] points){
		double sum = 0;
		for(int i = 0; i < points_x.length; i++){
			sum += points[i];
		}
		return sum/(double)points.length;
	}
	
	public static double getDistance(double x_1, double y_1, double x_2, double y_2){
		double sum = Math.abs((x_1 - x_2)*(x_1 - x_2)+(y_1 - y_2)*(y_1 - y_2));
		return Math.sqrt(sum);
	}
	
	@Override
	public String toString(){
		return id+"";
		//return id+", mean("+mean_x+"/"+mean_y+"), center("+center_x+"/"+center_y+")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + center_x;
		result = prime * result + center_y;
		result = prime * result + color;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(mean_x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mean_y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(points_x);
		result = prime * result + Arrays.hashCode(points_y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JFrontier other = (JFrontier) obj;
		if (center_x != other.center_x)
			return false;
		if (center_y != other.center_y)
			return false;
		if (color != other.color)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(mean_x) != Double.doubleToLongBits(other.mean_x))
			return false;
		if (Double.doubleToLongBits(mean_y) != Double.doubleToLongBits(other.mean_y))
			return false;
		if (!Arrays.equals(points_x, other.points_x))
			return false;
		if (!Arrays.equals(points_y, other.points_y))
			return false;
		return true;
	}
}
