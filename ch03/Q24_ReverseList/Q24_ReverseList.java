package CodingInterviewChinese2.ch03.Q24_ReverseList;

/**
 * Created by lj1218.
 * Date: 2019/8/29
 *
 * Page: 142
 * 面试题24：反转链表
 * 题目：定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
 */
public class Q24_ReverseList {

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

    // 反转链表
    public ListNode<E> reverse() {
        ListNode<E> cur = head;
        ListNode<E> pre = null;
        ListNode<E> next;
        while (cur != null) {
            next = cur.getNext();
            if (next == null) {
                head = cur;
            }

            cur.setNext(pre);
            if (pre == null) {
                tail = cur;
            }

            pre = cur;
            cur = next;
        }

        return head;
    }

    public ListNode<E> reverseRecursively1() {
        tail = reverseRecursively1Core(head);
        if (tail != null) {
            tail.setNext(null);
        }
        return head;
    }

    // 反转链表
    private ListNode<E> reverseRecursively1Core(ListNode<E> node) {
        if (node == null) {
            return null;
        }

        ListNode<E> next = node.getNext();
        if (next == null) {
            head = node;
        }

        ListNode<E> t = reverseRecursively1Core(next);
        if (t != null) {
            t.setNext(node);
        }
        return node;
    }

    public ListNode<E> reverseRecursively2() {
        tail = head;
        head = reverseRecursively2Core(head);
        return head;
    }

    private ListNode<E> reverseRecursively2Core(ListNode<E> hdr) {
        if (hdr == null || hdr.getNext() == null) {
            return hdr;
        }

        ListNode<E> newHead = reverseRecursively2Core(hdr.getNext());
        hdr.getNext().setNext(hdr);
        hdr.setNext(null);
        return newHead;
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
    }

    // ====================测试代码====================
    public static ListNode<Integer> test(String testName, LinkedList<Integer> list) {
        if (testName != null) {
            System.out.printf("%s begins:\n", testName);
        }

        System.out.println("The original list is:");
        System.out.println(list);

        ListNode<Integer> pReversedHead = list.reverse();
//        ListNode<Integer> pReversedHead = list.reverseRecursively1();
//        ListNode<Integer> pReversedHead = list.reverseRecursively2();

        System.out.println("The reversed list is:");
        System.out.println(list);
        System.out.println();

        return pReversedHead;
    }

    // 输入的链表有多个结点
    public static void Test1() {
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

        test("Test1", list);
    }

    // 输入的链表只有一个结点
    public static void Test2() {
        ListNode<Integer> pNode1 = new ListNode<>(1);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(pNode1);

        test("Test2", list);
    }

    // 输入的链表只有一个结点
    public static void Test3() {
        LinkedList<Integer> list = new LinkedList<>();

        test("Test3", list);
    }
}
