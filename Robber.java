/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cops_and_robbers;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author WyattCampbell
 */
public class Robber {

    public Robber(int x, int y, Image image) {
        this.image = ResourceTools.loadImageFromResource("cops_and_robbers/Scars_Blue.png");
        this.x = x;
        this.y = y;
        shoot = new ArrayList<>();
        this.angleRadians = 0;
    }
    private ArrayList<Shooting> shoot;
    private double angleRadians;
    public int bulletCount = 25;
    public int mags = 5;
    private Image image;
    private int x;
    private int y;

    
    public Point centreOfMass(){
        return new Point(x + (image.getWidth(null)/2), y + (image.getHeight(null)/2));
    }
    
    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(), getX(), getY(), null);
        graphics.drawRect(x, y, image.getWidth(null), image.getHeight(null)/8);
    }

    public void moveHorizontal(int x) {
        setX(this.x + x);
    }

    public void moveVertical(int y) {
        setY(this.y + y);
    }

    //<editor-fold defaultstate="collapsed" desc="Properites">
    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

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
    public Rectangle rectangle(){
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null)/8);
    }
    public void reload(){
        bulletCount = 25;
        mags = mags - 1;
    }
    public void shoot(){
        
    }

    /**
     * @return the angleRadians
     */
    public double getAngleRadians() {
        return angleRadians;
    }

    /**
     * @param angleRadians the angleRadians to set
     */
    public void setAngleRadians(double angleRadians) {
        this.angleRadians = angleRadians;
    }
}
//</editor-fold>
