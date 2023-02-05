package graph;

import graph.entity.ArcNode;
import graph.entity.VNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 关键路径
 * 拓扑排序+逆拓扑排序
 * ve[]: 事件发生的最早开始时间 拓扑排序
 * vl[]: 事件发生的最迟开始时间 逆拓扑排序
 * e[]=ve[]: 活动发生的最早时间=弧尾事件的最早发生事件
 * l[]=vl[]: 活动最晚发生事件=事件最迟结束时间-该活动需要花费的事件
 * d[] = l[]-e[]: 时间余量
 * https://blog.csdn.net/qq_44880154/article/details/114380592
 * @author hzs
 * @date 2022/10/15
 */
public class CriticalPath {

}
