package ua.cars.repository;

import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CarRepository {
    private final List<Car> Cars;

    public CarRepository() {
        Cars = new LinkedList<>();
    }


    public Car getById(String id) {
        for (Car auto : Cars) {
            if (auto.getId().equals(id)) {
                return auto;
            }
        }
        return null;
    }


    public List<Car> getAll() {
        return Cars;
    }


    public boolean create(Car auto) {
        Cars.add(auto);
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
        final Iterator<Car> iterator = Cars.iterator();
        while (iterator.hasNext()) {
            final Car auto = iterator.next();
            if (auto.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    private static void copy(final Car from, final Car to) {
        to.setManufacturer(from.getManufacturer());
        to.setModel(from.getModel());
        to.setBodyType(from.getBodyType());
        to.setPrice(from.getPrice());
    }

}