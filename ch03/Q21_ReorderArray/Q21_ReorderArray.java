package CodingInterviewChinese2.ch03.Q21_ReorderArray;

/**
 * Created by lj1218.
 * Date: 2019/8/28
 *
 * Page: 129
 * 面试题21：调整数组顺序使奇数位于偶数前面
 * 题目：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有
 * 奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 */
public class Q21_ReorderArray {

    public static void main(String[] args) {
        Test.main();
    }

    // 分类器接口
    public interface Classifier<E> {
        // 判断 e 是否属于前半部分
        boolean inFirstHalf(E e);
    }

    public static void ReorderOddEven(int[] numbers) {
        Reorder(numbers, new OddEvenClassifier());
    }

    public static void Reorder(int[] numbers, Classifier<Integer> classifier) {
        if (numbers == null || numbers.length == 0) {
            return;
        }

        int begin = 0;
        int end = numbers.length - 1;
        while (begin < end) {
            // 向后移动 begin
            while (begin < end && classifier.inFirstHalf(numbers[begin])) {
                ++begin;
            }

            // 向前移动 end
            while (begin < end && !classifier.inFirstHalf(numbers[end])) {
                --end;
            }

            // 交换
            if (begin < end) {
                int temp = numbers[begin];
                numbers[begin] = numbers[end];
                numbers[end] = temp;
            }
        }
    }

    // 嵌套类
    static class OddEvenClassifier implements Classifier<Integer> {
        @Override
        public boolean inFirstHalf(Integer n) {
            // 奇数排前，偶数排后
            return (n & 0x1) == 0x1;
        }
    }

    static class Test {

        public static void main() {
            Test1();
            Test2();
            Test3();
            Test4();
            Test5();
            Test6();
        }

        // ====================测试代码====================
        public static void PrintArray(int numbers[]) {
            if (numbers == null)
                return;

            for (int i = 0; i < numbers.length; ++i)
                System.out.printf("%d\t", numbers[i]);

            System.out.println();
        }

        public static void Test(String testName, int numbers[]) {
            if (testName != null) {
                System.out.printf("%s begins:\n", testName);
            }

            System.out.println("Test for solution:");
            PrintArray(numbers);
            ReorderOddEven(numbers);
            PrintArray(numbers);
            System.out.println();
        }

        public static void Test1() {
            int[] numbers = {1, 2, 3, 4, 5, 6, 7};
            Test("Test1", numbers);
        }

        public static void Test2() {
            int[] numbers = {2, 4, 6, 1, 3, 5, 7};
            Test("Test2", numbers);
        }

        public static void Test3() {
            int[] numbers = {1, 3, 5, 7, 2, 4, 6};
            Test("Test3", numbers);
        }

        public static void Test4() {
            int[] numbers = {1};
            Test("Test4", numbers);
        }

        public static void Test5() {
            int[] numbers = {2};
            Test("Test5", numbers);
        }

        public static void Test6() {
            Test("Test6", null);
        }
    }
}
