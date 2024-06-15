/**
 * מחלקה זו מייצגת את הסצנה הראשית של המשחק. היא מרחיבה את JPanel ואחראית על הצגת הסצנה של המשחק,
 * כולל הרקע והכביש. הכביש נע כלפי מטה בצורה רציפה כדי ליצור אפקט אנימציה.
 * <p>
 * - הסצנה מאותחלת עם מיקום נתון.
 * - הכביש מצויר ומונפש לנוע כלפי מטה.
 * - הרקע נצבע בצבע אפור.
 * - חוט (Thread) משמש להנעת הכביש בצורה רציפה ולעדכון הציור של הסצנה.
 */

import Utilities.CalculusMethods;
import Utilities.GraphicsUtils;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * מחלקה זו מייצגת את הסצנה הראשית של המשחק.
 */
public class Scene extends JPanel {

    public static final int WIDTH = Window.WIDTH;
    public static final int HEIGHT = Window.HEIGHT;
    private CarGen carGenertor;
    private Road road;


    /**
     * יוצר אובייקט חדש של Scene עם מיקום נתון.
     *
     * @param x המיקום האופקי של הסצנה
     * @param y המיקום האנכי של הסצנה
     */
    public Scene(int x, int y) {
        setBounds(x, y, WIDTH, HEIGHT);
        road = new Road(WIDTH, HEIGHT);
        Thread moveRoad = moveRoad();
        this.carGenertor = new CarGen(road);
        moveRoad.start();
        mainGameLoop();
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
        carGenertor.drawCars(g);
    }

    public boolean collision(Rectangle rect1, Rectangle rect2) {

        return rect1.intersects(rect2);
    }

    /**
     * יוצר ומחזיר Thread המניע את הכביש כלפי מטה כל הזמן ומרענן את הציור.
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


    public void mainGameLoop() {

        (new Thread(() -> {
            carGenertor.updateCars();
            while (true) {
                this.repaint();
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        })).start();
    }
}
