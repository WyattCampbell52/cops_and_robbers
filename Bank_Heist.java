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
class Bank_Heist extends Environment {

    Bank bank;
    Robbers robbers;
    private ArrayList<Shooting> shoot;
    private String direction;

    public Bank_Heist() {
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
                shooting.move(50);
            }
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            robbers.moveHorizontal(-10);
            direction = "Left";
            System.out.println(robbers.getX());
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            robbers.moveHorizontal(10);
            direction = "Right";
            System.out.println(robbers.getX());

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            robbers.moveVertical(10);
            System.out.println(robbers.getY());

        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            robbers.moveVertical(-10);
            System.out.println(robbers.getY());
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("shot");
            shoot.add(new Shooting(robbers.getX(), robbers.getY()));
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        if (true) {

        }
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (bank != null) {
            bank.draw(graphics);
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
