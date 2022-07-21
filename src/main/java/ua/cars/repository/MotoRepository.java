package ua.cars.repository;

import ua.cars.entity.Moto;

import java.util.*;

public class MotoRepository implements BaseRepository<Moto>{
    private final List<Moto> motos;

    public MotoRepository() {
        motos = new LinkedList<>();
    }
    @Override
    public Optional<Moto> findById(String id) {
        for (Moto atv : motos) {
            if (atv.getId().equals(id)) {
                return Optional.of(atv);
            }
        }
        return Optional.empty();
    }
    @Override
    public List<Moto> getAll() {
        return motos;
    }

    @Override
    public Moto create(Moto atv) {
        if (atv == null) {
            throw new IllegalArgumentException("ATV can not be null");
        }
        motos.add(atv);
        return atv;
    }

    @Override
    public boolean update(String id, Moto atv) {
        final Optional<Moto> founded = findById(id).or(()->Optional.empty());
        if (founded.isPresent()) {
            founded.get().setModel(atv.getModel());
            founded.get().setManufacturer(atv.getManufacturer());
            founded.get().setPrice(atv.getPrice());
            founded.get().setSitsNumber(atv.getSitsNumber());
            delete(founded.get().getId());
            create(founded.get());
            return true;
        }
        return false;
    }

    @Override
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
