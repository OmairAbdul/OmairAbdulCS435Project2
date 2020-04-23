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
    public ArrayList<Node> DFSRecHelper(Node end, Stack<Node> s, ArrayList<Node> returnArray){
        if(s.empty()){
            return returnArray;
        }
        Node currNode = s.pop();
        if(returnArray.contains(currNode)){
            DFSRecHelper(end, s , returnArray);
        }
        else if(currNode == end){
            returnArray.add(currNode);
            return returnArray;
        }
        else{
            for(Node neighbor : currNode.getConnectedTo()){
                if(!s.contains(neighbor)){
                    s.push(neighbor);
                }

            }
            returnArray.add(currNode);
            DFSRecHelper(end, s , returnArray);
        }
        return returnArray;

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
                return returnList;
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
        return returnList;
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
    public ArrayList<Node> BFTRecHelper(Graph g, Queue<Node> q, ArrayList<Node> returnArray){
        if(q.isEmpty()){
            return returnArray;
        }
        Node currNode = q.poll();
        if(!returnArray.contains(currNode)){
            returnArray.add(currNode);
        }
        for(Node neighbor : currNode.getConnectedTo()){
            if(!neighbor.getIsVisited()) {
                neighbor.setIsVisited(true);
                q.add(neighbor);
            }
        }
        BFTRecHelper(g, q, returnArray);
        return returnArray;
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
