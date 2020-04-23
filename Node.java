import java.util.ArrayList;
import java.util.HashMap;
public class Node{

    private String nodeVal;
    private ArrayList<Node> connectedTo = new ArrayList<Node>();
    private HashMap<Node, Integer> weightedConnectedTo = new HashMap<Node, Integer>();
    private boolean isVisited;
    private ArrayList<Node> inComingEdges = new ArrayList<Node>();

    public Node(String nV){
        nodeVal = nV;
    }


    public ArrayList<Node> getInComingEdges() {
        return inComingEdges;
    }

    public void addToInComingEdges(Node A){
        inComingEdges.add(A);
    }

    public boolean getIsVisited(){
        return isVisited;
    }

    public void setIsVisited(boolean newIsVisited){
        isVisited = newIsVisited;
    }
    public String getNodeVal(){
        return nodeVal;
    }

    public void setNodeVal(String nV){
        nodeVal=nV;
    }

    public ArrayList<Node> getConnectedTo(){
        return connectedTo;
    }

    public void addToConnectedTo(Node A){
        connectedTo.add(A);
    }

    public void removeFromConnectedTo(Node A){
        connectedTo.remove(A);
    }

    public HashMap<Node, Integer> getWeightedConnectedTo(){
        return weightedConnectedTo;
    }

    public void addToweightedConnectedTo(Node A, int weight){
        weightedConnectedTo.put(A, weight);
    }

    public void removeFromweightedConnectedTo(Node A){
        weightedConnectedTo.remove(A);
    }

}
