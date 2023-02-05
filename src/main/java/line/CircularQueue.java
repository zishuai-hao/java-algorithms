package line;

/**
 * @author hzs
 * @date 2022/11/30
 */
public class CircularQueue {

    Node rear;

    public CircularQueue() {
        rear = Node.of(-1, null);
        rear.next = rear;
    }

    static class Node {
        Node next;
        int data;

        public static Node of(int data, Node next) {
            final Node node = new Node();
            node.data = data;
            node.next = next;
            return node;
        }

        @Override
        public String toString() {
            return String.format("{data: %d} -> ", data);
        }
    }


    public void enQueue(int data) {
        final Node add = Node.of(data, null);
        // 为空
        add.next = rear.next;
        rear.next = add;
        rear = rear.next;
    }

    public void deQueue() {
//               r              t
        // h -> 1 -> h  => h -> h
//                   r              r
        // h -> 1 -> 2 -> h => h -> 2 -> h
        final Node head = rear.next;
        head.next = head.next.next;
        // 队列为空 返回头节点
        if (head == head.next) {
            rear = head;
        }
    }

    @Override
    public String toString() {
        Node temp = rear.next.next;
        final StringBuilder sb = new StringBuilder();
        while (temp != rear.next) {
            sb.append(temp);
            temp = temp.next;
        }
        sb.append("^");
        return sb.toString();
    }

    public static void main(String[] args) {
//        final Node node5 = Node.of(5, null);
//        final Node node4 = Node.of(4, node5);
//        final Node node3 = Node.of(3, node4);
//        final Node node2 = Node.of(2, node3);
//        final Node node1 = Node.of(1, node2);
//        node5.next = Node.of(-1, node1);
//        Node rear = node5;
//
        final CircularQueue circularQueue = new CircularQueue();
        circularQueue.enQueue(100);
        circularQueue.enQueue(200);
        circularQueue.enQueue(300);
        System.out.println(circularQueue);

        circularQueue.enQueue(30);

        System.out.println(circularQueue);

        circularQueue.deQueue();
        System.out.println(circularQueue);


    }
}
