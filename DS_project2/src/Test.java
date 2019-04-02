import java.sql.Time;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        String file = "src/Timetable.xls";
        Dijkstra graph = new Dijkstra();
        graph.Graph(file);

        String str = "";
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            str = scanner.nextLine();
        }scanner.close();

        String[] a = str.split(" ");

        long start = System.nanoTime();
        for(int i = 0 ;i < a.length-1; i++){
            graph.dijkstra(a[i],a[i+1]);
        }
        long end = System.nanoTime();
        graph.finalprint();
        System.out.print("  查找时间："+(end-start)/1000000+" ms ");
    }
}
