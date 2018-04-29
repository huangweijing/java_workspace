/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gizmodemo;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 *
 * @author huangweijing
 */
public class LineBlockObjectDemo 
        implements DemoInterface
{

    private Ball ball;
    private HeartLineBlock heart;
    private List<LineSegment> lines;
    
    public void init() {
        Geometry.setForesight(0.1);
        ball = new Ball(new Vect(30, 30), 5);
        ball.setV(new Vect(5,-1));
        lines = new ArrayList<LineSegment>();
        lines.add(new LineSegment(10, 10, 310, 10));
        lines.add(new LineSegment(10, 10, 10, 310));
        lines.add(new LineSegment(310, 310, 310, 10));
        lines.add(new LineSegment(310, 310, 10, 310));
        List<Vect>nodes = new ArrayList();
        nodes.add(new Vect(200,300));
        nodes.add(new Vect(200,200));
        nodes.add(new Vect(300,300));
        heart = new HeartLineBlock(new Vect(150,150));
    }

    public void drawDemo(Graphics2D g) {
        g.draw(ball.getShape().toEllipse2D());
        for(int i=0;i<lines.size();i++)
            g.draw(lines.get(i).toLine2D());
        heart.draw(g);
    }

    public void nextFrame() {
        ball.beForced(new Vect(0, 0.098));
        ball.nextFrame();
        overlapAndCollide();
    }

    public void overlapAndCollide() {
        heart.collisionDetectAndCollide(ball);
        for(int i=0;i<lines.size();i++){
            double time = Geometry.timeUntilWallCollision(lines.get(i),
                    ball.getShape(), 
                    ball.getV());
            if(time<1){
                Vect newV = Geometry.reflectWall(lines.get(i), ball.getV());
                ball.setV(newV);
            }
        }
    }

}
