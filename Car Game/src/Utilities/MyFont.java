package Utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyFont {

    public enum eFont {FasterOne, PoetsenOne}
    private static Map<eFont, Font> fonts = new HashMap<>();

    /**
     * טעינת הגופן מקובץ TTF ורישום בסביבת הגרפיקה המקומית.
     */
    private static void loadFont(eFont nameFont) {
        try {
            String fontPath = "";
            switch (nameFont) {
                case FasterOne:
                    fontPath = "Car Game/resources/Font/FasterOne-Regular.ttf";
                    break;
                case PoetsenOne:
                    fontPath = "Car Game/resources/Font/PoetsenOne-Regular.ttf";
                    break;
            }
            if (!fontPath.isEmpty()) {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                fonts.put(nameFont, font);
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * מחזיר את הגופן המותאם עם גודל הנתון.
     *
     * @param nameFont שם הגופן.
     * @param size     גודל הגופן.
     * @return הגופן המותאם עם הגודל.
     */
    public static Font getCustomFont(eFont nameFont, float size) {
        if (!fonts.containsKey(nameFont)) {
            // אם הגופן טרם נטען, טען אותו
            loadFont(nameFont);
        }
        // החזרת הגופן המותאם עם הגודל
        return fonts.get(nameFont).deriveFont(size);
    }
}