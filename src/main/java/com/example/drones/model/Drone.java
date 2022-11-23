package com.example.drones.model;

import com.example.drones.model.enums.Model;
import com.example.drones.model.enums.State;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "drone")
public class Drone implements Serializable {
    public Drone() {
    }

    public Drone(long id, String serialNumber, Model model, double weightLimit, double batteryCapacity, State state, List<Medication> medications) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.medications = medications;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "serialNumber")
    @Size(max = 100)
    private String serialNumber;
    @Column(name = "model")
    private Model model;
    @Column(name = "weightLimit")
    private double weightLimit;
    @Column(name = "batteryCapacity")
    private double batteryCapacity;
    @Column(name = "state")
    private State state;
    @OneToMany(mappedBy = "drone")
    private List<Medication>medications;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}