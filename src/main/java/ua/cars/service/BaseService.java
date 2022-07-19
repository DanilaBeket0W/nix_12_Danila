package ua.cars.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cars.entity.Manufacturer;
import ua.cars.entity.Vehicle;
import ua.cars.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends Vehicle> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    protected final BaseRepository<T> repository;

    protected BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public void create(T transport){
        final T added = repository.create(transport);
        System.out.println();
        LOGGER.debug("Created transport {}", added.getId());
        if(added.getManufacturer() != Manufacturer.NONE) {
            System.out.println("Your transport creaed\uD83D\uDC4D");
        }else {
            System.out.println("Your transport is invalid\uD83D\uDC4E");
        }
    }

    public void update(String id, T transport){
        if (transport.getManufacturer() == Manufacturer.NONE) {
            throw new IllegalArgumentException("Transport can not be null");
        }
        repository.update(id, transport);
    }

    public boolean delete(String id){
        repository.delete(id);
        LOGGER.debug("Created transport {}", id);
        return false;
    }

    public Optional<T> findById(String id){
        Optional<T> actual = repository.findById(id);
        try {
            return actual.map(auto -> {
                Optional<T> boat = actual;
                return boat;
            }).orElseThrow(() -> new IllegalArgumentException("Cannot find transport with id " + id));
        }catch (Exception e){
            LOGGER.debug("transport not found {}", id);
        }
        return Optional.empty();
    }

    public List<T> findALL(){
        return repository.getAll();
    }
}
