package ua.cars.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cars.entity.Boat;
import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.repository.BoatRepository;
import ua.cars.repository.CarRepository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BoatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);
    private final BoatRepository boatRepository;
    public BoatService(BoatRepository boatRepository){
        this.boatRepository = boatRepository;
    }

    public void create(Boat boat){
        final Boat added = boatRepository.create(boat).orElse(createDefaultBoat());
        System.out.println();
        LOGGER.debug("Created boat {}", added.getId());
        if(added.getManufacturer() != Manufacturer.NONE) {
            System.out.println("Your boat creaed\uD83D\uDC4D");
        }else {
            System.out.println("Your boat is invalid\uD83D\uDC4E");
        }
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

    public Optional<Boat> findById(String id){
        Optional<Boat> actual = boatRepository.findById(id);
        try {
            return actual.map(auto -> {
                Optional<Boat> boat = actual;
                return boat;
            }).orElseThrow(() -> new IllegalArgumentException("Cannot find boat with id " + id));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public void findByModel(String model) {
        if (!Objects.equals(model, "")) {
            boatRepository.findByModel(model).ifPresentOrElse(
                    boat -> {
                        System.out.println(boat.getModel());
                    },
                    () -> {
                        System.out.println("Cannot find boat " + model);
                    }
            );
        }else{
            System.out.println("Boats id can`t be empty");
        }
    }

    public List<Boat> findALL(){
        return boatRepository.getAll();
    }

    public List<Boat> findAllBySpecificManufacturer(Manufacturer manufacturer){
        List<String> specificBoatsID = boatRepository.findAllBySpecificManufacturer();
        List<Boat>specificBoats = new ArrayList<>();
        if (specificBoatsID.size()>0) {
            for (String ID : specificBoatsID) {
                boatRepository.findById(ID)
                        .filter(boat -> boat.getManufacturer().equals(manufacturer))
                        .ifPresent(boat -> {
                            specificBoats.add(boat);
                        });
            }
        }
        return specificBoats;
    }

    private Boat createDefaultBoat() {
        return new Boat("NONE", Manufacturer.NONE, BigDecimal.ZERO,BigDecimal.valueOf(1000));
    }
}
