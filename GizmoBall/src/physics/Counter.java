package physics;

import java.util.*;

/**
 * A counter is a mutable ADT used to instrument code to
 * measure the time of blocks of code.
 * 计数器是一个可变的自动数据转换器，用它来计算运行代码的时间。
 *
 * @specfield    name              : String   // a unique name for a counter 计数器的唯一名字
 * @specfield    totalTime         : long     // total time of all measurements taken  测出的时间总和
 * @specfield    totalInvocations  : int      // total number of measurements taken  所测的总个数
 * @derivedfield averageTime       : double   // average time of all measurements (totalTime / totalInvocations)所测的平均时间
 * @specfield    recursionDetected : boolean  // true iff begin was ever called twice without an end inbetween 
 *                                            // 当且仅当两次被叫开始中间无结束时为真
 * @endspec
 *
 * <p>Example Use:
 * <p>Existing code:<p>
 * <pre>
 * public class ComputationallyExpensive
 * {
 *   public double compute(double a, double b, double c)
 *   {
 *     double total = 0.0;
 *     double result;
 *     // ... 
 *     while (condition) {
 *       // ... 
 *       total += helperCompute(a + b, a - c);
 *       // ... 
 *     }
 *     // ... 
 *     return result;
 *   }
 *   private double helperCompute(double x, double y)
 *   {
 *     return Math.sqrt(x * x + y * y);
 *   }
 * }
 * public class Main
 * {
 *   public static void main(String[] args)
 *   {
 *     System.out.println("Computation is " + (new ComputationallyExpensive()).compute(1.0, 2.0, 1.0));
 *   }
 * }
 * </pre>
 * <p>Instrumented code:<p>
 * <pre> 
 * public class ComputationallyExpensive
 * {
 *   <font color="blue">private final static Counter computeCounter = new Counter("compute");</font>
 *   public double compute(double a, double b, double c)
 *   {
 *     <font color="blue">computeCounter.begin();</font>
 *     double total = 0.0;
 *     double result;
 *     // ... 
 *     while (condition) {
 *       // ... 
 *       total += helperCompute(a + b, a - c);
 *       // ... 
 *     }
 *     // ... 
 *     <font color="blue">computeCounter.end();</font>
 *     return result;
 *   }
 *   <font color="blue">private final static Counter helperComputeCounter = new Counter("helperCompute");</font>
 *   private double helperCompute(double x, double y)
 *   {
 *     <font color="blue">helperComputeCounter.begin();</font>
 *     <font color="blue">double result =</font> Math.sqrt(x * x + y * y);
 *     <font color="blue">helperComputeCounter.end();</font>
 *     return <font color="blue">result</font>;
 *   }
 * }
 * public class Main
 * {
 *   public static void main(String[] args)
 *   {
 *     System.out.println("Computation is " + (new ComputationallyExpensive()).compute(1.0, 2.0, 1.0));
 *     <font color="blue">System.out.println("Instrumentation results:\n" + Counter.getAllResults());</font>
 *   }
 * }
 * </pre>
 * <p>Output of Main.main():
 * <pre>
 *   Computation is 3.2837652137612
 *   Instrumentation results:
 *   compute had 1 invocations over 5240ms, averaging 5240ms each
 *   helperCompute had 100 invocations over 4240ms, averaging 42.4ms each      
 * </pre>
 * <p>Intepreting the results:
 * <p>The time spent in compute() was 5.24 seconds, and the time spent in
 * helperCompute was 4.24 seconds.  Note that since compute() is calling
 * helperCompute(), to find out the time spent <i>only</i> in compute(),
 * you have to subtract out the time spent in helperCompute().  Therefore,
 * if helperCompute() is the only other method which compute() calls, we
 * now know that the time spent on code found in compute() was 1.0s, and
 * the time spent on code found in helperCompute() was 4.24s.
 *
 * <p>NOTE: This implementation of a Counter is not useful for
 * instrumentation of multi-threaded code.
 */
public final class Counter
{

  // ============================== STATIC BEHAVIOR 静态行为==============================

  // static members
  private final static Map<String, Counter> counters = 
      new HashMap<String, Counter>(); // String -> Counter

  //
  // RI(s) =
  //   (s.counters != null) &&
  //   (all k in s.counters.keySet : k != null && k instanceof String) &&
  //   (all v in s.counters.values : v != null && v instanceof Counter) &&
  //   (all <k, v> in s.counters.entrySet : k = v.blockName)
  //

  //
  // AF(s) =
  //   s.counters.values is the set of all Counters ever instantiated
  //

  /**
   * @return Collection of Counters containing all Counters ever created   
   * 返还所有被生成的计器的集合
   */
  public final static Collection<Counter> getCounters()
  {
    return Collections.unmodifiableCollection(counters.values());
  }

  /**
   * @return concatenation of c.getResult() for all Counters ever created
   *为所有被生成的计数器返还c.getResult()的串联 
   */
  public final static String getAllResults()
  {
    StringBuffer results = new StringBuffer();

    Iterator counters = getCounters().iterator();
    while (counters.hasNext()) {
      Counter counter = (Counter) counters.next();
      results.append(counter.getResult());
      results.append("\n");
    }

    return results.toString();
  }

  // ============================== NON-STATIC BEHAVIOR 非静态行为==============================

  private final String blockName;
  private int totalInvocations;
  private long totalTime;
  private boolean recursionDetected;
  private int beginDepth;       // = [# calls to begin()] - [# calls to end()]
  private long beginTime;       // currentTime() when beginDepth became non-zero 当beginDepth变为非零时调用 currentTime()

  //
  // RI(c) = 
  //   (blockName != null) &&
  //   (beginDepth >= 0) &&
  //   (totalInvocations >= 0) &&
  //   (totalTime >= 0)
  //

  //
  // AF(c) = 
  //   specfield name              = this.blockName
  //   specfield totalInvocations  = this.totalInvocations
  //   specfield totalTime         = this.totalTime
  //   specfield recursionDetected = this.recursionDetected

  // ============================== CONSTRUCTORS构造器 ==============================

  /**
   * Constructs a Counter with the given block name 用已给出的block 的名字构造一个计数器
   * @param blockName name to use for this counter 把blockName名字给this counter
   * @exception IllegalArgumentException if <tt>blockName</tt> is null
   * 如果<tt>blockName</tt> 为空则抛出异常IllegalArgumentException 
   * @exception IllegalArgumentException if the constructor has already been called with the given <tt>blockName</tt>
   * 如果构造器已经被已给出的<tt>blockName</tt>调用则抛出异常IllegalArgumentException
   */
  public Counter(String blockName)
  {
    if (blockName == null) {
      throw new IllegalArgumentException("blockName cannot be null");
    }
    if (counters.containsKey(blockName)) {
      throw new IllegalArgumentException("The name " + blockName + " has already been used");
    }

    this.blockName = blockName;
    this.beginDepth = 0;
    this.recursionDetected = false;
    this.totalInvocations = 0;
    this.totalTime = 0;

    // add ourself to the global collection of Counters
    counters.put(blockName, this);
  }

  // ============================== MUTATORS ==============================

  /**
   * @effects starts a measurement using this counter 用这个计数器开始一个测量
   */
  public final void begin()
  {
    // start the timer iff it has not already been started
    beginDepth++;
    if (beginDepth == 1)
      beginTime = currentTime();
    else
      recursionDetected = true;
  }

  /**
   * @effects stops a measurement using this counter 使用这个计数器来停止一个测量
   * @exception IllegalStateException if begin() has not been called more times than end() on this counter
   * 如果begin()被调用的次数没有end()多，则抛出异常 IllegalStateException 
   */
  public final void end()
  {
    if (beginDepth <= 0)
      throw new IllegalStateException("end without begin");

    // stop the timer iff this is the last matching end()
    beginDepth--;
    if (beginDepth == 0) {
      long duration = currentTime() - beginTime;
      totalTime += duration;
      totalInvocations++;
    }
  }

  /**
   * @effects returns this counter to its original state 使计数器返回到最初状态
   * @exception IllegalStateException if the number of calls to begin() and end() was not equal
   * 如果begin()被调用的次数与end()不相等则抛出异常 IllegalStateException 
   */
  public void reset()
  {
    assertMatched();
    uncheckedReset();
  }

  // same as reset() except does not check matched begin/end 如果reset()没有核对begin/end是否匹配，则同上
  private void uncheckedReset()
  {
    recursionDetected = false;
    totalInvocations = 0;
    totalTime = 0;
  }

  // ============================== OBSERVERS观察者 ==============================

  /**
   * @return this.totalInvocations
   * @exception IllegalStateException if the number of calls to begin() and end() was not equal
   * 如果begin()被调用的次数与end()不相等则抛出异常 IllegalStateException 
   */
  public final int getTotalInvocations()
  {
    assertMatched();
    return totalInvocations;
  }

  /**
   * @return this.totalTime
   * @exception IllegalStateException if the number of calls to begin() and end() was not equal
   * 如果begin()被调用的次数与end()不相等则抛出异常 IllegalStateException 
   */
  public final long getTotalTime()
  {
    assertMatched();
    return totalTime;
  }

  /**
   * @return this.averageTime
   * @exception IllegalStateException if the number of calls to begin() and end() was not equal
   * 如果begin()被调用的次数与end()不相等则抛出异常 IllegalStateException 
   */
  public final double getAverageTime()
  {
    assertMatched();
    return ((double) totalTime) / ((double) totalInvocations);
  }

  /**
   * @return this.recursionDetected
   * @exception IllegalStateException if the number of calls to begin() and end() was not equal
   * 如果begin()被调用的次数与end()不相等则抛出异常 IllegalStateException 
   */
  public final boolean isRecursionDetected()
  {
    assertMatched();
    return recursionDetected;
  }
  
  /**
   * @return a String summarizing the results of this counter 返还一个String总汇一下计数器的结果
   * @exception IllegalStateException if the number of calls to begin() and end() was not equal
   * 如果begin()被调用的次数与end()不相等则抛出异常 IllegalStateException 
   */
  public String getResult()
  {
    return blockName + " had " +
      getTotalInvocations() + " invocations over " +
      getTotalTime() + "ms, averaging " +
      prettyPrintDouble(getAverageTime(), 1) + "ms each" +
      (recursionDetected ? "; recursion was detected" : "");
  }

  // ============================== HELPERS ==============================

  private void assertMatched()
  {
    if (beginDepth != 0) {
      uncheckedReset();
      beginDepth = 0;
      beginTime = 0;
      throw new IllegalStateException("begin was called " + beginDepth + " more times than end");
    }
  }

  // returns the current time, in milliseconds, from a
  //   fixed point in the past
  private final long currentTime()
  {
    return System.currentTimeMillis();
  }

  // returns a string representation of d, with at most
  //   'decimal' places after the decimal point
  private final String prettyPrintDouble(double d, int decimals)
  {
    int factor = 1;
    while (decimals > 0) {
      factor = factor * 10;
      decimals--;
    }
    return String.valueOf(Math.round(d * factor) / ((double) factor));
  }

}
