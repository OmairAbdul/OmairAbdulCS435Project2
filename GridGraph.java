import java.util.*;
public class GridGraph {
    private HashSet<GridNode> allGridNodes = new HashSet<GridNode>();

    public GridNode addGridNode(final int x, final int y, final String nodeVal){
        GridNode newNode =  new GridNode(x, y, nodeVal);
        allGridNodes.add(newNode);
        return newNode;
    }
    public void addUndirecteddEdge(final GridNode first, final GridNode second){
        first.addToConnectedTo(second);
        second.addToConnectedTo(first);
    }

    public void removeUndirectededEdge(final GridNode first, final GridNode second){
        first.removeFromConnectedTo(second);
        second.removeFromConnectedTo(first);
    }

    // returns a set of allGridNodes in the graph
    public HashSet<GridNode> getAllGridNodes(){
        return allGridNodes;
    }
}
