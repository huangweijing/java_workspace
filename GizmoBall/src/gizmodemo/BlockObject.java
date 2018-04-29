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
public class BlockObject {

    private List<LineSegment> lines;
    private List<Circle> dots;

    public BlockObject(Vect _pos) {
        init();
        setPos(_pos);
    }

    public void init() {
        lines = new ArrayList<LineSegment>();
        lines.add(new LineSegment(0, 40, 20, 0));
        lines.add(new LineSegment(20, 0, 40, 0));
        lines.add(new LineSegment(40, 0, 50, 20));
        lines.add(new LineSegment(60, 0, 50, 20));
        lines.add(new LineSegment(60, 0, 80, 0));
        lines.add(new LineSegment(80, 0, 100, 40));
        lines.add(new LineSegment(100, 40, 50, 100));
        lines.add(new LineSegment(50, 100, 0, 40));
        dots = new ArrayList<Circle>();
        dots.add(new Circle(0, 40, 2));
        dots.add(new Circle(20, 0, 2));
        dots.add(new Circle(40, 0, 2));
        dots.add(new Circle(50, 20, 2));
        dots.add(new Circle(60, 0, 2));
        dots.add(new Circle(80, 0, 2));
        dots.add(new Circle(100, 40, 2));
        dots.add(new Circle(50, 100, 2));
    }

    public void setPos(Vect _pos) {
        for (int i = 0; i < lines.size(); i++) {
            LineSegment curLine = lines.get(i);
            curLine = new LineSegment(curLine.p1().plus(_pos), curLine.p2().plus(_pos));
            lines.set(i, curLine);
        }
        for (int i = 0; i < dots.size(); i++) {
            Circle curCircle = dots.get(i);
            curCircle = new Circle(
                    curCircle.getCenter().plus(_pos),curCircle.getRadius());
            dots.set(i, curCircle);
        }
    }

    public double timeUntilCollideWithMe(Ball ball) {
        double minTime = Double.POSITIVE_INFINITY;
        for (int i = 0; i < lines.size(); i++) {
            double time = Geometry.timeUntilWallCollision(lines.get(i),
                    ball.getShape(),
                    ball.getV());
            if (time < minTime) {
                minTime = time;
            }
            if (time < 1) {
                Vect newV = Geometry.reflectWall(lines.get(i), ball.getV());
                ball.setV(newV);
                break;
            }
        }
        for (int i = 0; i < dots.size(); i++) {
            double time = Geometry.timeUntilCircleCollision(dots.get(i),
                    ball.getShape(),
                    ball.getV());
            if (time < minTime) {
                minTime = time;
            }
            if (time < 0.5) {
                Vect newV = Geometry.reflectCircle(dots.get(i).getCenter()
                        ,ball.getShape().getCenter(), ball.getV());
                ball.setV(newV);
                break;
            }
        }

        return minTime;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < lines.size(); i++) {
            g.draw(lines.get(i).toLine2D());
        }
    }
}
