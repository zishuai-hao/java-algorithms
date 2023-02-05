package graph.entity;

/**
 * 顶点对象(头节点)
 */
public class VNode<T> {
    public T name;
    public ArcNode firstArc;

    public VNode(T name, ArcNode firstArc) {
        this.name = name;
        this.firstArc = firstArc;
    }

    public VNode(T name) {
        this.name = name;
    }

    public static <T> VNode<T> of(T name) {
        return new VNode<>(name);
    }

    @Override
    public String toString() {
        return String.format("[%s] -> %s", name, firstArc == null ? "^" : firstArc.toString());
    }
}
