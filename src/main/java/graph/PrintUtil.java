package graph;

import java.util.Arrays;

/**
 * @author hzs
 * @date 2022/10/14
 */
public class PrintUtil {
    public static  void printMatrix(int[][] matrix) {
        System.out.println("-------start-------");
        for (int[] a : matrix) {
            if (a == null)
                System.out.println("null");
            int iMax = a.length - 1;
            if (iMax == -1)
                System.out.println("[]");

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                b.append(a[i] == Integer.MAX_VALUE ? "MAX" : a[i]);
                if (i == iMax){
                    System.out.println(b.append(']'));
                    break;}
                b.append(", ");
            }
        }
        System.out.println("-------end--------");
    }
}
