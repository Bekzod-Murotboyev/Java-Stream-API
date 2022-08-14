package me.bekzod.intStream;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MinTest {

    @Test
    void of() {
        IntStream intStream = IntStream.of(1, 2, 3, 3, 4, 4, 5, 6);
        intStream.forEach(System.out::println);
    }

    @Test
    void range() {

        IntStream.range(0, 10000)
                .parallel()
                .forEach(o -> {});

        IntStream.rangeClosed(0, 10).forEach(System.out::println);

        int min = IntStream.of(12, 23, 3, 4, 5, 56, 6, 7, 788, -123).min().getAsInt();
        int max = IntStream.of(12, 23, 3, 4, 5, 56, 6, 7, 788, -123).max().getAsInt();
        System.out.println("min = " + min);
        System.out.println("max = " + max);

    }

    @Test
    void iterate() {
        IntStream.iterate(1, (var -> var + 1))
                .limit(100)
                .forEach(System.out::println);

    }


    @Test
    void intSupplier() {
        IntStream generate = IntStream
                .generate(() -> new Random().nextInt(10, 10000))
                .limit(10);
        Stream<Integer> boxed = generate.boxed();

        generate.forEach(System.out::println);

    }

}
