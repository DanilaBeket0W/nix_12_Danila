package ua.cars;


import ua.cars.entity.Boat;
import ua.cars.entity.Car;
import ua.cars.entity.Moto;
import ua.cars.service.BoatService;
import ua.cars.service.CarService;
import ua.cars.service.MotoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class UIrun {
    private static final CarService carService = new CarService();
    private static final MotoService motoService = new MotoService();
    private static final BoatService boatService = new BoatService();
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
        System.out.println("Выбери что хочешь сделать:\n1 - добавить транспорт\n2 - обновить транспорт\n3 - удалить транспорт\n4 - найти транспорт по номеру id\n5 - посмотреть весь транспорт\n0 - для выхода\n");
        String line = reader.readLine();
        switch (line) {
            case "0" -> System.exit(0);
            case "1" -> createNavigation(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findALL();
            default -> System.out.println("Выбери вариант 0-5");
        }
    }
    private static void createNavigation(BufferedReader reader) throws IOException {
        System.out.println("Выбери какой тип транспорта хочешь добавить:\n1 - auto\n2 - atv\n3 - boat\n0 - для выхода в главное меню\n");
        String line = reader.readLine();
        switch (line) {
            case "0" -> navigation(reader);
            case "1" -> createAuto(reader);
            case "2" -> createMoto(reader);
            case "3" -> createBoat(reader);
            default -> System.out.println("Выбери вариант 0-3");
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
        System.out.println("\nВведи id транспорта которого хочешь обновить:");
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
        System.out.println("\nТранспорт с введеным id обновлен.");
    }
    private static void delete(BufferedReader reader) throws IOException {
        System.out.println("\nВведи id транспорта которого хочешь удалить:");
        String id = reader.readLine();
        carService.delete(id);
        motoService.delete(id);
        boatService.delete(id);
        System.out.println("\nТранспорт с введеным id удален.");
    }
    private static void findById(BufferedReader reader) throws IOException {
        System.out.println("\nведи id транспорта которого хочешь найти:");
        String id = reader.readLine();
        Car car = carService.findByid(id);
        if(car != null)IO_FUNCTIONS.carOutput(car);
        Moto atv = motoService.findByid(id);
        if (atv != null)IO_FUNCTIONS.motoOutput(atv);
        Boat boat = boatService.findByid(id);
        if (boat != null)IO_FUNCTIONS.boatOutput(boat);
    }
    private static void findALL(){
        for (Car auto : carService.findALL()) {
            IO_FUNCTIONS.carOutput(auto);
        }
        for (Moto atv : motoService.findALL()) {
            IO_FUNCTIONS.motoOutput(atv);
        }
        for (Boat boat : boatService.findALL()) {
            IO_FUNCTIONS.boatOutput(boat);
        }
    }
}
