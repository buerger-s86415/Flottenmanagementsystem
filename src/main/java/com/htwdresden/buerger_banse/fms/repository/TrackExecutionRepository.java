package com.htwdresden.buerger_banse.fms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.htwdresden.buerger_banse.fms.model.TrackExecution;

public interface TrackExecutionRepository extends CrudRepository<TrackExecution, Long> {

    // Finde alle TrackExecutions für einen bestimmten Tenant
    List<TrackExecution> findByTenant_Id(Long tenantId);

    // Finde alle TrackExecutions eines bestimmten UGVs
    List<TrackExecution> findByUgv_Id(Long ugvId);

    // Optional: TrackExecutions nach FleetUser
    List<TrackExecution> findByFleetUser_Id(Long userId);
}
