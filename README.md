# *Stream Api*

---

<details>

<summary>

 >### _Imperativ and Declarative Approaches_

</summary>

```java
import org.junit.jupiter.api.Test;
import me.bekzod.beans.Person;
import me.bekzod.mockdata.MockData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ImperativeAndDeclarative {

    @Test
    public void imperativeApproach() throws IOException {
        List<Person> people = MockData.getPeople();

        List<Person> youngPeople = new ArrayList<>();

        int limit = 10;
        int counter = 0;

        for (Person person : people) {
            if (person.getAge() <= 18) {
                youngPeople.add(person);
                counter++;
                if (counter == limit) {
                    break;
                }
            }
        }
        youngPeople.forEach(System.out::println);

    }

    @Test
    public void declarativeApproachUsingStreams() throws Exception {
        List<Person> people = MockData.getPeople();
        List<Person> youngPeople = people
                .stream()
                .filter(p -> p.getAge() <= 25)
                .limit(10)
                .toList();

        youngPeople.forEach(System.out::println);
    }
}
```

</details>

---
<details>

<summary>

 >### _Understanding Stream Api_

</summary>

```java
import org.junit.jupiter.api.Test;
import me.bekzod.beans.Person;
import me.bekzod.mockdata.MockData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UnderstandingStreamsTest {

    @Test
    void collect() throws IOException {
        List<String> emails = MockData.getPeople()
                .stream()
                .map(Person::getEmail)
                .collect(
                        ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll
                );
        emails.forEach(System.out::println);
    }

    @Test
    public void lazy() throws Exception {
        System.out.println(
                MockData.getCars()
                        .stream()
                        .filter(car -> {
                            System.out.println("filter car " + car);
                            return car.getPrice() < 10000;
                        })
                        .map(car -> {
                            System.out.println("mapping car " + car);
                            return car.getPrice();
                        })
                        .map(price -> {
                            System.out.println("mapping price " + price);
                            return price + (price * .14);
                        })
                        .collect(Collectors.toList())
        );
    }
}
```

</details>

---

<details>

<summary>

 >### _Distinct_

</summary>

```java
import org.junit.jupiter.api.Test;
import me.bekzod.beans.Car;
import me.bekzod.mockdata.MockData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class DistinctAndSetsTest {

    @Test
    public void distinct(){
        List<Integer> numbers = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9);
        List<Integer> distinct = numbers
                .stream()
                .distinct()
                .toList();
        assertEquals(9, distinct.size(), "Length of distinct collection does not match");
        assertThat(distinct).hasSize(9);
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
        assertThat(distinct).hasSize(9);
        System.out.println(distinct);
    }
}

```

</details>

---
<details>

<summary>

 >### _Filter_

</summary>

```java
import me.bekzod.beans.Car;
import me.bekzod.mockdata.MockData;
import org.junit.jupiter.api.Test;

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
    public void takeWhile() {
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
    public void findFirst() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result = Arrays.stream(numbers)
                .filter(n -> n == 50)
                .findFirst()
                .orElse(-1);
        System.out.println(result);

    }

    @Test
    public void findAny() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10};
        int result = Arrays.stream(numbers).filter(n -> n % 2 == 0)
                .findAny()
                .orElse(-1);
        System.out.println(result);
    }

    @Test
    public void allMatch()  {
        int[] even = {2, 4, 6, 9, 8,10};
        IntPredicate intPredicate = n -> n % 2 == 0;

        boolean allMatch = Arrays.stream(even)
                .allMatch(intPredicate);
        System.out.println(allMatch);
    }

    @Test
    public void anyMatch()  {
        int[] evenAndOneOdd = {2, 4, 6, 9, 10,4};
        boolean anyMatch = Arrays.stream(evenAndOneOdd)
                .anyMatch(n -> (n % 2 == 1));
        System.out.println(anyMatch);
    }

}





```

</details>

---
<details>

<summary>

 >### _Grouping Data_

</summary>

```java
import org.junit.jupiter.api.Test;
import me.bekzod.beans.Car;
import me.bekzod.mockdata.MockData;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingDataTest {

    @Test
    public void simpleGrouping() throws Exception {

        Map<String, List<Car>> map = MockData.getCars()
                .stream()
                .collect(Collectors.groupingBy(Car::getMake));

        map.forEach((s, cars) -> {
            System.out.println("Make " + s);
            cars.forEach(System.out::println);
            System.out.println("---------------------");
        });

    }

    @Test
    public void groupingAndCounting(){
        List<String> names = List.of(
                "John",
                "John",
                "Mariam",
                "Alex",
                "Mohammado",
                "Mohammado",
                "Vincent",
                "Alex",
                "Alex"
        );

        Map<String, Long> map = names.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting())
                );

        System.out.println(map);

    }

}
```

</details>

---
<details>

<summary>

 >### _IntStream_

</summary>

```java
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
    public void range() {

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
```

</details>

---



<details>

<summary>

 >### _String Joining_

</summary>

```java

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JoiningStrings {

    @Test
    public void joiningStrings() {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        // "Anna, John, Marcos, Helena, Yasmin"
        StringBuilder join = new StringBuilder();

        for (String name : names) {
            join.append(name.substring(0, 1).toUpperCase())
                    .append(name.substring(1))
                    .append(", ");
        }

        System.out.println(join);
        System.out.println(join.substring(0, join.length() - 2));
    }

    @Test
    public void joiningStringsWithStream() {
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        Function<String, String> mapTo = name -> name.substring(0, 1).toUpperCase() + name.substring(1);

        String join = names.stream()
                .map(mapTo)
                .collect(Collectors.joining("|"));
        System.out.println(join);

    }

}
```

</details>

---





<details>

<summary>

 >### _Comparing_

</summary>

```java
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
```

</details>

---





<details>

<summary>

 >### _Parallel Stream_

</summary>

```java
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
```

</details>

---





<details>

<summary>

 >### _Sorting_

</summary>

```java
import org.junit.jupiter.api.Test;
import me.bekzod.beans.Car;
import me.bekzod.beans.Person;
import me.bekzod.mockdata.MockData;

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
```

</details>

---





<details>

<summary>

 >### _Stream Statistics_

</summary>

```java
import org.junit.jupiter.api.Test;
import me.bekzod.beans.Car;
import me.bekzod.mockdata.MockData;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;

public class StatisticsWithStreamsTest {

    @Test
    public void count() throws Exception {
        List<Car> cars = MockData.getCars();
        Predicate<Car> fordCars = car -> car.getMake().equalsIgnoreCase("Ford");
        Predicate<Car> up2010 = car -> car.getYear() > 2010;
        long count = cars.stream()
                .filter(fordCars)
                .filter(up2010)
                .count();
        System.out.println(count);
    }

    @Test
    public void min() throws Exception {
        List<Car> cars = MockData.getCars();
        DoubleStream doubleStream = cars.stream()
                .mapToDouble(Car::getPrice);
        OptionalDouble min = doubleStream.min();
        double v = min.orElse(0.0);
        System.out.println(v);
    }

    @Test
    public void max() throws Exception {
        List<Car> cars = MockData.getCars();
        double max = cars.stream()
                .mapToDouble(Car::getPrice)
                .max()
                .orElse(0);
        System.out.println(max);
    }


    @Test
    public void average() throws Exception {
        List<Car> cars = MockData.getCars();
        double average = cars.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);
        System.out.println(average);
    }

    @Test
    public void sum() throws Exception {

        List<Car> cars = MockData.getCars();
        double sum = cars.stream()
                .mapToDouble(Car::getPrice)
                .sum();
        System.out.println(BigDecimal.valueOf(sum));
    }

    @Test
    public void statistics() throws Exception {
        List<Car> cars = MockData.getCars();
        DoubleSummaryStatistics statistics = cars.stream()
                .mapToDouble(Car::getPrice)
                .summaryStatistics();

        System.out.println(statistics.getCount());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getAverage());
        System.out.println(BigDecimal.valueOf(statistics.getSum()));
    }

}
```

</details>

---





<details>

<summary>

 >### _Mapping and Reduce_

</summary>

```java
import org.junit.jupiter.api.Test;
import me.bekzod.beans.Car;
import me.bekzod.beans.Person;
import me.bekzod.beans.PersonDto;
import me.bekzod.mockdata.MockData;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class TransformationsMapAndReduceTest {

    @Test
    void yourFirstTransformationWithMap() throws IOException {
        List<Person> people = MockData.getPeople();

        Function<Person, PersonDto> personPersonDTOFunction = person ->
                new PersonDto(
                        person.getId(),
                        person.getFirstName(),
                        person.getAge());

        Function<Person, PersonDto> mapMeToDTOPlease = PersonDto::mapMeToDTOPlease;

        Predicate<Person> ageFilter = person -> person.getAge() > 20;

        List<PersonDto> dtos = people.stream()
                .filter(ageFilter)
                .map(mapMeToDTOPlease)
                .peek(System.out::println)
                .toList();

        dtos.forEach(System.out::println);

    }

    @Test
    void mapToDoubleAndFindAverageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();
        double avg = cars.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);

        System.out.println(avg);
    }

    @Test
    public void reduce() {
        int[] integers = {1, 2, 3, 4, 99};
        int sum = Arrays.stream(integers).reduce(1, Integer::sum);

        System.out.println("dd");

        int sub = Arrays.stream(integers).reduce(3, (a, b) -> {
            System.out.println(a);
            return a - b;
        });
        System.out.println(sum);
        System.out.println(sub);
    }
}

```

</details>

---





<details>

<summary>

 >### _FlatMap_

</summary>

```java
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
```

</details>

---



