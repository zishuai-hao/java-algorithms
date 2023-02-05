package graph;

import graph.entity.ArcNode;
import graph.entity.VNode;

import java.util.ArrayList;

/**
 * 邻接表转逆邻接表
 *
 * @author hzs
 * @date 2022/11/28
 */
public class AdjList2Inverse {
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


        final ArrayList<VNode<String>> inverse = new ArrayList<>();
        for (VNode<String> vNode : adjList) {
            inverse.add(VNode.of(vNode.name));
        }

        // 遍历每个节点的出度，并将其作为入度存到逆邻接表
        for (int i = 0; i < adjList.size(); i++) {
            VNode<String> origin = adjList.get(i);
            ArcNode arc = origin.firstArc;
            while (arc != null) {
                final VNode<String> vNode = inverse.get(arc.adjIndex);
                // 头插法
                vNode.firstArc = new ArcNode(arc.data, i, vNode.firstArc);
                // 处理下一个出度
                arc = arc.nextArcNode;
            }
        }
        System.out.println(inverse);
    }
}
