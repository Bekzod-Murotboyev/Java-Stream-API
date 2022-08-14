package uz.jl.examples;


import org.junit.jupiter.api.Test;
import uz.jl.beans.Person;
import uz.jl.beans.PersonDto;
import uz.jl.mockdata.MockData;

import java.util.List;

public class HowStreamsWorkTest {
    @Test
    public void understandingCollect() throws Exception {
        List<PersonDto> emails = MockData.getPeople()
                .stream()
                .map(Person::mapmeplease)
                .toList();

        emails.forEach(System.out::println);
    }

    @Test
    public void intermediateAndTerminalOperations() throws Exception {
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
                        .toList());
    }
}
