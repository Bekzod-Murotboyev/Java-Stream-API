package uz.jl.examples;

import org.junit.jupiter.api.Test;
import uz.jl.beans.Car;
import uz.jl.beans.Person;
import uz.jl.mockdata.MockData;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SortingTest {

    @Test
    public void sortingSteamOfElements() throws IOException {
        List<Person> people = MockData.getPeople();
        List<String> sorted = people.stream()
                .map(Person::getFirstName)
                .sorted().toList();
        sorted.forEach(System.out::println);
    }

    @Test
    public void sortingSteamOfElementsReverse() throws IOException {
        List<Person> people = MockData.getPeople();

        List<String> sorted = people.stream()
                .map(Person::getFirstName)
                .sorted(Comparator.reverseOrder()).toList();
        sorted.forEach(System.out::println);
    }

    @Test
    public void sortingSteamOfObjets() throws IOException {
        List<Person> people = MockData.getPeople();

        Function<Person, Integer> getAge = Person::getAge;
        Function<Person, String> getFirstName = Person::getFirstName;

        Comparator<Person> comparing = Comparator
                .comparing(getAge)
                .reversed()
                .thenComparing(getFirstName);

        List<Person> sort = people.stream()
                .sorted(comparing)
                .toList();
        Comparator<Person> personComparator = (o1, o2) -> {
            int i = o1.getAge().compareTo(o2.getAge());
            return 0;
        };


        sort.forEach(System.out::println);
    }

    @Test
    public void topTenMostExpensiveBlueCars() throws IOException {
        List<Car> cars = MockData.getCars();
        Predicate<Car> colorFilter = car -> car.getColor().equalsIgnoreCase("blue");
        List<Car> topTen = cars.stream()
                .filter(colorFilter)
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .limit(10).toList();
        topTen.forEach(System.out::println);
    }

}
