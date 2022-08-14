package me.bekzod.examples;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelStreamTest {

    @Test
    void test() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        listOfNumbers.parallelStream().forEach(number ->
                System.out.println(number + " " + Thread.currentThread().getName())
        );
    }

    @SneakyThrows
    @Test
    void custom() {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        ForkJoinPool customThreadPool = new ForkJoinPool(2);
        int sum = customThreadPool.submit(
                () -> listOfNumbers.parallelStream().reduce(0, Integer::sum)).get();

        customThreadPool.shutdown();
        Assertions.assertThat(sum).isEqualTo(10);
    }
}
