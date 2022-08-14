package me.bekzod.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Person implements Comparable<Person> {

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String gender;
    private final Integer age;

    @Override
    public int compareTo(Person o) {
        int i = this.getAge().compareTo(o.getAge());
        if (i == 0)
            return this.getFirstName().compareTo(o.getFirstName());
        return i;
    }

    public static PersonDto mapmeplease(Person person) {
        return new PersonDto(
                person.getId(),
                person.getFirstName(),
                person.getAge()
        );
    }
}