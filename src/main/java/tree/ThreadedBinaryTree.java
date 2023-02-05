package tree;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;

/**
 * 线索二叉树：可以从任意一个节点开始遍历
 * 在一个普通二叉树的前/中/后序遍历中，在叶子节点保存节点的前后顺序
 *
 * 例如在中序线索化 （遍历顺序为左中右）
 * 1.该节点作为左子节点
 *  则他的前驱应该指向
 *      a. null(当其作为第一个遍历的元素)
 *      b. 或者是其爷爷节点
 *  他的后驱应该指向父节点
 * 2.该节点作为右子节点
 *  他的前驱应该指向他的父节点
 *  他的后驱应该指向
 *      a. 他的爷爷节点
 *      b. 或者是null (当他作为左后一个被遍历的元素)
 * 用代码实现 就是选择快慢指针，然后将
 *
 * 先许线索化 循环问题，
 * 由于先序遍历是根左右的顺序，首先会处理根节点，其左节点为空的情况下，
 * 会把爷爷节点作为前驱节点，形成了一个环 爷爷（根） -> 自己（根的左子树） -> 自己左子树（爷爷）
 * 然后会遍历左子树，也就是进入上面的循环，这里需要判断左子树是线索节点，就不进行遍历
 * @author hzs
 * @date 2022/10/30
 */
public class ThreadedBinaryTree {

    public static class ThreadedNode {
        // 左右节点
        public ThreadedNode lChild, rChild;
        // tag==0 左右节点 tag==1 线索
        public int lTag, rTag;
        public int data;

        public static ThreadedNode of(int data) {
            final ThreadedNode threadedNode = new ThreadedNode();
            threadedNode.data = data;
            return threadedNode;
        }

        public static ThreadedNode copy(ThreadedNode left) {
            if (left == null) {
                return null;
            }
            return ThreadedNode.of(left.data);
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    public static void main(String[] args) {
        final int[] data = {1, 2, 3, 4, 5, 6, -1, -1, 7};
        final ThreadedNode fullTree = getFullTree(data);
        System.out.println(fullTree);

        final ThreadedNode lastNode = InOrderThreadedAndReturnLastNode(fullTree, null);
        if (lastNode.rChild == null) {
            lastNode.rTag = 1;
        }
        System.out.println(fullTree);

        inOrderThreadTraverse(fullTree);
    }

    /**
     * 找到最左子节点
     */
    static ThreadedNode findFirst(ThreadedNode node) {
        while (node.lTag == 0) {
            node = node.lChild;
        }
        return node;
    }

    static ThreadedNode findNext(ThreadedNode node) {
        if (node == null) {
            return null;
        }
        if (node.rTag == 1) {
            return node.rChild;
        }else {
            return findFirst(node.rChild);
        }
    }

    /**
     * 中序线索化遍历一棵树
     * @param fullTree
     */
    private static void inOrderThreadTraverse(ThreadedNode fullTree) {
        fullTree = findFirst(fullTree);
        while (fullTree != null) {
            System.out.println(fullTree.data);
            fullTree = findNext(fullTree);
        }
      }

    /**
     * pre start is null
     */
    public static ThreadedNode InOrderThreadedAndReturnLastNode(ThreadedNode node, ThreadedNode preNode) {
        if (node.lChild == null) {
            node.lTag = 1;
            node.lChild = preNode;
        } else {
            preNode = InOrderThreadedAndReturnLastNode(node.lChild, preNode);
        }

        if (preNode != null && preNode.rChild == null) {
            preNode.rTag = 1;
            preNode.rChild = node;
        }
        if (node.rChild != null) {
            return InOrderThreadedAndReturnLastNode(node.rChild, node);
        }

        return node;
    }

    public static ThreadedNode getFullTree(int[] data) {
        int index = 0;
        final ThreadedNode root = ThreadedNode.of(data[index++]);
        final LinkedList<ThreadedNode> nodes = new LinkedList<>();
        nodes.push(root);

        while (index < data.length) {
            final ThreadedNode pop = nodes.pop();
            final ThreadedNode left = ThreadedNode.of(data[index++]);
            if (left.data != -1) {
                // 给当前节点追加左子节点
                pop.lChild = left;
                nodes.offer(left);
            }
            // 追加右子节点
            if (index < data.length) {
                final ThreadedNode right = ThreadedNode.of(data[index++]);
                if (right.data != -1) {
                    pop.rChild = right;
                    nodes.offer(right);

                }
            }
        }
        return root;
    }


}
