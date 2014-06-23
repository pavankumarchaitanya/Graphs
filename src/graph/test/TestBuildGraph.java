package graph.test;

import static org.junit.Assert.*;
import graph.impl.Graph;
import graph.impl.Vertex;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


public class TestBuildGraph {
	final int GRAPH_SIZE = 20;
	Graph g= new Graph();
	

	
	@Before
	public void setUp() throws Exception {
		for(int i = 1 ; i <= GRAPH_SIZE; i++)
		{
			g.addVertexToGraph(i);
			g.setVertexVisited((i%2==0?true:false), i);
		}
		
	}
	
	@Test
public void testVerticesVisitCode()
{
		for(int i=1;i<=GRAPH_SIZE;i++)
		{
			if(g.isVertexVisited(i))
				System.out.println("Vertex " + i + " has been visited");
			else
				System.out.println("Vertex " + i + " has not been visited");
			
		}
}
	
	@Test
	public void testAddEdgesToGraph()
	{
		int[][] Adj = { {0,1,0,0,1}, 
						{1,0,1,1,1},
						{0,1,0,1,1},
						{0,1,1,0,1},
						{1,1,1,1,1}
					  };
	g.setEdgesFromAdjacencyMatrix(Adj);
		
 Adj = g.getEdgesAsAdjacencyMatrix();
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
	@Test
	public void testListOfNeighbouringVertices()
	{
		int u = 5;
		int[][] Adj = { {0,1,0,0,1}, 
				{1,0,1,1,1},
				{0,1,0,1,1},
				{0,1,1,0,1},
				{1,1,1,1,1}
			  };
g.setEdgesFromAdjacencyMatrix(Adj);
g.setVertexVisited(true, 5);
		ArrayList<Vertex> vertexListObj = g.getNeighbouringUnvisitedVerticesList(u);
		
		Iterator<Vertex> iterator= vertexListObj.iterator();
		while(iterator.hasNext())
		{
		System.out.println("List of vertices next to vertex number: " + u );
		System.out.println(iterator.next().getVertexNumber());
		}
		
	}
	
	
	@Test
	public void testBFSPrintPath()
	{
		int u = 5;
		int[][] Adj = { {0,1,0,0,1}, 
				{1,0,1,1,1},
				{0,1,0,1,1},
				{0,1,1,0,1},
				{1,1,1,1,1}
			  };
g.setEdgesFromAdjacencyMatrix(Adj);
g.printShortestPath(1, 5);
	}
}
