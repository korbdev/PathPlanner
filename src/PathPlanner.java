import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class PathPlanner{
	
	public static final int INF = Integer.MAX_VALUE;
	
	public static final int UNKNOWN = 2;
	public static final int WALL = 1;
	public static final int FREE = 3;
	
	public double[][] map;
	public double[][] reachability_map;
	public int[][] parent_map;
	public int[][] safety_map;
	public int rows;
	public int cols;
	public int r;
	public int c;
	public int robot_size;
	public boolean matlab_indexing;
	
	public PathPlanner(double[][] map, int rows, int cols, int robot_size, boolean matlab_indexing){
		this.map = map;
		reachability_map = new double[rows][cols];
		parent_map = new int[rows][cols];
		safety_map = new int[rows][cols];
		this.rows = rows;
		this.cols = cols;
		this.robot_size = robot_size;
		this.matlab_indexing = matlab_indexing;
	}

	public double[][] planCostMap(int r, int c, boolean allowUnknownExploration){
		if(matlab_indexing){
			r--;
			c--;
		}
		
		this.r = r;
		this.c = c;
		
		//int start_i = r;
		//int start_j = c;

		double[][] r_map = new double[rows][cols];

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				reachability_map[i][j] = -1;
				safety_map[i][j] = 2;
				parent_map[i][j] = 0;
				r_map[i][j] = INF;
			}
		}
		r_map[r][c] = 0;
		reachability_map[r][c] = 0;
		
		PriorityQueue<GridNode> pQ = new PriorityQueue<GridNode>();
		pQ.add(new GridNode(0, r * cols + c));

		while(!pQ.isEmpty()){
			GridNode n = pQ.poll();
			int i = (int) n.getValue()/cols;
			int j = (int) n.getValue()%cols;

			if(!inCollision(robot_size, i, j, allowUnknownExploration)){
				for(int k = -1; k <= 1; k++){
					for(int l = -1; l <= 1; l++){
						int n_i = i+k;
						int n_j = j+l;
						if(inRange(n_i, n_j)){
							if(k != 0 || l != 0){
								if(isPassable(n_i, n_j, allowUnknownExploration) && !inCollision(robot_size, n_i, n_j, allowUnknownExploration)){
									safety_map[n_i][n_j] = FREE;
									if(r_map[n_i][n_j] == INF){
										double distance = r_map[i][j] + getEuklidianDistance(i, j, n_i, n_j);
										double index = n_i * cols + n_j;
										pQ.add(new GridNode(distance, index));
										if(distance < r_map[n_i][n_j]){
											parent_map[n_i][n_j] = (int)n.getValue();
											r_map[n_i][n_j] = distance;
											reachability_map[n_i][n_j] = distance;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return reachability_map;
	}

	public boolean isPassable(int row, int col, boolean allowUnknownExploration){
		if(allowUnknownExploration){
			return map[row][col] != WALL;// && map[row][col] != FREE;
		}
		else{
			return map[row][col] != WALL && map[row][col] != UNKNOWN;
		}
	}
	
	public boolean inRange(int row, int col){
		return row >= 0 && row < rows && col >= 0 && col < cols;
	}
	
	public boolean isGoal(int row, int col){
		return row == r && col == c;
	}
	
	public boolean inCollision(int buffer, int row, int col, boolean allowUnknownExploration){
		boolean collision = false;
		for(int i = -buffer; i <= buffer; i++){
			for(int j = -buffer; j <= buffer; j++){
				int n_i = row+i;
				int n_j = col+j;
				if(inRange(n_i, n_j)){
					//if(map[n_i][n_j] == FREE){
					//	safety_map[row][col] = FREE;
					//}
					if(allowUnknownExploration){
						if(map[n_i][n_j] == WALL){
							collision = true;
							safety_map[row][col] = WALL;
						}
						//if(map[n_i][n_j] == FREE){
						//	collision = true;
						//	safety_map[row][col] = FREE;
						//}
					}
					else{
						if(map[n_i][n_j] == WALL){
							collision = true;
							safety_map[row][col] = WALL;
						}
						if(map[n_i][n_j] == UNKNOWN){
							collision = true;
							safety_map[row][col] = UNKNOWN;
						}
					}
				}
				else{
					collision = true;
				}
			}
		}
		return collision;
	}
	
	//index in/out
	public int[][] generatePath(int row, int col){
		if(matlab_indexing){
			row--;
			col--;
		}
		int goal_idx = row * cols + col;
		int start_idx = r * cols + c;
		ArrayList<Integer[]> path = new ArrayList<Integer[]>();
		//check if path from (row/col) is possible or if we are already in a goal state
		if(parent_map[row][col] != 0 && !isGoal(row, col)){
			//walk from goal(row, col) to start(r, c)
			path.add(new Integer[]{row, col});
			while(goal_idx != start_idx){	
				goal_idx = parent_map[row][col];
				row = goal_idx / cols;
				col = goal_idx % cols;
				path.add(new Integer[]{row, col});
			}
		}
		int[][] arr_path = new int[path.size()][2];
		
		for(int i = 0; i < path.size(); i++){
			if(matlab_indexing){
				arr_path[i][0] = path.get(i)[0]+1;
				arr_path[i][1] = path.get(i)[1]+1;
			}
			else{
				arr_path[i][0] = path.get(i)[0];
				arr_path[i][1] = path.get(i)[1];
			}
		}
		
		return arr_path;
	}
	
	public static double getEuklidianDistance(double x_a, double y_a, double x_b, double y_b){
		return Math.sqrt( 
				((double)x_b - (double)x_a)*((double)x_b - (double)x_a)
				+ 
				((double)y_b - (double)y_a)*((double)y_b - (double)y_a)
				);
	}
	
	public double[][] computeBottleNeckMap(){
		double[][] bottleNeckMap = new double[rows][cols];
		boolean indexing = matlab_indexing;
		matlab_indexing = false;
		//ArrayList<Point> points = getFreePoints();
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(reachability_map[i][j] != 0){
					int[][] path = generatePath(i, j);
					
					//traverse path
					for(int k = 0; k < path.length; k++){
						int r = path[k][0];
						int c = path[k][1];
						bottleNeckMap[r][c]++;
					}
				}
			}
		}
		matlab_indexing = indexing;
		return bottleNeckMap;
	}
	
	public ArrayList<Point> getFreePoints(){
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(reachability_map[i][j] == FREE){
					points.add(new Point(i, j));
				}
			}
		}
		return points;
	}
	
	public void printMap(){
		for(int i = 0; i < 240; i++){
			for(int j = 0; j < 240; j++){
				System.out.print(map[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
