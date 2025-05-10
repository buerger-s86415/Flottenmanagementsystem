package com.htwdresden.buerger_banse.fms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.htwdresden.buerger_banse.fms.model.UGV;

public interface UGVRepository extends CrudRepository<UGV, Long> {
    UGV findByUgvId(String ugvId);
    List<UGV> findByTenant_Id(Long tenantId); // Suche alle UGVs zu einem Tenant
}