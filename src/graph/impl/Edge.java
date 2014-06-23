package graph.impl;

public class Edge {
private Vertex one;
boolean treeEdge=false, backEdge=false;
public Vertex getOne() {
	return one;
}
public void setOne(Vertex one) {
	this.one = one;
}
public Vertex getTwo() {
	return two;
}
public void setTwo(Vertex two) {
	this.two = two;
}
public int getWeight() {
	return weight;
}
public void setWeight(int weight) {
	this.weight = weight;
}
public boolean isTreeEdge() {
	return treeEdge;
}
public void setTreeEdge(boolean treeEdge) {
	this.treeEdge = treeEdge;
}
public boolean isBackEdge() {
	return backEdge;
}
public void setBackEdge(boolean backEdge) {
	this.backEdge = backEdge;
}
private Vertex two ;
private int weight = 1; // default value as one

@Override
	public int hashCode() {
		// TODO Auto-generated method stub
	
		return one.getVertexNumber();
	}
@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
	Edge tempVertex = (Edge)obj;
	if(one.getVertexNumber()==tempVertex.getOne().getVertexNumber() && two.getVertexNumber() == tempVertex.getTwo().getVertexNumber())
	return true;
	else return false;
	}
private Edge() {
	// TODO Auto-generated constructor stub
	
}
public Edge(int u, int v){
	
	this.getOne().setVertexNumber(u);
	this.getTwo().setVertexNumber(v);
	
}
public Edge(int u, int v, int weight){
	one = new Vertex(u);
	two = new Vertex(v);
	this.weight = weight;
	
	
}
}
