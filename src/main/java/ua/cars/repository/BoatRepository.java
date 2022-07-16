package ua.cars.repository;

import ua.cars.entity.Boat;
import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;

import java.math.BigDecimal;
import java.util.*;

public class BoatRepository {
    private final List<Boat> boats;

    public BoatRepository() {
        boats = new LinkedList<>();
    }

    public Optional<Boat> findById(String id) {
        for (Boat boat : boats) {
            if (boat.getId().equals(id)) {
                return Optional.of(boat);
            }
        }
        return Optional.empty();
    }

    public Optional<Boat> findByModel (String model){
        for (Boat boat : boats) {
            if (boat.getModel().equals(model)) {
                return Optional.of(boat);
            }
        }
        return Optional.empty();
    }

    public List<Boat> getAll() {
        return boats;
    }


    public Optional<Boat> create(Boat boat) {
        if (boat == null) {
            throw new IllegalArgumentException("Boat can not be null");
        }
        boats.add(boat);
        return Optional.of(boat);
    }


    public boolean update(String id, String model, Manufacturer manufacturer, BigDecimal price, BigDecimal cubicCapacity) {
        final Optional<Boat> founded = findById(id).or(()->Optional.empty());
        if (founded.isPresent()) {
            founded.get().setModel(model);
            founded.get().setManufacturer(manufacturer);
            founded.get().setPrice(price);
            founded.get().setCubicCapacity(cubicCapacity);
            delete(founded.get().getId());
            create(founded.get());
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

    public List<String> findAllBySpecificManufacturer(){
        List<String> specificBoatsID = new ArrayList<>();
        for (Boat boat : boats) {
            specificBoatsID.add(boat.getId());
        }
        return specificBoatsID;
    }
}
