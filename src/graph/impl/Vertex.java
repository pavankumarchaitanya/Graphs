package graph.impl;

public class Vertex {
private Color VertexColor;
private boolean Visited;
private int vertexNumber;
private int parent; 
private int distance;
private int vertexDiscoveryTime;
private int vertexDepartureTime;
public int getParent() {
	return parent;
}

public int getVertexDiscoveryTime() {
	return vertexDiscoveryTime;
}

public void setVertexDiscoveryTime(int vertexDiscoveryTime) {
	this.vertexDiscoveryTime = vertexDiscoveryTime;
}

public int getVertexDepartureTime() {
	return vertexDepartureTime;
}

public void setVertexDepartureTime(int vertexDepartureTime) {
	this.vertexDepartureTime = vertexDepartureTime;
}

public void setParent(int parent) {
	this.parent = parent;
}

public boolean isVisited() {
	return Visited;
}

public void setVisited(boolean visited) {
	Visited = visited;
}

public Color getVertexColor() {
	return VertexColor;
}

public void setVertexColor(Color vertexColor) {
	VertexColor = vertexColor;
}

public int getVertexNumber() {
	return vertexNumber;
}

public void setVertexNumber(int vertexNumber) {
	this.vertexNumber = vertexNumber;
}
@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
	Vertex tempVertex = (Vertex) obj;
	
		return this.vertexNumber ==tempVertex.getVertexNumber();
	}
@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.vertexNumber;
	}
public Vertex(int u)
{
this.vertexNumber = u;	
}

public int getDistance() {
	return distance;
}

public void setDistance(int distance) {
	this.distance = distance;
}

}
