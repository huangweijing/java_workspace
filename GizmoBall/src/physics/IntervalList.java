package physics;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.io.Serializable;

/****************************************************************************
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
 * @author: Jeffrey Sheldon (jeffshel@mit.edu)
 *          Spring 2001
 *
 * Version: $Id: IntervalList.java,v 1.2 2005/03/30 05:48:38 rohit Exp $
 *
 ***************************************************************************/

/**
 * An IntervalList is a mutable abstraction of a set of ranges of
 * continuous numbers.
 * IntervalList 是一个可变的抽象类型，是一组一定范围内连续的数
 **/

public class IntervalList
  implements Serializable
{

  // Representation Invariant:
  // forall 0 <= i < j < intervals.size,
  //            intervals[i].end < intervals[j].start

  // Abstraction Function:抽象函数
  //   An interval list represents the set of all numbers contained 
  //   within any of the intervals in intervals.  
  //   一个间隔的链表描述连续数字的集合
  //

  /**
   * An Interval is an immutable representation of a single contigous
   * range of numbers from start to end.
   * 
   **/
  public static class Interval
    implements Comparable<Interval>, Serializable
  {

    // Representation Invariant:
    //    start < end

    // Abstraction Function:
    //   The set of all numbers between start and end
    // 开始和结束之间所有数的集合
    
    public final double start;
    public final double end;

    /**
     * @requires: end >= start
     *
     * @effects: creates a new Interval representing the numbers from
     * <code>start</code> to <code>end</code>.
     * 作用： 创建一个新的Interval 来描述从<code>start</code> to <code>end</code>之间的数
     **/    
    public Interval(double start, double end) {
      if (Double.isNaN(start) || Double.isNaN(end)) {
	throw new IllegalArgumentException();
      }
      if (end < start) {
	System.out.println("start = " + start);
	System.out.println("end   = " + end);
	throw new IllegalArgumentException();
      }
      this.start = start;
      this.end = end;
    }

    /**
     * @effects: returns the lower bound of this Interval.
     * 返还Interval的下限
     **/
    public double start() {
      return start;
    } 

    /**
     * @effects: returns the upper bound of this Interval.
     * 返还Interval的上限
     **/
    public double end() {
      return end;
    }

    // returns true of this and i overlap
    private boolean overlaps(Interval i) {
      if (this.start <= i.start) {
	if (i.start > this.end) {
	  return false;
	} else {
	  return true;
	}
      } else {
	return i.overlaps(this);
      }
    }

    // requires that this an i overlap, returns a single interval
    // representing the union of the numbers contained within.
    // 要求返还一个单一的间隔，描述包含的数的联合
    private Interval merge(Interval i) {
      if (overlaps(i)) {
	if (this.start <= i.start) {
	  return new Interval(this.start, (i.end>this.end) ? i.end : this.end);
	} else {
	  return i.merge(this);
	}
      } else {
	throw new IllegalArgumentException();
      }
    }

    // returns a new interval with the same start point as this but
    // with an endpoint which is the nearer of this.end and this.start
    // + length
    // 返还一个新的间隔，它的起始点与this的相同，但结束点比this 的要近。
    private Interval restrictLength(double length) {
      if (!Double.isInfinite(start) && !Double.isInfinite(end) &&
	  (end - start > length)) {
	return new Interval(start, start + length);
      } else if (!Double.isInfinite(start) && Double.isInfinite(end)) {
	return new Interval(start, start + length);
      } else {
	return this;
      }
    }

    // requires this overlaps with i, returns a new Interval which
    // contains the intersection of numbers from this and i.
    // 让this 与i 交叠，返还一个新的Interval 包括this 和i之间的交点
    private Interval restrictTo(Interval i) {
      if (!overlaps(i)) {
	throw new IllegalArgumentException();
      }
      return new Interval(start > i.start ? start : i.start,
			  end   < i.end   ? end   : i.end);
    }

    // compares based only on the ordering of the start value
    // 对比只以开始的值的顺序为基础
    public int compareTo(Interval i) {
        //Interval i = (Interval) o;
      if (this.start < i.start) {
	return -1;
      } else if (this.start > i.start) {
	return 1;
      } else {
	return 0;
      }
    }

    public boolean equals(Object o) {
      if (o instanceof Interval) {
	Interval i = (Interval) o;
	if (i == this) { return true; }
	if (this.start == i.start &&
	    this.end == i.end) {
	  return true;
	} else {
	  return false;
	}
      } else {
	return false;
      }
    }

    public int hashCode() {
      return (new Double(start)).hashCode();
    }

    public String toString() {
      return "[" + start + " - " + end + "]";
    }     
  }

  private final List<Interval> intervals;

  /**
   * @requires: end >= start
   *
   * @effects: creates a new IntervalList containing the range from
   * <code>start</code> to <code>end</code>.
   * 作用：创建一个新的IntervalList 包括的范围是<code>start</code> to <code>end</code>.
   **/
  public IntervalList(double start, double end) {
    intervals = new LinkedList<Interval>();
    intervals.add(new Interval(start, end));
  }

  /**
   * @effects: creates a new empty IntervalList (contains no numbers)
   * 作用： 创建一个新的空的IntervalList （不包括任何数）
   **/
   public IntervalList() {
    intervals = new LinkedList<Interval>();
  }

  /**
   * @effects: creates a new IntervalList which contains a copy of the
   * ranges represented by <code>il</code>.
   * 作用： 创建一个新的IntervalList 包括一个<code>il</code>描述范围的副本。
   **/   
  public IntervalList(IntervalList il) {
    intervals = new LinkedList<Interval>(il.intervals);
  }

  /**
   * @effects: returns true iff <code>this</code> represents the empty
   * set of intervals.
   * 返还真，当且仅当<code>this</code> 代表intervals的空集
   **/
  public boolean isEmpty() {
    return intervals.size() == 0;
  }

  /**
   * @effects: returns the lower bound of the lowest range of this, or
   * Double.NaN if this represents an empty set of intervals.
   * 返还this最低范围内下限，或者如果是intervals的空集的话返还Double.NaN
   **/
  public double min() {
    if (intervals.size() == 0) {
      return Double.NaN;
    }
    return ((Interval)intervals.get(0)).start();
  }

  /**
   * @effects: returns the upper bound of the highest range of this, or
   * Double.NaN if this represents an empty set of intervals.
   * 返还this最高范围内上限，或者如果是intervals的空集的话返还Double.NaN
   **/
  public double max() {
    if (intervals.size() == 0) {
      return Double.NaN;
    }
    return ((Interval)intervals.get(intervals.size()-1)).end();
  }

  /**
   * @effects: canonicalizes <code>intervals</code> to contain sorted
   * non-overlapping ranges.
   * 作用： 规范<code>intervals</code> 使其包括排序的non-overlapping 
   **/
  private void canonicalize() {
    Collections.sort(intervals);
    Interval lastElement;
    Interval currentElement;
    
    ListIterator<Interval> iter = intervals.listIterator();
    if (!iter.hasNext()) {
      return;
    }
    currentElement = iter.next();
    while (iter.hasNext()) {
      lastElement = currentElement;
      currentElement = iter.next();
      if (lastElement.overlaps(currentElement)) {
	Interval newElement = lastElement.merge(currentElement);
	iter.remove();
	iter.previous();
	iter.remove();
	iter.add(newElement);
	currentElement = newElement;
      }
    }
  }

  /**
   * @requires: end >= start
   *
   * @effects: adds the range from <code>start</code> to
   * <code>end</code> to this.
   * 把从<code>start</code>到<code>end</code> 的范围 加到this上
   **/  
  public void addInterval(double start, double end) {
    addIntervalInternal(start, end);
    canonicalize();
  }

  // adds the range from start to end to this, but does not canonicalize
  private void addIntervalInternal(double start, double end) {
    intervals.add(new Interval(start, end));
  }

  /**
   * @requires: end >= start
   *
   * @effects: removes the range of numbers from <code>start</code> to
   * <code>end</code> from this.
   * 把从<code>start</code>到<code>end</code> 的范围 从this上移除
   **/  
  public void removeInterval(double start, double end) {
    removeIntervalInternal(start, end);
    canonicalize();
  }

  // removes the range of numbers from start to end from this but does
  // not canonicalize
  // 移除this 里从开始到结束的range，但这并不起到规范的作用
  private void removeIntervalInternal(double start, double end) {
    ListIterator<Interval> iter = intervals.listIterator();
    Interval toRemove = new Interval(start, end);
    while (iter.hasNext()) {
      Interval curr = iter.next();
      if (curr.overlaps(toRemove)) {
	if (start > curr.start()) {
	  if (end < curr.end()) {
	    iter.remove();
	    iter.add(new Interval(curr.start(), start));
	    iter.add(new Interval(end, curr.end()));
	  } else {
	    iter.remove();
	    iter.add(new Interval(curr.start(), start));
	  }
	} else {
	  if (end < curr.end()) {
	    iter.remove();
	    iter.add(new Interval(end, curr.end()));
	  } else {
	    iter.remove();
	  }
	}
      }
    }
  }

  /**
   * @effects: adds to this all of numbers reprsented by
   * <code>il</code>.
   * 把code>il</code>.里所有的数加到this上
   **/
  public void addIntervalList(IntervalList il) {
    Iterator<Interval> iter = il.intervals.iterator();
    while (iter.hasNext()) {
      Interval i = iter.next();
      addIntervalInternal(i.start(), i.end());
    }
    canonicalize();
  }

  /**
   * @effects: removes from this all of the numbers represented by
   * <code>il</code>.
   * 把code>il</code>.里所有的数从this上减去
   **/
  public void removeIntervalList(IntervalList il) {
    Iterator<Interval> iter = il.intervals.iterator();
    while (iter.hasNext()) {
      Interval i = iter.next();
      removeIntervalInternal(i.start(), i.end());
    }
    canonicalize();
  }

  /**
   * @requires: end >= start
   *
   * @effects: removes from this all of the numbers which are not
   * between <code>start</code> and <code>end</code>.
   * 从this中移除所有不在 <code>start</code> and <code>end</code>.之间的数
   **/
  public void restrictToInterval(double start, double end) {
    restrictToInterval(new Interval(start, end));
  }

  /**
   * @effects: removes from this all of the numbers which are not
   * contained in the range represented by <code>i</code>.
   * 移除所有不在<code>i</code>表示的范围内的数。
   **/
  public void restrictToInterval(Interval i) {
    ListIterator<Interval> iter = intervals.listIterator();
    while (iter.hasNext()) {
      Interval curr = iter.next();
      if (curr.overlaps(i)) {
	iter.set(curr.restrictTo(i));
      } else {
	iter.remove();
      }
    }
  }
  
  /**
   * @effects: shrinks the size of each continous range of numbers
   * contained in this to only be of <code>length</code>.  Each
   * continous range will be aligned to have the same start it
   * originally had, but the end point will be no farther than
   * <code>length</code> away.
   * 缩短每个连续范围的大小，这些范围包括this 到<code>length</code>的数
   * 每个连续范围都会被排列使在同一起点，但结束点不远于<code>length</code> 
   **/
  public boolean restrictSubIntervalLength(double length) {
    // returns true if it changed  变了就返回真
    boolean changed = false;
    ListIterator<Interval> iter = intervals.listIterator();
    while (iter.hasNext()) {
      Interval curr = iter.next();
      Interval n = curr.restrictLength(length);
      if (!n.equals(curr)) {
	changed = true;
	iter.set(n);
      }
    }
    return changed;
  }

  /**
   * @effects: returns an Iterator which will return, in increasing
   * order,  Intervals representing the numbers contained in this.
   * 返还Iterator ，它会以递增的顺序返还代表this 里的数的Intervals 
   **/   
  public Iterator<Interval> iterator() {
    return (Collections.unmodifiableList(intervals)).iterator();
  }

  public boolean equals(Object o) {
    if (o instanceof IntervalList) {
      IntervalList il = (IntervalList) o;
      return intervals.equals(il.intervals);
    }
    return false;
  }

  public int hashCode() {
    return intervals.hashCode();
  }

  public String toString() {
    return intervals.toString();
  }
}
