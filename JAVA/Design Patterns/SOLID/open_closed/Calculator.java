package SOLID.open_closed;

public class Calculator {
    public int calculateNumber(int number, int number2, Operation operation){
        return operation.perform(number, number2);
    }
}
