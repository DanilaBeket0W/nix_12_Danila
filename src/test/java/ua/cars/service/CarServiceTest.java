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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarServiceTest {

    private CarService target;
    private CarRepository carRepository;
    private static Car createDefaultAuto() {
        return new Car("NONE", Manufacturer.NONE, BigDecimal.ZERO, "NONE");
    }

    @BeforeEach
    void setUp() {
        carRepository = mock(CarRepository.class);
        target = new CarService(carRepository);
    }

    @Test
    void createAndSaveAutos() {
        final List<Car> actual = target.createAndSaveAutos(5);
        Assertions.assertEquals(5, actual.size());
        Mockito.verify(carRepository, Mockito.times(5))
                .create(Mockito.any());
    }

    @Test
    void create() {
        final Car defaultAuto = createDefaultAuto();
        target.create(defaultAuto);
        Mockito.verify(carRepository).create(defaultAuto);
    }

    @Test
    void create_checkArg() {
        final Car car = createDefaultAuto();

        when(carRepository.create(argThat(new ArgumentMatcher<Car>() {
            @Override
            public boolean matches(final Car auto) {
                return car.getModel().equals(auto.getModel());
            }
        }))).thenReturn(Optional.of(car));
        target.create(car);
        Mockito.verify(carRepository).create(car);
    }

    @Test
    void update() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->target.update("","",Manufacturer.NONE,BigDecimal.ZERO,""));
    }

    @Test
    void delete() {
        final Car defaultAuto = createDefaultAuto();
        target.delete(defaultAuto.getId());
        Mockito.verify(carRepository).delete(defaultAuto.getId());
    }

    @Test
    void findById() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        target.findById("");
        Mockito.verify(carRepository).findById(captor.capture());
        Assertions.assertEquals("",captor.getValue());
    }

    @Test
    void findByModel() {
        CarService repMock = mock(CarService.class);
        Mockito.doThrow(new IllegalArgumentException("Error occurred"))
                .when(repMock)
                .findByModel("");
        repMock.findByModel("");
    }

    @Test
    void findALL() {
        List<Car> cars = List.of(createDefaultAuto(),createDefaultAuto());
        Mockito.when(carRepository.getAll()).thenReturn(cars);
        target.findALL();
    }

    @Test
    void findAllBySpecificManufacturer_getNoneManufacturer() {
        CarService repMock = mock(CarService.class);
        Mockito.doThrow(new IllegalArgumentException("Invalid Manufacturer"))
                .when(repMock)
                .findAllBySpecificManufacturer(Manufacturer.NONE);
        repMock.findAllBySpecificManufacturer(Manufacturer.NONE);
    }

    @Test
    void findAllBySpecificManufacturer_callMethod() {
        final Car defaultAuto = createDefaultAuto();
        target.findAllBySpecificManufacturer(defaultAuto.getManufacturer());
        Mockito.verify(carRepository).findAllBySpecificManufacturer();
    }
}

