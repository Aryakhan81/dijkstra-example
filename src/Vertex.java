import java.util.HashSet;

public class Vertex {
	public int label = Integer.MAX_VALUE;
	public String name;
	public HashSet<Edge> edges;
	
	public Vertex() { 
		this.edges = new HashSet<>(); 
		this.name = this.toString();
	}
	
	public Vertex(String name) { 
		this.edges = new HashSet<>();
		this.name = name;
		}
	
	public Vertex(HashSet<Edge> edges) {
		this.edges = edges;
		this.name = this.toString();
	}
	
	public Vertex(Vertex v) {
		this.edges = new HashSet<>();
		this.edges.add(new Edge(this, v));
		this.name = this.toString();
	}
	
	public Vertex(Vertex v, int w) {
		this.edges = new HashSet<>();
		this.edges.add(new Edge(this, v, w));
		this.name = this.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Vertex)) return false;
		Vertex other = (Vertex) o;
		if(this.label == other.label && this.edges.equals(other.edges)) return true;
		else return false;
		
	}
	
	@Override
	public String toString() {
		if(this.name == null) return super.toString();
		else return this.name;
	}
}
