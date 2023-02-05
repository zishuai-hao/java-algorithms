package sort;

/**
 * 将前面的元素视为有序序列，然后插入进去
 *
 * @author hzs
 * @date 2022/11/10
 */
public class InsertionSort {


    void insertionSort(int length, int[] arr) {
        int temp;
        for (int i = 0; i < length; i++) {
            temp = arr[i]; // 设置当前元素为临时元素
            int pre = i - 1; // 前一个元素
            while (pre >= 0 && arr[pre] > temp) { // 前一个比较大
                arr[pre + 1] = arr[pre]; // 当前位置设置为前一个元素
                pre -= 1; // 继续移动指针，与前一个比较
            }
            arr[pre + 1] = temp; // 交换元素，或不变
        }
    }

}
