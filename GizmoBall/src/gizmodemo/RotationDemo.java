/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gizmodemo;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import physics.Angle;
import physics.Vect;

/**
 *
 * @author Administrator
 */
public class RotationDemo 
        implements DemoInterface
{
    private List<Ball> balls;
    private List<BlockInterface>blocks;

    public void init() {
        balls = new ArrayList<Ball>();
        for(int i=0;i<10;i++){
            Ball curBall = new Ball(new Vect(20*i+250,20*i+250),i*2);
            curBall.beForced(new Vect(1,-1));
            balls.add(curBall);
        }
        blocks = new ArrayList<BlockInterface>();
        blocks.add(new HeartLineBlock(new Vect (350,350)));
        blocks.add(new WorldLineBlock(new Vect(250,250)));
        //blocks.add(new TriangleLineBlock(new Vect(450,450)));
        
    }

    public void drawDemo(Graphics2D g) {
        for(int i=0;i<balls.size();i++){
            g.draw(balls.get(i).getShape().toEllipse2D());
        }
        for(int i=0;i<blocks.size();i++)
            blocks.get(i).draw(g);
    }

    public void overlapAndCollide() {
        for(int i=0;i<balls.size();i++){
            for(int k=0;k<blocks.size();k++)
                blocks.get(k).collisionDetectAndCollide(balls.get(i));
            for(int j=i;j<balls.size();j++){
                if(balls.get(i).timeUntilCollide(balls.get(j))<1){
                    balls.get(i).collideWithAnotherBall(balls.get(j));
                }
            }
        }
    }

    public void nextFrame() {
        for(int i=0;i<balls.size();i++){
            balls.get(i).beForced(new Vect(0,0.098));
            balls.get(i).nextFrame();
        }
        System.out.println(blocks.get(1).getAngle());
        blocks.get(1).rotateAround(new Vect(400,400), blocks.get(1).getAngle().plus(
                new Angle(100,1)));
        overlapAndCollide();
    }
    
}
