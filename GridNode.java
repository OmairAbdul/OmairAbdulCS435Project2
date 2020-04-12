import java.util.ArrayList;

public class GridNode {

    private int xVal;
    private int yVal;
    private String nodeVal;
    private ArrayList<GridNode> connectedTo = new ArrayList<GridNode>();

    public GridNode(int x, int y, String nV){
        xVal=x;
        yVal=y;
        nodeVal = nV;
    }

    public int getxVal() {
        return xVal;
    }

    public void setxVal(int xVal) {
        this.xVal = xVal;
    }

    public int getyVal() {
        return yVal;
    }

    public void setyVal(int yVal) {
        this.yVal = yVal;
    }

    public String getNodeVal() {
        return nodeVal;
    }

    public void setNodeVal(String nodeVal) {
        this.nodeVal = nodeVal;
    }

    public ArrayList<GridNode> getConnectedTo(){
        return connectedTo;
    }

    public void addToConnectedTo(GridNode A){
        connectedTo.add(A);
    }

    public void removeFromConnectedTo(GridNode A){
        connectedTo.remove(A);
    }
}
