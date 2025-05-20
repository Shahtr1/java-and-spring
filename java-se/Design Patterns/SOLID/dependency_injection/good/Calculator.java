package SOLID.dependency_injection.good;

import SOLID.open_closed.Operation;

public class Calculator {
    public int calculateNumber(int number1, int number2, Operation operation){
        return operation.perform(number1, number2);
    }
}
