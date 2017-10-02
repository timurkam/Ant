import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import static sun.applet.AppletResourceLoader.getImage;


/**
 * Created by 16089 on 12.03.2017.
 */

public class Graph extends JPanel {
    public static final int MAX_STOP=20;
    private int nStop; // Текущее количество остановок
    public static Stop[] stopList; // Массив остановок
    public static ArrayList<Way> roadsList;
    public ArrayList<Traveler> travelersList;

    public void move() {
        for (Traveler tr : travelersList) tr.move();
    }

    public void ispareniye() {
        for (Way way : roadsList)
        {
            if(way.ferment>0)
            way.ferment-=0.0000165;
        }

    }

    public void run() {
        while (true) {
            move();
            repaint();
            ispareniye();

            try {
                Thread.sleep(0);
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }
    }

    public Graph(ArrayList<Traveler> travelersList) // Конструктор
    {

        stopList = new Stop[MAX_STOP];
        //adjMat = new double[MAX_STOP][MAX_STOP];
        nStop = 0;
        roadsList = new ArrayList<>();
        this.travelersList = travelersList;
        /*for (int j = 0; j <= MAX_STOP; j++)
            for (int k = 0; k <=MAX_STOP; k++)
                adjMat[j][k] = 0;*/
    }


    public void addStop(Stop a) {
        stopList[nStop++] = a;
    }


    public void addWay(Stop startStop, Stop finishStop) {
        //adjMat[startStop.getIndex()][finishStop.getIndex()] = Way.getDistance(startStop, finishStop);
        //adjMat[finishStop.getIndex()][startStop.getIndex()] = Way.getDistance(startStop, finishStop);
        Way tmpway = new Way(startStop, finishStop);
        roadsList.add(tmpway);
    }


    /*public void displayStop(int v) {
        System.out.print(stopList[v].getName());
    }*/

    /*public double getAdjMat(int i, int j) {
        return adjMat[i][j];
    }*/

    public Stop getStopFromList(int index) {
        return stopList[index];
    }





    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Color newColor = new Color(0, 0, 255);
        g2.setColor(newColor);
        for (Stop stop : stopList) {
            g2.drawString(String.valueOf(stop.getIndex()), stop.x, stop.y);
        }
        //g2.setStroke(new BasicStroke(5.0f));
        int widthOfOvals = 15;
        g2.setColor(Color.BLACK);


        //for (Traveler tr : travelersList)
        for (Way way : roadsList) {
            if (way.ferment > 0) {


                g2.drawLine(way.stop1.x + widthOfOvals / 2, way.stop1.y + widthOfOvals / 2, way.stop2.x + widthOfOvals / 2, way.stop2.y + widthOfOvals / 2);
                //g2.drawString(String.valueOf(way.ferment),(way.stop1.x + way.stop2.x) / 2, (way.stop1.y + way.stop2.y) / 2);
            }
        }

        for (Stop stop : stopList) {
            g2.setColor(new Color(255, 192, 37));
            g2.fillOval(stop.x, stop.y, widthOfOvals, widthOfOvals);
        }

        g.setColor(new Color(255, 0, 0));


        for (Traveler tr : travelersList) {
            g.fillOval((int) tr.x, (int) tr.y, widthOfOvals, widthOfOvals);
            g2.drawString(String.valueOf(tr.j), (int) tr.x, (int) tr.y);
            g2.drawString("Best Distance:"+String.valueOf(tr.bestdist),1000, 500);
            //g2.drawString(tr.bestporyadok,80, 700);
        }

    }

}