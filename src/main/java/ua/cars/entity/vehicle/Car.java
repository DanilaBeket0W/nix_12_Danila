package ua.cars.entity.vehicle;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Car extends Vehicle {
    private String bodyType;

    public Car(String model, Manufacturer manufacturer, BigDecimal price, String bodyType, int count) {
        super(model, manufacturer, price, count);
        this.bodyType = bodyType;
    }
    public Car(String id, String model, Manufacturer manufacturer, BigDecimal price, String bodyType, int count) {
        super(model, manufacturer, price , count);
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
