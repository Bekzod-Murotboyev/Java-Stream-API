package uz.jl.beans;

public class PersonDto {

    private final Integer id;
    private final String name;
    private final Integer age;

    public PersonDto(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }


    @Override
    public String toString() {
        return "PeopleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static PersonDto map(Person person) {
        return new PersonDto(
                person.getId(),
                person.getFirstName(),
                person.getAge());
    }

    public static PersonDto mapMeToDTOPlease(Person person) {
        return new PersonDto(
                person.getId(),
                person.getFirstName(),
                person.getAge());
    }
}