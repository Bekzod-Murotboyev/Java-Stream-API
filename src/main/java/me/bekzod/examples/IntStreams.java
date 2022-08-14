package me.bekzod.examples;


import org.junit.jupiter.api.Test;
import me.bekzod.beans.Person;
import me.bekzod.mockdata.MockData;

import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class IntStreams {

    @Test
    public void range() throws Exception {

        System.out.println("with fori");

        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }
        System.out.println("with int stream exclusive");
        IntStream range = IntStream.range(0, 1_000_000_000);
        DoubleStream doubleStream = range.asDoubleStream();
        LongStream longStream = range.asLongStream();

        Stream<Long> boxed1 = longStream.boxed();
        Stream<Integer> boxed = range.boxed();
        range.forEach(System.out::println);

        System.out.println("with int stream inclusive");
        IntStream.rangeClosed(0, 10).forEach(System.out::println);
    }

    // Loop through people using IntStream
    @Test
    public void rangeIteratingLists() throws Exception {
        List<Person> people = MockData.getPeople();
        IntStream.range(0, people.size())
                .parallel()
                .forEach(index -> {
                    // do some kinna job here
                    System.out.println(people.get(index));
                });
    }

    @Test
    public void intStreamIterate() {
        IntStream range = IntStream.range(1, 100);
        OptionalInt any = range.filter(i -> i % 3 == 0).findFirst();
        int asInt = any.getAsInt();


        IntStream.iterate(2, value -> value + 1)
                .asDoubleStream()
                .limit(1000)
                .peek(System.out::println)
                .forEach(System.out::println);
    }

    @Test
    void of() {
        IntStream intStream = IntStream.of(1, 2, 3, 3, 4, 4, 5, 6);
        intStream.forEach(System.out::println);
    }

    @Test
    void ranges() {

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
