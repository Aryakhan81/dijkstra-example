public class Edge {
	public Vertex v1;
	public Vertex v2;
	public int weight;
	
	public Edge(Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = 0;
	}
	
	public Edge(Vertex v1, Vertex v2, int w) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = w;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Edge)) return false;
		Edge other = (Edge) o;
		if(this.weight == other.weight && this.v1.equals(other.v1) && this.v2.equals(other.v2)) return true;
		else return false;
	}
	
	@Override
	public String toString() {
		return "(" + this.v1.toString() + ", " + this.v2.toString() + ")";
	}
}
