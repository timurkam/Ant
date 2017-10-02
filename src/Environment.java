
import javax.swing.*;

public class Environment extends JFrame {
    JFrame frame;
    Graph graph;

    //Traveler traveler;

    public Environment(Graph graph) {
        this.graph = graph;

            //this.traveler = traveler;
        frame = new JFrame("Ants");
        frame.setBounds(0, 0, 1000, 700);
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void print() {
        frame.getContentPane().add(new JScrollPane(graph));
        frame.setVisible(true);
    }


}


