package graph;

import java.util.Arrays;

/**
 * 迪科斯特拉算法  用于计算加权图的最短路径 注意不能有负权边
 * O(n^2)
 * <p>
 * 使用贪婪算法（question：暂时还不知道什么意思）的思想 来找到最权
 * 例图：
 * --6--> A   ------------->
 * ⬆️                ↘️ 1
 * 起点      ⬆️ 3                 终点
 * ⬆️                 ↗️ 5
 * --2--> B   -------------->
 * <p>
 * 维护两张表
 * 1。 一张是 costs 花费表 表示起点到目标节点需要花费的时间
 * 从起点开始 更新到达各个节点的时间 默认设置为无穷大
 * 有一个参数为是否已经处理 下面讲
 * |节点|最短路径|[是否已经处理|]
 * -------------
 * |A|∞|[processed|]
 * ------
 * |B|∞|[processed|]
 * ------
 * |end|∞|[processed|]
 * 2。另一张表是父节点表 parent表示到达各个节点的父节点
 * |节点|父节点|
 * -------------
 * |A|None|
 * ------
 * |B|None|
 * ------
 * |end|None|
 * <p>
 * 什么需要这两张表呢，第一张表 记录根节点着到达各个节点需要的时间，从起点开始 我们就知道 下图
 * |节点|最短路径|是否已经处理|
 * -------------
 * |A|6||
 * ------
 * |B|2||
 * ------
 * |end|∞||
 * <p>
 * dijkstra 算法要求我们这么做：
 * 1. 从起点开始
 * 2. 计算所有邻居的距离更新costs表，然后将其状态置为已处理(确认最短路径)， 然后找到距离根节点最近的未访问节点
 * 3. 继续进行2 算法（如果没有了 说明搜索结束 退出）
 * <p>
 * 算法要求每次遍历到的节点一定是距离最短的节点
 *
 * @author hzs
 * @date 2022/10/07
 */
public class Dijkstra {
    public static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int n = 5;
        final int[][] MGraph = new int[n][];
        MGraph[0] = new int[]{MAX, 10, MAX, MAX, 5};
        MGraph[1] = new int[]{MAX, MAX, 1, MAX, 2};
        MGraph[2] = new int[]{MAX, MAX, MAX, 4, MAX};
        MGraph[3] = new int[]{7, MAX, 6, MAX, MAX};
        MGraph[4] = new int[]{MAX, 3, 9, 2, MAX};

        int startIndex = 0;
        // 距离根节点的最短路径
        int[] cost = Arrays.copyOf(MGraph[startIndex],MGraph.length);
        boolean[] isArrived = new boolean[n];
        int[] parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            if (cost[i] == MAX) {
                parent[i] = -1;
            }
        }
        isArrived[startIndex] = true;

        calculate(startIndex, cost, isArrived, parent, MGraph);
        System.out.println("parent:" + Arrays.toString(parent));
        System.out.println("cost:" + Arrays.toString(cost));
    }

    private static void calculate(int startIndex, int[] cost, boolean[] isArrived, int[] path, int[][] MGraph) {
        int minIndex = -1;
        while (minIndex != startIndex) {
            minIndex = startIndex;
            // 找到成本最低的节点
            for (int i = 0; i < cost.length; i++) {
                if (cost[minIndex] > cost[i] && !isArrived[i]) {
                    minIndex = i;
                }
            }

            isArrived[minIndex] = true;
            System.out.println("当前最近的节点：" + minIndex);

            // 更新成本表 看看有没有距离主节点更近的路线
            for (int i = 0; i < cost.length; i++) {
                if (isArrived[i]) {
                    continue;
                }
//               当前成本大于新路线成本,则更新路线以及成本 cost[i] > cost[minIndex] + MGraph[minIndex][i]
                if (cost[i] - MGraph[minIndex][i] > cost[minIndex]) {
                    cost[i] = cost[minIndex] + MGraph[minIndex][i];
                    path[i] = minIndex;
                }
            }

        }
    }
}
