package graph.entity;

public class ArcNode {
    public int data;
    /**
     * 指向节点的索引
     */
    public int adjIndex;
    /**
     * 指向下一个弧
     */
    public ArcNode nextArcNode;

    public ArcNode(int data, int adjIndex, ArcNode nextArcNode) {
        this.data = data;
        this.adjIndex = adjIndex;
        this.nextArcNode = nextArcNode;
    }

    @Override
    public String toString() {
        return String.format("%s:%d -> %s", adjIndex, data, nextArcNode == null ? "^" : nextArcNode.toString());
    }
}
