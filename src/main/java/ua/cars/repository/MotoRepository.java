package ua.cars.repository;

import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.entity.Moto;

import java.math.BigDecimal;
import java.util.*;

public class MotoRepository {
    private final List<Moto> motos;

    public MotoRepository() {
        motos = new LinkedList<>();
    }

    public Optional<Moto> findById(String id) {
        for (Moto atv : motos) {
            if (atv.getId().equals(id)) {
                return Optional.of(atv);
            }
        }
        return Optional.empty();
    }

    public Optional<Moto> findByModel (String model){
        for (Moto atv : motos) {
            if (atv.getModel().equals(model)) {
                return Optional.of(atv);
            }
        }
        return Optional.empty();
    }

    public List<Moto> getAll() {
        return motos;
    }


    public Optional<Moto> create(Moto atv) {
        if (atv == null) {
            throw new IllegalArgumentException("ATV can not be null");
        }
        motos.add(atv);
        return Optional.of(atv);
    }


    public boolean update(String id, String model, Manufacturer manufacturer, BigDecimal price, int sitsNumber) {
        final Optional<Moto> founded = findById(id).or(()->Optional.empty());
        if (founded.isPresent()) {
            founded.get().setModel(model);
            founded.get().setManufacturer(manufacturer);
            founded.get().setPrice(price);
            founded.get().setSitsNumber(sitsNumber);
            delete(founded.get().getId());
            create(founded.get());
            return true;
        }
        return false;
    }


    public boolean delete(String id) {
        final Iterator<Moto> iterator = motos.iterator();
        while (iterator.hasNext()) {
            final Moto atv = iterator.next();
            if (atv.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<String> findAllBySpecificManufacturer(){
        List<String> specificMotoID = new ArrayList<>();
        for (Moto atv : motos) {
            specificMotoID.add(atv.getId());
        }
        return specificMotoID;
    }

}
