package ua.cars.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Car extends Vehicle {
    private String bodyType;

    public Car(String model, Manufacturer manufacturer, BigDecimal price, String bodyType) {
        super(model, manufacturer, price);
        this.bodyType = bodyType;
    }
    public Car(String id, String model, Manufacturer manufacturer, BigDecimal price, String bodyType) {
        super(model, manufacturer, price);
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "bodyType='" + bodyType + '\'' +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
