### Explain all Java Version Features?

Here’s a quick overview of what the specific versions have to offer:

### Java 8: - Language Features: Lambdas etc.

Before Java 8, whenever you wanted to instantiate, for example, a new Runnable, you had to write an anonymous inner class like so:

```java
    Runnable runnable = new Runnable(){
        @Override
        public void run(){
            System.out.println("Hello world !");
        }
    };
```

With lambdas, the same code looks like this:

```java
        Runnable runnable = () -> System.out.println("Hello world two!");
```

- #### Collections & Streams
  In Java 8 you also got functional-style operations for collections, also known as the Stream API.

### Java 9:

- #### Collections:

  Collections got a couple of new helper methods, to easily construct Lists, Sets and Maps.

```java
      List<String> list = List.of("one", "two", "three");

      Set<String> set = Set.of("one", "two", "three");

      Map<String, String> map = Map.of("foo", "one", "bar", "two");
```

- #### Streams:
  Streams got a couple of additions, in the form of `takeWhile`, `dropWhile`, `iterate` methods.

```java
  // create a stream of numbers from 1 to 10
  Stream<Integer> stream
  = Stream.of(4, 4, 4, 5, 6, 7, 8, 9, 10);

  // apply takeWhile to take all the numbers
  // matches passed predicate
  List<Integer> list
  = stream.takeWhile(number -> (number / 4 == 1))
  .collect(Collectors.toList());
```

- #### Optional

  got the sorely missed ifPresentOrElse method.

```java
  // create a Optional
  Optional<Integer> op
  = Optional.of(9455);

  // print value
  System.out.println("Optional: " + op);

  // apply ifPresentOrElse
  op.ifPresentOrElse(
  (value)
  -> { System.out.println(
  "Value is present, its: " + value); },
  ()
  -> { System.out.println(
  "Value is empty"); });
```

- Interfaces got private methods

### Java 10:

- There have been a few changes to Java 10, like Garbage Collection etc.

But the only real change you as a developer will likely see is the introduction of the `var`-keyword, also called local-variable type inference.

### Java 11:

- Strings got a couple new methods

```java
  "Marco".isBlank();
  "Mar\nco".lines();
  "Marco ".strip();
```

### Java 12:

- Unicode 11 support and a preview of the new switch expression.
- While ASCII uses only 1 byte the Unicode uses 4 bytes to represent characters.

### Java 13:

- Unicode 12.1 support

- Switch statements look like this

```java
  boolean result = switch (status) {
  case SUBSCRIBER -> true;
  case FREE_TRIAL -> false;
  default -> throw new IllegalArgumentException("something is murky!");
  };
```

- Multiline Strings:

```java
  String htmlWithJava13 = """
  <html>
  <body>
  <p>Hello, world</p>
  </body>
  </html>
  """;

```
