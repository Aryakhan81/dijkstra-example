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
		
		v1.edges.add(e1);
		v1.edges.add(e3);
		v1.edges.add(e4);
		v2.edges.add(e1);
		v2.edges.add(e2);
		v3.edges.add(e2);
		v3.edges.add(e3);
		v4.edges.add(e4);
		v4.edges.add(e5);
		v4.edges.add(e6);
		v5.edges.add(e5);
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
		
		Graph g = new Graph(vertices, edges);
		int shortest_path = dijkstra(g, v3, v5);
		System.out.println(shortest_path);
	}
	
	public static int dijkstra(Graph g, Vertex start, Vertex end) {
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
}
