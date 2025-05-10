package com.htwdresden.buerger_banse.fms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.htwdresden.buerger_banse.fms.model.FleetUser;

public interface FleetUserRepository extends CrudRepository<FleetUser, Long> {

    // Finde FleetUser anhand der userID
    FleetUser findByUserID(int userID);

    // Alle Benutzer eines bestimmten Tenants
    List<FleetUser> findByTenant_Id(Long tenantId);
}
