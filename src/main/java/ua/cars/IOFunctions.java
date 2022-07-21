package ua.cars;

import ua.cars.entity.vehicle.Boat;
import ua.cars.entity.vehicle.Car;
import ua.cars.entity.vehicle.Manufacturer;
import ua.cars.entity.vehicle.Moto;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class IOFunctions {

    static final Manufacturer[] values = Manufacturer.values();
    static final List<Manufacturer> valuesList = List.of(values);

    public Car createCarInput (BufferedReader reader) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nСhoose the car manufacturer from below (0-3): ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nInput model: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nInput price: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nInput bodytype: ");
        String bodyType = (reader.readLine());
        Car car = new Car(model, manufacturer, price, bodyType,100);
        return car;
    }
    public Moto createMotoInput (BufferedReader reader) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nСhoose the atv manufacturer from below (0-3): ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nInput atv model: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nInput atv price: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nInput number of sits: ");
        int sitsNumber = in.nextInt();
        Moto moto = new Moto(model, manufacturer, price, sitsNumber,100);
        return moto;
    }
    public Boat createBoatInput (BufferedReader reader) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nСhoose the boat manufacturer from below (0-3): ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nInput boat model: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nInput boat price: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nInput engine cubic capacity: ");
        BigDecimal cubicCapacity = in.nextBigDecimal();
        Boat boat = new Boat(model, manufacturer, price, cubicCapacity,100);
        return boat;
    }

    public Car updateCarInput (BufferedReader reader, String id) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nСhoose the car manufacturer from below (0-3): ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nInput model: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nInput price: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nInput bodytype: ");
        String bodyType = (reader.readLine());
        Car car = new Car(id, model, manufacturer, price, bodyType,100);
        return car;
    }
    public Moto updateMotoInput (BufferedReader reader, String id) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nСhoose the atv manufacturer from below (0-3): ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nInput atv model: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nInput atv price: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nInput number of sits: ");
        int sitsNumber = in.nextInt();
        Moto moto = new Moto(id, model, manufacturer, price, sitsNumber,100);
        return moto;
    }
    public Boat updateBoatInput (BufferedReader reader, String id) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nСhoose the boat manufacturer from below (0-3): ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nInput boat model: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nInput boat price: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nInput engine cubic capacity: ");
        BigDecimal cubicCapacity = in.nextBigDecimal();
        Boat boat = new Boat(id, model, manufacturer, price, cubicCapacity,100);
        return boat;
    }
}
