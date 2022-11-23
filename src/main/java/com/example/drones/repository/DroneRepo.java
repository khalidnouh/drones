package com.example.drones.repository;

import com.example.drones.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepo extends JpaRepository<Drone,Long> {

    Optional<Drone> findBySerialNumber(String serialNo);


    List<Drone> findAllByBatteryCapacityGreaterThan(double batteryCapicity);
    List<Drone> findAllByBatteryCapacityLessThanEqual(double batteryCapicity);

}
