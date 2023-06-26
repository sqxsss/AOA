package Assignment2;

import org.jgrapht.Graph;
import org.junit.jupiter.api.Test;
import tools.Generator;

import static org.junit.jupiter.api.Assertions.*;

class MSTTest {
    MST mst = new MST();

    public void printWeightedGraph(Graph graph){
        Object[] edges = graph.edgeSet().toArray();
        System.out.println(graph.vertexSet().size());
        for(int i=0; i< edges.length;i++ ){
            System.out.print(graph.getEdgeSource(edges[i])+",");
            System.out.print(graph.getEdgeTarget(edges[i])+"---");
            System.out.println(graph.getEdgeWeight(edges[i]));
        }
        System.out.println("-----------");
    }

    @Test
    void mstTest1() {
        Generator generator = new Generator(10);
        Graph graph = generator.generateRandomSparseGraph();
        printWeightedGraph(graph);
        mst.MSTforSparse(graph);
    }

    @Test
    void mstTest2() {
        Generator generator = new Generator(20);
        Graph graph = generator.generateRandomSparseGraph();
        printWeightedGraph(graph);
        mst.MSTforSparse(graph);
    }

    @Test
    void mstTest3() {
        Generator generator = new Generator(30);
        Graph graph = generator.generateRandomSparseGraph();
        printWeightedGraph(graph);
        mst.MSTforSparse(graph);
    }

    @Test
    void mstTest4() {
        Generator generator = new Generator(40);
        Graph graph = generator.generateRandomSparseGraph();
        printWeightedGraph(graph);
        mst.MSTforSparse(graph);
    }

    @Test
    void mstTest5() {
        Generator generator = new Generator(50);
        Graph graph = generator.generateRandomSparseGraph();
        printWeightedGraph(graph);
        mst.MSTforSparse(graph);
    }
}