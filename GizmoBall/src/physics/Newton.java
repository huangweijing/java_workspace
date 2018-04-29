package physics;

import java.lang.Double; // import statement added to mollify javadoc

/****************************************************************************
 * Copyright (C) 1999, 2000 by the Massachusetts Institute of Technology,
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
 * @author   Jeffrey Sheldon
 * @version  $Id: Newton.java,v 1.1 2005/03/18 18:31:49 joy Exp $
 *
 ***************************************************************************/

/**
 * This class contains the logic required to perform netwon's method
 * of iterative root finding on single variable functions.
 * 这个类包括了执行netwon's 方法的逻辑，这种方法可找到单一可变函数的重根。
 */

public class Newton {

  // nobody should be constructing a "Newton"
  private Newton() {
  }
  
  /**
   * Newton.Result is a record type which aggregates the results of
   * evaluating both a function at a point and its derriviative.
   * Newton.Result 是一个记录类型，它集合了评价一个点上的函数和它的推导的结果
   **/
  public final static class Result {
    public final double f;
    public final double f_prime;

    /** @effects creates a new Result record with the given values **/
    /** 作用： 用已给值创建一个新的Result 记录**/
    public Result(double f, double f_prime) {
      this.f = f;
      this.f_prime = f_prime;
    }

    /** @returns true if either f or f' is NaN 如果f或f'只要有中一个是NaN则返回真**/
    public boolean undefined() {
      return Double.isNaN(f) || Double.isNaN(f_prime);
    }

    /** @returns true if this result's sign is not identical to other's **/
    /** 返还真如果这个结果的符号与其他的不同**/
    public boolean funcSignChance(Result other) {
      return (f * other.f <= 0);
    }

    /** @returns true if this result's derivative's sign is not identical to other's **/
    /** 返还真如果这个结果的推导符号与其他的不通**/
    public boolean derivativeSignChance(Result other) {
      return (f_prime * other.f_prime <= 0);
    }

    /** @returns funcSignChange() || derivativeSignChange() **/
    public boolean signChange(Result other) {
      return (f * other.f <= 0) || (f_prime * other.f_prime <= 0);
    }

    public String toString() {
      return "f(t)=" + f + ";f'(t)=" + f_prime + "";
    }
  }

  /** Conveience name for an undefined function result **/
  /** 给一个未定义的函数结果一个方便的名字**/
  public static final Result UNDEFINED = new Result(Double.NaN, Double.NaN);

  /**
   * Newton.Function is an interface which specifies a function whose
   * roots can be found by this class.
   * Newton.Function 是一个接口，它描述了一个函数，这个函数的根可以用这个类找出
   **/
  public static interface Function {
    /**
     * @returns the value of the function evaluated at <code>t</code>
     * 返还函数的值，用<code>t</code>求值
     **/
    public abstract Result evaluate(double t);
  }

  private static final int MAX_STEPS = 30;
  private static final double epsilon =  0.000000001;

  /**
   * @requires t_min <= t_max; t_step > 0
   *
   * Searches for possible roots of <code>function</code> by looking
   * for sign changes.  If a possible root is found newton's method is
   * performed to try to determine the precise value of the root.
   * This function searches for roots between <code>t_min</code> and
   * <code>t_max</code> with a step size of <code>t_step</code>.  If
   * no solution is found returns <code>NaN</code>, else returns the
   * solution.
   * 以符号的变化来找<code>function</code> 可能的根。如果找到了根，
   * 用newton's 方法使根的值精确。这个方法在<code>t_min</code> and
   * <code>t_max</code> 之间用 <code>t_step</code>找根。
   * 如果没找到解决办法则返回<code>NaN</code>, 否则返还结果。
   *
   * <p>Note: The returned value may not actually be with in these bounds.  
   * 注： 返还的结果也许不在此范围内。
   */
  public static double findRoot(Function function, 
				double t_min,
				double t_max,
				double t_step)
  {
    // initialize to NaN so that sign-change? is initially false
    Result eval = UNDEFINED;

    for (double t = t_min; t < t_max + t_step; t += t_step) {
      Result old = eval;
      eval = function.evaluate(t);
      // System.out.println("at " + t + " " + evan);

      if (eval.undefined()) {
	continue;
      }
      
      // did f or f' change sign?      
      if (eval.signChange(old)) {
	double root = findRoot(function, 
			       (old.f_prime <= 0) ? t - t_step : t);
	// check to make sure it was within the bounds of this check
       // 核对以确保它在范围内
	if ((t - t_step <= root) && (root <= t)) {
	  // System.out.println("returning " + root);
	  return root;
	}
      }
    }

    return Double.NaN;
  }

  /**
   * Performs netwon's method on <code>function</code> starting at
   * <code>initial_t</code> and returns the solution.  If no solution
   * is found, returns <code>NaN</code>
   * 在<code>function</code> 上执行netwon's 方法，从<code>initial_t</code> 
   * 开始并返回结果。如果没有找到结果，返还<code>NaN</code>
   **/
  public static double findRoot(Function function,
				double initial_t)
  {
    double t = initial_t;    

    for (int count = 0; count < MAX_STEPS; count++) {
      Result eval = function.evaluate(t);
      if (eval.undefined()) {
	return Double.NaN;
      }

      double t_next = t - eval.f/eval.f_prime;
      
      if (Math.abs(t_next - t) < epsilon) {
	if (Math.abs(eval.f) < 1000*epsilon) {
	  // claim it's close enough to call a hit (as opposed
	  // to a local minima) 要求它足够接近以调用hit 
	  return t_next;
	} else {
	  return Double.NaN;
	}
      }

      t = t_next;
    }

    return Double.NaN;
  }
}
