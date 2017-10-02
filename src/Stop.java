import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by 16089 on 11.03.2017.
 */
public class Stop {
    private int index;
    private String name;
    public int x,y;
    public Stop(){}

    public Stop(String name,int x, int y,int index) {
        this.name = name;
        this.index = index;
        this.x = x;
        this.y = y;
    }


    public int getIndex() {
        return index;
    }

}
