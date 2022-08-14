package uz.jl.examples;

import org.junit.jupiter.api.Test;
import uz.jl.beans.Car;
import uz.jl.mockdata.MockData;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class FilteringTest {


    @Test
    public void filter() throws Exception {
        List<Car> cars = MockData.getCars();

        Predicate<Car> carPriceLessThan20 = car -> car.getPrice() < 20_000.00;
        Predicate<Car> yellowCars = car -> car.getColor().equals("Yellow");
        Predicate<Car> superPredicate = carPriceLessThan20.and(yellowCars);

        List<Car> carsLessThan20k = cars.stream().filter(superPredicate).toList();
        carsLessThan20k.forEach(System.out::println);
    }


    @Test
    public void dropWhile() {

        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();
        System.out.println("using dropWhile");
        Stream.of(2, 4, 6, 9, 8, 10, 12)
                .dropWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
    }

    @Test
    public void takeWhile() throws Exception {
        // using filter
        System.out.println("using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();
        System.out.println("using take while");
        Stream.of(2, 4, 6, 9,8, 10, 12)
                .takeWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.print(n + " "));
    }

    @Test
    public void findFirst() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result = Arrays.stream(numbers)
                .filter(n -> n == 50)
                .findFirst()
                .orElse(-1);
        System.out.println(result);

    }

    @Test
    public void findAny() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10};
        int result = Arrays.stream(numbers).filter(n -> n % 2 == 0)
                .findAny()
                .orElse(-1);
        System.out.println(result);
    }

    @Test
    public void allMatch() throws Exception {
        int[] even = {2, 4, 6, 9, 8,10};
        IntPredicate intPredicate = n -> n % 2 == 0;

        boolean allMatch = Arrays.stream(even)
                .allMatch(intPredicate);
        System.out.println(allMatch);
    }

    @Test
    public void anyMatch() throws Exception {
        int[] evenAndOneOdd = {2, 4, 6, 9, 10,4};
        boolean anyMatch = Arrays.stream(evenAndOneOdd)
                .anyMatch(n -> (n % 2 == 1));
        System.out.println(anyMatch);
    }

}



