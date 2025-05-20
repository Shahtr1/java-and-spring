package linked_list.circular_singly_linked_list;

public class CircularLinkedList implements ICircularLinkedList {
    private ListNode tail;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public ListNode getTail() {
        return tail;
    }

    @Override
    public void addNodeAtTheBeginning(ListNode node) {
        if (size == 0) {
            tail = node;
            tail.next = tail;
            size++;
            return;
        }

        ListNode temp = tail.next;
        tail.next = node;
        node.next = temp;
        size++;
    }

    @Override
    public void addNodeAtTheEnd(ListNode node) {
        if (size == 0) {
            tail = node;
            tail.next = tail;
            size++;
            return;
        }

        ListNode temp = tail.next;
        tail.next = node;
        node.next = temp;
        tail = node;
        size++;
    }

    @Override
    public void removeNodeFromBeginning() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeNodeFromBeginning'");
    }

    @Override
    public String printList() {
        if (size == 0)
            return "List is empty";
        String res = "";
        ListNode pointer = tail.next;
        do {
            String delimeter = " -> ";
            if (pointer.equals(tail)) {
                delimeter = "";
            }
            res += pointer.data + delimeter;
            pointer = pointer.next;
        } while (!pointer.equals(tail.next));

        return res;
    }

}
