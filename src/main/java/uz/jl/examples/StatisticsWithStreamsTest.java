package uz.jl.examples;


import org.junit.jupiter.api.Test;
import uz.jl.beans.Car;
import uz.jl.mockdata.MockData;

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