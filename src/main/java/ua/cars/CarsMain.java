package ua.cars;

import ua.cars.repository.CarRepository;
import ua.cars.service.CarService;

public class CarsMain {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
        carService.createAndSaveAutos(10);
        UIrun.start();
    }
}
