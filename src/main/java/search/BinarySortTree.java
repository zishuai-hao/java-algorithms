package search;

import com.alibaba.fastjson.JSON;

import java.util.Stack;

/**
 * 查找长度
 *
 * @author hzs
 * @date 2022/10/16
 */
public class BinarySortTree {

    public static class Node {
        public int data;
        public Node right;
        public Node left;

        public static Node of(int data, Node right, Node left) {
            final Node node = new Node();
            node.data = data;
            node.left = left;
            node.right = right;
            return node;
        }

        @Override
        public String toString() {
//            return "Node{" +
//                    "data=" + data +
//                    ", right->" + right +
//                    ", left->" + left +
//                    '}';
            return JSON.toJSONString(this);
        }
    }


    /**
     * 最左下节点，中序遍历中第一个遍历的节点
     *
     * @param root
     * @return
     */
    public static Node searchLeft(Node root) {
        if (root.left != null) {
            return searchLeft(root.left);
        }
        return root;
    }

    /*
    Node{data=29,
        right->Node{data=37,
            right->Node{data=41,
                right->Node{data=43,
                    right->null,
                    left->null},
                left->null},
            left->Node{data=32,
                right->Node{data=33,
                    right->null,
                    left->null},
                left->null}
            },
        left->Node{data=13,
            right->Node{data=16,
                right->Node{data=19,
                    right->null,
                    left->null},
                left->null},
            left->Node{data=7,
                right->Node{data=10,
                    right->null,
                    left->null},
                left->null}
            }
        }



     */

    public static void main(String[] args) {
        int[] data = new int[]{29, 13, 37, 7, 16, 32, 41, 10, 19, 33, 43};

        Node root = create(data);
//        System.out.println(search(root, 7));
        root = delete(root, 29);
        root = delete(root, 16);
        insert(root, 29);
        insert(root, 16);
//        System.out.println(root);

        final Node of = Node.of(100, Node.of(10, null, null), null);

        System.out.println(isBinarySortTree(root));
    }

    public static Node create(int[] data) {
        Node root = null;
        for (int datum : data) {
            if (root == null) {
                root = Node.of(datum, null, null);
            } else {
                append(root, datum);
            }
        }
        return root;
    }

    private static void append(Node root, int datum) {
        a:
        while (root != null) {
            final int compare = Integer.compare(root.data, datum);
            switch (compare) {
                case 0:
                case -1:
                    if (root.right == null) {
                        root.right = Node.of(datum, null, null);
                        break a;

                    } else {
                        root = root.right;
                    }
                    break;
                default:
                    if (root.left == null) {
                        root.left = Node.of(datum, null, null);
                        break a;
                    } else {
                        root = root.left;
                    }
            }
        }
    }

    public static Node search(Node root, int data) {
        if (root == null) {
            return null;
        }
        final int compare = Integer.compare(root.data, data);
        switch (compare) {
            case 0:
                return root;
            case 1:
                return search(root.left, data);
            default:
                return search(root.right, data);
        }
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return Node.of(data, null, null);
        }

        final int compare = Integer.compare(root.data, data);
        switch (compare) {
            case -1:
            case 0:
                root.right = insert(root.right, data);
                break;
            default:
                root.left = insert(root.left, data);
        }
        return root;
    }

    /**
     * @param root 当前树的根节点
     * @param data 需要删除的节点
     * @return 被节点删除后的树
     */
    public static Node delete(Node root, int data) {
        if (root == null) {
            return null;
        }
        final int compare = Integer.compare(root.data, data);
        // 比较删除节点的位置
        switch (compare) {
            case 0:
                // 若没有左（右）子树，直接令右（左）子树上移
                if (root.right == null) {
                    return root.left;
                } else if (root.left == null) {
                    return root.right;
                } else {
                    // 若两个子树均存在，由于中序遍历下二叉树一定是有序的
                    // 所以我们令该节点在中序遍历的直接后继（右子树的最左子节点）移动到该节点位置，然后删除原来位置伤的后继节点
                    // 中序遍历下，该后继节点一定没有左子树
                    // 对right中序遍历得到第一个节点即为A 右子树的最小节点，将该节点在树中移除(delete) 然后 将该节点顶替 root
                    final Node node = searchLeft(root.right);
                    root.right = delete(root.right, node.data);
                    node.left = root.left;
                    node.right = root.right;
                    return node;
                }
            case 1:
                root.left = delete(root.left, data);
                break;
            default:
                root.right = delete(root.right, data);
        }
        return root;
    }


    // 左 中 右
    public static boolean isBinarySortTree(Node root) {
        final Stack<Node> stack = new Stack<>();
        Node p = root;
        int preValue = 0;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                final Node pop = stack.pop();
                if (preValue > pop.data) {
                    return false;
                }
                preValue = pop.data;
                if (pop.right != null) {
                    p = pop.right;
                }
            }
        }
        return true;
    }


}
