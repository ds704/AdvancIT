package aufgabe10;

import org.w3c.dom.Node;

import java.sql.Array;
import java.util.ArrayList;

public class FifoQueue {
    Node head;

    public static void main(String[] args) {
        FifoQueue neueListe = new FifoQueue();
        neueListe = FifoQueue.Node.put(neueListe,"Hallo");
        neueListe = FifoQueue.Node.put(neueListe,"Daniel");
        neueListe = FifoQueue.Node.put(neueListe,"es hat geklappt");
        FifoQueue.Node.get(neueListe);

    }

    static class Node {
        String data;
        Node next;

        Node(String d) {
            data = d;
            next = null;
        }

        public static FifoQueue put(FifoQueue list, String data) {
            Node new_node = new Node(data);
            if (list.head == null) {
                list.head = new_node;
            } else {
                Node last = list.head;
                while (last.next != null) {
                    last = last.next;
                }

            }
            return list;
        }

            public static void get(FifoQueue list)
            {
                Node currNode = list.head;
                System.out.print("Linked List: ");
                while (currNode != null) {
                    System.out.print(currNode.data + " ");
                    currNode = currNode.next;
                }
            }


        }


}
