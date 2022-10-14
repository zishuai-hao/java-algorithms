package graph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 广度优先算法 求一个节点到其他节点的最短路径 （无权图）
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
        final int[][] MGraph = new int[5][];
        MGraph[0] = new int[]{0, 1, 1, 0, 1};
        MGraph[1] = new int[]{1, 0, 0, 1, 1};
        MGraph[2] = new int[]{1, 0, 0, 1, 0};
        MGraph[3] = new int[]{0, 1, 1, 0, 1};
        MGraph[4] = new int[]{1, 1, 0, 1, 0};
        boolean[] visited = new boolean[5];
        final int[] bfs = BFS(MGraph, visited, 0);

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                BFS(MGraph, visited, i);
            }
        }
        System.out.println(Arrays.toString(bfs));
    }

    static int[] BFS(int[][] mGraph, boolean[] visited, int startIndex) {
        int[] dist = new int[mGraph.length];
        // 初始化一个队列（链表）
        final LinkedList<Integer> queue = new LinkedList<>();
        // 加入首节点
        queue.add(startIndex);
        visited[startIndex] = true;
        // 循环直到可访问节点遍历完毕
        while (!queue.isEmpty()) {
            // 弹出一个节点
            final Integer pop = queue.pop();
            System.out.println(pop);
            // 获取该节点的连接关系
            final int[] connect = mGraph[pop];
            // 遍历出可到达且未被访问过的节点
            for (int i = 0; i < connect.length; i++) {
                if (connect[i] != 0 && !visited[i]) {
                    visited[i] = true;
                    // 设置距离主节点的长度 + 1
                    dist[i] = dist[pop] + 1;
                    queue.add(i);
                }
            }
        }
        return dist;

    }
}
