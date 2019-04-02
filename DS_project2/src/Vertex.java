import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Vertex implements Comparable<Vertex>{
    public String name;
    public List<Edge> adjEdges = new ArrayList<>();//顶点的邻接边
    public List<Vertex> adjVertex = new ArrayList<>();
    public List<Vertex> preNode = new ArrayList<>();
    public int dist;//到源点的最短距离


    public Vertex(String name){
        this.name = name;
        adjEdges = new LinkedList<Edge>();
        dist = Integer.MAX_VALUE;
        preNode = null;
    }

    public Edge getEdge(Vertex v1){
            for(Edge edge : adjEdges){
            if(edge.getOther(this) == v1)
                return edge;
        }return null;
    }


    @Override
    public int compareTo(Vertex o) {
        if(this.dist > o.dist)
            return 1;
        else if(this.dist < o.dist)
            return -1;
        else return 0;
    }

}
