# Reactive Programming with Project Reactor and Spring WebFlux

## Overview

This repository provides an introduction to Reactive Programming, Project Reactor, and how to use them with Spring WebFlux. The goal is to build reactive applications that are resilient, responsive, and scalable by leveraging asynchronous data streams and the propagation of changes.

## Key Concepts

### Reactive Programming

Reactive Programming is a paradigm that focuses on:

- **Asynchronous**: Operations execute independently, improving efficiency.
- **Event-Driven**: Systems react to events, enabling real-time updates.
- **Non-Blocking**: Systems handle multiple tasks concurrently without waiting for operations to complete.
- **Backpressure**: Mechanism to handle the flow of data between producers and consumers, preventing consumers from being overwhelmed.

### Reactive Streams

Reactive Streams is a specification for asynchronous stream processing with non-blocking backpressure. It defines four main interfaces:

- **Publisher**: Emits a sequence of elements.

  ```java
  import org.reactivestreams.Publisher;
  import org.reactivestreams.Subscriber;

  // SimplePublisher emits a sequence of integers up to a maximum value.
  public class SimplePublisher implements Publisher<Integer> {
      private final int max;

      public SimplePublisher(int max) {
          this.max = max;
      }

      @Override
      public void subscribe(Subscriber<? super Integer> subscriber) {
          // When a subscriber subscribes, create a new SimpleSubscription and pass it to the subscriber.
          subscriber.onSubscribe(new SimpleSubscription(subscriber, max));
      }
  }
  ```

- **Subscriber**: Receives and processes the elements emitted by a Publisher.

  ```java
  import org.reactivestreams.Subscriber;
  import org.reactivestreams.Subscription;

  // SimpleSubscriber receives and processes integers emitted by a Publisher.
  public class SimpleSubscriber implements Subscriber<Integer> {
      private Subscription subscription;

      @Override
      public void onSubscribe(Subscription subscription) {
          this.subscription = subscription;
          // Request the first item.
          subscription.request(1);
      }

      @Override
      public void onNext(Integer item) {
          System.out.println("Received: " + item);
          // Request the next item.
          subscription.request(1);
      }

      @Override
      public void onError(Throwable throwable) {
          System.err.println("Error: " + throwable.getMessage());
      }

      @Override
      public void onComplete() {
          System.out.println("Done");
      }
  }
  ```

- **Subscription**: Represents a one-to-one relationship between a Publisher and a Subscriber.

  ```java
  import org.reactivestreams.Subscriber;
  import org.reactivestreams.Subscription;

  // SimpleSubscription manages the flow of data between the Publisher and the Subscriber.
  public class SimpleSubscription implements Subscription {
      private final Subscriber<? super Integer> subscriber;
      private final int max;
      private int current;

      public SimpleSubscription(Subscriber<? super Integer> subscriber, int max) {
          this.subscriber = subscriber;
          this.max = max;
      }

      @Override
      public void request(long n) {
          // Emit up to n items, but no more than the maximum.
          for (int i = 0; i < n && current < max; i++) {
              subscriber.onNext(current++);
          }
          // If all items have been emitted, notify the subscriber that the stream is complete.
          if (current >= max) {
              subscriber.onComplete();
          }
      }

      @Override
      public void cancel() {
          // Handle cancellation if needed.
      }
  }
  ```

- **Processor**: Combines a Subscriber and a Publisher, transforming data between source and destination.

  ```java
  import org.reactivestreams.Processor;
  import org.reactivestreams.Publisher;
  import org.reactivestreams.Subscriber;
  import org.reactivestreams.Subscription;

  // SimpleProcessor transforms Integer items into String items.
  public class SimpleProcessor implements Processor<Integer, String> {
      private Subscriber<? super String> subscriber;

      @Override
      public void subscribe(Subscriber<? super String> subscriber) {
          // Save the subscriber for later use.
          this.subscriber = subscriber;
      }

      @Override
      public void onSubscribe(Subscription subscription) {
          // Request all items from the upstream publisher.
          subscription.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(Integer item) {
          // Transform the Integer item to a String and pass it to the downstream subscriber.
          subscriber.onNext("Processed: " + item);
      }

      @Override
      public void onError(Throwable throwable) {
          subscriber.onError(throwable);
      }

      @Override
      public void onComplete() {
          subscriber.onComplete();
      }
  }

  ```

### Project Reactor

Project Reactor is a library for building reactive applications on the JVM, fully compliant with the Reactive Streams specification. It provides two main types: `Mono` and `Flux`.

#### Key Concepts and Components

- **Mono**: Represents a single asynchronous value or an empty value.
- **Flux**: Represents a sequence of asynchronous values.
- **Schedulers**: Control the execution context of reactive streams.
- **Operators**: Functions to transform, filter, and manipulate data streams.
- **Backpressure**: Ensures that producers do not overwhelm consumers.

### Spring WebFlux

The Spring Framework integrates reactive programming through Spring WebFlux, supporting both functional and annotation-based programming models.

## Examples

### Basic Mono Example

```java
import reactor.core.publisher.Mono;

public class BasicMonoExample {
    public static void main(String[] args) {
        // Create a Mono that emits a single "Hello, Reactor!" value.
        Mono<String> mono = Mono.just("Hello, Reactor!");

        // Subscribe to the Mono and print the emitted value.
        mono.subscribe(value -> System.out.println("Received: " + value));
    }
}
```

### Basic Flux Example

```java
import reactor.core.publisher.Flux;

public class BasicFluxExample {
    public static void main(String[] args) {
        // Create a Flux that emits a sequence of strings.
        Flux<String> flux = Flux.just("Hello", "Reactor", "World");

        // Subscribe to the Flux and print each emitted value.
        flux.subscribe(value -> System.out.println("Received: " + value));
    }
}

```

### Using Operators with Flux

```java
import reactor.core.publisher.Flux;

public class UsingOperatorsWithFlux {
    public static void main(String[] args) {
        // Create a Flux that emits a range of integers.
        Flux<Integer> flux = Flux.range(1, 10)
            // Transform each element by multiplying it by 2.
            .map(i -> i * 2)
            // Filter elements to keep only those divisible by 4.
            .filter(i -> i % 4 == 0);

        // Subscribe to the Flux and print each emitted value.
        flux.subscribe(value -> System.out::println);
    }
}

```

### Handling Backpressure

```java
import reactor.core.publisher.Flux;

public class HandlingBackpressure {
    public static void main(String[] args) {
        // Create a Flux that emits a large range of integers.
        Flux<Integer> flux = Flux.range(1, 1000)
            // Handle backpressure by buffering up to 10 items.
            .onBackpressureBuffer(10);

        // Subscribe to the Flux and print each emitted value.
        flux.subscribe(value -> System.out::println);
    }
}

```

### Using Schedulers for Parallel Execution

```java
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class UsingSchedulersForParallelExecution {
    public static void main(String[] args) {
        // Create a Flux that emits a range of integers.
        Flux<Integer> flux = Flux.range(1, 10)
            // Schedule the subscription to run on a parallel thread.
            .subscribeOn(Schedulers.parallel());

        // Subscribe to the Flux and print each emitted value.
        flux.subscribe(value -> System.out::println);
    }
}

```

### Advanced Use Cases

#### Combining Multiple Streams

```java
import reactor.core.publisher.Mono;

public class CombiningMultipleStreams {
    public static void main(String[] args) {
        // Create two Monos that emit different strings.
        Mono<String> mono1 = Mono.just("Mono 1");
        Mono<String> mono2 = Mono.just("Mono 2");

        // Combine the two Monos and transform their values into a single string.
        Mono<String> combined = Mono.zip(mono1, mono2, (m1, m2) -> m1 + " and " + m2);

        // Subscribe to the combined Mono and print the result.
        combined.subscribe(value -> System.out::println);
    }
}

```

#### Error Handling

```java
import reactor.core.publisher.Flux;

public class ErrorHandling {
    public static void main(String[] args) {
        // Create a Flux that emits a range of integers and throws an error at 5.
        Flux<Integer> flux = Flux.range(1, 10)
            .map(i -> {
                if (i == 5) {
                    throw new RuntimeException("Error at 5");
                }
                return i;
            })
            // Handle errors by switching to a fallback Flux.
            .onErrorResume(e -> Flux.just(10, 11, 12));

        // Subscribe to the Flux and print each emitted value.
        flux.subscribe(value -> System.out::println);
    }
}

```

#### Retrying on Error

```java
import reactor.core.publisher.Flux;

public class RetryingOnError {
    public static void main(String[] args) {
        // Create a Flux that emits a range of integers and throws an error at 5.
        Flux<Integer> flux = Flux.range(1, 10)
            .map(i -> {
                if (i == 5) {
                    throw new RuntimeException("Error at 5");
                }
                return i;
            })
            // Retry once before propagating the error.
            .retry(1);

        // Subscribe to the Flux and print each emitted value.
        flux.subscribe(value -> System.out::println);
    }
}

```

## Spring WebFlux Examples

### Annotation-Based Controllers

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {

    // Endpoint that returns a single value using Mono.
    @GetMapping("/mono")
    public Mono<String> getMono() {
        return Mono.just("Hello, Mono!");
    }

    // Endpoint that returns a sequence of values using Flux.
    @GetMapping("/flux")
    public Flux<String> getFlux() {
        return Flux.just("Hello", "from", "Flux");
    }
}

```

### Functional Endpoints

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions.route(GET("/mono"),
            request -> ServerResponse.ok().body(Mono.just("Hello, Mono!"), String.class))
            .andRoute(GET("/flux"),
            request -> ServerResponse.ok().body(Flux.just("Hello", "from", "Flux"), String.class));
    }
}

```

### WebClient

```java
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebClientExample {
    public static void main(String[] args) {
        // Create a WebClient instance.
        WebClient webClient = WebClient.create("http://localhost:8080");

        // Perform a GET request to the /mono endpoint and retrieve the response body as a Mono.
        Mono<String> monoResponse = webClient.get()
            .uri("/mono")
            .retrieve()
            .bodyToMono(String.class);

        // Perform a GET request to the /flux endpoint and retrieve the response body as a Flux.
        Flux<String> fluxResponse = webClient.get()
            .uri("/flux")
            .retrieve()
            .bodyToFlux(String.class);

        // Subscribe to the responses and print the results.
        monoResponse.subscribe(System.out::println);
        fluxResponse.subscribe(System.out::println);
    }
}

```

## Conclusion

Reactive programming with Project Reactor provides a powerful way to build resilient, scalable, and efficient applications. By leveraging non-blocking I/O, backpressure, and asynchronous processing, developers can create systems that are highly responsive and capable of handling large volumes of data. The integration with Spring Framework through Spring WebFlux simplifies the development of reactive web applications, enabling the creation of modern, high-performance systems.
