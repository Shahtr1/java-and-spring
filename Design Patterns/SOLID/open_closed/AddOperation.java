package SOLID.open_closed;

public class AddOperation implements Operation{

    @Override
    public int perform(int number1, int number2) {
        return number1 + number2;
    }
}
