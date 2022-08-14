package me.bekzod.examples;

import org.junit.jupiter.api.Test;
import me.bekzod.beans.Person;
import me.bekzod.mockdata.MockData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GettingStartedTest {

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
