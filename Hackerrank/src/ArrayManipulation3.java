import java.util.Arrays;
import java.util.Scanner;


public class ArrayManipulation3 {

	
	
	
    public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();

//        int data[] = new int[n];
		int[] data = {100, 200, 250, 150};
        SegmentTree st = new SegmentTree(data);
        System.out.println(Arrays.toString(st.heap));
        System.out.println("------------");
        st.update(2, 3, 1);
        st.rsq(0, 3);

//        for(int i=0; i<m; i++) {
//        	int a = scanner.nextInt() - 1;
//        	int b = scanner.nextInt() - 1;
//        	int k = scanner.nextInt();
//        	st.update(a, b, k);
//        }
        
//        System.out.println(st.rsq(1, data.length));
        System.out.println(Arrays.toString(st.heap));

	}

	
	
	/**
	 * The {@code SegmentTree} class is an structure for efficient search of cummulative data.
	 * It performs  Range Minimum Query and Range Sum Query in O(log(n)) time.
	 * It can be easily customizable to support Range Max Query, Range Multiplication Query etc.
	 * <p>
	 * Also it has been develop with  {@code LazyPropagation} for range updates, which means
	 * when you perform update operations over a range, the update process affects the least nodes as possible
	 * so that the bigger the range you want to update the less time it consumes to update it. Eventually those changes will be propagated
	 * to the children and the whole array will be up to date.
	 * <p>
	 * Example:
	 * <p>
	 * SegmentTreeHeap st = new SegmentTreeHeap(new Integer[]{1,3,4,2,1, -2, 4});
	 * st.update(0,3, 1)
	 * In the above case only the node that represents the range [0,3] will be updated (and not their children) so in this case
	 * the update task will be less than n*log(n)
	 *
	 * Memory usage:  O(n)
	 *
	 * @author Ricardo Pacheco 
	 */
	public static class SegmentTree {

	    private Node[] heap;
	    private int[] array;
	    private int size;

	    /**
	     * Time-Complexity:  O(n*log(n))
	     *
	     * @param array the Initialization array
	     */
	    public SegmentTree(int[] array) {
	        this.array = Arrays.copyOf(array, array.length);
	        //The max size of this array is about 2 * 2 ^ log2(n) + 1
	        size = (int) (2 * Math.pow(2.0, Math.floor((Math.log((double) array.length) / Math.log(2.0)) + 1)));
	        heap = new Node[size];
	        build(1, 0, array.length);
	    }


	    public int size() {
	        return array.length;
	    }

	    //Initialize the Nodes of the Segment tree
	    private void build(int v, int from, int size) {
	        heap[v] = new Node();
	        heap[v].from = from;
	        heap[v].to = from + size - 1;

	        if (size == 1) {
	            heap[v].sum = array[from];
	            heap[v].min = array[from];
	            heap[v].max = array[from];
	        } else {
	            //Build childs
	            build(2 * v, from, size / 2);
	            build(2 * v + 1, from + size / 2, size - size / 2);

	            heap[v].sum = heap[2 * v].sum + heap[2 * v + 1].sum;
	            //min = min of the children
	            heap[v].min = Math.min(heap[2 * v].min, heap[2 * v + 1].min);
	            heap[v].max = Math.max(heap[2 * v].min, heap[2 * v + 1].min);
	        }
	    }

	    /**
	     * Range Sum Query
	     *
	     * Time-Complexity: O(log(n))
	     *
	     * @param  from from index
	     * @param  to to index
	     * @return sum
	     */
	    public int rsq(int from, int to) {
	        return rsq(1, from, to);
	    }

	    private int rsq(int v, int from, int to) {
	        Node n = heap[v];

	        //If you did a range update that contained this node, you can infer the Sum without going down the tree
	        if (n.pendingVal != null && contains(n.from, n.to, from, to)) {
	            return (to - from + 1) * n.pendingVal;
	        }

	        if (contains(from, to, n.from, n.to)) {
	            return heap[v].sum;
	        }

	        if (intersects(from, to, n.from, n.to)) {
	            propagate(v);
	            int leftSum = rsq(2 * v, from, to);
	            int rightSum = rsq(2 * v + 1, from, to);

	            return leftSum + rightSum;
	        }

	        return 0;
	    }

	    /**
	     * Range Min Query
	     * 
	     * Time-Complexity: O(log(n))
	     *
	     * @param  from from index
	     * @param  to to index
	     * @return min
	     */
	    public int rMinQ(int from, int to) {
	        return rMinQ(1, from, to);
	    }

	    private int rMinQ(int v, int from, int to) {
	        Node n = heap[v];


	        //If you did a range update that contained this node, you can infer the Min value without going down the tree
	        if (n.pendingVal != null && contains(n.from, n.to, from, to)) {
	            return n.pendingVal;
	        }

	        if (contains(from, to, n.from, n.to)) {
	            return heap[v].min;
	        }

	        if (intersects(from, to, n.from, n.to)) {
	            propagate(v);
	            int leftMin = rMinQ(2 * v, from, to);
	            int rightMin = rMinQ(2 * v + 1, from, to);

	            return Math.min(leftMin, rightMin);
	        }

	        return Integer.MAX_VALUE;
	    }


	    /**
	     * Range Update Operation.
	     * With this operation you can update either one position or a range of positions with a given number.
	     * The update operations will update the less it can to update the whole range (Lazy Propagation).
	     * The values will be propagated lazily from top to bottom of the segment tree.
	     * This behavior is really useful for updates on portions of the array
	     * <p>
	     * Time-Complexity: O(log(n))
	     *
	     * @param from  from index
	     * @param to    to index
	     * @param value value
	     */
	    public void update(int from, int to, int value) {
	        update(1, from, to, value);
	    }

	    private void update(int v, int from, int to, int value) {

	        //The Node of the heap tree represents a range of the array with bounds: [n.from, n.to]
	        Node n = heap[v];

	        /**
	         * If the updating-range contains the portion of the current Node  We lazily update it.
	         * This means We do NOT update each position of the vector, but update only some temporal
	         * values into the Node; such values into the Node will be propagated down to its children only when they need to.
	         */
	        if (contains(from, to, n.from, n.to)) {
	            change(n, value);
	        }

	        if (n.size() == 1) return;

	        if (intersects(from, to, n.from, n.to)) {
	            /**
	             * Before keeping going down to the tree We need to propagate the
	             * the values that have been temporally/lazily saved into this Node to its children
	             * So that when We visit them the values  are properly updated
	             */
	            propagate(v);

	            update(2 * v, from, to, value);
	            update(2 * v + 1, from, to, value);

	            n.sum = heap[2 * v].sum + heap[2 * v + 1].sum;
	            n.min = Math.min(heap[2 * v].min, heap[2 * v + 1].min);
	            n.max = Math.max(heap[2 * v].min, heap[2 * v + 1].min);
	        }
	    }

	    //Propagate temporal values to children
	    private void propagate(int v) {
	        Node n = heap[v];

	        if (n.pendingVal != null) {
	            change(heap[2 * v], n.pendingVal);
	            change(heap[2 * v + 1], n.pendingVal);
	            n.pendingVal = null; //unset the pending propagation value
	        }
	    }

	    //Save the temporal values that will be propagated lazily
	    private void change(Node n, int value) {
	        n.pendingVal = (n.pendingVal == null ? 0 : n.pendingVal) + value;
	        n.sum = n.sum + n.size() * value;
	        n.min = n.min + value;
	        n.max = n.max + value;
//	        array[n.from] = array[n.from] + value;

	    }

	    //Test if the range1 contains range2
	    private boolean contains(int from1, int to1, int from2, int to2) {
	        return from2 >= from1 && to2 <= to1;
	    }

	    //check inclusive intersection, test if range1[from1, to1] intersects range2[from2, to2]
	    private boolean intersects(int from1, int to1, int from2, int to2) {
	        return from1 <= from2 && to1 >= from2   //  (.[..)..] or (.[...]..)
	                || from1 >= from2 && from1 <= to2; // [.(..]..) or [..(..)..
	    }

	    //The Node class represents a partition range of the array.
	    class Node {
	        int max;
			int sum;
	        int min;
	        //Here We store the value that will be propagated lazily
	        Integer pendingVal = null;
	        int from;
	        int to;

	        int size() {
	            return to - from + 1;
	        }
	        
	        public String toString() {
	        	return String.format("[%s,%s][max=%s, min=%s, sum=%s]\n"
	        			, from, to, max, min, sum);
	        }

	    }
	}
}
