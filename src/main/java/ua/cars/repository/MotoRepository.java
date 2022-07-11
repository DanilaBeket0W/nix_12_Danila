package ua.cars.repository;

import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.entity.Moto;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MotoRepository {
    private final List<Moto> Motos;

    public MotoRepository() {
        Motos = new LinkedList<>();
    }

    public Moto getById(String id) {
        for (Moto atv : Motos) {
            if (atv.getId().equals(id)) {
                return atv;
            }
        }
        return null;
    }


    public List<Moto> getAll() {
        return Motos;
    }


    public boolean create(Moto atv) {
        Motos.add(atv);
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
        final Iterator<Moto> iterator = Motos.iterator();
        while (iterator.hasNext()) {
            final Moto atv = iterator.next();
            if (atv.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    private static void copy(final Moto from, final Moto to) {
        to.setManufacturer(from.getManufacturer());
        to.setModel(from.getModel());
        to.setSitsNumber(from.getSitsNumber());
        to.setPrice(from.getPrice());
    }

}
