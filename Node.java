import java.util.ArrayList;
public class Node{

    private String nodeVal;
    private ArrayList<Node> connectedTo = new ArrayList<Node>();

    public Node(String nV){
        nodeVal = nV;
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

}
