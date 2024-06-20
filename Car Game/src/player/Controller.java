package player;

import Screens.ActionChoicePanel;
import Screens.Scene;
import Screens.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private Scene scene;
    private final CarPlayer carPlayer;

    /**
     * בונה את ה-Controller ומקבל את הסצנה והרכב השחקן
     * @param scene הסצנה הנוכחית של המשחק
     * @param carPlayer אובייקט הרכב של השחקן
     */
    public Controller(Scene scene, CarPlayer carPlayer) {
        this.scene = scene;
        this.carPlayer = carPlayer;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * מתודה זו מופעלת כאשר משתמש לוחץ על מקש כלשהו, ומבצעת פעולה בהתאם למקש שנלחץ
     * @param e אירוע הקשה על מקלדת
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (carPlayer.getLane() < 2) {
                carPlayer.setLane(carPlayer.getLane() + 1);
                carPlayer.setMoving(true);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (carPlayer.getLane() > 0) {
                carPlayer.setLane(carPlayer.getLane() - 1);
                carPlayer.setMoving(true);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            scene.stopThreads();
            addActionChoicePanel();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * מוסיף את לוח הבחירה של הפעולה לסצנה ומגדיר את פעולות הכפתורים
     */
    private void addActionChoicePanel() {
        ActionChoicePanel actionChoicePanel = new ActionChoicePanel(Scene.WIDTH / 2 - ActionChoicePanel.WIDTH / 2, Scene.HEIGHT / 2 - ActionChoicePanel.HEIGHT / 2);
        this.scene.add(actionChoicePanel);
        actionChoicePanel.requestFocus();

        actionChoicePanel.addCancelButtonActionListener((event) -> {
            this.scene.remove(actionChoicePanel);
            this.scene.requestFocus();
            scene.resumeThreads();
        });

        actionChoicePanel.addYesButtonActionListener((event) -> {
            this.scene.remove(actionChoicePanel);
            Window.removeTopPanel(scene);
        });

        this.scene.repaint();
    }
}