import java.util.*;
public class TopSort {
    public ArrayList<Node> Kahns(final DirectedGraph graph){
        HashMap<Node, Integer> inDegree = KahnsGetInDegree(graph);
        ArrayList<Node> topSort = new ArrayList<Node>();
        Queue<Node> QUEUE = new LinkedList<Node>();
        addNodesToQ(inDegree, QUEUE);
        while(!QUEUE.isEmpty()){
            Node currNode = QUEUE.poll();
            topSort.add(currNode);

            for (Node neighbor : currNode.getConnectedTo()) {
                inDegree.replace(neighbor, inDegree.get(neighbor) - 1);
            }

            addNodesToQ(inDegree, QUEUE);
        }
        return topSort;
    }

    // Get the in-Degree for each node
    public HashMap KahnsGetInDegree(DirectedGraph graph){
        // initialize and populate inDegree hashMap with the current numbers of incoming edges
        HashMap<Node, Integer> inDegree = new HashMap<Node, Integer>();
        HashSet<Node> nodesInGraph = graph.getAllNodes();
        Iterator<Node> it = nodesInGraph.iterator();
        while(it.hasNext()){
            Node cN = it.next();
            inDegree.put(cN, cN.getInComingEdges().size());
        }

        return inDegree;
    }

    // This function adds nodes with in-degree 0 to the passed in queue.
    private static void addNodesToQ(HashMap<Node, Integer> inDegree, Queue<Node> q) {
        Iterator<Node> it = inDegree.keySet().iterator();
        while(it.hasNext()){
            Node cN = it.next();
            if (inDegree.get(cN) == 0) {
                q.add(cN);
                inDegree.replace(cN, inDegree.get(cN) - 1);
            }
        }
    }

    public ArrayList<Node> mDFS(final DirectedGraph graph){
        ArrayList<Node> returnList = new ArrayList<Node>();
        Stack<Node> STACK = new Stack<Node>();

        Iterator<Node> it = graph.getAllNodes().iterator();
        while(it.hasNext()){
            Node cN = it.next();
            if(!cN.getIsVisited()){
                mDFSHelper(cN, STACK);
            }
        }

        while (!STACK.empty()) {
            Node currNode = STACK.pop();
            returnList.add(currNode);
        }
        return returnList;
    }

    public static void mDFSHelper(Node node, Stack stack) {
        node.setIsVisited(true);
        for (Node neighbor : node.getConnectedTo()) {
            if (!neighbor.getIsVisited()) {
                mDFSHelper(neighbor, stack);
            }
        }

        stack.push(node);
    }
}
