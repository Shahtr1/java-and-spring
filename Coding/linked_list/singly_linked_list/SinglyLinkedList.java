package linked_list.singly_linked_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SinglyLinkedList implements ISinglyLinkedList {
    private ListNode head;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public ListNode getHead() {
        return head;
    }

    public static void main(String[] args) {
        System.out.println("hello list");
    }

    public void printList() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        ListNode pointer = head;
        while (pointer != null) {
            String delimiter = " -> ";
            if (pointer.next == null) {
                delimiter = "";
            }
            System.out.print(pointer.data + delimiter);
            pointer = pointer.next;
        }
    }

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

    public ListNode getNodeAtIndexFromEnd(int indexToFind) {
        if (size == 0) {
            throw new IllegalStateException("Cannot retrieve node from an empty list");
        }
        if (indexToFind >= size) {
            throw new IndexOutOfBoundsException("Index " + indexToFind + " is out of bounds");
        }
        if (indexToFind < 0) {
            throw new IllegalArgumentException("Index " + indexToFind + " can't be negative");
        }

        int counter = 1;
        ListNode pointer = head;
        while (size - counter != indexToFind) {
            counter++;
            pointer = pointer.next;
        }
        return pointer;
    }

    public ListNode getLastNodeOfList() {
        if (size == 0) {
            throw new IllegalStateException("Cannot retrieve node from an empty list");
        }
        ListNode pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        return pointer;
    }

    public void addNode(ListNode node) {
        if (size == 0) {
            head = node;
        } else {
            ListNode pointer = head;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = node;
        }
        size++;
    }

    public void addNode(ListNode node, int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Index " + index + " can't be negative");
        }
        if (index == size) {
            addNode(node);
        } else {
            ListNode pointer = head;
            if (index == 0) {
                head = node;
                node.next = pointer;
                size++;
                return;
            }
            ListNode indexNode = getNodeAtIndex(index - 1);
            ListNode temp = indexNode.next;
            indexNode.next = node;
            node.next = temp;
            size++;
        }
    }

    public void deleteNode() {
        if (size == 0) {
            throw new IllegalStateException("Cannot delete node from an empty list");
        }
        if (size == 1) {
            head = null;
            size--;
            return;
        }
        ListNode indexNode = getNodeAtIndex(size - 2);
        indexNode.next = null;
        size--;
    }

    public void deleteNode(int index) {
        if (size == 0) {
            throw new IllegalStateException("Cannot delete node from an empty list");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Index " + index + " can't be negative");
        }
        if (index == 0) {
            head = null;
            size--;
            return;
        }
        ListNode pointer = head;
        ListNode nextPointer = head.next;
        while (index != 1) {
            index--;
            pointer = pointer.next;
            nextPointer = nextPointer.next;
        }
        pointer.next = nextPointer.next;
        nextPointer = null;
        size--;
    }

    public void reverse() {
        if (size == 0) {
            System.out.println("List is empty");
            return;
        }
        if (size == 1) {
            return;
        }
        int index = size;
        ListNode prevNode = null;
        ListNode nextNode = head;
        while (index != 0) {
            ListNode upcomingNode = nextNode.next;
            nextNode.next = prevNode;
            prevNode = nextNode;
            if (upcomingNode != null)
                nextNode = upcomingNode;
            index--;
        }
        head = nextNode;
    }

    public void removeDuplicates() {
        Map<Integer, Boolean> valueHolder = new HashMap<>();
        ListNode pointer = null;
        ListNode nextPointer = head;

        while (nextPointer != null) {
            if (valueHolder.get(nextPointer.data) != null) {
                pointer.next = nextPointer.next;
                nextPointer = nextPointer.next;
                size--;
            } else {
                valueHolder.put(nextPointer.data, true);
                pointer = nextPointer;
                nextPointer = nextPointer.next;
            }

        }

        for (Entry<Integer, Boolean> entry : valueHolder.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        System.out.println("end" + size);

    }

    public boolean hasLoop() {
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        if (size == 0) {
            return false;
        }

        do {
            if (fastPointer.next == null) {
                return false;
            }
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        } while (!slowPointer.equals(fastPointer));

        return true;
    }

    public SinglyLinkedList mergeTwoSortedLists(SinglyLinkedList list1, SinglyLinkedList list2) {
        SinglyLinkedList resList = new SinglyLinkedList();

        if (list1.size == 0 && list2.size == 0) {
            return resList;
        }

        if (list1.size == 0) {
            return list2;
        }

        if (list2.size == 0) {
            return list1;
        }

        ListNode firstPointer = list1.head;
        ListNode secondPointer = list2.head;

        while (firstPointer != null && secondPointer != null) {
            if (firstPointer.data < secondPointer.data) {
                resList.addNode(new ListNode(firstPointer.data));
                firstPointer = firstPointer.next;
            } else {
                resList.addNode(new ListNode(secondPointer.data));
                secondPointer = secondPointer.next;
            }
        }

        if (firstPointer != null) {
            resList.addNode(firstPointer);
        } else if (secondPointer != null) {
            resList.addNode(secondPointer);
        }

        return resList;
    }

    public SinglyLinkedList addTwoNumbers(int number1, int number2) {
        SinglyLinkedList resList = new SinglyLinkedList();

        SinglyLinkedList numList1 = new SinglyLinkedList();
        SinglyLinkedList numList2 = new SinglyLinkedList();

        while (number1 > 0) {
            int lastDigit = number1 % 10;
            numList1.addNode(new ListNode(lastDigit));
            number1 = number1 / 10;
        }

        while (number2 > 0) {
            int lastDigit = number2 % 10;
            numList2.addNode(new ListNode(lastDigit));
            number2 = number2 / 10;
        }

        ListNode pointer1 = numList1.head;
        ListNode pointer2 = numList2.head;

        int carry = 0;

        while (pointer1 != null && pointer2 != null) {
            int sum = pointer1.data + pointer2.data + carry;
            carry = sum / 10;
            sum = sum % 10;
            resList.addNode(new ListNode(sum));
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        if (pointer1 != null) {
            carry = calculateSum(resList, pointer1, carry);
            pointer1 = pointer1.next;

        } else if (pointer2 != null) {
            carry = calculateSum(resList, pointer2, carry);
            pointer2 = pointer2.next;
        }

        if (carry > 0) {
            resList.addNode(new ListNode(carry));
        }

        return resList;
    }

    private int calculateSum(SinglyLinkedList resList, ListNode pointer1, int carry) {
        int sum = pointer1.data + carry;
        carry = sum / 10;
        sum = sum % 10;
        resList.addNode(new ListNode(sum));
        return carry;
    }
}
