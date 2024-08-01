package com.shahbytes.reactive_programming.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void testFruitsFlux() {
        var fruitsFlux = fluxAndMonoServices.fruitsFlux();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void testFruitsMono() {
        var fruitsMono = fluxAndMonoServices.fruitsMono();

        StepVerifier.create(fruitsMono)
                .expectNext("Mango")
                .verifyComplete();
    }

    @Test
    void testFruitsFluxMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMap();

        StepVerifier.create(fruitsFlux)
                .expectNext("MANGO", "ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    void testFruitsFluxFilter() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFilter(5);

        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void testFruitsFluxFlatMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFlatMap();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void testFruitsFluxFlatMapAsync() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFlatMapAsync();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoFlatMap();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcatMap();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMapMany() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoFlatMapMany();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxTransform(5);

        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxTransformDefaultIfEmpty(10);

        StepVerifier.create(fruitsFlux)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxTransformSwitchIfEmpty(8);

        StepVerifier.create(fruitsFlux)
                .expectNext("Pineapple", "Jack Fruit")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcat();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Tomato", "Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcatWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Tomato", "Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsMonoConcatWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoConcatWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMerge();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Tomato", "Orange", "Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMergeWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Tomato", "Orange", "Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWithSequential() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMergeWithSequential();
        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Orange", "Tomato", "Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxZip();
        StepVerifier.create(fruitsFlux)
                .expectNext("MangoTomato", "OrangeLemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxZipWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("MangoTomato", "OrangeLemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxZipTuple();
        StepVerifier.create(fruitsFlux)
                .expectNext("MangoTomatoPotato", "OrangeLemonBeans")
                .verifyComplete();
    }


    @Test
    void fruitsMonoZipWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoZipWith();
        StepVerifier.create(fruitsFlux)
                .expectNext("MangoTomato")
                .verifyComplete();
    }
}
