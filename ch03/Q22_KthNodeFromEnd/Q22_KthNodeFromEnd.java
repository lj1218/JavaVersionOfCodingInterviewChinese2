package CodingInterviewChinese2.ch03.Q22_KthNodeFromEnd;

/**
 * Created by lj1218.
 * Date: 2019/8/28
 *
 * Page: 134
 * 面试题22：链表中倒数第k个结点
 * 题目：输入一个链表，输出该链表中倒数第k个结点。为了符合大多数人的习惯，
 * 本题从1开始计数，即链表的尾结点是倒数第1个结点。例如一个链表有6个结点，
 * 本题从1开始计数，即链表的尾结点是倒数第1个结点。例如一个链表有6个结点，
 * 值为4的结点。
 */
public class Q22_KthNodeFromEnd {

    public static void main(String[] args) {
        Test.main();
    }

}

class LinkedList<E> {
    private ListNode<E> head;
    private ListNode<E> tail;

    public LinkedList() {
        this.head = null;
    }

    public ListNode<E> findKthFromEnd(int k) {
        if (head == null || k <= 0) {
            return null;
        }

        ListNode<E> ahead = head;
        ListNode<E> behind = head;
        // ahead 向前走 k-1 步
        for (int i = 0; i < k - 1; ++i) {
            if (ahead.getNext() == null) {
                // 如果还没走完 k 步 ahead 指向了尾节点，说明 链表长度 < k
                return null;
            }
            ahead = ahead.getNext();
        }

        // 将 ahead 指向尾节点
        while (ahead.getNext() != null) {
            // ahead、behind 同步向前移动
            ahead = ahead.getNext();
            behind = behind.getNext();
        }

        // ahead 指向尾节点，behind 指向倒数第 k 个节点
        return behind;
    }

    public boolean add(ListNode<E> node) {
        if (node == null) {
            return false;
        }

        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode<E> node = head;
        while (node != null) {
            sb.append(node.toString());
            if (node.getNext() != null) {
                sb.append(" -> ");
            }
            node = node.getNext();
        }
        return sb.toString();
    }
}

class ListNode<E> {
    private E value;
    private ListNode<E> next;

    public ListNode(E value) {
        this.value = value;
        next = null;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public ListNode<E> getNext() {
        return next;
    }

    public void setNext(ListNode<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "" + value;
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
    public static void test(
            String testName, LinkedList<Integer> list,
            int kthToTail, ListNode<Integer> expectNode
    ) {
        if (testName != null) {
            System.out.printf("=====%s starts:=====\n", testName);
        }

        System.out.println("expected result: " + expectNode);

        if (expectNode == list.findKthFromEnd(kthToTail)) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }

        System.out.println();
    }

    // 测试要找的结点在链表中间
    public static void Test1() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);

        test("Test1", list, 2, node4);
    }

    // 测试要找的结点是链表的尾结点
    public static void Test2() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);

        test("Test2", list, 1, node5);
    }

    // 测试要找的结点是链表的头结点
    public static void Test3() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);

        test("Test3", list, 5, node1);
    }

    // 测试空链表
    public static void Test4() {
        LinkedList<Integer> list = new LinkedList<>();
        test("Test4", list, 100, null);
    }

    // 测试输入的第二个参数大于链表的结点总数
    public static void Test5() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);

        test("Test5", list, 6, null);
    }

    // 倒数第 0 个节点
    public static void Test6() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);

        test("Test6", list, 0, null);
    }
}
