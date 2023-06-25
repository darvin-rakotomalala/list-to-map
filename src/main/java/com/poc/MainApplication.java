package com.poc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class MainApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("############################   RUN   ############################");

        String line = "------------------------------------------------------------------";

        // input list of `Color` objects
        List<Color> colors = new ArrayList<>();

        colors.add(new Color("RED", "#FF0000"));
        colors.add(new Color("BLUE", "#0000FF"));
        colors.add(new Color("GREEN", "#008000"));

        Map<String, String> mapColor = new HashMap<>();

        // construct key-value pairs from `name` and `code` fields of `Color` class
        for (Color ob : colors) {
            mapColor.put(ob.getName(), ob.getCode());
        }

        log.info("--- List colors : " + colors);
        log.info("--- Map colors : " + mapColor);

        log.info(line);

        // construct key-value pairs from `name` and `code` fields of `Color` class using Java 8 Stream
        Map<String, String> map = colors.stream()
                .collect(Collectors.toMap(Color::getName, Color::getCode));

        log.info("--- List colors using Java 8 Stream : " + colors);
        log.info("--- Map colors using Java 8 Stream : " + map);

        log.info(line);

        // Use Collectors.groupingBy()
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle("Bike", "Harley Davidson"),
                new Vehicle("Bike", "Triumph"),
                new Vehicle("Car", "Mercedes"),
                new Vehicle("Car", "Audi"),
                new Vehicle("Car", "BMW"));

        Map<String, List<String>> multimap = vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getType,
                        Collectors.mapping(Vehicle::getCompany, Collectors.toList())));

        log.info("--- list to map using Java 8 Stream : " + multimap);

        log.info(line);

        //  In Java 8, you can loop a Map with forEach + lambda expression.
        multimap.forEach((k, v) -> {
            log.info("--- key : " + k);
            log.info("--- value : " + v.toString());
        });

        log.info(line);

        // Version without using the Stream API
        Map<String, List<String>> multimapVehicles = new HashMap<>();
        for (Vehicle vehicle : vehicles) {
            multimapVehicles.computeIfAbsent(vehicle.getType(), k -> new ArrayList<>())
                    .add(vehicle.getCompany());
        }
        log.info("--- list to map : " + multimapVehicles);

    }

}
