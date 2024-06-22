//package Screens;
//
//import Utilities.*;
//
//import javax.swing.*;
//import java.awt.*;
//
///**
// * מחלקה זו מייצגת את מסך הכניסה של התוכנית.
// */
//public class LoginScreen extends JPanel {
//
//    public static final int WIDTH = Window.WIDTH;
//    public static final int HEIGHT = Window.HEIGHT;
//
//    private final ImageIcon BACKGROUND_IMAGE;
//    public static final float ALPHA = 1.0f;
//
//    private static final String[] TITLE_TEXT = {"Crazy", "Cars"};
//    private static final Font CUSTOM_FONT_TEXT = MyFont.getCustomFont(MyFont.eFont.FasterOne,125f);
//    private static final int TITLE_WIDTH = 500;
//    private static final int TITLE_HEIGHT = 100;
//    private static int title_x = WIDTH / 2 - TITLE_WIDTH / 2;
//    private static int title_y = HEIGHT / 8;
//
//    private static RoundedCornerButton startButton;
//    private static RoundedCornerButton settingButton;
//    private static final int WIDTH_START_BUTTON = 200;
//    private static final int HEIGHT_START_BUTTON = 50;
//    private static final String START_TEXT = "START";
//    private static final String SETTINGS_TEXT = "SETTINGS";
//    public static final Font CUSTOM_FONT_BUTTON = MyFont.getCustomFont(MyFont.eFont.PoetsenOne,25f);
//    public static final Color COLOR_BUTTON = Color.RED;
//
//    private final String idMusic = "musicLoginScreen";
//
//    /**
//     * יוצר אובייקט חדש של Screens.LoginScreen.
//     **/
//    public LoginScreen() {
//
//        MyMusic.playMusic(idMusic, "Car Game/resources/music/Epic Rock Background Music.wav", 1.0f);
//
//        this.setBounds(0,0, WIDTH, HEIGHT);
//
//        this.BACKGROUND_IMAGE = new ImageIcon("Car Game/resources/Photos/BeckgroundImageLoginScreen.jpg");
//
//        createTitleLabels();
//
//        startButton = RoundedCornerButton.myButton(WIDTH / 2 - WIDTH_START_BUTTON / 2, title_y,
//                WIDTH_START_BUTTON, HEIGHT_START_BUTTON, START_TEXT, COLOR_BUTTON, this);
//        startButton.setFont(CUSTOM_FONT_BUTTON);
//
//        settingButton = RoundedCornerButton.myButton(startButton.getX(), startButton.getY() + startButton.getHeight() + 10,
//                startButton.getWidth(), startButton.getHeight(), SETTINGS_TEXT, COLOR_BUTTON, this);
//        settingButton.setFont(CUSTOM_FONT_BUTTON);
//
//        setLayout(null);
//
//        startButton.addActionListener((event) -> {
//            Scene scene = new Scene(0, 0);
//            Window.addPanelOnTop(scene);
//            scene.requestFocus();
//
//            MyMusic.stopMusic(idMusic);
//        });
//
//        settingButton.addActionListener((event) -> {
//            SettingsPanel settingsPanel = new SettingsPanel(Window.WIDTH / 3, Window.HEIGHT / 10);
//            Window.addPanelOnTop(settingsPanel);
//            settingsPanel.requestFocus();
//
//            startButton.setVisible(false);
//            settingButton.setVisible(false);
//
//            settingsPanel.addCancelButtonActionListener((e) -> {
//                Window.removeTopPanel(settingsPanel);
//                startButton.setVisible(true);
//                settingButton.setVisible(true);
//            });
//        });
//    }
//
//    /**
//     * מצייר את הרקע והמרכיבים הגרפיים על הפאנל.
//     *
//     * @param g אובייקט ה-Graphics המשמש לציור
//     */
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        GraphicsUtils.drawImageWithAlpha(g, BACKGROUND_IMAGE.getImage(), getX(), getY(),
//                WIDTH, HEIGHT, ALPHA, this);
//    }
//
//    /**
//     * יוצר את תוויות הכותרת ומוסיף אותן לפאנל.
//     */
//    private void createTitleLabels() {
//        for (String word : TITLE_TEXT) {
//            JLabel titleLabel = createLabel(word, title_x, title_y);
//            add(titleLabel);
//            title_x += WIDTH / 8;
//            title_y += title_y;
//        }
//    }
//
//    /**
//     * יוצר תווית עם טקסט, מיקום וגודל נתונים.
//     *
//     * @param text הטקסט של התווית
//     * @param x    המיקום האופקי של התווית
//     * @param y    המיקום האנכי של התווית
//     * @return התווית שנוצרה
//     */
//    private JLabel createLabel(String text, int x, int y) {
//        JLabel label = new JLabel(text);
//        label.setFont(CUSTOM_FONT_TEXT);
//        label.setBounds(x, y, TITLE_WIDTH, TITLE_HEIGHT);
//        return label;
//    }
//}
package Screens;

import Utilities.*;

import javax.swing.*;
import java.awt.*;

/**
 * מחלקה זו מייצגת את מסך הכניסה של התוכנית.
 */
public class LoginScreen extends JPanel {

    public static final int WIDTH = Window.WIDTH;
    public static final int HEIGHT = Window.HEIGHT;

    private final ImageIcon BACKGROUND_IMAGE;
    public static final float ALPHA = 1.0f;

    private static RoundedCornerButton startButton;
    private static RoundedCornerButton settingButton;
    public static final int WIDTH_BUTTON = 200;
    public static final int HEIGHT_BUTTON = 50;
    private final String START_TEXT;
    private final String SETTINGS_TEXT;
    public static final Font CUSTOM_FONT_BUTTON = MyFont.getCustomFont(MyFont.eFont.PoetsenOne,25f);
    public static final Color COLOR_BUTTON = Color.RED;

    private static final String idMusic = "musicLoginScreen";

    /**
     * יוצר אובייקט חדש של Screens.LoginScreen.
     **/
    public LoginScreen() {

        playMusic();

        this.setBounds(0,0, WIDTH, HEIGHT);

        START_TEXT = "START";
        SETTINGS_TEXT = "INSTRUCTIONS";

        this.BACKGROUND_IMAGE = new ImageIcon("Car Game/resources/Photos/BeckgroundImageLoginScreen.jpg");

        startButton = RoundedCornerButton.myButton(WIDTH / 2 - WIDTH_BUTTON / 2, HEIGHT / 4 * 3,
                WIDTH_BUTTON, HEIGHT_BUTTON, START_TEXT, COLOR_BUTTON, this);
        startButton.setFont(CUSTOM_FONT_BUTTON);

        settingButton = RoundedCornerButton.myButton(startButton.getX(), startButton.getY() + startButton.getHeight() + 10,
                startButton.getWidth(), startButton.getHeight(), SETTINGS_TEXT, COLOR_BUTTON, this);
        settingButton.setFont(CUSTOM_FONT_BUTTON);

        setLayout(null);

        startButton.addActionListener((event) -> {

            Scene scene = new Scene(0, 0);
            Window.addPanelOnTop(scene);
            scene.requestFocus();

            Window.removeTopPanel(this);

            MyMusic.stopMusic(idMusic);
        });

        settingButton.addActionListener((event) -> {
            SettingsPanel settingsPanel = new SettingsPanel(Window.WIDTH / 2 - SettingsPanel.WIDTH / 2, Window.HEIGHT  / 2 - SettingsPanel.HEIGHT / 2);
            Window.addPanelOnTop(settingsPanel);
            settingsPanel.requestFocus();

            startButton.setVisible(false);
            settingButton.setVisible(false);

            settingsPanel.addCancelButtonActionListener((e) -> {
                Window.removeTopPanel(settingsPanel);
                startButton.setVisible(true);
                settingButton.setVisible(true);
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

    public static void playMusic() {
        MyMusic.playMusic(idMusic, "Car Game/resources/music/Epic Rock Background Music.wav", 1.0f, false);
    }
}