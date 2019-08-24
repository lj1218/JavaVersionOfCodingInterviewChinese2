package CodingInterviewChinese2.ch03.Q09_2_StackWithTwoQueues;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by lj1218.
 * Date: 2019/8/24
 * Page: 71
 * 面试题9-2：用两个队列实现一个栈。
 */
public class Q09_2_StackWithTwoQueues {

    public static void main(String[] args) throws Exception {
        tests();
    }

    // ====================测试代码====================
    private static void test(String testName, char actual, char expected) {
        if (actual == expected)
            System.out.println(testName + " passed.");
        else
            System.out.println(testName + " failed.");
    }

    private static void tests() throws Exception {
        CStack<Character> stack = new CStack<>();

        stack.push('a');
        stack.push('b');
        stack.push('c');

        char tail = stack.pop();
        test("test1", tail, 'c');

        tail = stack.pop();
        test("test2", tail, 'b');

        stack.push('d');
        tail = stack.pop();
        test("test3", tail, 'd');

        stack.push('e');
        tail = stack.pop();
        test("test4", tail, 'e');

        tail = stack.pop();
        test("test5", tail, 'a');

        try {
            stack.pop();
        } catch (Exception e) {
            // java.lang.Exception: stack is empty
            System.out.println(e.toString());
        }
    }
}

class CStack<E> {
    private Queue<E> emptyQueue;
    private Queue<E> nonEmptyQueue;

    public CStack() {
        emptyQueue = new ArrayDeque<>();
        nonEmptyQueue = new ArrayDeque<>();
    }

    public void push(E e) {
        nonEmptyQueue.offer(e);
    }

    public E pop() throws Exception {
        if (nonEmptyQueue.isEmpty()) {
            throw new Exception("stack is empty");
        }

        while (nonEmptyQueue.size() > 1) {
            emptyQueue.offer(nonEmptyQueue.remove());
        }

        E e = nonEmptyQueue.remove();

        // 交换 空队列 和 非空队列
        Queue<E> temp = emptyQueue;
        emptyQueue = nonEmptyQueue;
        nonEmptyQueue = temp;
        return e;
    }
}
