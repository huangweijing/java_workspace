/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gizmodemo;

import java.awt.Graphics2D;
import physics.Circle;
import physics.Geometry;
import physics.Geometry.VectPair;
import physics.Vect;

/**
 *
 * @author huangweijing
 */
public class Ball {
    private Circle shape;
    private Vect velocity;
    public Ball(Vect _pos, double _r){
        shape = new Circle(_pos, _r);
        velocity = Vect.ZERO;
    }
    public Vect getPos(){
        return shape.getCenter();
    }
    public void setPos(Vect _pos){
        shape = new Circle(_pos, shape.getRadius());
    }
    public Vect getV(){
        return velocity;
    }
    public void setV(Vect _v){
        velocity = _v;
    }
    public Circle getShape()
    {
        return shape;
    }
    public void nextFrame(){
        Vect newPos = shape.getCenter().plus(velocity);
        this.setPos(newPos);
    }
    public void beForced(Vect f){
        velocity = velocity.plus(f);
    }
    public void draw(Graphics2D g){
        g.draw(this.shape.toEllipse2D());
    }
    public void collideWithAnotherBall(Ball another){
        VectPair pair = Geometry.reflectBalls(
                this.shape.getCenter(), 
                this.shape.getRadius(),
                this.velocity,
                another.shape.getCenter(),
                another.shape.getRadius(),
                another.getV());
        this.velocity = pair.v1;
        another.velocity = pair.v2;
    }
    public double timeUntilCollide(Ball another){
        double time = Geometry.timeUntilBallBallCollision(
                this.shape, 
                this.velocity,
                another.getShape(), 
                another.getV());
        return time;
    }
}
