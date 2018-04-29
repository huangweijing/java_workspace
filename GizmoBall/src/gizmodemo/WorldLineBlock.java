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
public class WorldLineBlock 
        implements BlockInterface
{
    protected Angle angle;
    protected LineBlock world;
    public WorldLineBlock(Vect _pos){
        angle = Angle.ZERO;
        List <Vect> nodes = new ArrayList<Vect>();
        nodes.add(new Vect(0,0));
        nodes.add(new Vect(300,0));
        nodes.add(new Vect(300,300));
        nodes.add(new Vect(0,300));
        this.world = new LineBlock(nodes,_pos);
    }
    public void draw(Graphics2D g){
        world.draw(g);
    }

    public double collisionDetectAndCollide(Ball ball) {
        return world.timeUntilCollideWithMe(ball);
    }

    public void rotateAround(Vect center, Angle _angle) {
        angle = _angle;
        world.rotateAround(center, _angle);
    }

    public Angle getAngle() {
        return angle;
    }
}
