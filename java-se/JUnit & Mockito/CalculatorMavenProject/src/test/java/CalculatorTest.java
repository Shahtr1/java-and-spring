import org.example.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Order(4)
@DisplayName("Test Math operations in Calculator class")
public class CalculatorTest {

    Calculator calculator;

    @BeforeAll
    static void setup(){
        System.out.println("Executing @BeforeAll method.");
    }

    @AfterAll
    static void cleanup(){
        System.out.println("Executing @AfterAll method.");
    }

    @BeforeEach
    void beforeEachTestMethod(){
        System.out.println("Executing @BeforeEach method.");
        calculator = new Calculator();
    }

    @AfterEach
    void afterEachTestMethod(){
        System.out.println("Executing @AfterEach method.");
    }

    // test<System Under test>_<Condition or State Change>_<Expected Result>
    @DisplayName("Test 4/2 = 2")
    @Test
    void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo(){
        // Arrange or // Given

        // Act or  // When
        int result = calculator.integerDivision(4,2);

        // Assert or  // Then
        assertEquals(2,result,"4/2 did not produce 2");
    }

//    @Disabled("TODO: Still need to work on it")
    @DisplayName("Division by zero")
    @Test
    void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithmeticException(){
//        fail("Not implemented yet");

//        Arrange
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";

//        Act & Assert
        ArithmeticException actualException = assertThrows(ArithmeticException.class,()->{
//            Act
            calculator.integerDivision(dividend,divisor);
        },"Division by zero should have thrown an Arithmetic exception.");

        // Assert
        assertEquals(expectedExceptionMessage,actualException.getMessage(),"Unexpected exception message");

    }

    @ParameterizedTest
    @ValueSource(strings = {"John", "Kate", "Alice"}) // can accept only single parameter
    void valueSourceDemonstration(String firstName){
        System.out.println(firstName);
        assertNotNull(firstName);
    }

    @DisplayName("Test integer subtraction [minuend, subtrahend, expectedResult]")
    @ParameterizedTest
//    @MethodSource()
//    @CsvSource( {
//            "33, 1, 32",
//            "54, 1, 53",
//            "24, 1, 23"
//    }  )
//    @CsvSource( {
//            "apple, orange",
//            "apple, ''",
//            "apple, ",  // second parameter has null value
//    }  )
    @CsvFileSource(resources = "/integerSubtraction.csv")
    void integerSubtraction(int minuend,int subtrahend,int expectedResult){
        int actualResult = calculator.integerSubtraction(minuend,subtrahend);
        assertEquals(expectedResult, actualResult,
                () -> minuend + " - " + subtrahend + " did not produce " + expectedResult);

//        use lambda to compute the string only when it fails
    }


//    only used for method source
//    private static Stream<Arguments> integerSubtraction(){
//        return Stream.of(
//                Arguments.of(33, 1, 32),
//                Arguments.of(54, 1, 53),
//                Arguments.of(24, 1, 23)
//                );
//    }
}
