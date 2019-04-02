import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dijkstra {
    private HashMap<String,Vertex> map = new HashMap<>();
    public Vertex midVertex = new Vertex("中转站");;
    public ArrayList<ArrayList<Vertex>> paths;

    public HashMap<Integer,String> endString = new HashMap<>();


    /*寻找最短路径*/
    void dijkstra(String s1,String s2){
        Vertex v1 = map.get(s1);//start
        Vertex v2 = map.get(s2);//end

        MinHeap heap = new MinHeap();
        for(Vertex v : map.values()){
            v.dist = Integer.MAX_VALUE;
            v.preNode = null;
        }
        v1.dist = 0;
        init(heap);

        while(!heap.isEmpty()){
            Vertex v = heap.deleteMin();
            if(v == v2){
                break;
            }
            List<Edge> adjEdges = v.adjEdges;
            for(Edge e : adjEdges){
                Vertex adjNode = e.getOther(v);
                if(v.dist != Integer.MAX_VALUE && adjNode.dist >= e.weight + v.dist){
                    adjNode.dist = e.weight + v.dist;
                    if(adjNode.preNode == null){
                        adjNode.preNode = new ArrayList<>();
                    }
                    if(adjNode.preNode.size() == 0 || adjNode.dist == adjNode.preNode.get(0).dist + adjNode.getEdge(adjNode.preNode.get(0)).weight){
                            adjNode.preNode.add(v);
                    }else {
                        adjNode.preNode.set(0,v);
                    }
                }
            }
            heap.buildHeap();
        }
        printPath(v1,v2);
    }

    /*初始化*/
    void init(MinHeap heap){
        for(Vertex v : map.values()){
            heap.insert(v);
        }
    }

    /*读表建图*/
    public void Graph(String path){
        File input = new File(path);
        try {
            Workbook workbook = Workbook.getWorkbook(input);
            int sheetSize = workbook.getNumberOfSheets();
            String lineName;
            String stationName;
            String time1 = "";
            String time2 = "";

            for(int s = 0; s < sheetSize;s++) {
                lineName = workbook.getSheetNames()[s];
                Sheet sheet = workbook.getSheet(s);

                /*有两个方向的站*/
                if (s == 9 || s == 10) {
                    Vertex newStation = null;
                    stationName = sheet.getCell(0, 2).getContents();

                    if (map.get(stationName) == null) {
                        newStation = new Vertex(stationName);
                        map.put(stationName, newStation);
                    } else newStation = map.get(stationName);

                    time1 = sheet.getCell(1,2).getContents();

                    Vertex preStation = null;
                    Vertex btStation = null;
                    int j = 0;//两个方向的站点对应的索引

                    preStation = newStation;
                    for (int i = 3; i < sheet.getRows(); i++) { //第一个方向和第二个方向的部分站点
                            stationName = sheet.getCell(0, i).getContents();
                            time2 = sheet.getCell(1,i).getContents();

                        if (time2.equals("--") || sheet.getCell(2, i).getContents().equals("--")) { //跳过某些站点，设置btstation
                            if (btStation == null) {
                                btStation = preStation;
                                j = i;
                            }
                            if(time2.equals("--") )
                                continue;
                        }

                        if (map.get(stationName) == null) {
                            newStation = new Vertex(stationName);
                            map.put(stationName, newStation);
                        } else newStation = map.get(stationName);
                        preStation.adjVertex.add(newStation);
                        newStation.adjVertex.add(preStation);
                        Edge edge = new Edge(preStation, newStation, lineName, getTime(time1, time2));
                        preStation.adjEdges.add(edge);
                        newStation.adjEdges.add(edge);
                        time1 = time2;
                        preStation = newStation;
                    }

                    preStation = btStation;

                    time1 = sheet.getCell(2,j - 1).getContents();

                    for (; j < sheet.getRows(); j++) {

                        stationName = sheet.getCell(0, j).getContents();
                        time2 = sheet.getCell(2,j).getContents();

                        if (time2.equals("--")) { //跳过某些站点
                            continue;
                        }

                        if (map.get(stationName) == null) {
                            newStation = new Vertex(stationName);
                            map.put(stationName, newStation);
                        } else newStation = map.get(stationName);
                        assert preStation != null;
                        preStation.adjVertex.add(newStation);
                        newStation.adjVertex.add(preStation);
                        Edge edge = new Edge(preStation, newStation, lineName, getTime(time1, time2));
                        preStation.adjEdges.add(edge);
                        newStation.adjEdges.add(edge);

                        time1 = time2;
                        preStation = newStation;
                    }
                } else {

                    Vertex newStation = null;
                    stationName = sheet.getCell(0, 1).getContents();

                    if (map.get(stationName) == null ) {
                        newStation = new Vertex(stationName);
                        map.put(stationName, newStation);
                    } else newStation = map.get(stationName);
                    time1 = sheet.getCell(1, 1).getContents();

                    Vertex preStation = newStation;
                    for (int i = 2; i < sheet.getRows(); i++) {
                        //preStation = newStation;
                        stationName = sheet.getCell(0, i).getContents();
                        time2 = sheet.getCell(1, i).getContents();
                        if (map.get(stationName) == null) {
                            if(s == 3 && stationName.equals("浦电路")){
                                newStation = new Vertex(stationName+"4");
                                map.put(stationName+"4",newStation);
                            }
                            else if(s == 5 && stationName.equals("浦电路")){
                                newStation = new Vertex(stationName+"6");
                                map.put(stationName+"6",newStation);
                            }
                            else {
                                newStation = new Vertex(stationName);
                                map.put(stationName, newStation);
                            }
                        } else newStation = map.get(stationName);
                        preStation.adjVertex.add(newStation);
                        newStation.adjVertex.add(preStation);
                        Edge edge = new Edge(preStation, newStation, lineName, getTime(time1, time2));
                        preStation.adjEdges.add(edge);
                        newStation.adjEdges.add(edge);
                        time1 = time2;
                        preStation = newStation;
                    }
                }
            }

        } catch (IOException | BiffException e ) {
            e.printStackTrace();
        }
    }


    /*计算时间（权值）*/
    int getTime(String time1,String time2){
        int minute = (int)time2.charAt(time2.length()-1) - (int)time1.charAt(time1.length()-1);
        int ten = (int)time2.charAt(time2.length()-2) - (int)time1.charAt(time1.length()-2);
        int hour = (int)time2.charAt(time2.length()-4) - (int)time1.charAt(time1.length()-4);
        hour = hour >= 0?hour:hour + 4;
        return hour*60 + ten*10 + minute;
    }

    /*打印路径*/
    void printPath(Vertex start,Vertex end){
        ArrayList<Vertex> path = new ArrayList<>();

        path.add(end);
        paths = new ArrayList<>();
        paths.add(path);
        prinPath(end,path,start);
        String line;
        Vertex v,vpre;

        int j = 0;
        int transfer[] = new int[paths.size()]; //换乘次数
        StringBuffer[] stringBuffers = new StringBuffer[paths.size()];

        for(int i = 0;i < stringBuffers.length;i++){
            stringBuffers[i] = new StringBuffer("");
        }

        for(ArrayList<Vertex> apath:paths){
            vpre = start;
            line = "";

            if(apath.size() == 2){
                line = apath.get(0).getEdge(vpre).line;
                stringBuffers[j].append(vpre.name);
                stringBuffers[j].append(" -- ");
                stringBuffers[j].append(line);
                stringBuffers[j].append(" -- ");
                transfer[j]++;
            }

            for(int i = apath.size() - 2; i >= 0; i--) {
                v = apath.get(i);
                Edge edge = v.getEdge(vpre);
                if(vpre.name.length() != 1){
                    if(!edge.line.equals(line)){
                        transfer[j]++;
                        line = edge.line;
                        if(!vpre.name.equals(midVertex.name))
                            stringBuffers[j].append(vpre.name);
                        stringBuffers[j].append(" -- ");
                        stringBuffers[j].append(line);
                        stringBuffers[j].append(" -- ");
                    }
                }
                vpre = v;
            }
            stringBuffers[j].append(end.name);
            stringBuffers[j].append(" ");
            j++;
        }
        midVertex = end;

        int m = transfer[0];
        for(int k = 1;k < transfer.length;k++){
            if(m > transfer[k]){
                m = transfer[k];
            }
        }
        for(int k = 0;k < transfer.length;k++){
            if(transfer[k] == m) {
                //System.out.print(stringBuffers[k].toString()+"   ");
                if(endString.containsKey(k)){
                    String a = endString.get(k);
                    endString.remove(k);
                    endString.put(k,a+stringBuffers[k].toString());
                }
                else {
                    endString.put(k, stringBuffers[k].toString());
                }
            }
        }
    }

    private void prinPath(Vertex now,ArrayList<Vertex> path,Vertex start){
        if(now != start){
            int distance = now.dist;
            Vertex vertex;
            for(int i = 1; i < now.preNode.size(); i++){
                vertex = now.preNode.get(i);
                if(vertex.dist + now.getEdge(vertex).weight == distance){
                    ArrayList<Vertex> apath = new ArrayList<>();
                    apath.addAll(path);
                    apath.add(vertex);
                    paths.add(apath);
                    prinPath(vertex,apath,start);
                }
            }
            path.add(now.preNode.get(0));
            prinPath(now.preNode.get(0),path,start);
        }
    }

    void finalprint(){
        for(String s:endString.values()){
            System.out.println(s);
        }
    }
}
