package CodingInterviewChinese2.ch05.Q45_SortArrayForMinNumber;

import java.util.Arrays;
//import java.util.Comparator;

/**
 * Created by lj1218.
 * Date: 2019/9/3
 *
 * Page: 227
 * 面试题45：把数组排成最小的数
 * 题目：输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼
 * 接出的所有数字中最小的一个。例如输入数组{3, 32, 321}，则打印出这3个数
 * 字能排成的最小数字321323。
 */
public class Q45_SortArrayForMinNumber {

    public static void main(String[] args) {
        Test.main();
    }

    public static void PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return;
        }

        // int 转 String
        String[] strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; ++i) {
            strNumbers[i] = "" + numbers[i];
        }

//        Arrays.sort(strNumbers, new Comparator<String>() {
//            // 按字典序排序
//            @Override
//            public int compare(String o1, String o2) {
//                return (o1 + o2).compareTo(o2 + o1);
//            }
//        });
        // 按字典序排序
        Arrays.sort(strNumbers, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        for (String s : strNumbers) {
            System.out.print(s);
        }
        System.out.println();
    }
}

class Test {

    public static void main() {
        Test1();
        Test2();
        Test3();
        Test4();
        Test5();
        Test6();
    }

    // ====================测试代码====================
    private static void test(String testName, int[] numbers, String expectedResult) {
        if (testName == null) {
            return;
        }
        System.out.printf("===== %s begins: =====\n", testName);

        if (expectedResult != null) {
            System.out.printf("Expected result is: \t%s\n", expectedResult);
        }

        System.out.print("Actual result is: \t");
        Q45_SortArrayForMinNumber.PrintMinNumber(numbers);

        System.out.println();
    }

    private static void Test1() {
        int numbers[] = {3, 5, 1, 4, 2};
        test("Test1", numbers, "12345");
    }

    private static void Test2() {
        int numbers[] = {3, 32, 321};
        test("Test2", numbers, "321323");
    }

    private static void Test3() {
        int numbers[] = {3, 323, 32123};
        test("Test3", numbers, "321233233");
    }

    private static void Test4() {
        int numbers[] = {1, 11, 111};
        test("Test4", numbers, "111111");
    }

    // 数组中只有一个数字
    private static void Test5() {
        int numbers[] = {321};
        test("Test5", numbers, "321");
    }

    private static void Test6() {
        test("Test6", null, "Don't print anything.");
    }
}
