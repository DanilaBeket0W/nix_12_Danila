package ua.cars.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import ua.cars.entity.Manufacturer;
import ua.cars.entity.Moto;
import ua.cars.repository.MotoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MotoServiceTest {

    private MotoService target;
    private MotoRepository motoRepository;
    private static Moto createDefaultMoto() {
        return new Moto("NONE", Manufacturer.NONE, BigDecimal.ZERO,0);
    }

    @BeforeEach
    void setUp() {
        motoRepository = mock(MotoRepository.class);
        target = new MotoService(motoRepository);
    }

    @Test
    void create() {
        final Moto defaultMoto = createDefaultMoto();
        target.create(defaultMoto);
        Mockito.verify(motoRepository).create(defaultMoto);
    }

    @Test
    void create_checkArg() {
        final Moto atv = createDefaultMoto();
        when(motoRepository.create(argThat(new ArgumentMatcher<Moto>() {
            @Override
            public boolean matches(final Moto moto) {
                return atv.getModel().equals(moto.getModel());
            }
        }))).thenReturn(Optional.of(atv));
        target.create(atv);
        Mockito.verify(motoRepository).create(atv);
    }

    @Test
    void update() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->target.update("","",Manufacturer.NONE,BigDecimal.ZERO,0));

    }

    @Test
    void delete() {
        final Moto defaultMoto = createDefaultMoto();
        target.delete(defaultMoto.getId());
        Mockito.verify(motoRepository).delete(defaultMoto.getId());
    }

    @Test
    void findById() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        target.findById("");
        Mockito.verify(motoRepository).findById(captor.capture());
        Assertions.assertEquals("",captor.getValue());
    }

    @Test
    void findByModel() {
        MotoService repMock = mock(MotoService.class);
        Mockito.doThrow(new IllegalArgumentException("Error occurred"))
                .when(repMock)
                .findByModel("");
        repMock.findByModel("");
    }

    @Test
    void findALL() {
        List<Moto> atvs = List.of(createDefaultMoto(),createDefaultMoto());
        Mockito.when(motoRepository.getAll()).thenReturn(atvs);
        target.findALL();
    }

    @Test
    void findAllBySpecificManufacturer_getNoneManufacturer() {
        MotoService repMock = mock(MotoService.class);
        Mockito.doThrow(new IllegalArgumentException("Invalid Manufacturer"))
                .when(repMock)
                .findAllBySpecificManufacturer(Manufacturer.NONE);
        repMock.findAllBySpecificManufacturer(Manufacturer.NONE);
    }

    @Test
    void findAllBySpecificManufacturer() {
        final Moto defaultMoto = createDefaultMoto();
        target.findAllBySpecificManufacturer(defaultMoto.getManufacturer());
        Mockito.verify(motoRepository).findAllBySpecificManufacturer();
    }
}