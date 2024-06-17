package player;

import javax.swing.*;
import java.awt.*;

public class CarPlayer {

    private int x;
    private int y;
    private final ImageIcon car;

    private static int lane = 2;

    public CarPlayer() {

        car = new ImageIcon("Car Game/resources/Photos/CarPlayerImage.png");

//        this.x = ;
//        this.y = ;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(car.getImage(), x, y, car.getIconWidth() / 2, car.getIconHeight() / 2, null);
    }

    public static void setLane(int lane) {
        CarPlayer.lane = lane;
    }

    public static int getLane() {
        return lane;
    }
}