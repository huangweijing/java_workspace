package physics;

import physics.Geometry.DoublePair;

/****************************************************************************
 * Copyright (C) 2001 by the Massachusetts Institute of Technology,
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
 * @author: Jeremy Nimmer (jwnimmer@alum.mit.edu)
 *          Spring 2001
 *
 * Version: $Id: GeometryReference.java,v 1.1 2005/03/18 18:31:49 joy Exp $
 *
 ***************************************************************************/

/**
 * GeometryReference is a reference implementation of
 * GeometryInterface which can be used to check results from other
 * implementations.  This class is intended for debugging.
 * GeometryReference 用来核对其他接口的结果。这个类是抓bug用的。
 * @see physics.Geometry
 **/
public class GeometryReference
  extends SimpleGeometry
{

  /**
   * @returns a new instance of a reference Geometry object 
   * 返还一个新的Geometry 实例 ，作为参数
   **/
  public GeometryReference()
  {
    super(Double.POSITIVE_INFINITY, 300);
  }

}
