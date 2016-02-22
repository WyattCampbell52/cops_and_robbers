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
import java.awt.event.KeyEvent;
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
    Robber robber;
    private ArrayList<Shooting> shoot;
    private String direction;

    public Heist() {
//        this.setBackground(ResourceTools.loadImageFromResource("cops_and_robbers/Bank.png"));
        robber = new Robber(0, 0, null);
//        bank = new Bank(0, 0);
        shoot = new ArrayList<>();
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        if (shoot != null) {
            for (Shooting shooting : shoot) {
                shooting.move(30);
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
//        shooting for now
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (robber.bulletCount > 0) {
                System.out.println("shot");
                shoot.add(new Shooting(robber.getX() + robber.getImage().getWidth(this), robber.getY() + robber.getImage().getHeight(this) / 2 + 5));
                robber.bulletCount = robber.bulletCount - 1;
            }
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
        if (true) {
//            Want to shoot with the mouse
            System.out.println(e.getPoint());
            
            if (robber != null) {
                //output an angle
                System.out.println("Angle = " + TrigonometryCalculator.calculateAngle(robber.centreOfMass(), e.getPoint()) + " radians");
                robber.setAngleRadians(TrigonometryCalculator.calculateAngle(robber.centreOfMass(), e.getPoint()));
            }
            
        }
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
//        if (bank != null) {
//            bank.draw(graphics);
            graphics.drawString("Bullets" + robber.bulletCount + "/" + robber.mags, 300, 300);
//        }
        if (robber != null) {
            robber.draw(graphics);
        }
        if (shoot != null) {
            for (Shooting shooting : shoot) {
                shooting.draw(graphics);
            }
        }
    }
}
