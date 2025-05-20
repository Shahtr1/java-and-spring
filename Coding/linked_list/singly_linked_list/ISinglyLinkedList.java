package linked_list.singly_linked_list;

import java.util.List;

public interface ISinglyLinkedList {
    int getSize();

    void printList();

    List<Integer> getListOfInts();

    ListNode getNodeAtIndex(int index);

    ListNode getNodeAtIndexFromEnd(int index);

    ListNode getLastNodeOfList();

    void addNode(ListNode node);

    void addNode(ListNode node, int index);

    void deleteNode();

    void deleteNode(int index);

    void reverse();

    void removeDuplicates();

    boolean hasLoop();

    SinglyLinkedList mergeTwoSortedLists(SinglyLinkedList list1, SinglyLinkedList list2);

    SinglyLinkedList addTwoNumbers(int number1, int number2);

}