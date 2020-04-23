import java.util.*;
public class DirectedGraph {
    private HashSet<Node> allNodes = new HashSet<Node>();

    public void addNode(final String nodeVal){
        Node newNode =  new Node(nodeVal);
        allNodes.add(newNode);
    }

    public void addDirectedEdge(final Node first, final Node second){
        first.addToConnectedTo(second);
        second.addToInComingEdges(first);
    }

    public void removeDirectedEdge(final Node first, final Node second){
        if(first.getConnectedTo().contains(second)){
            first.removeFromConnectedTo(second);
        }
    }

    // returns a set of allNodes in the graph
    public HashSet<Node> getAllNodes(){
        return allNodes;
    }
}
