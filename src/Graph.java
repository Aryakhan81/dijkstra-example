import java.util.HashSet;

public class Graph {
	public HashSet<Vertex> vertices;
	public HashSet<Edge> edges;
	
	public Graph(HashSet<Vertex> v, HashSet<Edge> e) {
		this.vertices = v;
		this.edges = e;
	}
	
	public boolean contains(Vertex v) { return this.vertices.contains(v); }
	
	public boolean contains(Edge e) { return this.edges.contains(e); }
	
	public void remove(Vertex v) { 
		this.vertices.remove(v);
		this.edges.removeIf(e -> (e.v1.equals(v) || e.v2.equals(v)));
	}
	
}
