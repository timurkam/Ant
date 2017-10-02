import java.util.Date;
import java.util.Random;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        int number=20;
        Date date = new Date();
        long millis = date.getTime();
        Random r = new Random(millis);
        Stop[] arraystop=new Stop[number];
        Traveler tmp;
        ArrayList<Traveler> travelers = new ArrayList<Traveler>();

        for (int i = 0; i < number; i++)
        {
            arraystop[i]=new Stop(""+i, r.nextInt(900), r.nextInt(600),i);
            tmp = new Traveler(""+i, arraystop[i]);
            travelers.add(tmp);
        }
        Graph graph = new Graph(travelers);
        for (int i = 0; i < number; i++)
        {
            graph.addStop(arraystop[i]);
        }

        for (int i = 0; i < graph.MAX_STOP-1; i++) {
            for (int jo = i + 1; jo < graph.MAX_STOP; jo++) {
                graph.addWay(graph.getStopFromList(i), graph.getStopFromList(jo)); //0


            }
        }
        Environment environment = new Environment(graph);
        environment.print();
        graph.run();
    }
}
