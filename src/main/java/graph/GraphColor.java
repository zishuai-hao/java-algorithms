package graph;

import java.util.Arrays;

/**
 * 涂色问题
 * 给相邻的国家上色，要求接壤的国家颜色不一样 4种颜色
 * 将这些国家的接壤关系构成一个邻接矩阵，国家一定是无向图
 * 使用DFS遍历一遍进行上色，上色时优先使用靠前的颜色，若接壤的国家有其他颜色，依次尝试下一个颜色
 *
 * @author hzs
 * @date 2022/11/18
 */
public class GraphColor {
    public static void main(String[] args) {
        final int[][] mGraph = new int[5][];
        mGraph[0] = new int[]{0, 1, 0, 0, 1};
        mGraph[1] = new int[]{1, 0, 0, 1, 1};
        mGraph[2] = new int[]{0, 0, 0, 1, 0};
        mGraph[3] = new int[]{0, 1, 1, 0, 1};
        mGraph[4] = new int[]{1, 1, 0, 1, 0};
        final int[] ints = graphColor(mGraph);
        System.out.println(Arrays.toString(ints));
    }


    public static int[] graphColor(int[][] graph) {
        int[] nodeColor = new int[graph.length];
        // 处理多个连通分量
        for (int i = 0; i < nodeColor.length; i++) {
            if (nodeColor[i] == 0) {
                dfsColor(graph, nodeColor, i);
            }
        }
        return nodeColor;
    }

    private static void dfsColor(int[][] graph, int[] nodeColor, int start) {
        // 染色
        for (int i = 1; i < 5; i++) {
            if (isSafe(graph, nodeColor, start, i)) {
                nodeColor[start] = i;
                break;
            }
        }
        // 深度遍历
        for (int i = 0; i < graph.length; i++) {
            if (nodeColor[i] == 0 && graph[start][i] == 1) { // 还未染色且有边
                dfsColor(graph, nodeColor, i);
            }
        }
    }

    /**
     * 判断颜色是否安全
     */
    private static boolean isSafe(int[][] graph, int[] nodeColor, int start, int color) {
        for (int j = 0; j < graph[start].length; j++) {
            if (graph[start][j] == 1 && nodeColor[j] == color) {
                return false;
            }
        }
        return true;
    }


}
