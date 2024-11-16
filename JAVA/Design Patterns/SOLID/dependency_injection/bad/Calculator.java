package SOLID.dependency_injection.bad;

public class Calculator {
    public int calculateNumber(int number1, int number2, String type){
        int result=0;
        switch (type){
            case "sum":
                AddOperation addOperation = new AddOperation();
                result = addOperation.perform(number1, number2);
            case "sub":
                SubtractOperation subtractOperation = new SubtractOperation();
                result = subtractOperation.perform(number1, number2);
        }
        return result;
    }
}
