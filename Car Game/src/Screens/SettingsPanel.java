package Screens;

import Utilities.GraphicsUtils;
import Utilities.RoundedCornerButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * מחלקה זו מייצגת את פאנל ההגדרות.
 */
public class SettingsPanel extends JPanel {

    public static final int WIDTH = Window.WIDTH / 3 * 2;
    public static final int HEIGHT = Window.HEIGHT / 3 * 2;

    private RoundedCornerButton cancelButton;
    private JTextArea instructionsText;

    /**
     * יוצר אובייקט חדש של SettingsPanel.
     *
     * @param x המיקום האופקי של הפאנל
     * @param y המיקום האנכי של הפאנל
     */
    public SettingsPanel(int x, int y) {
        setBounds(x, y, WIDTH, HEIGHT);
        this.setOpaque(false);
        setLayout(null);

        instructionsText = createInstructionsText();
        instructionsText.setBounds(20, 20, WIDTH - 40, HEIGHT - (LoginScreen.HEIGHT_BUTTON * 2 ));
        add(instructionsText);

        cancelButton = RoundedCornerButton.myButton(WIDTH / 2 - LoginScreen.WIDTH_BUTTON / 2, HEIGHT - (LoginScreen.HEIGHT_BUTTON * 2 ), LoginScreen.WIDTH_BUTTON, LoginScreen.HEIGHT_BUTTON, "OK", LoginScreen.COLOR_BUTTON, this);
        cancelButton.setFont(LoginScreen.CUSTOM_FONT_BUTTON);
        add(cancelButton);
    }

    /**
     * יוצר ומחזיר אזור טקסט להוראות המשחק.
     *
     * @return אובייקט JTextArea עם טקסט ההוראות
     */
    private JTextArea createInstructionsText() {
        JTextArea textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(LoginScreen.CUSTOM_FONT_BUTTON.deriveFont(25f));
        textArea.setForeground(Color.BLACK);
        textArea.setText("### Game Objective:\nStay alive on the road for as long as possible.\n\n### How to Play:\nUse the left and right arrow keys on your keyboard to switch lanes. Each press moves you to the next lane. To pause the game, hit the Esc button.\n\n### Good Luck!\nMay the odds be ever in your favor! Have a blast and stay safe out there! 🚗💨");
        return textArea;
    }

    /**
     * מוסיף מאזין לאירוע לחצן ביטול.
     *
     * @param actionListener אובייקט ActionListener להוספה לכפתור הביטול
     */
    public void addCancelButtonActionListener(ActionListener actionListener) {
        cancelButton.addActionListener(actionListener);
    }

    /**
     * מצייר את הרקע והמרכיבים הגרפיים על הפאנל.
     *
     * @param g אובייקט ה-Graphics המשמש לציור
     * לא מחזירה ערך.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GraphicsUtils.paintBackground(g, WIDTH, HEIGHT, 30, 30, new Color(255, 255, 255));
    }
}