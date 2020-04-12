import java.util.*;

public class WeightedGraph {
    private HashSet<Node> allNodes = new HashSet<Node>();

    public Node addNode(final String nodeVal){
        Node newNode =  new Node(nodeVal);
        allNodes.add(newNode);
        return newNode;
    }

    public void addWeightedEdge(final Node first, final Node second, final int edgeWeight){
        first.addToweightedConnectedTo(second, edgeWeight);
        second.addToInComingEdges(first);
    }

    public void removeWeightedEdge(final Node first, final Node second){
        first.removeFromweightedConnectedTo(second);
    }

    // returns a set of allNodes in the graph
    public HashSet<Node> getAllNodes(){
        return allNodes;
    }
}
