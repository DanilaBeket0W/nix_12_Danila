package ua.cars.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;

import java.math.BigDecimal;
import java.util.List;


class CarRepositoryTest {

    private CarRepository target;
    private Car car;

    private Car createDefaultAuto() {
        return new Car("Model", Manufacturer.HONDA, BigDecimal.ZERO,"Type");
    }

    @BeforeEach
    void setUp() {
        target = new CarRepository();
        car = createDefaultAuto();
        target.create(car);
    }

    @Test
    void getById_findOne() {
        final Car actual = target.getById(car.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(car.getId(),actual.getId());
    }

    @Test
    void getById_notFound() {
        final Car actual = target.getById("someId");
        Assertions.assertNull(actual);
    }

    @Test
    void getAll() {
        final List<Car> actual = target.getAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1,actual.size());
    }

    @Test
    void create_failSave() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->target.create(null));
    }

    @Test
    void create_successSave() {
        final boolean actual = target.create(car);
        Assertions.assertTrue(actual);
    }

    @Test
    void update_notFoundAuto() {
        final Car someAuto = createDefaultAuto();
        final boolean actual = target.update(someAuto.getId(),someAuto.getModel(),someAuto.getManufacturer(),someAuto.getPrice(),someAuto.getBodyType());
        Assertions.assertFalse(actual);
    }

    @Test
    void update_succesfullUpdate() {
        car.setPrice(BigDecimal.TEN);
        final  boolean actual = target.update(car.getId(),car.getModel(),car.getManufacturer(),car.getPrice(),car.getBodyType());
        Assertions.assertTrue(actual);
        final Car actualAuto = target.getById(car.getId());
        Assertions.assertEquals(BigDecimal.TEN,actualAuto.getPrice());
    }

    @Test
    void delete_notFound() {
        final boolean actual = target.delete("someId");
        Assertions.assertFalse(actual);
    }
    @Test
    void delete_failureDelete() {
      target.delete(car.getId());
      Assertions.assertEquals(0,target.getAll().size());
    }

}