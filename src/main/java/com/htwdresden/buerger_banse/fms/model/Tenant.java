package com.htwdresden.buerger_banse.fms.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int tenantID;

    @JsonIgnore
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Route> routes;

    @JsonIgnore
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FleetUser> fleetUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrackExecution> trackExecutions;

    @JsonIgnore
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UGV> ugvs;

    public Tenant() {
    }

    public Tenant(int tenantID) {
        this.tenantID = tenantID;
    }

    public Tenant(Long id) {
        this.id = id;
    }
    // Getters und Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTenantID() {
        return tenantID;
    }

    public void setTenantID(int tenantID) {
        this.tenantID = tenantID;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<FleetUser> getFleetUsers() {
        return fleetUsers;
    }

    public void setFleetUsers(List<FleetUser> fleetUsers) {
        this.fleetUsers = fleetUsers;
    }

    public List<TrackExecution> getTrackExecutions() {
        return trackExecutions;
    }

    public void setTrackExecutions(List<TrackExecution> trackExecutions) {
        this.trackExecutions = trackExecutions;
    }

    public List<UGV> getUgvs() {
        return ugvs;
    }

    public void setUgvs(List<UGV> ugvs) {
        this.ugvs = ugvs;
    }
    
}