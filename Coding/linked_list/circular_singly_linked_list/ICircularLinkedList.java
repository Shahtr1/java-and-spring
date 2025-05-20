package linked_list.circular_singly_linked_list;

public interface ICircularLinkedList {
    void addNodeAtTheBeginning(ListNode node);

    void addNodeAtTheEnd(ListNode node);

    void removeNodeFromBeginning();

    String printList();
}
