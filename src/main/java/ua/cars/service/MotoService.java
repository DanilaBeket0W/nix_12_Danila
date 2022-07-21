package ua.cars.service;


import ua.cars.entity.Moto;
import ua.cars.repository.BaseRepository;


public class MotoService extends BaseService<Moto> {
    public MotoService(BaseRepository<Moto> repository){
        super(repository);
    }

}
