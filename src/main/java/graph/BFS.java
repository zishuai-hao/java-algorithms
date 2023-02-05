package graph;

import java.util.LinkedList;

/**
 * 储结构：邻接矩阵
 * 广度优先遍历（有向无向）类似于层次遍历，每一次都会把该层的节点遍历完成之后，然后遍历下一层节点
 * 使用一个visited 数组保存访问状态
 * 为了避免多个连通分量以及有向图方向的问题，遍历visited数组，来决定BFS的起始节点
 *
 * @author hzs
 * @date 2022/10/06
 */
public class BFS {

    public static void main(String[] args) {
        // initialize data 邻接矩阵（adjacency matrix）
        final int[][] MGraph = new int[5][];
        MGraph[0] = new int[]{0, 1, 1, 0, 1};
        MGraph[1] = new int[]{1, 0, 0, 1, 1};
        MGraph[2] = new int[]{1, 0, 0, 1, 0};
        MGraph[3] = new int[]{0, 1, 1, 0, 1};
        MGraph[4] = new int[]{1, 1, 0, 1, 0};

        boolean[] visited = new boolean[5];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                BFS(MGraph, visited, i);
            }
        }
    }

    static void BFS(int[][] mGraph, boolean[] visited, int startIndex) {
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
            // 遍历出可到达且未被访问过的节点并且加入队列
            for (int i = 0; i < connect.length; i++) {
                if (connect[i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

    }


}
