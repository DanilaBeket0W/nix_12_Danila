package ua.cars.service;

import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.repository.CarRepository;

import java.math.BigDecimal;
import java.util.*;

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
        final Car added = carRepository.create(car).orElse(createDefaultAuto());
        System.out.println();
        LOGGER.debug("Created auto {}", added.getId());
        if(added.getManufacturer() != Manufacturer.NONE) {
            System.out.println("Your car creaed\uD83D\uDC4D");
        }else {
            System.out.println("Your car is invalid\uD83D\uDC4E");
        }
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

public Optional<Car> findById(String id){
    Optional<Car> actual = carRepository.findById(id);
    try {
        return actual.map(auto -> {
            Optional<Car> car = actual;
            return car;
        }).orElseThrow(() -> new IllegalArgumentException("Cannot find auto with id " + id));
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
    return Optional.empty();
}

    public void findByModel(String model) {
        if (!Objects.equals(model, "")){
            carRepository.findByModel(model).ifPresentOrElse(
                auto -> {
                    System.out.println(auto.getModel());
                },
                () -> {
                    System.out.println("Cannot find auto " + model);
                }
            );
        }else{
            System.out.println("Autos id can`t be empty");
        }
    }

    public List<Car> findALL(){
        return carRepository.getAll();
    }

    public List<Car> findAllBySpecificManufacturer(Manufacturer manufacturer){
        List<String> specificCarsID = carRepository.findAllBySpecificManufacturer();
        List<Car>specificCars = new ArrayList<>();
        if (specificCarsID.size()>0) {
            for (String ID : specificCarsID) {
                carRepository.findById(ID)
                        .filter(auto -> auto.getManufacturer().equals(manufacturer))
                        .ifPresent(auto -> {
                            specificCars.add(auto);
                        });
            }
        }
        return specificCars;
    }

    private Car createDefaultAuto() {
        return new Car("NONE", Manufacturer.NONE, BigDecimal.ZERO,"NONE");
    }
}
