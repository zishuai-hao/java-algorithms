package sort;

/**
 * 希尔排序
 * 在插入排序中，
 * 大多数元素<strong>有序</strong>的情况下，对比或移动的次数较少；
 * 当元素<strong>较少</strong>时，对比或移动的次数较少
 * 综合这两个特性 总结出改进方案1：
 * 1. 将完整需要排序的数组，依次划分为多个长度为n（上述结论2）的子序列（最后一个序列长度允许小于n），然后分别对多个子序列使用插入排序
 * 2. 当然 n < len(arr) 时，一次排序后只在多个子序列内部有序，将n递增, 继续进行插入排序
 * 3. 直到 n = len(arr) 时，进行一个完整的插入排序，此时数组有序
 * 当然 改进方案1 还是有缺点，由于子序列之间元素都是连续的，排序之后，依然对整体数组还是无序，提出改正方案2:
 * 1. 使用一个 增量（类似于step）k,根据这个增量划分元素 则数组被划分为
 * {{k,2k,3k...},{k+1,2K+1,3k+1...}...{k + k-1, 2k + k-1... }}多个子序列
 * 2. 分别对各个子序列进行直接插入排序，得到一个局部有序的新数组。
 * 3. 由于数组可能无限大，需要进行多次增量直接插入，逼近全局有序
 * 每次减少增量 进行插入排序 直到增量为1时，进行一次完整的插入排序
 *
 * @author hzs
 * @date 2022/11/10
 */
public class ShellSort {

    public static void shellSort(int[] arr) {
        int length = arr.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++)   {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }

}



