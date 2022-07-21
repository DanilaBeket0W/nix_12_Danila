package ua.cars;

import ua.cars.entity.Vehicle;
import ua.cars.repository.CarRepository;
import ua.cars.service.CarService;

import java.math.BigDecimal;

public class CarsMain {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
        UIrun.start();
    }
}
