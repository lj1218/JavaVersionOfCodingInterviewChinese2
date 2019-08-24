package CodingInterviewChinese2.ch03.Q09_QueueWithTwoStacks;

import java.util.Stack;

/**
 * Created by lj1218.
 * Date: 2019/8/24
 * Page: 68
 * 面试题9：用两个栈实现队列
 *   题目：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail
 * 和deleteHead，分别完成在队列尾部插入结点和在队列头部删除结点的功能。
 */
public class Q09_QueueWithTwoStacks {

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
        CQueue<Character> queue = new CQueue<>();

        queue.appendTail('a');
        queue.appendTail('b');
        queue.appendTail('c');

        char head = queue.deleteHead();
        test("test1", head, 'a');

        head = queue.deleteHead();
        test("test2", head, 'b');

        queue.appendTail('d');
        head = queue.deleteHead();
        test("test3", head, 'c');

        queue.appendTail('e');
        head = queue.deleteHead();
        test("test4", head, 'd');

        head = queue.deleteHead();
        test("test5", head, 'e');
    }
}

class CQueue<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;

    public CQueue() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    public void appendTail(E e) {
        // 队尾元素直接压入 stack1
        stack1.push(e);
    }

    public E deleteHead() throws Exception {
        if (stack2.empty()) {
            // 如果 stack2 为空，则将 stack1 中的元素依次弹出压入 stack2
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }

        if (stack2.empty()) {
            throw new Exception("queue is empty");
        }

        return stack2.pop();
    }
}
