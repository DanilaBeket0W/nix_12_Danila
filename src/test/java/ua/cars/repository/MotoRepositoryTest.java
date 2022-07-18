package ua.cars.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.cars.entity.Manufacturer;
import ua.cars.entity.Moto;

import java.math.BigDecimal;
import java.util.List;



class MotoRepositoryTest {
    private MotoRepository target;
    private Moto moto;

    private Moto createDefaultMoto() {
        return new Moto("Model", Manufacturer.HONDA, BigDecimal.ZERO,0);
    }

    @BeforeEach
    void setUp() {
        target = new MotoRepository();
        moto = createDefaultMoto();
        target.create(moto);
    }

    @Test
    void getById_findOne() {
        final Moto actual = target.findById(moto.getId()).get();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(moto.getId(),actual.getId());
    }

    @Test
    void getAll() {
        final List<Moto> actual = target.getAll();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1,actual.size());
    }

    @Test
    void create_failSave() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->target.create(null));
    }

    @Test
    void update_notFoundAuto() {
        final Moto someMoto = createDefaultMoto();
        final boolean actual = target.update(someMoto.getId(),someMoto.getModel(),someMoto.getManufacturer(),someMoto.getPrice(),someMoto.getSitsNumber());
        Assertions.assertFalse(actual);
    }

    @Test
    void update() {
        moto.setPrice(BigDecimal.TEN);
        final  boolean actual = target.update(moto.getId(),moto.getModel(),moto.getManufacturer(),moto.getPrice(),moto.getSitsNumber());
        Assertions.assertTrue(actual);
        final Moto actualAuto = target.findById(moto.getId()).get();
        Assertions.assertEquals(BigDecimal.TEN,actualAuto.getPrice());
    }

    @Test
    void delete_notFound() {
        final boolean actual = target.delete("someId");
        Assertions.assertFalse(actual);
    }
    @Test
    void delete_failureDelete() {
        target.delete(moto.getId());
        Assertions.assertEquals(0,target.getAll().size());
    }

}