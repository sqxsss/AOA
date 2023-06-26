package tools;

import org.jgrapht.Graph;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.util.SupplierUtil;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class Generator {

    private int maxVerticesSize = 10;

    public Generator() {
    }

    public Generator(int maxSize) {
        int randomMaxSize = this.getRandomSize(1, maxSize);
        this.setMaxVerticesSize(randomMaxSize);
    }

    public int getMaxVerticesSize() {
        return this.maxVerticesSize;
    }

    public void setMaxVerticesSize(int size) {
        this.maxVerticesSize = size;
    }

    //generate a number of vertices randomly
    public int getRandomSize(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    //generate the complete graph, the number of vertices is entered by parameter size
    protected Graph<Integer, DefaultEdge> generateCompleteGraph(boolean isWeighted) {
        Supplier<Integer> vSupplier = new Supplier<Integer>() {
            private int id = 0;
            @Override
            public Integer get() {
                return id++;
            }
        };
        Graph<Integer, DefaultEdge> completeGraph;

        if (isWeighted) {
            completeGraph = new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), true);
        } else {// no weighted, undericted
            completeGraph = new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);
        }
        // Create the CompleteGraphGenerator object
        CompleteGraphGenerator<Integer, DefaultEdge> completeGenerator = new CompleteGraphGenerator<>(this.getMaxVerticesSize());
        completeGenerator.generateGraph(completeGraph);

        return completeGraph;
    }

    public Graph<Integer, DefaultEdge> generateRandomGraph() {// generate randomly undericted graph for assignment 1
        Graph<Integer, DefaultEdge> randomGraph = this.generateCompleteGraph(false);
        this.deleteRandomEdge(randomGraph, false);
        return randomGraph;
    }

    public Graph<Integer, DefaultEdge> generateRandomSparseGraph() {// generate randomly weighted undericted graph for assignment 2
        Graph<Integer, DefaultEdge> randomGraph = this.generateCompleteGraph(true);
        this.deleteRandomEdge(randomGraph, true);
        addWeight(randomGraph);
        return randomGraph;
    }

    public Graph noEdgeGraph(int size) { // create the graph has no edges
        Supplier<Integer> vSupplier = new Supplier<Integer>() {
            private int id = 0;
            @Override
            public Integer get() {
                return id++;
            }
        };
        Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);
        for(int i =0; i< size;i++){
            graph.addVertex(i);
        }
        return graph;
    }

    // delete edges from the complete graph in order to get the apporiate graph
    private void deleteRandomEdge(Graph graph, boolean isSparse) {
        int n = graph.vertexSet().size();
        int edgeSize;
        if (isSparse) {//no more than n+8 edges, here only consider the situation that the graph is connected, so the graph must has at least n - 1 edges.
            edgeSize = getRandomSize(n * (n - 1) / 2 - (n + 8), n * (n - 1) / 2 - (n - 1));
        } else {//here only consider the situation that e<v
            edgeSize = getRandomSize(n * (n - 1) / 2 - n, n * (n - 1) / 2);
        }
        for (int i = 1; i <= edgeSize; i++) {
            if (graph.edgeSet().size() == 0) {
                break;
            }
            int edgeV1 = getRandomSize(0, n);
            int edgeV2 = getRandomSize(0, n);
            if (graph.removeEdge(edgeV1, edgeV2) == null) {
                i--;
            }
        }
    }

    public void addWeight(Graph graph) {// add weight to edges in order to get randomly weighted graph
        Object[] edges = graph.edgeSet().toArray();
        for (int i = 0; i < edges.length; i++) {
            int weight = getRandomSize(1, 100);
            graph.setEdgeWeight(edges[i], weight);
        }
    }

    static int[] known;
    static int count = 0;

    public int DFS(int[][] matrix, int i) {// depth-first search
        known[i] = 1;
        count++;
        for (int j = 0; j < matrix.length; j++) {
            if (known[j] == 0 && matrix[i][j] == 1) {
                DFS(matrix, j);
            }
        }
        return count;
    }

    public boolean isConnected(Graph graph) {// check out whether the graph is connected
        if(graph.vertexSet().size()<2){
            return false;
        }
        count = 0;
        int[][] matrix = adMatrix(graph);
        known = new int[matrix.length];
        int dfsV = DFS(matrix, 0);

        return dfsV == graph.vertexSet().size() ? true : false;
    }

    public Graph copy(Graph graph) {// copy a graph
        Graph copyG = noEdgeGraph(graph.vertexSet().size());
        Object[] edges = graph.edgeSet().toArray();
        for(int i=0;i<edges.length;i++) {
            int v1 = (int)graph.getEdgeSource(edges[i]);
            int v2 = (int)graph.getEdgeTarget(edges[i]);
            copyG.addEdge(v1,v2);
        }
        return copyG;
    }

    public int[][] adMatrix(Graph graph) { // use matrix to represent graph
        int size = graph.vertexSet().size();
        int[][] matrix = new int[size][size];
        Object[] edges = graph.edgeSet().toArray();
        for (int i = 0; i < edges.length; i++) {
            int t = (int) graph.getEdgeTarget(edges[i]);
            int s = (int) graph.getEdgeSource(edges[i]);
            matrix[t][s] = 1;
            matrix[s][t] = 1;
        }
        return matrix;
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
