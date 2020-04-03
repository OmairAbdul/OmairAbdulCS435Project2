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

    public static void main(String[] args){
         /*Graph testGraph = createLinkedList(10);
        Iterator<Node> it = testGraph.getAllNodes().iterator();
        while(it.hasNext()){
            ArrayList<Node> c = it.next().getConnectedTo();
            for(int i = 0; i<c.size();i++){
                System.out.print(c.get(i).getNodeVal());
                System.out.print(" ");
            }
            System.out.println();
        }*/
        Graph firstGraph = createRandomUnweightedGraphIter(6);
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
       /* Node fn = it.next();
        System.out.println(fn.getNodeVal());
        Node sn = it.next();
        System.out.println(sn.getNodeVal());*/
        GraphSearch firstSearch = new GraphSearch();
        System.out.println(firstSearch.BFTRec(firstGraph));
        for(Node ok : firstSearch.BFTRec(firstGraph)){
            System.out.println(ok.getNodeVal());
        }
    }
}
