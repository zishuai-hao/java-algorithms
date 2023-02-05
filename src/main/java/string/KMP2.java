package string;

/**
 * 在遍历过程中发现模式串索引k位置，主串j位置的字符不匹配，则一定有
 * 模式串k-1=主串j-1 =>模式串0 = 主串j-k+1
 * 称这0～k个的子串，以及对应主串的索引为已知信息，KMP算法就是利用已知信息来减少遍历的次数
 * 利用已知信息的方式是求出最长公共前后缀，
 * 公共前后缀 是把该子串分为四个（2个）部分 ：（前缀  + A ）+ （后缀 + B），其中A!=B , 前缀=后缀
 * 例如是失配点x的子串 abcabx， 其中abcab中的最大前后缀为ab，
 * 而在B发生失配时，后缀对应位置必定是匹配的，又因为结果一定是以该前缀(ab)开头，
 * 下一个（ab）实际上就是后缀，直接将前缀向后移动到后缀的位置， 应该令A与主串字符比较
 *
 * x失配时，可以直接将前缀移动到后缀位置
 *
 *
 * 严蔚敏版 next数组 ：索引从1开始，指向前缀的后一个位置(也就是j指针下次比较的索引)
 *
 * KMP优化，失配移动的时候判断 是否next数组所指向的元素与当前元素相同，如果相同，则继续进行一次跳转
 * 如 abaa 发生失配，正常应该再跳转到第一个a 索引为1，但是 两个a明显都不匹配，所以应该直接跳转到0（也就是移动主串指针，重置模式串指针）
 * @author hzs
 * @date 2022/10/28
 */
public class KMP2 {

    public static void main(String[] args) {
        char s1[] = "absdfgaaaabcdabcaaaabcdabca".toCharArray();
        char s2[] = "abcdabca".toCharArray();
        char s3[] = "00001231".toCharArray();


        int[] next = new int[100];
        getNext(s2,next);
        getNextVal(next);

        int loop;

        for(loop = 0; loop < 8; loop++)
            System.out.printf("%d ", next[loop]);

        int result = 0;
        for (char c : s1) {
        }

    }

    private static void getNextVal(int[] next) {

    }

    static void getNext(char[] s2, int []next) {
        int prefix_len=0; // 前缀指针
        int i=1; // 后缀指针
        next[0] = 0; // 缀表 存放当前未知的前缀长度 aabaa  next[4]=2
        while (i < s2.length){
            // 可以组成成更大长度的公共前缀
            if (s2[prefix_len] == s2[i]) {
                next[i] = 1 + next[i-1];
                i++;
                prefix_len++;
            } else {
                // 不存在前后缀
                if (prefix_len == 0) {
                    next[i] = 0;
                    i++;
                }else {
                    // 查前缀表 是否存在更短的前后缀
                    prefix_len = next[prefix_len - 1];
                }
            }
        }
    }
}
