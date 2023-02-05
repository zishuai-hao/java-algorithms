package line;

/**
 * 链表翻转实现思想：
 *
 * @author hzs
 * @date 2022/11/15
 */
public class RevertLinkedList {
    static class LinkedNode {
        int data;
        LinkedNode next;

        public static LinkedNode of(int i) {
            final LinkedNode linkedNode = new LinkedNode();
            linkedNode.data = i;
            return linkedNode;
        }

        @Override
        public String toString() {
            return "LinkedNode{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        final LinkedNode linkedNode = createLinkedNode(new int[]{1, 2, 3, 4, 5});
        System.out.println(linkedNode);
//        final LinkedNode revert = revert(linkedNode);
//        System.out.println(revert);

        final LinkedNode linkedNode1 = createLinkedNode(new int[]{1, 2, 3, 4, 5});
        inPlaceRevert(linkedNode1);
        System.out.println(linkedNode1);

    }

    /**
     * 将链表分为两段，从右侧中取出元素追加到左侧，直到右侧元素为空
     */
    public static LinkedNode revert(LinkedNode head) {
        // 取出实际元素
        LinkedNode headCopy = head;
        head = head.next;

        LinkedNode prev = null;
        LinkedNode curr = head;
        while (curr != null) {
            // 4. 回正头指针
            head = curr;
            // 1. 保存下一个元素的指针
            curr = curr.next;
            // 2. 将该元素拼接到第一段的尾部
            head.next = prev;
            // 3. 移动prev指针到该链表的结尾
            prev = head;
        }
        headCopy.next = head;
        return headCopy;
    }

    /**
     * 将链表逆序，用一个临时节点充当数据头
     * @param head
     * @return
     */
    public static void inPlaceRevert(LinkedNode head) {
        LinkedNode firstElement = head.next;

        LinkedNode temp = head.next;

        // 循环判断条件，当首元节点移动到末尾，翻转完成
        while (firstElement.next != null ) {
            // 取出下一个元素追加到头结点
            head.next = firstElement.next;
            // 删除这个元素原来的位置
            firstElement.next = firstElement.next.next;
            // 将临时链表追加到该节点之后
            head.next.next = temp;
            // 更新临时链表
            temp = head.next;
        }
    }

    public static LinkedNode createLinkedNode(int[] arr) {
        LinkedNode linkedNode = LinkedNode.of(-1);
        LinkedNode linkedNodeCopy = linkedNode;
        for (int j = 0; j < arr.length; j++) {
            int i = arr[j];
            linkedNode.next = LinkedNode.of(i);
            linkedNode = linkedNode.next;
        }
        return linkedNodeCopy;
    }
}
