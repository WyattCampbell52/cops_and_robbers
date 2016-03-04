/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cops_and_robbers;

import environment.Velocity;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
public class Robber {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        x = 0;
        y = 0;
        
        velocity = new Velocity(0, 0);
        angleRadians = 0;

        image = ResourceTools.loadImageFromResource("images/White_Guard_HairBlonde_Standing.png");
        
        bulletCount = DEFAULT_BULLET_COUNT;
        magCount = 5;
    }
    
    public Robber(int x, int y, double angleRadians) {
        this.x = x;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Drawing">
    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform olde = g2d.getTransform();
        
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angleRadians));
        at.setToRotation(getAngleRadians() -90, x + (image.getWidth(null) / 2), y + (image.getHeight(null) / 2));
        g2d.setTransform(at);
        g2d.drawImage(getImage(), x, y, null);
        graphics.drawRect(x, y, image.getWidth(null), image.getHeight(null));
        
        g2d.setTransform(olde);
        g2d.dispose();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Movement Methods">
    void move() {
        x += getVelocity().x;
        y += getVelocity().y;
    }
    
    void stop() {
        velocity.x = 0;
        velocity.y = 0;
    }
//</editor-fold>
        
//<editor-fold defaultstate="collapsed" desc="Other Methods">
    public boolean reload(){
        if (magCount > 0) {
            magCount--;
            bulletCount = DEFAULT_BULLET_COUNT;
            return true;
        }
        return false;
    }
    
    public void shoot(){
        
    }
//</editor-fold>
  
//<editor-fold defaultstate="collapsed" desc="Properites">
    public static final int DEFAULT_BULLET_COUNT = 25;
    public static final int MAX_BULLET_COUNT = 50;
    public static final int MIN_BULLET_COUNT = 0;
    
    private double angleRadians;
    public int bulletCount;
    public int magCount;
    private Image image;
    private Velocity velocity;
    private int x;
    private int y;

    public Point centreOfMass(){
        return new Point(x + (image.getWidth(null)/2), y + (image.getHeight(null)/2));
    }
    
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
    
    public Rectangle hitBox(){
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null)/8);
    }

    /**
     * @return the angleRadiansRadians
     */
    public double getAngleRadians() {
        return angleRadians;
    }

    /**
     * @param angleRadiansRadians the angleRadiansRadians to set
     */
    public void setAngleRadians(double angleRadiansRadians) {
        this.angleRadians = angleRadiansRadians;
    }

    /**
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * @param velocity the velocity to set
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }


}
//</editor-fold>
