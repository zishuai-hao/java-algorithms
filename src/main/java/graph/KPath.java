package graph;

import graph.entity.ArcNode;
import graph.entity.VNode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 邻接表存储，判断是否存在一条长度为k的简单路径
 * 回溯+剪枝
 *
 * @author hzs
 * @date 2022/11/16
 */
public class KPath {
    public static void main(String[] args) {
        final ArrayList<VNode<String>> adjList = new ArrayList<>();
        adjList.add(0, new VNode<>("A"));
        adjList.add(1, new VNode<>("B"));
        adjList.add(2, new VNode<>("C"));
        adjList.add(3, new VNode<>("D"));
        adjList.add(4, new VNode<>("E"));
        adjList.add(5, new VNode<>("F"));

        adjList.get(0).firstArc = new ArcNode(5, 2, new ArcNode(15, 3, null));
        adjList.get(1).firstArc = new ArcNode(2, 0, new ArcNode(8, 4, null));
        adjList.get(2).firstArc = new ArcNode(15, 1, new ArcNode(7, 5, null));
        adjList.get(3).firstArc = null;
        adjList.get(4).firstArc = new ArcNode(4, 3, null);
        adjList.get(5).firstArc = new ArcNode(10, 3, new ArcNode(18, 4, null));

        // java里的双端队列

        final boolean[] isVisited = new boolean[adjList.size()];
        System.out.println(hasKPath(adjList, 2, 2, 4, isVisited));
        System.out.println(Arrays.toString(isVisited));
    }

    /**
     * 回溯法实现
     */
    public static boolean hasKPath(ArrayList<VNode<String>> adjList, int k, int source, int target, boolean[] isVisited) {
        // 当前路径长度为k时，判断有没有到达
        if (k == 0) {
            return source == target;
        }

        // 获取该节点的边
        final VNode<String> vNode = adjList.get(source);
        isVisited[source] = true;
        ArcNode arc = vNode.firstArc;

        while (arc != null && !isVisited[arc.adjIndex]) { // 也可以使用visited数组
            // 从下一个节点开始，是否有k-1阶路径可以到达
            boolean b = hasKPath(adjList, k - 1, arc.adjIndex, target, isVisited);
            if (b) return b;
            arc = arc.nextArcNode;
        }

        isVisited[source] = false;
        return false;
    }
}
