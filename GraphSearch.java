import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
public class GraphSearch {
    /*public ArrayList<Node> DFSRec(final Node start, final Node end){
        Stack<Node> STACK = new Stack<Node>();
        ArrayList<Node> returnList = new ArrayList<Node>();
        while(!STACK.empty()){
            DFSRecHelper(STACK);
        }

    }
    public Stack<Node> DFSRecHelper(Stack<Node> inputSTACK, ArrayList<Node> inputArray){
        Node currNode = inputSTACK.pop();
    }*/
    public ArrayList<Node> DFSIter(final Node start, final Node end){
        Stack<Node> STACK = new Stack<Node>();
        ArrayList<Node> returnList = new ArrayList<Node>();
        STACK.push(start);
        while(!STACK.empty()){
            Node currNode = STACK.pop();
            if(returnList.contains(currNode)){
                continue;
            }
            if(currNode == end){
                returnList.add(currNode);
                break;
            }
            for(Node neighbor : currNode.getConnectedTo()){
                STACK.push(neighbor);
            }
            returnList.add(currNode);

        }
        if(!returnList.contains(end)){
            System.out.println("The start node can never reach the end node");
            ArrayList<Node> emptyList = new ArrayList<>();
            return emptyList;
        }
        else{
            return returnList;
        }
    }

    public ArrayList<Node> BFTRec(final Graph graph){
        ArrayList<Node> visitedArray = new ArrayList<Node>();
        ArrayList<Node> returnArray = new ArrayList<Node>();
        Iterator<Node> it = graph.getAllNodes().iterator();
        while(it.hasNext()){
            Node vertex = it.next();
            if(!visitedArray.contains(vertex)){
                visitedArray = BFTRecHelper(vertex, visitedArray);
                returnArray.add(vertex);
            }
        }
        return returnArray;
    }

    public ArrayList<Node> BFTRecHelper(Node v, ArrayList<Node> vArray){
        vArray.add(v);
        for(Node neighbor : v.getConnectedTo()){
            if(!vArray.contains(neighbor)) {
                vArray.add(neighbor);
                BFTRecHelper(neighbor, vArray);
            }
        }
        return vArray;
    }
    public ArrayList<Node> BFTIter(final Graph graph){
        Queue<Node> QUEUE = new LinkedList<Node>();
        ArrayList<Node> visitedArray = new ArrayList<Node>();
        ArrayList<Node> returnArray = new ArrayList<Node>();
        Iterator<Node> it = graph.getAllNodes().iterator();
        while(it.hasNext()){
            Node vertex = it.next();
            if(!visitedArray.contains(vertex)){
                visitedArray.add(vertex);
                QUEUE.add(vertex);
                while(!QUEUE.isEmpty()){
                    Node currNode = QUEUE.poll();
                    if(!returnArray.contains(currNode)){
                        returnArray.add(currNode);
                    }
                    for(Node neighbor : currNode.getConnectedTo()){
                        if(!visitedArray.contains(neighbor))
                            QUEUE.add(neighbor);
                            visitedArray.add(neighbor);
                        }
                    }
                }
            }
            return returnArray;
        }
}
