package ua.cars.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cars.entity.Boat;
import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.entity.Moto;
import ua.cars.repository.CarRepository;
import ua.cars.repository.MotoRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MotoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);
    private final MotoRepository motoRepository;
    public MotoService(MotoRepository carRepository){
        this.motoRepository = carRepository;
    }

    public void create(Moto moto){
        final Moto added = motoRepository.create(moto).orElse(createDefaultMoto());
        System.out.println();
        LOGGER.debug("Created atv {}", added.getId());
        if(added.getManufacturer() != Manufacturer.NONE) {
            System.out.println("Your atv creaed\uD83D\uDC4D");
        }else {
            System.out.println("Your atv is invalid\uD83D\uDC4E");
        }
    }

    public void update(String id, String model, Manufacturer manufacturer, BigDecimal price, int sitsNumber){
        if (manufacturer == Manufacturer.NONE) {
            throw new IllegalArgumentException("Auto can not be null");
        }
        motoRepository.update(id, model, manufacturer, price, sitsNumber);
    }
    public void delete(String id){
        motoRepository.delete(id);
        LOGGER.debug("Created moto {}", id);
    }
    public Optional<Moto> findById(String id){
        Optional<Moto> actual = motoRepository.findById(id);
        try {
            return actual.map(atv -> {
                Optional<Moto> moto = actual;
                return moto;
            }).orElseThrow(() -> new IllegalArgumentException("Cannot find moto with id " + id));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public void findByModel(String model) {
        if (!Objects.equals(model, "")) {
            motoRepository.findByModel(model).ifPresentOrElse(
                    atv -> {
                        System.out.println(atv.getModel());
                    },
                    () -> {
                        System.out.println("Cannot find atv " + model);
                    }
            );
        }else{
            System.out.println("Motos id can`t be empty");
        }
    }
    public List<Moto> findALL(){
        return motoRepository.getAll();
    }

    public List<Moto> findAllBySpecificManufacturer(Manufacturer manufacturer){
        List<String> specificMotoID = motoRepository.findAllBySpecificManufacturer();
        List<Moto>specificMoto = new ArrayList<>();
        if (specificMotoID.size()>0) {
            for (String ID : specificMotoID) {
                motoRepository.findById(ID)
                        .filter(atv -> atv.getManufacturer().equals(manufacturer))
                        .ifPresent(atv -> {
                            specificMoto.add(atv);
                        });
            }
        }
        return specificMoto;
    }

    private Moto createDefaultMoto() {
        return new Moto("NONE", Manufacturer.NONE, BigDecimal.ZERO,0);
    }
}
