package physics;

import java.lang.Double; // import statement added to mollify javadoc
import java.util.Iterator;
import physics.Geometry.VectPair;
import physics.Geometry.DoublePair;

/****************************************************************************
 * Copyright (C) 2001 by the Massachusetts Institute of Technology,
 *                    Cambridge, Massachusetts.
 *
 *                      All Rights Reserved
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
 * @author: Jeffrey Sheldon (jeffshel@mit.edu)
 *          Fall 2000, Spring 2001
 *          Major rewrites and improvements to iterative solving
 *
 * @author: Jeremy Nimmer (jwnimmer@alum.mit.edu)
 *          Fall 2000, Spring 2001
 *          Editorial role (testing and specification editing)
 *
 * Version: $Id: SimpleGeometry.java,v 1.1 2005/03/18 18:31:49 joy Exp $
 *
 ***************************************************************************/

/**
 * SimpleGeometry alters the behavior of GeometyImpl by removing the
 * doughnut optimizations.  This often reduces the running time of the
 * timeUntilRotating* methods, but <b>will</b> reduce accuracy unless
 * a small maximumForesight is used.  Most callers will not use
 * SimpleGeometry directly, but will use the singleton Geometry
 * instead.
 * SimpleGeometry 排除圆环的优化来改变GeometyImpl 的行为。这通常可以减少timeUntilRotating的
 * 运行时间，但<b>will</b> 减小了精确度，除非使用一个小的maximumForesight。多数调用者不会直接
 * 使用SimpleGeometry ，而是使用singleton Geometry
 * <p>
 *
 * The doughnut optimizations are used by the timeUntilRotating*
 * methods to narrow the possible times during which a collision might
 * happen, in order to narrow the search space and improve accuracy.
 * 圆环优化被timeUntilRotating使用来使碰撞时间变紧凑，为的是使查找变紧凑并且更加精确
 *
 * <p>
 *
 * When doughnut optimizations are disabled, the timeUntilRotating*
 * methods will always evaluate at least <code>searchSlices</code>
 * data points between 0 and <code>maximumForesight</code> to search
 * for a root.  This will be faster for the cases where the doughnut
 * calculations do not lead to a useful decrease in the time being
 * searched, but will be slower for the cases where the doughnut
 * optimizations would have deduced that no collision was possible.
 * 当圆环优化失败时，timeUntilRotating总会评测0 and <code>maximumForesight</code>
 * 之间的数据点，以找到根。当圆环优化并没有有效地减少查找时间时能使其变更快，
 * 但当圆环优化推导出没有碰撞发生时会变慢。
 *
 * @see physics.GeometryImpl
 * @see physics.Geometry
 **/
public class SimpleGeometry
  extends GeometryImpl
{

  /**
   * @requires (maximumForesight >= 0.0) && (searchSlices >= 1) &&
   * ((searchSlices >= 200) || (maximumForesight / searchSlices <= 0.01))
   *
   * @effects Constructs a SimpleGeometry with the specified tuning
   * parameters, which are described in the class overview of
   * GeometryImpl.
   * 作用：用给定的tuning参数构造一个SimpleGeometry ，在GeometryImpl的overview 类中描述过
   *
   * @see physics.GeometryImpl
   **/
  public SimpleGeometry(double maximumForesight, int searchForCollisionSlices) {
    super(maximumForesight, searchForCollisionSlices);
    if (!((searchSlices >= 200) || ((maximumForesight / searchSlices) <= 0.01))) {
      throw new IllegalArgumentException();
    }
  }

  /**
   * @effects performs the operation in a more conservative way than
   * the superclass implementation (omits the doughnut optimizations).
   * 作用： 用比超类执行更保守的方式执行操作
   **/
  protected IntervalList restrictSearchInterval(IntervalList intervals,
						double inner_radius,
						double outer_radius,
						double phi_1,
						double phi_2,
						double omega,
						Vect center,
						Circle ball,
						Vect velocity)
  {
    // Compute the interval where we are in the outer circle
    // 计算外环的间隔

    Circle outer_plus_ball =
      new Circle(center, outer_radius + ball.getRadius());

    DoublePair dp = timeUntilCircleCollision(outer_plus_ball,
					     ball.getCenter(), velocity);

    // If we never hit, we have no interval
    // 如果不碰撞就没有间隔
    if (!dp.areFinite()) {
      return new IntervalList();
    }

    // Limit to the outer circle time 限于外环的时间
    intervals.restrictToInterval(dp.d1, dp.d2);

    return intervals;
  }

}
