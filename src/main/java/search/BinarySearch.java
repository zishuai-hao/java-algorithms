package search;

/**
 * @author hzs
 * @date 2022/10/16
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        int result = binarySearch(arr);
        System.out.println("result:" + result);
    }

    private static int binarySearch(int[] arr) {
        int search = 8;
        int result = -1;
        int l = 0;
        int r = arr.length - 1;


        while (l <= r) {
            int mid = (r + l) / 2;
            if (arr[mid] == search) {
                result = mid;
                break;
            }
            if (arr[mid] > search) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return result;
    }

}
