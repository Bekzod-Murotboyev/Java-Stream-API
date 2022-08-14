package me.bekzod.examples;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransformationsWithFlatMapTest {

    private static final List<List<String>> arrayListOfNames = List.of(
            List.of("Mariam", "Alex", "Ismail"),
            List.of("John", "Alesha", "Andre"),
            List.of("Susy", "Ali")
    );

    @BeforeEach
    void setUp() {
        System.out.println(arrayListOfNames);
    }

    @Test
    public void withoutFlatMap() throws Exception {
        // [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]
        List<String> names = new ArrayList<>();
        for (List<String> strings : arrayListOfNames) {
            names.addAll(strings);
        }
        System.out.println(names);
    }

    @Test
    public void withFlatMap() throws Exception {
        // [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]
        Stream<Stream<String>> streamStream = arrayListOfNames
                .stream()
                .map(List::stream);
        Stream<String> stringStream = arrayListOfNames.stream().flatMap(List::stream);
        Stream<String> names = arrayListOfNames.stream()
                .flatMap(List::stream);
        List<String> strings = names.toList();

        System.out.println(strings);
    }

    @Test
    public void flatMapWithOptionals() {
        List<Optional<String>> optionals = List.of(
                Optional.of("Amigos"),
                Optional.of("Code")
        );

        List<String> list = optionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        System.out.println(list);
    }
}

