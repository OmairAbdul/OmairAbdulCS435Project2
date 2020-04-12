import java.util.*;
public class mainClass {
    public static Graph createRandomUnweightedGraphIter(int n){
        Graph newGraph = new Graph();
        while(n != 0){
            int r = new Random().nextInt(n*20);
            newGraph.addNode(String.valueOf(r));
            n= n-1;
        }
        ArrayList<Node> allNodesList = new ArrayList<Node>(newGraph.getAllNodes());
        for(int i = 0; i < allNodesList.size(); i++){
            for(int j = i+1; j < allNodesList.size(); j++){
                int r = new Random().nextInt(2);
                if(r==0){
                    newGraph.addUndirectedEdge(allNodesList.get(i), allNodesList.get(j));
                }
            }
        }
        return newGraph;
    }
    public static Graph createLinkedList(int n){
        Graph newGraph = new Graph();
        int val = 1;
        Node prevNode = null;
        while(n != 0){
            Node currNode = newGraph.addNode(String.valueOf(val));
            n= n-1;
            if(prevNode!=null){
                newGraph.addUndirectedEdge(prevNode, currNode);
            }
            prevNode = currNode;
            val+=1;
        }
        return newGraph;
    }

    public static DirectedGraph createRandomDAGIter(int n){
        DirectedGraph newDAG = new DirectedGraph();
        while(n != 0){
            int r = new Random().nextInt(n*20);
            newDAG.addNode(String.valueOf(r));
            System.out.println(r);
            n= n-1;
        }
        ArrayList<Node> allNodesList = new ArrayList<Node>(newDAG.getAllNodes());
        for(int i = 0; i < allNodesList.size(); i++){
            for(int j = i+1; j < allNodesList.size(); j++){
                int r = new Random().nextInt(2);
                if(r==0){
                    newDAG.addDirectedEdge(allNodesList.get(i), allNodesList.get(j));
                }
            }
        }
        return newDAG;

    }

    public static WeightedGraph createRandomCompleteWeightedGraph(int n){
        WeightedGraph newWG = new WeightedGraph();
        while(n!=0){
            int r = new Random().nextInt(n*20);
            newWG.addNode(String.valueOf(r));
            System.out.println(r);
            n= n-1;
        }
        ArrayList<Node> allNodesList = new ArrayList<Node>(newWG.getAllNodes());
        for(int i = 0; i < allNodesList.size(); i++){
            for(int j = 0; j < allNodesList.size(); j++){
                if(allNodesList.get(i) != allNodesList.get(j)){
                    int w = new Random().nextInt(100);
                    newWG.addWeightedEdge(allNodesList.get(i), allNodesList.get(j), w);
                }
            }
        }
        return newWG;
    }

    public static WeightedGraph createWeightedLinkedList(int n){
        WeightedGraph newGraph = new WeightedGraph();
        int val = 1;
        Node prevNode = null;
        while(n != 0){
            Node currNode = newGraph.addNode(String.valueOf(val));
            n= n-1;
            if(prevNode!=null){
                newGraph.addWeightedEdge(prevNode, currNode, 1);
            }
            prevNode = currNode;
            val+=1;
        }
        return newGraph;
    }

    public static  HashMap<Node, Integer> dijkstras(final Node start){
        //Create an empty map of nodes to distances.
        HashMap<Node, Integer> nodesToDistances = new HashMap<Node, Integer>();

        //Create an array that keeps track of visited nodes
        ArrayList<Node> visitedArray = new ArrayList<Node>();

        //The start node distance equals 0
        nodesToDistances.put(start, 0);
        visitedArray.add(start);

        //Add the start nodes neighbors to the nodesToDistance HashMap
        for(Node s: start.getWeightedConnectedTo().keySet()){
            nodesToDistances.put(s, start.getWeightedConnectedTo().get(s).intValue());
        }


        boolean completionSwitch = false;
        while(!completionSwitch){

            //find the next node with the min distance
            int minValue = Integer.MAX_VALUE;
            for(Node curr: nodesToDistances.keySet()){
                    if(!visitedArray.contains(curr) && nodesToDistances.get(curr).intValue() < minValue){
                        minValue = nodesToDistances.get(curr).intValue();
                    }
            }
           Node currNode = null;
           for(Node curr: nodesToDistances.keySet()){
               if(nodesToDistances.get(curr).intValue() == minValue && !visitedArray.contains(curr)){
                   currNode = curr;
                   visitedArray.add(currNode);
                   break;
               }
           }

           if(currNode == null || currNode.getWeightedConnectedTo().isEmpty()){
               completionSwitch = true;
               break;
           }

           //Relaxation
            for(Node neighbor: currNode.getWeightedConnectedTo().keySet()){
               if(nodesToDistances.containsKey(neighbor)){
                   if(nodesToDistances.get(neighbor).intValue() > currNode.getWeightedConnectedTo().get(neighbor).intValue() + nodesToDistances.get(currNode).intValue()){
                        nodesToDistances.put(neighbor, currNode.getWeightedConnectedTo().get(neighbor).intValue() + nodesToDistances.get(currNode).intValue());
                   }
               }
               else {
                   nodesToDistances.put(neighbor, currNode.getWeightedConnectedTo().get(neighbor).intValue() + nodesToDistances.get(currNode).intValue());
               }
            }

        }
        return nodesToDistances;

    }

    public static GridGraph createRandomGridGraph(int n){
        GridGraph gg = new GridGraph();
        GridNode[][] maze = new GridNode[n+1][n+1];
        for(int row=0; row < n+1; row++){
            for(int column=0; column < n+1; column++ ){
                GridNode curr = gg.addGridNode(row, column, Integer.toString(row)+","+Integer.toString(column));
                maze[row][column] = curr;
            }
        }

        for(int row=0; row < n+1; row++) {
            for (int column = 0; column < n + 1; column++) {
                if(column<n){
                    int r = new Random().nextInt(2);
                    if(r==0){
                        gg.addUndirecteddEdge(maze[row][column], maze[row][column+1]);
                    }
                }
                if(row<n){
                    int r2 = new Random().nextInt(2);
                    if(r2==0){
                        gg.addUndirecteddEdge(maze[row][column], maze[row+1][column]);
                    }
                }
            }
        }
        return gg;
    }

    public static ArrayList<GridNode> astar(final GridNode sourceNode, final GridNode destNode){
        //Create an empty map of nodes to pairs of distances(g, h).;
        HashMap<GridNode, ArrayList<Integer>> nodesToDistances = new HashMap<GridNode, ArrayList<Integer>>();

        //Create an array that keeps track of visited nodes
        ArrayList<GridNode> visitedArray = new ArrayList<GridNode>();


        //Calculate the heuristic value for the origin h and set the distance for the origin to (0, h).
        ArrayList<Integer> originD = new ArrayList<Integer>();
        originD.add(0);
        originD.add(manhattanDistCalc(sourceNode, destNode));
        nodesToDistances.put(sourceNode, originD);
        visitedArray.add(sourceNode);

        //Add the start nodes neighbors to the nodesToDistance HashMap
        for(GridNode s: sourceNode.getConnectedTo()){
            ArrayList<Integer> currA = new ArrayList<Integer>();
            currA.add(1);
            currA.add(manhattanDistCalc(s, destNode));
            nodesToDistances.put(s, currA);
        }
        //System.out.println(nodesToDistances.get(sourceNode));
        boolean completionSwitch = false;
        while(!completionSwitch){
            //find the next node with the min distance
            int minValue = Integer.MAX_VALUE;
            for(GridNode curr: nodesToDistances.keySet()){
                if(!visitedArray.contains(curr) && (nodesToDistances.get(curr).get(0) + nodesToDistances.get(curr).get(1)) < minValue){
                    minValue = (nodesToDistances.get(curr).get(0) + nodesToDistances.get(curr).get(1));
                }
            }
            GridNode currNode = null;
            for(GridNode curr: nodesToDistances.keySet()){
                if((nodesToDistances.get(curr).get(0) + nodesToDistances.get(curr).get(1)) == minValue && !visitedArray.contains(curr)){
                    currNode = curr;
                    visitedArray.add(currNode);
                    break;
                }
            }

            if(currNode == null || currNode.getConnectedTo().isEmpty()){
                completionSwitch = true;
                break;
            }

            //Relaxation
            for(GridNode neighbor: currNode.getConnectedTo()){
                if(nodesToDistances.containsKey(neighbor)){
                    if(nodesToDistances.get(neighbor).get(0) > 1 + nodesToDistances.get(currNode).get(0)){
                        //System.out.println("helllllooooooooooodaod");
                        ArrayList<Integer> currArray = new ArrayList<Integer>();
                        currArray.add(1 + nodesToDistances.get(currNode).get(0));
                        currArray.add(manhattanDistCalc(currNode, destNode));
                        nodesToDistances.put(neighbor, currArray);
                    }
                }
                else {
                    ArrayList<Integer> currArray = new ArrayList<Integer>();
                    currArray.add(1 + nodesToDistances.get(currNode).get(0));
                    currArray.add(manhattanDistCalc(currNode, destNode));
                    nodesToDistances.put(neighbor, currArray);
                    //nodesToDistances.put(neighbor, currArray);
                }
                /*ArrayList<Integer> currArray = new ArrayList<Integer>();
                currArray.add(1 + nodesToDistances.get(currNode).get(0));
                currArray.add(manhattanDistCalc(currNode, destNode));
                nodesToDistances.put(neighbor, currArray);
                nodesToDistances.put(neighbor, currArray);*/
            }
        }
        //System.out.println(nodesToDistances);
        for(GridNode n1: nodesToDistances.keySet()){
            System.out.print(n1.getNodeVal() + " " + nodesToDistances.get(n1));
            System.out.println();
        }
        return visitedArray;
    }

    public static int manhattanDistCalc(GridNode s, GridNode d){
        int calculation = Math.abs(s.getxVal() - d.getxVal()) + Math.abs(s.getyVal() - d.getyVal());
        return calculation;
    }

    public static void main(String[] args){
        /*GridNode sn = null;
        GridNode dn = null;
        GridGraph g1 = createRandomGridGraph(5);
        Iterator<GridNode> it = g1.getAllGridNodes().iterator();
        while (it.hasNext()){
            GridNode cg = it.next();
            if(cg.getNodeVal().equals("0,0")){
                sn = cg;
            }
            if(cg.getNodeVal().equals("5,5")){
                dn = cg;
            }
        }
        System.out.println(astar(sn, dn));
        /*for(GridNode p: astar(sn, dn)){
            System.out.println(p.getNodeVal());
        }*/

        GridGraph g2 = new GridGraph();
        GridNode n1 = g2.addGridNode(0, 0, "0,0");
        GridNode n2 = g2.addGridNode(0, 1, "0,1");
        GridNode n3 = g2.addGridNode(0, 2, "0,2");
        GridNode n4 = g2.addGridNode(0, 3, "0,3");
        GridNode n5 = g2.addGridNode(1, 0, "1,0");
        GridNode n6 = g2.addGridNode(1, 1, "1,1");
        GridNode n7 = g2.addGridNode(1, 2, "1,2");
        GridNode n8 = g2.addGridNode(1, 3, "1,3");
        GridNode n9 = g2.addGridNode(2, 0, "2,0");
        GridNode n10 = g2.addGridNode(2, 1, "2,1");
        GridNode n11 = g2.addGridNode(2, 2, "2,2");
        GridNode n12 = g2.addGridNode(2, 3, "2,3");
        GridNode n13 = g2.addGridNode(3, 0, "3,0");
        GridNode n14 = g2.addGridNode(3, 1, "3,1");
        GridNode n15 = g2.addGridNode(3, 2, "3,2");
        GridNode n16 = g2.addGridNode(3, 3, "3,3");
        g2.addUndirecteddEdge(n1, n2);
        g2.addUndirecteddEdge(n1, n4);
        g2.addUndirecteddEdge(n2, n3);
        g2.addUndirecteddEdge(n4, n5);
        g2.addUndirecteddEdge(n5, n8);
        g2.addUndirecteddEdge(n8, n9);

        System.out.println(astar(n1, n9));
        for(GridNode p: astar(n1, n9)){
            System.out.println(p.getNodeVal());
        }
        /*Iterator<GridNode> it = g1.getAllGridNodes().iterator();
        while(it.hasNext()){
            GridNode gn = it.next();
            System.out.println(gn.getNodeVal());
        }*/
        /*//Dijkstra test 1
        WeightedGraph fG = new WeightedGraph();
        Node n1 = fG.addNode("1");
        Node n2 = fG.addNode("2");
        Node n3 = fG.addNode("3");
        Node n4 = fG.addNode("4");
        Node n5 = fG.addNode("5");
        Node n6 = fG.addNode("6");
        fG.addWeightedEdge(n1, n2, 2);
        fG.addWeightedEdge(n1, n3, 4);
        fG.addWeightedEdge(n2, n4, 7);
        fG.addWeightedEdge(n2, n3, 1);
        fG.addWeightedEdge(n3, n5, 3);
        fG.addWeightedEdge(n4, n6, 1);
        fG.addWeightedEdge(n5, n4, 2);
        fG.addWeightedEdge(n5, n6, 5);
        for(Node n: dijkstras(n1).keySet()){
            System.out.print(n.getNodeVal() + " " + dijkstras(n1).get(n).intValue());
            System.out.println();
        }*/


        // Dijkstra Test 2
        /*WeightedGraph fG = new WeightedGraph();
        Node n1 = fG.addNode("1");
        Node n2 = fG.addNode("2");
        Node n3 = fG.addNode("3");
        Node n4 = fG.addNode("4");
        Node n5 = fG.addNode("5");
        Node n6 = fG.addNode("6");
        fG.addWeightedEdge(n1, n2, 50);
        fG.addWeightedEdge(n1, n3, 45);
        fG.addWeightedEdge(n1, n4, 10);
        fG.addWeightedEdge(n2, n4, 15);
        fG.addWeightedEdge(n2, n3, 10);
        fG.addWeightedEdge(n3, n5, 30);
        fG.addWeightedEdge(n4, n1, 1);
        fG.addWeightedEdge(n4, n5, 15);
        fG.addWeightedEdge(n5, n2, 20);
        fG.addWeightedEdge(n5, n3, 35);
        for(Node n: dijkstras(n1).keySet()){
            System.out.print(n.getNodeVal() + " " + dijkstras(n1).get(n).intValue());
            System.out.println();
        }*/

        // Dijkstra Test 3
        /*WeightedGraph firstGraph = createRandomCompleteWeightedGraph(3);
        Iterator<Node> it = firstGraph.getAllNodes().iterator();
        Iterator<Node> itD = firstGraph.getAllNodes().iterator();
        while(it.hasNext()){
            Node cN = it.next();
            System.out.print(cN+" ");
            System.out.print(cN.getNodeVal()+" ");
            Iterator<Node> itH = cN.getWeightedConnectedTo().keySet().iterator();
            while(itH.hasNext()){
                Node cH = itH.next();
                System.out.println("( " + cH.getNodeVal() + " " +cN.getWeightedConnectedTo().get(cH)+") ");
            }
            System.out.println();
        }
        Node next = itD.next();
        System.out.println(next);
        System.out.println(dijkstras(next));*/

        //mDFS and Khans Test
        /*DirectedGraph firstGraph = createRandomDAGIter(5);
        Iterator<Node> it = firstGraph.getAllNodes().iterator();
        while(it.hasNext()){
            Node cN = it.next();
            System.out.print(cN+" ");
            System.out.print(cN.getNodeVal()+" ");
            for(Node every : cN.getConnectedTo() ){
                System.out.print(every.getNodeVal()+" ");
            }
            System.out.println();
        }
        TopSort firstSearch = new TopSort();
        //System.out.println(firstSearch.KahnsGetInDegree(firstGraph));
        System.out.println(firstSearch.mDFS(firstGraph));*/


        //Part1 Testing
        /*
        Graph firstGraph = createRandomUnweightedGraphIter(5);
        Iterator<Node> it = firstGraph.getAllNodes().iterator();
        Iterator<Node> it2 = firstGraph.getAllNodes().iterator();
        while(it2.hasNext()){
            Node cN = it2.next();
            System.out.print(cN+" ");
            System.out.print(cN.getNodeVal()+" ");
            for(Node every : cN.getConnectedTo() ){
                System.out.print(every.getNodeVal()+" ");
            }
            System.out.println();
        }
        System.out.println();
        Node fn = it.next();
        System.out.println(fn.getNodeVal());
        Node sn = it.next();
        System.out.println(sn.getNodeVal());
        GraphSearch firstSearch = new GraphSearch();
        System.out.println(firstSearch.DFSIter(fn, sn));
        for(Node ok : firstSearch.DFSIter(fn, sn)){
            System.out.println(ok.getNodeVal());
        }
        System.out.println("__________________________________________");
        GraphSearch secondSearch = new GraphSearch();
        System.out.println(secondSearch.DFSRec(fn, sn));
        for(Node ok : secondSearch.DFSRec(fn, sn)){
            System.out.println(ok.getNodeVal());
        }*/
    }
}
