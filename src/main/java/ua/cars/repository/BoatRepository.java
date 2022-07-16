package ua.cars.repository;

import ua.cars.entity.Boat;
import ua.cars.entity.Manufacturer;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BoatRepository {
    private final List<Boat> boats;

    public BoatRepository() {
        boats = new LinkedList<>();
    }

    public Boat getById(String id) {
        for (Boat boat : boats) {
            if (boat.getId().equals(id)) {
                return boat;
            }
        }
        return null;
    }


    public List<Boat> getAll() {
        return boats;
    }


    public boolean create(Boat boat) {
        if (boat == null) {
            throw new IllegalArgumentException("Boat can not be null");
        }
        boats.add(boat);
        return true;
    }


    public boolean update(String id, String model, Manufacturer manufacturer, BigDecimal price, BigDecimal cubicCapacity) {
        final Boat founded = getById(id);
        if (founded != null) {
            founded.setModel(model);
            founded.setManufacturer(manufacturer);
            founded.setPrice(price);
            founded.setCubicCapacity(cubicCapacity);
            delete(founded.getId());
            create(founded);
            return true;
        }
        return false;
    }


    public boolean delete(String id) {
        final Iterator<Boat> iterator = boats.iterator();
        while (iterator.hasNext()) {
            final Boat boat = iterator.next();
            if (boat.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
