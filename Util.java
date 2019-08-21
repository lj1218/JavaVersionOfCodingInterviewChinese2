package CodingInterviewChinese2;

/**
 * Created by lj1218.
 * Date: 2019/8/21
 */
public class Util {
    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
