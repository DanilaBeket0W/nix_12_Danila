package ua.cars.service;

import ua.cars.entity.vehicle.Car;
import ua.cars.repository.BaseRepository;


public class CarService extends BaseService<Car>{

    public CarService(BaseRepository<Car> repository){
        super(repository);
    }
}
