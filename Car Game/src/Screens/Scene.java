package Screens; /**
 * מחלקה זו מייצגת את הסצנה הראשית של המשחק. היא מרחיבה את JPanel ואחראית על הצגת הסצנה של המשחק,
 * כולל הרקע והכביש. הכביש נע כלפי מטה בצורה רציפה כדי ליצור אפקט אנימציה.
 *
 * - הסצנה מאותחלת עם מיקום נתון.
 * - הכביש מצויר ומונפש לנוע כלפי מטה.
 * - הרקע נצבע בצבע אפור.
 * - חוט (Thread) משמש להנעת הכביש בצורה רציפה ולעדכון הציור של הסצנה.
 */
import RoadManagement.Road;
import Utilities.GraphicsUtils;
import player.CarPlayer;
import player.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * מחלקה זו מייצגת את הסצנה הראשית של המשחק.
 */
public class Scene extends JPanel {

    public static final int WIDTH = Window.WIDTH;
    public static final int HEIGHT = Window.HEIGHT;
    private final Road road;
    private final CarPlayer carPlayer;
    private final Controller controller;

    /**
     * יוצר אובייקט חדש של Screens.Scene עם מיקום נתון.
     *
     * @param x המיקום האופקי של הסצנה
     * @param y המיקום האנכי של הסצנה
     */
    public Scene(int x, int y) {
        setBounds(x, y, WIDTH, HEIGHT);

        road = new Road(WIDTH, HEIGHT);
        Thread moveRoad = moveRoad();
        moveRoad.start();

        carPlayer = new CarPlayer();

        controller = new Controller(carPlayer);
        addKeyListener(controller);
        setFocusable(true);
        requestFocusInWindow();
    }

    /**
     * מציירת את הסצנה והרכיבים הגרפיים שלה.
     *
     * @param g אובייקט ה-Graphics המשמש לציור
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // צביעת הרקע בצבע אפור
        GraphicsUtils.paintBackground(g, WIDTH, HEIGHT, 0, 0, Color.GRAY);

        // ציור הכביש
        road.drawShape(g);

        carPlayer.paint(g);
    }

    /**
     * יוצר ומחזיר חוט (Thread) המניע את הכביש כלפי מטה כל הזמן ומרענן את הציור.
     *
     * @return Thread חוט המניע את הכביש ומרענן את הציור
     */
    private Thread moveRoad() {
        Thread thread = new Thread(() -> {
            while (true) {
                this.road.moveDown();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }
}