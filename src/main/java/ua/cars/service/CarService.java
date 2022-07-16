package ua.cars.service;

import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.repository.CarRepository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarService {
    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);
    private final CarRepository carRepository;
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> createAndSaveAutos(int count) {//метод создан только в CarService исключительно для того чтобы использовать verify times, так так другого случая использования придумать не смог
        List<Car> result = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final Car auto = new Car(
                    "Model-" + RANDOM.nextInt(1000),
                    getRandomManufacturer(),
                    BigDecimal.valueOf(RANDOM.nextDouble(1000.0)),
                    "Model-" + RANDOM.nextInt(1000)
            );
            result.add(auto);
            carRepository.create(auto);
            LOGGER.debug("Created auto {}", auto.getId());
        }
        return result;
    }
    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public void create(Car car){
        carRepository.create(car);
        LOGGER.debug("Created auto {}", car.getId());
    }

    public void update(String id, String model, Manufacturer manufacturer, BigDecimal price, String bodyType){
        if (manufacturer == Manufacturer.NONE) {
            throw new IllegalArgumentException("Auto can not be null");
        }
        carRepository.update(id, model, manufacturer, price, bodyType);
    }
    public void delete(String id){
        carRepository.delete(id);
        LOGGER.debug("Deleted auto {}", id);
    }
    public Car findByid(String id){
        if(id == null){
            return carRepository.getById("");
        }else {
            return carRepository.getById(id);
        }
    }
    public List<Car> findALL(){
        return carRepository.getAll();
    }
}
