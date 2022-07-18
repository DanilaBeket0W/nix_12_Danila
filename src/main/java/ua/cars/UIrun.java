package ua.cars;


import ua.cars.entity.Boat;
import ua.cars.entity.Car;
import ua.cars.entity.Manufacturer;
import ua.cars.entity.Moto;
import ua.cars.repository.BoatRepository;
import ua.cars.repository.CarRepository;
import ua.cars.repository.MotoRepository;
import ua.cars.service.BoatService;
import ua.cars.service.CarService;
import ua.cars.service.MotoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class UIrun {
    static final Manufacturer[] values = Manufacturer.values();
    static final List<Manufacturer> valuesList = List.of(values);
    private static final CarService carService = new CarService(new CarRepository());
    private static final MotoService motoService = new MotoService(new MotoRepository());
    private static final BoatService boatService = new BoatService(new BoatRepository());
    private static final IOFunctions IO_FUNCTIONS = new IOFunctions();

    public static void start() {
        System.out.println("CARS CRUD");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println();
                navigation(reader);
            }
        } catch (IOException e) {
            System.out.println("ERROR" + e);
        }
    }


    private static void navigation(BufferedReader reader) throws IOException {
        System.out.println("Choose an action:\n1 - add transport\n2 - update transport\n3 - delete transport\n4 - find transport by id\n5 - show all transport\n6 - find all transport by specific manufacturer\n7 - find transport by model\n0 - to exit\n");
        String line = reader.readLine();
        switch (line) {
            case "0" -> System.exit(0);
            case "1" -> createNavigation(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findALL();
            case "6" -> findAllBySpecificManufacturer();
            case "7" -> findByModel(reader);
            default -> System.out.println("Choose 0-5");
        }
    }

    private static void createNavigation(BufferedReader reader) throws IOException {
        System.out.println("Choose type of transport:\n1 - auto\n2 - atv\n3 - boat\n0 - back to meny\n");
        String line = reader.readLine();
        switch (line) {
            case "0" -> navigation(reader);
            case "1" -> createAuto(reader);
            case "2" -> createMoto(reader);
            case "3" -> createBoat(reader);
            default -> System.out.println("Choose 0-3");
        }
    }

    private static void createAuto(BufferedReader reader) throws IOException {
        Car car = IO_FUNCTIONS.createCarInput(reader);
        carService.create(car);
    }

    private static void createMoto(BufferedReader reader) throws IOException {
        Moto moto = IO_FUNCTIONS.createMotoInput(reader);
        motoService.create(moto);
    }

    private static void createBoat(BufferedReader reader) throws IOException {
        Boat boat = IO_FUNCTIONS.createBoatInput(reader);
        boatService.create(boat);
    }

    private static void update(BufferedReader reader) throws IOException {
        System.out.println("\nInput id to update please:");
        String id = reader.readLine();
        Optional<Car> car = carService.findById(id);
        car.ifPresent(auto -> {
            System.out.println("Car with ID: " + id + "\nis: " + auto);
            Car updatedCar = null;
            try {
                updatedCar = IO_FUNCTIONS.updateCarInput(reader, id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            carService.update(id, updatedCar.getModel(), updatedCar.getManufacturer(), updatedCar.getPrice(), updatedCar.getBodyType());
        });
        Optional<Moto> atv = motoService.findById(id);
        atv.ifPresent(moto -> {
            System.out.println("ATV with ID: " + id + "\nis: " + moto);
            Moto updatedMoto = null;
            try {
                updatedMoto = IO_FUNCTIONS.updateMotoInput(reader, id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            motoService.update(id, updatedMoto.getModel(), updatedMoto.getManufacturer(), updatedMoto.getPrice(), updatedMoto.getSitsNumber());
        });
        Optional<Boat> boat = boatService.findById(id);
        boat.ifPresent(kater -> {
            System.out.println("Boat with ID: " + id + "\nis: " + kater);
            Boat updatedBoat = null;
            try {
                updatedBoat = IO_FUNCTIONS.updateBoatInput(reader, id);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            boatService.update(id, updatedBoat.getModel(), updatedBoat.getManufacturer(), updatedBoat.getPrice(), updatedBoat.getCubicCapacity());
        });
        System.out.println("\nTransport with this id updated.");
    }

    private static void delete(BufferedReader reader) throws IOException {
        System.out.println("\nInput id to delete please:");
        String id = reader.readLine();
        carService.delete(id);
        motoService.delete(id);
        boatService.delete(id);
        System.out.println("\nTransport with this id deleted.");
    }

    private static void findById(BufferedReader reader) throws IOException {
        System.out.println("\nInput id to search please:");
        String id = reader.readLine();
        Car car = carService.findById(id).orElseGet(() -> {
            System.out.println("Database don`t exist auto with id " + id);
            return createDefaultAuto();
        });
        if (!car.equals(createDefaultAuto())) {
            System.out.println(car);
        }
        Moto atv = motoService.findById(id).orElseGet(() -> {
            System.out.println("Database don`t exist atv with id " + id);
            return createDefaultMoto();
        });
        if (!atv.equals(createDefaultMoto())) {
            System.out.println(atv);
        }
        Boat boat = boatService.findById(id).orElseGet(() -> {
            System.out.println("Database don`t exist boat with id " + id);
            return createDefaultBoat();
        });
        if (!boat.equals(createDefaultBoat())) {
            System.out.println(boat);
        }
    }

    private static void findALL() {
        for (Car auto : carService.findALL()) {
            System.out.println(auto);
        }
        for (Moto atv : motoService.findALL()) {
            System.out.println(atv);
        }
        for (Boat boat : boatService.findALL()) {
            System.out.println(boat);
        }
    }

    private static void findAllBySpecificManufacturer() {
        Scanner in = new Scanner(System.in);
        System.out.println("\nСhoose the car manufacturer from below (0-3): ");
        System.out.println(valuesList);
        int index = in.nextInt();
        Manufacturer manufacturer = valuesList.get(index);
        List<Car> carList = carService.findAllBySpecificManufacturer(manufacturer);
        if (carList.size() > 0) {
            System.out.println(carList);
        }
        List<Moto> atvList = motoService.findAllBySpecificManufacturer(manufacturer);
        if (atvList.size() > 0) {
            System.out.println(atvList);
        }
        List<Boat> boatList = boatService.findAllBySpecificManufacturer(manufacturer);
        if (boatList.size() > 0) {
            System.out.println(boatList);
        }
        if (carList.size() < 0 && boatList.size() < 0 && atvList.size() < 0) {
            System.out.println("Can`t to find transport with manufacturer " + manufacturer);
        }
    }

    private static void findByModel(BufferedReader reader) throws IOException {
        System.out.println("\nInput model: ");
        String model = (reader.readLine()).toUpperCase();
        carService.findByModel(model);
        motoService.findByModel(model);
        boatService.findByModel(model);
    }

    private static Car createDefaultAuto() {
        return new Car("NONE", Manufacturer.NONE, BigDecimal.ZERO, "NONE");
    }

    private static Moto createDefaultMoto() {
        return new Moto("NONE", Manufacturer.NONE, BigDecimal.ZERO, 0);
    }

    private static Boat createDefaultBoat() {
        return new Boat("NONE", Manufacturer.NONE, BigDecimal.ZERO, BigDecimal.valueOf(1000));
    }


}
