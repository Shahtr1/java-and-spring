package linked_list.circular_singly_linked_list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.Test;

public class CircularLinkedListTest {

    private CircularLinkedList circularLinkedList;

    @Test
    public void testAddNodeAtTheEnd_shouldAddNodes() {
        circularLinkedList = new CircularLinkedList();
        addNodesToEndOfList(4);
        assertEquals("1 -> 2 -> 3 -> 4", circularLinkedList.printList());
    }

    @Test
    public void testAddNodeAtTheBeginning_shouldAddNodes() {
        circularLinkedList = new CircularLinkedList();
        addNodesToBeginningOfList(4);
        assertEquals("4 -> 3 -> 2 -> 1", circularLinkedList.printList());
    }

    private void addNodesToEndOfList(int numberOfNodes) {
        IntStream.range(0, numberOfNodes).forEach(index -> {
            circularLinkedList.addNodeAtTheEnd(new ListNode(index + 1));
        });
    }

    private void addNodesToBeginningOfList(int numberOfNodes) {
        IntStream.range(0, numberOfNodes).forEach(index -> {
            circularLinkedList.addNodeAtTheBeginning(new ListNode(index + 1));
        });
    }

}
