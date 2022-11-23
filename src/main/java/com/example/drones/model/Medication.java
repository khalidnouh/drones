package com.example.drones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Entity(name = "medication")
@Data
public class Medication  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @NonNull
    @Pattern(regexp = "^[a-zA-Z0-9]+(?:--?[a-zA-Z0-9]+)*$")
    @Column(name="name")
    private String name; // (allowed only letters, numbers, ‘-‘, ‘_’);
    @Column(name="weight")
    private double weight;
    @Pattern(regexp = "^[A-Z0-9]+(?:_[A-Z0-9]+)*$")
    @Column(name="code")
    private String code;//(allowed only upper case letters, underscore and numbers);
    @Lob
    @Column(name="CATEGORY_PHOTO")
    private byte[] image;
    @ManyToOne
    @JoinColumn(name="drone_id", nullable=false)
    @JsonIgnore
    private Drone drone;
    public Medication() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}