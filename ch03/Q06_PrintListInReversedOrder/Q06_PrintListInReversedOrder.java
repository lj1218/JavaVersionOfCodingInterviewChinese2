package CodingInterviewChinese2.ch03.Q06_PrintListInReversedOrder;

import java.util.Stack;

/**
 * Created by lj1218.
 * Date: 2019/8/21
 * Page: 58
 * 面试题6：从尾到头打印链表
 *   题目：输入一个链表的头结点，从尾到头反过来打印出每个结点的值。
 *
 * 提示：循环放入栈再依次弹出打印 或者 递归打印（链表长度太大会导致方法栈溢出）。
 */
public class Q06_PrintListInReversedOrder {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    // ====================测试代码====================
    private static void test(List<Integer> list) {
        list.print();
        list.printReversedOrderIteratively();
        list.printReversedOrderRecursively();
        System.out.print("\n\n");
    }

    // 1->2->3->4->5
    private static void test1() {
        System.out.println("Test1 begins.");
        List<Integer> list = new List<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        test(list);

        list.clear();
    }

    // 只有一个结点的链表: 1
    private static void test2() {
        System.out.println("Test2 begins.");
        List<Integer> list = new List<>();
        list.add(1);

        test(list);

        list.clear();
    }

    // 空链表
    private static void test3() {
        System.out.println("Test3 begins.");
        List<Integer> list = new List<>();

        test(list);
    }
}

class List<E> {

    private ListNode head;
    private ListNode tail;
    private int size;

    public List() {
        head = tail = null;
        size = 0;
    }

    public boolean add(E value) {
        ListNode newNode = new ListNode(value);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
        ++size;
        return true;
    }

    public void clear() {
        for (ListNode node = head; node != null; ) {
            ListNode next = node.next;
            node.next = null;
            node = next;
            --size;
        }

        head = tail = null;
    }

    public void print() {
        for (ListNode node = head; node != null; node = node.next) {
            System.out.print(node.value + " ");
        }
        System.out.println();
    }

    public void printReversedOrderIteratively() {
        Stack<ListNode> stack = new Stack<>();
        for (ListNode node = head; node != null; node = node.next) {
            stack.push(node);
        }
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    public void printReversedOrderRecursively() {
        printReversedOrderRecursively0(head);
    }

    private void printReversedOrderRecursively0(ListNode head) {
        if (head != null) {
            if (head.next != null) {
                printReversedOrderRecursively0(head.next);
            }
            System.out.print(head + " ");
        }
    }


    class ListNode {
        private E value;
        private ListNode next;

        public ListNode(E value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }
}
