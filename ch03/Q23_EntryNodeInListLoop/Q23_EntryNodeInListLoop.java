package CodingInterviewChinese2.ch03.Q23_EntryNodeInListLoop;

/**
 * Created by lj1218.
 * Date: 2019/8/29
 *
 * Page: 139
 * 面试题23：链表中环的入口结点
 * 题目：一个链表中包含环，如何找出环的入口结点？例如，在图3.8的链表中，
 * 环的入口结点是结点3。
 *
 *           +--------------+
 *           |              |
 *           V              |
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6
 *
 */
public class Q23_EntryNodeInListLoop {

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

    public ListNode<E> entryNodeOfLoop() {
        ListNode<E> meetingNode = meetingNode();
        if (meetingNode == null) {
            return null;
        }

        // 得到环中节点数目
        int nodesInLoop = 1;
        ListNode<E> pNode1 = meetingNode;
        while (pNode1.getNext() != meetingNode) {
            pNode1 = pNode1.getNext();
            ++nodesInLoop;
        }

        // 先移动 pNode1，次数为环中节点数目
        pNode1 = head;
        for (int i = 0; i < nodesInLoop; ++i) {
            pNode1 = pNode1.getNext();
        }

        // 再移动 pNode1 和 pNode2
        ListNode<E> pNode2 = head;
        while (pNode1 != pNode2) {
            pNode1 = pNode1.getNext();
            pNode2 = pNode2.getNext();
        }

        return pNode1;
    }

    // 如果快慢指针相遇，那么相遇节点一定在环中
    private ListNode<E> meetingNode() {
        if (head == null) {
            return null;
        }

        ListNode<E> slow = head;
        ListNode<E> fast = slow.getNext();
        while (fast != null) {
            if (fast == slow) {
                return fast;
            }

            // slow 走一步
            slow = slow.getNext();

            // fast 走两步
            fast = fast.getNext();
            if (fast != null) {
                fast = fast.getNext();
            }
        }

        return null;
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
        Test7();
    }

    // ==================== Test Code ====================
    public static void test(String testName, LinkedList<Integer> list, ListNode<Integer> entryNode) {
        if (testName != null) {
            System.out.printf("%s begins:\n", testName);
        }

        System.out.println("expected entryNode: " + entryNode);

        if (entryNode == list.entryNodeOfLoop()) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }

        System.out.println();
    }

    // A list has a node, without a loop
    public static void Test1() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(pNode1);

        test("Test1", list, null);
    }

    // A list has a node, with a loop
    public static void Test2() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(pNode1);

        pNode1.setNext(pNode1);

        test("Test2", list, pNode1);
    }

    // A list has multiple nodes, with a loop
    public static void Test3() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode2 = new ListNode<>(2);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode4 = new ListNode<>(4);
        ListNode<Integer> pNode5 = new ListNode<>(5);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(pNode1);
        list.add(pNode2);
        list.add(pNode3);
        list.add(pNode4);
        list.add(pNode5);

        pNode5.setNext(pNode3);

        test("Test3", list, pNode3);
    }

    // A list has multiple nodes, with a loop
    public static void Test4() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode2 = new ListNode<>(2);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode4 = new ListNode<>(4);
        ListNode<Integer> pNode5 = new ListNode<>(5);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(pNode1);
        list.add(pNode2);
        list.add(pNode3);
        list.add(pNode4);
        list.add(pNode5);

        pNode5.setNext(pNode1);

        test("Test4", list, pNode1);
    }

    // A list has multiple nodes, with a loop
    public static void Test5() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode2 = new ListNode<>(2);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode4 = new ListNode<>(4);
        ListNode<Integer> pNode5 = new ListNode<>(5);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(pNode1);
        list.add(pNode2);
        list.add(pNode3);
        list.add(pNode4);
        list.add(pNode5);

        pNode5.setNext(pNode5);

        test("Test5", list, pNode5);
    }

    // A list has multiple nodes, without a loop
    public static void Test6() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode2 = new ListNode<>(2);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode4 = new ListNode<>(4);
        ListNode<Integer> pNode5 = new ListNode<>(5);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(pNode1);
        list.add(pNode2);
        list.add(pNode3);
        list.add(pNode4);
        list.add(pNode5);

        test("Test6", list, null);
    }

    // Empty list
    public static void Test7() {
        LinkedList<Integer> list = new LinkedList<>();

        test("Test7", list, null);
    }
}
