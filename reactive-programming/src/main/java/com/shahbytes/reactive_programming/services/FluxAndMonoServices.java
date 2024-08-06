package com.shahbytes.reactive_programming.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoServices {
    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();
        fluxAndMonoServices.fruitsFlux().subscribe(s -> {
            System.out.println("s = " + s);
        });

        fluxAndMonoServices.fruitsMono().subscribe(s -> {
            System.out.println("Mono -> s = " + s);
        });
    }

    // Flux
    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana")).log();
    }

    // Map
    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .map(String::toUpperCase)
                .log();
    }

    // Filter
    public Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(s -> s.length() > number)
                .log();
    }

    // FlatMap
    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    // FlatMap -> async verification
    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .flatMap(s -> Flux.just(s.split(""))

                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)))
                )
                .log();
    }

    // ConcatMap
    public Flux<String> fruitsFluxConcatMap() {
        // preserve the order
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .concatMap(s -> Flux.just(s.split(""))

                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)))
                )
                .log();
    }

    // FlatMap
    public Mono<List<String>> fruitsMonoFlatMap() {
        return Mono.just("Mango")
                .flatMap(s -> Mono.just(
                                List.of(s.split(""))
                        )
                ).log();
    }

    // Mono
    public Mono<String> fruitsMono() {
        return Mono.just("Mango").log();
    }

    // FlatMapMany
    public Flux<String> fruitsMonoFlatMapMany() {
        // to convert Mono of String to Flux of String
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(
                                s.split("")
                        )
                ).log();
    }

    // Transform
    public Flux<String> fruitsFluxTransform(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .log();
//        .filter(s -> s.length() > number);

    }

    // DefaultIfEmpty
    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
    }

    // SwitchIfEmpty
    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("Pineapple", "Jack Fruit"))
                .transform(filterData)
                .log();

    }

    // Concat -> sequential order
    // Combine difference reactive streams
    public Flux<String> fruitsFluxConcat() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");

        return Flux.concat(fruits, veggies).log();
    }

    // ConcatWith -> sequential order
    // Combine difference reactive streams
    public Flux<String> fruitsFluxConcatWith() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");

        return fruits.concatWith(veggies).log();
    }

    // ConcatWith Mono -> sequential order
    // Combine difference reactive streams
    public Flux<String> fruitsMonoConcatWith() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");

        return fruits.concatWith(veggies).log();
    }

    // Merge -> will subscribe to the publishers eagerly and will be emitting asynchronously
    public Flux<String> fruitsFluxMerge() {
        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Lemon")
                .delayElements(Duration.ofMillis(75));

        return Flux.merge(fruits, veggies).log();
    }

    // MergeWith -> will subscribe to the publishers eagerly and will be emitting asynchronously
    public Flux<String> fruitsFluxMergeWith() {
        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Lemon")
                .delayElements(Duration.ofMillis(75));

        return fruits.mergeWith(veggies).log();
    }

    // MergeWithSequentialOrder -> will subscribe to the publishers eagerly and will be emitting in sequential order
    public Flux<String> fruitsFluxMergeWithSequential() {
        var fruits = Flux.just("Mango", "Orange")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Lemon")
                .delayElements(Duration.ofMillis(75));

        return Flux.mergeSequential(fruits, veggies).log();
    }

    // Different type of reactive type
    // Zip -> upto 8 publishers
    public Flux<String> fruitsFluxZip() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");

        return Flux.zip(fruits, veggies, (first, second) -> first + second).log();
    }

    // Different type of reactive type
    // ZipWith -> upto 8 publishers
    public Flux<String> fruitsFluxZipWith() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");

        return fruits.zipWith(veggies, (first, second) -> first + second).log();
    }

    // Different type of reactive type
    // Zip -> upto 8 publishers
    public Flux<String> fruitsFluxZipTuple() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");
        var moreVeggies = Flux.just("Potato", "Beans");

        return Flux.zip(fruits, veggies, moreVeggies)
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3());
    }

    // Mono ZipWIth
    public Mono<String> fruitsMonoZipWith() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");

        return fruits.zipWith(veggies, (first, second) -> first + second).log();
    }

    // DoOn...
    public Flux<String> fruitsFluxFilterDoOn(int number) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(s -> s.length() > number)
                .doOnNext(s -> System.out.println(s + " " + number))
                .doOnSubscribe(subscription -> System.out.println("subscription " + subscription))
                .doOnComplete(() -> System.out.println("Completed!!!"))
                .log();
    }

    // OnErrorReturn
    public Flux<String> fruitFluxOnErrorReturn() {
        return Flux.just("Apple", "Mango").
                concatWith(Flux.error(
                        new RuntimeException("Grapes")
                ))
                .onErrorReturn("Grapes were skipped");

    }


}
