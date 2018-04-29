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
public class Flipper
        implements BlockInterface {

    private LineBlock flipper;
    private Angle angle;
    private int state;

    public Flipper(Vect _pos) {
        List<Vect> nodes = new ArrayList<Vect>();
        nodes.add(new Vect(10,0));
        nodes.add(new Vect(0,20));
        nodes.add(new Vect(10,50));
        nodes.add(new Vect(20,10));
//        nodes.add(new Vect(0, 10));
//        nodes.add(new Vect(0, 20));
//        nodes.add(new Vect(10, 30));
//        nodes.add(new Vect(20, 30));
//        nodes.add(new Vect(70, 20));
//        nodes.add(new Vect(50, 10));
//        nodes.add(new Vect(20, 0));
//        nodes.add(new Vect(10, 0));
        flipper = new LineBlock(nodes, _pos);
        angle = Angle.ZERO;
        state = 0;
    }

    public void draw(Graphics2D g) {
        flipper.draw(g);
    }

    public double collisionDetectAndCollide(Ball ball) {
        return flipper.timeUntilCollideWithMe(ball);
    }
    
    public void flipMe(){
        if (state == 0) {
            state = 1;
        }
    }

    public void fliping() {
        //System.out.println(angle.radians());
            if (state == 1) {
            angle = angle.plus(new Angle(10, 1));
            flipper.rotateAround(flipper.getPos().plus(new Vect(15, 15)), angle);
            if (angle.compareTo(Angle.DEG_90) > 0) {
                state = -1;
            }
        } else if (state == -1) {
            angle = angle.minus(new Angle(10, 1));
            flipper.rotateAround(flipper.getPos().plus(new Vect(15, 15)), angle);
            if (angle.compareTo(Angle.DEG_225) > 0) {
                state = 0;
            }

        }
    }

    public void rotateAround(Vect center, Angle _angle) {
        angle = angle.plus(_angle);
        flipper.rotateAround(flipper.getPos().plus(new Vect(15, 15)), angle);
    }

    public Angle getAngle() {
        return angle;
    }
}
