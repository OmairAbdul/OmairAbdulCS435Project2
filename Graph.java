import java.util.*;
public class Graph{
    // total nodes in graph
    private HashSet<Node> allNodes = new HashSet<Node>();
    // adds a new node to the graph
    public Node addNode(final String nodeVal){
        Node newNode =  new Node(nodeVal);
        allNodes.add(newNode);
        return newNode;
    }


    // adds an undirected edge betweenfirstandsecond(and vice versa)
    public void addUndirectedEdge(final Node first, final Node second){
        first.addToConnectedTo(second);
        second.addToConnectedTo(first);
    }

    // removes an undirected edge betweenfirstandsecond(and vice versa)
    public void removeUndirectedEdge(final Node first, final Node second){
        first.removeFromConnectedTo(second);
        second.removeFromConnectedTo(first);
    }

    // returns a set of allNodes in the graph
    public HashSet<Node> getAllNodes(){
        return allNodes;
    }

}

