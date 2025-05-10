package com.htwdresden.buerger_banse.fms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UGV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ugvId;
    private String description;
    private float maxSpeed;
    private float batteryLevel;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    public UGV() {

    }

    public UGV(String ugvId, String description, float maxSpeed, float batteryLevel, Tenant tenant) {
        this.ugvId = ugvId;
        this.description = description;
        this.maxSpeed = maxSpeed;
        this.batteryLevel = batteryLevel;
        this.tenant = tenant;
    }

    // Getters und Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUgvId() {
        return ugvId;
    }

    public void setUgvId(String ugvId) {
        this.ugvId = ugvId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(float batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
    
}

