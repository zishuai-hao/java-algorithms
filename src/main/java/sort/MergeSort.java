package sort;

import static java.lang.Math.abs;

/**
 * 原理就是将数组尽可能从中间分开，直到不能再分，形成一个树形结构；
 * 然后自下而上进行合并排序，对叶子节点排序后，合并到父节点后再进行一次排序，以此类推
 * @author hzs
 * @date 2022/11/11
 */
public class MergeSort {

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /**
     * 归并排序

     * @param length
     * @param arr
     */
    void mergeSort(int length, int[] arr) {
        int left, right;
        left = 0;
        right = length - 1;
        mergerSort(arr, left, right);
    }

    void mergerSort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        // 继续切分
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergerSort(arr, left, mid - 1);
            mergerSort(arr, mid, right);
        }
        //  插入排序
        for (int i = left; i < right; ++i) {
            if (arr[i + 1] > arr[i]) {
                mergeInsert(arr, left, i + 1);
            }
        }
    }


    void mergeInsert(int[] arr, int left, int right) {
        swap(arr, right, right - 1);
        for (int i = right - 1; i > left; --i) {
            if (arr[i] > arr[i - 1]) {
                swap(arr, i, i - 1);
            }
        }
    }

}
