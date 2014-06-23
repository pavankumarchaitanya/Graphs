package graph.impl;

import graph.exceptions.PathNotFoundException;
import graph.exceptions.VertexNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import junit.framework.Assert;

public class Graph {
	//In a graph the vertices start from 1
	private HashSet<Edge> EdgeSet = new HashSet<Edge>();
	private HashMap<Integer,Vertex> VertexMap = new HashMap<Integer,Vertex>();
	private static int time = 0;
	public HashSet<Edge> getEdgeSet() {
		return new HashSet<Edge>(EdgeSet);
	}

	public boolean hasEdge(int u, int v) {
		// TODO Auto-generated method stub
		Edge tempEdge = new Edge(u,v);
		if(tempEdge!=null)
			return EdgeSet.contains(tempEdge);
		return false;
	}

	public boolean hasVertex(int u)
	{
		Vertex tempVertex = VertexMap.get(u);
		if(tempVertex!=null)
			return true;
		else
			return false;
	}

	public boolean isVertexVisited(int u)
	{
		Vertex tempVertex = VertexMap.get(u);
		if(tempVertex!=null)
			return (tempVertex.isVisited());
		else return false;
	}

	public void setVertexVisited(boolean value, int u)
	{ //test this method
		Vertex tempVertex = VertexMap.get(u);
		if(tempVertex!=null)
			tempVertex.setVisited(value);
		else 
			throw new VertexNotFoundException();

	}

	public boolean addVertexToGraph(int u)
	{
		Vertex tempVertex = VertexMap.get(u);
		if(tempVertex==null)
		{
			VertexMap.put(u, new Vertex(u));
			return true;

		}
		else return false;
	}

	public void setEdgesFromAdjacencyMatrix(int[][] Adj)
	{
		for(int i=0;i<Adj.length; i++)
		{
			if(Adj.length!=Adj[i].length){
				throw new RuntimeException("Adjacency Matrix is not well formed");
			}
		}


		VertexMap = new HashMap<Integer,Vertex>(); //clear the vertex map
		EdgeSet = new HashSet<Edge>(); //clear the edge set
		for( int i = 0; i< Adj.length; i ++ )
		{
			VertexMap.put(i+1, new Vertex(i+1)); // add vertices to the graph

			for(int j=0;j< Adj[i].length; j++)
			{
				if(Adj[i][j] !=0)
					this.addEdgeToGraph(new Edge(i+1,j+1,Adj[i][j])); //add edges to the graph, assign the weight
			}

		}

		this.equals(this);
	}

	private void addEdgeToGraph(Edge E)
	{
		EdgeSet.add(E);
	}
	public void removeEdgeFromGraph(int u, int v)
	{
		EdgeSet.remove(new Edge(u, v));
	}

	public int[][] getEdgesAsAdjacencyMatrix()
	{
		int [][]Adj = new int[VertexMap.size()][VertexMap.size()];
		Edge edgeObj = null;
		if (EdgeSet.size()!=0 ){

			Iterator<Edge> i = EdgeSet.iterator();
			while(i.hasNext())
			{
				edgeObj = i.next();
				Adj[edgeObj.getOne().getVertexNumber()-1][edgeObj.getTwo().getVertexNumber()-1] = edgeObj.getWeight();

			}
		}
		return Adj;
	}

	public void printAdjacencyMatrix()
	{

		int [][] Adj = getEdgesAsAdjacencyMatrix();
		System.out.println("Adjacency Matrix is: ");
		for(int i=0;i<Adj.length;i++)
		{
			for (int j=0;j<Adj[1].length;j++ )
			{
				System.out.print(" " + Adj[i][j]); 

			}
			System.out.println();

		}

	}
	public ArrayList<Vertex> getNeighbouringUnvisitedVerticesList(int u)
	{
		//for u=5 the row and column numbers would be 4 ie.., u-1	

		ArrayList<Vertex> vertexArrayObj = new ArrayList<Vertex>();
		int [][] Adj = getEdgesAsAdjacencyMatrix();

		for (int i = 0 ; i<Adj[u-1].length; i++)
		{
			if (Adj[u-1][i] > 0 && VertexMap.get(i+1).isVisited() == false )
			{
				vertexArrayObj.add(VertexMap.get(i+1));	
			}

		}


		return vertexArrayObj;
	}

	public void BFS(int startVertex)
	{
		Iterator<Integer> i = VertexMap.keySet().iterator();
		Vertex tempVertex = null;
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		while(i.hasNext())
		{
			tempVertex = VertexMap.get(i.next());
			tempVertex.setVertexColor(Color.White);
			tempVertex.setParent(-1);
			tempVertex.setDistance(0);
			tempVertex.setVisited(false);

		}
		Vertex startVertexObj = VertexMap.get(startVertex);
		startVertexObj.setParent(-1);
		startVertexObj.setVertexColor(Color.Grey);
		startVertexObj.setVisited(true);
		startVertexObj.setDistance(0);
		queue.addFirst(startVertexObj);

		while(!queue.isEmpty()){
			tempVertex = null;
			Vertex  u = queue.removeLast();
			ArrayList<Vertex> vertexArray = getNeighbouringUnvisitedVerticesList(u.getVertexNumber());	
			for(int j = 0 ; j< vertexArray.size(); j++)
			{

				tempVertex = vertexArray.get(j);
				if(tempVertex.getVertexColor()==Color.White)
				{	
					tempVertex.setVertexColor(Color.Grey);
					tempVertex.setParent(u.getVertexNumber());
					tempVertex.setVisited(true);
					tempVertex.setDistance(u.getDistance()+1);
					queue.addFirst(tempVertex);
				}
			}
		}



	}

	public void printShortestPath(int startIndex, int endIndex) {
		// TODO Auto-generated method stub

		BFS(startIndex);
		System.out.println("path from " + startIndex +"to " + endIndex);
		//	 printPath(startIndex,endIndex);


		Stack<Integer> stackObj = new Stack<Integer>();
		Vertex tempVertex = VertexMap.get(endIndex);
		while( tempVertex.getParent()!=-1 ){
			stackObj.push(tempVertex.getVertexNumber());
			tempVertex = VertexMap.get(tempVertex.getParent());
			if (tempVertex.getVertexNumber()==startIndex)
				break;
		}
		if (tempVertex.getVertexNumber()==startIndex)
		{
			stackObj.add(startIndex);
			System.out.println("Path is : ");
			while (!stackObj.isEmpty()){
				System.out.print("->" + stackObj.pop() );	
			}



		}else throw new PathNotFoundException();
	}
	private void printPath(int startIndex, int endIndex){
		if(VertexMap.get(endIndex).getParent()!=-1 )
		{
			if (VertexMap.get(endIndex).getParent()==startIndex)
				return;

			printPath(startIndex,VertexMap.get(endIndex).getParent() );
			
			System.out.println(+endIndex);

		}
		else 
			throw new PathNotFoundException();

	}
	
	public void DFS(Vertex v)
	{
		
		v.setVertexDiscoveryTime(time);
		v.setVisited(true);
		for (Vertex u : getNeighbouringUnvisitedVerticesList(v.getVertexNumber())) {
			time++;
			getEdge(v.getVertexNumber(), u.getVertexNumber()).setTreeEdge(true);
			
			DFS(u); // actual code :P
			
			
			
			
		}
		
		v.setVertexDepartureTime(++time);
		
	}
/*
 * @return : Edge if u,v is present in E else returns a null
 */
	public Edge getEdge(int u, int v)
	{
		
		if (EdgeSet.contains(new Edge(u, v)))
		{
			Iterator<Edge> iterObj = EdgeSet.iterator();
			Edge tempEdgeObj = null;
			while (iterObj.hasNext())
			{
				tempEdgeObj = iterObj.next();
				if(tempEdgeObj.getOne().getVertexNumber()==u && tempEdgeObj.getTwo().getVertexNumber()==v)
					return tempEdgeObj;
			}
		}
		
		return null;
	}
}
