package me.bekzod.examples;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import me.bekzod.beans.Car;
import me.bekzod.beans.Person;
import me.bekzod.mockdata.MockData;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MinMax {

    @Test
    public void min() throws IOException {

        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);

        Optional<Integer> optionInte = numbers
                .stream()
                .min(Comparator.reverseOrder());
//                .min(Comparator.comparing());

        optionInte.ifPresent(System.out::println);

        List<Person> people = MockData.getPeople();
        Comparator<Person> xyz = Comparator
                .comparing(Person::getAge)
                .reversed()
                .thenComparing(Person::getFirstName)
                .thenComparing(Person::getGender);
        Person youngest = people.stream()
                .min(xyz)
                .get();

        System.out.println("youngest = " + youngest);
        Integer min = numbers.stream().min(Comparator.naturalOrder()).get();
        System.out.println(min);
    }

    @Test
    public void max() {
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
        Integer max = numbers.stream().max(Comparator.naturalOrder()).get();
        System.out.println(max);
    }


    @Test
    @SneakyThrows
    void comparableTest() {
        List<Car> cars = MockData.getCars();
        cars.stream().sorted((o1, o2) -> {
                    int i = -1 * o1.getYear().compareTo(o2.getYear());
                    if (i == 0)
                        return o1.getPrice().compareTo(o2.getPrice());
                    return i;
                })
                .forEach(System.out::println);

        cars.stream().sorted(Comparator
                        .comparing(Car::getYear).reversed()
                        .thenComparing(Car::getPrice)
                        .thenComparing(Car::getMake))
                .forEach(System.out::println);
    }


}




