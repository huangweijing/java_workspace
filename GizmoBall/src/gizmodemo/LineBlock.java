/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gizmodemo;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 *
 * @author Administrator
 */
public class LineBlock {

    protected List<LineSegment> lines;
    protected List<Circle> dots;
    protected List<Vect> nodes;
    protected Vect pos;
    protected Angle angle;

    /**构造函数，初始化相对端点，线段以及节点，并设置位置*/
    public LineBlock(List<Vect> _nodes, Vect _pos) {
        init(_nodes);
        setPos(_pos);
    }

    public Vect getPos(){
        return pos;
    }
    
    /**设置线性物体的位置*/
    public void setPos(Vect _pos) {
        pos = _pos;
        init(nodes);
        for (int i = 0; i < nodes.size(); i++) {
            Circle curCir = dots.get(i);
            LineSegment curLine = lines.get(i);
            dots.set(i, new Circle(curCir.getCenter().plus(pos),
                    curCir.getRadius()));
            lines.set(i, new LineSegment(curLine.p1().plus(pos),
                    curLine.p2().plus(pos)));
        }
    }

    /**初始化各节点和线段*/
    public void init(List<Vect> _nodes) {
        this.angle = Angle.ZERO;
        this.nodes = _nodes;
        this.lines = new ArrayList<LineSegment>();
        this.dots = new ArrayList<Circle>();
        for (int i = 0; i < nodes.size(); i++) {
            Vect curNode = nodes.get(i);
            Vect nextNode = (i == nodes.size() - 1) ? nodes.get(0) : nodes.get(i + 1);
            dots.add(new Circle(curNode, 2));
            lines.add(i, new LineSegment(curNode, nextNode));
        //System.out.println(i);
        }
    }

    /**计算到离自己最近的线段或节点的距离
     * 若发生碰撞，则会改变小球的运动方向
     * @return 离自己最近的线段或节点的距离
     */
    public double distanceCollideWithMe(Ball ball) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < lines.size(); i++) {
            //计算从小球圆心在直线上投影的点
            Vect perpPoint = Geometry.perpendicularPoint(
                    lines.get(i),
                    ball.getShape().getCenter());
            //如果该点存在，计算距离，并判断碰撞
            if (perpPoint != null) {
                double ldist = Geometry.distanceSquared(
                        perpPoint,
                        ball.getShape().getCenter());
                if (ldist < minDist) {
                    minDist = ldist;
                }
                if (ldist <= ball.getShape().getRadius() * 2) {
                    Vect newV = Geometry.reflectWall(lines.get(i), ball.getV());
                    ball.setV(newV);
                    break;
                }
            }
            //计算小球到节点的距离
            double cdist = Geometry.distanceSquared(
                    dots.get(i).getCenter(),
                    ball.getShape().getCenter());
            if (cdist < minDist) {
                minDist = cdist;
            }
            if (cdist <= ball.getShape().getRadius() * 2) {
                Vect newV = Geometry.reflectCircle(
                        dots.get(i).getCenter(),
                        ball.getShape().getCenter(),
                        ball.getV());
                ball.setV(newV);
                break;
            }
        }
        //返还最短的距离
        return minDist;
    }

    /**计算到离自己最近的线段或节点的时间
     * 若发生碰撞，则会改变小球的运动方向
     * @return 离自己最近的线段或节点的时间
     */
    public double timeUntilCollideWithMe(Ball ball) {
        //注释见distanceCollideWithMe
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
            if (time < 1) {
                Vect newV = Geometry.reflectCircle(dots.get(i).getCenter(), ball.getShape().getCenter(), ball.getV());
                ball.setV(newV);
                break;
            }
        }
        return minTime;
    }

    /**绘制该多边形*/
    public void draw(Graphics2D g) {
        for (int i = 0; i < lines.size(); i++) 
            g.draw(lines.get(i).toLine2D());
        
        for(int i=0;i<dots.size();i++)
            g.draw(dots.get(i).toEllipse2D());
    }
    
    public Angle getAngle(){
        return angle;
    }
    
    public void rotateAround(Vect center,Angle _angle){
        //init(nodes);
        setPos(pos);
        this.angle = _angle;
        for (int i = 0; i < lines.size(); i++) {
            LineSegment line = lines.get(i);
            line = Geometry.rotateAround(line, center, _angle);
            lines.set(i, line);
            Circle dot = dots.get(i);
            dot = Geometry.rotateAround(dot, center, _angle);
            dots.set(i, dot);
        }
    }
}
