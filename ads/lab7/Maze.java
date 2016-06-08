package ads.lab7;

import java.util.*;

/**
 * A class for generating a random maze using disjoint sets
 */
public class Maze {

	// the number of rows
	private int rows;
	
	// the number of columns
	private int columns;
	
	// the full grid (i.e. all the possible inner edges)
	private List<Edge> grid;
	
	// the set of inner edges of the maze
	private Set<Edge> maze;
	
	/**
	 * generates a maze of size roiws X columns
	 */
	public Maze(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		maze = new HashSet<Edge>();
		grid = new ArrayList<Edge>();
		generateMaze();
	}
	
	/**
	 * This method generates the maze by filling the set maze.
	 * The method is the one explained during lecture.
	 * The grid is the collection from which the edges are randomly
	 * picked up, so it should be shuffled using the method
	 * Collections.shuffle
	 */
	private void generateMaze() {
		DisjointSets ds = new DisjointSets(rows*columns);
		/**
		 * for (int i = 0; i < rows; i++) {
		 * for (int j = 0; j < columns; j++) {
		 * 	int n = i*columns+j;
		 * 	if (i < rows -1) grid.add(new Edge(n, n +columns));
		 * 	if (j < columns -1 ) grid.add(new Edge(n, n +1));
		 */
		// remplit grid avec tout les murs
		for (int i = 0; i < rows*columns; i++) {
			if (i%columns < columns - 1) {
				grid.add(new Edge(i, i +1));
			}
			if (i+columns < rows*columns) {
				grid.add(new Edge(i, i+columns));
			}
		}

		Collections.shuffle(grid);

		for (int i = 0; i < grid.size(); i++) {
			Edge e = grid.get(i);
			// cherche les parents dans disjoint set
			int parentX = ds.find(e.getX());
			int parentY = ds.find(e.getY());
			if (parentX != parentY) {
				ds.union(parentX, parentY);
			}
			else {
				maze.add(e);
			}
		}
	}
	
	//////////////// toString (DON'T CHANGE THIS PART)
	
	/**
	 * returns a String representation of the maze
	 */
	public String toString() {
		String s = topbot();
		for ( int i = 0; i < rows - 1; i++ )
			s += vertical(i) + horizontal(i);
		return s += vertical(rows - 1) + topbot();
	}
	
	/**
	 * returns the top-bottom line of the maze
	 */
	private String topbot() {
		String s = "+";
	    for ( int i = 0; i < columns; i++ )
	    	s += "--+";
	    return s + "\n";
	}
	
	/**
	 * returns the String representation of
	 * the vertical edges of row number 'row'
	 */
	private String vertical(int row) {
		String s = null;
		if ( row == 0 )
			s = " ";
		else
			s = "|";
		for ( int i = row*columns; i < row*columns + columns - 1; i++ )
			if ( maze.contains(new Edge(i,i+1)) )
				s += "  |";
			else
				s += "   ";
	    if (row == rows - 1)
	        s += "   \n";
	    else
	        s += "  |\n";
		return s;
	}
	
	/**
	 * returns the String representation of
	 * the horizontal edges of row number 'row'
	 */	
	private String horizontal(int row) {
		String s = "+";
		for ( int i = row*columns; i < row*columns + columns; i++ )
			if ( maze.contains(new Edge(i,i+columns)) )
				s += "--+";
			else
				s += "  +";
		return s + "\n";
	}
	
	//////////////// main method: you can try various size of maze
	
	public static void main(String args[]) {
		Maze m = new Maze(10,50);
		System.out.println(m);
	}
}
