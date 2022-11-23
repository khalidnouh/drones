package com.example.drones.request;

import com.example.drones.model.Medication;

import java.io.Serializable;
import java.util.List;

public class LoadMedicationsRequest implements Serializable {
   private String dronSerialNumber;
   private List<Medication>medications;

    public String getDronSerialNumber() {
        return dronSerialNumber;
    }

    public void setDronSerialNumber(String dronSerialNumber) {
        this.dronSerialNumber = dronSerialNumber;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
