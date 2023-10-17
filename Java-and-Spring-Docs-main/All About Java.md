### What are default methods?

Before Java 8, interfaces could have only abstract methods.

The implementation of these methods has to be provided in a separate class.

So, if a new method is to be added in an interface, then its implementation code has to be provided in the class implementing the same interface

To overcome this issue,
Java 8 has introduced the concept of default methods which allow the interfaces to have methods with implementation
without affecting the classes that implement the interface.

### Static methods in interfaces?

The interfaces can have static methods as well which is similar to static method of classes.

### Difference between Abstract class and Interface

1. - Abstract class can have abstract and non-abstract methods.
   - Interface can have only abstract methods. Since Java 8, it can have default and static methods also.
2. - Abstract class doesn't support multiple inheritance.
   - Interface supports multiple inheritance.
3. - Abstract class can provide the implementation of interface.
   - Interface can't provide the implementation of abstract class.
4. - An abstract class can be extended using keyword "extends"
   - An interface can be implemented using keyword "implements".
5. - A Java abstract class can have class members like private, protected, etc.
   - Members of a Java interface are public by default.

### What are Primitive Types?

A -> Java defines eight primitive data types:

1. byte
2. short
3. int
4. long
5. float
6. double
7. boolean and
8. char.

All other variables in java are object reference types.

- Primitive types in Java are called literals.
- Primitive types in Java are statically-typed, which means all variables must first be declared before they can be used,

### What are Wrapper Classes?

A wrapper class is an object that encapsulates a primitive type. Each primitive type has a corresponding wrapper:

Each wrapper class has Object as a superclass.

- Byte, Short, Integer, Long Float and Double have Number as their direct superclass.

This means that each wrapper class can implement the methods of the Object class such as `hashCode()`, `equals(Object obj)`, `clone()`, and `toString()`.

Since wrapper objects are reference types, their default value will be `null`.

This behavior is important to understand when you are choose between using a primitive type or a wrapper object.

- #### Wrapper Objects are Immutable:

  All primitive wrapper objects in Java are final, which means they are immutable. When a wrapper object get its value modified,

  the compiler must create a new object and then reassign that object to the original.

```java
  public void addOne(Integer i) {
  i = i + 1;
  }
```

This creation and eventual garbage collection of objects will add a lot of overhead, especially when doing large computations in loops.

### What is Autoboxing?

Autoboxing is the automatic conversion of primitive types to their corresponding object wrapper classes.

```java
    List<Integer> numbers = new ArrayList<Integer>();
    	for(int i = 0; i < 10; i++) {
    	  numbers.add(i);
    	}
```

### What is Unboxing?

Unboxing is the automatic conversion of object wrapper types into their corresponding primitive types.

```java
    Integer a = new Integer(5);
    int b = a;
```

### Explaing Equals Behaviour in Autoboxing and Unboxing?

Autoboxing and Unboxing does not always happen with the `==` operator.

It will compare the object’s reference in memory.

When only one of the arguments of the `==` operator is an object and the other is a primitive type, unboxing will occur.

To avoid situations like this we can use the `.equals()` operator.

### When to use Primitive types?

When doing a large amount of calculations, primitive types are always faster — they have much less overhead.

When you don’t want the variable to be able to be null.

When you don’t want the default value to be null.
If the method must return a value

### When to use Wrapper Class?

When you are using Collections or Generics — it is required

If you want the `MIN_SIZE` or `MAX_SIZE` of a type.

If sometimes the method can return a null value.

### What are First-Class Functions?

A Programming language is said to have first-class functions if it treats functions as first-class citizens.

### What is a First-Class Citizen?

A first-class citizen (sometimes called first-class objects) is an entity which supports all the operations generally available to other entities.

These operations typically include being passed as an argument, returned from a function, and assigned to a variable.

### What are closures?

A closure is a record storing a function together with an environment: a mapping associating each free variable of the function with the value or storage location to which the name was bound when the closure was created.

A closure, allows the function to access these captured variables through the closure's reference to them, even when the function is invoked outside their scope.

```js
myFunction();
document.getElementById("demo").innerHTML = a \* a;

    	function myFunction() {
    		a = 4;
    	}
```

### What is Lambda Expression?

The Expression through which we can represent an Anonymous function.

1. Anonymous: Nameless/Unknown
2. Anonymous Function: A method who dont have any name or modifier.

```js
var greet = function () {
  console.log("Welcome to GeeksforGeeks!");
};
greet();
```

### Can we write Lambda Expression for every method?

No.
Only can be applicable for functional interface.
The method that is present in functional interface, for that method we can only write it.

It reduces the boiler plate code, no need to repeat shit.

### What is Stream in Java?

Stream API is used to process collections of objects.

A stream is a sequence of objects that supports various methods which can be pipelined to produce the desired result.

A stream is not a data structure instead it takes input from the Collections, Arrays or I/O channels.

Streams dont change the original data structure, they only provide the result as per the piplelined methods.

#### NOTE: check functional interfaces file first

### How to sort a list using lambda?

```java
Collections.sort(list); // ascending
Collections.reverse(list); // descending
sysout(list);

    list.stream()
    	.sorted()
    	.forEach(s -> sysout(s)); // ascending

    list.stream()
    	.sorted(Comparator.reverseOrder)
    	.forEach(s -> sysout(s)); // descending

    Collections.sort(employees, (o1, o2) -> (int) (o1.getSalary() - o2.getSalary()));

    sysout(employees);

    employees.stream()
    	.sorted((o1, o2) -> (int) (o1.getSalary() - o2.getSalary()))
    	.forEach(t -> sysout(""))

    // we can also directly pass the lambda reference
    employees.stream()
    	.sorted(Comparator.comparing(emp -> emp.getSalary()))
    	.forEach(t -> sysout(""))

    // we can also directly pass the method reference
    employees.stream()
    	.sorted(Comparator.comparing(Employee::getName))
    	.forEach(t -> sysout(""))
```

### How to sort a map using lambda?

```java
public class Employee implements Comparable<Employee> {

        private Long id;
        private String name;

        // constructor, getters, setters

        // override equals and hashCode
        @Override
        public int compareTo(Employee employee) {
            return (int)(this.id - employee.getId());
        }
    }

    Map<String, Employee> map = new HashMap<>();

    Employee employee1 = new Employee(1L, "Mher");
    map.put(employee1.getName(), employee1);
    Employee employee2 = new Employee(22L, "Annie");
    map.put(employee2.getName(), employee2);
    Employee employee3 = new Employee(8L, "John");
    map.put(employee3.getName(), employee3);
    Employee employee4 = new Employee(2L, "George");
    map.put(employee4.getName(), employee4);
```

1. #### Using a TreeMap:

   keys in TreeMap are sorted using their natural order.

```java
   TreeMap<String, Employee> sorted = new TreeMap<>(map);
```

2. #### Using ArrayList:

```java
   List<String> employeeByKey = new ArrayList<>(map.keySet());
   Collections.sort(employeeByKey);
```

3. #### Sort by Value:

```java
   List<Employee> employeeById = new ArrayList<>(map.values());
   Collections.sort(employeeById);
```

4. #### Using a TreeSet:

```java
   SortedSet<String> keySet = new TreeSet<>(map.keySet());
   SortedSet<Employee> values = new TreeSet<>(map.values());
```

5. #### Using Lambdas and Streams:

   Since the Java 8, we can use the Stream API and lambda expressions to sort the map. All we need is to call the sorted method over the map's stream pipeline.

```java

   map.entrySet()
   .stream()
   .sorted(Map.Entry.<String, Employee>comparingByKey())
   .forEach(System.out::println);

   map.entrySet()
   .stream()
   .sorted(Map.Entry.comparingByValue())
   .forEach(System.out::println);
```

### Difference between `map()` and `flatMap()` method?

Both these methods are intermediate methods
and returns another stream as part of the output.

- `map()` method used for transformation.
  takes Stream<T> as input and return Stream<R>
  Its mapper function produces one value for each input value.
  hence it is also called One-to-One mapping

- `flatMap()` used for transformation & flattening.
  takes Stream of streams as input and return Stream
  Its mapper function produces multiple value for each input value.

  hence it is also called One-to-Many mapping

  #### Data transformation:

  `Stream.of("a", "b", "c", "d")`

  to

  `[A, B, C, D]`

  #### Flattening:

  `[[1, 2], [3, 4], [5, 6], [7, 8]]`

  to

  `[1, 2, 3, 4, 5, 6, 7, 8]`

  #### Examples:

  ```java
  // One-to-One
  List<string> emails = customers.stream()
  .map(customer -> customer.getEmail())
  .collect(Collectors.toList()); // converting back to list

  sysout(emails);

  // One-to-Many
  // if customer has multiple phone numbers
  List<List<string>> phoneNumbers = customers.stream()
  .map(customer -> customer.getPhoneNumbers())
  .collect(Collectors.toList());

  sysout(phoneNumbers);

  // go for flatMap
  List<string> phones = customers.stream()
  .flatMap(customer -> customer.getPhoneNumbers().stream())
  .collect(Collectors.toList());

  sysout(phones);
  ```

### Define Optional object?

Optional is a container object used to contain not-null objects.

Optional object is used to represent null with absent value.

This class has various utility methods to facilitate code to handle values as `available` or `not available` instead of checking null values.

There are three ways to create Optional object

1. empty
2. of
3. ofNullable

```java
   Customer customer = new Customer(101, john, null, Arrays.asList("23323", "323"));

   Optional<Object> emptyOptional = Optional.empty();
   sysout(emptyOptional); // we will get optional.empty()

   Optional<String> emailOptional1 = Optional.of(customer.getEmail());
   sysout(emailOptional1);
   // returns new optional with value after running Objects.requreNonNull(value)
   // if(Null) throw NullPointerException

   Optional<String> emailOptional2 = Optional.ofNullable(customer.getEmail());
   // value == null ? empty() : of(value);
   sysout(emailOptional2);

   // if value is present we will get
   Optional[tramboos123@gmail.com]

   // to get exact value use get
   if(emailOptional2.isPresent()){
   sysout(emailOptional2.get());
   }

   // use default value, if emailOptional2.get() is null
   sysout(emailOptional2.orElse("default@email.com"))

   // thorw customized exception
   sysout(emailOptional2
   .orElseThrow(() -> new IllegalArgumentException("email not present")));
```

### What is Java Parallel Streams?

Java Parallel Streams is a feature of Java 8.
The order of execution is not under our control.

```java
    // code starts
    long start = 0;
    long end = 0;

    start = System.currenttiomeMillis();
    IntStream.range(1, 100).forEach(System.out::println);
    end = System.currenttiomeMillis();
    sysout("Time taken: " + (end - start));

    sysout("=============================================");

    start = System.currenttiomeMillis();
    IntStream.range(1, 100).Parallel().forEach(System.out::println);
    end = System.currenttiomeMillis();
    sysout("Parallel Time taken: " + (end - start));
```

### What is diamond problem?

Suppose you have the both `Animals` and `Animals1` implementing the same interface `IAnimals`:
interface `IAnimals`

```java
{
public string eat();
}


abstract class Animals implements IAnimals
{
	public abstract void run();
	public string eat(){ return "Animals eating"; }
}

abstract class Animals1 implements IAnimals
{
	public abstract void run1();
	public string eat(){ return "Animals1 eating"; }
}

// If you now define your Dog class:

class Dog extends Animals,Animals1
{
public void run() {System.out.println("Run method");}
public void run1() {System.out.println("Run1 method");}
}
```

It will have the method `eat()` too, which is not abstract so it can use it directly.

What would be the return of this method for a dog?

Which string will be returned, the one with `Animals`, or the one with `Animals1`?

### What is `entry` interface in Java?

Map.Entry interface in Java provides certain methods to access the entry in the `Map`.

### What is `entrySet` And `KeySet` in maps?

Let's create and initialize a `HashMap` whose key is of type String and value is of type Integer:

```java
Map<String, Integer> map = new HashMap<>();
map.put("one", 1);
map.put("two", 2);
```

#### The keySet() Method:

The keySet() method returns the Set of keys contained in the Map.

```java
Set<String> actualValues = map.keySet();
assertEquals(2, actualValues.size());
```

#### The entrySet() Method:

The entrySet() method returns the set of key-value mappings.

```java
Set<Map.Entry<String, Integer>> actualValues = map.entrySet

assertTrue(actualValues.contains(new SimpleEntry<>("one", 1)));

assertTrue(actualValues.contains(new SimpleEntry<>("two", 2)));
```

Here, we've chosen the `AbstractMap.SimpleEntry` implementation of the interface `Map.Entry` for our test.

### What is `Comparator` and `Comparable` in Java?

They are interfaces.

#### Comparable:

```java
public class Player implements Comparable<Player> {

	private int ranking;
	private String name;
	private int age;

	// constructor, getters, setters


	@Override
	public int compareTo(Player otherPlayer) {
		return Integer.compare(getRanking(), otherPlayer.getRanking());
	}

}

public static void main(String[] args) {
	List<Player> footballTeam = new ArrayList<>();
	Player player1 = new Player(59, "John", 20);
	Player player2 = new Player(67, "Roger", 22);
	Player player3 = new Player(45, "Steven", 24);
	footballTeam.add(player1);
	footballTeam.add(player2);
	footballTeam.add(player3);

	System.out.println("Before Sorting : " + footballTeam);
	Collections.sort(footballTeam);
	System.out.println("After Sorting : " + footballTeam);
}
```

#### Comparator:

The Comparator interface defines a `compare(arg1, arg2)` method with two arguments that represent compared objects, and works similarly to the `Comparable.compareTo()` method.

To create a Comparator, we have to implement the Comparator interface.

For our first example, we'll create a Comparator to use the ranking attribute of Player to sort the players:

```java
public class PlayerRankingComparator implements Comparator<Player> {

    @Override
    public int compare(Player firstPlayer, Player secondPlayer) {
    	return Integer.compare(firstPlayer.getRanking(), secondPlayer.getRanking());
    }

}
```

Similarly, we can create a Comparator to use the age attribute of Player to sort the players:

```java
public class PlayerAgeComparator implements Comparator<Player> {

    @Override
    public int compare(Player firstPlayer, Player secondPlayer) {
    	return Integer.compare(firstPlayer.getAge(), secondPlayer.getAge());
    }

}
```

Using this approach, we can override the natural ordering:

```java
PlayerRankingComparator playerComparator = new PlayerRankingComparator();
Collections.sort(footballTeam, playerComparator);
```

If we want a different sorting order, we only need to change the Comparator we're using

The Comparable interface is a good choice to use for defining the default ordering, or in other words, if it's the main way of comparing objects.

Sometimes we can't modify the source code of the class whose objects we want to sort, thus making the use of Comparable impossible

#### NOTE: Avoiding the Subtraction Trick

```java
Comparator<Player> comparator = (p1, p2) -> p1.getRanking() - p2.getRanking();
```

Although it's much more concise than other solutions, it can be a victim of integer overflows in Java:

However, due to integer overflow, the `Integer.MAX_VALUE – (-1)` will be less than zero.

### What is Java `equals()` and `hashCode()` Contract?

The Object class defines both the `equals()` and `hashCode()` methods – which means that these two methods are implicitly defined in every Java class,
including the ones we create:

```java
class Money {
int amount;
String currencyCode;
}

    Money income = new Money(55, "USD");
    Money expenses = new Money(55, "USD");
    boolean balanced = income.equals(expenses)

    // The default implementation of equals() in the Object class says that equality is the same as object identity, and income and expenses are two distinct instances.

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Money))
            return false;
        Money other = (Money)o;
        boolean currencyCodeEquals = (this.currencyCode == null && other.currencyCode == null)
          || (this.currencyCode != null && this.currencyCode.equals(other.currencyCode));
        return this.amount == other.amount && currencyCodeEquals;
    }
```

#### equals() Contract:

Java SE defines a contract that our implementation of the equals() method must fulfill. Most of the criteria are common sense. The equals() method must be:

- reflexive: an object must equal itself
- symmetric: x.equals(y) must return the same result as y.equals(x)
- transitive: if x.equals(y) and y.equals(z) then also x.equals(z)
- consistent: the value of equals() should change only if a property that is contained in equals() changes (no randomness allowed)

#### hashCode():

hashCode() returns an integer representing the current instance of the class.

We should calculate this value consistent with the definition of equality for the class.

Thus if we override the equals() method, we also have to override hashCode().

- internal consistency: the value of hashCode() may only change if a property that is in equals() changes
- equals consistency: objects that are equal to each other must return the same hashCode
- collisions: unequal objects may have the same hashCode

### What is `Association`, `Aggregation` and `Composition`?

Association is a relation between two separate classes which establishes through their Objects.

Association can be `one-to-one`, `one-to-many`, `many-to-one`, `many-to-many`.

In Object-Oriented programming, an Object communicates to another object to use functionality and services provided by that object.

Composition and Aggregation are the two forms of association.

- #### Aggregation:

  It is a special form of Association where:

  - It represents Has-A’s relationship.
  - It is a unidirectional association i.e. a one-way relationship.
  - In Aggregation, both the entries can survive individually which means ending one entity will not affect the other entity.

- #### Composition:

  Composition is a restricted form of Aggregation in which two entities are highly dependent on each other.

  - It represents "part-of" relationship.
  - In composition, both entities are dependent on each other.
  - The composed object cannot exist without the other entity.

Composition is a strong Association whereas Aggregation is a weak Association.

### Why are some Java versions, like 8 also called 1.8?

With the switch to time-based releases with Java 9 the naming scheme also changed, and Java versions aren’t prefixed with 1.x anymore.

### What is the difference between a JRE and a JDK?

Historically, you downloaded just a JRE if you were only interested in running Java programs.

A JRE includes, among other things, the Java Virtual Machine (JVM) and the "java" command line tool.

To develop new Java programs, you needed to download a JDK.

A JDK includes everything the JRE has, as well as the compiler javac and a couple of other tools like `javadoc` (Java documentation generator) and `jdb` (Java Debugger).

Up until Java 8, the Oracle website offered JREs and JDKs as separate downloads - even though the JDK also always included a JRE in a separate folder.

With Java 9 that distinction was basically gone, and you are always downloading a JDK. The directory structure of JDKs also changed, with not having an explicit JRE folder anymore.

### What is difference between ArrayList and LinkedList?

ArrayList and LinkedList both implement the List interface and maintain insertion order.

- ArrayList internally uses a dynamic array to store the elements
- LinkedList internally uses a doubly linked list to store the elements.

- Manipulation with ArrayList is slow because it internally uses an array.
- Manipulation with LinkedList is faster than ArrayList because it uses a doubly linked list,
  If any element is removed from the array, so no bit shifting is required in memory.
  all the other elements are shifted in memory.

- An ArrayList class can act as a list only because it implements List only.
- LinkedList class can act as a list and queue both because it implements List and Deque interfaces.

- ArrayList is better for storing and accessing data.
- LinkedList is better for manipulating data.

- The memory location for the elements of an ArrayList is contiguous.
- The location for the elements of a linked list is not contagious.

- Generally, when an ArrayList is initialized, There is no case of default capacity in a LinkedList.
  a default capacity of 10 is assigned to the ArrayList.

- When the rate of addition or removal rate is more than the read scenarios, then go for the LinkedList.

- Since the elements of an ArrayList are stored more compact as compared to a LinkedList; therefore, the ArrayList is more cache-friendly as compared to the LinkedList.

Thus, chances for the cache miss are less in an ArrayList as compared to a LinkedList. Generally, it is considered that a LinkedList is poor in cache-locality.

- Memory overhead in the LinkedList is more as compared to the ArrayList. It is because, in a LinkedList, we have two extra links (next and previous) as it is required to store the address of the previous and the next nodes,and these links consume extra space. Such links are not present in an ArrayList.
