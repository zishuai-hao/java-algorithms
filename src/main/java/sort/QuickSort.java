package sort;

import static util.CommonUtil.swap;

/**
 * @author hzs
 * @date 2022/11/11
 */
public class QuickSort {

    void quickSort(int length, int[] arr) {
        int left, right;
        left = 0;
        right = length - 1;
        quickerSort(arr, left, right);
    }

    void quickerSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = getPoint(arr, left, right);
        quickerSort(arr, left, pivot - 1);
        quickerSort(arr, pivot + 1, right);
    }

    private int getPoint(int[] arr, int left, int right) {
        int pivot = 0;
        while (left < right) {
            if (arr[right] >= arr[pivot]) {
                right--;
                continue;
            }
            if (arr[left] <= arr[pivot]) {
                left++;
                continue;
            }
            // 说明存在right < pivot && left > pivot
            // 所以交换两个数的位置
            swap(arr, left, right);
        }

        // 将预设的中间值 从 0 放到数组中间位置
        swap(arr,pivot,left);
        return left;

    }


}
