package me.bekzod.examples;


import org.junit.jupiter.api.Test;
import me.bekzod.beans.Person;
import me.bekzod.mockdata.MockData;

import java.util.List;
import java.util.OptionalInt;
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
}
