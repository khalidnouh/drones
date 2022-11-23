package com.example.drones.service;

import com.example.drones.model.Drone;
import com.example.drones.model.Medication;
import com.example.drones.request.LoadMedicationsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface DispatchService {
    Drone registerDrone( Drone drone);
    ResponseEntity<List<Drone>>getAll();
    ResponseEntity<?> loadMedications(LoadMedicationsRequest request);
    ResponseEntity<?> checkingLoadedMedication(String droneSerialNo);
    ResponseEntity<?> checkDroneBattery(String droneSerialNo);

    ResponseEntity<?> checkAvailableDronesForLoading();
    void checkDronesBattery();
    ResponseEntity<?>listAudit();
}
