package CodingInterviewChinese2.ch03.Q25_MergeSortedLists;

/**
 * Created by lj1218.
 * Date: 2019/8/29
 *
 * Page: 145
 * 面试题25：合并两个排序的链表
 * 题目：输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按
 * 照递增排序的。例如输入图3.11中的链表1和链表2，则合并之后的升序链表如链
 * 表3所示。
 *
 * 链表1：1 -> 3 -> 5 -> 7
 * 链表2：2 -> 4 -> 6 -> 8
 * 链表3：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
 *
 * 说明：采用 递归 和 非递归 2种解法实现
 */
public class Q25_MergeSortedLists {

    public static void main(String[] args) {
        Test.main();
    }

}

class LinkedList<E extends Comparable> {
    private ListNode<E> head;
    private ListNode<E> tail;

    public LinkedList() {
        this.head = null;
    }

    // 解法1：递归
    // 合并两个排序的链表
    public LinkedList<E> merge(LinkedList<E> list) {
        if (list == null) {
            return this;
        }
        head = mergeCore(head, list.head);
        adjustTail();
        return this;
    }

    // 合并两个排序的链表
    private ListNode<E> mergeCore(ListNode<E> listNode1, ListNode<E> listNode2) {
        if (listNode1 == null) {
            return listNode2;
        } else if (listNode2 == null) {
            return listNode1;
        }

        ListNode<E> mergeHead;
        if (listNode1.getValue().compareTo(listNode2.getValue()) < 0) {
            mergeHead = listNode1;
            mergeHead.setNext(mergeCore(listNode1.getNext(), listNode2));
        } else {
            mergeHead = listNode2;
            mergeHead.setNext(mergeCore(listNode1, listNode2.getNext()));
        }

        return mergeHead;
    }

    // 解法2：非递归
    public LinkedList<E> mergeNonRecursively(LinkedList<E> list) {
        if (list == null || list.head == null) {
            return this;
        }

        if (head == null) {
            head = list.head;
            tail = list.tail;
            return this;
        }

        // 设置合并链表头结点
        ListNode<E> mergedHead;
        ListNode<E> node1 = head;
        ListNode<E> node2 = list.head;
        if (node1.getValue().compareTo(node2.getValue()) < 0) {
            mergedHead = node1;
            node1 = node1.getNext();
        } else {
            mergedHead = node2;
            node2 = node2.getNext();
        }

        // mergedTail 指向头结点，然后将两条链表节点按值由小到大连起来
        ListNode<E> mergedTail = mergedHead;
        while (node1 != null && node2 != null) {
            if (node1.getValue().compareTo(node2.getValue()) < 0) {
                mergedTail.setNext(node1);
                node1 = node1.getNext();
            } else {
                mergedTail.setNext(node2);
                node2 = node2.getNext();
            }
            mergedTail = mergedTail.getNext();
        }

        if (node1 != null) {
            mergedTail.setNext(node1);
        } else {
            mergedTail.setNext(node2);
        }

        head = mergedHead;
        adjustTail();
        return this;
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

    public ListNode<E> getTail() {
        return tail;
    }

    // tail 指向合并后的链表尾
    private void adjustTail() {
        // tail 指向合并后的链表尾
        if (head == null) {
            tail = null;
            return;
        }
        ListNode<E> node = head;
        while (node.getNext() != null) {
            node = node.getNext();
        }
        tail = node;
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

    }

    // ====================测试代码====================
    public static LinkedList<Integer> test(String testName, LinkedList<Integer> list1, LinkedList<Integer> list2) {
        if (testName != null) {
            System.out.printf("%s begins:\n", testName);
        }

        System.out.println("The list1 is:");
        System.out.println(list1);
        System.out.println("The list2 is:");
        System.out.println(list2);

        LinkedList<Integer> mergedList;
        if (list1 != null) {
//            mergedList = list1.merge(list2);
            mergedList = list1.mergeNonRecursively(list2);
        } else if (list2 != null) {
//            mergedList = list2.merge(null);
            mergedList = list2.mergeNonRecursively(null);
        } else {
            mergedList = null;
        }

        System.out.println("The merged list is:");
        System.out.println(mergedList);
        if (mergedList != null) {
            System.out.println("tail: " + mergedList.getTail());
        }
        System.out.println();

        return mergedList;
    }

    // list1: 1->3->5
    // list2: 2->4->6
    public static void Test1() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode5 = new ListNode<>(5);
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(pNode1);
        list1.add(pNode3);
        list1.add(pNode5);

        ListNode<Integer> pNode2 = new ListNode<>(2);
        ListNode<Integer> pNode4 = new ListNode<>(4);
        ListNode<Integer> pNode6 = new ListNode<>(6);
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(pNode2);
        list2.add(pNode4);
        list2.add(pNode6);

        test("Test1", list1, list2);
    }

    // 两个链表中有重复的数字
    // list1: 1->3->5
    // list2: 1->3->5
    public static void Test2() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode5 = new ListNode<>(5);
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(pNode1);
        list1.add(pNode3);
        list1.add(pNode5);

        ListNode<Integer> pNode2 = new ListNode<>(1);
        ListNode<Integer> pNode4 = new ListNode<>(3);
        ListNode<Integer> pNode6 = new ListNode<>(5);
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(pNode2);
        list2.add(pNode4);
        list2.add(pNode6);

        test("Test2", list1, list2);
    }


    // 两个链表都只有一个数字
    // list1: 1
    // list2: 2
    public static void Test3() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(pNode1);

        ListNode<Integer> pNode2 = new ListNode<>(2);
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(pNode2);

        test("Test3", list1, list2);
    }

    // 一个链表为空链表
    // list1: 1->3->5
    // list2: 空链表
    public static void Test4() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        ListNode<Integer> pNode3 = new ListNode<>(3);
        ListNode<Integer> pNode5 = new ListNode<>(5);
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(pNode1);
        list1.add(pNode3);
        list1.add(pNode5);

        test("Test4", list1, null);
    }

    // 两个链表都为空链表
    // list1: 空链表
    // list2: 空链表
    public static void Test5() {
        test("Test5", null, null);
    }
}
