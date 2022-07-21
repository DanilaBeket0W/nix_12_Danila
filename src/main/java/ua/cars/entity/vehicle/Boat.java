package ua.cars.entity.vehicle;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Boat extends Vehicle{


    private BigDecimal cubicCapacity;

    public Boat(String model, Manufacturer manufacturer, BigDecimal price, BigDecimal cubicCapacity, int count) {
        super(model, manufacturer, price, count);
        this.cubicCapacity = cubicCapacity;
    }
    public Boat(String id, String model, Manufacturer manufacturer, BigDecimal price, BigDecimal cubicCapacity, int count) {
        super(model, manufacturer, price , count);
        this.cubicCapacity = cubicCapacity;
    }

    @Override
    public String toString() {
        return "BoatEntity{" +
                "cubicCapacity='" + cubicCapacity + '\'' +
                ", id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
