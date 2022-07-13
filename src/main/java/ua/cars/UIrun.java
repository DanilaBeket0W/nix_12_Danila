package ua.cars;


import ua.cars.entity.Boat;
import ua.cars.entity.Car;
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


public class UIrun {
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
        System.out.println("Choose an action:\n1 - add transport\n2 - update transport\n3 - delete transport\n4 - find transport by id\n5 - show all transport\n0 - to exit\n");
        String line = reader.readLine();
        switch (line) {
            case "0" -> System.exit(0);
            case "1" -> createNavigation(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findALL();
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
        Car car = carService.findByid(id);
        if(car != null){
            Car updatedCar = IO_FUNCTIONS.updateCarInput(reader, id);
            carService.update(id,updatedCar.getModel(),updatedCar.getManufacturer(),updatedCar.getPrice(),updatedCar.getBodyType());
        }
        Moto atv = motoService.findByid(id);
        if (atv != null){
            Moto updatedMoto = IO_FUNCTIONS.updateMotoInput(reader, atv.getId());
            motoService.update(id,updatedMoto.getModel(),updatedMoto.getManufacturer(),updatedMoto.getPrice(),updatedMoto.getSitsNumber());
        }
        Boat boat = boatService.findByid(id);
        if (boat != null){
            Boat updatedBoat = IO_FUNCTIONS.updateBoatInput(reader, boat.getId());
            boatService.update(id,updatedBoat.getModel(),updatedBoat.getManufacturer(),updatedBoat.getPrice(),updatedBoat.getCubicCapacity());
        }
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
        Car car = carService.findByid(id);
        if(car != null){
            System.out.println(car);}
        Moto atv = motoService.findByid(id);
        if (atv != null){
            System.out.println(atv);}
        Boat boat = boatService.findByid(id);
        if (boat != null){
            System.out.println(boat);}
    }
    private static void findALL(){
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

}
