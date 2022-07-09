package ua.cars;

import ua.cars.entity.Boat;
import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.entity.Moto;
import ua.cars.service.BoatService;

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
        System.out.println("\nВыбери номер марки авто (0-3) из перечисленых : ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nВведи модель авто: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nВнеси цену авто: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nВведи тип кузова авто: ");
        String bodyType = (reader.readLine());
        Car car = new Car(model, manufacturer, price, bodyType);
        return car;
    }
    public Moto createMotoInput (BufferedReader reader) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nВыбери номер марки atv (0-3) из перечисленых : ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nВведи модель atv: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nВнеси цену atv: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nВведи количество мест: ");
        int sitsNumber = in.nextInt();
        Moto moto = new Moto(model, manufacturer, price, sitsNumber);
        return moto;
    }
    public Boat createBoatInput (BufferedReader reader) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nВыбери номер марки катера (0-3) из перечисленых : ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nВведи модель катера: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nВнеси цену катера: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nВведи обьем двигателя катера: ");
        BigDecimal cubicCapacity = in.nextBigDecimal();
        Boat boat = new Boat(model, manufacturer, price, cubicCapacity);
        return boat;
    }

    public void carOutput (Car car) {
        System.out.println("________________________\n");
        System.out.println(car.getManufacturer());
        System.out.println(car.getModel());
        System.out.println(car.getBodyType());
        System.out.println(car.getPrice());
        System.out.println(car.getId());
    }
    public void motoOutput (Moto moto) {
        System.out.println("________________________\n");
        System.out.println(moto.getManufacturer());
        System.out.println(moto.getModel());
        System.out.println(moto.getSitsNumber());
        System.out.println(moto.getPrice());
        System.out.println(moto.getId());
    }
    public void boatOutput (Boat boat) {
        System.out.println("________________________\n");
        System.out.println(boat.getManufacturer());
        System.out.println(boat.getModel());
        System.out.println(boat.getCubicCapacity());
        System.out.println(boat.getPrice());
        System.out.println(boat.getId());
    }

    public Car updateCarInput (BufferedReader reader, String id) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nВыбери номер марки авто (0-3) из перечисленых : ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nВведи модель авто: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nВнеси цену авто: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nВведи тип кузова авто: ");
        String bodyType = (reader.readLine());
        Car car = new Car(id, model, manufacturer, price, bodyType);
        return car;
    }
    public Moto updateMotoInput (BufferedReader reader, String id) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nВыбери номер марки atv (0-3) из перечисленых : ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nВведи модель atv: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nВнеси цену atv: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nВведи количество мест: ");
        int sitsNumber = in.nextInt();
        Moto moto = new Moto(id, model, manufacturer, price, sitsNumber);
        return moto;
    }
    public Boat updateBoatInput (BufferedReader reader, String id) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("\nВыбери номер марки катера (0-3) из перечисленых : ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        System.out.println("\nВведи модель катера: ");
        String model = (reader.readLine()).toUpperCase();
        System.out.println("\nВнеси цену катера: ");
        BigDecimal price = in.nextBigDecimal();
        System.out.println("\nВведи обьем двигателя катера: ");
        BigDecimal cubicCapacity = in.nextBigDecimal();
        Boat boat = new Boat(id, model, manufacturer, price, cubicCapacity);
        return boat;
    }
}
