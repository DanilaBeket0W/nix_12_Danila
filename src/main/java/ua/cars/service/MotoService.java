package ua.cars.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.cars.entity.Manufacturer;
import ua.cars.entity.Moto;
import ua.cars.repository.CarRepository;
import ua.cars.repository.MotoRepository;

import java.math.BigDecimal;
import java.util.List;

public class MotoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);
    private final MotoRepository motoRepository;
    public MotoService(MotoRepository carRepository){
        this.motoRepository = carRepository;
    }

    public void create(Moto moto){
        motoRepository.create(moto);
        LOGGER.debug("Created moto {}", moto.getId());
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
    public Moto findByid(String id){
        if(id == null){
            return motoRepository.getById("");
        }else {
            return motoRepository.getById(id);
        }
    }
    public List<Moto> findALL(){
        return motoRepository.getAll();
    }
}
