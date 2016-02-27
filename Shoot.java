/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cops_and_robbers;

import environment.Velocity;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.RescaleOp;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
public class Shoot {
Robber robber;
    public Shoot(int x, int y, double angularVelocity, double angle) {
        image = ResourceTools.loadImageFromResource("images/Bullet.png");
        this.x = x;
        this.y = y;
        this.velocity = new Velocity(10, 10);
        this.angularVelocity = angularVelocity;
        this.angleRadians = angle;

        this.startTime = System.currentTimeMillis();
    }

    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform olde = g2d.getTransform();

        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angleRadians));
        at.setToRotation(angleRadians, x + (image.getWidth(null) / 2), y + (image.getHeight(null) / 2));
        g2d.setTransform(at);
        g2d.drawImage(image, x, y, null);
        
        graphics.setColor(Color.red);
        graphics.drawRect(x, y, image.getWidth(null), image.getHeight(null));

    }

    public Rectangle rectangle() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

//<editor-fold defaultstate="collapsed" desc="Properties">    
    private int x;
    private int y;

    private int maxX;
    private int minX;
    private int maxY;
    private int minY;

    private Velocity velocity;
    private int speed;

    private double angularVelocity;
    private double angleRadians;
    private int rotationSpeed = 5;

    private Image image;

    private long startTime;
    private long maxDurationMillis = 2000;
    private boolean alive = true;

    public Point centreOfMass(){
        return new Point(x + (image.getWidth(null)/2), y + (image.getHeight(null)/2));
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

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;

        velocity = TrigonometryCalculator.getVelocity(Math.toRadians((angleRadians) % 360), speed);
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
     * @return the angularVelocity
     */
    public double getAngularVelocity() {
        return angularVelocity;
    }

    /**
     * @param angularVelocity the angularVelocity to set
     */
    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    /**
     * @return the angle
     */
    public double getAngle() {
        return angleRadians;
    }

    /**
     * @return the angle in radians
     */
    public double getAngleInRadians() {
        return Math.toRadians(angleRadians);
    }

    /**
     * @param angle the angle to set
     */
    public void setAngleRadians(double angleRadiansRadians) {
        this.angleRadians = angleRadiansRadians;
    }

    void rotate(int rotationSpeed) {
        angleRadians = (angleRadians + rotationSpeed) % 360;
    }

    void accelerate(int velocityChange) {
        setSpeed(speed + 10);
    }

    void move() {
        x -= velocity.x;
        y -= velocity.y;

        checkLifeTime();
    }

    public boolean checkLifeTime() {
        alive = ((System.currentTimeMillis() - startTime) < this.maxDurationMillis);
        return alive;
    }

    public void boundries() {
        if (x > 900) {
            x = -50;
        } else if (x < -50) {
            x = 900;
        }
        if (y > 580) {
            y = -100;
        } else if (y < -100) {
            y = 580;
        }

    }

    /**
     * @return the maxX
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * @param maxX the maxX to set
     */
    public void setMaxX(int maxX) {
        maxX = 900;
        this.maxX = maxX;
    }

    /**
     * @return the minX
     */
    public int getMinX() {
        return minX;
    }

    /**
     * @param minX the minX to set
     */
    public void setMinX(int minX) {
        minX = -50;
        this.minX = minX;
    }

    /**
     * @return the maxY
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * @param maxY the maxY to set
     */
    public void setMaxY(int maxY) {
        maxY = 550;
        this.maxY = maxY;
    }

    /**
     * @return the minY
     */
    public int getMinY() {
        return minY;
    }

    /**
     * @param minY the minY to set
     */
    public void setMinY(int minY) {
        minY = -100;
        this.minY = minY;
    }

    /**
     * @return the rotationSpeed
     */
    public int getRotationSpeed() {
        return rotationSpeed;
    }

    /**
     * @param rotationSpeed the rotationSpeed to set
     */
    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
    //</editor-fold>

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}

//</editor-fold>
