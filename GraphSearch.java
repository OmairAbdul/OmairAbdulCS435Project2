import java.lang.reflect.Array;
import java.util.*;
public class GraphSearch {
    public ArrayList<Node> DFSRec(final Node start, final Node end){
        Stack<Node> STACK = new Stack<Node>();
        ArrayList<Node> returnList = new ArrayList<Node>();
        STACK.push(start);
        returnList = DFSRecHelper(end, STACK, returnList);
        if(!returnList.contains(end)){
            System.out.println("The start node can never reach the end node");
            ArrayList<Node> emptyList = new ArrayList<>();
            return emptyList;
        }
        else{
            return returnList;
        }
    }
    public ArrayList<Node> DFSRecHelper(Node end, Stack<Node> s, ArrayList<Node> rA){
        if(s.empty()){
            return rA;
        }
        Node currNode = s.pop();
        if(rA.contains(currNode)){
            DFSRecHelper(end, s , rA);
        }
        else if(currNode == end){
            rA.add(currNode);
            return rA;
        }
        else{
            for(Node neighbor : currNode.getConnectedTo()){
                s.push(neighbor);
            }
            rA.add(currNode);
            DFSRecHelper(end, s , rA);
        }
        return rA;

    }

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

    public ArrayList<Node> BFTRec(Graph graph){
        ArrayList<Node> returnArray = new ArrayList<Node>();
        Iterator<Node> itSetVisited = graph.getAllNodes().iterator();
        while(itSetVisited.hasNext()){
            itSetVisited.next().setIsVisited(false);
        }
        Iterator<Node> it = graph.getAllNodes().iterator();
        Queue<Node> QUEUE = new LinkedList<Node>();
        while(it.hasNext()){
            Node vertex = it.next();
            if(!vertex.getIsVisited()){
                vertex.setIsVisited(true);
                QUEUE.add(vertex);
                returnArray = BFTRecHelper(graph, QUEUE, returnArray);
            }
        }
        return returnArray;

    }
    public ArrayList<Node> BFTRecHelper(Graph g, Queue<Node> q, ArrayList<Node> rA){
        if(q.isEmpty()){
            return rA;
        }
        Node v = q.poll();
        if(!rA.contains(v)){
            rA.add(v);
        }
        for(Node neighbor : v.getConnectedTo()){
            if(!neighbor.getIsVisited()) {
                neighbor.setIsVisited(true);
                q.add(neighbor);
            }
        }
        BFTRecHelper(g, q, rA);
        return rA;
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
