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
public class TriangleLineBlock 
        implements BlockInterface
{
    protected LineBlock triangle;
    private Angle angle;
    public TriangleLineBlock(Vect _pos){
        List<Vect> nodes = new ArrayList<Vect>();
        nodes.add(new Vect(0,0));
        nodes.add(new Vect(100,0));
        nodes.add(new Vect(100,100));
        triangle = new LineBlock(nodes,_pos);
    }
    public void draw(Graphics2D g) {
        triangle.draw(g);
    }

    public double collisionDetectAndCollide(Ball ball) {
        return triangle.timeUntilCollideWithMe(ball);
    }

    public void rotateAround(Vect center, Angle _angle) {
        angle = _angle;
        triangle.rotateAround(center, _angle);
    }

    public Angle getAngle() {
        return angle;
    }

}
