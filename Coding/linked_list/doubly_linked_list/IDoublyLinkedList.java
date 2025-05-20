package linked_list.doubly_linked_list;

import java.util.List;

public interface IDoublyLinkedList {
    int getSize();

    String printListForward();

    String printListBackward();

    List<Integer> getListOfInts();

    ListNode getNodeAtIndex(int index);

    void addNode(ListNode node, int index);

    void addNodeAtBeginning(ListNode node);

    void addNodeAtEnd(ListNode node);

    void deleteNode(int index);

    void deleteFirstNode();

    void deleteLastNode();
}
