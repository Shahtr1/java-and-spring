Q0 -> How many Functional Interfaces?

The interface who contains only one abstract method 
but can have multiple default and static method.
Example:
	Runnable -----> run()
	Callable ----->  call()
	Comparable -----> compareTo()
	Comparator -----> compare()
	Consumer ------> accept()
	Predicate -------> test()
	Supplier --------> get()

It has annotation @FunctionalInterface

Q1 -> What is Consumer Functional Interface?
A  -> Consumer<T> is an in-built functional interface introduced in Java 8. Consumer can be used in all contexts where an object needs to be consumed i.e., 
	taken as input, and some operation is to be performed on the object without returning any result.

	void accept(T t);

	//////// Consumer example ////////

	main(){
		Consumer<Integer> consumer = t -> sysout("Printing : " + t);
		consumer.accept(10);

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

		list.stream().forEach(consumer);

		or pass directly

		list.stream().forEach(t -> sysout("Printing : " + t));
	}

	/////// END ////////


Q2 -> What is Predicate Functional Interface?
A  -> This interface is used for conditional check
	where you think, we can use these true/false returning functions in day to day programming we should choose Predicate.

	boolean test(T t);

	//////// Predicate Example ///////

	main(){

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

		list.stream().filter(t -> t % 2 == 0).forEach(t -> sysout("Even : " + t));

	}

	//////// END ////////



Q3 -> What is Supplier Functional Interface?
A 	-> Supplier can be used in all contexts where there is no input but an output us expected.
	
	T get();

	//////// Supplier Example ////////

	Supplier<String> supplier = () -> {
		return "Hi hero";
	}

	sysout(supplier.get());

	List<String> list = Arrays.asList("a", "b");

	sysout(list.stream().findAny().orElseGet(supplier));

	//////// END ////////

Q4 -> How can we create our own FunctionalInterface?
A  -> //////// Code Starts //////

	@FunctionalInterface
	public interface MyFunctionalInterface{
		
		// // Test 1 without parameters
		// void m1();


		// // Test 2 with parameter
		// void sum(int input);


		// Test 3 with return type and multiple args 
		int subtract(int i1, int i2);


		default void m2(){
			sysout("")
		}

		default void m3(){
			sysout("")
		}

		static void m4(){
			sysout("")
		}
	}

	public class MainApp{
		
		// // Test 1 without parameters
		// MyFunctionalInterface myInterface = () -> {
		// 	sysout("m1");
		// };

		// myInterface.m1();



		// // Test 2 with parameter
		// MyFunctionalInterface myInterface = (input) -> {
		// 	sysout("Sum : " + input);
		// }

		// myInterface.sum(123);



		// Test 3 with return type and multiple args 
		MyFunctionalInterface myInterface = (i1, i2) -> {
			return i2 - i1;
		};

		sysout(myInterface.subtract(8, 20));

	}

	///////	END //////














