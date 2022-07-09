package ua.cars.service;

import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.repository.CarRepository;

import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);
    private final CarRepository carRepository = new CarRepository();

    public void create(Car car){
        carRepository.create(car);
        LOGGER.debug("Created auto {}", car.getId());
    }

    public void update(String id, String model, Manufacturer manufacturer, BigDecimal price, String bodyType){
        carRepository.update(id, model, manufacturer, price, bodyType);
    }
    public void delete(String id){
        carRepository.delete(id);
        LOGGER.debug("Deleted auto {}", id);
    }
    public Car findByid(String id){
        return carRepository.getById(id);
    }
    public List<Car> findALL(){
        return carRepository.getAll();
    }
}
