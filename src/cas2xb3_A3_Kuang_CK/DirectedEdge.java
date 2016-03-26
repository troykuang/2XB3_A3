package cas2xb3_A3_Kuang_CK;

public class DirectedEdge { 
    private final String v;
    private final String w;
    private final double weight;

    /**
     * Initializes a directed edge from vertex <tt>v</tt> to vertex <tt>w</tt> with
     * the given <tt>weight</tt>.
     * @param v the tail vertex
     * @param w the head vertex
     * @param weight the weight of the directed edge
     * @throws IndexOutOfBoundsException if either <tt>v</tt> or <tt>w</tt>
     *    is a negative integer
     * @throws IllegalArgumentException if <tt>weight</tt> is <tt>NaN</tt>
     */
    public DirectedEdge(String v, String w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Returns the tail vertex of the directed edge.
     * @return the tail vertex of the directed edge
     */
    public String from() {
        return v;
    }

    /**
     * Returns the head vertex of the directed edge.
     * @return the head vertex of the directed edge
     */
    public String to() {
        return w;
    }

    /**
     * Returns the weight of the directed edge.
     * @return the weight of the directed edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns a string representation of the directed edge.
     * @return a string representation of the directed edge
     */
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}