package graph;

import java.util.Arrays;

/**
 * @author hzs
 * @date 2022/10/02
 */
public class KMP {
    public static void main(String[] args) {
        String patten = "abaabcac";
        String str = "aacabcababaabcacaabcb"; // index 8
        final int[] next = new int[patten.length()];
//        getNextArray(next, patten);
//        System.out.println(Arrays.toString(next));
        final int[] nextArray2 = getNextArray2(patten);
        System.out.println(Arrays.toString(nextArray2));
//        int result = getIndex(patten, str, next);
//        System.out.println("result:" + result);
    }

    static int getIndex(String patten, String str, int[] next) {
        int j = 0;
        int result = -1;
        for (int i = 0; i < str.length(); i++) {
            if (patten.charAt(j) == str.charAt(i)) {
                if (j + 1 == patten.length()) {
                    result = i;
                    break;
                }
                j++;
            } else {
                // 回退到前缀之后 （没有前缀回退到0）
                j = next[j];
            }
        }
        return (result - patten.length() + 1);
    }

    /**
     * 将两个字符的匹配结果存入第三个字符中
     *
     * @param patten
     * @return
     */
    static int[] getNextArray2(String patten) {
        int[] next = new int[patten.length() + 1];
        int i = 0; // 前缀索引 // 缀长度
        int j = 1; // 后缀索引
        while (j + 1 < patten.length()) {
            // 匹配失败赋值1，匹配成功则赋值且+1
            // 回溯重新匹配
            if (j == 1) {
                next[j] = i + 1;
            }
            if (patten.charAt(i) == patten.charAt(j)) {
                ++i;
                ++j;
                next[j] = i + 1;
            } else {
                if (i == 0 && j != 1) {
//                    ++j;
//                    ++i;
//                    next[j] = i;
                } else {
                    i = next[i - 1];
                }
            }
        }
        return next;
    }

    static void getNextArray(int[] next, String patten) {
        int i = 0; // 前缀索引，前后缀长度
        int j = 1; // 后缀索引
        while (j < next.length) {
            // 匹配成功，前后缀长度加一
            if (patten.charAt(j) == patten.charAt(i)) {
                next[j] = i + 1;
                j++;
                i++;
            } else {
                // 该子串没有公共前后缀
                if (i == 0) {
                    next[j] = 0;
                    j++;
                }
                // 匹配，失败，该子串前后缀长度减少一位继续比较
                else {
                    i = next[i - 1];
                }
            }
        }
    }
}
