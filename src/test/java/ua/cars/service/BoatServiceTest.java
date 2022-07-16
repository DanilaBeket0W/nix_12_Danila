package ua.cars.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import ua.cars.entity.Boat;
import ua.cars.entity.Manufacturer;
import ua.cars.repository.BoatRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BoatServiceTest {

    private Boat createDefaultBoat() {
        return new Boat("Model", Manufacturer.HONDA, BigDecimal.ZERO,BigDecimal.valueOf(1000));
    }
    private BoatService target;
    private BoatRepository boatRepository;

    @BeforeEach
    void setUp() {
        boatRepository = mock(BoatRepository.class);
        target = new BoatService(boatRepository);
    }

    @Test
    void create() {
        target.create(createDefaultBoat());
        Mockito.verify(boatRepository).create(Mockito.any());
    }

    @Test
    void create_checkArg() {
        final BoatRepository boatRepository = mock(BoatRepository.class);
        when(boatRepository.create(argThat(new ArgumentMatcher<Boat>() {
            @Override
            public boolean matches(final Boat boat) {
                assertEquals("someModel",((Boat)boat).getModel());
                return true;
            }
        }))).thenReturn(Boolean.TRUE);
        final Boat boat = createDefaultBoat();
        boat.setModel("someModel");
        boatRepository.create(boat);
    }

    @Test
    void update() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->target.update("","",Manufacturer.NONE,BigDecimal.ZERO,BigDecimal.valueOf(1000)));
    }

    @Test
    void delete() {
        BoatRepository repMock = mock(BoatRepository.class);
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
        Mockito.verify(boatRepository).getById(captor.capture());
        Assertions.assertEquals("",captor.getValue());
    }

    @Test
    void findALL() {
        List<Boat> boats = List.of(createDefaultBoat(),createDefaultBoat());
        Mockito.when(boatRepository.getAll()).thenReturn(boats);
        target.findALL();
    }
}