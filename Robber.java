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

    public Robber(int x, int y, Image image) {
        this.image = ResourceTools.loadImageFromResource("images/White_Guard_HairBlonde_Standing.png");
        this.x = x;
        this.y = y;
        shoot = new ArrayList<>();
        this.angleRadians = 0;
    }
    
    

    
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
    
    
    public void moveHorizontal(int x) {
        setX(this.x + x);
    }

    public void moveVertical(int y) {
        setY(this.y + y);
    }

    //<editor-fold defaultstate="collapsed" desc="Properites">
    MouseEvent e;
    private ArrayList<Shoot> shoot;
    private double angleRadians;
    public int bulletCount = 25;
    public int mags = 5;
    private Image image;
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
}
//</editor-fold>
