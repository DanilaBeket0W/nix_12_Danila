package ua.cars.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.cars.entity.Boat;
import ua.cars.entity.Manufacturer;

import java.math.BigDecimal;
import java.util.List;


class BoatRepositoryTest {
    private BoatRepository target;
    private Boat boat;

    private Boat createDefaultBoat() {
        return new Boat("Model", Manufacturer.HONDA, BigDecimal.ZERO,BigDecimal.valueOf(1000));
    }

    @BeforeEach
    void setUp() {
        target = new BoatRepository();
        boat = createDefaultBoat();
        target.create(boat);
    }

    @Test
    void getById_findOne() {
        final Boat actual = target.findById(boat.getId()).get();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(boat.getId(),actual.getId());
    }

    @Test
    void getAll() {
        final List<Boat> actual = target.getAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1,actual.size());
    }

    @Test
    void create_failSave() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->target.create(null));
    }

    @Test
    void update_notFoundAuto() {
        final Boat someBoat = createDefaultBoat();
        final boolean actual = target.update(someBoat.getId(),someBoat.getModel(),someBoat.getManufacturer(),someBoat.getPrice(),someBoat.getCubicCapacity());
        Assertions.assertFalse(actual);
    }

    @Test
    void update_succesfullUpdate() {
        boat.setPrice(BigDecimal.TEN);
        final  boolean actual = target.update(boat.getId(),boat.getModel(),boat.getManufacturer(),boat.getPrice(),boat.getCubicCapacity());
        Assertions.assertTrue(actual);
        final Boat actualBoat = target.findById(boat.getId()).get();
        Assertions.assertEquals(BigDecimal.TEN,actualBoat.getPrice());
    }

    @Test
    void delete_notFound() {
        final boolean actual = target.delete("someId");
        Assertions.assertFalse(actual);
    }
    @Test
    void delete_failureDelete() {
        target.delete(boat.getId());
        Assertions.assertEquals(0,target.getAll().size());
    }
}