package Screens;

import Utilities.GraphicsUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {

    public static final int WIDTH = Window.WIDTH / 3;
    public static final int HEIGHT = Window.HEIGHT / 6 * 5;

    private JButton cancelButton;

    public SettingsPanel(int x, int y) {
        setBounds(x, y, WIDTH, HEIGHT);
        setLayout(null);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(WIDTH - 100, HEIGHT - 50, 80, 30);
        add(cancelButton);
    }

    public void addCancelButtonActionListener(ActionListener actionListener) {
        cancelButton.addActionListener(actionListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GraphicsUtils.paintBackground(g, WIDTH, HEIGHT, 0, 0, Color.BLACK);
    }
}
