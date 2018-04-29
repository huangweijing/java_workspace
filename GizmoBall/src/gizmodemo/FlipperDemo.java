/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gizmodemo;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import physics.Geometry;
import physics.Vect;

/**
 *
 * @author Administrator
 */
public class FlipperDemo implements DemoInterface{
    Flipper f;
    WorldLineBlock world;
    Ball ball;
    public FlipperDemo(JPanel panel){
        this.addKeyListener(panel);
    }
    public void init() {
        Geometry.setForesight(0.1);
        world = new WorldLineBlock(new Vect(10,10));
        ball = new Ball(new Vect(30, 30), 5);
        ball.beForced(new Vect(1,1));
        f=new Flipper(new Vect(100,100));
    }

    public void addKeyListener(JPanel panel){
        panel.addKeyListener(new KeyListener(){

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                f.flipMe();
            }

            public void keyReleased(KeyEvent e) {
            }
            
        });
        panel.setFocusable(true);
    }
    
    public void drawDemo(Graphics2D g) {
        world.draw(g);
        f.draw(g);
        ball.draw(g);
    }

    public void overlapAndCollide() {
        world.collisionDetectAndCollide(ball);
        
    }

    public void nextFrame() {
        f.fliping();
        f.collisionDetectAndCollide(ball);
        ball.beForced(new Vect(0,0.098));
        ball.nextFrame();
        this.overlapAndCollide();
    }

}
