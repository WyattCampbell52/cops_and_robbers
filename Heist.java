/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cops_and_robbers;

import environment.Environment;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
class Heist extends Environment {
//we need a more than one person game with the camera following the person and the gun follows the mouse.

    Bank bank;
    Shoot shoot;
    Robber robber;
    CrossHairs crossHairs;
    private ArrayList<Shoot> bullet;
    private String direction;
    private Point mousePosition;

    public Heist() {
        this.setBackground(ResourceTools.loadImageFromResource("images/Bank_Heist.png"));
        robber = new Robber(0, 0, null);
//        bank = new Bank(0, 0);
        bullet = new ArrayList<>();
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosition = e.getPoint();
                repaint();
//                    System.out.println("Angle = " + TrigonometryCalculator.calculateAngle(robber.centreOfMass(), e.getPoint()) + " radians");
                robber.setAngleRadians(TrigonometryCalculator.calculateAngle(robber.centreOfMass(), mousePosition));
            }
        });
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        if (bullet != null) {
            for (Shoot shoot : bullet) {
                shoot.move();
            }
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            robber.moveHorizontal(-10);
            direction = "Left";
            System.out.println(robber.getX());
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            robber.moveHorizontal(10);
            direction = "Right";
            System.out.println(robber.getX());

        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            robber.moveVertical(-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            robber.moveVertical(10);
        }
//        bulleting for now
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    mousePosition = e.getPoint();
                    repaint();
//                    System.out.println("Angle = " + TrigonometryCalculator.calculateAngle(robber.centreOfMass(), e.getPoint()) + " radians");
                    robber.setAngleRadians(TrigonometryCalculator.calculateAngle(robber.centreOfMass(), mousePosition));
                    crossHairs = new CrossHairs(mousePosition);
                }
            });
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (robber.mags > 0) {
                if (robber.bulletCount < 25) {
                    robber.reload();;
                }
            }
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
//            Want to bullet with the mouse
        if (robber.bulletCount > 0) {
            System.out.println("shot");
            bullet.add(new Shoot(robber.centreOfMass().x + 20, robber.centreOfMass().y, TrigonometryCalculator.calculateAngle(robber.centreOfMass(), mousePosition), -TrigonometryCalculator.calculateAngle(robber.centreOfMass(), e.getPoint()) + 90));
            robber.bulletCount = robber.bulletCount - 1;
        }
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
//        if (bank != null) {
//            bank.draw(graphics);
        graphics.drawString("Bullets" + robber.bulletCount + "/" + robber.mags, 300, 300);
//        }
        if (crossHairs != null) {
            crossHairs.draw(graphics);
        }
        if (bullet != null) {
            for (Shoot bulleting : bullet) {
                bulleting.draw(graphics);
            }
        }
        if (robber != null) {
            robber.draw(graphics);
        }
    }
}
