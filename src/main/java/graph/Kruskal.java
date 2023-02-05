package graph;

import graph.entity.VNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * 最小生成树
 * 主要以边作为操作单元，每次选择权值最小的两条边且两端点不同属于一颗树进行连接
 * list<Node>:
 * -->Node: [weight][node1][node2]
 * 使用两个并查集 作为存储结构，将每个节点视为一颗树
 * kruskal
 * O(nlog2e)
 *
 * @author hzs
 * @date 2022/10/07
 */
public class Kruskal {

    // 并查集
    public static int[] father;

    static class Edge implements Comparable {
        int weight;
        VNode<Integer> start;
        VNode<Integer> end;

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public VNode<Integer> getStart() {
            return start;
        }

        public void setStart(VNode<Integer> start) {
            this.start = start;
        }

        public VNode<Integer> getEnd() {
            return end;
        }

        public void setEnd(VNode<Integer> end) {
            this.end = end;
        }

        public Edge(int weight, VNode<Integer> start, VNode<Integer> end) {
            this.weight = weight;
            this.start = start;
            this.end = end;
        }

        public static Edge of(int weight, VNode<Integer> start, VNode<Integer> end) {
            return new Edge(weight, start, end);
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Edge) {
                return Integer.compare(this.weight, ((Edge) o).weight);
            }
            return 0;
        }


    }

    public static void main(String[] args) {
        final ArrayList<Edge> edges = new ArrayList<>();
        edges.add(Edge.of(1, null, null));
        edges.add(Edge.of(3, null, null));
        edges.add(Edge.of(3, null, null));

        Collections.sort(edges);

        System.out.println(edges.stream().map(Edge::getWeight).collect(Collectors.toList()));

    }

    /**
     * 并查集 找到根节点
     */
    static int find(int nodeI) {
        if (father[nodeI] != nodeI) {
            return find(father[nodeI]);
        }
        return nodeI;
    }

    /**
     * 并查集 合并树
     */
    static void unite(Integer node1, Integer node2) {
        final int node2Fa = find(node2);
        final int node1Fa = find(node1);
        // 设置n1的父节点 作为 n2的父节点
        father[node2Fa] = node1Fa;
    }

//    public static void main(String[] args) {
//        int n = 6;
//        final ArrayList<Edge> results = new ArrayList<>(n - 1);
//
//        final ArrayList<Edge> edges = getEdges();
//
//        // 单项父节点表 当表中索引指向其他节点，则表示已经归属于另一个子树 最后一个空间留给生成树
//        int connect = 0;
//        father = new int[n];
//        for (int i = 0; i < father.length; i++) {
//            // 将自己设置为自己的父节点
//            father[i] = i;
//        }
//
//        for (final Edge edge : edges) {
//            // 判断这两个节点是否已经连接 只需要判断他们的父节点是否相同
//            if (find(edge.start.name) != find(edge.end.name)) {
//                // 连接这两个节点
//                unite(edge.start.name, edge.end.name);
//                results.add(edge);
//                connect++;
//                // 判断是否已经组成通路
//                if (connect == n - 1) {
//                    break;
//                }
//            }
//        }
//        for (Edge edge : results) {
//            System.out.printf("weight:%d  join: %s --> %s; ", edge.weight, edge.start.name, edge.end.name);
//        }
//        System.out.println("\r" + Arrays.toString(father));
//    }

    private static ArrayList<Edge> getEdges() {
        //      initialize data 邻接矩阵懒得写了
        final VNode<Integer> v0 = VNode.of(0);
        final VNode<Integer> v1 = VNode.of(1);
        final VNode<Integer> v2 = VNode.of(2);
        final VNode<Integer> v3 = VNode.of(3);
        final VNode<Integer> v4 = VNode.of(4);
        final VNode<Integer> v5 = VNode.of(5);

        // 使用一个数组来存储节点的父节点的索引
        final ArrayList<Edge> edges = new ArrayList<>();
        edges.add(Edge.of(1, v0, v3));
        edges.add(Edge.of(2, v2, v5));
        edges.add(Edge.of(3, v1, v4));
        edges.add(Edge.of(4, v2, v3));
        edges.add(Edge.of(4, v3, v5));
        edges.add(Edge.of(5, v0, v2));
        edges.add(Edge.of(5, v1, v3));
        edges.add(Edge.of(6, v0, v1));
        edges.add(Edge.of(6, v3, v4));
        edges.add(Edge.of(6, v4, v5));
        return edges;
    }
}

