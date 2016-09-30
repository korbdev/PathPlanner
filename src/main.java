import java.util.ArrayList;
import java.util.PriorityQueue;
import java.math.*;
public class main {

	public static void main(String[] args) {

		Content<String> c1 = new Content<String>(1, "Eins");
		
		Content<String> c2 = new Content<String>(2, "Zwei");
		
		Content<String> c3 = new Content<String>(3, "Drei");
		
		Content<String> c4 = new Content<String>(4, "Vier");
		
		Content<String> c5 = new Content<String>(5, "Fuenf");
		
		Content<String> c6 = new Content<String>(6, "Sechs");
		
		Content<String> c7 = new Content<String>(7, "Sieben");
		
		Node<Content<String>> n = new Node<Content<String>>(c1);
		
		/*n.insert(c1, c2);
		n.insert(c2, c3);
		
		n.insert(c1, c5);
		n.insert(c1, c4);
		
		n.insert(c2, c6);
		
		n.insert(c3, c7);*/

		n.insert(c1, c2);
		n.insert(c2, c3);
		
		n.insert(c3, c4);
		n.insert(c4, c5);
		
		n.insert(c5, c6);
		
		n.insert(c6, c7);

		n.insert(c3, c6);
		
		n.insert((Content<String>)n.getContent(), c7);
		
		int[] res  = n.walk();
		
		Node<Content<String>> n1 = n.find(c2);
		res = n1.walk();
		
		n.walk();
		
		n.draw("output");
		
		n.delete(c5);
		
		n.draw("output2");
		
		int[] points_x = {0, 1, 2, 3};
		int[] points_y = {0, 1, 2, 3};
		
		JFrontier f1 = new JFrontier(1, points_x, points_y, 1);
		JFrontier f2 = new JFrontier(2, points_x, points_y, 2);
		JFrontier f3 = new JFrontier(3, points_x, points_y, 3);
		JFrontier f4 = new JFrontier(4, points_x, points_y, 4);
		JFrontier f5 = new JFrontier(5, points_x, points_y, 5);
		JFrontier f6 = new JFrontier(6, points_x, points_y, 6);
		JFrontier f7 = new JFrontier(7, points_x, points_y, 7);
		JFrontier f8 = new JFrontier(8, points_x, points_y, 8);
		JFrontier f9 = new JFrontier(9, points_x, points_y, 9);
		JFrontier f10 = new JFrontier(10, points_x, points_y, 10);
		JFrontier f11 = new JFrontier(11, points_x, points_y, 11);
		JFrontier f12 = new JFrontier(12, points_x, points_y, 12);
		JFrontier f13 = new JFrontier(13, points_x, points_y, 13);
		JFrontier f14 = new JFrontier(14, points_x, points_y, 14);
		JFrontier f15 = new JFrontier(15, points_x, points_y, 15);
		JFrontier f16 = new JFrontier(16, points_x, points_y, 16);
		JFrontier f17 = new JFrontier(17, points_x, points_y, 17);
		JFrontier f18 = new JFrontier(18, points_x, points_y, 18);
		JFrontier f19 = new JFrontier(19, points_x, points_y, 19);
		JFrontier f20 = new JFrontier(20, points_x, points_y, 20);
		JFrontier f21 = new JFrontier(21, points_x, points_y, 21);
		System.out.println(f1.toString());
		
		FrontierNode fn1 = new FrontierNode(f1);
		FrontierNode fn2 = new FrontierNode(f2);
		FrontierNode fn3 = new FrontierNode(f3);
		FrontierNode fn4 = new FrontierNode(f4);
		FrontierNode fn5 = new FrontierNode(f5);
		FrontierNode fn6 = new FrontierNode(f6);
		FrontierNode fn7 = new FrontierNode(f7);
		FrontierNode fn8 = new FrontierNode(f8);
		FrontierNode fn9 = new FrontierNode(f9);
		FrontierNode fn10 = new FrontierNode(f10);
		FrontierNode fn11 = new FrontierNode(f11);
		FrontierNode fn12 = new FrontierNode(f12);
		FrontierNode fn13 = new FrontierNode(f13);
		FrontierNode fn14 = new FrontierNode(f14);
		FrontierNode fn15 = new FrontierNode(f15);
		FrontierNode fn16 = new FrontierNode(f16);
		FrontierNode fn17 = new FrontierNode(f17);
		FrontierNode fn18 = new FrontierNode(f18);
		FrontierNode fn19 = new FrontierNode(f19);
		FrontierNode fn20 = new FrontierNode(f20);
		FrontierNode fn21 = new FrontierNode(f21);
		
		/*fn1.insert(f1, f3);
		fn1.insert(f1, f2);
		fn1.insert(f1, f10);
		fn1.insert(f1, f11);
		fn1.insert(f11, f4);
		fn1.insert(f4, f5);
		fn1.insert(f5, f6);
		fn1.insert(f6, f7);
		fn1.insert(f6, f8);
		fn1.insert(f6, f9);
		fn1.insert(f10, f12);
		fn1.insert(f10, f13);
		fn1.insert(f10, f14);
		fn1.insert(f10, f15);*/
		
		fn1.insert(f1, f2);
		fn1.insert(f1, f3);
		fn1.insert(f1, f4);
		
		fn1.insert(f3, f5);
		fn1.insert(f3, f6);
		
		fn1.insert(f4, f7);
		fn1.insert(f4, f8);
		
		fn1.insert(f5, f9);
		fn1.insert(f5, f10);
		
		fn1.insert(f7, f11);
		
		//fn1.insert(f1, f3);
		//fn1.insert(f1, f4);
		
		fn1.draw("fn");
		FrontierNode[] leafs = fn1.getLeafsAsArray();
		System.out.println("Leafs: "+leafs.length);
		
		fn1.delete(leafs[0].getContent());
		
		//fn1.mark(f4);
		
		//leafs = fn1.getLeafsAsArray();
		//System.out.println("Leafs: "+leafs.length);
		
		//fn1.draw("fn2");
		/*
		res = n.walk();
		
		System.out.println(res[0]+ ", " + res[1]+ ", " + res[2]);
		
		Content<String> n_c1 = new Content<String>(1, "Eins");
		
		Node<Content<String>> n1 = n.find(c2);
		
		res = n1.walk();
		
		System.out.println(res[0]+ ", " + res[1]+ ", " + res[2]);
		
		n.draw("output.png");
		
		n1.draw("output2.png");*/
		//initialise map;
		/*int rows = 500;
		int cols = 500;
		double[][] map = new double[rows][cols];

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				map[i][j] = 2;
			}
		}

		for(int i = rows/2-3; i < rows/2-2; i++){
			for(int j = 0; j < cols/2; j++){
				map[i][j] = 1;
			}
		}

		PathPlanner p = new PathPlanner(map, rows, cols, 1, false);
		double[][] r_map = p.planCostMap(4, 4, true);
		
		for(int a = 0; a < 100; a++){
			r_map = p.planCostMap(7, 7, true);
		}
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				//System.out.print(p.parent_map[i][j]+"\t");
			}
			//System.out.println("");
		}

		System.out.println("");

		//double[][] bnmap = p.computeBottleNeckMap();
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				//System.out.print(bnmap[i][j]+"\t");
			}
			//System.out.println("");
		}*/

		//for(int i = 0; i < rows; i++){
		//	for(int j = 0; j < cols; j++){
		//		System.out.print(p.reachability_map[i][j]+"\t");
		//	}
		//	System.out.println("");
		//}

		//int[][] path = p.generatePath(9, 9);

		//for(int i = 0; i < path.length; i++){
		//	System.out.println("("+path[i][0]+"/"+path[i][1]+")");
		//}
		/*int nf = Integer.MAX_VALUE;
		int start_i = rows/2;
		int start_j = cols/2;

		double[][] map = new double[rows][cols];
		double[][] r_map = new double[rows][cols];

		PriorityQueue<GridNode> pQ = new PriorityQueue<GridNode>();
		pQ.add(new GridNode(0, start_i * cols + start_j));

		//init
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				map[i][j] = 0;
				r_map[i][j] = nf;
			}
		}
		r_map[start_i][start_j] = 0;

		for(int i = rows/2; i < rows/2+1; i++){
			for(int j = 0; j < cols/2; j++){
				map[i][j] = 1;
			}
		}

		Planner p = new Planner(map, rows, cols);
		p.printMap();

		while(!pQ.isEmpty()){
			GridNode n = pQ.poll();
			int i = (int) n.getValue()/cols;
			int j = (int) n.getValue()%cols;

			for(int k = -1; k <= 1; k++){
				for(int l = -1; l <= 1; l++){
					int n_i = i+k;
					int n_j = j+l;
					if(n_i >= 0 && n_i < rows && n_j >= 0 && n_j < cols){
						if(k != 0 || l != 0){
							if(map[n_i][n_j] != 1.0){
								if(r_map[n_i][n_j] == nf){
									double distance = r_map[i][j] + Math.sqrt( (n_i - i)*(n_i - i) + (n_j - j)*(n_j - j) );
									double index = n_i * cols + n_j;
									System.out.println(""+i+" "+ j+ " "+index);
									pQ.add(new GridNode(distance, index));
									if(distance < r_map[n_i][n_j]){
										r_map[n_i][n_j] = distance;
									}
								}
							}
						}
					}
				}
			}
		}

		//print
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.print(+map[i][j]+" ");
			}
			System.out.println("");
		}
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				System.out.print(+r_map[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("finished");*/
	}

}

/*


for k = -1:1
for l = -1:1
    if(i+k > 0 && i+k < m+1 && j+l > 0 && j+l < n+1)
        if(k~=0 || l~=0 )
            if map(i+k, j+l) ~= 1
                if isinf(r_map(i+k, j+l))
                    new_dist = r_map(i,j) + sqrt( (i-(i+k))*(i-(i+k)) + (j-(j+l))*(j-(j+l)));
                    pQ.add(sub2ind(size(r_map), i+k, j+l));
                    %r_map(i+k, j+l) = new_dist;
                    %Q(i+k, j+l) = r_map(i+k, j+l);
                    if new_dist < r_map(i+k, j+l)
                        r_map(i+k, j+l) = new_dist;
                        %Q(i+k, j+l) = r_map(i+k, j+l);
                    end
                end*/