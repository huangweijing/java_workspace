/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gizmodemo;

import java.awt.Graphics2D;
import physics.Angle;
import physics.Vect;

/**
 *
 * @author Administrator
 */
public interface BlockInterface {

    /**绘制当前的Block*/
    public void draw(Graphics2D g);

    /**检测和处理与小球的碰撞*/
    public double collisionDetectAndCollide(Ball ball);
    
    /***/
    public void rotateAround(Vect center, Angle _angle);
    
    public Angle getAngle();
}
