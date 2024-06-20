package Screens;

import EnemyCar.EnemyCarFactory;
import RoadManagement.Road;
import Utilities.GraphicsUtils;
import player.CarPlayer;
import player.Controller;
import player.OvertakeMeter;
import player.Speedometer;

import javax.swing.*;
import java.awt.*;

public class Scene extends JPanel {
    public static final int WIDTH = Window.WIDTH;
    public static final int HEIGHT = Window.HEIGHT;
    private final Road road;
    private final CarPlayer carPlayer;
    private final Controller controller;
    private final Speedometer speedometer;
    private final OvertakeMeter overtakeMeter;
    private EnemyCarFactory enemyCarFactory;
    private boolean running = true;

    public Scene(int x, int y) {
        setBounds(x, y, WIDTH, HEIGHT);

        road = new Road(WIDTH, HEIGHT);

        carPlayer = new CarPlayer();

        controller = new Controller(this, carPlayer);
        addKeyListener(controller);
        setFocusable(true);
        requestFocusInWindow();

        speedometer = new Speedometer(0,0, 0);
        speedometer.setSpeedLimit(100);

        overtakeMeter = new OvertakeMeter(WIDTH - 220, 0, 5);

        enemyCarFactory = new EnemyCarFactory();

        startThreads();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GraphicsUtils.paintBackground(g, WIDTH, HEIGHT, 0, 0, Color.WHITE);

        road.drawShape(g);

        carPlayer.paint(g);

        speedometer.paint(g);

        overtakeMeter.paint(g);

        enemyCarFactory.paintCars(g);
    }

    private Thread moveRoadAndCars() {
        Thread thread = new Thread(() -> {
            while (running) {
                road.moveDown();

                enemyCarFactory.addCar();
                enemyCarFactory.moveCars();
                enemyCarFactory.removeCars();

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

    private Thread moveCarPlayer() {
        Thread thread = new Thread(() -> {
            while (running) {
                if (carPlayer.getIsMoving()) {
                    carPlayer.move();
                    repaint();
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

    private Thread moveOvertakeMeter() {
        Thread thread = new Thread(() -> {
            while (running) {
                this.speedometer.move();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return thread;
    }

    private void startThreads() {
        running = true;
        Thread moveRoad = moveRoadAndCars();
        moveRoad.start();
        Thread moveCarPlayer = moveCarPlayer();
        moveCarPlayer.start();
        Thread moveOvertakeMeter = moveOvertakeMeter();
        moveOvertakeMeter.start();
    }

    public void stopThreads() {
        running = false;
    }

    public void resumeThreads() {
        startThreads();
    }
}