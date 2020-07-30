package concurrent.juc.map;

import java.io.IOException;

/**
 * Description: 使用链表存储数据
 * User: zhangll
 * Date: 2020-07-29
 * Time: 10:46
 */
public class HashMapDemo {
    Node head = null; // 队列的头部

    public void add(Node node) {
        if (head == null) {
            head = node;
            return;
        }
        // 追加到队列尾部
        Node temp = head;
        while (temp.next != null) { // 找到一个next为空的节点，这个节点就是最后一个
            temp = temp.next;
        }
        temp.next = node;
    }

    public void print() { // 打印链表内容
        Node temp = head;
        while (temp.next != null) {
            System.out.println(temp.key);
            temp = temp.next;
        }
        System.out.println(temp.key);
    }

    public static void main(String[] args) throws IOException {
        HashMapDemo hashMapDemo = new HashMapDemo();
        Node node1 = new Node();
        node1.key = "1";
        hashMapDemo.add(node1);

        Node node2 = new Node();
        node2.key = "4";
        hashMapDemo.add(node2);

        Node node3 = new Node();
        node3.key = "3";
        hashMapDemo.add(node3);

        hashMapDemo.print();

    }
}

class Node {
    public String key;
    public String value;

    public Node next;
}
