package player;

import javax.swing.*;
import java.awt.*;

public class CarPlayer {

    private int x;
    private int y;
    private final ImageIcon car;

    public CarPlayer(int x, int y) {

        setX(x);
        setY(y);

        car = new ImageIcon("Car Game/resources/Photos/CarPlayerImage.png");
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(car.getImage(), x, y, car.getIconWidth() / 2, car.getIconHeight() / 2, null);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveRight() {
        x += 10;
    }

    public void moveLeft() {
        x -= 10;
    }
}