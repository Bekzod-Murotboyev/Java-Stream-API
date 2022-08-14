package me.bekzod.examples;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import me.bekzod.beans.Car;
import me.bekzod.mockdata.MockData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DistinctAndSetsTest {

    @Test
    public void distinct() throws Exception {
        List<Integer> numbers = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9);
        List<Integer> distinct = numbers.stream().distinct().toList();
//        org.junit.jupiter.api.Assertions.assertEquals(3, distinct.size(), "Length of distinct collection does not match");
//        Assertions.assertThat(distinct).hasSize(9);
        System.out.println(distinct);
    }


    @Test
    public void distinctCars() throws Exception {
        List<Car> cars = MockData.getCars();
        List<Car> distinct = cars.stream()
                .filter(car -> car.getYear() < 2015)
                .distinct().toList();
        System.out.println("cars.size() = " + cars.size());
        System.out.println("distinct.size() = " + distinct.size());
    }


    @Test
    public void distinctWithSet() throws Exception {
        List<Integer> numbers = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9);
        Set<Integer> distinct = new HashSet<>(numbers);
        Assertions.assertThat(distinct).hasSize(9);
        System.out.println(distinct);
    }
}
