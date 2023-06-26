package Assignment1;
import org.jgrapht.Graph;

public class CycleFinding {

    public boolean ifExistCycle(Graph graph) {
        if(graph.edgeSet().size()>=graph.vertexSet().size()) {
            return true;
        } else{
            for(int i=0;i<graph.vertexSet().size();i++){
                if(graph.degreeOf((int)graph.vertexSet().toArray()[i])<=1) {
                    graph.removeVertex((int)graph.vertexSet().toArray()[i]);
                    i = -1;
                }
            }
            if(graph.vertexSet().size()>0) {
                return true;
            }
        }
        return false;
    }
}
