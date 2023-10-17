### What are Java Streams?

Stream API is used to process collections of objects.
A stream is a sequence of objects that supports various methods which can be pipelined to produce the desired result.

A stream is not a data structure instead it takes input from the Collections, Arrays or I/O channels.

Streams dont change the original data structure, they only provide the result
as per the piplelined methods.

There are many ways to create a stream instance of different sources.

Once created, the instance will not modify its source, therefore allowing the creation of multiple instances from a single source.

- Empty Stream:

```java
  Stream<String> streamEmpty = Stream.empty();
```

We often use the `empty()` method upon creation to avoid returning `null` for streams with no element:

```java
public Stream<String> streamOf(List<String> list) {
return list == null || list.isEmpty() ? Stream.empty() : list.stream();
}
```

- Stream of Collection:

  We can also create a stream of any type of

  #### Collection (Collection, List, Set):

```java
  Collection<String> collection = Arrays.asList("a", "b", "c");

  Stream<String> streamOfCollection = collection.stream();
```

- #### Stream of Array:
  An array can also be the source of a stream:

```java
  String[] arr = new String[]{"a", "b", "c"};

  Stream<String> streamOfArrayFull = Arrays.stream(arr);

  Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);
```

- #### Stream.builder():

  When builder is used, the desired type should be additionally specified in the right part of the statement,
  otherwise the build() method will create an instance of the Stream<Object>:

```java
  Stream<String> streamBuilder =
  Stream.<String>builder().add("a").add("b").add("c").build();
```

- #### Stream.iterate():

  Another way of creating an infinite stream is by using the iterate() method:

```java
  Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);

```

The first element of the resulting stream is the first parameter of the iterate() method.

When creating every following element, the specified function is applied to the previous element. In the example above the second element will be 42.

- #### Stream of String:

  We can also use String as a source for creating a stream with the help of the chars() method of the String class.

  Since there is no interface for CharStream in JDK, we use the IntStream to represent a stream of chars instead.

Java 8 streams can't be reused
