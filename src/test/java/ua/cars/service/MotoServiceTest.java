package ua.cars.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import ua.cars.entity.Manufacturer;
import ua.cars.entity.Moto;
import ua.cars.repository.CarRepository;
import ua.cars.repository.MotoRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MotoServiceTest {
    private Moto createDefaultMoto() {
        return new Moto("Model", Manufacturer.HONDA, BigDecimal.ZERO,2);
    }
    private MotoService target;
    private MotoRepository motoRepository;

    @BeforeEach
    void setUp() {
        motoRepository = mock(MotoRepository.class);
        target = new MotoService(motoRepository);
    }

    @Test
    void create() {
        target.create(createDefaultMoto());
        Mockito.verify(motoRepository).create(Mockito.any());
    }

    @Test
    void create_checkArg() {
        final MotoRepository motoRepository = mock(MotoRepository.class);
        when(motoRepository.create(argThat(new ArgumentMatcher<Moto>() {
            @Override
            public boolean matches(final Moto moto) {
                assertEquals("someModel",((Moto)moto).getModel());
                return true;
            }
        }))).thenReturn(Boolean.TRUE);
        final Moto moto = createDefaultMoto();
        moto.setModel("someModel");
        motoRepository.create(moto);
    }

    @Test
    void update() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->target.update("","",Manufacturer.NONE,BigDecimal.ZERO,0));
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
        Mockito.verify(motoRepository).getById(captor.capture());
        Assertions.assertEquals("",captor.getValue());
    }

    @Test
    void findALL() {
        List<Moto> motos = List.of(createDefaultMoto(),createDefaultMoto());
        Mockito.when(motoRepository.getAll()).thenReturn(motos);
        target.findALL();
    }
}