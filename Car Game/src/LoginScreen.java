import Utilities.FasterOneFont;
import Utilities.RoundedCornerButton;
import Utilities.GraphicsUtils;
import Utilities.PoetsenOneFont;

import javax.swing.*;
import java.awt.*;

/**
 * מחלקה זו מייצגת את מסך הכניסה של התוכנית.
 */
public class LoginScreen extends JPanel {

    public static final int WIDTH = Window.WIDTH;
    public static final int HEIGHT = Window.HEIGHT;

    private final ImageIcon BACKGROUND_IMAGE;
    private static final float ALPHA = 1.0f;

    private static final String[] TITLE_TEXT = {"Crazy", "Cars"};
    private static final Font CUSTOM_FONT_TEXT = FasterOneFont.getCustomFont(125f);
    private static final int TITLE_WIDTH = 500;
    private static final int TITLE_HEIGHT = 100;
    private static int title_x = WIDTH / 2 - TITLE_WIDTH / 2;
    private static int title_y = HEIGHT / 8;

    private static RoundedCornerButton startButton;
    private static RoundedCornerButton settingButton;
    private static final int WIDTH_START_BUTTON = 150;
    private static final int HEIGHT_START_BUTTON = 50;
    private static final String START_TEXT = "START";
    private static final String SETTINGS_TEXT = "SETTINGS";
    private static final Font CUSTOM_FONT_BUTTON = PoetsenOneFont.getCustomFont(25f);
    private static final Color COLOR_BUTTON = new Color(139, 123, 116);

    /**
     * יוצר אובייקט חדש של LoginScreen.
     **/
    public LoginScreen() {
        this.setBounds(0,0, WIDTH, HEIGHT);

        this.BACKGROUND_IMAGE = new ImageIcon("Car Game/resources/Photos/One-sidedLoginImage.jpg");

        createTitleLabels();

        startButton = RoundedCornerButton.myButton(WIDTH / 2 - WIDTH_START_BUTTON / 2, title_y,
                WIDTH_START_BUTTON, HEIGHT_START_BUTTON, START_TEXT, COLOR_BUTTON, this);
        startButton.setFont(CUSTOM_FONT_BUTTON);

        settingButton = RoundedCornerButton.myButton(startButton.getX(), startButton.getY() + startButton.getHeight() + 10,
                startButton.getWidth(), startButton.getHeight(), SETTINGS_TEXT, COLOR_BUTTON, this);
        settingButton.setFont(CUSTOM_FONT_BUTTON);

        setLayout(null);

        startButton.addActionListener((event) -> {
            Scene scene = new Scene(0, 0);
            Window.addPanelOnTop(scene);
            scene.requestFocus();
        });

        settingButton.addActionListener((event) -> {
            SettingsPanel settingsPanel = new SettingsPanel(Window.WIDTH / 3, Window.HEIGHT / 10);
            Window.addPanelOnTop(settingsPanel);

            settingsPanel.addCancelButtonActionListener((e) -> {
                Window.removeTopPanel(settingsPanel);
            });
        });
    }

    /**
     * מצייר את הרקע והמרכיבים הגרפיים על הפאנל.
     *
     * @param g אובייקט ה-Graphics המשמש לציור
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GraphicsUtils.drawImageWithAlpha(g, BACKGROUND_IMAGE.getImage(), getX(), getY(),
                WIDTH, HEIGHT, ALPHA, this);
    }

    /**
     * יוצר את תוויות הכותרת ומוסיף אותן לפאנל.
     */
    private void createTitleLabels() {
        for (String word : TITLE_TEXT) {
            JLabel titleLabel = createLabel(word, title_x, title_y);
            add(titleLabel);
            title_x += WIDTH / 8;
            title_y += title_y;
        }
    }

    /**
     * יוצר תווית עם טקסט, מיקום וגודל נתונים.
     *
     * @param text הטקסט של התווית
     * @param x    המיקום האופקי של התווית
     * @param y    המיקום האנכי של התווית
     * @return התווית שנוצרה
     */
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(CUSTOM_FONT_TEXT);
        label.setBounds(x, y, TITLE_WIDTH, TITLE_HEIGHT);
        return label;
    }
}
