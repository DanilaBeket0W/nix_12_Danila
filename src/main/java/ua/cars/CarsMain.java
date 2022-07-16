package ua.cars;

import ua.cars.repository.CarRepository;
import ua.cars.service.CarService;

public class CarsMain {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
        carService.createAndSaveAutos(10);
        UIrun.start();


//        ifPresent используеться в методе update класа UIrun
//        map и orElseThrow используються в методе findById класов сервисов
//        orElse используеться в методе create класов сервисов
//        or используються в методе update класов репазиториев
//        orElseGet используються в методе findById класа UIrun
//        filter используеться в методах findAllBySpecificManufacturer класов сервисов
//        ifPresentOrElse используеться в методах findByModel класов сервисов
    }
}
