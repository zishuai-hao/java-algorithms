package graph;

import java.util.Arrays;

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
        int nodeSize = 6;
        final int[][] graph = new int[nodeSize][nodeSize];
        graph[0] = new int[]{0, 6, 5, 1, MAX, MAX};
        graph[1] = new int[]{6, 0, MAX, 5, 3, MAX};
        graph[2] = new int[]{5, MAX, 0, 4, MAX, 2};
        graph[3] = new int[]{1, 5, 4, 0, 6, 4};
        graph[4] = new int[]{MAX, 3, MAX, 6, 0, 6};
        graph[5] = new int[]{MAX, MAX, 2, 4, 6, 0};
        CloseEdge[] lowCost = new CloseEdge[nodeSize];

        int mergedIndex = 0;
        // 初始化辅助表
        for (int i = 0; i < nodeSize; i++) {
            lowCost[i] = CloseEdge.of(mergedIndex, graph[mergedIndex][i]);
        }

        for (int i = 1; i < nodeSize; i++) {
            // search next node
            int minIndex = getMinIndex(lowCost);

            // join minimumCostSpanningTree
            lowCost[minIndex].lowCost = 0;

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

    /**
     *
     * @param lowCost 成本表
     * @return
     */
    private static int getMinIndex(CloseEdge[] lowCost) {
        int minIndex = -1;
        for (int j = 0; j < lowCost.length; j++) {
            CloseEdge closeEdge = lowCost[j];
            if (closeEdge.lowCost == 0) {
                continue;
            }
            if (minIndex == -1 || lowCost[minIndex].lowCost > closeEdge.lowCost) {
                minIndex = j;
            }
        }
        return minIndex;
    }


}
