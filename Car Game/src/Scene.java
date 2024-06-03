import Utilities.GraphicsUtils;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Scene extends JPanel {

    public static final int WIDTH = Window.WIDTH;
    public static final int HEIGHT = Window.HEIGHT;

    private Road road;
    public Scene(int x, int y) {
        setBounds(x, y, WIDTH, HEIGHT);

        road = new Road(WIDTH, HEIGHT);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GraphicsUtils.paintBackground(g, WIDTH, HEIGHT, 0, 0, Color.GRAY);

        road.drawShape(g);
    }
}