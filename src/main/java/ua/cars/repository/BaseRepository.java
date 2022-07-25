package ua.cars.repository;

import ua.cars.entity.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends Vehicle> {
    Optional<T> findById(String id);
    List<T> getAll();

    T create(T auto);

    boolean update(String id, T vehicle);

    boolean delete(String id);
}
