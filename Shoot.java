/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cops_and_robbers;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 *
 * @author WyattCampbell
 */
public class Shoot {
    public Shoot(int x, int y){
        this.x = x;
        this.y = y;
        this.image = ResourceTools.loadImageFromResource("cops_and_robbers/Bullet.png");
        this.angleRadians = 0;

    }
    public void draw(Graphics graphics){
Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform olde = g2d.getTransform();

        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angleRadians));
        at.setToRotation(getAngleRadians() -90, x + (image.getWidth(null) / 2), y + (image.getHeight(null) / 2));
        g2d.setTransform(at);
        g2d.drawImage(getImage(), x, y, null);
        graphics.drawRect(x, y, image.getWidth(null), image.getHeight(null));

        g2d.setTransform(olde);
        g2d.dispose();    }
    public void move(int x){
        this.x = this.x + x;
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    private int x;
    private int y;
    private double angleRadians;
    private Image image;
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
    public Point centreOfMass(){
        return new Point(x + (image.getWidth(null)/2), y + (image.getHeight(null)/2));
    }
}
//</editor-fold>
