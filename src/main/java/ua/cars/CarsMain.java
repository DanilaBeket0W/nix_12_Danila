package ua.cars;

import ua.cars.entity.TransportGarage;
import ua.cars.entity.vehicle.Car;
import ua.cars.entity.vehicle.Manufacturer;
import ua.cars.entity.vehicle.Vehicle;
import ua.cars.repository.CarRepository;
import ua.cars.service.CarService;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CarsMain {
    public static void main(String[] args) throws InterruptedException {
        final CarService carService = new CarService(new CarRepository());
        //UIrun.start();

        //TEST MY LINKED-LIST
        Car car = new Car("some", Manufacturer.BMW, BigDecimal.valueOf(10000), "some", 10);
        Car car1 = new Car("first", Manufacturer.BMW, BigDecimal.valueOf(15000), "first", 1000);
        Car car2 = new Car("second", Manufacturer.BMW, BigDecimal.valueOf(13000), "second", 100);
        Car car3 = new Car("third", Manufacturer.BMW, BigDecimal.valueOf(13000), "third", 1);
        TransportGarage<Car> myLinkedList = new TransportGarage<Car>();
        myLinkedList.add(car);
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        myLinkedList.add(car1);
        System.out.println(myLinkedList.get(0));
        System.out.println(myLinkedList.get(1));
        System.out.println(myLinkedList.getTimeFirstCarADD());
        System.out.println(myLinkedList.getTimeLastCarADD());
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        myLinkedList.add(car2);
        System.out.println(myLinkedList.getTimeFirstCarADD());
        System.out.println(myLinkedList.getTimeLastCarADD());
        myLinkedList.forEach((temp) -> {
            System.out.println(temp);
        });
        myLinkedList.set(0, new Car("SOME", Manufacturer.BMW, BigDecimal.valueOf(10000), "SOME", 100));
        System.out.println(myLinkedList.getRestNumber());
        myLinkedList.remove(2);
        myLinkedList.forEach((temp) -> {
            System.out.println(temp);
        });

        //TEST MY COMPARATORS
        Comparator<Car> myComparator = new MyComparatorByPrice().thenComparing(new MyComparatorByAlphabet().thenComparing(new MyComparatorByCount()));
        TreeSet<Car> carsList = new TreeSet(myComparator);
        carsList.add(car);
        carsList.add(car1);
        carsList.add(car2);
        carsList.add(car3);
        carsList.forEach((c) -> {
            System.out.println(c);
        });
    }
}

class MyComparatorByPrice implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        if (o1.getPrice().doubleValue() < o2.getPrice().doubleValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}

class MyComparatorByAlphabet implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getModel().compareTo(o2.getModel());
    }
}

class MyComparatorByCount implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        if (o1.getCount() > o2.getCount()) {
            return 1;
        } else {
            return -1;
        }
    }
}

