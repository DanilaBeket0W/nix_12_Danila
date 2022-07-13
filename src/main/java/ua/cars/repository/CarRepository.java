package ua.cars.repository;

import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CarRepository {
    private final List<Car> cars;

    public CarRepository() {
        cars = new LinkedList<>();
    }


    public Car getById(String id) {
        for (Car auto : cars) {
            if (auto.getId().equals(id)) {
                return auto;
            }
        }
        return null;
    }


    public List<Car> getAll() {
        return cars;
    }


    public boolean create(Car auto) {
        if (auto == null) {
            throw new IllegalArgumentException("Auto can not be null");
        }
        cars.add(auto);
        return true;
    }

//    @Override
//    public boolean create(List<Car> auto) {  //не нашлось применения :)
//        return false;
//    }


public boolean update(String id, String model, Manufacturer manufacturer, BigDecimal price, String bodyType) {
    final Car founded = getById(id);
    if (founded != null) {
        founded.setModel(model);
        founded.setManufacturer(manufacturer);
        founded.setPrice(price);
        founded.setBodyType(bodyType);
        delete(founded.getId());
        create(founded);
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

}