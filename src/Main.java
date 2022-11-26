import java.util.ArrayList;
import java.util.HashSet;

public class Main {
	
	public static void main(String[] args) {
		//Build a graph via vertices and edges
		Vertex v1 = new Vertex("A");
		Vertex v2 = new Vertex("B");
		Vertex v3 = new Vertex("C");
		Vertex v4 = new Vertex("D");
		Vertex v5 = new Vertex("E");
		Vertex v6 = new Vertex("F");
		
		Edge e1 = new Edge(v1, v2, 1);
		Edge e2 = new Edge(v2, v3, 2);
		Edge e3 = new Edge(v1, v3, 4);
		Edge e4 = new Edge(v1, v4, 1);
		Edge e5 = new Edge(v4, v5, 2);
		Edge e6 = new Edge(v4, v6, 3);
		Edge e7 = new Edge(v2, v5, 1);
		
		v1.edges.add(e1);
		v1.edges.add(e3);
		v1.edges.add(e4);
		v2.edges.add(e1);
		v2.edges.add(e2);
		v2.edges.add(e7);
		v3.edges.add(e2);
		v3.edges.add(e3);
		v4.edges.add(e4);
		v4.edges.add(e5);
		v4.edges.add(e6);
		v5.edges.add(e5);
		v5.edges.add(e7);
		v6.edges.add(e6);
		
		HashSet<Vertex> vertices = new HashSet<>();
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.add(v4);
		vertices.add(v5);
		vertices.add(v6);
		
		HashSet<Edge> edges = new HashSet<>();
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		edges.add(e5);
		edges.add(e6);
		edges.add(e7);
		
		Graph g = new Graph(vertices, edges);
		int shortest_path = dijkstra(g, v3, v5);
		System.out.println(shortest_path);
		System.out.println(prim(g, v1));
	}
	
	/**Dijkstra's algorithm to find the shortest path in a weighted graph
	 * 
	 * @param g the graph in which to find the shortest path
	 * @param start the vertex in the graph to start at
	 * @param end the vertex to end at
	 * @return the length of the shortest path
	 */
	public static int dijkstra(Graph graph, Vertex start, Vertex end) {
		//Copy in a new graph to not damage the old one
		Graph g = new Graph(graph);
		//Bad argument check
		if(g == null || !g.contains(start) || !g.contains(end)) return -1;
		
		//Create an ArrayList to see what we have visited (just for funzies)
		ArrayList<String> visited = new ArrayList<>();
		
		//Set first label to 0
		start.label = 0;
		
		//Main body while loop
		while(true) {
			//Find the least vertex
			Vertex minLabel = new Vertex();
			for(Vertex v : g.vertices) {
				if(v.label < minLabel.label) minLabel = v;
			}
			
			visited.add(minLabel.name);
			if(minLabel.equals(end)) {
				System.out.println(visited);
				return minLabel.label;
			}
			
			//Search through each edge, and update the labels of adjacent vertices
			for(Edge e : minLabel.edges) {
				if(e.v1.equals(minLabel)) 
					e.v2.label = e.v2.label > minLabel.label + e.weight ? minLabel.label + e.weight : e.v2.label;
				else
					e.v1.label = e.v1.label > minLabel.label + e.weight ? minLabel.label + e.weight : e.v1.label;
			}
			
			//Remove the current vertex from the vertices left to visit
			g.remove(minLabel);
		}
	}
	
	/**
	 * Prim's algorithm to find the minimal spanning tree of a graph
	 * <br/>
	 * Note: this algorithm will find a possible MST, as it does not consider any ordering within the vertex set
	 * @param g the graph of which to find the minimal spanning tree
	 * @param start the vertex at which to start
	 * @return the minimal spanning tree of g
	 */
	public static Graph prim(Graph g, Vertex start) {
		//Bad argument check
		if(g == null || !g.contains(start)) return null;
		
		//Create the variable to hold the minimal spanning tree
		Graph mst = new Graph();
		mst.addOnly(start);
		
		//Loop for each vertex in the vertex set of g
		for(int i = 0; i < g.vertices.size(); i++) {
			Edge minEdge = new Edge(start, start, Integer.MAX_VALUE);
			
			//Loop through every vertex that we have added so far to the mst
			for(Vertex v : mst.vertices) {
				
				//Loop through each edge of each vertex in the mst
				for(Edge e : v.edges) {
					
					//Make sure it is not repeated and if it's smaller than minEdge, set minEdge to it
					if(mst.contains(e)) continue;
					if(!((mst.contains(e.v1) && !mst.contains(e.v2)) || (mst.contains(e.v2) && !mst.contains(e.v1)))) continue;
					if(e.weight < minEdge.weight) minEdge = e;
				}	
			}
			
			//Add only the smallest edge to the mst, and the vertex it connects to
			mst.addOnly(minEdge);
			if(!mst.contains(minEdge.v1)) mst.addOnly(minEdge.v1);
			else if(!mst.contains(minEdge.v2)) mst.addOnly(minEdge.v2);
		}
		
		//Clean the tree
		mst.removeOnly(new Edge(start, start, Integer.MAX_VALUE));
		for(Vertex v : mst.vertices) {
			v.edges.removeIf(e -> !(mst.contains(e)));
		}
		
		return mst;
	}
}
