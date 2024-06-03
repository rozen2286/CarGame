package Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class RoundedCornerButton extends JButton {

    private static final int ARC_WIDTH = 30;
    private static final int ARC_HEIGHT = 30;
    private Color customForeground;
    private Shape shape;

    public RoundedCornerButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    /**
     * קובע את צבע הטקסט המותאם אישית של הכפתור.
     *
     * @param customForeground צבע הטקסט המותאם אישית.
     */
    public void setCustomForeground(Color customForeground) {
        this.customForeground = customForeground;
        setForeground(customForeground);
    }

    /**
     * מצייר את הכפתור, כולל הרקע המעוגל והטקסט.
     *
     * @param g אובייקט ה-Graphics המשמש לציור.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(customForeground);
            setForeground(getBackground());
        } else {
            g2.setColor(getBackground());
            setForeground(customForeground);
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
        super.paintComponent(g2);
        g2.dispose();
    }

    /**
     * מצייר את הגבול המעוגל של הכפתור.
     *
     * @param g אובייקט ה-Graphics המשמש לציור.
     */
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
    }

    /**
     * בודק אם נקודה מסוימת נמצאת בתוך צורת המלבן המעוגל של הכפתור.
     *
     * @param x קואורדינטת ה-x של הנקודה.
     * @param y קואורדינטת ה-y של הנקודה.
     * @return true אם הנקודה נמצאת בתוך הצורה, אחרת false.
     */
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
        }
        return shape.contains(x, y);
    }

    /**
     * יוצרת ומחזירה כפתור מעוגל עם צבע טקסט לבן, ומגדירה אותו בתוך קונטיינר נתון.
     * המתודה משתמשת במתודה השנייה כדי ליצור את הכפתור, עם הגדרת צבע טקסט ברירת מחדל לבן.
     *
     * @param x          הקואורדינטה האופקית של הכפתור.
     * @param y          הקואורדינטה האנכית של הכפתור.
     * @param width      הרוחב של הכפתור.
     * @param height     הגובה של הכפתור.
     * @param name       הטקסט שיופיע על הכפתור.
     * @param background צבע הרקע של הכפתור.
     * @param container  הקונטיינר שבו יתווסף הכפתור.
     * @return הכפתור המעוגל שנוצר.
     */
    public static RoundedCornerButton myButton(int x, int y, int width, int height, String name, Color background, Container container) {

       return myButton(x, y, width, height, name, background, Color.WHITE, container);
    }

    /**
     * יוצרת ומחזירה כפתור מעוגל עם צבע טקסט מותאם אישית וגופן מותאם אישית, ומגדירה אותו בתוך קונטיינר נתון.
     *
     * @param x          הקואורדינטה האופקית של הכפתור.
     * @param y          הקואורדינטה האנכית של הכפתור.
     * @param width      הרוחב של הכפתור.
     * @param height     הגובה של הכפתור.
     * @param name       הטקסט שיופיע על הכפתור.
     * @param background צבע הרקע של הכפתור.
     * @param foreground צבע הטקסט של הכפתור.
     * @param container  הקונטיינר שבו יתווסף הכפתור.
     * @return הכפתור המעוגל שנוצר.
     */
    public static RoundedCornerButton myButton(int x, int y, int width, int height, String name, Color background, Color foreground, Container container) {
        RoundedCornerButton roundedButton = new RoundedCornerButton(name);

        roundedButton.setBounds(x, y, width, height);
        roundedButton.setBackground(background);
        roundedButton.setCustomForeground(foreground);

        container.add(roundedButton);

        return roundedButton;
    }
}