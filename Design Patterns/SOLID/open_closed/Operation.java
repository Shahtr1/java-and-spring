package SOLID.open_closed;


// We keep adding new operation implementation and our calculator get extensions without modifying the main task
public interface Operation {
    int perform(int number1, int number2);
}
