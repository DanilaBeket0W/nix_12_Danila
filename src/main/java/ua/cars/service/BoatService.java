package ua.cars.service;


import ua.cars.entity.vehicle.Boat;
import ua.cars.repository.BaseRepository;

public class BoatService extends BaseService<Boat> {
    public BoatService(BaseRepository<Boat> repository){
        super(repository);
    }


}
