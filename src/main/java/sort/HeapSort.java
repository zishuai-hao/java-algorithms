package sort;

/**
 * 堆排序：以大根堆或者小根堆为存储结构的选择排序
 * 大根堆： 根 >= 左、右， 是一个顺序存储的完全二叉树
 * 构造大根堆： 将每个分之节点都与其子节点比较，选择最大的一个作为父节点
 *            所以要求构造的时候从最底层的分支节点开始比较，直到根节点
 * @author hzs
 * @date 2022/10/23
 */
public class HeapSort {



    public static void main(String[] args) {

    }
    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void heapSort(int length, int[] arr) {
        // 构建一个最大堆
        buildHeap(arr, length);

        for (int i = 0; i < length; i++) {
            // 交换堆顶与最后子节点
            swap(arr, 0, length - i - 1);
            // 将新子节点下沉 交换出剩余数组内最大值的堆顶
            sink(arr, 0, length - i - 1);
        }

    }

    void buildHeap(int[] arr, int length) {
        // 从最后一个非叶子节点开始 下沉
        for (int i = length / 2 - 1; i >= 0; --i) {
            sink(arr, i, length);
        }
    }

    /**
     * 元素下沉
     */
    void sink(int[] arr, int current, int length) {
        // 存在子节点
        while (2 * current + 1 < length) {
            int maxChild = 2 * current + 1;

            // 寻找最大子节点
            if (maxChild + 1 < length) {
                maxChild = arr[maxChild] < arr[maxChild + 1] ? maxChild + 1 : maxChild;
            }

            // 比较是否更大
            if (arr[current] < arr[maxChild]) {
                swap(arr, current, maxChild);
            }else {
                // 已经是最大节点，不发生交换直接退出
                break;
            }
            // 继续下沉
            current = maxChild;
        }
    }

    /**
     * 新增元素 或构造堆时 ，元素浮动
     * @param arr
     * @param length
     */
    void swim(int[] arr, int length) {
        int point = length - 1;
        while (parent(point) >= 0) {
            if (arr[point] > arr[parent(point)]) {
                swap(arr, point, parent(point));
            }
            point = parent(point);
        }
    }

    int parent(int point) {
        return point == 0 ? -1 : (point - 1) / 2;
    }

    void insert(int data) {
//        add()
//        swim();
    }

    void delete(int data) {
//        swap()
//        remove();
//        sink()

    }

}
