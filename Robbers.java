/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cops_and_robbers;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author WyattCampbell
 */
public class Robbers {
    public Robbers(int x , int y, Image image){
        this.image = ResourceTools.loadImageFromResource("cops_and_robbers/Robber_Still.png");
        this.x = x;
        this.y = y;
    }
    private Image image;
    private int x;
    private int y;
    
    public void draw(Graphics graphics){
        graphics.drawImage(getImage(), getX(), getY(), null);
    }
    
    public void moveHorizontal(int x){
        this.x = this.x + x;
    }
    
    public void moveVertical(int y){
        this.y = this.y + y;
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
    
}
//</editor-fold>
