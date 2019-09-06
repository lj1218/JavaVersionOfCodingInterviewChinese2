package CodingInterviewChinese2.ch05.Q52_FirstCommonNodesInLists;

/**
 * Created by lj1218.
 * Date: 2019/9/6
 *
 * Page: 253
 * 面试题52：两个链表的第一个公共结点
 * 题目：输入两个链表，找出它们的第一个公共结点。
 */
public class Q52_FirstCommonNodesInLists {

    public static void main(String[] args) {
        Test.main();
    }

    public static ListNode<Integer> findFirstCommonNode(ListNode<Integer> list1, ListNode<Integer> list2) {
        if (list1 == null || list2 == null) {
            return null;
        }

        // 计算 list1，list2 长度 及其 差值 diff
        int list1Len = listLength(list1);
        int list2Len = listLength(list2);
        int diff = Math.abs(list1Len - list2Len);

        ListNode<Integer> longList = list1;
        ListNode<Integer> shortList = list2;
        if (list1Len < list2Len) {
            longList = list2;
            shortList = list1;
        }

        // 长链表先走 diff 步
        for (int i = 0; i < diff; ++i) {
            longList = longList.getNext();
        }

        // 两条链表一起前进，相等则找到第一个公共节点
        while (shortList != null && shortList != longList) {
            shortList = shortList.getNext();
            longList = longList.getNext();
        }

        return shortList;
    }

    public static <E> int listLength(ListNode<E> list) {
        int len = 0;
        while (list != null) {
            ++len;
            list = list.getNext();
        }
        return len;
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

    public static <E> void connectListNodes(ListNode<E> first, ListNode<E> second) {
        first.next = second;
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

    private static void test(String testName, ListNode<Integer> pHead1, ListNode<Integer> pHead2, ListNode<Integer> pExpected) {
        if (testName == null) {
            return;
        }
        System.out.printf("%s begins: ", testName);

        ListNode<Integer> pResult = Q52_FirstCommonNodesInLists.findFirstCommonNode(pHead1, pHead2);
        if (pResult == pExpected) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }

    // 第一个公共结点在链表中间
    // 1 - 2 - 3 \
    //            6 - 7
    //     4 - 5 /
    private static void Test1() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode2 = new ListNode<>(2);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode4 = new ListNode<>(4);
        ListNode<Integer> pNode5 = new ListNode<>(5);
        ListNode<Integer> pNode6 = new ListNode<>(6);
        ListNode<Integer> pNode7 = new ListNode<>(7);

        ListNode.connectListNodes(pNode1, pNode2);
        ListNode.connectListNodes(pNode2, pNode3);
        ListNode.connectListNodes(pNode3, pNode6);
        ListNode.connectListNodes(pNode4, pNode5);
        ListNode.connectListNodes(pNode5, pNode6);
        ListNode.connectListNodes(pNode6, pNode7);

        test("Test1", pNode1, pNode4, pNode6);
    }

    // 没有公共结点
    // 1 - 2 - 3 - 4
    //
    // 5 - 6 - 7
    private static void Test2() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode2 = new ListNode<>(2);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode4 = new ListNode<>(4);
        ListNode<Integer> pNode5 = new ListNode<>(5);
        ListNode<Integer> pNode6 = new ListNode<>(6);
        ListNode<Integer> pNode7 = new ListNode<>(7);

        ListNode.connectListNodes(pNode1, pNode2);
        ListNode.connectListNodes(pNode2, pNode3);
        ListNode.connectListNodes(pNode3, pNode4);
        ListNode.connectListNodes(pNode5, pNode6);
        ListNode.connectListNodes(pNode6, pNode7);

        test("Test2", pNode1, pNode5, null);
    }

    // 公共结点是最后一个结点
    // 1 - 2 - 3 - 4 \
    //                7
    //         5 - 6 /
    private static void Test3() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode2 = new ListNode<>(2);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode4 = new ListNode<>(4);
        ListNode<Integer> pNode5 = new ListNode<>(5);
        ListNode<Integer> pNode6 = new ListNode<>(6);
        ListNode<Integer> pNode7 = new ListNode<>(7);

        ListNode.connectListNodes(pNode1, pNode2);
        ListNode.connectListNodes(pNode2, pNode3);
        ListNode.connectListNodes(pNode3, pNode4);
        ListNode.connectListNodes(pNode4, pNode7);
        ListNode.connectListNodes(pNode5, pNode6);
        ListNode.connectListNodes(pNode6, pNode7);

        test("Test3", pNode1, pNode5, pNode7);
    }

    // 公共结点是第一个结点
    // 1 - 2 - 3 - 4 - 5
    // 两个链表完全重合
    private static void Test4() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode2 = new ListNode<>(2);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode4 = new ListNode<>(4);
        ListNode<Integer> pNode5 = new ListNode<>(5);

        ListNode.connectListNodes(pNode1, pNode2);
        ListNode.connectListNodes(pNode2, pNode3);
        ListNode.connectListNodes(pNode3, pNode4);
        ListNode.connectListNodes(pNode4, pNode5);

        test("Test4", pNode1, pNode1, pNode1);
    }

    // 输入的两个链表有一个空链表
    private static void Test5() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode2 = new ListNode<>(2);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode4 = new ListNode<>(4);
        ListNode<Integer> pNode5 = new ListNode<>(5);

        ListNode.connectListNodes(pNode1, pNode2);
        ListNode.connectListNodes(pNode2, pNode3);
        ListNode.connectListNodes(pNode3, pNode4);
        ListNode.connectListNodes(pNode4, pNode5);

        test("Test5", null, pNode1, null);
    }

    // 输入的两个链表有一个空链表
    private static void Test6() {
        test("Test6", null, null, null);
    }
}
