package ua.cars.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class Moto extends Vehicle{
    private int sitsNumber;

    public Moto(String model, Manufacturer manufacturer, BigDecimal price, int sitsNumber) {
        super(model, manufacturer, price);
        this.sitsNumber = sitsNumber;
    }
    public Moto(String id, String model, Manufacturer manufacturer, BigDecimal price, int sitsNumber) {
        super(model, manufacturer, price);
        this.sitsNumber = sitsNumber;
    }

    @Override
    public String toString() {
        return "MotoEntity{" +
                "sitsNumber='" + sitsNumber + '\'' +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
