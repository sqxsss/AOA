package Assignment2;

import Assignment1.CycleFinding;
import org.jgrapht.Graph;
import tools.Generator;

public class MST {

    public Object[] sortByWeight(Graph graph) {
        Object[] edges = graph.edgeSet().toArray();
        for(int i=1; i<edges.length; i++){
            for(int j=i; j>0; j--){
                if(graph.getEdgeWeight(edges[j])<graph.getEdgeWeight(edges[j-1])){
                    Object temp = edges[j-1];
                    edges[j-1] = edges[j];
                    edges[j] = temp;
                }
            }
        }
        return edges;
    }

    public boolean MSTforSparse(Graph graph){
        CycleFinding cycleFinding = new CycleFinding();
        Generator generator = new Generator();
        Object[] sortedEdges = sortByWeight(graph);
        Graph newG = generator.noEdgeGraph(graph.vertexSet().size());

        if(generator.isConnected(graph)){
            for(int i = 0; i<sortedEdges.length;i++){
                if(newG.edgeSet().size()==newG.vertexSet().size()-1){
                    break;
                }
                int v1 = (int)graph.getEdgeSource(sortedEdges[i]);
                int v2 = (int)graph.getEdgeTarget(sortedEdges[i]);
                newG.addEdge(v1,v2);

                Graph temp = generator.copy(newG);
                if(cycleFinding.ifExistCycle(temp)){
                    newG.removeEdge(v1,v2);
                    newG.addVertex(v1);
                    newG.addVertex(v2);
                }else{
                    System.out.println(v1 + ", " + v2);
                }
            }
            return true;
        }else {
            System.out.println("not connected");
            return false;
        }
    }
}
