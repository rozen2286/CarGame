import Utilities.CalculusMethods;

import java.awt.*;
import javax.swing.JPanel;


public class CarProperty extends JPanel {
    public int carWidth ;
    public static final int WIDTH = Window.WIDTH;
    public static final int HEIGHT = Window.HEIGHT;
    private int x;
    private int y;
    private int lane;

private Road road;
    private int carhight = 50;

    public CarProperty(int x, int y,Road road) {
        this.x = x;
        this.y = y;
        this.road=road;

    }
    public CarProperty(int x, int y,Road road,int lane) {
        this.x = x;
        this.y = y;
        this.road=road;
        this.lane=lane;

    }

    public int getLane() {
        return lane;
    }

    public void Carprint(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(this.x, this.y, 80, this.carhight);
    }

    public void MovingCarLeft() {
       this.x = x +10;
       this.y = y + 10;
       this.carhight = carhight + 1;

    }
    public void MovingCarCenter() {

        this.y = y + 10;
        this.carhight = carhight + 1;

    }

    public void MovingCarRight() {
        this.x = x -10;
        this.y = y + 10;
        this.carhight = carhight + 1;

    }
    public int getY() {
        return y;
    }

}

