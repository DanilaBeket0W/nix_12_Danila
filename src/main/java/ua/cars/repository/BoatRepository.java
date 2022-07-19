package ua.cars.repository;

import ua.cars.entity.Boat;

import java.util.*;

public class BoatRepository implements BaseRepository<Boat> {
    private final List<Boat> boats;

    public BoatRepository() {
        boats = new LinkedList<>();
    }
    @Override
    public Optional<Boat> findById(String id) {
        for (Boat boat : boats) {
            if (boat.getId().equals(id)) {
                return Optional.of(boat);
            }
        }
        return Optional.empty();
    }
    @Override
    public List<Boat> getAll() {
        return boats;
    }

    @Override
    public Boat create(Boat boat) {
        if (boat == null) {
            throw new IllegalArgumentException("Boat can not be null");
        }
        boats.add(boat);
        return boat;
    }

    @Override
    public boolean update(String id, Boat kater) {
        final Optional<Boat> founded = findById(id).or(()->Optional.empty());
        if (founded.isPresent()) {
            founded.get().setModel(kater.getModel());
            founded.get().setManufacturer(kater.getManufacturer());
            founded.get().setPrice(kater.getPrice());
            founded.get().setCubicCapacity(kater.getCubicCapacity());
            delete(founded.get().getId());
            create(founded.get());
            return true;
        }
        return false;
    }

    @Override
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
