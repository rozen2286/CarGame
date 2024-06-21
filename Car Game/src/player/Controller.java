package player;

import Screens.ActionChoicePanel;
import Screens.LoginScreen;
import Screens.Scene;
import Screens.Window;
import Utilities.MyMusic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private Scene scene;
    private final CarPlayer carPlayer;

    private final String idSoundClick = "soundClick";

    /**
     * בונה את ה-Controller ומקבל את הסצנה והרכב השחקן
     *
     * @param scene     הסצנה הנוכחית של המשחק
     * @param carPlayer אובייקט הרכב של השחקן
     */
    public Controller(Scene scene, CarPlayer carPlayer) {
        this.scene = scene;
        this.carPlayer = carPlayer;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // לא בשימוש כרגע
    }

    /**
     * מתודה זו מופעלת כאשר משתמש לוחץ על מקש כלשהו, ומבצעת פעולה בהתאם למקש שנלחץ
     *
     * @param e אירוע הקשה על מקלדת
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // מעביר את הרכב לנתיב הימני אם אינו בנתיב הימני ביותר
            if (carPlayer.getLane() < 2) {
                carPlayer.setLane(carPlayer.getLane() + 1);
                carPlayer.setMoving(true);

                playSound();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // מעביר את הרכב לנתיב השמאלי אם אינו בנתיב השמאלי ביותר
            if (carPlayer.getLane() > 0) {
                carPlayer.setLane(carPlayer.getLane() - 1);
                carPlayer.setMoving(true);

                playSound();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            // עוצר את המשחק ומציג את פאנל הבחירה של הפעולה
            scene.stopThreads();
            addActionChoicePanel();

            Scene.stopMusic();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // לא בשימוש כרגע
    }

    /**
     * מוסיף את לוח הבחירה של הפעולה לסצנה ומגדיר את פעולות הכפתורים
     */
    private void addActionChoicePanel() {
        ActionChoicePanel actionChoicePanel = new ActionChoicePanel(Scene.WIDTH / 2 - ActionChoicePanel.WIDTH / 2, Scene.HEIGHT / 2 - ActionChoicePanel.HEIGHT / 2);
        this.scene.add(actionChoicePanel);
        actionChoicePanel.requestFocus();

        // הגדרת פעולות הכפתור ביטול
        actionChoicePanel.addCancelButtonActionListener((event) -> {
            this.scene.remove(actionChoicePanel);
            this.scene.requestFocus();
            scene.resumeThreads();

            Scene.playMusic();
        });

        // הגדרת פעולות הכפתור כן
        actionChoicePanel.addYesButtonActionListener((event) -> {
            this.scene.remove(actionChoicePanel);
            Window.removeTopPanel(scene);

            LoginScreen.playMusic();
        });

        this.scene.repaint();
    }

    private void playSound() {
        MyMusic.playMusic(idSoundClick, "Car Game/resources/music/mixkit-cool-interface-click-tone-2568.wav", 1.0f, false);
    }
}
