package uz.jl.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
//@EqualsAndHashCode(exclude = {"id"})
@EqualsAndHashCode(of = {"year"})
public class Car {

    private final Integer id;

    private final String make;

    private final String model;

    private final String color;

    private final Integer year;

    private final Double price;

}
