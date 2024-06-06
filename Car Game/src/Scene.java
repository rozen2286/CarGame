import Utilities.GraphicsUtils;
import javax.swing.*;
import java.awt.*;

/**
 * מחלקה זו מייצגת את הסצנה הראשית של המשחק.
 */
public class Scene extends JPanel {

    public static final int WIDTH = Window.WIDTH;
    public static final int HEIGHT = Window.HEIGHT;

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
    }
}