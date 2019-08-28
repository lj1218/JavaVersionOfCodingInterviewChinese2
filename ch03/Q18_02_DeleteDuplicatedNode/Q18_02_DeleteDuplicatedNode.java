package CodingInterviewChinese2.ch03.Q18_02_DeleteDuplicatedNode;

/**
 * Created by lj1218.
 * Date: 2019/8/28
 *
 * Page: 122
 * 面试题18（二）：删除链表中重复的结点
 * 题目：在一个排序的链表中，如何删除重复的结点？例如，在图3.4（a）中重复
 * 结点被删除之后，链表如图3.4（b）所示。
 *
 * (a) 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
 * (b) 1 -> 2 -> 5
 */
public class Q18_02_DeleteDuplicatedNode {

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

    // 删除链表中重复的结点
    // in : 1 -> 2 -> 2 -> 2 -> 3 -> 3 -> 4 -> 5 -> 5 -> 6
    // out: 1 -> 4 -> 6
    public void deleteDuplication() {
        if (head == null) {
            return;
        }

        ListNode<E> newTail = null; // 指向链表删除重复节点的尾节点
        ListNode<E> preNode = null; // 指向 node 前一个节点
        ListNode<E> node = head;
        while (node != null) {
            ListNode<E> nextNode = node.getNext(); // 指向 node 后一个节点

            // 找到第一个节点，它与它的上一个、下一个节点均不相等
            while (node.equals(preNode) || node.equals(nextNode)) {
                preNode = node;
                node = nextNode;
                if (nextNode == null) {
                    // 上次 node 已经指向尾节点，遍历结束
                    break;
                }
                nextNode = nextNode.getNext();
            }

            // 将找到的节点，链入新链
            if (newTail == null) {
                newTail = node;
                head = node;
            } else {
                newTail.setNext(node);
                if (node != null) {
                    // 如果找到的节点不为 null，则将 newTail 指向它（这里的判断非常重要）
                    newTail = node;
                }
            }

            // preNode, node 分别指向下一个节点
            if (node != null) {
                preNode = node;
                node = nextNode;
            }
        }

        tail = newTail;
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

    public boolean equals(E[] values) {
        if (values == null) {
            return head == null;
        }

        ListNode<E> node = head;
        int i = 0;
        for (; node != null && i < values.length; ++i) {
            if (!values[i].equals(node.getValue())) {
                break;
            }
            node = node.getNext();
        }

        return (node == null && i == values.length);
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

    public boolean equals(ListNode<E> other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }

        if (value != null) {
            return value.equals(other.value);
        } else return other.value == null;
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
        Test7();
        Test8();
        Test9();
        Test10();
    }

    // ====================测试代码====================
    public static void Test(String testName, LinkedList<Integer> list, Integer[] expectedValues) {
        if (testName != null)
            System.out.printf("%s begins: ", testName);

        System.out.println(list);
        list.deleteDuplication();
        System.out.println(list);

        if (list.equals(expectedValues)) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }
        System.out.println();
    }

    // 某些结点是重复的
    public static void Test1() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(3);
        ListNode<Integer> node5 = new ListNode<>(4);
        ListNode<Integer> node6 = new ListNode<>(4);
        ListNode<Integer> node7 = new ListNode<>(5);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);

        Integer[] expectedValues = {1, 2, 5};
        Test("Test1", list, expectedValues);
    }

    // 没有重复的结点
    public static void Test2() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);
        ListNode<Integer> node6 = new ListNode<>(6);
        ListNode<Integer> node7 = new ListNode<>(7);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);

        Integer[] expectedValues = {1, 2, 3, 4, 5, 6, 7};
        Test("Test2", list, expectedValues);
    }

    // 除了一个结点之外其他所有结点的值都相同
    public static void Test3() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(1);
        ListNode<Integer> node3 = new ListNode<>(1);
        ListNode<Integer> node4 = new ListNode<>(1);
        ListNode<Integer> node5 = new ListNode<>(1);
        ListNode<Integer> node6 = new ListNode<>(1);
        ListNode<Integer> node7 = new ListNode<>(2);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);

        Integer[] expectedValues = {2};
        Test("Test3", list, expectedValues);
    }

    // 所有结点的值都相同
    public static void Test4() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(1);
        ListNode<Integer> node3 = new ListNode<>(1);
        ListNode<Integer> node4 = new ListNode<>(1);
        ListNode<Integer> node5 = new ListNode<>(1);
        ListNode<Integer> node6 = new ListNode<>(1);
        ListNode<Integer> node7 = new ListNode<>(1);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);

        Test("Test4", list, null);
    }

    // 所有结点都成对出现
    public static void Test5() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(1);
        ListNode<Integer> node3 = new ListNode<>(2);
        ListNode<Integer> node4 = new ListNode<>(2);
        ListNode<Integer> node5 = new ListNode<>(3);
        ListNode<Integer> node6 = new ListNode<>(3);
        ListNode<Integer> node7 = new ListNode<>(4);
        ListNode<Integer> node8 = new ListNode<>(4);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);
        list.add(node8);

        Test("Test5", list, null);
    }

    // 除了两个结点之外其他结点都成对出现
    public static void Test6() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(1);
        ListNode<Integer> node3 = new ListNode<>(2);
        ListNode<Integer> node4 = new ListNode<>(3);
        ListNode<Integer> node5 = new ListNode<>(3);
        ListNode<Integer> node6 = new ListNode<>(4);
        ListNode<Integer> node7 = new ListNode<>(5);
        ListNode<Integer> node8 = new ListNode<>(5);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);
        list.add(node8);

        Integer[] expectedValues = {2, 4};
        Test("Test6", list, expectedValues);
    }

    // 链表中只有两个不重复的结点
    public static void Test7() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);

        Integer[] expectedValues = {1, 2};
        Test("Test7", list, expectedValues);
    }

    // 结点中只有一个结点
    public static void Test8() {
        ListNode<Integer> node1 = new ListNode<>(1);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);

        Integer[] expectedValues = {1};
        Test("Test8", list, expectedValues);
    }

    // 结点中只有两个重复的结点
    public static void Test9() {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(1);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);
        list.add(node2);

        Test("Test9", list, null);
    }

    // 空链表
    public static void Test10() {
        LinkedList<Integer> list = new LinkedList<>();

        Test("Test10", list, null);
    }
}
