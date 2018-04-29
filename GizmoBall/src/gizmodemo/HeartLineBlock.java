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
public class HeartLineBlock 
        implements BlockInterface
{
    protected Angle angle;
    protected LineBlock heart;
    public HeartLineBlock(Vect _pos){
        List <Vect> nodes = new ArrayList<Vect>();
        nodes.add(new Vect(0,40));
        nodes.add(new Vect(20,0));
        nodes.add(new Vect(40,0));
        nodes.add(new Vect(50,20));
        nodes.add(new Vect(60,0));
        nodes.add(new Vect(80,0));
        nodes.add(new Vect(100,40));
        nodes.add(new Vect(50,100));
        heart = new LineBlock(nodes,_pos);
        angle = Angle.ZERO;
    }
    public void draw(Graphics2D g){
        heart.draw(g);
    }
    public double collisionDetectAndCollide(Ball ball){
        return heart.timeUntilCollideWithMe(ball);
    }
    public void rotateAround(Vect center, Angle _angle){
        angle = _angle;
        heart.rotateAround(center, _angle);
    }
    public Angle getAngle(){
        return angle;
    }
}
