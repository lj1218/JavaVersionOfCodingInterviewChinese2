package CodingInterviewChinese2.ch05.Q41_StreamMedian;

import CodingInterviewChinese2.util.MaxHeap;
import CodingInterviewChinese2.util.MinHeap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by lj1218.
 * Date: 2019/9/2
 * <p>
 * Page: 214
 * 面试题41：数据流中的中位数
 * 题目：如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么
 * 中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
 * 那么中位数就是所有数值排序之后中间两个数的平均值。
 */
public class Q41_StreamMedian {

    public static void main(String[] args) throws Exception {
        Test.main();
    }

}

class DynamicArray {
    private MaxHeap<Double> maxHeap;
    private MinHeap<Double> minHeap;

    public DynamicArray() {
        this.maxHeap = new MaxHeap<>(Double[].class);
        this.minHeap = new MinHeap<>(Double[].class);
    }

    // 先插入最小堆，再插入最大堆，交替插入
    public void insert(Double e) throws Exception {
        if (((minHeap.size() + maxHeap.size()) & 0x1) == 0) {
            // 已插入偶数个元素，当前元素插入最小堆
            if (maxHeap.size() > 0 && e.compareTo(maxHeap.getHeapElem()) < 0) {
                // 用 e 替换最大堆的堆顶元素
                e = maxHeap.replaceHeapElem(e);
            }

            // 将 e 加入最小堆
            minHeap.add(e);
        } else {
            // 已插入奇数个元素，当前元素插入最大堆
            if (minHeap.size() > 0 && e.compareTo(minHeap.getHeapElem()) > 0) {
                // 用 e 替换最小堆的堆顶元素
                e = minHeap.replaceHeapElem(e);
            }

            // 将 e 加入最大堆
            maxHeap.add(e);
        }
    }

    public Double getMedian() throws Exception {
        if (minHeap.size() + maxHeap.size() == 0) {
            throw new Exception("No numbers are available");
        }

        if (((minHeap.size() + maxHeap.size()) & 0x1) == 0) {
            // 已插入偶数个元素，中间元素为最大堆、最小堆堆顶元素平均值
            return (minHeap.getHeapElem() + maxHeap.getHeapElem()) / 2;
        } else {
            // 已插入奇数个元素，中间元素为最小堆堆顶元素
            return minHeap.getHeapElem();
        }
    }
}

@SuppressWarnings("ALL")
class DynamicArray2 {
    // 使用优先级队列实现
    private PriorityQueue<Double> maxHeap;
    private PriorityQueue<Double> minHeap;

    public DynamicArray2() {
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<Double>(10,
                new Comparator<Double>() {
                    @Override
                    public int compare(Double o1, Double o2) {
                        return o2.compareTo(o1);
                    }
                });
    }

    // 先插入最小堆，再插入最大堆，交替插入
    public void insert(Double e) {
        if (((minHeap.size() + maxHeap.size()) & 0x1) == 0) {
            // 已插入偶数个元素，当前元素插入最小堆
            if (maxHeap.size() > 0 && e.compareTo(maxHeap.peek()) < 0) {
                // 用 e 替换最大堆的堆顶元素
                maxHeap.offer(e);
                e = maxHeap.poll();
            }

            // 将 e 加入最小堆
            minHeap.offer(e);
        } else {
            // 已插入奇数个元素，当前元素插入最大堆
            if (minHeap.size() > 0 && e.compareTo(minHeap.peek()) > 0) {
                // 用 e 替换最小堆的堆顶元素
                minHeap.offer(e);
                e = minHeap.poll();
            }

            // 将 e 加入最大堆
            maxHeap.add(e);
        }
    }

    public Double getMedian() throws Exception {
        if (minHeap.size() + maxHeap.size() == 0) {
            throw new Exception("No numbers are available");
        }

        if (((minHeap.size() + maxHeap.size()) & 0x1) == 0) {
            // 已插入偶数个元素，中间元素为最大堆、最小堆堆顶元素平均值
            return (minHeap.peek() + maxHeap.peek()) / 2;
        } else {
            // 已插入奇数个元素，中间元素为最小堆堆顶元素
            return minHeap.peek();
        }
    }
}

class Test {

    public static void main() throws Exception {
        test1();
        test2();
    }

    // ====================测试代码====================
    private static void test(String testName, DynamicArray numbers, double expected) throws Exception {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        if (Math.abs(numbers.getMedian() - expected) < 0.0000001) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }
    }

    private static void test(String testName, DynamicArray2 numbers, double expected) throws Exception {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        if (Math.abs(numbers.getMedian() - expected) < 0.0000001) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }
    }

    public static void test1() throws Exception {
        DynamicArray numbers = new DynamicArray();

        System.out.print("Test1 begins: ");
        try {
            numbers.getMedian();
            System.out.println("FAILED.");
        } catch (Exception e) {
            System.out.println("Passed.");
        }

        numbers.insert(5.);
        test("Test2", numbers, 5);

        numbers.insert(2.);
        test("Test3", numbers, 3.5);

        numbers.insert(3.);
        test("Test4", numbers, 3);

        numbers.insert(4.);
        test("Test5", numbers, 3.5);

        numbers.insert(1.);
        test("Test6", numbers, 3);

        numbers.insert(6.);
        test("Test7", numbers, 3.5);

        numbers.insert(7.);
        test("Test8", numbers, 4);

        numbers.insert(0.);
        test("Test9", numbers, 3.5);

        numbers.insert(8.);
        test("Test10", numbers, 4);
    }

    public static void test2() throws Exception {
        DynamicArray2 numbers = new DynamicArray2();

        System.out.println("=====================");
        System.out.print("Test1 begins: ");
        try {
            numbers.getMedian();
            System.out.println("FAILED.");
        } catch (Exception e) {
            System.out.println("Passed.");
        }

        numbers.insert(5.);
        test("Test2", numbers, 5);

        numbers.insert(2.);
        test("Test3", numbers, 3.5);

        numbers.insert(3.);
        test("Test4", numbers, 3);

        numbers.insert(4.);
        test("Test5", numbers, 3.5);

        numbers.insert(1.);
        test("Test6", numbers, 3);

        numbers.insert(6.);
        test("Test7", numbers, 3.5);

        numbers.insert(7.);
        test("Test8", numbers, 4);

        numbers.insert(0.);
        test("Test9", numbers, 3.5);

        numbers.insert(8.);
        test("Test10", numbers, 4);
    }
}
