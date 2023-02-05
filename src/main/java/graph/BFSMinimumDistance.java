package graph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 广度优先算法 单源点最短路径 （无权图）
 * distance[]: 距离根节点路径
 * path[]: 直接前驱
 * visited[]:访问状态
 *
 * @author hzs
 * @date 2022/10/07
 */
public class BFSMinimumDistance {

    public static void main(String[] args) {
        // initialize data 邻接矩阵（adjacency matrix）
        final int[][] mGraph = new int[5][];
        mGraph[0] = new int[]{0, 1, 0, 0, 1};
        mGraph[1] = new int[]{1, 0, 0, 1, 1};
        mGraph[2] = new int[]{0, 0, 0, 1, 0};
        mGraph[3] = new int[]{0, 1, 1, 0, 1};
        mGraph[4] = new int[]{1, 1, 0, 1, 0};

        // 访问状态
        boolean[] visited = new boolean[5];
        // 距离根节点的路径长度
        int[] dist = new int[mGraph.length];
        // 直接前驱
        int[] path = new int[mGraph.length];

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                BFS(mGraph, visited,dist,path, i);
            }
        }
        System.out.println(Arrays.toString(path));
    }

    static void BFS(int[][] mGraph, boolean[] visited, int[] dist, int[] path, int startIndex) {

        // 初始化一个队列（链表）
        final LinkedList<Integer> queue = new LinkedList<>();
        // 加入首节点
        queue.add(startIndex);
        visited[startIndex] = true;
        // 循环直到可访问节点遍历完毕
        while (!queue.isEmpty()) {

            final Integer pop = queue.pop();
            // 获取该节点的连接关系
            final int[] connect = mGraph[pop];

            // 遍历出可到达且未被访问过的节点
            for (int i = 0; i < connect.length; i++) {
                if (connect[i] != 0 && !visited[i]) {
                    visited[i] = true;
                    // 设置距离主节点的长度 + 1
                    dist[i] = dist[pop] + 1;
                    path[i] = pop;
                    queue.add(i);
                }
            }
        }

    }
}
