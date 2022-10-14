package graph;

/**
 * 顶点之间的最短距离 可以解决负权图 但是不能解决负权回路
 * DP问题 不经过中转节点的最短距离 -》经过v0节点的最短距离 -》经过v1,v0节点的最短距离 ---》经过V0,V1...VN个节点的最短距离
 * O(n^3)
 * 由两个矩阵构成  1 邻接矩阵记录各个节点之间的路径
 * 2 记录中转节点
 *
 * @author hzs
 * @date 2022/10/07
 */
public class Floyd {
    public static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int n = 5;

        final int[][] MGraph = new int[n][];
        MGraph[0] = new int[]{0, MAX, 1, MAX, 10};
        MGraph[1] = new int[]{MAX, 0, MAX, 1, 5};
        MGraph[2] = new int[]{MAX, 1, 0, MAX, 7};
        MGraph[3] = new int[]{MAX, MAX, MAX, 0, 1};
        MGraph[4] = new int[]{MAX, MAX, MAX, MAX, 0};

        final int[][] path = new int[n][];
        path[0] = new int[]{-1, -1, -1, -1, -1};
        path[1] = new int[]{-1, -1, -1, -1, -1};
        path[2] = new int[]{-1, -1, -1, -1, -1};
        path[3] = new int[]{-1, -1, -1, -1, -1};
        path[4] = new int[]{-1, -1, -1, -1, -1};

        // i 可中转的节点
        for (int transitPoint = 0; transitPoint < n; transitPoint++) {
            for (int j = 0; j < MGraph.length; j++) {
                for (int k = 0; k < MGraph[j].length; k++) {
                    // 判断添加中转点后 两个端点的距离是否减小
                    // 更新路径长度 修改中转点
                    if (MGraph[j][k] - MGraph[j][transitPoint] > MGraph[transitPoint][k]) {
                        MGraph[j][k] = MGraph[j][transitPoint] + MGraph[transitPoint][k];
                        path[j][k] = transitPoint;
                    }
                }
            }
        }

        PrintUtil.printMatrix(path);
        PrintUtil.printMatrix(MGraph);
    }


}
