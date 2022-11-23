package com.example.drones.service;

import com.example.drones.model.AuditEventLog;
import com.example.drones.model.Drone;
import com.example.drones.model.Medication;
import com.example.drones.model.enums.State;
import com.example.drones.repository.AuditEventLogRepo;
import com.example.drones.repository.DroneRepo;
import com.example.drones.repository.MedicationRepo;
import com.example.drones.request.LoadMedicationsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DispatchServiceImpl implements DispatchService {
    @Autowired
    private DroneRepo droneRepo;
    @Autowired
    private MedicationRepo medicationRepo;
    @Autowired
    AuditEventLogRepo auditEventLogRepo;

    @Override
    public Drone registerDrone(Drone drone) {
        return droneRepo.save(drone);
    }

    @Override
    public ResponseEntity<List<Drone>> getAll() {
        return  ResponseEntity.ok(droneRepo.findAll());
    }

    @Override
    public ResponseEntity<?> loadMedications(LoadMedicationsRequest request) {
        Optional<Drone> drone = droneRepo.findBySerialNumber(request.getDronSerialNumber());
        if (!drone.isPresent())
            return new ResponseEntity<>("Cann't find drone with this serial no:" + request.getDronSerialNumber(), HttpStatus.OK);
        if (drone.get().getBatteryCapacity() < 25)
            return new ResponseEntity<>("Drone cann't load medications as  battery capacity below 25 ", HttpStatus.OK);
        double medicationWeight = 0;
        for (Medication medication : request.getMedications()) {
            medicationWeight += medication.getWeight();
        }
        //get drone by serialno
        if (medicationWeight > drone.get().getWeightLimit())
            return new ResponseEntity<>("Drone can't load with more weight that it can carry ", HttpStatus.OK);
        request.getMedications().forEach(medication -> {
            medication.setDrone(drone.get());
            medication = medicationRepo.save(medication);
        });
        //update drone status
        drone.get().setState(State.LOADED);
        droneRepo.save(drone.get());
        return new ResponseEntity<>("Medications loaded successfully on drone :" + drone.get().getSerialNumber(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> checkingLoadedMedication(String droneSerialNo) {
        Optional<Drone> drone = droneRepo.findBySerialNumber(droneSerialNo);
        if (!drone.isPresent())
            return new ResponseEntity<>("Cann't find drone with this serial no:" + droneSerialNo, HttpStatus.OK);
        List<Medication> medications=medicationRepo.findAllByDrone(drone.get());
        return ResponseEntity.ok(medications);
    }

    @Override
    public ResponseEntity<?> checkDroneBattery(String droneSerialNo) {
        Optional<Drone> drone = droneRepo.findBySerialNumber(droneSerialNo);
        if (!drone.isPresent())
            return new ResponseEntity<>("Cann't find drone with this serial no:" + droneSerialNo, HttpStatus.OK);
        return ResponseEntity.ok(drone.get().getBatteryCapacity());

    }

    @Override
    public ResponseEntity<?> checkAvailableDronesForLoading() {
        List<Drone> availableDrones=droneRepo.findAllByBatteryCapacityGreaterThan(25);
        if (availableDrones.size()==0)
            return new ResponseEntity<>("there is no vailable drones for loading", HttpStatus.OK);
        return  ResponseEntity.ok(availableDrones);

    }

    @Override
    public void checkDronesBattery() {
        AuditEventLog auditEventLog=new AuditEventLog();
        auditEventLog.setMessage("check Drones Battery periodic task");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        auditEventLog.setTime(date);
        auditEventLogRepo.save(auditEventLog);

    }

    @Override
    public ResponseEntity<?> listAudit() {
        return ResponseEntity.ok(auditEventLogRepo.findAll());
    }
}
