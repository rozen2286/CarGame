package Utilities;

import java.awt.*;
import java.awt.image.ImageObserver;

public class GraphicsUtils {

    /**
     * צובעת רקע מלבן עם פינות מעוגלות.
     *
     * @param g הגרפיקה בה להשתמש לציור.
     * @param width רוחב המלבן.
     * @param height גובה המלבן.
     * @param r1 רדיוס הפינה האופקית של המלבן.
     * @param r2 רדיוס הפינה האנכית של המלבן.
     * @param color הצבע בו להשתמש למילוי המלבן.
     */
    public static void paintBackground(Graphics g, int width, int height, int r1, int r2, Color color) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillRoundRect(0, 0, width, height, r1, r2);
        g2.dispose();
    }

    /**
     * מצייר תמונה עם אלפא (שקיפות).
     *
     * @param g הגרפיקה בה להשתמש לציור.
     * @param image התמונה לציור.
     * @param x המיקום האופקי לציור התמונה.
     * @param y המיקום האנכי לציור התמונה.
     * @param width רוחב התמונה.
     * @param height גובה התמונה.
     * @param alpha רמת השקיפות של התמונה.
     * @param observer משקיף התמונה.
     */
    public static void drawImageWithAlpha(Graphics g, Image image, int x, int y, int width, int height, float alpha, ImageObserver observer) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(image, x, y, width, height, observer);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Reset alpha to opaque
    }
}
