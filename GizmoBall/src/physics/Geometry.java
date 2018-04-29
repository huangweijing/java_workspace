package physics;

import java.lang.Double; // import statement added to mollify javadoc
import java.util.Iterator;
import java.io.Serializable;

/****************************************************************************
 *授权书已在Angle.java中翻译，且对代码无所贡献，在此不再翻译
 * Copyright (C) 1999-2001 by the Massachusetts Institute of Technology,
 *                       Cambridge, Massachusetts.
 *
 *                        All Rights Reserved
 *
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for any purpose and without fee is hereby
 * granted, provided that the above copyright notice appear in all
 * copies and that both that copyright notice and this permission
 * notice appear in supporting documentation, and that MIT's name not
 * be used in advertising or publicity pertaining to distribution of
 * the software without specific, written prior permission.
 *  
 * THE MASSACHUSETTS INSTITUTE OF TECHNOLOGY DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS.  IN NO EVENT SHALL THE MASSACHUSETTS
 * INSTITUTE OF TECHNOLOGY BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 *
 * @author: Matt Frank, MIT Laboratory for Computer Science,
 *          mfrank@lcs.mit.edu
 *          1999-Apr-03
 *
 * @author: Rob Pinder, Phil Sarin, Lik Mui
 *          Spring 2000
 *          Exception handling and argument type refinemnt
 *
 * @author: Jeffrey Sheldon (jeffshel@mit.edu)
 *          Fall 2000, Spring 2001
 *          Major rewrites and improvements to iterative solving
 *
 * @author: Jeremy Nimmer (jwnimmer@alum.mit.edu)
 *          Fall 2000, Spring 2001
 *          Editorial role (testing and specification editing)
 *
 * Version: $Id: Geometry.java,v 1.1 2005/03/18 18:31:49 joy Exp $
 *
 ***************************************************************************/

/**
 * The Geometry library contains procedural abstractions which are useful
 * in modeling the physical interactions between objects.
 * 几何学库中包括程序上的抽象类，他们对对象间的物理交互的建模很有帮助。
 *
 * <p>The library is described in terms of these concepts:
 * 库是由一下方面的概念来描述的
 * <br><ul>
 * <li> object  - a ball or a bouncer 一个球或一个bouncer
 * <li> ball    - a circle with position and velocity 一个有速率和位置的圆
 * <li> bouncer - a line segment or circle with position and angular velocity 
 *  一个有位置和角速度的线段或圆
 * </ul>
 *
 * <p>
 * The intended use of the Geometry library is as follows:
 * 几何库的用法有以下几点
 *
 * <p><ol><li>
 * The client calls the timeUntilCollision() methods to calculate the
 * times at which the ball(s) will collide with each of the bouncers
 * or with another ball.
 * The minimum of all these times (call it "mintime") is the
 * time of the next collision.
 * 用户调用timeUntilCollision（）方法来计算球与bouncer或球和球之间碰撞的时间
 * 这些时间中的最小值是下次碰撞的时间
 *
 * <li>
 * The client updates the position of the ball(s) and the bouncers to
 * account for mintime passing.  At this point, the ball and the object
 * it is about to hit are exactly adjacent to one another.
 * 用户更新球和bouncers的位置来计算通过的最小时间。从这一点来说，球和它去碰撞的对象
 * 彼此之间就非常接近了。
 *
 * <li>
 * The client calls the appropriate reflect() method to calculate the
 * change in the ball's velocity.
 *用户调用适当的reflect（）方法来计算球转向的角度
 * <li>The client updates the ball's velocity and repeats back to step 1.
 *    用户更新球的角度并回到第一步
 * </ol>
 *
 * <p><a name="constant_velocity"></a>
 *
 * <p>The timeUntilCollision() methods assume constant ball velocity.
 * That is, no force will be acting on the ball, so it will follow a
 * straight-line path.  Therefore, if external forces (such as gravity
 * or friction) need to be accounted for, the client must do so before
 * or after the of the "time until / update position / reflect" series
 * of steps - never inbetween those three steps.
 * 
 * timeUntilCollision（）方法假设球的角度不变。
 * 就是说，没有外力施加在球上，所以球将直线下落。
 * 因此，如果要将外力计算入内，客户必须在"time until / update position / reflect"
 * 等一系列动作之前或之后做这件事（计算外力），不要插在这三步之间。
 * <p><a name="endpoint_effects"></a>
 *
 * <b>Important note</b>:
 * The methods which deal with line segment bouncers do NOT deal with
 * the end-points of the segment.  To ensure realistic behavior, shapes
 * should be constructed from a combination of line segments with
 * zero-radius circles at the end points.
 *
 * <p>
 * For example: A ball is located at (0,0) and is moving in the
 * (1,1) direction towards two line segments; one segments spans the
 * points (1,1),(1,2) and the other spans (1,1),(2,1).
 * The ball will hit the ends of both line segments at a 45 degree angle and
 * something REALLY WEIRD will happen.  However, if a circle with zero radius
 * is placed at (1,1) then the ball will bounce off the circle in the
 * expected manner.
 * 举个例子来说：一个球位于（0，0）以向着（1，1）方位的两条线段移动；一条线段是
 * （1，1）（1，2），另一条线段是（1，1）（2，1）。
 * 球会以45°角与两条线段碰撞，一些奇怪的事就会发生。但是，如果半径为零的一个圆处于（1，1）
 * 球就会以预期的效果和圆碰撞。
 **/
public class Geometry {

  // nobody should be constructing a "Geometry"
  private Geometry() {
  }

  private static GeometryInterface geometry = new GeometryImpl();

  /**
   * @param impl the object to be used as the singleton
   * 
   * @effects changes which implementation of
   * <code>GeometryInterface</code> will be used to service the static
   * methods of this class.  Most users will prefer to use
   * <code>setForesight</code> or <code>setTuningParameters</code>
   * instead.
   * 运行会改变为这个类中的静态方法服务的<code>GeometryInterface</code>的执行。
   * 多数用户更多地使用<code>setForesight</code> or <code>setTuningParameters</code>来代替。
   * @see #setForesight
   * @see #setTuningParameters
   **/
  public static void setGeometry(GeometryInterface impl) {
    if (impl == null) { 
      throw new IllegalArgumentException();
    } 
    geometry = impl;
  }

  /**
   * Modifies the behavior of this class to use the specified 
   * <code>maximumForesight</code> and <code>numberOfSlices</code>.  If
   * <code>useDoughnut</code> is true then doughnut optimizations are
   * enabled.  The values used by default are &lt;+Inf, true, 15&gt;.
   * Many uses may prefer to simply use <code>setForesight</code>
   * instead.
   * 修改这个类的行为以使用特定的<code>maximumForesight</code> and <code>numberOfSlices</code>. 
   * 如果code>useDoughnut</code> 为真，则激活圆环优化。
   * 默认值为 &lt;+Inf, true, 15&gt;.
   * 许多用户更喜欢用<code>setForesight</code>替代。
   *
   * @param maximumForesight The maximal time in the future that a
   * collision will be searched for.  Collisions may still be returned
   * that happen farther than <code>maximumForesight</code> in the
   * future, but no extra effort will be made to find them.  If set to
   * +Infinity, <code>useDoughnut</code> must also be true.
   * maximumForesight ：碰撞所用时间的最大值。碰撞还会在<code>maximumForesight</code> 
   * 之前发生，但不再花费余力找到他们。如果设置为+Infinity，<code>useDoughnut</code>
   * 一定也会为真。
   *
   * @param useDoughnut When true, the timeUntilRotating* methods
   * perform extra calculations to reduce the time during which
   * collisions are searched for.  If maximumForesight is small, it is
   * sometimes quicker to skip these additional checks.  Must be true
   * if maximumForesight is +Infinity.
   * 当useDoughnut 为真，timeUntilRotating方法要进行额外的计算以减少寻找碰撞的市静安。
   * 如果maximumForesight 小，有时候能比较快地跳出三步检查。
   * 
   * @param numberOfSlices The number of slices that the time being
   * searched for a possible collision is divided into.  Since some
   * methods (noteably timeUntilRotating*) cannot use closed form
   * formula, they must search for possible collisions over some time
   * frame.  Increasing the size of this will decrease the likelihood
   * of one of the timeUntilRotating* methods missing a collision, but
   * will also cause them to run slower.
   * numberOfSlices ：查询可能性碰撞的时间被分成几个片段。因为一些方法不适用于少数规则。
   * 他们必须在一个时间框内查找可能的碰撞。加大它的范围会减少timeUntilRotating方法中错失碰撞的可能性。
   * 但是也会引起运行速度减慢。
   *
   * @see #setForesight
   * @see Double#POSITIVE_INFINITY
   **/
  public static void setTuningParameters(double maximumForesight,
					 boolean useDoughnut,
					 int numberOfSlices) {
    if (useDoughnut) {
      setGeometry(new GeometryImpl(maximumForesight, numberOfSlices));
    } else {
      setGeometry(new SimpleGeometry(maximumForesight, numberOfSlices));
    }
  }

  /**
   * Modifies the behavior of this class to use the specified
   * <code>maximumForesight</code>.
   *
   * @param maximumForesight The maximal time in the future that a
   * collision will be searched for.  Collisions may still be returned
   * that happen farther than <code>maximumForesight</code> in the
   * future, but no extra effort will be made to find them.
   * maximumForesight ：碰撞所用时间的最大值。碰撞还会在<code>maximumForesight</code> 
   * 之前发生，但不再花费余力找到他们。
   * 
   * @see Double#POSITIVE_INFINITY
   **/
  public static void setForesight(double maximumForesight) {
    if (maximumForesight <= 0.1) {
      setGeometry(new SimpleGeometry(maximumForesight, 15));
    } else {
      setGeometry(new GeometryImpl(maximumForesight, 15));
    }
  }

  /**
   * <code>DoublePair</code> is a simple immutable record type representing
   * a pair of <code>double</code>s.
   * <code>DoublePair</code> 是一个简单的不变的记录类型，描述一对<code>double</code>
   **/
  public static class DoublePair
    implements Serializable
  {
    public final double d1;
    public final double d2;

    /**
     * Creates a DoublePair with <code>d1</code> and
     * <code>d2</code> as given
     * 用已给的<code>d1</code> and <code>d2</code> 生成一个 DoublePair 
     **/
    public DoublePair (double d1, double d2) {
      this.d1 = d1;
      this.d2 = d2;
    }

    /**
     * Creates a DoublePair with <code>d1</code> and
     * <code>d2</code> both set to the given argumen
     * 用已给的<code>d1</code> and <code>d2</code> 生成一个 DoublePair
     * 设置给定的参数 
     **/
    public DoublePair (double both) {
      this(both, both);
    }

    public boolean areFinite() {
      return !Double.isInfinite(d1) && !Double.isInfinite(d2) &&
	!Double.isNaN(d1) && !Double.isNaN(d2);
    }

    public String toString() {
      return "[" + d1 + "," + d2 + "]";
    }

    public boolean equals(Object o) {
      return (o instanceof DoublePair) && equals((DoublePair) o);
    }

    public boolean equals(DoublePair p) {
      if (p == null) return false;
      return (d1 == p.d1) && (d2 == p.d2);
    }

    public int hashCode() {
      return (new Double(d1)).hashCode() + (new Double(d2)).hashCode();
    }
  }

  /**
   * <code>VectPair</code> is a simple immutable record type representing
   * a pair of <code>Vect</code>s.
   * <code>VectPair</code> 是一个简单的不变的记录类型，描述一对<code>Vect</code>
   * @see Vect
   **/
  public static class VectPair
    implements Serializable
  {
    public final Vect v1;
    public final Vect v2;

    /**
     * Creates a VectPair with <code>v1</code> and
     * <code>v2</code> as given
     * 用已给的<code>v1</code> and <code>v2</code> 生成一个 VectPair 
     **/
    public VectPair(Vect v1, Vect v2) {
      this.v1 = v1;
      this.v2 = v2;
    }

    public String toString() {
      return "[" + v1 + "," + v2 + "]";
    }

    public boolean equals(Object o) {
      return (o instanceof VectPair) && equals((VectPair) o);
    }

    public boolean equals(VectPair p) {
      if (p == null) return false;
      return
	((v1 == null) ? (p.v1 == null) : v1.equals(p.v1)) &&
	((v2 == null) ? (p.v2 == null) : v2.equals(p.v2));
    }

    public int hashCode() {
      return
	((v1 == null) ? 0 : (3 * v1.hashCode())) +
	((v2 == null) ? 0 : (7 * v2.hashCode()));
    }
  }

  /**
   * DoublePair with both <code>d1</code> and <code>d2</code>
   * set to <code>Double.NaN</code>
   * @see java.lang.Double#NaN
   **/
  public static final DoublePair DOUBLE_PAIR_NAN =
    new DoublePair(Double.NaN);

  /**
   * Solves the quadratic equation.
   * 解二次方程
   * @return a pair containing the roots of the equation
   * 返还一对方程的根
   *   a*x<sup>2</sup> + b*x + c = 0 with the lesser of the two roots返还根中较小的那个
   *   in <code>result.d1</code>.  If no real roots exist, the
   *   returned pair will contain <code>NaN</code> for both values.
   *   如果存在实根返还的一对中会包括<code>NaN</code> 的两个值。
   *
   * @see java.lang.Double#NaN
   **/
  public static DoublePair quadraticSolution(double a, double b, double c) {
    return geometry.quadraticSolution(a, b, c);
  }


  /**
   * Solves the quadratic equation.
   * 解二次方程
   * @return the lesser of the two roots of the quadratic返还根中较小的那个
   * equation specified by a*x<sup>2</sup> + b*x + c = 0, or
   * <code>NaN</code> if no real roots exist.
   * 如果不存在实根用a*x<sup>2</sup> + b*x + c = 0或code>NaN</code>来解
   * @see java.lang.Double#NaN
   **/
  public static double minQuadraticSolution(double a,
					    double b,
					    double c) {
    return geometry.minQuadraticSolution(a, b, c);
  }


  /***************************************************************************
   *
   * METHODS FOR LINE SEGMENTS 线段的方法
   *
   * Suppose we have a line running through the points <x,y> and <w,z>.
   * 假设有一条从<x,y> 到<w,z>.的线段。
   * 还有一个点<a,b>.我们要找粗点到线段的距离。用这个方法可以找到点到线上所有点之间的最小距离。
   * And we have a point <a,b>.  We'd like to find the distance from the
   * point to the line.  We can calculate this by finding the minimum
   * distance between the point and all points on the line. 
   * (Write the line as a function of s: j[s] = x + (w-x)s, k[s] = y + (z-y)s,线段的函数
   * then write the distance squared as a function of s: 距离是以上函数的开方
   *     (a - j[s])^2 + (b - k[s])^2
   * Take the derivative with respect to s and set it equal 0.  使其等于零The
   * result is that the distance squared between the point and the line
   * is: 结果就是点到线段的距离
   *
   * (b(x-w) - a(y-z) + (w y - x z))^2 / ((x-w)^2 + (y-z)^2)
   *
   *
   * Furthermore, the point on the line that is perpendicular to the
   * point is given by:
   * 另外，与这个点形成垂线的线上的点
   * minS = ((w-x)(a-x) + (z-y)(b-y)) / ((w-x)^2 + (y-z)^2)
   * minX = j[minS], minY = k[minS]
   *
   * Okay, now assume that the point is moving.  a[t] = u t + c,
   * b[t] = v t + d.  We want to find the time, t, at which the distance
   * between the point and the line will be exactly "r".
   * 好，现在假设点在移动，我们想要找到时间。点到线段的距离就是r.
   * Then the numerator of the previous expression will be a quadratic
   * expression of the variable t, with At^2 + Bt + C where
   * 前面表达式中的分子作为二次方程中的变量t
   * F = (v(x-w) - u(y-z))
   * G = (d(x-w) - c(y-z) + (w y - x z))
   * H = ((x-w)^2 + (y-z)^2)
   *
   * A = F^2
   * B = 2 F G
   * C = G^2
   *
   * So to find the answer we let:为了找到答案我们令
   * Cprime = C - r^2 H
   *
   * and finally:
   *
   * t = (-B +/- Sqrt(B^2 - 4 A Cprime)) / (2 A)
   *
   ***************************************************************************/


  /**
   * Returns the point on <code>line</code> which forms a line with
   * <code>point</code> that is perpendicular to <code>line</code>.
   * 返还直线上可以与给定点形成直线的垂线的点
   * @requires <code>line</code> has non-zero length 
   * 给定的直线长度非零
   * @return the point on <code>line</code> which forms a line with
   * <code>point</code> that is perpendicular to <code>line</code>, or
   * <code>null</code> if no such point exists within the given line
   * segment.
   * 返还直线上可以与给定点形成直线的垂线的点，如果没有这样的点返还<code>null</code> 
   * @see #perpendicularPointWholeLine(LineSegment, Vect)
   **/
  static public Vect perpendicularPoint(LineSegment line,
                                        Vect point) {
    return geometry.perpendicularPoint(line, point);
  }

  /**
   * Returns the point on the infinitly long line represented by
   * <code>line</code> which forms a line with <code>point</code> that
   * is perpendicular to <code>line</code>.
   * 返还过这个点并垂直于给定直线的垂线
   * @requires <code>line</code> has non-zero length
   *
   * @return the point on the infinitly long line represented by
   * <code>line</code> which forms a line with <code>point</code> that
   * is perpendicular to <code>line</code>, or <code>null</code> if no
   * such point exists within the given line segment.
   * 如果没有这样的点返还<code>null</code> 
   * @see #perpendicularPoint(LineSegment, Vect)
   **/
  public static Vect perpendicularPointWholeLine(LineSegment line,
						 Vect point) {
    return geometry.perpendicularPointWholeLine(line, point);
  }

  /**
   * Accounts for the effects of inelastic collisions given the intial
   * and resulting velocities of the collision assuming elasticity.
   * 由于无弹性碰撞在初始时也给的是弹性碰撞的速率
   * @requires <code>rCoeff</code> >= 0
   *
   * @effects given an initial velocity, <code>incidentVect</code>,
   * and the velocity resulting from an elastic collision,
   * <code>reflectedVect</code>, and a reflection coefficient,
   * <code>rCoeff</code>, returns the resulting velocity of the
   * collision had it been inelastic with the given reflection
   * coefficient.  If the reflection coefficient is 1.0, the resulting
   * velocity will be equal to <code>reflectedVect</code>.  A
   * reflection coefficient of 0 implies that the collision will
   * absorb any energy that was reflected in the elastic case.
   * 给定一个初始的速率，<code>incidentVect</code>,和弹性碰撞后的速率
   * <code>reflectedVect</code>, 反射系数，* <code>rCoeff</code>, 
   * 返还一个碰撞的速率结果，
   * 如果反射系数是1.0，速率结果等于code>reflectedVect</code
   * 反射系数为零说明碰撞会吸收任何弹性碰撞中表现出的能量。
   * 
   * @param incidentVect the intial velocity of the ball
   * incidentVect ：球的初始速率
   * @param reflectedVect the resulting velocity after the collision
   * reflectedVect ： 碰撞后的速率 （假设是弹性碰撞）
   * assuming elasticity.
   * @param rCoeff the reflection coefficent.
   * rCoeff ： 反射系数
   * @return the resulting velocity after an inelastic collision.
   * 非弹性碰撞后的速率
   **/
  public static Vect applyReflectionCoeff(Vect incidentVect,
					  Vect reflectedVect,
					  double rCoeff) {
    return geometry.applyReflectionCoeff(incidentVect,
					 reflectedVect,
					 rCoeff);
  }


  /**
   * Computes the time until a ball, represented by a circle,
   * travelling at a specified velocity collides with a specified line
   * segment.
   * 计算一个球以特定的速度沿特定的线段行驶的时间
   * @requires <code>line</code> has non-zero length
   *
   * @effects computes the time until a circular ball
   * travelling at a specified velocity collides with a specified line
   * segment.  If no collision will occur, <tt>POSITIVE_INFINITY</tt> is
   * returned.  This method assumes that the ball will travel with
   * constant velocity until impact.
   * 计算一 个球以特定的速度沿特定的线段行驶的时间
   * 如果没出现碰撞，返还<tt>POSITIVE_INFINITY</tt> 
   * 这个方法假设球在碰撞之前匀速行驶。
   * @param line the line segment representing a wall or (part of) an
   * object that might be collided with
   * line: 线段代表一面墙或者一个可能发生碰撞的物体
   * @param ball a circle indicate the size and location of a ball
   * which might collide with the given line segment
   * ball: 一个给定大小和位置的球，它有可能与线碰撞
   * @param velocity the velocity of the ball before impact
   * velocity： 碰撞前球的速度
   * @return the time until collision, or <tt>POSITIVE_INFINITY</tt> if
   * the collision will not occur
   * 返还碰撞前的时间，没有碰撞的话返还<tt>POSITIVE_INFINITY</tt> 
   * @see Double#POSITIVE_INFINITY
   * @see <a href="#endpoint_effects">endpoint effects</a>
   **/
  public static double timeUntilWallCollision(LineSegment line,
					      Circle ball,
					      Vect velocity) {
    return geometry.timeUntilWallCollision(line, ball, velocity);
  }

  /**ei
   * Computes the new velocity of a ball after bouncing (reflecting)
   * off a wall.
   * 计算被墙反射后球的新的速率
   * @requires <code>line</code> has non-zero length,
   * <code>reflectionCoeff</code> >= 0
   *
   * @effects computes the new velocity of a ball reflecting off of a
   * wall.  The velocity resulting from this method corresponds to
   * collision against a surface with the given reflection
   * coefficient.  A reflection coefficient of 1 indiciates a
   * perfectly elastic collision.  This method assumes that the ball
   * is at the point of impact.
   * 作用： 计算被墙反射后球的新的速率。由这个方法算出的速率符合给出反射系数的平面碰撞
   * 反射系数为1是完全弹性碰撞。这个方法保证了球在碰撞点。

   * @param line the line segment representing the wall which is being hit
   * line: 代表被撞的墙的线段
   * @param velocity the velocity of the ball before impact
   * velocity： 球被撞之前的速率
   * line: 代表被撞的墙的线段
   * reflectionCoeff： 反射系数
   * @return the velocity of the ball after impacting the given wall
   * 返还球与墙碰撞后的速率
   **/
  public static Vect reflectWall(LineSegment line,
                                 Vect velocity,
				 double reflectionCoeff) {
    return geometry.reflectWall(line, velocity, reflectionCoeff);
  }


  /**
   * Computes the new velocity of a ball after bouncing (reflecting)
   * off a wall.
   * 计算球与墙相撞反射后的速率
   * @requires <code>line</code> has non-zero length
   *
   * @effects computes the new velocity of a ball reflecting off of a
   * wall.  The velocity resulting from this method corresponds to a
   * perfectly elastic collision.  This method assumes that the ball
   * is at the point of impact.

   * 作用： 计算被墙反射后球的新的速率。由这个方法算出的速率符合给出反射系数的平面碰撞
   * 反射系数为1是完全弹性碰撞。这个方法保证了球在碰撞点。   
   * @param line the line segment representing the wall which is being hit
   * line: 代表被撞的墙的线段
   * @param velocity the velocity of the ball before impact
   * velocity： 球被撞之前的速率
   * @return the velocity of the ball after impacting the given wall
   * 返还球与墙碰撞后的速率

   **/
  public static Vect reflectWall(LineSegment line,
                                 Vect velocity) {
    return geometry.reflectWall(line, velocity);
  }

  /****************************************************************************
   *
   * METHODS FOR CIRCLES 圆的方法
   *
   ***************************************************************************/

  /**
   * @return the square of the distance between two points
   * represented by <code>v1</code> and <code>v2</code>.
   * 返还<code>v1</code> and <code>v2</code>.两点之间的距离的平方
   **/
   static public double distanceSquared(Vect v1, Vect v2) {
     return geometry.distanceSquared(v1, v2);
   }

  /**
   * @return the square of the distance between two points
   * represented by <code>(x1, y1)</code> and <code>(x2,
   * y2)</code>.
   * 返还 <code>(x1, y1)</code> and <code>(x2,
   * y2)</code>.两点之间的距离的平方
   **/
   static public double distanceSquared(double x1, double y1,
					double x2, double y2) {
     return geometry.distanceSquared(x1, y1, x2, y2);
   }


  /**
   * Computes the time until a ball represented by a circle,
   * travelling at a specified velocity collides with a specified
   * circle.
   * 计算一个球以特定速度与特定球相撞前的时间
   * @requires ball.radius > 0
   * 
   * @effects computes the time until a ball represented by a circle,
   * travelling at a specified velocity collides with a specified
   * circle.  If no collision will occur <tt>POSITIVE_INFINITY</tt> is
   * returned.  This method assumes the ball travels with constant
   * velocity until impact.
   * 作用：计算一个球以特定速度与特定球相撞前的时间。如果没有碰撞返还<tt>POSITIVE_INFINITY</tt
   *这个方法保证球在碰撞前以不变的速度行驶。
   *
   * @param circle a circle representing the circle with which the
   * ball may collide
   * circle: 一个圆代表将与球相撞的圆
   * @param ball a circle representing the size and initial location
   * of the ball
   * ball ： 一个圆代表给定大小和位置的球
   * @param velocity the velocity of the ball before impact
   * velocity： 碰撞前球的速率
   * @return the time until collision or <tt>POSITIVE_INFINITY</tt> if
   * the collision will not occur
   * 返还直到碰撞或者调用了<tt>POSITIVE_INFINITY</tt（没有碰撞）时的时间
   * @see Double#POSITIVE_INFINITY
   **/
  static public double timeUntilCircleCollision(Circle circle,
						Circle ball,
						Vect velocity) {
    return geometry.timeUntilCircleCollision(circle, ball, velocity);
  }

  /**  
   * Computes the new velocity of a ball reflecting off of a
   * circle.
   * 计算球与圆相撞后的新的速率
   * @requires <code>reflectionCoeff</code> >= 0
   *
   * @effects computes the new velocity of a ball reflecting off of a
   * circle.  The velocity resulting from this method corresponds to a
   * collision against a surface with the given reflection
   * coefficient.  A reflection coefficient of 1 indicates a perfectly
   * elastic collision.  This method assumes that the ball is at the
   * point of impact.
   * 作用： 计算球与圆相撞后的新的速率。由这个方法算出的速率符合给出反射系数的平面碰撞
   * 反射系数为1是完全弹性碰撞。这个方法保证了球在碰撞点。   
   *
   * @param circle the center point of the circle which is being hit
   * circle: 被撞圆的中心
   * @param ball the center point of the ball
   * ball： 球的中心
   * @param velocity the velocity of the ball before impact
   * velocity： 碰撞前球的速率
   * @param reflectionCoeff the reflection coefficient
   *reflectionCoeff ： 反射系数
   * @return the velocity of the ball after impacting the given circle
   * 返还与给定圆相撞前的速率 
   **/
  public static Vect reflectCircle(Vect circle,
				   Vect ball,
                                   Vect velocity, 
				   double reflectionCoeff) {
    return geometry.reflectCircle(circle, ball, velocity, reflectionCoeff);
  }

  /**  
   * Computes the new velocity of a ball reflecting off of a
   * circle.
   * 
   * 计算球与圆相撞后的新的速率

   * @effects computes the new velocity of a ball reflecting off of a
   * circle.  The velocity resulting from this method corresponds to a
   * perfectly elastic collision.  This method assumes that the ball
   * is at the point of impact.
   * 作用： 计算球与圆相撞后的新的速率。由这个方法算出的速率符合给出反射系数的平面碰撞
   * 反射系数为1是完全弹性碰撞。这个方法保证了球在碰撞点。   
   * @param circle the center point of the circle which is being hit
   * circle: 被撞圆的中心
   * @param ball the center point of the ball
   *ball： 球的中心
   * @param velocity the velocity of the ball before impact
   *velocity： 碰撞前球的速率
   * @return the velocity of the ball after impacting the given circle 
   * 返还与给定圆相撞前的速率    
   **/
  public static Vect reflectCircle(Vect circle,
				   Vect ball,
                                   Vect velocity) {
    return geometry.reflectCircle(circle, ball, velocity);
  }

  /****************************************************************************
   *
   * METHODS FOR ROTATING LINE SEGMENTS AND CIRCLES
   * 旋转的线和圆的方法
   ***************************************************************************/

  /**
   * Rotates the point represented by <code>p</code> by
   * <code>a</code> around the center of rotation, <code>cor</code>,
   * and returns the result.
   *  <code>p</code> by <code>a</code> 
   * @effects rotates the point represented by <code>p</code> by
   * <code>a</code> around the center of rotation, <code>cor</code>,
   * and returns the result.
   * 使在旋转中心的点<code>p</code> by <code>a</code> 转起来并返还结果。 
   * 作用： 使在旋转中心的点<code>p</code> by <code>a</code> 转起来并返还结果。 
   * @param point the initial location of the point to be rotated
   * point: 要转的点的初始位置
   * @param cor the point indicating the center of rotation
   * cor： 指示旋转中心的标志
   * @param a the amount by which to rotate <code>point</code>
   * a： 要转的量
   * @return point <code>point</code> rotated around <code>cor</code>
   * by <code>a</code>
   * 返还以<code>a</code>绕<code>cor</code>旋转的点<code>point</code> 
   **/
  public static Vect rotateAround(Vect point, Vect cor, Angle a) {
    return geometry.rotateAround(point, cor, a);
  }

  /**
   * Rotates the line segment represented by
   * <code>line</code> by <code>a</code> around the center of
   * rotation, <code>cor</code>, and returns the result.
   * 使以<code>a</code为中心的线<code>line</code> 旋转，并返回结果
   *
   * @effects rotates the line segment represented by
   * <code>line</code> by <code>a</code> around the center of
   * rotation, <code>cor</code>, and returns the result.
   * 作用：使以<code>a</code为中心的线旋转，并返回结果
   *
   * @param line the initial location of the line segment to be rotated
   * line: 要旋转的线的初始位置
   * @param cor the point indicating the center of rotation
   * cor： 指示旋转中心的标志
   * @param a the amount by which to rotate <code>point</code>
   * a： 要转的量
   * @return line segment <code>line</code> rotated around <code>cor</code>
   * by <code>a</code>
   * 返还以<code>a</code>绕<code>cor</code>旋转的线<code>line</code> 
   **/
  public static LineSegment rotateAround(LineSegment line, Vect cor, Angle a) {
    return geometry.rotateAround(line, cor, a);
  }

  /**
   * Rotates the circle represented by
   * <code>circle</code> by <code>a</code> around the center of
   * rotation, <code>cor</code>, and returns the result.
   * 使以<code>a</code为中心的圆<code>circle</code> 旋转，并返回结果
   *
   * @effects rotates the circle represented by
   * <code>circle</code> by <code>a</code> around the center of
   * rotation, <code>cor</code>, and returns the result.
   * 作用：使以<code>a</code为中心的圆旋转，并返回结果
   *
   * @param circle the initial location of the circle to be rotated
   * circle: 要旋转的圆的初始位置
   * @param cor the point indicating the center of rotation
   * cor： 指示旋转中心的标志
   *
   * @param a the amount by which to rotate <code>point</code>
   * a： 要转的量
   * @return circle <code>circle</code> rotated around <code>cor</code>
   * by <code>a</code>
   * 返还以<code>a</code>绕<code>cor</code>旋转的圆<code>circle</code> 
   **/
  public static Circle rotateAround(Circle circle, Vect cor, Angle a) {
    return geometry.rotateAround(circle, cor, a);
  }

  /**
   * Computes the times when the point moving along the given
   * trajectory will intersect the given circle
   * 计算给定的点沿给定轨迹与圆相交的时间
   * @param circle circle to find collisions with
   * circle:要相撞的圆
   * @param point initial position of the point
   * point:点的初始位置
   * @param velocity linear velocity of the point
   * velocity 点的线速度
   * @return the times until intersection, with lesser result in d1,
   * or <tt>+Inf</tt>s if no collisions will occur
   * 返还交汇前的时间，d1较小的那个值，如果没有碰撞的话<tt>+Inf</tt>s 
   * @see Double#POSITIVE_INFINITY
   **/
  public static DoublePair timeUntilCircleCollision(Circle circle,
						    Vect point,
						    Vect velocity)
  {
    return geometry.timeUntilCircleCollision(circle, point, velocity);
  }
  
  /**
   * Computes the time until a ball travelling at a specified
   * velocity collides with a rotating line segment.
   * 计算球以特定速率与旋转线相撞前的时间
   * @requires <code>line</code> has non-zero length
   *
   * @effects computes the time until a circular ball
   * travelling at a specified velocity collides with a specified line
   * segment which is rotating at a fixed angular velocity about a
   * fixed center of rotation.
   * 作用：计算球以特定速率与旋转线相撞前的时间。线以固定角度和方向旋转
   *
   * <p><img src="doc-files/rotate_line.gif">
   *
   * @param line the initial position of the rotating line segment (wall)
   * line: 旋转线段的初始位置
   * @param center the center of rotation for <code>line</code>
   * center: 旋转中心
   * @param angularVelocity the angular velocity of the rotation of
   * <code>line</code> in radians per second.  A positive angular
   * velocity denotes a rotation in the direction from the positive
   * x-axis to the positive y-axis.
   *  angularVelocity ： 线的旋转速率，以弧度/秒 计算。
   * 正角速度表示旋转方向是从x轴正半轴到y轴正半轴
   * @param ball the size and initial location of the ball
   * ball: 球的初始大小和位置
   * @param velocity the initial velocity of the ball.  The ball is
   * assumed to travel at a constant velocity until impact.
   * velocity： 球的初始速度。球在碰撞前匀速行驶。
   * @return the time until collision or <tt>POSITIVE_INFINITY</tt> if no
   * collision was detected.
   * 返还碰前的时间，如果没有碰撞的话<tt>+Inf</tt>s 

   * @see Double#POSITIVE_INFINITY
   * @see <a href="#endpoint_effects">endpoint effects</a>
   **/
  public static double timeUntilRotatingWallCollision(LineSegment line,
						      Vect center,
						      double angularVelocity,
						      Circle ball,
						      Vect velocity)
  {
    return geometry.timeUntilRotatingWallCollision(line,
						   center,
						   angularVelocity,
						   ball,
						   velocity);
  }

  /**
   * Computes the new velocity of a ball reflecting off of a
   * wall which is rotating about a point with constant angular
   * velocity.
   * 计算球与一个以固定点和恒定角速度旋转的墙相撞后的新速度
   * @requires <code>line</code> has non-zero length
   *           && the ball is at the point of impact
   *
   * @effects computes the new velocity of a ball reflecting off of a
   * wall which is rotating about a point with constant angular
   * velocity.  The velocity resulting from this method corresponds to
   * a perfectly elastic collision.  This method assumes that the ball
   * is at the point of impact.  If the ball does not hit in between
   * the endpoints of <code>line</code>, <code>velocity</code> is
   * returned.
   * 作用：计算球与一个以固定点和恒定角速度旋转的墙相撞后的新速度
   * 由这个方法算出的速率符合给出反射系数的平面碰撞
   * 反射系数为1是完全弹性碰撞。这个方法保证了球在碰撞点。
   * 如果球在终点前没有与线<code>line</code碰撞，则返回速度<code>velocity</code> 。
   * @param line the line segment representing the initial position of
   * the rotating wall
   * line: 代表旋转墙的最初位置的线段
   * @param center the point about which <code>line</code> rotates
   * center线的旋转中心
   * @param angularVelocity the angular velocity at which
   * <code>line</code> rotates, in radians per second.  A positive angular
   * velocity denotes a rotation in the direction from the positive
   * x-axis to the positive y-axis.
   * angularVelocity ： 线的旋转角速度，以弧度/秒计算。
   *正角速度表示旋转方向是从x轴正半轴到y轴正半轴
   * @param velocity the velocity of the ball before impact
   * velocity 碰撞前球的速度
   * @return the velocity of the ball after impacting the wall
   * 返还与墙碰撞后球的速度
   **/
  public static Vect reflectRotatingWall(LineSegment line,
                                         Vect center,
                                         double angularVelocity,
                                         Circle ball,
                                         Vect velocity) {
    return geometry.reflectRotatingWall(line,
					center,
					angularVelocity,
					ball,
					velocity);
  }

  /**
   * Computes the new velocity of a ball reflecting off of a
   * wall which is rotating about a point with constant angular
   * velocity.
   * 计算球与一个以固定点和恒定角速度旋转的墙相撞后的新速度
   *
   * @requires <code>line</code> has non-zero length
   *           && the ball is at the point of impact
   *           && <code>reflectionCoeff</code> >= 0
   *
   * @effects computes the new velocity of a ball reflecting off of a
   * wall which is rotating about a point with constant angular
   * velocity.  The velocity resulting from this method corresponds to
   * a collision against a surface of the given reflection
   * coefficient.  A reflection coefficient of 1 indicates a perfectly
   * elastic collision.  This method assumes that the ball is at the
   * point of impact.  If the ball does not hit in between the
   * endpoints of <code>line</code>, <code>velocity</code> is
   * returned.
   * 作用：计算球与一个以固定点和恒定角速度旋转的墙相撞后的新速度
   * 由这个方法算出的速率符合给出反射系数的平面碰撞
   * 反射系数为1是完全弹性碰撞。这个方法保证了球在碰撞点。
   * 如果球在终点前没有与线<code>line</code碰撞，则返回速度<code>velocity</code> 。

   *
   * @param line the line segment representing the initial position of
   * the rotating wall
   * line: 代表旋转墙的最初位置的线段
   *
   * @param center the point about which <code>line</code> rotates
   * center线的旋转中心
   *
   * @param angularVelocity the angular velocity at which
   * <code>line</code> rotates, in radians per second.  A positive angular
   * velocity denotes a rotation in the direction from the positive
   * x-axis to the positive y-axis.
   * angularVelocity ： 线的旋转角速度，以弧度/秒计算。
   *正角速度表示旋转方向是从x轴正半轴到y轴正半轴

   *
   * @param velocity the velocity of the ball before impact
   * velocity 碰撞前球的速度
   *
   * @param reflectionCoeff the reflection coefficient
   * reflectionCoeff ： 反射系数
   * @return the velocity of the ball after impacting the wall
   * 返还与墙碰撞后球的速度
   **/
  public static Vect reflectRotatingWall(LineSegment line,
                                         Vect center,
                                         double angularVelocity,
                                         Circle ball,
                                         Vect velocity,
					 double reflectionCoeff)
  {
    return geometry.reflectRotatingWall(line,
					center,
					angularVelocity,
					ball,
					velocity,
					reflectionCoeff);
  }

  /**
   * Computes the time until a ball travelling at a specified
   * velocity collides with a rotating circle.
   * 计算球以特定速率与旋转圆相撞前的时间
   *
   * @effects computes the time until a circular ball
   * travelling at a specified velocity collides with a specified circle
   * that is rotating about a given center of rotation at a given
   * angular velocity.  If no collision will occurr <tt>POSITIVE_INFINITY</tt>
   * is returned. This method assumes the
   * ball will travel with constant velocity until impact.
   * 作用：计算球以特定速率与旋转圆相撞前的时间。圆以固定角度和方向，绕固定中心旋转
   * 如果没有发生碰撞，返还<tt>POSITIVE_INFINITY</tt>
   * 这个方法保证球在碰撞之前匀速行驶。
   * <p>
   * <img src="doc-files/rotate_circle.gif">
   *
   * @param circle a circle representing the initial location and size
   * of the rotating circle
   * circle: 表示旋转圆的初始位置和大小
   * @param center the point around which the circle is rotating
   * center： 旋转圆的旋转中心
   * @param angularVelocity the angular velocity with which
   * <code>circle</code> is rotating about <code>center</code>, in
   * radians per second.  A positive angular velocity denotes a
   * rotation in the direction from the positive x-axis to the
   * positive y-axis.
   * angularVelocity ： 线的旋转角速度，以弧度/秒计算。
   *正角速度表示旋转方向是从x轴正半轴到y轴正半轴

   * 
   * @param ball a circle representing the size and initial position
   * of the ball
   * ball: 表示球的初始位置和大小

   * @param velocity the velocity of the ball before impact
   * velocity 碰撞前球的速度
   *
   * @see Double#POSITIVE_INFINITY
   **/

  public static double timeUntilRotatingCircleCollision(Circle circle,
							Vect center,
							double angularVelocity,
							Circle ball,
							Vect velocity)
  {
    return geometry.timeUntilRotatingCircleCollision(circle,
						     center,
						     angularVelocity,
						     ball,
						     velocity);
  }

  /**
   * Computes the new velocity of a ball reflected off of a rotating
   * circle.
   * 计算球与一个以固定点和恒定角速度旋转的圆相撞后的新速度
   *
   * @requires the ball is at the point of impact
   * 要求球在碰撞点
   *
   * @effects computes the new velocity of a ball reflected off of a
   * circle which is rotating with constant angular velocity around a
   * point.  The velocity resulting from this method corresponds to a
   * perfectly elastic collision.
   * 作用：计算球与一个以固定点和恒定角速度旋转的圆相撞后的新速度
   * 由这个方法算出的速率符合给出反射系数的平面碰撞
   *
   * @param circle the rotating circle
   * circle: 旋转的圆
   * @param center the point about which <code>circle</code> is
   * rotating
   * center: 圆的旋转中心
   * @param angularVelocity the angular velocity with which
   * <code>circle</code> is rotating about <code>center</code>, in
   * radians per second.  A positive angular velocity denotes a
   * rotation in the direction from the positive x-axis to the
   * positive y-axis.
   * angularVelocity ： 线的旋转角速度，以弧度/秒计算。
   *正角速度表示旋转方向是从x轴正半轴到y轴正半轴
   *
   * @param ball the size and position of the ball before impact
   * ball:相撞前球的位置和大小
   * @param velocity the velocity of the ball before impact
   * 相撞前球的速度
   * @return the velocity of the ball after impacting the rotating
   * circle
   * 返还球与旋转圆相撞后的速度
   **/
  public static Vect reflectRotatingCircle(Circle circle,
					   Vect center,
                                           double angularVelocity,
					   Circle ball,
                                           Vect velocity) {
    return geometry.reflectRotatingCircle(circle,
					  center,
					  angularVelocity,
					  ball,
					  velocity);
  }


  /**
   * Computes the new velocity of a ball reflected off of a rotating
   * circle.
   * 计算球与一个以固定点和恒定角速度旋转的圆相撞后的新速度
   *
   * @requires the ball is at the point of impact
   * 要求球在碰撞点
   *
   * @effects computes the new velocity of a ball reflected off of a
   * circle which is rotating with constant angular velocity around a
   * point.  The velocity resulting from this method corresponds to a
   * collision against a surface with the given reflection
   * coefficient.  A reflection coefficient of 1.0 indicates a
   * perfectly elastic collision.
   * 作用：计算球与一个以固定点和恒定角速度旋转的圆相撞后的新速度
   * 由这个方法算出的速率符合给出反射系数的平面碰撞
   *
   * @param circle the rotating circle
   * circle: 旋转的圆
   *
   * @param center the point about which <code>circle</code> is
   * rotating
   * center: 圆的旋转中心
   *
   * @param angularVelocity the angular velocity with which
   * <code>circle</code> is rotating about <code>center</code>, in
   * radians per second.  A positive angular velocity denotes a
   * rotation in the direction from the positive x-axis to the
   * positive y-axis.
   * angularVelocity ： 线的旋转角速度，以弧度/秒计算。
   *正角速度表示旋转方向是从x轴正半轴到y轴正半轴
   *
   *
   * @param ball the size and position of the ball before impact
   * ball:相撞前球的位置和大小
   *
   * @param velocity the velocity of the ball before impact
   * 相撞前球的速度
   * 
   * @param reflectionCoeff the reflection coefficient
   * 反射系数
   * @return the velocity of the ball after impacting the rotating
   * circle
   * 返还球与旋转圆相撞后的速度
   **/
  public static Vect reflectRotatingCircle(Circle circle,
					   Vect center,
                                           double angularVelocity,
					   Circle ball,
                                           Vect velocity,
					   double reflectionCoeff)
  {
    return geometry.reflectRotatingCircle(circle,
					  center,
					  angularVelocity,
					  ball,
					  velocity,
					  reflectionCoeff);
  }

  /****************************************************************************
   *
   * METHODS FOR MULTI-BALL SIMULATIONS 模拟多球的方法
   *
   ***************************************************************************/


  /**
   * Computes the time until two balls collide.
   * 计算两球相撞前的时间
   * @effects computes the time until two balls, represented by two
   * circles, travelling at specified constant velocities, collide.
   * If no collision will occur <tt>POSITIVE_INFINITY</tt> is returned.
   * This method assumes that both balls will travel at constant
   * velocity until impact.
   * 作用： 计算两球相撞前的时间。两个球由两个圆表示。这两个圆以特定的固定速度行驶，然后相撞
   * 如果没有相撞则返还<tt>POSITIVE_INFINITY</tt> 
   * 这个方法保证了两个球在相撞前以常定速度行驶。
   * @param ball1 a circle representing the size and initial position
   * of the first ball.
   * ball1: 一个圆表示第一个球的初始位置和大小
   * @param vel1 the velocity of the first ball before impact
   * vel1：撞击前第一个球的速度
   * @param ball2 a circle representing the size and initial position
   * of the second ball.
   * ball2: 一个圆表示第二个球的初始位置和大小
   * @param vel2 the velocity of the second ball before impact
   * vel2：撞击前第二个球的速度
   * 
   * @return the time until collision or <tt>POSITIVE_INFINITY</tt> if the
   * collision will not occur
   * 返还相撞前的时间，没有相撞的话返还<tt>POSITIVE_INFINITY</tt> 
   * @see Double#POSITIVE_INFINITY
   **/
  public static double timeUntilBallBallCollision(Circle ball1,
						  Vect   vel1,
						  Circle ball2,
						  Vect   vel2) {
    return geometry.timeUntilBallBallCollision(ball1, vel1,
					       ball2, vel2);
  }


  /**
   * Computes the resulting velocities of two balls which collide.
   * 计算两球相撞后的速度
   * @requires mass1 > 0 && mass2 > 0 && the distance between the two
   * balls is approximately equal to the sum of their radii; that is,
   * the balls are positioned at the point of impact.
   * 要求： mass1 > 0 && mass2 > 0 && 两球的距离几乎等于他们半径的和
   * 就是说球在相撞的点上
   * @effects computes the resulting velocities of two balls which
   * collide.
   * 作用： 计算两球相撞后的速度
   * @param center1 the position of the center of the first ball
   * center1:第一个球的中心
   * @param mass1 the mass of the first ball
   * mass1：第一个球的质量
   * @param velocity1 the velocity of the first ball before impact
   * velocity1 的第一个球撞击后的速度
   * @param center2 the position of the center of the second ball
   * center2:第二个球的中心
   *
   * @param mass2 the mass of the second ball
   * mass2：第二个球的质量
   *
   * @param velocity2 the velocity of the second ball before impact
   * velocity2 第二个球撞击后的速度

   *
   * @return a <code>VectPair</code>, where the first <code>Vect</code> is
   * the velocity of the first ball after the collision and the second
   * <code>Vect</code> is the velocity of the second ball after the collision.
   * 返还一个<code>VectPair</code，第一个<code>Vect</code> 是第一个球球撞击后的速度
   * 第二个<code>Vect</code> 是第二个球球撞击后的速度
   **/
  public static VectPair reflectBalls(Vect center1,
				      double mass1,
				      Vect velocity1,
				      Vect center2,
				      double mass2,
				      Vect velocity2) {
    return geometry.reflectBalls(center1,
				 mass1,
				 velocity1,
				 center2,
				 mass2,
				 velocity2);
  }

}
