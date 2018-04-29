package physics;

/**********************************************************************
 * Copyright (C) 1999, 2000 by the Massachusetts Institute of Technology,
 *                      Cambridge, Massachusetts.
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
 *在此予以授权允许免费使用，拷贝，修改这个软件和相关文档。可用于任何目。在此提供以上的授权提示。
 *此提示将出现在所有文档中。但MIT的名字并不出现在以发布软件为目的的广告或者出版物中。
 *
 * THE MASSACHUSETTS INSTITUTE OF TECHNOLOGY DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS.  IN NO EVENT SHALL THE MASSACHUSETTS
 * INSTITUTE OF TECHNOLOGY BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *对于这个软件，美国麻省理工学院的技术声明不做任何担保，其中包括所有隐含的保证，适销性和适用性。
 *不论发生任何情况，麻省理工技术研究所不承担任何特殊，间接或相应的损害赔偿或任何损害赔偿所造成的损失
 *使用，数据或利润，无论是在一项行动的合同，疏忽或其他侵权行为采取行动，而引起的或与之涉嫌
 *与使用或执行这一软件。
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 * @author   Lik Mui
 * @version  $Id: Circle.java,v 1.1 2005/03/18 18:31:48 joy Exp $
 * @date     $Date: 2005/03/18 18:31:48 $
 *
 *********************************************************************/

import java.io.Serializable;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;

/**
 * Circle is an immutable abstract data type which models the
 * mathematical notion of a circle in cartesian space.
 * Circle 是一个不变的抽象数据类型，它在笛卡尔坐标系中建立起圆的数学模型
 */
public final class Circle implements Serializable {

  private final Vect centerPoint;
  private final double radius;

  // Rep. Invariant: 公式
  //   centerPoint != null &&
  //   radius >= 0.0

  // Abstraction Function:抽象函数
  //   The circle with a center at 'centerPoint' and a radius 'radius'
  //  圆有圆点和半径

  // Constructors -----------------------------------
  // 构造器

  /**
   * @requires 要求<code>r</code> >= 0 and <code>center</code> != null
   * 
   * @effects Creates a new circle with the specified size and location.
   *  创建一个新的圆，有特定的大小和位置
   * @param center the center point of the circle 圆的圆心
   * @param r the radius of the circle 圆的半径
   */
  public Circle(Vect center, double r) {
    if ((r < 0) || (center == null)) {
      throw new IllegalArgumentException();
    }
    centerPoint = center;
    radius = r;
  }

  /**
   * @requires <code>r</code> >= 0
   *
   * @effects Creates a new circle with the specified size and location.
   *  创建一个新的圆，有特定的大小和位置
   *
   * @param cx the x coordinate of the center point of the circle 圆心的x轴坐标
   * @param cy the y coordinate of the center point of the circle 圆心的y轴坐标
   * @param r the radius of the circle 圆的半径
   */
  public Circle(double cx, double cy, double r) {
    this(new Vect(cx, cy), r);
  }

  /**
   * @requires <code>r</code> >= 0 and <code>center</code> != null
   *
   * @effects Creates a new circle with the specified size and location.
   *创建一个新的圆，有特定的大小和位置
   * @param center the center point of the circle center为圆心
   * @param r the radius of the circle  r为半径
   */
  public Circle(Point2D center, double r) {
    this(new Vect(center), r);
  }

  // Observers --------------------------------------

  /**
   * @return the center point of this circle. 返还圆心
   */
  public Vect getCenter() {
    return centerPoint;
  }

  /**
   * @return the radius of this circle. 返还半径
   */
  public double getRadius() {
    return radius;
  }

  /**
   * @return a new Ellipse2D which is the same as this circle 
   * 返还一个Ellipse2D，它与这个圆相同
   */
  public Ellipse2D toEllipse2D() {
    return new Ellipse2D.Double(centerPoint.x() - radius,
				centerPoint.y() - radius,
				2 * radius,
				2 * radius);
  }

  // Object methods --------------------------------------Object 方法

  public boolean equals(Circle c) {
    if (c == null) return false;
    return (radius == c.radius) && centerPoint.equals(c.centerPoint);
  }

  public boolean equals(Object o) {
    if (o instanceof Circle)
      return equals((Circle) o);
    else
      return false;
  }

  public String toString() {
    return "[Circle center=" + centerPoint + " radius=" + radius + "]";
  }

  public int hashCode() {
    return centerPoint.hashCode() + 17 * (new Double(radius)).hashCode();
  }
}
