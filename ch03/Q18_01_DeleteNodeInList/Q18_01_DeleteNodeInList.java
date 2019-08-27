package CodingInterviewChinese2.ch03.Q18_01_DeleteNodeInList;

/**
 * Created by lj1218.
 * Date: 2019/8/27
 *
 * Page: 119
 * 面试题18（一）：在O(1)时间删除链表结点
 * 题目：给定单向链表的头指针和一个结点指针，定义一个函数在O(1)时间删除该结点。
 */
public class Q18_01_DeleteNodeInList {

    public static void main(String[] args) {
        Test1();
        Test2();
        Test3();
        Test4();
        Test5();
    }

    // ====================测试代码====================
    public static void Test(String testName, LinkedList<Integer> list, ListNode<Integer> nodeToBeDeleted) {
        System.out.println("\n------------- " + testName + " -------------");
        System.out.println("The original list is:");
        System.out.println(list);

        System.out.println("The node to be deleted is:");
        System.out.println(nodeToBeDeleted);

        list.deleteNode(nodeToBeDeleted);

        System.out.println("The result list is:");
        System.out.println(list);
    }

    // 链表中有多个结点，删除中间的结点
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

        Test("Test1", list, node3);
    }

    // 链表中有多个结点，删除尾结点
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

        Test("Test2", list, node5);
    }

    // 链表中有多个结点，删除头结点
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

        Test("Test3", list, node1);
    }

    // 链表中只有一个结点，删除头结点
    public static void Test4() {
        ListNode<Integer> node1 = new ListNode<>(1);

        LinkedList<Integer> list = new LinkedList<>();
        list.add(node1);

        Test("Test4", list, node1);
    }

    // 链表为空
    public static void Test5() {
        LinkedList<Integer> list = new LinkedList<>();

        Test("Test5", list, null);
    }
}

class LinkedList<E> {
    private ListNode<E> head;
    private ListNode<E> tail;

    public LinkedList() {
        this.head = null;
    }

    public boolean deleteNode(ListNode<E> nodeToBeDeleted) {
        if (head == null || nodeToBeDeleted == null) {
            return false;
        }

        // 要删除的节点不是尾节点
        if (nodeToBeDeleted.getNext() != null) {
            ListNode<E> next = nodeToBeDeleted.getNext();
            nodeToBeDeleted.setValue(next.getValue());
            nodeToBeDeleted.setNext(next.getNext());
        } else if (head == nodeToBeDeleted) {
            // 链表只有一个节点，删除头结点（也是尾节点）
            head = tail = null;
        } else {
            // 链表中有多个节点，删除尾节点
            ListNode<E> node = head;
            while (node.getNext() != nodeToBeDeleted) {
                node = node.getNext();
            }

            node.setNext(null);
            tail = node;
        }
        return true;
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
