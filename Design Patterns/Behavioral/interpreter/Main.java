package Behavioral.interpreter;

public class Main {

    // Method to create a rule for checking male names
    public static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        // OR expression: Returns true if either "Robert" or "John" is found in the context
        return new ORExpression(robert, john);
    }

    // Method to create a rule for checking married women
    public static Expression getMarriedWomanExpression() {
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        // AND expression: Returns true only if both "Julie" and "Married" are found in the context
        return new ANDExpression(julie, married);
    }

    // Method to create a rule for checking a married person (male or female)
    public static Expression getMarriedPersonExpression() {
        Expression married = new TerminalExpression("Married");
        Expression person = new ORExpression(new TerminalExpression("Robert"), new TerminalExpression("Julie"));
        // AND expression: Returns true if the person (Robert or Julie) is married
        return new ANDExpression(married, person);
    }

    public static void main(String[] args) {
        // Creating expressions based on the rules
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();
        Expression isMarriedPerson = getMarriedPersonExpression();

        // Test cases to demonstrate the interpreter pattern functionality
        System.out.println("Test Cases for Male Expression:");
        System.out.println("John is male? " + isMale.interpret("John")); // Expected: true
        System.out.println("Robert is male? " + isMale.interpret("Robert")); // Expected: true
        System.out.println("Alice is male? " + isMale.interpret("Alice")); // Expected: false

        System.out.println("\nTest Cases for Married Woman Expression:");
        System.out.println("Julie is a married woman? " + isMarriedWoman.interpret("Married Julie")); // Expected: true
        System.out.println("Julie is a married woman? " + isMarriedWoman.interpret("Single Julie")); // Expected: false
        System.out.println("Robert is a married woman? " + isMarriedWoman.interpret("Married Robert")); // Expected: false

        System.out.println("\nTest Cases for Married Person Expression:");
        System.out.println("Robert is married? " + isMarriedPerson.interpret("Married Robert")); // Expected: true
        System.out.println("Julie is married? " + isMarriedPerson.interpret("Married Julie")); // Expected: true
        System.out.println("Alice is married? " + isMarriedPerson.interpret("Married Alice")); // Expected: false
    }
}
