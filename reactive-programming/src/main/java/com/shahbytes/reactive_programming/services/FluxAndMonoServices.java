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


}
