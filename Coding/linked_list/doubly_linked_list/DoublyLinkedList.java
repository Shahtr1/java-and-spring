package linked_list.doubly_linked_list;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList implements IDoublyLinkedList {

    private ListNode head;
    private ListNode tail;

    private int size = 0;

    public ListNode getHead() {
        return head;
    }

    public ListNode getTail() {
        return tail;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String printListForward() {
        if (head == null) {
            System.out.println("List is empty");
            return "List is empty";
        }

        String res = "";
        ListNode pointer = head;
        while (pointer != null) {
            String delimiter = " -> ";
            if (pointer.next == null) {
                delimiter = "";
            }
            System.out.print(pointer.data + delimiter);
            res += pointer.data + delimiter;
            pointer = pointer.next;
        }

        return res;
    }

    @Override
    public String printListBackward() {
        if (tail == null) {
            System.out.println("List is empty");
            return "List is empty";
        }

        String res = "";
        ListNode pointer = tail;
        while (pointer != null) {
            String delimiter = " <- ";
            if (pointer.prev == null) {
                delimiter = "";
            }
            System.out.print(pointer.data + delimiter);
            res += pointer.data + delimiter;
            pointer = pointer.prev;
        }

        return res;
    }

    @Override
    public List<Integer> getListOfInts() {
        List<Integer> resList = new ArrayList<>();
        if (head == null) {
            return resList;
        }
        ListNode pointer = head;
        while (pointer != null) {
            resList.add(pointer.data);
            pointer = pointer.next;
        }
        return resList;
    }

    @Override
    public ListNode getNodeAtIndex(int index) {
        if (size == 0) {
            throw new IllegalStateException("Cannot retrieve node from an empty list");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Index " + index + " can't be negative");
        }
        ListNode pointer = head;
        while (index != 0) {
            index--;
            pointer = pointer.next;
        }
        return pointer;
    }

    public void addNode(ListNode node, int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Index " + index + " can't be negative");
        }
        if (index == size) {
            // TODO:
        } else {
            // TODO:
        }
    }

    @Override
    public void addNodeAtBeginning(ListNode node) {
        if (size == 0) {
            head = node;
            tail = node;
            size++;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;
        size++;
    }

    @Override
    public void addNodeAtEnd(ListNode node) {
        if (size == 0) {
            head = node;
            tail = node;
            size++;
            return;
        }

        tail.next = node;
        node.prev = tail;
        tail = node;
        size++;
    }

    @Override
    public void deleteNode(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteNode'");
    }

    @Override
    public void deleteFirstNode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFirstNode'");
    }

    @Override
    public void deleteLastNode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteLastNode'");
    }

}
