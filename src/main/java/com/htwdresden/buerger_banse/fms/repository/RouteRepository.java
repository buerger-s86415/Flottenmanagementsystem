package com.htwdresden.buerger_banse.fms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.htwdresden.buerger_banse.fms.model.Route;

public interface RouteRepository extends CrudRepository<Route, Long> {

    // Finde Route anhand des Namens
    Route findByShortName(String shortName);

    // Alle Routen eines bestimmten Tenants
    List<Route> findByTenant_Id(Long tenantId);
}
