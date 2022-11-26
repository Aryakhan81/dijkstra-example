import java.util.HashSet;

public class Graph {
	public HashSet<Vertex> vertices;
	public HashSet<Edge> edges;
	
	public Graph() {
		this.vertices = new HashSet<Vertex>();
		this.edges = new HashSet<Edge>();
	}
	
	public Graph(HashSet<Vertex> v, HashSet<Edge> e) {
		this.vertices = v;
		this.edges = e;
	}
	
	public Graph(Graph g) {
		this.vertices = new HashSet<Vertex>(g.vertices);
		this.edges = new HashSet<Edge>(g.edges);
	}
	
	public boolean contains(Vertex v) { return this.vertices.contains(v); }
	
	public boolean contains(Edge e) { return this.edges.contains(e); }
	
	public void remove(Vertex v) { 
		this.vertices.remove(v);
		this.edges.removeIf(e -> (e.v1.equals(v) || e.v2.equals(v)));
	}
	
	public void removeOnly(Edge e) { this.edges.removeIf(edge -> (edge.equals(e))); }
	
	public void add(Vertex v) {
		this.vertices.add(v);
		this.edges.addAll(v.edges);
	}
	
	public void addOnly(Vertex v) { this.vertices.add(v); }
	
	public void addOnly(Edge e) { this.edges.add(e); }
		
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Graph)) return false;
		Graph other = (Graph) o;
		if(this.edges.equals(other.edges) && this.vertices.equals(other.vertices)) return true;
		else return false;
	}
	
	@Override
	public String toString() {
		return "Edges: " + this.edges.toString() + ", Vertices: " + this.vertices.toString();
	}
	
}
