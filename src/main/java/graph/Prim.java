package graph;

import graph.entity.ArcNode;
import graph.entity.VNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最小生成树
 * isJoin[] : 是否加入了生成树
 * lowCost[] ： 最低代价
 * O(n^2)
 *
 * @author hzs
 * @date 2022/10/07
 */
public class Prim {

    /**
     * 距离生成树的距离 辅助数组
     */
    static class CloseEdge {
        // 最接近的节点
        int nodeIndex;
        // 最低的成本 0表示已经合并
        int lowCost;

        public static CloseEdge of(int nodeIndex, int lowCost) {
            final CloseEdge closeEdge = new CloseEdge();
            closeEdge.nodeIndex = nodeIndex;
            closeEdge.lowCost = lowCost;
            return closeEdge;
        }

        @Override
        public String toString() {
            return "CloseEdge{" +
                    "nodeIndex=" + nodeIndex +
                    ", lowCost=" + lowCost +
                    '}';
        }
    }

    public static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        final List<VNode> vNodes = new ArrayList<>();
        adjust(vNodes);
        int nodeSize = 6;
        final int[][] graph = new int[nodeSize][nodeSize];
        graph[0] = new int[]{MAX, 6, 5, 1, MAX, MAX};
        graph[1] = new int[]{6, MAX, MAX, 5, 3, MAX};
        graph[2] = new int[]{5, MAX, MAX, 4, MAX, 2};
        graph[3] = new int[]{1, 5, 4, MAX, 6, 4};
        graph[4] = new int[]{MAX, 3, MAX, 6, MAX, 6};
        graph[5] = new int[]{MAX, MAX, 2, 4, 6, MAX};
        CloseEdge[] lowCost = new CloseEdge[nodeSize];
        int mergedIndex = 0;
        // 初始化辅助表
        for (int i = 1; i < nodeSize; i++) {
            lowCost[i] = CloseEdge.of(mergedIndex, graph[mergedIndex][i]);
        }
        System.out.println(Arrays.toString(lowCost));

        lowCost[mergedIndex] = CloseEdge.of(mergedIndex, 0);
        for (int i = 1; i < nodeSize; i++) {
            // search next node
            int minIndex = -1;
            for (int j = 1; j < lowCost.length; j++) {
                CloseEdge closeEdge = lowCost[j];
                if (closeEdge.lowCost == 0) {
                    continue;
                }
                if (minIndex == -1 || lowCost[minIndex].lowCost > closeEdge.lowCost) {
                    minIndex = j;
                }
            }

            // join minimumCostSpanningTree
            lowCost[minIndex].lowCost = 0;
            System.out.printf("join %d --> %d%n \r", lowCost[minIndex].nodeIndex, minIndex);

            // refresh cost auxiliary table
            for (int t = 0; t < nodeSize; t++) {
                if (lowCost[t].lowCost > graph[minIndex][t]) {
                    lowCost[t].nodeIndex = minIndex;
                    lowCost[t].lowCost = graph[minIndex][t];
                }
            }
        }

        System.out.println(Arrays.toString(lowCost));
    }

    private static void adjust(List<VNode> vNodes) {
        vNodes.add(new VNode("v0",
                new ArcNode(6, 1,
                        new ArcNode(5, 2,
                                new ArcNode(5, 3, null)))));
        vNodes.add(new VNode("v1",
                new ArcNode(6, 0,
                        new ArcNode(5, 3,
                                new ArcNode(3, 4, null)))));
        vNodes.add(new VNode("v2",
                new ArcNode(5, 0,
                        new ArcNode(4, 3,
                                new ArcNode(2, 5, null)))));
        vNodes.add(new VNode("v3", new ArcNode(1, 0, new ArcNode(5, 1, new ArcNode(4, 2, new ArcNode(6, 4, new ArcNode(4, 5, null)))))));
//        vNodes.add(new VNode("v4", new ArcNode(5, )));
//        vNodes.add(new VNode("v5", new ArcNode(5, )));
    }

}
