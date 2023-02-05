package tree;

import java.util.LinkedHashMap;

/**
 * 哈夫曼树 （最优二叉树） 度为2 WPL带权路径长度最小的树
 * 是一个二叉树，且深度越大，权值越高
 * 将非叶子节点的路径编码为为1 通向叶子节点的路径编码为0 （最后一个节点会有两个子节点 分别为0/1）
 * 将这些路径拼接可以得到一租编码集合，该集合中的编码长度依次增加，且互相不会成为其他编码的前缀，也就是可变前缀编码
 * 0 / 10/ 111/ 110
 *
 * @author hzs
 * @date 2022/10/03
 */
public class HuffmanTree {
    static class Node {
        /**
         * 权重
         */
        int weight;

        /**
         * 父节点
         */
        Node parent;

        /**
         * 左子节点
         */
        Node lChild;

        /**
         * 右子节点
         */
        Node rChild;

        public Node(int weight) {
            this.weight = weight;
        }

        public Node(int weight, Node parent, Node lChild, Node rChild) {
            this.weight = weight;
            this.parent = parent;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }

    public static void main(String[] args) {
        final int[] weights = {1, 2, 3, 4, 5, 6};
        Node root = buildHuffmanTree(weights);
        final LinkedHashMap<String, Integer> huffmanCodes = new LinkedHashMap<>();
        inorder(root);
        huffmanCoding(root, huffmanCodes);
        System.out.println(huffmanCodes);
    }

    /**
     * 构造简单的哈夫曼树
     *
     * @param weights 节点权重&节点值
     * @return 根节点
     */
    static Node buildHuffmanTree(int[] weights) {
        Node root = new Node(weights[0], null, null, null);
        for (int i = 1; i < weights.length; i++) {
            if (root.parent == null) {
                root.parent = new Node(root.weight + weights[i], null, root, null);
            }
            root.parent.rChild = new Node(weights[i], root.parent, null, null);
            root = root.parent;
        }
        return root;
    }

    /**
     * 中序遍历
     *
     * @param root 根节点
     */
    static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.lChild);
        System.out.print(root.weight + ", ");
        inorder(root.rChild);
    }


    /**
     * 生成哈夫曼编码
     *
     * @param root         根节点
     * @param huffmanCodes 需要填充的数组
     */
    static void huffmanCoding(Node root, LinkedHashMap<String, Integer> huffmanCodes) {
        final char[] chars = new char[6];
        int i = 0;
        while (root != null) {
            if (root.rChild != null) {
                chars[i++] = '0';
                huffmanCodes.put(String.valueOf(chars, 0, i), root.rChild.weight);
                i--; // 无需还原
            }
            if (root.lChild == null) {
                huffmanCodes.put(String.valueOf(chars, 0, i), root.weight);
            }
            chars[i++] = '1';
            root = root.lChild;
        }
    }


}


