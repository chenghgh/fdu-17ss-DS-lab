public class Edge {
    private Vertex vert1,vert2;
    public int weight;
    public String line;

    public Edge (Vertex vt1, Vertex vt2,String name,int weight) {
        line = name;vert1 = vt1; vert2 = vt2; this.weight = weight;
    }

    public Vertex getOther(Vertex a) {
        if(a == vert1)
            return vert2;
        else if(a == vert2)
            return vert1;
        else return null;
    }

}
