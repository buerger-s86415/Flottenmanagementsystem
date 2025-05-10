package com.htwdresden.buerger_banse.fms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class TrackExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private FleetUser fleetUser;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ugv_id")
    private UGV ugv;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "position_exec_id")
    private PositionExec positionExec;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    public TrackExecution() {}

    public TrackExecution(Route route, FleetUser fleetUser, UGV ugv, PositionExec positionExec, Tenant tenant) {
        this.route = route;
        this.fleetUser = fleetUser;
        this.ugv = ugv;
        this.positionExec = positionExec;
        this.tenant = tenant;
    }

    public Long getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public FleetUser getFleetUser() {
        return fleetUser;
    }

    public void setFleetUser(FleetUser fleetUser) {
        this.fleetUser = fleetUser;
    }

    public UGV getUgv() {
        return ugv;
    }

    public void setUgv(UGV ugv) {
        this.ugv = ugv;
    }

    public PositionExec getPositionExec() {
        return positionExec;
    }

    public void setPositionExec(PositionExec positionExec) {
        this.positionExec = positionExec;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
