package search;

import search.BinarySortTree.Node;

import static search.BinarySortTree.*;

/**
 * 平衡二叉树
 * 平衡因子 = 左子树-右子树高度 只可能是 -1，0，1
 * 插入节点保持平衡，以下平衡调整仅仅在一颗树的局部--最小不平衡树中操作
 * 平衡二叉树触发不平衡的条件： 左右子树的高度差超过2,则说明 当AH = H+1 BH=H时，A高度增加，则发生不平衡
 * LL A的左子节点(D)新增了一个节点 则右旋，以A为树的根，然后将A的右子树(C)划分给A的同级B的左子树 (因为 A<C<B)
 * DD A的右子节点(D)新增了一个节点，则左旋，以A为树的根，然后将A的左子树（C）划分给A的同级B的右子树
 * LR 先左后右
 * RL 先右后左
 * @author hzs
 * @date 2022/10/18
 */
public class AVLTree {

    public static void main(String[] args) {
        int[] data = new int[]{29, 13, 37, 7, 16, 32, 41, 10, 19, 33, 43};
        final Node node = create(data);
        node.right = null;
        final int avlTree = isAVLTree(node);
        System.out.println(avlTree);
        System.out.println(node);
    }

    /**
     * 是否为平衡二叉树
     * -1 不是
     */
    public static int isAVLTree(Node root) {
        if (root == null) {
            return 0;
        }
        int left = isAVLTree(root.left);
        int right = isAVLTree(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
