import java.util.ArrayList;
import java.util.HashSet;

public class Graph {
    private ArrayList<GNode> nodes;
    public Graph(){
        nodes = new ArrayList<>();
    }

    public void addNode(GNode node){
        nodes.add(node);
    }

    public ArrayList<GNode> getNodes(){
        return nodes;
    }
}
