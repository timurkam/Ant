import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by 16089 on 11.03.2017.
 */
public class Traveler {
    //Graph g;
    public double x, y;
    public int j;
    public String name;
    public Stop startStop;
    //private ArrayList<Stop> map;
    public boolean[] map = new boolean[Graph.MAX_STOP];
    public String poryadok = new String();
    public String bestporyadok = new String();
    //перерисовка
    public int dx, dy;
    public double dx1, dy1;
    public Stop tmpStop = null;
    public boolean flag = true;
    public int i = 0;
    public int counter = 0;
    public static int bestdist=0;
    public int dist = 0;
    public int currentway=0;
    public int nuzhnoe=0;
    int finalwayindex = -1;
    //Way prevway = null;
    int indexprevprev = -1;
    //Stop tmpStop=startStop;

    public Stop findWay() {
        ArrayList<Way> result = new ArrayList<>();
        for (int i = 0; i < Graph.roadsList.size(); i++)
            if ((Graph.roadsList.get(i).stop1 == startStop) || (Graph.roadsList.get(i).stop2 == startStop))
                result.add(Graph.roadsList.get(i));

        double max = result.get(0).ferment;
        int maxIndex = 0;
        for (int i = 0; i < result.size(); i++)
            if (result.get(maxIndex).ferment > max) {
                max = result.get(maxIndex).ferment;
                maxIndex = i;

            }
        if (result.get(maxIndex).stop1.equals(startStop)) return result.get(maxIndex).stop2;
        else return result.get(maxIndex).stop1;

    }

    public Stop getStop(int index) {
        Stop result = null;
        for (int i = 0; i < Graph.MAX_STOP; i++) {
            if (Graph.stopList[i].getIndex() == index) result = Graph.stopList[i];
        }
        return result;
    }

    public Way getWay(Stop stop1, Stop stop2) {
        Way result = null;
        for (int i = 0; i < Graph.roadsList.size(); i++) {
            if (((Graph.roadsList.get(i).stop1 == stop1) && (Graph.roadsList.get(i).stop2 == stop2)) || ((Graph.roadsList.get(i).stop1 == stop2) && (Graph.roadsList.get(i).stop2 == stop1)))
                result = Graph.roadsList.get(i);
        }
        //System.out.println(result);
        return result;
    }

    public void falseallways() {
        for (int i = 0; i < Graph.roadsList.size(); i++) {
            Graph.roadsList.get(i).onway = false;
        }
    }

    public Traveler(String name, Stop startStop) {
        this.name = name;
        this.startStop = startStop;
        this.x = startStop.x;
        this.y = startStop.y;
        this.tmpStop = new Stop();
        //map = new ArrayList<>();
        for (int i = 0; i < Graph.MAX_STOP; i++) {
            map[i] = false;
            //System.out.println(map[i]);
        }


    }

    public void move() {


        nextStop();

    }

    public void nextStop() {



        Date date = new Date();
        long millis = date.getTime();
        Random r = new Random(millis);
        j = 0;
        int indexprev = -1;

            if (counter == 0) {
                for (int i = 0; i < Graph.MAX_STOP; i++) {
                    if ((Graph.stopList[i].x == x) && (Graph.stopList[i].y == y)) {
                        indexprev = Graph.stopList[i].getIndex();

                    }
                }

                finalwayindex = 0;
                double maxferm = 0;
                for (int i = 0; i < Graph.MAX_STOP; i++) {


                    if ((map[i] == false) && (i != indexprev)) {
                        if (getWay(getStop(indexprev), getStop(i)).ferment > maxferm) {

                            maxferm = getWay(getStop(indexprev), getStop(i)).ferment;
                            finalwayindex = i;
                        }
                    }
                }
                if (maxferm == 0) {
                    do {
                        tmpStop = Graph.stopList[r.nextInt(Graph.MAX_STOP)];
                    } while ((map[tmpStop.getIndex()]) || (tmpStop.getIndex() == indexprev));
                    finalwayindex = tmpStop.getIndex();
                }




                //System.out.println("finalwayindex " + finalwayindex);
                tmpStop = getStop(finalwayindex);
                map[finalwayindex] = true;
                if (tmpStop.getIndex() < 10) poryadok = poryadok + "0" + tmpStop.getIndex() + " ";
                else poryadok = poryadok + tmpStop.getIndex() + " ";
                //System.out.println("poryadok " + poryadok);


                //System.out.println(indexprev);
                dist = Way.getDistance(getStop(indexprev), tmpStop);
                currentway+=dist;
                //double ferm=1/(double)dist;

                getWay(getStop(indexprev), tmpStop).ferment += 1 / (double) dist;
                getWay(getStop(indexprev), tmpStop).onway = true;
                //dist=dist/10;
                dx1 = ((tmpStop.x - x) / dist);
                dy1 = ((tmpStop.y - y) / dist);
            }
            x += dx1;
            y += dy1;
            counter++;
            if (counter == dist) {
                counter = 0;
                //falseallways();
                x = tmpStop.x;
                y = tmpStop.y;
            }

            for (int i = 0; i < Graph.MAX_STOP; i++) {
                if (map[i] == true) j++;
            }
            //System.out.println("j"+j);
            if (j == Graph.MAX_STOP) {
                //flag = false;
                x = getStop(indexprev).x;
                y = getStop(indexprev).y;
                indexprevprev = indexprev;
                j = 0;
                if(bestdist==0)
                    bestdist=currentway;
                if(currentway<bestdist)
                {
                    bestdist = currentway;
                    System.out.println("best " + currentway);
                    bestporyadok = poryadok;
                    System.out.println("best " + bestporyadok);
                }
                currentway=0;
                poryadok = "";
                for (int i = 0; i < Graph.MAX_STOP; i++) {
                    map[i] = false;
                }
            }

    }


}
