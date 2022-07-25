package ua.cars;

import ua.cars.entity.vehicle.Car;

import java.util.Comparator;

public class MyComparatorByCount implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        if (o1.getCount() > o2.getCount()) {
            return 1;
        } else {
            return -1;
        }
    }
}

