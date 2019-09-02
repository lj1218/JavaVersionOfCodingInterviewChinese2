package CodingInterviewChinese2;

import java.util.Random;

/**
 * Created by lj1218.
 * Date: 2019/8/21
 */
public class Util {
    private static Random random = new Random();

    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void swap(char[] a, int i, int j) {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static <T> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 将数组 data 下标范围 [start, end] 随机分成两部分，左边 <= 右边（左、右部分非有序）
     *
     * @param data  数组 data
     * @param start 起始下标
     * @param end   终止下标
     * @return 返回下标 index，data[index] >= 左边部分所有值，<= 右边部分所有值
     * @throws Exception 参数无效时，抛出异常
     */
    public static int partition(Integer data[], int start, int end) throws Exception {
        if (data == null || data.length == 0 || start < 0 || end >= data.length)
            throw new Exception("Invalid Parameters");

        int index = randomInRange(start, end);
        swap(data, index, end);

        int small = start - 1;
        for (index = start; index < end; ++index) {
            if (data[index] < data[end]) {
                ++small;
                if (small != index)
                    swap(data, index, small);
            }
        }

        ++small;
        swap(data, small, end);

        return small;
    }

    /**
     * 返回 min ~ max 之间的随机整数（含 min, max）
     *
     * @param min 随机值下界
     * @param max 随机值上界
     * @return min ~ max 之间的随机整数
     */
    public static int randomInRange(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
