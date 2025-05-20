package linked_list.doubly_linked_list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DoublyLinkedListTest {
    private DoublyLinkedList doublyLinkedList;

    @BeforeEach
    void setUp() {
        doublyLinkedList = new DoublyLinkedList();
    }

    @Test
    void testAddNodeAtEnd_shouldAddNodesToTheList() {
        addNodesToEndOfList(4);
        verifyStringArrayInAscendingOrder(4);
        assertEquals("1 -> 2 -> 3 -> 4", doublyLinkedList.printListForward());
        assertEquals("4 <- 3 <- 2 <- 1", doublyLinkedList.printListBackward());
    }

    @Test
    void testAddNodeAtBeginning_shouldAddNodesToTheList() {
        addNodesToBeginningOfList(4);
        assertEquals("1 <- 2 <- 3 <- 4", doublyLinkedList.printListBackward());
        assertEquals("4 -> 3 -> 2 -> 1", doublyLinkedList.printListForward());
    }

    private void verifyStringArrayInAscendingOrder(int length) {
        int[] dataArray = doublyLinkedList.getListOfInts().stream().mapToInt(i -> i).toArray();
        IntStream.range(0, length).forEach(index -> {
            assertEquals(index + 1, dataArray[index]);
        });

        assertEquals(length, dataArray.length);
    }

    private void addNodesToEndOfList(int numberOfNodes) {
        IntStream.range(0, numberOfNodes).forEach(index -> {
            doublyLinkedList.addNodeAtEnd(new ListNode(index + 1));
        });
    }

    private void addNodesToBeginningOfList(int numberOfNodes) {
        IntStream.range(0, numberOfNodes).forEach(index -> {
            doublyLinkedList.addNodeAtBeginning(new ListNode(index + 1));
        });
    }
}
