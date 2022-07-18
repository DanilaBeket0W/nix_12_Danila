package ua.cars.repository;

import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;

import java.math.BigDecimal;
import java.util.*;

public class CarRepository {
    private final List<Car> cars;

    public CarRepository() {
        cars = new LinkedList<>();
    }

    public Optional<Car> findById(String id) {
        for (Car auto : cars) {
            if (auto.getId().equals(id)) {
                return Optional.of(auto);
            }
        }
        return Optional.empty();
    }

    public Optional<Car> findByModel (String model){
        for (Car auto : cars) {
            if (auto.getModel().equals(model)) {
                return Optional.of(auto);
            }
        }
        return Optional.empty();
    }

    public List<Car> getAll() {
        return cars;
    }


    public Optional<Car> create(Car auto) {
        if (auto == null) {
            throw new IllegalArgumentException("Auto can not be null");
        }
        cars.add(auto);
        return Optional.of(auto);
    }

    public boolean update(String id, String model, Manufacturer manufacturer, BigDecimal price, String bodyType) {
      final Optional<Car> founded = findById(id).or(()->Optional.empty());
         if (founded.isPresent()) {
            founded.get().setModel(model);
            founded.get().setManufacturer(manufacturer);
            founded.get().setPrice(price);
            founded.get().setBodyType(bodyType);
            delete(founded.get().getId());
            create(founded.get());
            return true;
        }
         return false;
    }

    public boolean delete(String id) {
        final Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            final Car auto = iterator.next();
            if (auto.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<String> findAllBySpecificManufacturer(){
        List<String> specificCarsID = new ArrayList<>();
        for (Car auto : cars) {
            specificCarsID.add(auto.getId());
        }
        return specificCarsID;
    }

}