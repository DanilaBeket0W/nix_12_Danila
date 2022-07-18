package ua.cars.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import ua.cars.entity.Boat;
import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.repository.BoatRepository;
import ua.cars.repository.CarRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BoatServiceTest {

    private BoatService target;
    private BoatRepository boatRepository;
    private static Boat createDefaultBoat() {
        return new Boat("NONE", Manufacturer.NONE, BigDecimal.ZERO, BigDecimal.valueOf(1000));
    }

    @BeforeEach
    void setUp() {
        boatRepository = mock(BoatRepository.class);
        target = new BoatService(boatRepository);
    }

    @Test
    void create() {
        final Boat defaultAuto = createDefaultBoat();
        target.create(defaultAuto);
        Mockito.verify(boatRepository).create(defaultAuto);
    }

    @Test
    void create_checkArg() {
        final Boat boat = createDefaultBoat();

        when(boatRepository.create(argThat(new ArgumentMatcher<Boat>() {
            @Override
            public boolean matches(final Boat kater) {
                return boat.getModel().equals(kater.getModel());
            }
        }))).thenReturn(Optional.of(boat));
        target.create(boat);
        Mockito.verify(boatRepository).create(boat);
    }

    @Test
    void update() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->target.update("","",Manufacturer.NONE,BigDecimal.ZERO,BigDecimal.valueOf(1000)));
    }

    @Test
    void delete() {
        final Boat defaultBoat = createDefaultBoat();
        target.delete(defaultBoat.getId());
        Mockito.verify(boatRepository).delete(defaultBoat.getId());
    }

    @Test
    void findById() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        target.findById("");
        Mockito.verify(boatRepository).findById(captor.capture());
        Assertions.assertEquals("",captor.getValue());
    }

    @Test
    void findByModel() {
        BoatService repMock = mock(BoatService.class);
        Mockito.doThrow(new IllegalArgumentException("Error occurred"))
                .when(repMock)
                .findByModel("");
        repMock.findByModel("");
    }

    @Test
    void findALL() {
        List<Boat> boats = List.of(createDefaultBoat(),createDefaultBoat());
        Mockito.when(boatRepository.getAll()).thenReturn(boats);
        target.findALL();
    }

    @Test
    void findAllBySpecificManufacturer_getNoneManufacturer() {
        BoatService repMock = mock(BoatService.class);
        Mockito.doThrow(new IllegalArgumentException("Invalid Manufacturer"))
                .when(repMock)
                .findAllBySpecificManufacturer(Manufacturer.NONE);
        repMock.findAllBySpecificManufacturer(Manufacturer.NONE);
    }

    @Test
    void findAllBySpecificManufacturer_callMethod() {
        final Boat defaultBoat = createDefaultBoat();
        target.findAllBySpecificManufacturer(defaultBoat.getManufacturer());
        Mockito.verify(boatRepository).findAllBySpecificManufacturer();
    }
}