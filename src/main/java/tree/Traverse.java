package tree;

import java.util.LinkedList;
import java.util.Stack;

import static search.BinarySortTree.Node;

/**
 * @author hzs
 * @date 2022/10/19
 */
public class Traverse {

    public static void main(String[] args) {
        final int[] data = {1, 2, 3, 4, 5, 6, 7, 8};
        final Node fullTree = getFullTree(data);
        System.out.println(fullTree);
        final Stack<Node> stack = new Stack<>();
        stack.push(fullTree);
//        preorderTraverse(stack);

        InorderTraverse(fullTree);
        postorderTraverse(fullTree);
    }

    public static Node getFullTree(int[] data) {
        int index = 0;
        final Node root = Node.of(data[index++], null, null);
        final LinkedList<Node> nodes = new LinkedList<>();
        nodes.push(root);

        while (index < data.length) {
            final Node pop = nodes.pop();
            final Node left = Node.of(data[index++], null, null);
            // 给当前节点追加左子节点
            pop.left = left;
            nodes.offer(left);
            // 追加右子节点
            if (index < data.length) {
                final Node right = Node.of(data[index++], null, null);
                pop.right = right;
                nodes.offer(right);
            }
        }
        return root;
    }

    /**
     * 先序遍历（非递归
     *
     * @param stack 栈
     */
    public static void preorderTraverse(Stack<Node> stack) {
        // 退栈
        // 压入右节点
        // 压入左节点
        while (!stack.isEmpty()) {
            final Node pop = stack.pop();
            System.out.println(pop.data);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    // 中序遍历 左中右
    // 1.递归压入左子节点
    // 2.弹出节点，如果有右节点，那么将右子树做一次中序遍历
    public static void InorderTraverse(Node root) {
        // 左中右 所以只需要将
        // 1. 父节点入栈然后将左子节点入栈
        // [父 左 <--
        // 2 访问左节点
        // 3 访问父节点
        // 4 将右节点作为一个父节点重复 1
        final Stack<Node> stack = new Stack<>();
        Node p = root;

        // 不论右子树空不空均进入循环
        while (p != null || !stack.isEmpty() ) {

            // 首先应该处理最左子节点,依次将左节点压入栈
            while (p != null) {
                stack.push(p);
                p = p.left;
            }

            if (!stack.isEmpty()) {
                // 弹出节点，该节点的左子节点一定被访问（栈的原则）
                p = stack.pop();
                System.out.print(p.data+",");
                // 切换到右子树
                p = p.right;
            }
        }
        System.out.println();

    }

    /**
     * 左子树递归入栈，弹出，如果上一次访问的是左子树，就将右子树做一次后续遍历，否则直接弹出（左子树访问过了）
     * 后序遍历（非递归）
     * 1. 判断有没有左孩子然后压入栈
     * 2.判断栈顶元素有右孩子（或者temp是左孩子），就将右孩子压入栈 重复1
     * 3.当到达叶节点开始弹出，用一个变量temp记录该节点
     *      a. 如果是左节点，说明右节点还没有访问，压入栈 （在2.执行）
     *      b. 如果是右节点，说明左右节点都访问过了，直接把自己弹出去（在3执行）
     */
    public static void postorderTraverse(Node root) {
        Node p = root;
        final Stack<Node> stack = new Stack<>();

        Node preVisitedNode = null;
        while (p != null || !stack.isEmpty()) {
            // 1. 判断有没有左孩子然后压入栈
            if (p != null) {
                stack.push(p);
                p = p.left;
            }else {
                // look look 栈顶元素
                p = stack.peek();
                // 2.判断p是不是根节点，然后令右子树做一次后序遍历
                if (p.right != null && p.right != preVisitedNode) {
                    p = p.right;
                }
                else {
                    preVisitedNode = stack.pop();
                    System.out.print(preVisitedNode.data + ",");
                    p = null;
                }
            }
        }
        System.out.println();

    }

}
