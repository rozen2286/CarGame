package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private final CarPlayer carPlayer;

    public Controller(CarPlayer carPlayer) {
        this.carPlayer = carPlayer;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (CarPlayer.getLane() < 3) {
                CarPlayer.setLane(CarPlayer.getLane() + 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (CarPlayer.getLane() > 1) {
                CarPlayer.setLane(CarPlayer.getLane() - 1);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
