/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cops_and_robbers;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 *
 * @author WyattCampbell
 */
public class Bank {
    public Bank(int x, int y) {
        this.image = ResourceTools.loadImageFromResource("cops_and_robbers/Bank.png");
        this.x = x;
        this.y = y;
        
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }
    private Image image;
    private int x;
    private int y;
    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    public void moveLeft(){
        x = x - 1;
    }
    public void moveRight(){
        x = x + 1;
    }
}
