/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cops_and_robbers;

import audio.Playlist;
import audio.SoundManager;
import audio.Source;
import audio.Track;
import environment.Environment;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
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
    Projectile shoot;
    Robber robber;
    CrossHairs crossHairs;
    private ArrayList<Projectile> bullet;
    private String direction;
    private Point mousePosition;

    public Heist() {
        bank = new Bank();
        robber = new Robber(0, 0, 0.0);
//        bank = new Bank(0, 0);
        bullet = new ArrayList<>();
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosition = e.getPoint();
                repaint();
//                    System.out.println("Angle = " + TrigonometryCalculator.calculateAngle(robber.centreOfMass(), e.getPoint()) + " radians");
                robber.setAngleRadians(TrigonometryCalculator.calculateAngle(robber.centreOfMass(), mousePosition) + .75);
            }
        });

    }
    SoundManager soundManager;
    public static final String RELOAD = "Relaod";
    public static final String EMPTYCLIP = "Empty Clip";

    private void setUpSound() {
//        set up list of trackes in a playlist
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(RELOAD, Source.FILE, "/sounds/Reload.wav"));
        tracks.add(new Track(EMPTYCLIP, Source.FILE, "/sounds/Empty_Gun.wav"));

//        Playlist
        Playlist playlist = new Playlist(tracks);
//        pass the playlist to a sound manager
        soundManager = new SoundManager(playlist);
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        if (bullet != null) {
            for (Projectile projectile : bullet) {
                projectile.move();
            }
        }
        if (robber != null) {
//            robber.move(robber.getX(), robber.getY());
            robber.move();
        }
    }

    int robberSpeed = 2;

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
//            robber.move(-1,0);
            robber.setVelocity(new Velocity(-robberSpeed, 0));
            direction = "Left";
            System.out.println(robber.getX());
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
//            robber.move(1,0);
            robber.setVelocity(new Velocity(robberSpeed, 0));
            direction = "Right";
            System.out.println(robber.getX());

        } else if (e.getKeyCode() == KeyEvent.VK_W) {
//            robber.move(0,-1);
            robber.setVelocity(new Velocity(0, -robberSpeed));
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
//            robber.move(0,1);
            robber.setVelocity(new Velocity(0, robberSpeed));
        }
//        bulleting for now
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    mousePosition = e.getPoint();
                    repaint();
//                    System.out.println("Angle = " + TrigonometryCalculator.calculateAngle(robber.centreOfMass(), e.getPoint()) + " radians");
                    crossHairs = new CrossHairs(mousePosition);
                }
            });
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (robber.magCount > 0) {
                if (robber.bulletCount < 25) {
//                    soundManager.play(RELOAD, 1);
                    robber.reload();
                }
            }
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_A) || 
            (e.getKeyCode() == KeyEvent.VK_D) ||
            (e.getKeyCode() == KeyEvent.VK_W) ||
            (e.getKeyCode() == KeyEvent.VK_S)){
            robber.stop();
//            robber.setVelocity(new Velocity(0, 0));
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
//            Want to bullet with the mouse
        if (robber.bulletCount > 0) {
            System.out.println("shot");
            bullet.add(new Projectile(robber.centreOfMass(), TrigonometryCalculator.calculateVelocity(robber.centreOfMass(), mousePosition, 20), -robber.getAngleRadians()));
            robber.bulletCount = robber.bulletCount - 1;
        } else if (robber.bulletCount == 0) {
//            soundManager.play(EMPTYCLIP, 1);
        }
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (bank != null) {
            bank.draw(graphics);
            graphics.drawString("Bullets" + robber.bulletCount + "/" + robber.magCount, 300, 300);
        }
        if (crossHairs != null) {
            crossHairs.draw(graphics);
        }
        if (bullet != null) {
            for (Projectile bulleting : bullet) {
                bulleting.draw(graphics);
            }
        }
        if (robber != null) {
            robber.draw(graphics);
        }
    }
}
