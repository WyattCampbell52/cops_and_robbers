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

/**
 *
 * @author WyattCampbell
 */
class Heist extends Environment {
//we need a more than one person game with the camera following the person and the gun follows the mouse.

    Bank bank;
    Robbers robbers;
    private int bulletCount = 50;
    private int mags = 5;
    private ArrayList<Shooting> shoot;
    private String direction;

    public Heist() {
//        this.setBackground(ResourceTools.loadImageFromResource("cops_and_robbers/Bank.png"));
        robbers = new Robbers(0, 0, null);
        bank = new Bank(0, 0);
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
            robbers.moveHorizontal(-10);
            direction = "Left";
            System.out.println(robbers.getX());
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            robbers.moveHorizontal(10);
            direction = "Right";
            System.out.println(robbers.getX());

        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            robbers.moveVertical(-10);
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            robbers.moveVertical(10);
        }
//        shooting for now
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (bulletCount > 0) {
                System.out.println("shot");
                shoot.add(new Shooting(robbers.getX() + robbers.getImage().getWidth(this), robbers.getY() + robbers.getImage().getHeight(this) / 2 + 5));
                bulletCount = bulletCount - 1;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (mags > 0) {
                if (bulletCount < 50) {

                    bulletCount = 50;
                    mags = mags - 1;
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
        }
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (bank != null) {
            bank.draw(graphics);
            graphics.drawString("Bullets" + bulletCount + "/" + mags, 300, 300);
        }
        if (robbers != null) {
            robbers.draw(graphics);
        }
        if (shoot != null) {
            for (Shooting shooting : shoot) {
                shooting.draw(graphics);
            }
        }
    }
}
