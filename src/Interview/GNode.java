package Interview;

import java.util.ArrayList;

public class GNode {

    boolean visited;
    int val;
    ArrayList<GNode> inEdge;
    ArrayList<GNode> outEdge;
    ArrayList<GNode> neighbors;


    public GNode(int data){
        visited = false;
        val = data;
        inEdge = new ArrayList<>();
        outEdge = new ArrayList<>();
        neighbors = new ArrayList<>();
    }
}
