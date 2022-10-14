package graph;

import graph.entity.ArcNode;
import graph.entity.VNode;

import java.util.ArrayList;

/**
 * 邻接表存出度， 逆邻接表存入度
 * 使用邻接表实现
 * visited[]: 节点是否已经被访问
 * @author hzs
 * @date 2022/10/06
 */
public class DFS {
    public static void main(String[] args) {
//        initialize data 邻接表
        final ArrayList<VNode> adjList = new ArrayList<>();
        adjList.add(0, new VNode("A"));
        adjList.add(1, new VNode("B"));
        adjList.add(2, new VNode("C"));
        adjList.add(3, new VNode("D"));
        adjList.add(4, new VNode("E"));
        adjList.add(5, new VNode("F"));
        boolean[] visited = new boolean[6];
        adjList.get(0).firstArc = new ArcNode(5, 2, new ArcNode(15, 3, null));
        adjList.get(1).firstArc = new ArcNode(2, 0, new ArcNode(8, 4,null));
        adjList.get(2).firstArc = new ArcNode(15, 1, new ArcNode(7, 5, null));
        adjList.get(3).firstArc = null;
        adjList.get(4).firstArc = new ArcNode(4, 3,null);
        adjList.get(5).firstArc = new ArcNode(10, 3, new ArcNode(18, 4, null));
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                DFS(adjList, visited,i);
            }
        }
    }

    static void DFS(ArrayList<VNode> adjList, boolean[] visited, int index) {
        if (visited[index]) {
            return;
        } else {
            visited[index] = true;
        }
        final VNode vNode = adjList.get(index);
        System.out.println(vNode.name);
        ArcNode arc = vNode.firstArc;
        while (arc != null) {
            DFS(adjList, visited, arc.adjIndex);
            arc = arc.nextArcNode;
        }
    }


}

