import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by 16089 on 11.03.2017.
 */
public class Way {
    public double ferment;
    public boolean onway=false;
    public boolean best=false;
    public Stop stop1, stop2;
    Way(Stop stop1, Stop stop2){
        this.stop1 = stop1;
        this.stop2 = stop2;
        ferment=0;
    }

    public static int getDistance(Stop city1, Stop city2) {
        if(city1.x == city2.x) {
            return abs(city1.y - city2.y);
        } else if(city1.y == city2.y) {
            return abs(city1.x - city2.x);
        } else return (int)(sqrt(abs(city1.x - city2.x)*abs(city1.x - city2.x) + abs(city1.y - city2.y)*abs(city1.y - city2.y)));
    }
}
