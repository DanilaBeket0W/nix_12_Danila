package ua.cars.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.repository.CarRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class CarServiceTest {

    private Car createDefaultAuto() {
        return new Car("Model", Manufacturer.HONDA, BigDecimal.ZERO,"Type");
    }
    private CarService target;
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = mock(CarRepository.class);
        target = new CarService(carRepository);
    }

    @Test
    void create() {
        target.create(createDefaultAuto());
        Mockito.verify(carRepository).create(Mockito.any());
    }

    @Test
    void create_checkArg() {
        final CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.create(argThat(new ArgumentMatcher<Car>() {
            @Override
            public boolean matches(final Car car) {
                assertEquals("someModel",((Car)car).getModel());
                return true;
            }
        }))).thenReturn(Boolean.TRUE);
        final Car car = createDefaultAuto();
        car.setModel("someModel");
        carRepository.create(car);
    }

    @Test
    void createAndSaveAutos() {
        final List<Car> actual = target.createAndSaveAutos(5);
        Assertions.assertEquals(5, actual.size());
        Mockito.verify(carRepository, Mockito.times(5))
                .create(Mockito.any());
    }

    @Test
    void update() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->target.update("","",Manufacturer.NONE,BigDecimal.ZERO,""));
    }

    @Test
    void delete() {
        CarRepository repMock = mock(CarRepository.class);
        try{Mockito.doThrow(new IllegalArgumentException("Error occurred"))
                .when(repMock)
                .delete("");
        repMock.delete("");}catch (Exception e){
            System.out.println("id can not be empty");
        }
    }

    @Test
    void findByid_getNull() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        target.findByid(null);
        Mockito.verify(carRepository).getById(captor.capture());
        Assertions.assertEquals("",captor.getValue());
    }

    @Test
    void findALL() {
        List<Car> cars = List.of(createDefaultAuto(),createDefaultAuto());
        Mockito.when(carRepository.getAll()).thenReturn(cars);
        target.findALL();
    }
}