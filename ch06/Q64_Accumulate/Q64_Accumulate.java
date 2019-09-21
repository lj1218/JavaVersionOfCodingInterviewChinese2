package CodingInterviewChinese2.ch06.Q64_Accumulate;

/**
 * Created by lj1218.
 * Date: 2019/9/21
 *
 * Page: 307
 * 面试题64：求1+2+…+n
 * 题目：求1+2+…+n，要求不能使用乘除法、for、while、if、else、switch、case
 * 等关键字及条件判断语句（A?B:C）。
 */
public class Q64_Accumulate {

    public static void main(String[] args) {
        Test.main();
    }

    // ====================方法一====================
    // 说明：不满足题意
    static class Temp {
        private static int n;
        private static int sum;

        public Temp() {
            ++n;
            sum += n;
        }

        static int getSum() {
            return sum;
        }

        static void reset() {
            n = sum = 0;
        }
    }

    public static int sum_Solution1(int n) {
        Temp.reset();
//        Temp[] t = new Temp[n];
        // Java 初始化数组只能一个个元素进行
        for (int i = 0; i < n; ++i) {
            new Temp();
        }

        return Temp.getSum();
    }

    // ====================方法二====================
    // 说明：不满足题意
    static class AB {
        private static A[] array = new A[]{new A(), new B()};

        static class A {
            public int sum(int n) {
                return 0;
            }
        }

        static class B extends A {
            public int sum(int n) {
                // array[!!n].sum(n - 1) + n;
                // !!n 在 Java 中非法，只能用条件表达式代替
                return array[n >= 1 ? 1 : 0].sum(n - 1) + n;
            }
        }

        static int getSum(int n) {
            return array[1].sum(n);
        }
    }

    public static int sum_Solution2(int n) {
        return AB.getSum(n);
    }

    // ====================方法三====================
    // https://blog.csdn.net/weixin_38361153/article/details/89211065
    // 思路:
    // 借助&&的短路功能，对于 A && B，有如下规则
    //
    // 若A = true，则执行B
    // 若A = false，则不执行B
    // 如果n=0时，则不会再进行递归调用
    public static int sum_Solution3(int n) {
        int sum = 0;
        boolean foo = (n > 0) && (sum = n + sum_Solution3(n - 1)) > 0;
        return sum;
    }

    // ====================方法四====================
    // https://blog.csdn.net/weixin_38361153/article/details/89211065
    // 公式法
    //
    // 思路：库函数
    // Sum = n(n+1)/2
    //
    // 说明：感觉这种方法不太好，算是一种投机方法。
    public static int sum_Solution4(int n) {
        int sum = (int) (Math.pow(n, 2) + n);
        return sum >> 1;
    }
}

class Test {

    public static void main() {
        Test1();
        Test2();
        Test3();
        Test4();
    }

    // ====================测试代码====================
    private static void test(int n, int expected) {
        System.out.printf("Test for %d begins:\n", n);

        if (Q64_Accumulate.sum_Solution1(n) == expected) {
            System.out.println("Solution1 passed.");
        } else {
            System.out.println("Solution1 failed.");
        }

        if (Q64_Accumulate.sum_Solution2(n) == expected) {
            System.out.println("Solution2 passed.");
        } else {
            System.out.println("Solution2 failed.");
        }

        if (Q64_Accumulate.sum_Solution3(n) == expected) {
            System.out.println("Solution3 passed.");
        } else {
            System.out.println("Solution3 failed.");
        }

        if (Q64_Accumulate.sum_Solution4(n) == expected) {
            System.out.println("Solution4 passed.");
        } else {
            System.out.println("Solution4 failed.");
        }

        System.out.println();
    }

    private static void Test1() {
        int number = 1;
        int expected = 1;
        test(number, expected);
    }

    private static void Test2() {
        int number = 5;
        int expected = 15;
        test(number, expected);
    }

    private static void Test3() {
        int number = 10;
        int expected = 55;
        test(number, expected);
    }

    private static void Test4() {
        int number = 0;
        int expected = 0;
        test(number, expected);
    }
}
