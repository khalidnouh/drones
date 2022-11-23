package com.example.drones.controller;

import com.example.drones.model.Drone;
import com.example.drones.request.LoadMedicationsRequest;
import com.example.drones.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dispatchController")
public class DispatchController {

    @Autowired
    DispatchService dispatchService;
    //- registering a drone;
    @PostMapping("/registerDrone")
    public ResponseEntity<?> registerDrone(@RequestBody @Valid Drone drone) {
        Drone savedDrone = dispatchService.registerDrone(drone);
        return new ResponseEntity<>("Drone registered successfully: " + savedDrone.getId(), HttpStatus.OK);
    }

    @GetMapping("/getAllDrones")
    public ResponseEntity<?> getAllDrones() {
        return dispatchService.getAll();
    }


    @GetMapping("/listAudit")
    public ResponseEntity<?> getLogs() {
        return dispatchService.listAudit();
    }

    //- loading a drone with medication items;
    @PostMapping("/loadMedications")
    public ResponseEntity<?> loadMedications(@RequestBody @Valid LoadMedicationsRequest request) {
        return dispatchService.loadMedications(request);
    }

    //- checking loaded medication items for a given drone;
    @GetMapping("/loadMedications")
    public ResponseEntity<?> checkingLoadedMedication(@RequestParam("droneSerialNo") String droneSerialNo) {
        return dispatchService.checkingLoadedMedication(droneSerialNo);
    }

    //- checking available drones for loading;
    @GetMapping("/checkAvailableDrones")
    public ResponseEntity<?> checkAvailableDronesForLoading() {
        return dispatchService.checkAvailableDronesForLoading();
    }

    //- check drone battery level for a given drone;
    @GetMapping("/checkDroneBattery")
    public ResponseEntity<?> checkDroneBattery(@RequestParam("droneSerialNo") String droneSerialNo) {
        return dispatchService.checkDroneBattery(droneSerialNo);
    }

}
