/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gizmodemo;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import physics.Vect;

/**
 *
 * @author Administrator
 */
public class MultBallDemo 
        implements DemoInterface
{
    private List<Ball> balls;
    private WorldLineBlock world;
    private HeartLineBlock heart;
    public void init() {
        balls = new ArrayList<Ball>();
        for(int i=0;i<10;i++){
            Ball curBall = new Ball(new Vect(20*i,20*i),i*2);
            curBall.beForced(new Vect(1,-1));
            balls.add(curBall);
        }
        heart = new HeartLineBlock(new Vect (105,105));
        world = new WorldLineBlock(new Vect(5,5));
    }

    public void drawDemo(Graphics2D g) {
        for(int i=0;i<balls.size();i++){
            g.draw(balls.get(i).getShape().toEllipse2D());
        }
        world.draw(g);
        heart.draw(g);
    }

    public void overlapAndCollide() {
        for(int i=0;i<balls.size();i++){
            world.collisionDetectAndCollide(balls.get(i));
            heart.collisionDetectAndCollide(balls.get(i));
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
        overlapAndCollide();
    }

}
