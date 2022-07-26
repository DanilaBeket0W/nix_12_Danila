package ua.cars;

import ua.cars.entity.LinkedTransportGarage;
import ua.cars.entity.Node;
import ua.cars.entity.TreeTransportGarage;
import ua.cars.entity.vehicle.Car;
import ua.cars.entity.vehicle.Manufacturer;
import ua.cars.repository.CarRepository;
import ua.cars.service.CarService;

import java.math.BigDecimal;
import java.util.*;

public class CarsMain {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
        //UIrun.start(); //надо раскоментировать чтобы запустить основной функционал

        Car car = new Car("some", Manufacturer.BMW, BigDecimal.valueOf(10000), "some", 10);
        Car car1 = new Car("first", Manufacturer.BMW, BigDecimal.valueOf(15000), "first", 1000);
        Car car2 = new Car("second", Manufacturer.BMW, BigDecimal.valueOf(13000), "second", 100);
        Car car3 = new Car("third", Manufacturer.BMW, BigDecimal.valueOf(1000), "third", 1);
        Car car4 = new Car("fourth", Manufacturer.BMW, BigDecimal.valueOf(50000), "fourth", 100000);
        Car car5 = new Car("fifth", Manufacturer.BMW, BigDecimal.valueOf(1), "fifth", 0);


        //TEST MY TREE
        TreeTransportGarage myTree = new TreeTransportGarage();
        myTree.insertNode(car);
        myTree.insertNode(car1);
        myTree.insertNode(car2);
        myTree.insertNode(car3);
        myTree.insertNode(car4);
        myTree.insertNode(car5);
        myTree.printTree();
        System.out.println(myTree.rightBranchPriceCount());
        System.out.println(myTree.leftBranchPriceCount());
        myTree.deleteNode(car3);
        myTree.printTree();
        Optional<Node> foundNode = myTree.findNodeByValue(car);
        foundNode.get().printNode();

    }
}

