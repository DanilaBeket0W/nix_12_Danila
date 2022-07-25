package ua.cars;

import ua.cars.entity.vehicle.Car;

import java.util.Comparator;

public class MyComparatorByPrice implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        if (o1.getPrice().doubleValue() < o2.getPrice().doubleValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}

