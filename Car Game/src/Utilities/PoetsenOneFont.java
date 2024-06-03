package Utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class PoetsenOneFont {

    private static Font brushFont;

    /**
     * טעינת הגופן מקובץ TTF ורישום בסביבת הגרפיקה המקומית.
     */
    public static void loadFont() {
        try {
            // טוען את הגופן מקובץ TTF שחולץ מהתיקייה הדחוסה
            brushFont = Font.createFont(Font.TRUETYPE_FONT, new File("Car Game/resources/Font/PoetsenOne-Regular.ttf"));
            // רישום הגופן בסביבת הגרפיקה המקומית
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(brushFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * מחזיר את הגופן המותאם עם גודל הנתון.
     *
     * @param size גודל הגופן.
     * @return הגופן המותאם עם הגודל.
     */
    public static Font getCustomFont(float size) {
        if (brushFont == null) {
            // אם הגופן טרם נטען, טען אותו
            loadFont();
        }
        // החזרת הגופן המותאם עם הגודל
        return brushFont.deriveFont(size);
    }
}
