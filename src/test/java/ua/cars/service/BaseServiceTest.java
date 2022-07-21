package ua.cars.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import ua.cars.entity.vehicle.Car;
import ua.cars.entity.vehicle.Manufacturer;
import ua.cars.repository.CarRepository;

import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BaseServiceTest {

    private BaseService<Car> target;
    private CarRepository repository;
    Car auto;
    private static Car createDefaultAuto() {
        return new Car("NONE", Manufacturer.NONE, BigDecimal.ZERO, "NONE",100);
    }

    @BeforeEach
    void setUp() {
        repository = mock(CarRepository.class);
        target = new CarService(repository);
        auto = createDefaultAuto();
    }

        @Test
    void create_checkArg() {
        final Car car = createDefaultAuto();
        when(repository.create(argThat(new ArgumentMatcher<Car>() {
            @Override
            public boolean matches(final Car auto) {
                return car.getModel().equals(auto.getModel());
            }
        }))).thenReturn(car);
        target.create(car);
        Mockito.verify(repository).create(car);
    }

    @Test
    void update() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->target.update("",auto));
    }

    @Test
    void delete_callMethod() {
        final Car defaultAuto = createDefaultAuto();
        target.delete(defaultAuto.getId());
        Mockito.verify(repository).delete(defaultAuto.getId());
    }

    @Test
    void delete_failed() {
        Mockito.when(repository.delete(anyString())).thenThrow(
                new IllegalArgumentException("Cannot find motorbike with this id"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> target.delete(anyString()));
    }

    @Test
    void findById() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        target.findById("");
        Mockito.verify(repository).findById(captor.capture());
        Assertions.assertEquals("",captor.getValue());
    }
}