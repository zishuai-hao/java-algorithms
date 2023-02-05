package graph;

import graph.entity.ArcNode;
import graph.entity.VNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 拓扑排序 有向无环图
 * 遍历时间序列的发生顺序
 * 1. 找到入度为0的节点加入队列
 * 2. 访问并记录顺序
 * 3. 删除该节点，回到第一步
 * <p>
 * 如果遍历完成后没有访问到所有节点，则说明该图不是无环图
 *
 * @author hzs
 * @date 2022/10/07
 */
public class TopologicalSort {
    public static void main(String[] args) {
        // 邻接表
        int n = 5;

        final ArrayList<VNode<String>> adjList = new ArrayList<>();
        adjList.add(0, new VNode<>("0"));
        adjList.add(1, new VNode<>("1"));
        adjList.add(2, new VNode<>("2"));
        adjList.add(3, new VNode<>("3"));
        adjList.add(4, new VNode<>("4"));
        adjList.get(0).firstArc = new ArcNode(1, 1, null);
        adjList.get(1).firstArc = new ArcNode(1, 3, null);
        adjList.get(2).firstArc = new ArcNode(1, 3, new ArcNode(1, 4, null));
        adjList.get(3).firstArc = new ArcNode(1, 4, new ArcNode(1, 1, null));
//        adjList.get(3).firstArc = new ArcNode(1, 4, null);
        adjList.get(4).firstArc = null;
        System.out.println(adjList);
        // 计算节点入度
        int[] inDegree = new int[n];
        for (VNode<String> vNode : adjList) {
            ArcNode firstArc = vNode.firstArc;
            while (firstArc != null) {
                inDegree[firstArc.adjIndex]++;
                firstArc = firstArc.nextArcNode;
            }
        }

        // 拓扑排序顺序
        final int[] print = new int[5];
        Arrays.fill(print,-1);
        int printIndex = 0;

        // 等待队列
        final LinkedList<Integer> waitList = new LinkedList<>();

        // 加入度为0的节点
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                waitList.push(i);
            }
        }

        while (!waitList.isEmpty()) {
            Integer pop = waitList.pop();
            print[printIndex++] = pop;

            // 删除该节点
            inDegree[pop] = -1;
            ArcNode firstArc = adjList.get(pop).firstArc;
            while (firstArc != null) {
                // push 度为0的节点
                if (--inDegree[firstArc.adjIndex] == 0) {
                    waitList.push(firstArc.adjIndex);
                }
                firstArc = firstArc.nextArcNode;
            }
        }

        if (printIndex != n) {
            System.err.println("拓扑排序失败，图中存在环路");
            for (int i = 0; i < inDegree.length; i++) {
                if (inDegree[i] != -1) {
                    System.out.println("简单回路：" + adjList.get(i));
                }
            }
        }

        System.out.println(Arrays.toString(inDegree));
        System.out.println(Arrays.toString(print));

    }
}
