package ua.cars.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cars.entity.Boat;
import ua.cars.entity.Manufacturer;
import ua.cars.repository.BoatRepository;
import ua.cars.repository.CarRepository;


import java.math.BigDecimal;
import java.util.List;

public class BoatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);
    private final BoatRepository boatRepository;
    public BoatService(BoatRepository boatRepository){
        this.boatRepository = boatRepository;
    }

    public void create(Boat boat){
        boatRepository.create(boat);
        LOGGER.debug("Created boat {}", boat.getId());
    }

    public void update(String id, String model, Manufacturer manufacturer, BigDecimal price, BigDecimal cubicCapacity){
        if (manufacturer == Manufacturer.NONE) {
            throw new IllegalArgumentException("Auto can not be null");
        }
        boatRepository.update(id, model, manufacturer, price, cubicCapacity);
    }
    public void delete(String id){
        boatRepository.delete(id);
        LOGGER.debug("Created boat {}", id);
    }
    public Boat findByid(String id){
        if(id == null){
            return boatRepository.getById("");
        }else {
            return boatRepository.getById(id);
        }
    }
    public List<Boat> findALL(){
        return boatRepository.getAll();
    }
}
