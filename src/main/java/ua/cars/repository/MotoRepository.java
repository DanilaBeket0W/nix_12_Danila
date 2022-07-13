package ua.cars.repository;

import ua.cars.entity.Manufacturer;
import ua.cars.entity.Moto;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MotoRepository {
    private final List<Moto> motos;

    public MotoRepository() {
        motos = new LinkedList<>();
    }

    public Moto getById(String id) {
        for (Moto atv : motos) {
            if (atv.getId().equals(id)) {
                return atv;
            }
        }
        return null;
    }


    public List<Moto> getAll() {
        return motos;
    }


    public boolean create(Moto atv) {
        if (atv == null) {
            throw new IllegalArgumentException("ATV can not be null");
        }
        motos.add(atv);
        return true;
    }


    public boolean update(String id, String model, Manufacturer manufacturer, BigDecimal price, int sitsNumber) {
        final Moto founded = getById(id);
        if (founded != null) {
            founded.setModel(model);
            founded.setManufacturer(manufacturer);
            founded.setPrice(price);
            founded.setSitsNumber(sitsNumber);
            delete(founded.getId());
            create(founded);
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

}
