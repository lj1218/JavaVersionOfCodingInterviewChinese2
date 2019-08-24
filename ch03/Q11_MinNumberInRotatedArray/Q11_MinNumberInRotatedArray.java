package CodingInterviewChinese2.ch03.Q11_MinNumberInRotatedArray;

/**
 * Created by lj1218.
 * Date: 2019/8/24
 * Page: 82
 * 面试题11：旋转数组的最小数字
 *   题目：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如数组
 * {3, 4, 5, 1, 2}为{1, 2, 3, 4, 5}的一个旋转，该数组的最小值为1。
 */
public class Q11_MinNumberInRotatedArray {

    public static void main(String[] args) {
        // 典型输入，单调升序的数组的一个旋转
        int[] array1 = {3, 4, 5, 1, 2};
        Test(array1, 1, null);

        // 有重复数字，并且重复的数字刚好的最小的数字
        int[] array2 = {3, 4, 5, 1, 1, 2};
        Test(array2, 1, null);

        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        int[] array3 = {3, 4, 5, 1, 2, 2};
        Test(array3, 1, null);

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array4 = {1, 0, 1, 1, 1};
        Test(array4, 0, null);

        // 单调升序数组，旋转0个元素，也就是单调升序数组本身
        int[] array5 = {1, 2, 3, 4, 5};
        Test(array5, 1, null);

        // 数组中只有一个数字
        int[] array6 = {2};
        Test(array6, 2, null);

        // 数组不是递增数组的旋转（会抛异常）
        int[] array7 = {5, 6, 7, 8, 9, 10, 2, 3, 1};
        Test(array7, 1, "Invalid array");

        // 输入null
        Test(null, 0, "Invalid parameters");
    }

    public static int Min(int[] numbers) throws Exception {
        if (numbers == null || numbers.length == 0) {
            throw new Exception("Invalid parameters");
        }

        int p1 = 0;
        int p2 = numbers.length - 1;
        if (numbers[p1] < numbers[p2]) {
            // 旋转后序列跟原序列一致
            return numbers[p1];
        }

        while (true) {
            if (p2 - p1 == 1) {
                return numbers[p2];
            }

            int mid = (p1 + p2) / 2;
            if (numbers[p1] == numbers[mid] && numbers[mid] == numbers[p2]) {
                return MinInRange(numbers, p1, p2);
            }

            // 缩小查找返回（折半查找）
            if (numbers[mid] >= numbers[p1]) {
                p1 = mid;
            } else if (numbers[mid] <= numbers[p2]) {
                p2 = mid;
            } else {
                throw new Exception("Invalid array 1");
            }

            if (numbers[p1] < numbers[p2]) {
                throw new Exception("Invalid array 2");
            }
        }
    }

    private static int MinInRange(int[] numbers, int start, int end) {
        int min = numbers[start];
        for (int i = start + 1; i <= end; ++i) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
        }
        return min;
    }

    // ====================测试代码====================
    public static void Test(int[] numbers, int expected, String exceptionString) {
        try {
            int result = Min(numbers);

            for (int number : numbers) {
                System.out.printf("%d ", number);
            }

            if (result == expected)
                System.out.print("\tpassed\n");
            else
                System.out.print("\tfailed\n");
        } catch (Exception e) {
            if (numbers == null && e.toString().contains(exceptionString)) {
                System.out.println("Test passed.");
            } else if (e.toString().contains(exceptionString)) {
                System.out.println("Test passed.");
            } else {
                System.out.println("Test failed.");
            }
        }
    }
}
