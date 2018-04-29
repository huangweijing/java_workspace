package physics;

/****************************************************************************
 * Copyright (C) 1999 by the Massachusetts Institute of Technology,
 *                           Cambridge, Massachusetts.
 *
 *                        All Rights Reserved
 *                            授权
 *
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for any purpose and without fee is hereby
 * granted, provided that the above copyright notice appear in all
 * copies and that both that copyright notice and this permission
 * notice appear in supporting documentation, and that MIT's name not
 * be used in advertising or publicity pertaining to distribution of
 * the software without specific, written prior permission.
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *在此予以授权允许免费使用，拷贝，修改这个软件和相关文档。可用于任何目。在此提供以上的授权提示。
 *此提示将出现在所有文档中。但MIT的名字并不出现在以发布软件为目的的广告或者出版物中。
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
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
 * Author: Matt Frank, MIT Laboratory for Computer Science,
 *         mfrank@lcs.mit.edu
 *         1999-Apr-03
 *
 * Version: $Id: Angle.java,v 1.1 2005/03/18 18:31:48 joy Exp $
 *
 ***************************************************************************/

import java.io.Serializable;

/**
 * Angle is an immutable abstract data type which represents the
 * mathematical notion of an angle.  <code>Angle</code> represents a
 * non-negative angle less than 360 degrees or 2*pi radians.
 * Angle 是一个不变的抽象数据类型，从数学角度描述角的概念。
 *<code>Angle</code> 描述一个非负的小于360的角或者小于2π的圆弧。
 *
 **/

public final class Angle
  implements Serializable, Comparable
{

  // Rep. Invariant:公式
  //   cosine^2 + sine^2 = 1

  // Abstraction Function:抽象函数
  //   The angle <a> such that cos(a) = cosine and sin(a) = sine

  // Rep. Rationale:公理
  //   In most of the math we use here we start in cartesian coordinates, so
  //   calculating sine and cosine is relatively efficient (just a sqrt and
  //   a division).  Finding the angle in terms of arcsin and arccos would be
  //   very slow.  On the other hand, adding and subtracting angles that are
  //   represented this way can be done relatively efficiently using standard
  //   trig. identities.
  //   这里使用的大多数数学基于笛卡尔坐标，所以计算sine和cosine的效率比较高（只需做乘方和除法）。
  //   如果用arcsin和arccos找角会比较慢。另外，用这种方式对角做加法和减法效率比较高。

  private final double cosine;
  private final double sine;

  private static final double SQRT = Math.sqrt(0.5);
  // useful constants  一个有用的常量
  /** A zero-degree or zero-radian angle 一个零度的角或弧*/
  public static final Angle ZERO = new Angle(1.0, 0.0);
  /** A 45-degree angle 一个45°角*/
  public static final Angle DEG_45 = new Angle(SQRT, SQRT);
  /** A 90-degree angle 一个90°角*/
  public static final Angle DEG_90 = new Angle(0.0, 1.0);
  /** A 135-degree angle 一个135°角*/
  public static final Angle DEG_135 = new Angle(-SQRT, SQRT);
  /** A 180-degree angle 一个180°角*/
  public static final Angle DEG_180 = new Angle(-1.0, 0.0);
  /** A 225-degree angle 一个225°角 */
  public static final Angle DEG_225 = new Angle(-SQRT, -SQRT);
  /** A 270-degree angle 一个270°角*/
  public static final Angle DEG_270 = new Angle(0.0, -1.0);
  /** A 315-degree angle 一个315°角*/
  public static final Angle DEG_315 = new Angle(SQRT, -SQRT);

  /** An angle of pi/4 radians 一个1/4π的弧*/
  public static final Angle RAD_PI_OVER_FOUR = DEG_45;
  /** An angle of pi/2 radians 一个1/2π的弧*/
  public static final Angle RAD_PI_OVER_TWO = DEG_90;
  /** An angle of pi radians 一个π的弧*/
  public static final Angle RAD_PI = DEG_180;

  // CONSTRUCTORS:构造器

  /**
   * @effects constructs an <code>Angle</code> with <code>radians</code> radians.
   * 用弧<code>radians</code> 构造一个角<code>Angle</code> 
   */
  public Angle(double radians) {
    cosine = Math.cos(radians);
    sine = Math.sin(radians);
  }

  /**
   * @requires 要求 (x,y) != (0,0)
   *
   * @effects constructs the <code>Angle</code> that is formed between the
   * positive x-axis and the line from the origin to (<code>x</code>,
   * <code>y</code>).
   * 构造角<code>Angle</code> ，使其位于x轴正半轴到一条从原点开始的线上的(<code>x</code>,<code>y</code>)之间。
   *
   **/
  public Angle(double x, double y) {
    double r = Math.sqrt((x * x) + (y * y));
    if (r == 0.0) {
      if ((x == 0.0) && (y == 0.0)) {
	throw new IllegalArgumentException("Requires violated: Triangle is singular");
      } else {
	throw new ArithmeticException("Triangle is singular; imprecision on <" + x + "," + y + ">");
      }
    }
    cosine = x / r;
    sine = y / r;
  }

  // OBSERVERS: 观察者

  /**
   * @return the cosine of this. 返还cosine余弦
   */
  public double cos() {
    return cosine;
  }

  /**
   * @return the sine of this. 返还sine正弦值
   */
  public double sin() {
    return sine;
  }

  /**
   * @return the tangent of this. 返还tangent
   */
  public double tan() {
    return sine/cosine;
  }

  /**
   * @return the number of radians represented by this in the range of 
   * -pi to pi.
   *返还-π到π之间的弧度数
   *
   **/
  public double radians() {
    double d = Math.atan2(sine, cosine);
    if (d > Math.PI || d < -Math.PI) {
      System.out.println("d = " + d);
      throw new IllegalArgumentException();
    }
    return d;
  }

  /**
   * Compares this object with the specified object for order.
   * @return a negative integer, zero, or a positive integer as this
   * object is less than, equal to, or greater than the specified object.
   * @exception ClassCastException if <code>o</code> is not an Angle
   * @exception NullPointerException if <code>o</code> is null
   * 比较 这个 object 和 specified object的次序
   * 返还一个负整数，0 或 一个正整数，表示this object是小于，等于或大于specified object
   * 除了ClassCastException外，如果<code>o</code> 不是一个角的话
   * 除了NullPointerException， 如果<code>o</code> 为空。
   */
  public int compareTo(Object o)
  {
    // Comparable.compareTo allows us to throw a ClassCastException  
    // Comparable.compareTo 允许抛出异常
    return compareTo((Angle) o);
  }
  
  /**
   * Compares this object with the specified object for order.
   * @return a negative integer, zero, or a positive integer as this
   * object is less than, equal to, or greater than the specified object.
   * @exception NullPointerException if <code>c</code> is null
   * 比较 这个 object 和 specified object的次序
   * 返还一个负整数，0 或 一个正整数，表示this object是小于，等于或大于specified object
   * 除了NullPointerException， 如果<code>o</code> 为空。
   */
  public int compareTo(Angle c)
  {
    if (this.equals(c))
      return 0;

    // first discriminate on the basis of top vs. bottom half (sin)
    // then discriminate on the basis of left vs. right half (cos)
    // 首先区别顶部和底部的half (sin)
    // 然后区别左边和右边的half (sin)
    if (sine >= 0.0) {
      if (c.sine < 0.0) {
	return -1;
      } else {
	if (cosine < c.cosine) {
	  return 1;
	} else {
	  return -1;
	}
      }
    } else {
      if (c.sine >= 0.0) {
	return 1;
      } else {
	if (cosine < c.cosine) {
	  return -1;
	} else {
	  return 1;
	}
      }
    }
  }

  // PRODUCERS: 生产者

  /**
   * @requires <code>a</code> is not null
   * @return the angle <code>this</code> + <code>a</code>.
   *要求<code>a</code> 不为空
   * 返还角度<code>this</code> - <code>a</code>.

   */
  public Angle plus(Angle a) {
    // These are standard trig identities.  See the appendix of your
    // favorite calculus text book.
    // 这些是标准的三角等式。感兴趣的话可查看微积分教课书的附录
    //
    double cosine = (this.cosine * a.cosine) - (this.sine * a.sine);
    double sine = (this.sine * a.cosine) + (this.cosine * a.sine);

    return new Angle(cosine, sine);
  }

  /**
   * @requires <code>a</code> is not null
   * @return the angle <code>this</code> - <code>a</code>.
   *要求<code>a</code> 不为空
   * 返还角度<code>this</code> - <code>a</code>.
   */
  public Angle minus(Angle a) {
    // These are standard trig identities.  See the appendix of your
    // favorite calculus text book.
    // 这些是标准的三角等式。感兴趣的话可查看微积分教课书的附录

    double cosine = (this.cosine * a.cosine) + (this.sine * a.sine);
    double sine = (this.sine * a.cosine) - (this.cosine * a.sine);

    return new Angle(cosine, sine);
  }

  public String toString() {
    return "Angle(" + cosine + "," + sine + ")";
  }

  public boolean equals(Angle a) {
    if (a == null) return false;
    return ((this.cosine == a.cosine) && (this.sine == a.sine));
  }

  public boolean equals(Object o) {
    return (o instanceof Angle) && equals((Angle) o);
  }

  public int hashCode() {
    return (new Double(sine)).hashCode() + (new Double(cosine)).hashCode();
  }

}
