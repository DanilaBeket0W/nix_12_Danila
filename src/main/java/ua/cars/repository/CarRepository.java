package ua.cars.repository;

import ua.cars.entity.vehicle.Car;

import java.util.*;

public class CarRepository implements BaseRepository<Car>{
    private final List<Car> cars;

    public CarRepository() {
        cars = new LinkedList<>();
    }
    @Override
    public Optional<Car> findById(String id) {
        for (Car auto : cars) {
            if (auto.getId().equals(id)) {
                return Optional.of(auto);
            }
        }
        return Optional.empty();
    }
    @Override
    public List<Car> getAll() {
        return cars;
    }

    @Override
    public Car create(Car auto) {
        if (auto == null) {
            throw new IllegalArgumentException("Auto can not be null");
        }
        cars.add(auto);
        return auto;
    }
    @Override
    public boolean update(String id, Car car) {
      final Optional<Car> founded = findById(id).or(()->Optional.empty());
         if (founded.isPresent()) {
            founded.get().setModel(car.getModel());
            founded.get().setManufacturer(car.getManufacturer());
            founded.get().setPrice(car.getPrice());
            founded.get().setBodyType(car.getBodyType());
            delete(founded.get().getId());
            create(founded.get());
            return true;
        }
         return false;
    }
    @Override
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
}