package linked_list.singly_linked_list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SinglyLinkedListTest {
    private SinglyLinkedList singlyLinkedList;

    @BeforeEach
    void setUp() {
        singlyLinkedList = new SinglyLinkedList();
    }

    @Test
    void testGetListOfStrings_whenAddedThreeNodes_shouldHaveListOfThreeEntries() {
        addNodesToList(3);
        verifyStringArrayInAscendingOrder(3);
    }

    @Test
    void testSize_whenListIsEmpty_returnZero() {
        assertEquals(0, singlyLinkedList.getSize());
    }

    @Test
    void testSize_whenListHasTwoNodes_returnTwo() {
        addNodesToList(2);
        assertEquals(2, singlyLinkedList.getSize());
    }

    @Test
    void testGetLastNodeOfList_whenListIsEmpty_shouldThrowException() {
        assertThrows(IllegalStateException.class, () -> singlyLinkedList.getLastNodeOfList());
    }

    @Test
    void testGetLastNodeOfList_whenThreeNodes_shouldReturnLastNode() {
        addNodesToList(3);
        assertEquals(3, singlyLinkedList.getLastNodeOfList().data);
    }

    @Test
    void testGetNodeAtIndex_whenListIsEmpty_shouldThrowException() {
        assertThrows(IllegalStateException.class, () -> singlyLinkedList.getNodeAtIndex(0));
    }

    @Test
    void testGetNodeAtIndex_whenIndexIsGreaterOrEqualToSizeOfList_shouldThrowException() {
        addNodesToList(3);
        assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.getNodeAtIndex(4));
        assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.getNodeAtIndex(3));
    }

    @Test
    void testGetNodeAtIndex_whenIndexIsNegative_shouldThrowException() {
        addNodesToList(3);
        assertThrows(IllegalArgumentException.class, () -> singlyLinkedList.getNodeAtIndex(-2));
    }

    @Test
    void testGetNodeAtIndex_whenIndexIsProvided_shouldReturnData() {
        addNodesToList(4);

        int data = singlyLinkedList.getNodeAtIndex(2).data;
        assertEquals(3, data);

        data = singlyLinkedList.getNodeAtIndex(3).data;
        assertEquals(4, data);
    }

    @Test
    void testGetNodeAtIndexFromEnd_whenIndexIsProvided_shouldReturnData() {
        addNodesToList(6);

        int data = singlyLinkedList.getNodeAtIndexFromEnd(2).data;
        assertEquals(4, data);

        data = singlyLinkedList.getNodeAtIndexFromEnd(3).data;
        assertEquals(3, data);

        data = singlyLinkedList.getNodeAtIndexFromEnd(0).data;
        assertEquals(6, data);

        data = singlyLinkedList.getNodeAtIndexFromEnd(5).data;
        assertEquals(1, data);
    }

    @Test
    void testAddNode_whenNoIndexProvided_shouldAddAtTheEnd() {
        singlyLinkedList.addNode(new ListNode(1));
        assertEquals(1, singlyLinkedList.getLastNodeOfList().data);

        singlyLinkedList.addNode(new ListNode(2));
        assertEquals(2, singlyLinkedList.getLastNodeOfList().data);

        singlyLinkedList.addNode(new ListNode(3));
        assertEquals(3, singlyLinkedList.getLastNodeOfList().data);
    }

    @Test
    void testAddNode_whenIndexGreaterThanSizeOrNegative_shouldThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.addNode(new ListNode(1), 4));
        assertThrows(IllegalArgumentException.class, () -> singlyLinkedList.addNode(new ListNode(1), -1));
    }

    @Test
    void testAddNode_whenIndexEqualsToSize_listShouldAdd() {
        int size = singlyLinkedList.getSize();
        singlyLinkedList.addNode(new ListNode(1), size);
        assertEquals(1, singlyLinkedList.getLastNodeOfList().data);

        size = singlyLinkedList.getSize();
        singlyLinkedList.addNode(new ListNode(2), size);
        assertEquals(2, singlyLinkedList.getLastNodeOfList().data);

        size = singlyLinkedList.getSize();
        singlyLinkedList.addNode(new ListNode(3), size);
        assertEquals(3, singlyLinkedList.getLastNodeOfList().data);
    }

    @Test
    void testAddNode_whenIndexProvided_listShouldAdd() {
        singlyLinkedList.addNode(new ListNode(1), 0);
        singlyLinkedList.addNode(new ListNode(2), 1);
        singlyLinkedList.addNode(new ListNode(3), 2);
        singlyLinkedList.addNode(new ListNode(5));
        singlyLinkedList.addNode(new ListNode(6));
        singlyLinkedList.addNode(new ListNode(4), 3);
        singlyLinkedList.addNode(new ListNode(7), 6);
        singlyLinkedList.addNode(new ListNode(10));
        singlyLinkedList.addNode(new ListNode(9), 7);
        singlyLinkedList.addNode(new ListNode(8), 7);
        singlyLinkedList.addNode(new ListNode(11), 10);

        assertEquals(11, singlyLinkedList.getSize());
    }

    @Test
    void testDeleteNode_whenListIsEmpty_shouldThrowException() {
        assertThrows(IllegalStateException.class, () -> singlyLinkedList.deleteNode());
        assertEquals(0, singlyLinkedList.getSize());
    }

    @Test
    void testDeleteNode_whenListHasOneNode_shouldDeleteLastNodeAndMakeListEmpty() {
        singlyLinkedList.addNode(new ListNode(1), 0);
        singlyLinkedList.deleteNode();
        assertEquals(0, singlyLinkedList.getSize());
    }

    @Test
    void testDeleteNode_whenListHasMoreThanOneNode_shouldDeleteLastNode() {
        addNodesToList(3);
        singlyLinkedList.deleteNode();
        assertEquals(2, singlyLinkedList.getLastNodeOfList().data);
        assertEquals(2, singlyLinkedList.getSize());
    }

    @Test
    void testDeleteNodeWithIndex_whenIndexIsGreaterOrEqualToSize_shouldThrowException() {
        addNodesToList(3);
        assertThrows(IndexOutOfBoundsException.class, () -> singlyLinkedList.deleteNode(4));
    }

    @Test
    void testDeleteNodeWithIndex_whenIndexIsNegative_shouldThrowException() {
        addNodesToList(3);
        assertThrows(IllegalArgumentException.class, () -> singlyLinkedList.deleteNode(-4));
    }

    @Test
    void testDeleteNodeWithIndex_whenIndexIsZeroAndListHasOneNode_shouldMakeListEmpty() {
        singlyLinkedList.addNode(new ListNode(1), 0);
        singlyLinkedList.deleteNode(0);
        assertEquals(0, singlyLinkedList.getSize());
    }

    @Test
    void testDeleteNodeWithIndex_whenIndexIsPresentAndListHasThreeNodes_shouldRemoveNode() {
        singlyLinkedList.addNode(new ListNode(1));
        singlyLinkedList.addNode(new ListNode(2));
        singlyLinkedList.addNode(new ListNode(3));
        singlyLinkedList.addNode(new ListNode(9));
        singlyLinkedList.addNode(new ListNode(4));
        singlyLinkedList.addNode(new ListNode(5));
        singlyLinkedList.addNode(new ListNode(6));
        singlyLinkedList.deleteNode(3);
        assertEquals(6, singlyLinkedList.getSize());
        verifyStringArrayInAscendingOrder(6);
    }

    @Test
    void testDeleteNodeWithIndex_whenIndexIsLastAndListHasThreeNodes_shouldRemoveLastNode() {
        addNodesToList(6);
        singlyLinkedList.deleteNode(5);
        assertEquals(5, singlyLinkedList.getSize());
        verifyStringArrayInAscendingOrder(5);
        assertEquals(5, singlyLinkedList.getLastNodeOfList().data);
    }

    @Test
    void testReverse_listShouldBeReversed() {
        singlyLinkedList.addNode(new ListNode(5));
        singlyLinkedList.addNode(new ListNode(4));
        singlyLinkedList.addNode(new ListNode(3));
        singlyLinkedList.addNode(new ListNode(2));
        singlyLinkedList.addNode(new ListNode(1));
        singlyLinkedList.reverse();
        verifyStringArrayInAscendingOrder(5);
    }

    @Test
    void testReverse_whenOnlyOneItem_listShouldBeSame() {
        singlyLinkedList.addNode(new ListNode(1));
        singlyLinkedList.reverse();
        assertEquals(1, singlyLinkedList.getSize());
        assertEquals(1, singlyLinkedList.getLastNodeOfList().data);
    }

    @Test
    void testRemoveDuplicates_shouldReturnUniqueData() {
        singlyLinkedList.addNode(new ListNode(1));
        singlyLinkedList.addNode(new ListNode(7));
        singlyLinkedList.addNode(new ListNode(5));
        singlyLinkedList.addNode(new ListNode(2));
        singlyLinkedList.addNode(new ListNode(1));
        singlyLinkedList.addNode(new ListNode(7));
        singlyLinkedList.addNode(new ListNode(2));
        singlyLinkedList.addNode(new ListNode(10));
        singlyLinkedList.addNode(new ListNode(13));

        singlyLinkedList.removeDuplicates();

        assertEquals(1, singlyLinkedList.getNodeAtIndex(0).data);
        assertEquals(7, singlyLinkedList.getNodeAtIndex(1).data);
        assertEquals(5, singlyLinkedList.getNodeAtIndex(2).data);
        assertEquals(2, singlyLinkedList.getNodeAtIndex(3).data);
        assertEquals(10, singlyLinkedList.getNodeAtIndex(4).data);
        assertEquals(13, singlyLinkedList.getNodeAtIndex(5).data);

        assertEquals(6, singlyLinkedList.getSize());
    }

    @Test
    void testHasLoop_shouldNotDetectALoop_returnFalse() {
        addNodesToList(17);
        assertFalse(singlyLinkedList.hasLoop());
    }

    @Test
    void testHasLoopWithTwoEntries_shouldDetectALoop_returnTrue() {
        addNodesToList(2);

        ListNode pointer = singlyLinkedList.getNodeAtIndex(1);
        pointer.next = singlyLinkedList.getHead();

        assertTrue(singlyLinkedList.hasLoop());
    }

    @Test
    void testHasLoopWithFourEntries_shouldDetectALoop_returnTrue() {
        addNodesToList(4);

        ListNode pointer = singlyLinkedList.getNodeAtIndex(2);
        pointer.next = singlyLinkedList.getHead();

        assertTrue(singlyLinkedList.hasLoop());
    }

    @Test
    void testHasLoopWithSevenEntries_shouldDetectALoop_returnTrue() {
        addNodesToList(7);

        ListNode secondNodePointer = singlyLinkedList.getNodeAtIndex(2);
        ListNode fifthNodePointer = singlyLinkedList.getNodeAtIndex(5);
        fifthNodePointer.next = secondNodePointer;

        assertTrue(singlyLinkedList.hasLoop());

    }

    @Test
    void testMergeTwoSortedLists_whenTwoSortedListsAreProvidedOneIsLarger_returnsSingleSortedList() {
        SinglyLinkedList list1 = new SinglyLinkedList();
        list1.addNode(new ListNode(1));
        list1.addNode(new ListNode(4));
        list1.addNode(new ListNode(8));
        list1.addNode(new ListNode(10));
        list1.addNode(new ListNode(15));
        list1.addNode(new ListNode(21));
        list1.addNode(new ListNode(21));
        list1.addNode(new ListNode(27));
        list1.addNode(new ListNode(28));
        list1.addNode(new ListNode(48));
        list1.addNode(new ListNode(100));

        SinglyLinkedList list2 = new SinglyLinkedList();
        list2.addNode(new ListNode(2));
        list2.addNode(new ListNode(6));
        list2.addNode(new ListNode(10));
        list2.addNode(new ListNode(17));
        list2.addNode(new ListNode(21));
        list2.addNode(new ListNode(31));
        list2.addNode(new ListNode(48));

        SinglyLinkedList sortedList = singlyLinkedList.mergeTwoSortedLists(list1, list2);

        assertTrue(areTwoListsEqual(
                List.of(1, 2, 4, 6, 8, 10, 10, 15, 17, 21, 21, 21, 27, 28, 31, 48, 48, 100),
                sortedList.getListOfInts()));

    }

    @Test
    void testMergeTwoSortedLists_whenTwoSortedListsAreProvided_returnsSingleSortedList() {
        SinglyLinkedList list1 = new SinglyLinkedList();
        list1.addNode(new ListNode(1));
        list1.addNode(new ListNode(4));
        list1.addNode(new ListNode(8));

        SinglyLinkedList list2 = new SinglyLinkedList();
        list2.addNode(new ListNode(3));
        list2.addNode(new ListNode(6));
        list2.addNode(new ListNode(7));

        SinglyLinkedList sortedList = singlyLinkedList.mergeTwoSortedLists(list1, list2);

        assertEquals(1, sortedList.getNodeAtIndex(0).data);
        assertEquals(3, sortedList.getNodeAtIndex(1).data);
        assertEquals(4, sortedList.getNodeAtIndex(2).data);
        assertEquals(6, sortedList.getNodeAtIndex(3).data);
        assertEquals(7, sortedList.getNodeAtIndex(4).data);
        assertEquals(8, sortedList.getNodeAtIndex(5).data);

        assertTrue(areTwoListsEqual(List.of(1, 3, 4, 6, 7, 8), sortedList.getListOfInts()));

        assertEquals(6, sortedList.getSize());
    }

    @Test
    void testAddTwoNumbers_whenTwoNumbersArePassed_shouldReturnAListWithSumInReverse() {
        SinglyLinkedList sumList = singlyLinkedList.addTwoNumbers(9583, 465);

        assertTrue(areTwoListsEqual(List.of(8, 4, 0, 0, 1), sumList.getListOfInts()));
    }

    private boolean areTwoListsEqual(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("Both lists should be of same size");
        }
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) {
                return false;
            }
        }

        return true;
    }

    private void verifyStringArrayInAscendingOrder(int length) {
        int[] dataArray = singlyLinkedList.getListOfInts().stream().mapToInt(i -> i).toArray();
        IntStream.range(0, length).forEach(index -> {
            assertEquals(index + 1, dataArray[index]);
        });

        assertEquals(length, dataArray.length);
    }

    private void addNodesToList(int numberOfNodes) {
        IntStream.range(0, numberOfNodes).forEach(index -> {
            singlyLinkedList.addNode(new ListNode(index + 1));
        });
    }

}
