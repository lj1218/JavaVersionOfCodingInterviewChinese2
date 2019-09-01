package CodingInterviewChinese2.ch04.Q38_2_EightQueens;

import CodingInterviewChinese2.Util;

import java.util.Arrays;

/**
 * Created by lj1218.
 * Date: 2019/9/1
 *
 * Page: 200
 * 面试题38 - 相关问题二：八皇后问题
 * 题目：在 8x8 的国际象棋上摆放 8 个皇后，使其不能相互攻击，即任意两个
 * 皇后不得处在同一行、同一列或者同一条对角线上。图 4.20 中的每个黑色格子
 * 表示一个皇后，这就是一种符合条件的摆放方法。请问总共有多少种符合条件的摆法？
 *
 * 分析：由于 8 个皇后的任意两个不能处在同一行，那么肯定每一个皇后占据一行。
 * 于是我们可以定义一个数组 columnIndex[8]，数组中第 i 个数字表示位于第
 * i 行皇后的列号。先把数组 columnIndex 进行全排列（参考 Q38_StringPermutation）。
 * 因为我们用不同的数字初始化数组，所以任意两个皇后肯定不同列。只需判断每一个
 * 排列对应的 8 个皇后是不是存在 2 个皇后在同一条对角线上，也即是对于数组的
 * 两个下标 i 和 j，是否有 i-j == columnIndex[i]-columnIndex[j]，或者
 * j-i == columnIndex[i]-columnIndex[j]。
 *
 */
public class Q38_2_EightQueens {

    public static void main(String[] args) {
        solution();
    }

    public static void solution() {
        int[] columnIndex = {0, 1, 2, 3, 4, 5, 6, 7};
        System.out.println("\nsolution num: " + solution(columnIndex, 0, 0));
    }

    // 递归过程不太好理解
    private static int solution(int[] columnIndex, int begin, int solutionNum) {
        if (columnIndex.length == begin) {
            if (isSolution(columnIndex)) {
                System.out.println(Arrays.toString(columnIndex));
                ++solutionNum;
            }
        } else {
            for (int i = begin; i < columnIndex.length; ++i) {
                Util.swap(columnIndex, i, begin);

                solutionNum = solution(columnIndex, begin + 1, solutionNum);

                Util.swap(columnIndex, i, begin);
            }
        }
        return solutionNum;
    }

    private static boolean isSolution(int[] cols) {
        // 判断 cols.length 个皇后是不是存在 2 个皇后在同一条对角线上
        for (int i = 0; i < cols.length; ++i) {
            for (int j = i + 1; j < cols.length; ++j) {
                int diff = cols[i] - cols[j];
                if (i - j == diff || j - i == diff) {
                    return false;
                }
            }
        }

        return true;
    }
}
